package purdue.maplog.controllers;
import java.net.*;
import java.io.*;

import android.annotation.TargetApi;
import android.os.StrictMode;
import android.widget.Toast;

@TargetApi(9)
public class Client{
   public static void saveToServer(String username, String password, Serializable objectToBeSaved){
       Socket socket = null;
       
       try{
           socket = new Socket(InetAddress.getByName("lore.cs.purdue.edu"), 5217);
           ObjectOutputStream oo = new ObjectOutputStream(socket.getOutputStream());
           
           oo.writeObject("save");
           oo.flush();
           oo.writeObject(username);
           oo.flush();
           oo.writeObject(password);
           oo.flush();
           
           ObjectInputStream oi = new ObjectInputStream(socket.getInputStream());
           
           boolean loginSuccess = (Boolean) oi.readObject();
           if(loginSuccess)
               System.out.println("Welcome!");
           else{
               System.out.println("No such user/password combination!");
               return;
           }
           
           oo.writeObject(objectToBeSaved);
           oo.flush();
       } catch (IOException e){
           System.out.println(e);
       } catch (ClassNotFoundException e){
           System.out.println(e);
       }
       try {
		socket.close();
	} catch (IOException e) {
		e.printStackTrace();
	}
   }
   
   public static void changePassword(String username, String password, String newPassword){
		Socket socket = null;
		
		try{
			socket = new Socket(InetAddress.getByName("lore.cs.purdue.edu"), 5217);
			ObjectOutputStream oo = new ObjectOutputStream(socket.getOutputStream());
			
			oo.writeObject("changePassword");
			oo.flush();
			oo.writeObject(username);
			oo.flush();
			oo.writeObject(password);
			oo.flush();
			
			oo.writeObject(newPassword);
			oo.flush();
		} catch (IOException e){
			System.out.println(e);
		}
	}

   
   // Return null if username and password combination doesn't work.
   @TargetApi(9)
public static Object loadFromServer(String username, String password){
       Socket socket = null;
       Object targetObject = null;
       
       StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
       StrictMode.setThreadPolicy(policy); 
       try{
           socket = new Socket(InetAddress.getByName("lore.cs.purdue.edu"), 5217);
           ObjectOutputStream oo = new ObjectOutputStream(socket.getOutputStream());
           
           oo.writeObject("load");
           oo.flush();
           oo.writeObject(username);
           oo.flush();
           oo.writeObject(password);
           oo.flush();
           
           ObjectInputStream oi = new ObjectInputStream(socket.getInputStream());
           
           boolean loginSuccess = (Boolean) oi.readObject();
           if(loginSuccess)
           {
   			   Login_UI.isSuccess = true;
           }
           else{
               Login_UI.isSuccess = false;
               return null;
           }
           
           targetObject = oi.readObject();
       } catch (IOException e){
           System.out.println(e);
       } catch (ClassNotFoundException e){
           System.out.println(e);
       }
       try {
   		socket.close();
   	} catch (IOException e) {
   		e.printStackTrace();
   	}
       return targetObject;
   }
   
   
   public static boolean userRegister(String username, String password){
		Socket socket = null;
		boolean result = false;
		
		try{
			socket = new Socket(InetAddress.getByName("lore.cs.purdue.edu"), 5217);
			ObjectOutputStream oo = new ObjectOutputStream(socket.getOutputStream());
			
			oo.writeObject("register");
			oo.flush();
			oo.writeObject(username);
			oo.flush();
			oo.writeObject(password);
			oo.flush();
			
			oo.writeObject(username);
			oo.flush();
			oo.writeObject(password);
			oo.flush();
			
			
			ObjectInputStream oi = new ObjectInputStream(socket.getInputStream());
			
			result = ! (oi.readBoolean());
		} catch (IOException e){
			e.printStackTrace();
		}
		
		return result;
	}
   
}
