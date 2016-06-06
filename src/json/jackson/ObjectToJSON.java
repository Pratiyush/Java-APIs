package json.jackson;

import java.io.IOException;

import org.codehaus.jackson.map.ObjectMapper;

/*How to convert Java object to JSON string?

This page shows how to convert java object to JSON string using Jackson's data binding.

As a first step add Jackson dependent jar file "jackson-mapper-asl" to your classpath. Here is the pom.xml file for your reference:

*/public class ObjectToJSON {
	 public static void main(String[] a){
         
	        Employee emp = new Employee();
	        ObjectMapper mapperObj = new ObjectMapper();
	         
	        try {
	            // get Employee object as a json string
	            String jsonStr = mapperObj.writeValueAsString(emp);
	            System.out.println(jsonStr);
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	    }
}
  class Employee {
	 
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
