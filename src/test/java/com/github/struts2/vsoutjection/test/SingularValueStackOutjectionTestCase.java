package com.github.struts2.vsoutjection.test;

import com.github.struts2.vsoutjection.exceptions.IllegalAnnotatedMethodException;
import com.github.struts2.vsoutjection.test.dao.Data;
import com.github.struts2.vsoutjection.test.singular.actions.OnActionMethodAction;
import com.github.struts2.vsoutjection.test.singular.actions.OutOfSyncFieldAction;
import com.github.struts2.vsoutjection.test.singular.actions.OutOfSyncMethodAction;
import com.opensymphony.xwork2.util.ValueStack;

public class SingularValueStackOutjectionTestCase extends AbstractValueStackOutjectionTestCase {
	
	public void testNewInstanceMethodAction () throws Exception {
		
		performTest("/new-instance-method-test.action", 
				"testNewInstanceMethodAction", 
				new ValueStackOutjectionTestRunner() {
					
					@Override
					public void testLogic(String actionName) throws Exception {
						ValueStack valueStack = executeStrutsActionWorkFlow(actionName);
						printValueStackObjectsClassTypes(valueStack);
						Object obj = valueStack.peek();
						confirmDataIntegrity(obj, "NewInstance", "Data");						
					}
				});
	}
	
	public void testMoveTopLevelMethodAction () throws Exception {
		
		performTest("/move-toplevel-method-test.action", 
				"testMoveTopLevelMethodAction", 
				new ValueStackOutjectionTestRunner() {
					
					@Override
					public void testLogic(String actionName) throws Exception {
						int secondObjIdx = 1;
						ValueStack valueStack = executeStrutsActionWorkFlow(actionName);
						printValueStackObjectsClassTypes(valueStack);
						Object obj = valueStack.getRoot().get(secondObjIdx);
						confirmDataIntegrity(obj, "move data", "level below");
					}
				});
	}

	public void testOnActionMethodAction() throws Exception {
		
		performTest("/onaction-method-test.action", 
				"testOnActionMethodAction", 
				new ValueStackOutjectionTestRunner() {
					
					@Override
					public void testLogic(String actionName) throws Exception {
						ValueStack valueStack = executeStrutsActionWorkFlow(actionName);
						printValueStackObjectsClassTypes(valueStack);
						Object obj = valueStack.getRoot().peek();
						confirmDataIntegrity(obj, "onAction", "foo");
						
						log.info("Testing action that does not match onAction method...");
						
						actionName = "/no-stack-onaction-method-test.action";
						valueStack = executeStrutsActionWorkFlow(actionName);
						printValueStackObjectsClassTypes(valueStack);
						
						for (Object o : valueStack.getRoot().toArray()) {
							assertFalse(o instanceof Data);
						}
						
						obj = valueStack.peek();
						assertNull(((OnActionMethodAction)obj).getOnActionData());						
					}
				});
	}
	
	public void testOutOfSyncMethodAction () throws Exception {
		
		performTest("/out-of-sync-test.action", 
				"testOutOfSyncMethodAction", 
				new ValueStackOutjectionTestRunner() {
			
			@Override
			public void testLogic(String actionName) throws Exception {
				int vsActionIdx = 1;
				
				ValueStack valueStack = executeStrutsActionWorkFlow(actionName);
				Data vsData = (Data)valueStack.peek();
				
				Data actionData = ((OutOfSyncMethodAction)
						valueStack.getRoot().get(vsActionIdx))
						.getOutOfSyncData();
				
				log.info("Testing that the data is not the same hence out of sync...");
				assertNotSame(vsData, actionData);				
			}
		});
	}
	
	public void testSimpleVSMethodAction () throws Exception {
		
		performTest("/simple-method-test.action", 
				"testSimpleVSMethodAction", 
				new ValueStackOutjectionTestRunner() {
					
					@Override
					public void testLogic(String actionName) throws Exception {
						ValueStack valueStack = executeStrutsActionWorkFlow(actionName);
						printValueStackObjectsClassTypes(valueStack);
						Object obj = valueStack.peek();
						
						confirmDataIntegrity(obj, "foo", "bar");
					}
				});
	}
	
	public void testInheritanceMethodAction() throws Exception {
		
		performTest("/inheritance-method-test.action", 
				"testInheritanceMethodAction",
				new ValueStackOutjectionTestRunner() {
					
					@Override
					public void testLogic(String actionName) throws Exception {
						ValueStack valueStack = executeStrutsActionWorkFlow(actionName);
						printValueStackObjectsClassTypes(valueStack);
						Object obj = valueStack.peek();
						
						confirmDataIntegrity(obj, "spring", "roo");
					}
				});
	}
	
	public void testIsMethodAction () throws Exception {
		
		performTest("/is-method-test.action", 
				"testIsMethodAction", 
				new ValueStackOutjectionTestRunner() {
					
					@Override
					public void testLogic(String actionName) throws Exception {
						ValueStack valueStack = executeStrutsActionWorkFlow(actionName);
						printValueStackObjectsClassTypes(valueStack);
						
						assertTrue((Boolean)valueStack.peek());
					}
				});
	}
	
	public void testNonStandardMethodAction () throws Exception {
		
		performTest("/non-standard-method-test.action", 
				"testNonStandardMethodAction", 
				new ValueStackOutjectionTestRunner() {
					
					@Override
					public void testLogic(String actionName) throws Exception {
						ValueStack valueStack = executeStrutsActionWorkFlow(actionName);
						printValueStackObjectsClassTypes(valueStack);
						
						confirmDataIntegrity(valueStack.peek(), "model", "test");						
					}
				});
	}
	
