package com.allanshoulders.vsoutjection.test.singular.actions;

import org.apache.struts2.convention.annotation.Action;

import com.allanshoulders.vsoutjection.annotations.VSOutject;
import com.allanshoulders.vsoutjection.test.dao.Data;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class OutOfSyncFieldAction extends ActionSupport {

	@VSOutject
	private Data outOfSyncData = new Data("foo", "bar");
	
	@Action(value="out-of-sync-field-test")
	@Override
	public String execute() throws Exception {
		outOfSyncData = new Data("out-of-sync", "foo");
		return SUCCESS;
	}
	
	public Data getOutOfSyncData() {
		return outOfSyncData;
	}
}
