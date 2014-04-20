package com.allanshoulders.vsoutjection.test.mulitple.actions;

import org.apache.struts2.convention.annotation.Action;

import com.allanshoulders.vsoutjection.annotations.VSOutject;
import com.allanshoulders.vsoutjection.test.dao.Data;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class MoveTopLevelMultiFieldAction extends ActionSupport {

	@VSOutject(isTopLevel=true)
	private Data topLevelData;
	
	@VSOutject(isTopLevel=false)
	private Data secondLevelData;
	
	public MoveTopLevelMultiFieldAction() {
		topLevelData = new Data(1);
		secondLevelData = new Data(2);
	}
	
	@Action(value="move-top-level-multifield-test")
	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
}
