package com.allanshoulders.vsoutjection.test.singular.actions;

import org.apache.struts2.convention.annotation.Action;

import com.allanshoulders.vsoutjection.annotations.VSOutject;
import com.allanshoulders.vsoutjection.test.dao.Data;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class MoveTopLevelFieldAction extends ActionSupport {

	@VSOutject(newInstance=true, isTopLevel=false)
	private Data moveTopLevelData;
	
	@Action(value="move-top-level-test")
	@Override
	public String execute() throws Exception {
		moveTopLevelData.setValue1("move top level");
		moveTopLevelData.setValue2("foo");
		return SUCCESS;
	}
}
