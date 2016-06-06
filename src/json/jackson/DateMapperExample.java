package json.jackson;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.annotate.JsonSerialize;

public class DateMapperExample {
	 public static void main(String[] a){
         
	        Employee7 emp = new Employee7();
	        emp.setEmpId(1016);
	        emp.setName("Nataraj G");
	        emp.setDepartment("Accounting");
	        emp.setDesignation("Accountant");
	        emp.setSalary(40000);
	        emp.setDoj(new Date());
	         
	        ObjectMapper mapperObj = new ObjectMapper();
	         
	        try {
	            // get Employee object as a json string
	            String jsonStr = mapperObj.writerWithDefaultPrettyPrinter().writeValueAsString(emp);
	            System.out.println(jsonStr);
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	    }
}

/*Now annotate your doj member in Employee class as shown below: This will solve the problem, you will get the date formate as specified in the above DateSerializer class.

?
1
2
@JsonSerialize(using=DateSerializer.class)
    private Date doj;
- See more at: http://www.java2novice.com/java-json/jackson/handle-date-in-json/#sthash.aYZGWnod.dpuf
*/class DateSerializer extends JsonSerializer<Date>{
	 
    public void serialize(Date dt, JsonGenerator jsonGen, SerializerProvider serProv)
                                            throws IOException, JsonProcessingException {
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = sdf.format(dt);
        jsonGen.writeString(formattedDate);
    }
}
@JsonPropertyOrder({ "emp_id", "emp_name", "emp_designation", "department", "salary" })
class Employee7 {
 
    @JsonProperty("emp_id")
    private int empId;
     
    @JsonProperty("emp_name")
    private String name;
     
    @JsonProperty("emp_designation")
    private String designation;
     
    private String department;
     
    private int salary;
    @JsonSerialize(using=DateSerializer.class)
    private Date doj;
     
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("************************************");
        sb.append("\nempId: ").append(empId);
        sb.append("\nname: ").append(name);
        sb.append("\ndesignation: ").append(designation);
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
 
    public Date getDoj() {
        return doj;
    }
 
    public void setDoj(Date doj) {
        this.doj = doj;
    }
}
