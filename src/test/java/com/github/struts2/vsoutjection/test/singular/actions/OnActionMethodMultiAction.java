package com.github.struts2.vsoutjection.test.singular.actions;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;

import com.github.struts2.vsoutjection.annotations.ValueStack;
import com.github.struts2.vsoutjection.test.dao.Data;
import com.opensymphony.xwork2.ActionSupport;

@Namespace("/")
@SuppressWarnings("serial")
public class OnActionMethodMultiAction extends ActionSupport {

	private Data data;
	
	@Action("onaction-multimethod-action1")
	public String action1() {
		data.setValue1("foo");
		return SUCCESS;
	}
	
	@Action("onaction-multimethod-action2")
	public String action2() {
		data.setValue1("bar");
		return SUCCESS;
	}

	@ValueStack(newInstance=true, onAction={"onaction-multimethod-action1", 
			"onaction-multimethod-action2"})
	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}
	
}
