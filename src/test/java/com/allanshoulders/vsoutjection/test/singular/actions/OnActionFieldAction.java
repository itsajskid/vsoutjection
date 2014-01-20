package com.allanshoulders.vsoutjection.test.singular.actions;

import org.apache.struts2.convention.annotation.Action;

import com.allanshoulders.vsoutjection.annotations.VSOutject;
import com.allanshoulders.vsoutjection.test.dao.Data;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class OnActionFieldAction extends ActionSupport {

	@VSOutject(newInstance=true, onAction="on-action-field-test")
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
