package json.jackson;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class JacksonExample {
	
	public static void main(String a[]){
		//jsonToObject();
		objectToJson() ;
		jsonToObject();
		
	}

		
	public static void jsonToObject() {

		ObjectMapper mapper = new ObjectMapper();

		try {

			// read from file, convert it to user class
			User user = mapper.readValue(new File("./resources/json-jackson/user.json"),
					User.class);

			// display to console
			System.out.println(user);

		} catch (JsonGenerationException e) {

			e.printStackTrace();

		} catch (JsonMappingException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}

	}

	public static void objectToJson() {

		User user = new User();
		ObjectMapper mapper = new ObjectMapper();

		try {

			// convert user object to json string, and save to a file
			mapper.writeValue(new File("./resources/user.json"), user);

			// display to console
			System.out.println(mapper.writeValueAsString(user));

		} catch (JsonGenerationException e) {

			e.printStackTrace();

		} catch (JsonMappingException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}

	}

}

class User {

	private int age = 29;
	private String name = "mkyong";
	@SuppressWarnings("serial")
	private List<String> messages = new ArrayList<String>() {
		{
			add("msg 1");
			add("msg 2");
			add("msg 3");
		}
	};

	// getter and setter methods

	@Override
	public String toString() {
		return "User [age=" + age + ", name=" + name + ", " + "messages="
				+ messages + "]";
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getMessages() {
		return messages;
	}

	public void setMessages(List<String> messages) {
		this.messages = messages;
	}
}
