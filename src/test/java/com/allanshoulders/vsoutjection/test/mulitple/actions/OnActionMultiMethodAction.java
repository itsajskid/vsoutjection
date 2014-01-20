package com.allanshoulders.vsoutjection.test.mulitple.actions;

import org.apache.struts2.convention.annotation.Action;

import com.allanshoulders.vsoutjection.annotations.VSOutject;
import com.allanshoulders.vsoutjection.test.dao.Data;
import com.opensymphony.xwork2.ActionSupport;

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

	@VSOutject(newInstance=true, onAction="onaction-default-test")
	public Data getDefaultData() {
		return defaultData;
	}

	public void setDefaultData(Data defaultData) {
		this.defaultData = defaultData;
	}

	@VSOutject(newInstance=true, onAction="onaction-custom-test")
	public Data getCustomData() {
		return customData;
	}

	public void setCustomData(Data customData) {
		this.customData = customData;
	}
	
}
