package com.arshu.roommate.parse;

import java.util.List;

import com.arshu.roommate.exception.RMException;
import com.arshu.roommate.util.RMConstants.TaskStatus;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

public class Mate extends RMParseObject {
	
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
	
	@Override
	public ParseObject getParseObject() {
		ParseObject testObject = new ParseObject(this.getClass().getSimpleName());
		testObject.put(NAME, name);
		testObject.put(EMAIL, email);
		testObject.put(PASSWORD, password);
		testObject.put(TYPE, type);
		return testObject;
	}
	
	@Override
	protected void loadChildValues(ParseObject parseObject) {
		this.name = parseObject.getString(NAME);
		this.email = parseObject.getString(EMAIL);;
		this.password = parseObject.getString(PASSWORD);;
		this.type = parseObject.getInt(TYPE);
	}
	
	
	@Override
	public void saveInBackground(final TaskCompleteCallBack callback) {
		getParseObject().saveEventually(new SaveCallback() {
			
			@Override
			public void done(ParseException e) {
				if(e== null){
					callback.onComplete(TaskStatus.SCUCCESS, null);
				}else{
					callback.onComplete(TaskStatus.FAILURE, new RMException(e));
				}
			}
		});
		
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
	
	
}
