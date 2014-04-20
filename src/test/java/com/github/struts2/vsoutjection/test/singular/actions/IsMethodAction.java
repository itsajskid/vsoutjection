package com.github.struts2.vsoutjection.test.singular.actions;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;

import com.github.struts2.vsoutjection.annotations.ValueStack;
import com.opensymphony.xwork2.ActionSupport;

@Namespace("/")
@SuppressWarnings("serial")
public class IsMethodAction extends ActionSupport {
	
	private Boolean boolData;
	
	@Action(value="is-method-test")
	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}

	@ValueStack
	public Boolean isBoolData() {
		boolData = Boolean.TRUE;
		return boolData;
	}

	public void setBoolData(Boolean boolData) {
		this.boolData = boolData;
	}
	
	

}
