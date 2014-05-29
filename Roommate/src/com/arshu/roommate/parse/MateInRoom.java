//package com.arshu.roommate.parse;
//
//import java.util.List;
//
//import com.arshu.roommate.exception.RMException;
//import com.arshu.roommate.util.RMConstants.TaskStatus;
//import com.arshu.roommate.util.RMConstants.UserType;
//import com.parse.FindCallback;
//import com.parse.ParseException;
//import com.parse.ParseObject;
//import com.parse.ParseQuery;
//import com.parse.SaveCallback;
//
//public class MateInRoom extends ParseObject {
//	
//	private static final String MATE= "mate";
//	private Mate mate; 
//	
//	private static final String ROOM= "room";
//	private Room room;
//	
//	private static final String USERTYPE= "userType";
//	private UserType userType; 
//	
//	public MateInRoom(Mate mate, Room room, UserType userType) {
//		super();
//		this.mate = mate;
//		this.room = room;
//		this.userType = userType;
//	}
//
//	public MateInRoom() {}
//	
//	
//	
////	public ParseObject getParseObject() {
////		ParseObject testObject = super.getParseObject();//new ParseObject(this.getClass().getSimpleName());
////		testObject.put(MATE, mate);
////		testObject.put(ROOM, room);
////		testObject.put(USERTYPE, userType);
////		
////		this.getObject(testObject);
////		return testObject;
////	}
//	
////	@Override
////	public RMParseObject getObject(ParseObject parseObject) {
////		MateInRoom mate = new MateInRoom();
////		RMParseObject.loadParentValues(mate, parseObject);
////		mate.mate = (Mate) parseObject.get(MATE);
////		mate.room =  (Room) parseObject.get(ROOM);
////		mate.userType = (UserType) parseObject.get(USERTYPE);
////		return mate;
////	}
////	
////	@Override
////	public void saveInBackground(final TaskCompleteCallBack callback) {
////		getParseObject().saveEventually(new SaveCallback() {
////			
////			@Override
////			public void done(ParseException e) {
////				if(e== null){
////					callback.onComplete(TaskStatus.SCUCCESS, null);
////				}else{
////					callback.onComplete(TaskStatus.FAILURE, new RMException(e));
////				}
////			}
////		});
////		
////	}
//	
//	public void checkUser(final String name,final LoginCallBack callback){
////TODO		ParseQuery<ParseObject> query = ParseQuery.getQuery(this.getClass().getSimpleName());
////		query.whereEqualTo(NAME, name);
////		query.findInBackground(new FindCallback<ParseObject>() {
////		    public void done(List<ParseObject> scoreList, ParseException e) {
////		        if (e == null&& scoreList != null && scoreList.size() > 0) {
////		        	MateInRoom mate = (MateInRoom) getObject(scoreList.get(0));
////		        	callback.onSuccess(mate);
////		        } else {
////		        	if(e != null){
////		        		callback.onError(new RMException(e));
////		        	}else{
////		        		callback.onError(new RMException("No such user "));
////		        	}
////		        	
////		        }
////		    }
////		});
//	}
//	
//	
//	public interface LoginCallBack{
//		public void onSuccess(MateInRoom mate);
//		public void onError(RMException exception);
//		
//	}
//	
//	
//}
