package com.allanshoulders.vsoutjection.test.singular.actions;

import org.apache.struts2.convention.annotation.Action;

import com.allanshoulders.vsoutjection.annotations.VSOutject;
import com.allanshoulders.vsoutjection.test.dao.Data;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class IllegalAnnotatedMethodExceptionAction extends ActionSupport {
	
	private Data illegalData;
	
	@Action(value="illegal-annotated-method-test")
	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}

	@VSOutject(newInstance=true)
	public Data illegalData() {
		return illegalData;
	}

}
