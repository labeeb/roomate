//package com.arshu.roommate.parse;
//
//import java.util.List;
//
//import com.arshu.roommate.exception.RMException;
//import com.arshu.roommate.util.RMConstants.TaskStatus;
//import com.parse.FindCallback;
//import com.parse.ParseException;
//import com.parse.ParseObject;
//import com.parse.ParseQuery;
//import com.parse.SaveCallback;
//
//public class Room implements RMParseObject {
//	
//	private static final String NAME= "name";
//	private String name; 
//	
//	private static final String DESCRIPTION= "description";
//	private String description;
//	
//	private static final String TYPE= "type";
//	private int type; 
//	
//	public Room(String name, String description) {
//		super();
//		this.name = name;
//		this.description = description;
//		this.type = 0;
//	}
//	
//	public Room() {}
//	
//	@Override
//	public ParseObject getParseObject() {
//		ParseObject testObject = new ParseObject(this.getClass().getSimpleName());
//		testObject.put(NAME, name);
//		testObject.put(DESCRIPTION, description);
//		testObject.put(TYPE, type);
//		return testObject;
//	}
//	
//	@Override
//	public RMParseObject getObject(ParseObject parseObject) {
//		Room mate = new Room();
//		mate.name = parseObject.getString(NAME);
//		mate.description = parseObject.getString(DESCRIPTION);
//		mate.type = parseObject.getInt(TYPE);
//		return mate;
//	}
//	
//	@Override
//	public void saveInBackground(final TaskCompleteCallBack callback) {
//		getParseObject().saveEventually(new SaveCallback() {
//			
//			@Override
//			public void done(ParseException e) {
//				if(e== null){
//					callback.onComplete(TaskStatus.SCUCCESS, null);
//				}else{
//					callback.onComplete(TaskStatus.FAILURE, new RMException(e));
//				}
//			}
//		});
//		
//	}
//	
//	public void checkUser(final String name,final LoginCallBack callback){
//		ParseQuery<ParseObject> query = ParseQuery.getQuery(this.getClass().getSimpleName());
//		query.whereEqualTo(NAME, name);
//		query.findInBackground(new FindCallback<ParseObject>() {
//		    public void done(List<ParseObject> scoreList, ParseException e) {
//		        if (e == null&& scoreList != null && scoreList.size() > 0) {
//		        	Room mate = (Room) getObject(scoreList.get(0));
//		        	callback.onSuccess(mate);
//		        } else {
//		        	if(e != null){
//		        		callback.onError(new RMException(e));
//		        	}else{
//		        		callback.onError(new RMException("No such user "));
//		        	}
//		        	
//		        }
//		    }
//		});
//	}
//	
//	
//	public interface LoginCallBack{
//		public void onSuccess(Room mate);
//		public void onError(RMException exception);
//		
//	}
//	
//	
//}
