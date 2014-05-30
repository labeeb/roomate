package com.arshu.roommate.parse;

import android.os.Parcel;
import android.os.Parcelable;

import com.parse.ParseObject;

public class Room extends RMBaseValueObject {
	
	private static final String NAME= "name";
	private String name; 
	
	private static final String DESCRIPTION= "description";
	private String description;
	
	private static final String CREATEDMATEOBJECT= "createdMateObject";
	private ParseObject createdMateObject;
	
	private static final String TYPE= "type";
	private int type; 
	
	public Room(String name, String description,ParseObject createdMateObject) {
		super();
		this.name = name;
		this.description = description;
		this.type = 0;
		this.createdMateObject = createdMateObject;
	}
	
	public Room() {}
	
	@Override
	public void createParseObject() {
		parseObject = new ParseObject(this.getClass().getSimpleName());
		parseObject.put(NAME, name);
		parseObject.put(DESCRIPTION, description);
		parseObject.put(CREATEDMATEOBJECT, createdMateObject);
		parseObject.put(TYPE, type);
	}
	
	@Override
	protected void loadChildValues(ParseObject parseObject) {
		this.name = parseObject.getString(NAME);
		this.description = parseObject.getString(DESCRIPTION);
		this.createdMateObject = parseObject.getParseObject(CREATEDMATEOBJECT);
		this.type = parseObject.getInt(TYPE);
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	/*
	 * For Parcelable 	
	 */
	

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		super.writeParentToParcel(dest);
		dest.writeString(name);
		dest.writeString(description);
		saveParseObject(dest, createdMateObject);
		dest.writeInt(type);
	}
	private Room(Parcel in) {
		super.readParent(in);
		name = in.readString();
		description = in.readString();
		createdMateObject = readPaseObject(in);
		type = in.readInt();
	}

	public static final Parcelable.Creator<Room> CREATOR = new Parcelable.Creator<Room>() {
		public Room createFromParcel(Parcel in) {
			return new Room(in);
		}

		public Room[] newArray(int size) {
			return new Room[size];
		}
	};

	
}
