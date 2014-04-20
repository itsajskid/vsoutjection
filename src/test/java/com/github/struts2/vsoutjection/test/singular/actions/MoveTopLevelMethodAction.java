package com.github.struts2.vsoutjection.test.singular.actions;


import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;

import com.github.struts2.vsoutjection.annotations.ValueStack;
import com.github.struts2.vsoutjection.test.dao.Data;
import com.opensymphony.xwork2.ActionSupport;

@Namespace("/")
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

	@ValueStack(isTopLevel=false, newInstance=true)
	public Data getMoveData() {
		return moveData;
	}

	public void setMoveData(Data moveData) {
		this.moveData = moveData;
	}
	
}
