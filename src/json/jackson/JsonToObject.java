package json.jackson;

import java.io.File;
import java.io.IOException;

import org.codehaus.jackson.map.ObjectMapper;
/*How to convert JSON string to Java object?

This page shows how to convert JSON string to java object using Jackson's data binding.

Note: Refer How to convert Java object to JSON string? page for dependent libraries.

Create a simple Employee pojo. We will read JSON string from a file and map it to Employee class.

*/public class JsonToObject {
	public static void main(String a[]){
        
        ObjectMapper mapper = new ObjectMapper();
        try {
            File jsonInputFile = new File("./resources/json-jackson/employee.json");
            Employee1 emp = mapper.readValue(jsonInputFile, Employee1.class);
            System.out.println(emp);
             
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
	
	
}
class Employee1 {
	 
    private int empId;
    private String name;
    private String designation;
    private String department;
    private int salary;
     
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
