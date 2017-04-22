package main;

import java.io.*; 
import java.net.*;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter; 
public class MonitorCheckHostSrv {
	
	//Log serwera 1.0
	public static void logger (String logInfo) {  

	    Logger logger = Logger.getLogger("MonitorCheckHostSrv");  
	    FileHandler fh;  

	    try {  

	          
	        fh = new FileHandler("MonitorCheckHostSrv.log", true);  
	        logger.addHandler(fh);
	        SimpleFormatter formatter = new SimpleFormatter();  
	        fh.setFormatter(formatter);  

	        logger.setUseParentHandlers(false);
	        logger.info(logInfo);  
	        fh.close();

	    } catch (SecurityException e) {  
	        e.printStackTrace();  
	    } catch (IOException e) {  
	        e.printStackTrace();  
	    }  

	    

	}

	
	
	//wys³anie ¿¹dania do hosta
	public static void Exec(int q, String msg, String ip, String OS) throws Exception
	{
		
		
		String sentence; 
		String modifiedSentence; 
		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in)); 
		Socket clientSocket = new Socket(ip, 6789);
		
		if (q==0){
			
			clientSocket.close();	
		}
		else {
		DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream()); 
		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); 
		//sentence = inFromUser.readLine();
		
		sentence = msg;
		outToServer.writeBytes(sentence+'\n'); 
		Boolean a =true;
		do{
			
		System.out.println("====================================================");
		modifiedSentence = inFromServer.readLine(); 
		
		System.out.println(">"+ modifiedSentence );
			while(a == true){
				
			
			
			if (modifiedSentence.toUpperCase().equals("END ") ) a=false;  else modifiedSentence = inFromServer.readLine(); System.out.println(">"+ modifiedSentence ); 
			
			
			if (modifiedSentence==null) a=false;
			}
			
		}while (a == true);
		} 
		System.out.println("====================================================");
		System.out.println("DONE");
	}
	

	
	
	
	public static void main(String commandLineArgs[]) throws Exception 
	{ 
		
		try {
		String arg_1= commandLineArgs[1].toString();
		
		if (commandLineArgs[0]!=null && commandLineArgs[1]!=null) { Exec(1,arg_1,commandLineArgs[0],null); logger(arg_1+" "+commandLineArgs[0] );}
		
			
		
		else
		{
			
			logger("no args");
		}
		}
		catch (Exception e)
		{
			
			System.out.println("no arguments or no connection \n");
			
			
			
			logger("ERROR no arguments or no connection \n");
			logger("ERROR "+e.toString());
		}
	
			
			System.out.println("Disconect");
			logger("Disconect");
		
	//	}
	}}