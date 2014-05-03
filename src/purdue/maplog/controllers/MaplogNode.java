package purdue.maplog.controllers;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.Date;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.widget.ImageView;

public class MaplogNode implements Serializable {

	private static final long serialVersionUID = 2L;
	private int id;
	private String title;
	private String content;
	private int latitute;
	private int longitute;
	private Date date;
	private boolean isHidden = false;
	private byte[] picture;

	private static ByteBuffer dst;
	private static byte[] bytesar;

	public MaplogNode(Date date) {
		this.date = date;
	}

//	public MaplogNode(int id, String title, String content, Bitmap picture,
//			Location loc, Date date) {
//		this.id = id;
//		this.title = title;
//		this.content = content;
//		this.picture = picture;
//		this.date = date;
//	}

//	private void writeObject(ObjectOutputStream out) throws IOException {
//		out.writeInt(id);
//		out.writeObject(title);
//		out.writeObject(content);
//		out.writeInt(latitute);
//		out.writeInt(longitute);
//		out.writeObject(date);
//		out.writeBoolean(isHidden);
//		try {
//			out.writeInt(picture.getRowBytes());
//			out.writeInt(picture.getHeight());
//			out.writeInt(picture.getWidth());
//
//			int picSize = picture.getRowBytes() * picture.getHeight();
//			if (dst == null || picSize > dst.capacity())
//				dst = ByteBuffer.allocate(picSize);
//
//			out.writeInt(dst.capacity());
//
//			dst.position(0);
//
//			picture.copyPixelsToBuffer(dst);
//			if (bytesar == null || picSize > bytesar.length)
//				bytesar = new byte[picSize];
//
//			dst.position(0);
//			dst.get(bytesar);
//
//			out.write(bytesar, 0, bytesar.length);
//		} catch (Exception e) {
//		}
//		out.flush();
//		out.close();
//
//	}
//
//	private void readObject(ObjectInputStream in) throws IOException,
//			ClassNotFoundException {
//
//		id = in.readInt();
//		title = (String) in.readObject();
//		content = (String) in.readObject();
//		latitute = in.readInt();
//		longitute = in.readInt();
//		date = (Date) in.readObject();
//		isHidden = in.readBoolean();
//
//		try {
//			int nbRowBytes = in.readInt();
//			int height = in.readInt();
//			int width = in.readInt();
//
//			int picSize = nbRowBytes * height;
//
//			if (bytesar == null || picSize > bytesar.length)
//				bytesar = new byte[picSize];
//
//			int offset = 0;
//
//			while (in.available() > 0) {
//				offset = offset + in.read(bytesar, offset, in.available());
//			}
//
//			if (dst == null || picSize > dst.capacity())
//				dst = ByteBuffer.allocate(picSize);
//			dst.position(0);
//			dst.put(bytesar);
//			dst.position(0);
//			picture = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
//			picture.copyPixelsFromBuffer(dst);
//		} catch (Exception e) {
//			//e.printStackTrace();
//		}
//		in.close();
//	}

	public int getMaplogId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

	public Bitmap getPicture() {
		if(picture!=null)
			return BitmapFactory.decodeByteArray(picture, 0 ,picture.length);
		else 
			return null;
	}

	public boolean isHidden() {
		return isHidden;
	}

	public void setIsHidden(boolean isH) {
		isHidden = isH;
	}

	public int getLatitute() {
		return latitute;
	}

	public int getLongitute() {
		return longitute;
	}

	public Date getDate() {
		return date;
	}

	public void setTitle(String title2) {
		this.title = title2;
	}

	public void setContent(String content2) {
		this.content = content2;
	}

	public void setLatitute(int latitute) {
		this.latitute = latitute;
	}

	public void setLongitute(int longitute) {
		this.longitute = longitute;
	}

	public void setPic(Bitmap pic) {
		//this.picture = pic;
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		pic.compress(Bitmap.CompressFormat.JPEG, 100, stream);
		picture = stream.toByteArray();
		
	}
}
