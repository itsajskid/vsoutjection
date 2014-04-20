package com.github.struts2.vsoutjection.test.singular.actions;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;

import com.github.struts2.vsoutjection.annotations.ValueStack;
import com.github.struts2.vsoutjection.test.dao.Data;
import com.opensymphony.xwork2.ActionSupport;

@Namespace("/")
@SuppressWarnings("serial")
public class SimpleVSMethodAction extends ActionSupport {

	private Data simpleData;
	
	@Action(value="simple-method-test")
	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}

	@ValueStack
	public Data getSimpleData() {
		simpleData = new Data("foo", "bar");
		return simpleData;
	}

	public void setSimpleData(Data simpleData) {
		this.simpleData = simpleData;
	}
	
}
