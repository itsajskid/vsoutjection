package com.allanshoulders.vsoutjection.test.mulitple.actions;

import org.apache.struts2.convention.annotation.Action;

import com.allanshoulders.vsoutjection.annotations.VSOutject;
import com.allanshoulders.vsoutjection.test.dao.Data;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class MoveTopLevelMultiMethodAction extends ActionSupport {

	private Data topLevelData;
	private Data secondLevelData;
	
	@Action(value="move-top-level-multimethod-test")
	@Override
	public String execute() throws Exception {
		topLevelData.setValue2("topLevelData");
		secondLevelData.setValue2("secondLevelData");
		return SUCCESS;
	}

	@VSOutject(newInstance=true, isTopLevel=true)
	public Data getTopLevelData() {
		return topLevelData;
	}

	public void setTopLevelData(Data topLevelData) {
		this.topLevelData = topLevelData;
	}

	@VSOutject(newInstance=true, isTopLevel=false)
	public Data getSecondLevelData() {
		return secondLevelData;
	}

	public void setSecondLevelData(Data secondLevelData) {
		this.secondLevelData = secondLevelData;
	}
}
