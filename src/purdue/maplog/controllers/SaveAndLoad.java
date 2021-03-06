package purdue.maplog.controllers;

import java.io.*;

public class SaveAndLoad {
	
	public SaveAndLoad (){
	
	}
	
	public void save() throws Exception {
		// Write to disk with FileOutputStream
		

		Client.saveToServer(Login_UI.user.getUserName(),Login_UI.user.getPassWord(), Login_UI.user);
		FileOutputStream f_out;
		File f = new File("/sdcard/MapLogData.data");
		if(!f.exists())
		    f.createNewFile();
		f_out = new FileOutputStream(f);


		// Write object with ObjectOutputStream
		ObjectOutputStream obj_out = new
			ObjectOutputStream (f_out);

		
		// Write object out to disk
//		obj_out.writeObject ( Login_UI.user );
		
		obj_out.close();
		f_out.close();
		
	}
	
	public void load() throws IOException, ClassNotFoundException {
		// Read from disk using FileInputStream
		FileInputStream f_in = new 
			FileInputStream("/sdcard/MapLogData.data");

		// Read object using ObjectInputStream
		ObjectInputStream obj_in = 
			new ObjectInputStream (f_in);

		// Read an object
//		Login_UI.user = (UserNode) obj_in.readObject();
		Login_UI.user = (UserNode) Client.loadFromServer(Login_UI.user.getUserName(), Login_UI.user.getPassWord());
		obj_in.close();
		f_in.close();
	}
	
}
