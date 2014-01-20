package com.allanshoulders.vsoutjection.processors;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.allanshoulders.vsoutjection.annotations.VSOutject;
import com.allanshoulders.vsoutjection.enums.ProcessorEnum;
import com.allanshoulders.vsoutjection.exceptions.IllegalAnnotatedMethodException;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.util.CompoundRoot;
import com.opensymphony.xwork2.util.ValueStack;

/**
 * 
 * <p>
 * This class provides the various operations needed to manipulate the 
 * {@link ValueStack}.  
 * </p>
 * 
 * @author Allan J. Shoulders
 * @see 1.2.0
 *
 */
public class ValueStackOutjectionManager {
	
	private ValueStack valueStack;
	
	/**
	 * Single argument constructor must have a {@link ValueStack} reference
	 * which the operations defined can be manipulated.
	 * 
	 * @param valueStack the {@link ValueStack} reference.
	 */
	public ValueStackOutjectionManager (ValueStack valueStack) {
		this.valueStack = valueStack;
	}
	
	/**
	 * <p>
	 * This method moves the top level object on the ValueStack one level
	 * down.
	 * </p>
	 * 
	 * @param compoundRoot Object provided by the framework to access the ValueStack
	 * as a list.
	 */
	@SuppressWarnings("unchecked")
	public void moveFromTopLevel () {
		CompoundRoot compoundRoot = valueStack.getRoot();
		
		if (compoundRoot != null && compoundRoot.size() > 1) {
			Object obj = compoundRoot.pop();
			compoundRoot.add(compoundRoot.size()-1, obj);
		}
	}	
	
	/**
	 * <p>
	 * This method pushes an object onto the ValueStack. If the {@link VSOutject}
	 * parameter's isTopLevel attribute is set to true, then the object is pushed one
	 * level below. Otherwise, the object that is pushed onto the ValueStack is at
	 * the very top of the stack.
	 * </p>
	 * 
	 * @param model the object to be pushed onto the ValueStack.
	 * @param vsOutject the {@link VSOutject} annotation belonging to the field
	 * or method of the model parameter.
	 */
	public void moveByTopLevelOption (Object model, VSOutject vsOutject) {
		valueStack.push(model);
		
		if (!vsOutject.isTopLevel()) {
			this.moveFromTopLevel();
		}
	}
	
	/**
	 * <p>
	 * Creates a new instance of {@link Field}'s type and 
	 * synchronizes the reference with that of the {@link Action} class.
	 * </p>
	 * 
	 * @param field the {@link VSOutject} annotated field reference.
	 * @param action the {@link Action} class reference.
	 * @return the instantiated reference created and set on the 
	 * {@link Action} class.
	 * @throws InstantiationException thrown if the class does not have a 
	 * no-argument constructor that can be called.
	 * @throws IllegalAccessException thrown if the field cannot be accessed
	 * for any reason.
	 */
	public Object addNewInstance (Field field, Action action) 
			throws InstantiationException, 
			IllegalAccessException {
		Object model = field.getType().newInstance();
		
		if (field.isAccessible()) {
			field.set(action, model);
		} else {
			field.setAccessible(true);
			field.set(action, model);
			field.setAccessible(false);
		}
		
		return model;
	}
	
	/**
	 * <p>
	 * Creates a new instance of {@link Method}'s return type and then
	 * synchronizes it with {@link Action} class. 
	 * </p>
	 * 
	 * <p>
	 * Typical use involves matching getter (get, is) and setter methods.
	 * That is, the method provided as a parameter should be a getter method
	 * that also has a setter method defined in the {@link Action} class. 
	 * The setter is required because the new instance must be synchronized 
	 * with the {@link Action} class.
	 * </p>
	 * 
	 * <p>
	 * If the method reference passed to this method is not a compliant
	 * getter method, an {@link IllegalAnnotatedMethodException} will be
	 * thrown.
	 * </p>
	 * 
	 * @param method the {@link VSOutject} method that the return type will 
	 * be extracted from.
	 * @param action the {@link Action} class reference.
	 * @return the instantiated reference and set on the {@link Action} class
	 * reference.
	 * @throws InstantiationException thrown if the return type belonging to the 
	 * method parameter does not have a no-argument constructor or for any reason cannot
	 * be instantiated.
	 * @throws IllegalAccessException thrown if the method parameter cannot be 
	 * accessed or invoked for any reason.
	 * @throws SecurityException if a security violation 
	 * (i.e. insufficient privileges) occurs.
	 * @throws NoSuchMethodException thrown if the setter method is not defined 
	 * in the {@link Action} parameter.
	 * @throws IllegalArgumentException thrown if the setter method is invoked
	 * and receives an incompatible type or subtype as a parameter.
	 * @throws IllegalAnnotatedMethodException is thrown when an {@link Action}
	 * class defines a getter method, but does not define a matching setter 
	 * method.
	 * @throws InvocationTargetException thrown if the {@link Action} class
	 * does not define the method it has been requested to invoke.
	 */
	public Object addNewInstance (Method method, Action action) 
			throws InstantiationException, 
			IllegalAccessException, 
			SecurityException, 
			NoSuchMethodException, 
			IllegalArgumentException,
			IllegalAnnotatedMethodException,
			InvocationTargetException {
		
		Object model = method.getReturnType().newInstance();
		
		StringBuilder setterStrBlder = null;
		
		for (ProcessorEnum procEnum : ProcessorEnum.values()) {
			
			if (procEnum != ProcessorEnum.SET && 
					method.getName().startsWith(procEnum.toString())) {
				
				setterStrBlder = new StringBuilder()
					.append(ProcessorEnum.SET.toString())
					.append(method.getName().substring(
							procEnum.name().length()));
				break;
			}
			
		}
		
		if (setterStrBlder == null) {
			throw new IllegalAnnotatedMethodException(
					"@VSOutject annotated methods using newInstance=true" + 
							" must be compliant getter/is method.");			
		}
		
		Method setMethod = action.getClass()
			.getMethod(setterStrBlder.toString(), model.getClass());
		setMethod.invoke(action, model);
		
		return model;
	}
	/**
	 * <p>
	 * Method returns a reference to the {@link Field} defined in the
	 * {@link Action} class even if the field has an access level that
	 * would normally prevent it from being accessed.
	 * </p>
	 * 
	 * @param field the inaccessible field that the returned reference
	 * will be copied from.
	 * @param action the {@link Action} class to which this field belongs to.
	 * @return a reference to the inaccessible field passed to this method.
	 * @throws IllegalArgumentException thrown if the field is not defined on
	 * in the {@link Action} class.
	 * @throws IllegalAccessException thrown if any failure to get a reference
	 * of the inaccessible field occurs.
	 */
	public Object getInaccessibleField (Field field, Action action) 
			throws IllegalArgumentException, 
			IllegalAccessException {
		Object model;
		
		if (field.isAccessible()) {
			model = field.get(action);
		} else {
			field.setAccessible(true);
			model = field.get(action);
			field.setAccessible(false);
		}
		
		return model;
		
	}	

}
