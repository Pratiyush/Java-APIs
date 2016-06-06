package json.gson;

import com.google.gson.annotations.SerializedName;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/*This page shows how to rename json properties when using Gson APIs. 
@SerializedName annotation helps you to modify the json property names. 
Annotate POJO members with @SerializedName annotation with desired property name.*/

public class ObjectToJsonAnnotations {
 
    public static void main(String a[]){
         
        Employee2 emp = new Employee2();
        emp.setEmpId(1016);
        emp.setName("Nataraj G");
        emp.setSalary(20000);
        emp.setDesignation("Manager");
        emp.setDepartment("Accounts");
         
        Gson gsonObj = new GsonBuilder().setPrettyPrinting().create();
        // converts object to json string
        String jsonStr = gsonObj.toJson(emp);
        System.out.println(jsonStr);
    }
}
	
class Employee2{
 
    @SerializedName("emp_id")
    private int empId;
     
    @SerializedName("emp_name")
    private String name;
     
    private String designation;
    private String department;
    private int salary;
     
    public int getEmpId() {
        return empId;
    }
    public void setEmpId(int empId) {
        this.empId = empId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDesignation() {
        return designation;
    }
    public void setDesignation(String designation) {
        this.designation = designation;
    }
    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {
        this.department = department;
    }
    public int getSalary() {
        return salary;
    }
    public void setSalary(int salary) {
        this.salary = salary;
    }
}
