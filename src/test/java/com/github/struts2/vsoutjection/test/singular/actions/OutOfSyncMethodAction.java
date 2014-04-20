package com.github.struts2.vsoutjection.test.singular.actions;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;

import com.github.struts2.vsoutjection.annotations.ValueStack;
import com.github.struts2.vsoutjection.test.dao.Data;
import com.opensymphony.xwork2.ActionSupport;

@Namespace("/")
@SuppressWarnings("serial")
public class OutOfSyncMethodAction extends ActionSupport {

	private Data outOfSyncData;
	
	@Action(value="out-of-sync-test")
	@Override
	public String execute() throws Exception {
		outOfSyncData.setValue1("valueStack value 1");
		outOfSyncData.setValue2("valueStack value 2");
		
		outOfSyncData = new Data("out of sync", "data");
		return SUCCESS;
	}

	@ValueStack(newInstance=true)
	public Data getOutOfSyncData() {
		return outOfSyncData;
	}

	public void setOutOfSyncData(Data outOfSyncData) {
		this.outOfSyncData = outOfSyncData;
	}
	
}
