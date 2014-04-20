package com.allanshoulders.vsoutjection.test.mulitple.actions;

import org.apache.struts2.convention.annotation.Action;

@SuppressWarnings("serial")
public class InheritanceMultiMethodAction extends SimpleVSMultiFieldAction {

	@Action(value="multi-inheritance-test")
	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
}
