package com.github.struts2.vsoutjection.test.singular.actions;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;

import com.github.struts2.vsoutjection.annotations.ValueStack;
import com.github.struts2.vsoutjection.test.dao.Data;
import com.opensymphony.xwork2.ActionSupport;

@Namespace("/")
@SuppressWarnings("serial")
public class NonStandardMethodAction extends ActionSupport {
	
	@Action(value="non-standard-method-test")
	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}

	@ValueStack
	public Data model() {
		return new Data("model", "test");
	}
	
}
