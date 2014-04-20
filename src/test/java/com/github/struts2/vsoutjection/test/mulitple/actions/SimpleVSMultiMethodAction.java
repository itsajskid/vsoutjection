package com.github.struts2.vsoutjection.test.mulitple.actions;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;

import com.github.struts2.vsoutjection.annotations.ValueStack;
import com.github.struts2.vsoutjection.test.dao.Data;
import com.opensymphony.xwork2.ActionSupport;

@Namespace("/")
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

	@ValueStack
	public Data getSimpleData() {
		return simpleData;
	}

	public void setSimpleData(Data simpleData) {
		this.simpleData = simpleData;
	}

	@ValueStack
	public Boolean isSimpleBool() {
		return simpleBool;
	}

	public void setSimpleBool(Boolean simpleBool) {
		this.simpleBool = simpleBool;
	}
	
}
