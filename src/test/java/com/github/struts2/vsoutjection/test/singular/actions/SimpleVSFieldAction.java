package com.github.struts2.vsoutjection.test.singular.actions;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;

import com.github.struts2.vsoutjection.annotations.ValueStack;
import com.github.struts2.vsoutjection.test.dao.Data;
import com.opensymphony.xwork2.ActionSupport;

@Namespace("/")
@SuppressWarnings("serial")
public class SimpleVSFieldAction extends ActionSupport {

	@ValueStack
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
