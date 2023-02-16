/**
 * 
 */
package com.foreverjava.server_side_sts1;

import java.io.IOException;

import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author GAURAV
 *
 */
@CrossOrigin
@RestController
public class Server_controller {
	@GetMapping("/Hello")
	public String welcome() {
		System.out.println("..................getting a request....");
		return new String("APi is working as expected - Gaurav");
	}
	
	@GetMapping("/Error")
	public String error() {
		return "error 1";
	}
	
//	@PostMapping("/jc")
//	public StringBuilder save(@RequestBody String code) throws IOException {
//		System.out.println(code);
//		FileWriterClass f = new FileWriterClass(code);
//		return f.write();
//	}
	@PostMapping("/java")
	public StringBuilder runnable(@RequestBody String s) throws IOException {
		JSONObject j = new JSONObject(s);
		System.out.println(j.getString("code") + " : " +j.getString("input"));
		//String[] data = arr.split("::::");
		//data[0] = data[0].trim();
		//data[1] = data[1].trim();
		
		FileWriterClass f = new FileWriterClass(j.getString("code"),j.getString("input"));
		return f.write();
	}
}




