package json.gson;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
 
import com.google.gson.Gson;
public class JsonToObject {
	public static void main(String a[]){
        
        BufferedReader br = null;
        Gson gsonObj = new Gson();
        try {
            br = new BufferedReader(new FileReader("./resources/json-gson/employeeDetails.json"));
            // convert json string to object
            Employee1 emp = gsonObj.fromJson(br, Employee1.class);
            System.out.println("Emp Name: "+emp.getName());
            System.out.println("Emp Id: "+emp.getEmpId());
        } catch (FileNotFoundException e) {
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
