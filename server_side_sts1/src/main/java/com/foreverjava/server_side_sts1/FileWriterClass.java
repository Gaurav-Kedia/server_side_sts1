package com.foreverjava.server_side_sts1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class FileWriterClass {
	
	private static boolean isWindows = System.getProperty("os.name").toLowerCase().startsWith("windows");
	String code;
	String input;
	Scanner compiler_output, runtime;
	String command, command2;
	ProcessBuilder pb;
	Process process, process2;
	StringBuilder output = new StringBuilder("Here is the output for your code : \n");
	
	public FileWriterClass(String val, String in) {
		this.code=val;
		this.input=in;
	}
	
	public StringBuilder write() throws IOException {
		make_java_file();
		make_input_file();
		
		compiler();
		runtime();
			
        return output;
	}
	
	public void make_java_file() {
		try {
            File newTextFile = new File("/tmp/code.java");
            FileWriter fw = new FileWriter(newTextFile);
            //code = code.substring(1, code.length()-1);
            fw.write(code);
            fw.close();
	    System.out.println("successfully create code file");

        } catch (IOException iox) {
            iox.printStackTrace();
        }
	}
	
	public void make_input_file() {
		try {
            File newTextFile = new File("/tmp/input.txt");
            FileWriter fw = new FileWriter(newTextFile);
            //input = input.substring(1, input.length()-1);
            fw.write(input);
            fw.close();
	    System.out.println("successfully create input file");

        } catch (IOException iox) {
            iox.printStackTrace();
        }
	}
	
	public void compiler() throws IOException {
	File location = new File("/tmp/");
	command = "javac code.java";
		
	output.append("Running in: " + location);
        output.append("Command: " + command);
		
	ProcessBuilder builder = new ProcessBuilder();
        builder.directory(location);

        if(isWindows) {
            	builder.command("cmd.exe", "/c", command2);
        }else {
            	builder.command("sh", "-c", command2);
        }
	//command = "bash /c pwd";
	//command = "cmd /d dir";
	process = null;
	try
	{
		//process = Runtime.getRuntime().exec(command);
		process = builder.start();
	} 
	catch (IOException e)
	{
		e.printStackTrace();
	}
		
	final BufferedReader is = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
    	output.append("from compiler : \n");
        while ((line = is.readLine()) != null) {
        	System.out.println("c : " + line);
        	output.append(line);
        	output.append("\n");
        }
        final BufferedReader is2 = new BufferedReader(new InputStreamReader(process.getErrorStream()));
        boolean f = false;
        while ((line = is2.readLine()) != null) {
        	f = true;
        	System.out.println(line);
        	output.append(line);
        	output.append("\n");
        }
        
        if(!f) {
        	output.append("successfully compiled \\n");
        }
	}
	
	public void runtime() throws IOException {
		File location = new File("/tmp/");
		command2 = "java code.java < input.txt";
		
		output.append("Running in: " + location);
        	output.append("Command: " + command2);
		
		ProcessBuilder builder = new ProcessBuilder();
        	builder.directory(location);

        	if(isWindows) {
            		builder.command("cmd.exe", "/c", command2);
        	}else {
            		builder.command("sh", "-c", command2);
        	}
		//command2 = "bash /c pwd";
		//command2 = "cmd /d dir";
		process2 = null;
		try
		{
		    //process2 = Runtime.getRuntime().exec(command2);
		      process2 = builder.start();
		} 
		catch (IOException e)
		{
		    e.printStackTrace();
		}
	
		final BufferedReader is3 = new BufferedReader(new InputStreamReader(process2.getInputStream()));
		String line2;
		output.append("from runtime : \n");
		while ((line2 = is3.readLine()) != null) {
			System.out.println("r : " + line2);
			output.append(line2);
			output.append("\n");
		}
		final BufferedReader is4 = new BufferedReader(new InputStreamReader(process2.getErrorStream()));
		while ((line2 = is4.readLine()) != null) {
			System.out.println(line2);
			output.append(line2);
			output.append("\n");
		}
	}

}
