package com.github.struts2.vsoutjection.test.mulitple.actions;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;

import com.github.struts2.vsoutjection.annotations.ValueStack;
import com.github.struts2.vsoutjection.test.dao.Data;
import com.opensymphony.xwork2.ActionSupport;

@Namespace("/")
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

	@ValueStack(newInstance=true, isTopLevel=true)
	public Data getTopLevelData() {
		return topLevelData;
	}

	public void setTopLevelData(Data topLevelData) {
		this.topLevelData = topLevelData;
	}

	@ValueStack(newInstance=true, isTopLevel=false)
	public Data getSecondLevelData() {
		return secondLevelData;
	}

	public void setSecondLevelData(Data secondLevelData) {
		this.secondLevelData = secondLevelData;
	}
}
