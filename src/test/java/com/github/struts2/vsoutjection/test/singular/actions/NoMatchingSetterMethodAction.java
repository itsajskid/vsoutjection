package com.github.struts2.vsoutjection.test.singular.actions;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;

import com.github.struts2.vsoutjection.annotations.ValueStack;
import com.github.struts2.vsoutjection.test.dao.Data;
import com.opensymphony.xwork2.ActionSupport;

@Namespace("/")
@SuppressWarnings("serial")
public class NoMatchingSetterMethodAction extends ActionSupport {
	
	private Data data;
	
	@Action(value="no-matching-setter-test")
	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}

	@ValueStack(newInstance=true)
	public Data getData() {
		return data;
	}

}
