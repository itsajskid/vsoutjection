package com.allanshoulders.vsoutjection.test.singular.actions;

import org.apache.struts2.convention.annotation.Action;

import com.allanshoulders.vsoutjection.annotations.VSOutject;
import com.allanshoulders.vsoutjection.test.dao.Data;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class SimpleVSMethodAction extends ActionSupport {

	private Data simpleData;
	
	@Action("simple-method-test")
	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}

	@VSOutject
	public Data getSimpleData() {
		simpleData = new Data("foo", "bar");
		return simpleData;
	}

	public void setSimpleData(Data simpleData) {
		this.simpleData = simpleData;
	}
	
}
