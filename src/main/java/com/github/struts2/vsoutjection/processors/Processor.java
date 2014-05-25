package com.github.struts2.vsoutjection.processors;

/**
 * <p>
 * The Processor interface defines a single method that provides logic
 * as to how to process any fields or methods that contain {@link ValueStack}
 * annotations.
 * </p>
 * 
 * 
 * @author Allan J. Shoulders
 * @version 2.0.1
 * 
 * 
 * @see ValueStackAnnotatedFieldsProcessor 
 * @see ValueStackAnnotatedMethodsProcessor
 *
 */
public interface Processor {
	
	/**
	 * An abstract method that should provide logic that
	 * will process the {@link ValueStack} annotated methods or fields.
	 * 
	 * @throws Exception
	 */
	public abstract void processAnnotations() throws Exception;

}
