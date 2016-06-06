package json.javax;
import java.io.StringWriter;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;
public class CreateJsonArrayObject {
	public static void main(String a[]){
        

		JsonObjectBuilder jsonBuilder = Json.createObjectBuilder();
		jsonBuilder.add("emp_name", "Nataraj G");
		jsonBuilder.add("emp_id", 1016);
		jsonBuilder.add("salary", 20000);
		
		// create Json array with only values
		JsonArrayBuilder plnArrBld = Json.createArrayBuilder();
		plnArrBld.add("Rakesh");
		plnArrBld.add("John");
		JsonArray arr = plnArrBld.build();
		// the array got created, add it to the json as a child element
		jsonBuilder.add("direct_contacts", arr);
		
		// create an array of key-value pairs
		JsonArrayBuilder kvArrBld = Json.createArrayBuilder();
		// create each key-value pair as seperate object and add it to the array
		kvArrBld.add(Json.createObjectBuilder().add("email", "java2novice@gmail.com").build());
		kvArrBld.add(Json.createObjectBuilder().add("mobile", "123123123123").build());
		
		JsonArray contactsArr = kvArrBld.build();
		// add contacts array object
		jsonBuilder.add("contacts", contactsArr);
		
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
