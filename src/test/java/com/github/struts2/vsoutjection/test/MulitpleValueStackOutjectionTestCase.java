package com.allanshoulders.vsoutjection.test;

import java.util.Map;

import com.allanshoulders.vsoutjection.test.dao.Data;
import com.opensymphony.xwork2.util.ValueStack;


public class MulitpleValueStackOutjectionTestCase extends AbstractValueStackOutjectionTestCase {

	public void testSimpleVSMultiMethodAction () throws Exception {
		performTest("/simple-vs-multi-test.action",
				"testSimpleVSMulitpleMethodAction", 
				new ValueStackOutjectionTestRunner() {
					
					@Override
					public void testLogic(String actionName) throws Exception {
						ValueStack valueStack = 
								executeStrutsActionWorkFlow(actionName);
						printValueStackObjectsClassTypes(valueStack);
						
						int firstStackObj = 0, secondStackObj = 1;
						
						Object[] stackObjs = new Object[]{valueStack.getRoot().get(firstStackObj),
								valueStack.getRoot().get(secondStackObj)};
						
						for (Object stackObj : stackObjs) {
							if (stackObj instanceof Data) {
								confirmDataIntegrity(stackObj, 
										"simpleMethod", 
										"foo");
							} else if (stackObj instanceof Boolean) {
								assertTrue((Boolean)stackObj);
							} else {
								assertTrue("The top two level objects do not "
										+ "have the expected values.", false);
							}
						}
					}
				});
	}
	
	public void testSimpleVSMultiFieldAction () throws Exception {
		performTest("/simple-vs-field-test.action",
				"testSimpleVSMultiFieldAction", 
				new ValueStackOutjectionTestRunner() {
					
					@SuppressWarnings("unchecked")
					@Override
					public void testLogic(String actionName) throws Exception {
						ValueStack valueStack =
								executeStrutsActionWorkFlow(actionName);
						printValueStackObjectsClassTypes(valueStack);
						
						int firstStackObj = 0, secondStackObj = 1;
						
						Object[] stackObjs = new Object[]{valueStack.getRoot().get(firstStackObj),
								valueStack.getRoot().get(secondStackObj)};
						
						for (Object obj : stackObjs) {
							if (obj instanceof Data) {
								confirmDataIntegrity(obj, "foo", "bar");
							} else if (obj instanceof Map) {
								Data d = 
										((Map<String, Data>)obj).get("baz");
								confirmDataIntegrity(d, "foo", "bar");
							}
						}
					}
				});
	}
	
	public void testOnActionMultiAciton() throws Exception {
		performTest("/onaction-default-test.action",
				"testOnActionMultiAciton", 
				new ValueStackOutjectionTestRunner() {
					
					@Override
					public void testLogic(String actionName) throws Exception {
						ValueStack valueStack = 
								executeStrutsActionWorkFlow(actionName);
						printValueStackObjectsClassTypes(valueStack);
						Object obj = valueStack.peek();
						confirmDataIntegrity(obj, "default", "foo");
						
						log.info("Testing the the action: onaction-custom-test");
						
						actionName = "/onaction-custom-test.action";
						
						valueStack = executeStrutsActionWorkFlow(actionName);
						printValueStackObjectsClassTypes(valueStack);
						obj = valueStack.peek();
						confirmDataIntegrity(obj, "custom", "foo");
					}
				});
	}
	
	public void testOnActionMultiFieldAction () throws Exception {
		performTest("/onaction-defaultfield-test.action",
				"testOnActionMultiFieldAction",
				new ValueStackOutjectionTestRunner() {
					
					@Override
					public void testLogic(String actionName) throws Exception {
						ValueStack valueStack = 
								executeStrutsActionWorkFlow(actionName);
						printValueStackObjectsClassTypes(valueStack);
						Object obj = valueStack.peek();
						confirmDataIntegrity(obj, 1, "foo");
						
						log.info("Testing the the action: onaction-customfield-test");
						
						actionName = "/onaction-customfield-test.action";
						
						valueStack = executeStrutsActionWorkFlow(actionName);
						printValueStackObjectsClassTypes(valueStack);
						obj = valueStack.peek();
						confirmDataIntegrity(obj, 2, "bar");
					}
				});
	}
	
	public void testNonStandardMultiMethodAction () throws Exception {
		performTest("/non-standard-multi-test.action",
				"testNonStandardMultiMethodAction", 
				new ValueStackOutjectionTestRunner() {
					
					@Override
					public void testLogic(String actionName) throws Exception {
						ValueStack valueStack = 
								executeStrutsActionWorkFlow(actionName);
						printValueStackObjectsClassTypes(valueStack);
						
						int firstObjIdx = 0, secondObjIdx = 1;
						
						Object[] stackObjs = new Object[]{
								valueStack.getRoot().get(firstObjIdx),
								valueStack.getRoot().get(secondObjIdx)};
						
						for (Object stackObj : stackObjs) {
							if (stackObj instanceof Data) {
								confirmDataIntegrity(stackObj, 1, "foo");
							} else if (stackObj instanceof Map) {
								log.info(stackObj.toString());
							}
						}
						
					}
				});
	}
	
	public void testMoveTopLevelMultiMethodAction () throws Exception {
		performTest("/move-top-level-multimethod-test.action",
				"testMoveTopLevelMultiMethodAction", 
				new ValueStackOutjectionTestRunner() {
					
					@Override
					public void testLogic(String actionName) throws Exception {
						ValueStack valueStack = 
								executeStrutsActionWorkFlow(actionName);
						printValueStackObjectsClassTypes(valueStack);
						
						log.info("Testing to make sure toplevel data object is "
								+ "at the top of the ValueStack.");
						
						confirmDataIntegrity(valueStack.peek(), null, "topLevelData");
					}
				});
	}
	
	public void testMoveTopLevelMultiFieldAction () throws Exception {
		performTest("/move-top-level-multifield-test.action", 
				"testMoveTopLevelMultiFieldAction", 
				new ValueStackOutjectionTestRunner() {
					
					@Override
					public void testLogic(String actionName) throws Exception {
						ValueStack valueStack = 
								executeStrutsActionWorkFlow(actionName);
						printValueStackObjectsClassTypes(valueStack);
						
						log.info("Testing to make sure toplevel data object is "
								+ "at the top of the ValueStack.");
						
						confirmDataIntegrity(valueStack.peek(), 1);						
					}
				});
	}
	
	public void testInheritanceMultiMethodAction () throws Exception {
		performTest("/multi-inheritance-test.action", 
				"testInheritanceMultiMethodAction", 
				new ValueStackOutjectionTestRunner() {
					
					@Override
					public void testLogic(String actionName) throws Exception {
						ValueStack valueStack = 
								executeStrutsActionWorkFlow(actionName);
						printValueStackObjectsClassTypes(valueStack);
						
						int firstObjectIdx = 0, secondObjIdx = 1;
						
						Object[] stackObjs = new Object[]{
								valueStack.getRoot().get(firstObjectIdx),
								valueStack.getRoot().get(secondObjIdx)
						};
						
						for (Object stackObj : stackObjs) {
							if (stackObj instanceof Data) {
								confirmDataIntegrity(stackObj, "simpleMethod", "foo");
							} else if (stackObj instanceof Boolean) {
								assertTrue((Boolean)stackObj);
							}
						}
					}
				});
	}
	
}
