package com.github.struts2.vsoutjection.test.singular.actions;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;

@Namespace("/")
@SuppressWarnings("serial")
public class InheritanceMethodAction extends NewInstanceMethodAction {

	@Action(value="inheritance-method-test")
	@Override
	public String execute() throws Exception {
		getNewInstanceData().setValue1("spring");
		getNewInstanceData().setValue2("roo");
		return SUCCESS;
	}
}
