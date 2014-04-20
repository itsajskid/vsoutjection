package com.allanshoulders.vsoutjection.test.singular.actions;

import org.apache.struts2.convention.annotation.Action;

import com.allanshoulders.vsoutjection.annotations.VSOutject;
import com.allanshoulders.vsoutjection.test.dao.Data;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class NoMatchingSetterMethodAction extends ActionSupport {
	
	private Data data;
	
	@Action(value="no-matching-setter-test")
	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}

	@VSOutject(newInstance=true)
	public Data getData() {
		return data;
	}

}
