package json.javax;
import java.io.StringWriter;

import javax.json.Json;
import javax.json.stream.JsonGenerator;
public class CreateJsonObjectUsingStreamingModel {
	 public static void main(String a[]){
         
	        StringWriter strWtr = new StringWriter();
	        JsonGenerator jsonGen = Json.createGenerator(strWtr);
	        JsonGenerator start = jsonGen.writeStartObject();
	        start.write("emp_name", "Nataraj G");
	        start.write("emp_id", 1016);
	        start.write("salary", 20000);
	        start.writeEnd();
	        jsonGen.close();
	        System.out.println(strWtr.toString());
	    }
}
