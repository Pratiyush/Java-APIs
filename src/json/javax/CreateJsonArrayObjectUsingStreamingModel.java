package json.javax;
import java.io.StringWriter;

import javax.json.Json;
import javax.json.stream.JsonGenerator;
public class CreateJsonArrayObjectUsingStreamingModel {
	 public static void main(String a[]){
         
		 StringWriter strWtr = new StringWriter(); 
		 JsonGenerator jsonGen = Json.createGenerator(strWtr);
		 JsonGenerator start = jsonGen.writeStartObject();
		 start.write("emp_name", "Nataraj G");
		 start.write("emp_id", 1016);
		 start.write("salary", 20000); 
		 // create Json array with only values 
		 JsonGenerator plnArrGen = start.writeStartArray("direct_contacts"); 
		 plnArrGen.write("Rakesh"); 
		 plnArrGen.write("John"); 
		 plnArrGen.writeEnd();
		 // create an array of key-value pairs 
		 JsonGenerator kvArrGen = start.writeStartArray("contacts"); 
		 // create each key-value pair as seperate object and add it to the array 
		 kvArrGen.writeStartObject().write("email", "java2novice@gmail.com").writeEnd();
		 kvArrGen.writeStartObject().write("mobile", "123123123123").writeEnd(); 
		 kvArrGen.writeEnd(); 
		 start.writeEnd(); 
		 jsonGen.close(); 
		 System.out.println(strWtr.toString());
	    }
}
