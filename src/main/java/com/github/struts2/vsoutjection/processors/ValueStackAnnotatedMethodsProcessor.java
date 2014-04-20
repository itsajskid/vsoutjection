package com.github.struts2.vsoutjection.processors;

import java.lang.reflect.Method;

import org.apache.commons.lang3.ArrayUtils;

import com.github.struts2.vsoutjection.annotations.ValueStack;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;

/**
 * 
 * <p>
 * This class is an implementation of {@link Processor} interface. It's main
 * purpose is to process any {@link ValueStack} annotated methods. If processing
 * is successful, the result will be an object reference pushed onto the 
 * {@link ValueStack}.
 * </p>
 * 
 * <p>
 * There is no defined order that the annotated methods are processed. 
 * Processing of the fields may vary depending on the value of 
 * the <i>isTopLevel</i> attribute associated with a 
 * given {@link ValueStack} annotation.
 * </p>
 * 
 * <p>
 * Unlike it's field based counterpart, 
 * {@link ValueStackAnnotatedFieldsProcessor}, any inherited methods that are
 * {@link ValueStack} annotated will be processed in addition to any declared
 * by the action class.
 * </p>
 * 
 * @author Allan J. Shoulders
 * @version 1.2.1
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
			if (method.isAnnotationPresent(ValueStack.class)) {
				ValueStack vsAnnotation = method.getAnnotation(ValueStack.class);
				
				if (!ArrayUtils.isEmpty(vsAnnotation.onAction()) &&
						!ArrayUtils.contains(vsAnnotation.onAction(), 
								actionInvocation.getProxy().getActionName())) {
					continue;
				}
				
				if (vsAnnotation.newInstance()) {
					model = vsoMgr.addNewInstance(method, action);
				} else {
					model = method.invoke(action);
				}
				
				if (model != null) {
					vsoMgr.moveByTopLevelOption(model, vsAnnotation);
				}

			}
			
		}
		
	}
	
}
