package com.github.struts2.vsoutjection.processors;

import java.lang.reflect.Field;

import org.apache.commons.lang3.ArrayUtils;

import com.github.struts2.vsoutjection.annotations.ValueStack;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;

/**
 * <p>
 * This class in an implementation of the {@link Processor} interface. It's main
 * purpose is to process any field level properties that have been annotated with
 * the {@link ValueStack} annotation.
 * </p>
 * 
 * <p>
 * There is no defined order that the annotated fields are processed. 
 * Placement of the fields on the @{link ValueStack} may vary depending 
 * on the value of the <i>isTopLevel</i> attribute associated with a 
 * given {@link ValueStack} annotation.
 * </p>
 * 
 * <p>
 * This class will <b>not process</b> inherited annotated fields.
 * </p>
 * 
 * @author Allan J. Shoulders
 * @version 2.0.1
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
	 * level {@link ValueStack} annotations. It searches through every field
	 * in the current action class for any {@link ValueStack} annotated fields.
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
			if (field.isAnnotationPresent(ValueStack.class)) {
				ValueStack vsAnnotation = field.getAnnotation(ValueStack.class);
				
				if (!ArrayUtils.isEmpty(vsAnnotation.onAction()) &&
						!ArrayUtils.contains(vsAnnotation.onAction(), 
								actionInvocation.getProxy().getActionName())) {
					continue;
				}
				
				if (vsAnnotation.newInstance()) {
					model = vsoMgr.addNewInstance(field, action);
				} else {
					model = vsoMgr.getInaccessibleField(field, action);
				}
				
				if (model != null ) {
					vsoMgr.moveByTopLevelOption(model, vsAnnotation);
				}
			}
		}
		
	}

}
