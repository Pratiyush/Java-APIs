package json.jackson;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
//copy comments
//http://www.java2novice.com/java-json/jackson/map-to-json/
import org.codehaus.jackson.map.ObjectMapper;
public class MapToJson {
    public static void main(String a[]){
        
        ObjectMapper mapperObj = new ObjectMapper();
        Map<String, String> inputMap = new HashMap<String, String>();
        inputMap.put("name", "Java2Novice");
        inputMap.put("site", "http://java2novice.com");
        // convert map to JSON String
        try {
            String jsonResp = mapperObj.writeValueAsString(inputMap);
            System.out.println(jsonResp);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
