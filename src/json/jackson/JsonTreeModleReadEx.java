package json.jackson;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
//How to read specific json node in Jackson api (tree model)?

/*This page shows how to read Json data in tree model using JsonNode.

Note: Refer How to convert Java object to JSON string? page for dependent libraries.

Here is the input json file:

- See more at: http://www.java2novice.com/java-json/jackson/tree-model-read-json-node/#sthash.kvBgBMq9.dpuf
*/public class JsonTreeModleReadEx {
	 public static void main(String a[]){
         
	        ObjectMapper mapper = new ObjectMapper();
	        try {
	            // reading json input from the file and mapping to object
	            File jsonInputFile = new File("./resources/json-jackson/person.json");
	             
	            JsonNode rootNode = mapper.readTree(jsonInputFile);
	            // read employee id
	            JsonNode empId = rootNode.path("emp_id");
	            System.out.println(empId.getIntValue());
	            // read employee name
	            JsonNode empName = rootNode.path("emp_name");
	            System.out.println(empName.getTextValue());
	            // read direct reports
	            JsonNode drNode = rootNode.path("direct_reports");
	            Iterator<JsonNode> itr = drNode.getElements();
	            System.out.println("\nDirect reports:");
	            while (itr.hasNext()) {
	                JsonNode temp = itr.next();
	                System.out.println(temp.getTextValue());
	            }
	             
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	    }
}
