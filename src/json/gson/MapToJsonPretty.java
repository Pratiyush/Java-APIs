package json.gson;

import java.util.HashMap;
import java.util.Map;
 
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
 
public class MapToJsonPretty {
 
    public static void main(String a[]){
         
        Map<String, String> inputMap = new HashMap<String, String>();
        inputMap.put("name", "Java2Novice");
        inputMap.put("site", "http://java2novice.com");
        // convert map to JSON String
        // notice that we enabled pretty printing in the below line
        Gson gsonObj = new GsonBuilder().setPrettyPrinting().create();
        String jsonStr = gsonObj.toJson(inputMap);
        System.out.println(jsonStr);
    }
}
