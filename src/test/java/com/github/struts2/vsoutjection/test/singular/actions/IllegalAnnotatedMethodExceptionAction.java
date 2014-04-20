package com.github.struts2.vsoutjection.test.singular.actions;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;

import com.github.struts2.vsoutjection.annotations.ValueStack;
import com.github.struts2.vsoutjection.test.dao.Data;
import com.opensymphony.xwork2.ActionSupport;

@Namespace("/")
@SuppressWarnings("serial")
public class IllegalAnnotatedMethodExceptionAction extends ActionSupport {
	
	private Data illegalData;
	
	@Action(value="illegal-annotated-method-test")
	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}

	@ValueStack(newInstance=true)
	public Data illegalData() {
		return illegalData;
	}

}
