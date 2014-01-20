package com.allanshoulders.vsoutjection.processors;

import java.lang.reflect.Method;

import com.allanshoulders.vsoutjection.annotations.VSOutject;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.DefaultTextProvider;
/**
 * 
 * <p>
 * This class is an implementation of {@link Processor} interface. It's main
 * purpose is to process any {@link VSOutject} annotated methods. If processing
 * is successful, the result will be an object reference pushed onto the 
 * {@link ValueStack}.
 * </p>
 * 
 * <p>
 * In general, the fields are process in the same order as a Stack. That is
 * Last-In-First-Out (LIFO). So the first annotated method will be processed
 * first, placing it at the bottom of the Stack (in most cases, not the 
 * absolute bottom as the {@link Action} class and 
 * {@link DefaultTextProvider} are processed first). 
 * Processing of the methods may vary depending on the value of certain
 * attributes associated with a given {@link VSOutject} annotation.
 * </p>
 * 
 * <p>
 * Unlike it's field based counterpart, 
 * {@link ValueStackAnnotatedFieldsProcessor}, any inherited methods that are
 * {@link VSOutject} annotated will be processed in addition to any declared
 * by the action class.
 * </p>
 * 
 * @author Allan J. Shoulders
 * @version 1.2.0
 *
 */
public class ValueStackAnnotatedMethodsProcessor implements Processor {
	
	private ActionInvocation actionInvocation;

	public ValueStackAnnotatedMethodsProcessor(ActionInvocation actionInvocation) {
		this.actionInvocation = actionInvocation;	
	}

	@Override
	public void processAnnotations() throws Exception {
		Action action = (Action)actionInvocation.getAction();
		ValueStackOutjectionManager vsoMgr = 
				new ValueStackOutjectionManager(actionInvocation.getStack());
		Method[] methods = action.getClass().getMethods();
		Object model = null;
		
		for (Method method : methods) {
			if (method.isAnnotationPresent(VSOutject.class)) {
				VSOutject vsOutject = method.getAnnotation(VSOutject.class);
				
				if (vsOutject.onAction().length() > 0 &&
						!actionInvocation
						.getProxy()
						.getActionName().equals(vsOutject.onAction())) {
					
					continue;
				}
				
				if (vsOutject.newInstance()) {
					model = vsoMgr.addNewInstance(method, action);
				} else {
					model = method.invoke(action);
				}
				
				if (model != null) {
					vsoMgr.moveByTopLevelOption(model, vsOutject);
				}

			}
			
		}
		
	}
	
}
