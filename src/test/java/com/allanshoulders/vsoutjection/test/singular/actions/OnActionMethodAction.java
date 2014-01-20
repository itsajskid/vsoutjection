package com.allanshoulders.vsoutjection.test.singular.actions;

import org.apache.struts2.convention.annotation.Action;

import com.allanshoulders.vsoutjection.annotations.VSOutject;
import com.allanshoulders.vsoutjection.test.dao.Data;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class OnActionMethodAction extends ActionSupport {

	private Data onActionData;
	
	@Action(value="onaction-method-test")
	@Override
	public String execute() throws Exception {
		onActionData.setValue1("onAction");
		onActionData.setValue2("foo");
		return SUCCESS;
	}
	
	@Action(value="no-stack-onaction-method-test")
	public String noValueOnStack () {
		return SUCCESS;
	}

	@VSOutject(onAction="onaction-method-test", newInstance=true)
	public Data getOnActionData() {
		return onActionData;
	}

	public void setOnActionData(Data onActionData) {
		this.onActionData = onActionData;
	}
	
}
