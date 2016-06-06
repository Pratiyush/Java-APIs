package json.gson;

import java.util.HashMap;
import java.util.Map;
 
import com.google.gson.Gson;
 
public class MapToJson {
 
    public static void main(String a[]){
         
        Gson gsonObj = new Gson();
        Map<String, String> inputMap = new HashMap<String, String>();
        inputMap.put("name", "Java2Novice");
        inputMap.put("site", "http://java2novice.com");
        // convert map to JSON String
        String jsonStr = gsonObj.toJson(inputMap);
        System.out.println(jsonStr);
    }
}
