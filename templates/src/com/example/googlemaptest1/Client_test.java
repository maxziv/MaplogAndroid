package com.example.googlemaptest1;


import java.io.*; 
import java.net.*;
import java.util.*; 

import android.widget.Toast;

/**  * This program makes a socket connection to the atomic clock in Boulder, Colorado, and prints the  * time that the server sends.  * @version 1.20 2004-08-03  * @author Cay Horstmann  */
public class Client_test {  
String se;
int port;

	public Client_test(String se, int port){
		this.se=se;
		this.port=port;
		
	}
 
  public String getServerString()    {   
	  String line="";
    try       {        
      Socket s = new Socket(se, port);     
      try          {        
        InputStream inStream = s.getInputStream();    
        Scanner in = new Scanner(inStream);              
        while (in.hasNextLine())             {          
         line = in.nextLine();  	

       //  return line;
        } 
        return line;
        
      }          finally   
      {       
        s.close();   
      }    
    }  
    catch (IOException e)  
    {          e.printStackTrace();    
    }   
   // return line;
    return "aa";
  //  return null;
    
  }
}

