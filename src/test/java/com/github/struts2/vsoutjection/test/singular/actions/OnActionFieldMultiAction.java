package com.github.struts2.vsoutjection.test.singular.actions;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;

import com.github.struts2.vsoutjection.annotations.ValueStack;
import com.github.struts2.vsoutjection.test.dao.Data;
import com.opensymphony.xwork2.ActionSupport;

@Namespace("/")
@SuppressWarnings("serial")
public class OnActionFieldMultiAction extends ActionSupport {

	@ValueStack(newInstance=true, onAction={"onaction-multifield-1", "onaction-multifield-2"})
	private Data data;
	
	@Action("onaction-multifield-1")
	public String onActionMulti1() {
		data.setValue1("foo");
		return SUCCESS;
	}
	
	@Action("onaction-multifield-2")
	public String onActionMulti2() {
		data.setValue1("bar");
		return SUCCESS;
	}
}
