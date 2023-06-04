package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
	public RequestSpecification req;
	public RequestSpecification requestSpecification() throws IOException {
		if(req==null) {
			
		
		PrintStream log=new PrintStream(new FileOutputStream("logging.txt"));
		// RestAssured.baseURI="http://rahulshettyacademy.com";
			// RequestSpec build return type is requestSpecifications
					 req=new RequestSpecBuilder().setBaseUri(Utils.getGlobalValue("baseUrl"))
							.addQueryParam("key","qaclick123").setContentType(ContentType.JSON).
							addFilter(RequestLoggingFilter.logRequestTo(log))
							.addFilter(ResponseLoggingFilter.logResponseTo(log))
							.build();
					System.out.println(Utils.getGlobalValue("baseUrl")+" base Uri is here");
				return req;
		}
				return req;
				}
	// when we are running multiple test with different set of data we use if condition check if it is null
	// its run from the begging either it's continue last log file.
	// this for Above code
	
	public  static String getGlobalValue(String key) throws IOException {
		Properties prop = new Properties();
		FileInputStream fis=new FileInputStream("C:\\Users\\anilv\\eclipse-workspace\\com.CucumberAutomtion\\src\\test\\java\\resources\\global.properties");
		
		prop.load(fis);
		return prop.getProperty(key);
		
		
		
}
	public String getJsonPath(Response resource, String key) {
		String resps=resource.asString();
		JsonPath js=new JsonPath(resps);
		return js.get(key);
	}
}
