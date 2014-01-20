package com.allanshoulders.vsoutjection.processors;

import java.lang.reflect.Field;

import com.allanshoulders.vsoutjection.annotations.VSOutject;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.DefaultTextProvider;

/**
 * <p>
 * This class in an implementation of the {@link Processor} interface. It's main
 * purpose is to process any field level properties that have been annotated with
 * the {@link VSOutject} annotation.
 * </p>
 * 
 * <p>
 * In general, the fields are processed in the same order as a Stack. That is,
 * Last-In-First-Out (LIFO). The first annotated field will be processed
 * first, placing it at the bottom of the Stack (in most cases, not the 
 * absolute bottom as the {@link Action} class and 
 * {@link DefaultTextProvider} are processed first). 
 * Processing of the fields may vary depending on the value of certain
 * attributes associated with a given {@link VSOutject} annotation.
 * </p>
 * 
 * <p>
 * This class will <b>not process</b> inherited annotated fields.
 * </p>
 * 
 * @author Allan J. Shoulders
 * @version 1.2.0
 *
 */
public class ValueStackAnnotatedFieldsProcessor implements Processor {
	
	private ActionInvocation actionInvocation;

	/**
	 * Single argument constructor that accepts an {@link ActionInvocation} object.
	 * 
	 * @param actionInvocation the {@link ActionInvocation} object passed to an
	 * instance of this class.
	 */
	public ValueStackAnnotatedFieldsProcessor(ActionInvocation actionInvocation) {
		this.actionInvocation = actionInvocation;
	}

	/**
	 * <p>
	 * This overridden method provides the logic needed to process any field
	 * level {@link VSOutject} annotations. It searches through every field
	 * in the current action class for any {@link VSOutject} annotated fields.
	 * For all annotated fields, it will push those fields onto the ValueStack.
	 * </p>
	 * 

	 */
	@Override
	public void processAnnotations() throws Exception {
		Action action = (Action)actionInvocation.getAction();
		ValueStackOutjectionManager vsoMgr = 
				new ValueStackOutjectionManager(actionInvocation.getStack());
		Field[] fields = action.getClass().getDeclaredFields();
		Object model = null;
		
		for (Field field : fields) {
			if (field.isAnnotationPresent(VSOutject.class)) {
				VSOutject vsOutject = field.getAnnotation(VSOutject.class);
				
				if (vsOutject.onAction().length() > 0 && 
						!actionInvocation
						.getProxy()
						.getActionName().equals(vsOutject.onAction())) {
					continue;
				}
				
				if (vsOutject.newInstance()) {
					model = vsoMgr.addNewInstance(field, action);
				} else {
					model = vsoMgr.getInaccessibleField(field, action);
				}
				
				if (model != null ) {
					vsoMgr.moveByTopLevelOption(model, vsOutject);
				}
			}
		}
		
	}

}
