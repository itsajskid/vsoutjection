package com.github.struts2.vsoutjection.test.mulitple.actions;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;

import com.github.struts2.vsoutjection.annotations.ValueStack;
import com.github.struts2.vsoutjection.test.dao.Data;
import com.opensymphony.xwork2.ActionSupport;

@Namespace("/")
@SuppressWarnings("serial")
public class OnActionMultiFieldAction extends ActionSupport {
	
	@ValueStack(newInstance=true, onAction="onaction-defaultfield-test")
	private Data defaultData;
	
	@ValueStack(newInstance=true, onAction="onaction-customfield-test")
	private Data customData;
	
	@Action(value="onaction-defaultfield-test")
	@Override
	public String execute() throws Exception {
		defaultData.setValue1(1);
		defaultData.setValue2("foo");
		return SUCCESS;
	}
	
	@Action(value="onaction-customfield-test")
	public String customMethodAction () throws Exception {
		customData.setValue1(2);
		customData.setValue2("bar");
		return SUCCESS;
	}
}
