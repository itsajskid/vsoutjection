package com.allanshoulders.vsoutjection.test.singular.actions;

import org.apache.struts2.convention.annotation.Action;

import com.allanshoulders.vsoutjection.annotations.VSOutject;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class IsFieldAction extends ActionSupport {
	
	@VSOutject
	private boolean isFieldAction = true;

	@Action(value="is-field-test")
	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
}
