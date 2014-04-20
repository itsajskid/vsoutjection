package com.github.struts2.vsoutjection.interceptors;

import com.github.struts2.vsoutjection.annotations.ValueStack;
import com.github.struts2.vsoutjection.processors.Processor;
import com.github.struts2.vsoutjection.processors.ValueStackAnnotatedFieldsProcessor;
import com.github.struts2.vsoutjection.processors.ValueStackAnnotatedMethodsProcessor;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.opensymphony.xwork2.interceptor.ParametersInterceptor;
/**
 * <p>
 * ValueStackOutjectionInterceptor is a subclass of {@link MethodFilterInterceptor}
 * that will push a Plain Old Java Object (POJO) onto the ValueStack.
 * </p>
 * 
 * <p>
 * Proper use of the ValueStackOutjectionInterceptor requires an Action class to annotate
 * its getter methods using the {@link ValueStack} annotations. For all getter methods
 * properly annotated, the ValueStackOutjectionInterceptor will find these methods, invoke
 * each annotated method, and push the returned reference onto the ValueStack.
 * </p>
 * 
 * <p>
 * The ValueStackOutjectionInterceptor can (if {@link ValueStack} annotation attributes are
 * configured correctly) create a new instance of the returned type of the annotated getter
 * method. It can also move the pushed reference one level below the top level object on the 
 * ValueStack if needed. See {@link ValueStack} for further details.
 * </p>
 * 
 * <p>
 * Similar to the use of the {@link ModelDriven} interface, one should be careful not to
 * reassign properties that have been pushed onto the ValueStack during execution of action
 * class methods. This will cause the data on the ValueStack to be out of sync with the newly
 * assigned property. Changes to the property will not be visible to the reference on the
 * ValueStack.
 * </p>
 * 
 * <p>
 * This interceptor should be applied to an interceptor stack that makes use of the
 * {@link ParametersInterceptor}. This interceptor should be used prior to.
 * </p>
 * 
 * <p>
 * Finally, the {@link ValueStack} annotation should be used with caution. Beware of pushing 
 * multiple objects on the ValueStack of the same type, or objects with the same property 
 * names. Fundamental rules regarding ValueStack assignment priorities will apply.
 * </p> 
 * 
 * 
 * @author Allan J. Shoulders
 * @version 1.2.1
 * @see ValueStack
 *
 */
public class ValueStackOutjectionInterceptor extends MethodFilterInterceptor {

	private static final long serialVersionUID = 1L;

	/**
	 * <p>
	 * Interceptor method that is called by the interceptor stack.
	 * 
	 * @param actionInvocation {@link ActionInvocation} provided by the framework.
	 * 
	 * @return {@link String} signaling whether to continue to the next
	 * interceptor or to interrupt the interceptor chain. 
	 * </p>
	 */
	@Override
	protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
		
		Processor[] processors = new Processor[]{
				new ValueStackAnnotatedMethodsProcessor(actionInvocation), 
				new ValueStackAnnotatedFieldsProcessor(actionInvocation)
		};
		
		for (Processor processor : processors) {
			processor.processAnnotations();
		}
		
		return actionInvocation.invoke();
	}

}
