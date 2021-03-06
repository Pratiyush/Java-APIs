package json.jackson;

import java.io.File;
import java.io.IOException;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.ObjectMapper;
/*How to rename JSON properties using Jackson annotations?

In this example you will know how to ignore json property using @JsonIgnore jackson annotation. If you annotate any property with @JsonIgnore annotation, it will be ignored at runtime.

Note: Refer How to convert Java object to JSON string? page for dependent libraries.

*/public class JsonToObjectAnnotations {
	public static void main(String a[]){
        
        ObjectMapper mapper = new ObjectMapper();
        try {
            // reading json input from the file and mapping to object
            File jsonInputFile = new File("./resources/json-jackson/employeeAnnotation.json");
            Employee4 emp = mapper.readValue(jsonInputFile, Employee4.class);
            System.out.println(emp);
            // converting object to string value
            String jsonStr
                    = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(emp);
            System.out.println(jsonStr);
             
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}



class Employee4 {

   @JsonProperty("emp_id")
   private int empId;
    
   @JsonProperty("emp_name")
   private String name;
    
   @JsonProperty("emp_designation")
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
