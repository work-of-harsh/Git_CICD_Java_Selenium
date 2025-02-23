package Harsh_Project.data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

 // this class is already in base class
public class Data_Reader {
	public static void main(String[] args) throws IOException {
		Data_Reader obj = new Data_Reader();
		System.out.println(obj.getJsonDataToMapt().get(0));	
	}
	
	public  List<HashMap<String, String>> getJsonDataToMapt() throws IOException {
		
		//read json to string
		String Json_content = FileUtils.readFileToString(new File("C:\\Users\\Lenovo\\eclipse-workspace\\Selenium_Framework_Harsh\\src\\test\\java\\Harsh_Project\\data\\Input_data_file.json"),StandardCharsets.UTF_8);
	    //String to Hashmap usinf Jakson Databind
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(Json_content, new TypeReference<List<HashMap<String, String>>>() {
	      });
		  return data;
	
	}

}
