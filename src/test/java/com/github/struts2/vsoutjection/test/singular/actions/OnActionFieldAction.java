package com.github.struts2.vsoutjection.test.singular.actions;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;

import com.github.struts2.vsoutjection.annotations.ValueStack;
import com.github.struts2.vsoutjection.test.dao.Data;
import com.opensymphony.xwork2.ActionSupport;

@Namespace("/")
@SuppressWarnings("serial")
public class OnActionFieldAction extends ActionSupport {

	@ValueStack(newInstance=true, onAction="on-action-field-test")
	private Data onActionFieldData;
	
	@Action(value="onaction-test")
	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
	
	@Action(value="on-action-field-test")
	public String onActionExecute () throws Exception {
		onActionFieldData.setValue1("on-action");
		onActionFieldData.setValue2("foo");
		return SUCCESS;
	}
}
