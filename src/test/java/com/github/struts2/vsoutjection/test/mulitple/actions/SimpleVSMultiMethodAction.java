package com.allanshoulders.vsoutjection.test.mulitple.actions;

import org.apache.struts2.convention.annotation.Action;

import com.allanshoulders.vsoutjection.annotations.VSOutject;
import com.allanshoulders.vsoutjection.test.dao.Data;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class SimpleVSMultiMethodAction extends ActionSupport {

	private Data simpleData;
	
	private Boolean simpleBool;
	
	public SimpleVSMultiMethodAction() {
		simpleData = new Data("simpleMethod", "foo");
		simpleBool = Boolean.TRUE;
	}
	
	@Action(value="simple-vs-multi-test")
	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}

	@VSOutject
	public Data getSimpleData() {
		return simpleData;
	}

	public void setSimpleData(Data simpleData) {
		this.simpleData = simpleData;
	}

	@VSOutject
	public Boolean isSimpleBool() {
		return simpleBool;
	}

	public void setSimpleBool(Boolean simpleBool) {
		this.simpleBool = simpleBool;
	}
	
}
