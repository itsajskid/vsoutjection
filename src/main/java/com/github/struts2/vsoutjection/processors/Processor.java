package com.allanshoulders.vsoutjection.processors;

/**
 * <p>
 * The Processor interface defines a single method that provides logic
 * as to how to process any fields or methods that contain {@link VSOutject}
 * annotations.
 * </p>
 * 
 * 
 * @author Allan J. Shoulders
 * @version 1.2.1
 * 
 * 
 * @see ValueStackAnnotatedFieldsProcessor
 * @see ValueStackAnnotatedMethodsProcessor
 *
 */
public interface Processor {
	
	/**
	 * An abstract method that should provide logic that
	 * will process the {@link VSOutject} annotated methods or fields.
	 * 
	 * @throws Exception
	 */
	public abstract void processAnnotations() throws Exception;

}
