package com.github.struts2.vsoutjection.test.singular.actions;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;

import com.github.struts2.vsoutjection.annotations.ValueStack;
import com.github.struts2.vsoutjection.test.dao.Data;
import com.opensymphony.xwork2.ActionSupport;

@Namespace("/")
@SuppressWarnings("serial")
public class OutOfSyncFieldAction extends ActionSupport {

	@ValueStack
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
