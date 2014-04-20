package com.github.struts2.vsoutjection.test.singular.actions;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;

import com.github.struts2.vsoutjection.annotations.ValueStack;
import com.github.struts2.vsoutjection.test.dao.Data;
import com.opensymphony.xwork2.ActionSupport;

@Namespace("/")
@SuppressWarnings("serial")
public class NewInstanceFieldAction extends ActionSupport {

	@ValueStack(newInstance=true)
	private Data newInstanceData;
	
	@Action(value="new-instance-field-test")
	@Override
	public String execute() throws Exception {
		newInstanceData.setValue1("new-instance");
		newInstanceData.setValue2("foo");
		return SUCCESS;
	}
}
