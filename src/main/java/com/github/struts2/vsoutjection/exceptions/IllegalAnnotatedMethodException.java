package com.github.struts2.vsoutjection.exceptions;

/**
 * <p>
 * IllegalAnnotatedMethodException is a checked exception that is thrown when
 * a {@link ValueStack} annotated method sets the newInstance attribute to true
 * and does not have a corresponding setter method defined.
 * </p>
 * 
 * 
 * @author Allan J. Shoulders
 * @version 2.0.1
 * 
 *
 */
public class IllegalAnnotatedMethodException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * No argument constructor to create an instance of this class.
	 */
	public IllegalAnnotatedMethodException() {
		super();
	}
	
	/**
	 * Single argument constructor which accepts a {@link String} message to be
	 * printed with the stracktrace.
	 * 
	 * @param message the {@link String} to be passed to instance of this exception.
	 */
	public IllegalAnnotatedMethodException(String message) {
		super(message);
	}

}
