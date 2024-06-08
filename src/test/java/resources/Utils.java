package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Utils {
	
	//the variable req is made static here because the same instance will share to across every test case.
	public static RequestSpecification req;
	ResponseSpecification res;
	public RequestSpecification RequestSpec() throws IOException
	{
		if(req==null)
		{
		PrintStream log=new PrintStream(new FileOutputStream("D:\\books\\Testing\\Rest API\\RestWorkspace\\APIFramework\\target\\output.txt"));
		req = new RequestSpecBuilder().setBaseUri(getGlobalVeriable("baseurl"))
				.addQueryParam("key", "qaclick123").addFilter(RequestLoggingFilter.logRequestTo(log))
				.addFilter(ResponseLoggingFilter.logResponseTo(log)).setContentType(ContentType.JSON).build();
		
		return req;
		}
		return req;
	}
	
	public ResponseSpecification responsespec()
	{
		res=new ResponseSpecBuilder().expectStatusCode(200)
				.expectContentType(ContentType.JSON).build();
		return res;
	}
	
	public static String getGlobalVeriable(String key) throws IOException
	{
		Properties prop=new Properties();
		FileInputStream fis=new FileInputStream("D:\\books\\Testing\\Rest API\\RestWorkspace\\APIFramework\\src\\test\\java\\resources\\global.properties");
		prop.load(fis);
		return prop.getProperty(key);
	}
	
	public String getjsonvalue(String key, Response payload)
	{
		String res=payload.asString();
		System.out.println(res);
		JsonPath js=new JsonPath(res);
		System.out.println(js.getString(key));
		return js.getString(key);
	}

}
