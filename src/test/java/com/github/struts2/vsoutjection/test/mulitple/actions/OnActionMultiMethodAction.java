package com.github.struts2.vsoutjection.test.mulitple.actions;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;

import com.github.struts2.vsoutjection.annotations.ValueStack;
import com.github.struts2.vsoutjection.test.dao.Data;
import com.opensymphony.xwork2.ActionSupport;

@Namespace("/")
@SuppressWarnings("serial")
public class OnActionMultiMethodAction extends ActionSupport {

	private Data defaultData;
	private Data customData;
	
	@Action(value="onaction-default-test")
	@Override
	public String execute() throws Exception {
		defaultData.setValue1("default");
		defaultData.setValue2("foo");
		return SUCCESS;
	}
	
	@Action(value="onaction-custom-test")
	public String customMethodAction () throws Exception {
		customData.setValue1("custom");
		customData.setValue2("foo");
		return SUCCESS;
	}

	@ValueStack(newInstance=true, onAction="onaction-default-test")
	public Data getDefaultData() {
		return defaultData;
	}

	public void setDefaultData(Data defaultData) {
		this.defaultData = defaultData;
	}

	@ValueStack(newInstance=true, onAction="onaction-custom-test")
	public Data getCustomData() {
		return customData;
	}

	public void setCustomData(Data customData) {
		this.customData = customData;
	}
	
}