	public void testIllegalAnnotatedExceptionAction () throws Exception {
		
		performTest("/illegal-annotated-method-test.action", 
				"testIllegalAnnotatedExceptionAction", 
				new ValueStackOutjectionTestRunner() {
					
					@Override
					public void testLogic(String actionName) throws Exception {
						try {
							executeStrutsActionWorkFlow(actionName);
							assertTrue(false);
						} catch (IllegalAnnotatedMethodException e) {
							log.info("Successfully caught exception: ", e);
						}						
					}
				});
	}
	
	public void testNoMatchingSetterMethodAction () throws Exception {
		
		performTest("/no-matching-setter-test.action", 
				"testNoMatchingSetterMethodAction", 
				new ValueStackOutjectionTestRunner() {
			
			@Override
			public void testLogic(String actionName) throws Exception {
				try {
					executeStrutsActionWorkFlow(actionName);
					assertTrue(false);
				} catch (NoSuchMethodException e) {
					log.info("Successfully caught exception: ", e);
				}				
			}
		});
	}
	
	public void testSimpleVSFieldAction() throws Exception {
		performTest("/simple-vsfield-test.action",
				"testSimpleVSFieldAction",
				new ValueStackOutjectionTestRunner() {
					
					@Override
					public void testLogic(String actionName) throws Exception {
						ValueStack valueStack = 
								executeStrutsActionWorkFlow(actionName);
						printValueStackObjectsClassTypes(valueStack);
						Object obj = valueStack.peek();
						confirmDataIntegrity(obj, "simple data", "field");
					}
				});
	}
	
	public void testIsFieldAction() throws Exception {
		performTest("/is-field-test.action", 
				"testIsFieldAction", 
				new ValueStackOutjectionTestRunner() {
					
					@Override
					public void testLogic(String actionName) throws Exception {
						ValueStack valueStack =
								executeStrutsActionWorkFlow(actionName);
						printValueStackObjectsClassTypes(valueStack);
						Object obj = valueStack.peek();
						
						assertTrue(obj instanceof Boolean);
						assertTrue((Boolean)obj == true);
					}
				});
	}
	
	public void testNewInstanceFieldAction() throws Exception {
		performTest("/new-instance-field-test.action", 
				"testNewInstanceFieldAction", 
				new ValueStackOutjectionTestRunner() {
					
					@Override
					public void testLogic(String actionName) throws Exception {
						ValueStack valueStack = 
								executeStrutsActionWorkFlow(actionName);
						printValueStackObjectsClassTypes(valueStack);
						confirmDataIntegrity(valueStack.peek(), "new-instance", "foo");
					}
				});
	}
	
	public void testMoveTopLevelFieldAction () throws Exception {
		performTest("/move-top-level-test.action",
				"testMoveTopLevelFieldAction",
				new ValueStackOutjectionTestRunner() {
					
					@Override
					public void testLogic(String actionName) throws Exception {
						ValueStack valueStack =
								executeStrutsActionWorkFlow(actionName);
						printValueStackObjectsClassTypes(valueStack);
						int secondBeforeTopIndex = 1;
						confirmDataIntegrity(valueStack.getRoot().get(secondBeforeTopIndex), 
								"move top level", 
								"foo");
					}
				});
	}
	
	public void testOnActionFieldAction () throws Exception {
		performTest("/on-action-field-test",
				"testOnActionFieldAction",
				new ValueStackOutjectionTestRunner() {
					
					@Override
					public void testLogic(String actionName) throws Exception {
						ValueStack valueStack = executeStrutsActionWorkFlow(actionName);
						printValueStackObjectsClassTypes(valueStack);
						Object obj = valueStack.peek();						
						confirmDataIntegrity(obj, "on-action", "foo");

						log.info("Testing action that does not match "
								+ "onAction method...");
						
						actionName = "/onaction-test.action";
						valueStack = 
								executeStrutsActionWorkFlow(actionName);
						printValueStackObjectsClassTypes(valueStack);
						
						obj = valueStack.peek();
						
						assertFalse (obj instanceof Data);
						
						log.info("Success!");
					}
				});
	}
	
	public void testOutOfSyncFieldAction () throws Exception {
		performTest("/out-of-sync-field-test.action", 
				"testOutOfSyncFieldAction", 
				new ValueStackOutjectionTestRunner() {
			
			@Override
			public void testLogic(String actionName) throws Exception {
				ValueStack valueStack = 
						executeStrutsActionWorkFlow(actionName);
				printValueStackObjectsClassTypes(valueStack);
				
				Object obj = valueStack.peek();
				confirmDataIntegrity(obj, "foo", "bar");
				
				int actionIndex =  1;
				OutOfSyncFieldAction action = 
						(OutOfSyncFieldAction)valueStack
							.getRoot()
							.get(actionIndex);
				Data actionData = action.getOutOfSyncData();
				confirmDataIntegrity(actionData, "out-of-sync", "foo");
				
				log.info("Comparing the Data object on the valueStack to "
						+ "the Data object in the action class...");
				
				assertNotSame(actionData, obj);
				
				log.info("Data objects for action and "
						+ "valueStack are not the same...");
				
			}
		});
	}

}
