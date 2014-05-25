package com.github.struts2.vsoutjection.test.mulitple.actions;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;

import com.github.struts2.vsoutjection.annotations.ValueStack;
import com.github.struts2.vsoutjection.test.dao.Data;
import com.opensymphony.xwork2.ActionSupport;

@Namespace("/")
@SuppressWarnings("serial")
public class OnActionMultiAction extends ActionSupport {

	@ValueStack(newInstance=true, onAction={"fielddata1-action", "methodfield1-action"})
	private Data fieldData1;
	
	@ValueStack(newInstance=true, onAction={"fielddata2-action", "methodfield2-action"})
	private Data fieldData2;
	
	private Data methodData1;
	private Data methodData2;
	
	@Action("fielddata1-action")
	public String field1Action () {
		fieldData1.setValue1("fieldData1");
		methodData1.setValue1("methodData1");
		return SUCCESS;
	}
	
	@Action("fielddata2-action")
	public String field2Action () {
		fieldData2.setValue1("fieldData2");
		methodData2.setValue1("methodData2");
		return SUCCESS;
	}
	
	@Action("methodfield1-action")
	public String method1Action () {
		fieldData1.setValue1("fieldData1");
		methodData1.setValue1("methodData1");
		return SUCCESS;
	}
	
	@Action("methodfield2-action")
	public String method2Action () {
		fieldData2.setValue1("fieldData2");
		methodData2.setValue1("methodData2");
		return SUCCESS;
	}

	@ValueStack(newInstance=true, onAction={"fielddata1-action", "methodfield1-action"})
	public Data getMethodData1() {
		return methodData1;
	}

	public void setMethodData1(Data methodData1) {
		this.methodData1 = methodData1;
	}

	@ValueStack(newInstance=true, onAction={"fielddata2-action", "methodfield2-action"})
	public Data getMethodData2() {
		return methodData2;
	}

	public void setMethodData2(Data methodData2) {
		this.methodData2 = methodData2;
	}
}
