package com.allanshoulders.vsoutjection.test.singular.actions;


import org.apache.struts2.convention.annotation.Action;

import com.allanshoulders.vsoutjection.annotations.VSOutject;
import com.allanshoulders.vsoutjection.test.dao.Data;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class MoveTopLevelMethodAction extends ActionSupport {
	
	private Data moveData;

	@Action(value="move-toplevel-method-test")
	@Override
	public String execute() throws Exception {
		moveData.setValue1("move data");
		moveData.setValue2("level below");
		return SUCCESS;
	}

	@VSOutject(isTopLevel=false, newInstance=true)
	public Data getMoveData() {
		return moveData;
	}

	public void setMoveData(Data moveData) {
		this.moveData = moveData;
	}
	
}
