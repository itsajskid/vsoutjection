package com.allanshoulders.vsoutjection.test.singular.actions;

import org.apache.struts2.convention.annotation.Action;

import com.allanshoulders.vsoutjection.annotations.VSOutject;
import com.allanshoulders.vsoutjection.test.dao.Data;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class SimpleVSFieldAction extends ActionSupport {

	@VSOutject
	protected Data simpleData;
	
	public SimpleVSFieldAction() {
		simpleData = new Data("simple data", "field");
	}
	
	@Action(value="simple-vsfield-test")
	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
}
