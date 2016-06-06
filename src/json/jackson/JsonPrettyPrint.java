package json.jackson;
import java.io.IOException;

import org.codehaus.jackson.map.ObjectMapper;

/*Enable JSON pretty print using Jackson API
This page shows how to enable JSON pretty print using Jackson APIs.
Note: Refer How to convert Java object to JSON string? page for dependent libraries.

*/public class JsonPrettyPrint {
	  public static void main(String a[]){
	         
	        Employee2 emp = new Employee2();
	        ObjectMapper mapperObj = new ObjectMapper();
	         
	        try {
	            // get Employee object as a json string
	            String jsonStr = mapperObj.writeValueAsString(emp);
	            System.out.println("json output in compact mode:\n");
	            System.out.println(jsonStr);
	            String jsonPrettyStr
	                    = mapperObj.writerWithDefaultPrettyPrinter().writeValueAsString(emp);
	            System.out.println("\n\njson output in pretty print mode:\n");
	            System.out.println(jsonPrettyStr);
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	    }
}
class Employee2 {
	 
    private int empId = 1016;
    private String name = "Nataraja Gootooru";
    private String designation = "Programmer";
    private String department = "Java2Novice";
    private int salary = 20000;
     
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("************************************");
        sb.append("\nempId: ").append(empId);
        sb.append("\nname: ").append(name);
        sb.append("\ndesignation: ").append(designation);
        sb.append("\ndepartment: ").append(department);
        sb.append("\nsalary: ").append(salary);
        sb.append("\n************************************");
        return sb.toString();
    }
     
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
