package com.github.struts2.vsoutjection.test.singular.actions;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;

import com.github.struts2.vsoutjection.annotations.ValueStack;
import com.github.struts2.vsoutjection.test.dao.Data;
import com.opensymphony.xwork2.ActionSupport;

@Namespace("/")
@SuppressWarnings("serial")
public class MoveTopLevelFieldAction extends ActionSupport {

	@ValueStack(newInstance=true, isTopLevel=false)
	private Data moveTopLevelData;
	
	@Action(value="move-top-level-test")
	@Override
	public String execute() throws Exception {
		moveTopLevelData.setValue1("move top level");
		moveTopLevelData.setValue2("foo");
		return SUCCESS;
	}
}
