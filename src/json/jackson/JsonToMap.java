package json.jackson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
/*How to convert JSON string to Map using Jackson API?

This page shows how to convert JSON string to java map object using Jackson's data binding.

Note: Refer How to convert Java object to JSON string? page for dependent libraries.

*/public class JsonToMap {
	 public static void main(String a[]){
         
	        String jsonStr = "{\"name\":\"Nataraj\", \"job\":\"Programmer\"}";
	        Map<String,String> resultMap = new HashMap<String,String>();
	        ObjectMapper mapperObj = new ObjectMapper();
	         
	        System.out.println("Input Json: "+jsonStr);
	        try {
	            resultMap = mapperObj.readValue(jsonStr,
	                            new TypeReference<HashMap<String,String>>(){});
	            System.out.println("Output Map: "+resultMap);
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	    }
}
