package json.javax;
import java.io.StringWriter;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;
public class CreateJsonObject {
	public static void main(String a[]){
        
        JsonObjectBuilder jsonBuilder = Json.createObjectBuilder();
        jsonBuilder.add("emp_name", "Nataraj G");
        jsonBuilder.add("emp_id", 1016);
        jsonBuilder.add("salary", 20000);
         
        JsonObject empObj = jsonBuilder.build();
        // here we are writing to String writer.
        // if you want you can write it to a file as well
        StringWriter strWtr = new StringWriter();
        JsonWriter jsonWtr = Json.createWriter(strWtr);
        jsonWtr.writeObject(empObj);
        jsonWtr.close();
         
        System.out.println(strWtr.toString());
    }
}
