package com.allanshoulders.vsoutjection.test.singular.actions;

import org.apache.struts2.convention.annotation.Action;

import com.allanshoulders.vsoutjection.annotations.VSOutject;
import com.allanshoulders.vsoutjection.test.dao.Data;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class NewInstanceMethodAction extends ActionSupport {

	private Data newInstanceData;
	
	@Action(value="new-instance-method-test")
	@Override
	public String execute() throws Exception {
		newInstanceData.setValue1("NewInstance");
		newInstanceData.setValue2("Data");
		return SUCCESS;
	}
	
	@VSOutject(newInstance=true)
	public Data getNewInstanceData() {
		return newInstanceData;
	}

	public void setNewInstanceData(Data newInstanceData) {
		this.newInstanceData = newInstanceData;
	}	
}
