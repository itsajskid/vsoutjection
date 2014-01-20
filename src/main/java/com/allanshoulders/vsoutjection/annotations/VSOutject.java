package com.allanshoulders.vsoutjection.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.allanshoulders.vsoutjection.interceptors.ValueStackOutjectionInterceptor;

/**
 * <p>
 * The VSOutject annotation allows certain accessor methods (getters in particular) to be
 * flagged. Getter methods that have been flagged will have their returned references
 * pushed onto the ValueStack. 
 * </p>
 * 
 * 
 * @author Allan J. Shoulders
 * @version 1.2.0
 * @see ValueStackOutjectionInterceptor
 *
 */

@Target(value={ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface VSOutject {
	/**
	 * <p>
	 * This attribute when set to false will alert the {@link ValueStackOutjectionInterceptor}
	 * to move the reference returned by the method one level below the top level object on
	 * the value stack. When this attribute is set to true, the reference returned by the
	 * method will be pushed to the very top of the ValueStack. This attribute is set to
	 * true by default.
	 * </p>
	 */
	boolean isTopLevel() default true;
	
	/**
	 * <p>
	 * This attribute when set to true will alert the {@link ValueStackOutjectionInterceptor}
	 * to create a new instance of the same type as the method's returned type. That instance
	 * will be pushed on to the ValueStack. The instance will also be set within the Action
	 * class. The Action class must have a corresponding setter method. The 
	 * {@link ValueStackOutjectionInterceptor} will create a new instance of the object even
	 * if the returned object by the getter method contains a reference. 
	 * </p>
	 * 
	 * <p>
	 * When the value of the this attribute is false, the Action class is responsible for
	 * ensuring the annotated method returns a non-null reference. 
	 * </p>
	 * 
	 * <p>
	 * This attribute is set to false by default.
	 * </p>
	 */
	boolean newInstance() default false;
	
	/**
	 * <p>
	 * This attribute when set to a Struts 2 action alias, will flag the 
	 * {@link ValueStackOutjectionInterceptor} to push the value of the annotated
	 * object onto the ValueStack <b>only</b> when that action alias is being executed.
	 * </p>
	 * 
	 * <p>
	 * This attribute is set to an empty string by default. When this
	 * attribute is set to an empty string or not set at all it will be ignored.
	 * </p>
	 * 
	 */
	String onAction() default "";
}
