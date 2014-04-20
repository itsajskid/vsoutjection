package com.github.struts2.vsoutjection.test.mulitple.actions;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;

import com.github.struts2.vsoutjection.annotations.ValueStack;
import com.github.struts2.vsoutjection.test.dao.Data;
import com.opensymphony.xwork2.ActionSupport;

@Namespace("/")
@SuppressWarnings("serial")
public class MoveTopLevelMultiFieldAction extends ActionSupport {

	@ValueStack(isTopLevel=true)
	private Data topLevelData;
	
	@ValueStack(isTopLevel=false)
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
