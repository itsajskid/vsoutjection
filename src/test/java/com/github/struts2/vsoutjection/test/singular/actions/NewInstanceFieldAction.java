package com.allanshoulders.vsoutjection.test.singular.actions;

import org.apache.struts2.convention.annotation.Action;

import com.allanshoulders.vsoutjection.annotations.VSOutject;
import com.allanshoulders.vsoutjection.test.dao.Data;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class NewInstanceFieldAction extends ActionSupport {

	@VSOutject(newInstance=true)
	private Data newInstanceData;
	
	@Action(value="new-instance-field-test")
	@Override
	public String execute() throws Exception {
		newInstanceData.setValue1("new-instance");
		newInstanceData.setValue2("foo");
		return SUCCESS;
	}
}
