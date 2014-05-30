package com.arshu.roommate.parse;

import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

import com.arshu.roommate.exception.RMException;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class Mate extends RMBaseValueObject {
	
	private static final String NAME= "name";
	private String name; 
	
	private static final String EMAIL= "email";
	private String email;
	
	private static final String PASSWORD= "password";
	private String password;
	
	private static final String TYPE= "type";
	private int type; 
	
	public Mate(String name, String email, String password, int type) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.type = type;
	}
	
	public Mate() {}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	@Override
	public void createParseObject() {
		ParseObject testObject = new ParseObject(this.getClass().getSimpleName());
		testObject.put(NAME, name);
		testObject.put(EMAIL, email);
		testObject.put(PASSWORD, password);
		testObject.put(TYPE, type);
	}
	
	@Override
	protected void loadChildValues(ParseObject parseObject) {
		this.name = parseObject.getString(NAME);
		this.email = parseObject.getString(EMAIL);;
		this.password = parseObject.getString(PASSWORD);;
		this.type = parseObject.getInt(TYPE);
	}
	
	
	public void checkUser(final String email,final String password,final LoginCallBack callback){
		ParseQuery<ParseObject> query = ParseQuery.getQuery(this.getClass().getSimpleName());
		query.whereEqualTo(EMAIL, email);
		query.whereEqualTo(PASSWORD, password);
		query.findInBackground(new FindCallback<ParseObject>() {
		    public void done(List<ParseObject> scoreList, ParseException e) {
		        if (e == null&& scoreList != null && scoreList.size() > 0) {
		        	Mate.this.loadValuesFromParseobject(scoreList.get(0));
		        	callback.onSuccess(Mate.this);
		        } else {
		        	if(e != null){
		        		callback.onError(new RMException(e));
		        	}else{
		        		callback.onError(new RMException("No such user "));
		        	}
		        	
		        }
		    }
		});
	}
	
	
	public interface LoginCallBack{
		public void onSuccess(Mate mate);
		public void onError(RMException exception);
		
	}

	

	
/*
 * For Parcelable 	
 */

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		super.writeParentToParcel(dest);
		dest.writeString(name);
		dest.writeString(email);
		dest.writeString(password);
		dest.writeInt(type);
	}
	private Mate(Parcel in) {
		super.readParent(in);
		name = in.readString();
		email = in.readString();
		password = in.readString();
		type = in.readInt();
	}

	public static final Parcelable.Creator<Mate> CREATOR = new Parcelable.Creator<Mate>() {
		public Mate createFromParcel(Parcel in) {
			return new Mate(in);
		}

		public Mate[] newArray(int size) {
			return new Mate[size];
		}
	};
}
