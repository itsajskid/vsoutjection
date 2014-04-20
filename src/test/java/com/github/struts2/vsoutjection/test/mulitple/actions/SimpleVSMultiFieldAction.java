package com.github.struts2.vsoutjection.test.mulitple.actions;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;

import com.github.struts2.vsoutjection.annotations.ValueStack;
import com.github.struts2.vsoutjection.test.dao.Data;
import com.opensymphony.xwork2.ActionSupport;

@Namespace("/")
@SuppressWarnings("serial")
public class SimpleVSMultiFieldAction extends ActionSupport {

	@ValueStack
	private Data simpleData;
	
	@ValueStack
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
