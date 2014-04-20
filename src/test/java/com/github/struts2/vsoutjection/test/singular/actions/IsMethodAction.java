package com.allanshoulders.vsoutjection.test.singular.actions;

import org.apache.struts2.convention.annotation.Action;

import com.allanshoulders.vsoutjection.annotations.VSOutject;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class IsMethodAction extends ActionSupport {
	
	private Boolean boolData;
	
	@Action(value="is-method-test")
	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}

	@VSOutject
	public Boolean isBoolData() {
		boolData = Boolean.TRUE;
		return boolData;
	}

	public void setBoolData(Boolean boolData) {
		this.boolData = boolData;
	}
	
	

}
