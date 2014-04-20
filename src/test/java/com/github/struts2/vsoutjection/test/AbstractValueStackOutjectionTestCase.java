package com.github.struts2.vsoutjection.test;

import org.apache.struts2.StrutsTestCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.struts2.vsoutjection.test.dao.Data;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.util.ValueStack;

public abstract class AbstractValueStackOutjectionTestCase extends StrutsTestCase {
	
	protected static final String METHOD_BEGIN_FMT = "%s begins...";
	protected static final String METHOD_ENDS_FMT = "%s ends...";
	protected static final String TEST_DATA_INTEGRITY = "Testing data integrity...";
	protected static final String TEST_DATA_INTEGRITY_PASS = "Data integrity passes...";
	protected static final String OBJECTS_ON_STACK_MSG = "OBJECTS ON THE VALUESTACK BY CLASS TYPE:";
	protected static final String OBJECTS_ON_STACK_FMT = "ALL VALUESTACK OBJECTS PRINTED. OBJECT COUNT: %d";
	
	protected final Logger log = LoggerFactory.getLogger(this.getClass());

	protected ValueStack executeStrutsActionWorkFlow (String actionName) throws Exception {
		ActionProxy actionProxy = getActionProxy(actionName);
		actionProxy.execute();
		return actionProxy.getInvocation().getStack();		
	}
	
	protected void confirmDataIntegrity (Object stackData, Object dataValue1) {
		log.info(TEST_DATA_INTEGRITY);
		
		assertTrue(stackData instanceof Data);
		Data newInstanceData = (Data)stackData;
		
		assertEquals(dataValue1, newInstanceData.getValue1());
		
		log.info(TEST_DATA_INTEGRITY_PASS);		
	}
	
	protected void confirmDataIntegrity (Object stackData, Object dataValue1, Object dataValue2) {
		log.info(TEST_DATA_INTEGRITY);
		
		assertTrue(stackData instanceof Data);
		Data newInstanceData = (Data)stackData;
		
		assertEquals(dataValue1, newInstanceData.getValue1());
		assertEquals(dataValue2, newInstanceData.getValue2());
		
		log.info(TEST_DATA_INTEGRITY_PASS);
	}
	
	protected void printValueStackObjectsClassTypes (ValueStack valueStack) {
		log.info(OBJECTS_ON_STACK_MSG);
		
		Object[] objArray = valueStack.getRoot().toArray();
		
		for (Object o : objArray) {
			log.info(o.getClass().getName());
		}
		
		log.info(String.format(OBJECTS_ON_STACK_FMT, valueStack.getRoot().size()));
	}
	
	protected void performTest(String actionName, 
			String methodName, 
			ValueStackOutjectionTestRunner testRunner) throws Exception {
		
		log.info(String.format(String.format(METHOD_BEGIN_FMT, methodName)));
		
		testRunner.testLogic(actionName);
		
		log.info(String.format(String.format(METHOD_ENDS_FMT, methodName)));
		
	}
	
}
