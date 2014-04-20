package com.allanshoulders.vsoutjection.test.singular.actions;

import org.apache.struts2.convention.annotation.Action;

import com.allanshoulders.vsoutjection.annotations.VSOutject;
import com.allanshoulders.vsoutjection.test.dao.Data;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class NonStandardMethodAction extends ActionSupport {
	
	@Action(value="non-standard-method-test")
	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}

	@VSOutject
	public Data model() {
		return new Data("model", "test");
	}
	
}
