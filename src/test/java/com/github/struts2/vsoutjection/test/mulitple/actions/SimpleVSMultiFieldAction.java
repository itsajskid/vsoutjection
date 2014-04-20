package com.allanshoulders.vsoutjection.test.mulitple.actions;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;

import com.allanshoulders.vsoutjection.annotations.VSOutject;
import com.allanshoulders.vsoutjection.test.dao.Data;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class SimpleVSMultiFieldAction extends ActionSupport {

	@VSOutject
	private Data simpleData;
	
	@VSOutject
	private Map<String, Data> dataMap;
	
	public SimpleVSMultiFieldAction() {
		simpleData = new Data("foo", "bar");
		dataMap = new HashMap<String, Data>();
		dataMap.put("baz", simpleData);
	}
	
	@Action(value="simple-vs-field-test")
	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
}
