<h1>Struts 2 Value Stack Outjection</h1>
Struts 2 plugin to push multiple objects on the value stack using annotations.

<h2>Introduction</h2>
<p>
The Model Driven interface can be used by an Action class to push a object on to the Value Stack. It is very simple to use and easy to establish. It is however, limited to a single object to be pushed on top of the Value Stack.
Features
</p>
<p>
The Value Stack Outjection plugin allows mulitple objects that are properties of an Action class to be pushed onto the Value Stack.
</p>

<h2>Additional features:</h2>

The plugin can create new instances of the object to be placed on the Value Stack for you. When the instance is created, it will be automatically set on your Action class. If desired, it can move pushed objects one level below top-level object. This is key when some business logic or plugins need the Action class to be the top level object.

<h3>VERSION 1.2.1</h3>

- Field level objects can now be annotated.
- Annotate any method. The method must return a non-void type.
- onAction attribute allows you control which objects get pushed to the ValueStack based on the action that is executed.
- Javadoc inaccuracy has been resolved.

<h2>Setup</h2>

Setup for the Value Stack Outjection plugin is very simple. Follow the step below.

<h3>Step 1</h3>
Checkout (or download as ZIP) the latest Value Stack Outjection distro. Move the vsoutjection jar to your app's WEB-INF lib.

<h3>Step 2</h3>
In your package definition of your struts.xml (or sub configuration files) extend the vsoutject-default package. Within your action, make reference to the interceptor vsoutjectStack.

 <package name="xyz-employees" namespace="/employees" extends="vsoutject-default">
    
        <action name="all-employees" class="com.allanshoulders.employees.actions.EmployeeAction">
                <interceptor-ref name="vsoutjectStack"/>
                <result>/employees/getAllEmployees.jsp</result>
        </action>
 </package>

You can also define your own interceptor stacks with vsoutject:

            <!-- Sample vsoutject stack -->
            <interceptor-stack name="vsoutjectStack">
                <interceptor-ref name="vsoutject"/>
                <interceptor-ref name="defaultStack"/>
            </interceptor-stack>

NOTE: If you override an interceptor stack (such as defaultStack) or have defined your own interceptor stack, make sure the vsoutject interceptor reference comes BEFORE the params-interceptor.

<h3>Step 3</h3>
Annotate which properties you want pushed onto the value stack:

public class EmployeeAction extends ActionSupport {
        private JsonTable jsonTable;
        private Employee employee;
        
        //VSOutject annotations must be on getter methods only.
        @VSOutject(isTopLevel=false,newInstance=true)
        public JsonTable getJsonTable() {
                return jsonTable;
        }

        //Since we have set the instance attribute to true,
        //we must define a setter method that will be used to
        //inject the automatically created reference onto
        //our action class. 
        public void setJsonTable(JsonTable jsonTable) {
                this.jsonTable = jsonTable;
        }

        //The attribute isTopLevel is false. This means
        //The object will be placed a level below the
        //current top-level object on the ValueStack.
        @VSOutject(isTopLevel=false)
        public Employee getModel() {
                employee = new Employee();
                employee.setFirstName("Test");
                employee.setLastName("Tester");
                return employee;
        }       
}


<h3>Contact</h3>
I'll be more than happy to answer questions regarding the vsoutjection plugin. In addition to sending me email you can also reach me:

<b>on twitter:</b> @itsajskid<br/>
<b>on github:</b> itsajskid

