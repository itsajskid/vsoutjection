package com.github.struts2.vsoutjection.test.mulitple.actions;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;

import com.github.struts2.vsoutjection.annotations.ValueStack;
import com.github.struts2.vsoutjection.test.dao.Data;
import com.opensymphony.xwork2.ActionSupport;

@Namespace("/")
@SuppressWarnings("serial")
public class NonStandardMultiMethodAction extends ActionSupport {

	@Action(value="non-standard-multi-test")
	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
	
	@ValueStack
	public Data createData () {
		return new Data(1, "foo");
	}
	
	@ValueStack
	public Map<Integer, Data> createDataMap () {
		Map<Integer, Data> dataMap = new LinkedHashMap<Integer, Data>();
		dataMap.put(1, new Data(1, "foo"));
		dataMap.put(2, new Data(2, "bar"));
		dataMap.put(3, new Data(3, "baz"));
		return dataMap;
	}
}
