package com.github.struts2.vsoutjection.enums;

/**
 * <p>
 * This Enumeration is a set of constants used throughout the classes that implement
 * the {@link Processor} interface. The values of this Enumeration are traditional
 * Javabean prefixes for method names (get, set, is).
 * </p>
 * 
 * 
 * @author Allan J. Shoulders
 * @version 2.0.1
 *
 */
public enum ProcessorEnum {

	IS, SET, GET;
	
	@Override
	public String toString() {
		return this.name().toLowerCase();
	}
}
