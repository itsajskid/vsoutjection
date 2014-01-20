package com.allanshoulders.vsoutjection.test.mulitple.actions;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;

import com.allanshoulders.vsoutjection.annotations.VSOutject;
import com.allanshoulders.vsoutjection.test.dao.Data;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class NonStandardMultiMethodAction extends ActionSupport {

	@Action(value="non-standard-multi-test")
	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
	
	@VSOutject
	public Data createData () {
		return new Data(1, "foo");
	}
	
	@VSOutject
	public Map<Integer, Data> createDataMap () {
		Map<Integer, Data> dataMap = new LinkedHashMap<Integer, Data>();
		dataMap.put(1, new Data(1, "foo"));
		dataMap.put(2, new Data(2, "bar"));
		dataMap.put(3, new Data(3, "baz"));
		return dataMap;
	}
}
