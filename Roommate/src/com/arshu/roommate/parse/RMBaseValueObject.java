package com.arshu.roommate.parse;

import android.os.Parcel;
import android.os.Parcelable;

import com.arshu.roommate.exception.RMException;
import com.arshu.roommate.util.RMConstants.TaskStatus;
import com.arshu.roommate.util.RMLog;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;

public abstract class RMBaseValueObject implements Parcelable{
	
	protected ParseObject parseObject;

	public ParseObject getParseObject() {
		return parseObject;
	}
	
	public void setParseObject(ParseObject parseObject) {
		this.parseObject = parseObject;
	}



	abstract protected void loadChildValues(ParseObject parseObject);

	
	final public void loadValuesFromParseobject(ParseObject parseObject){
		this.parseObject = parseObject;
		try {
			this.parseObject.fetchIfNeeded();
			loadChildValues(parseObject);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
//	public void loadParentValues(RMParseObject rmParseObject,ParseObject parseObject){
//		rmParseObject.objectid = parseObject.getObjectId();
//		
//	}
	
	/**
	 * This will create the parse object. This is called when an object is saved for the first time
	 * @return
	 */
	public abstract void createParseObject();
	
//	public ParseObject getParseObject(){
//		
//		ParseObject parseObject = new ParseObject(this.getClass().getSimpleName());
//		parseObject.put(OBJECTID, objectid);
//		return parseObject;
//	}
	

	public void saveInBackground(final TaskCompleteCallBack callback) {
		//final ParseObject parseObject = getParseObject();
		if(parseObject == null){
			createParseObject();
		}
		if(parseObject == null){
			callback.onComplete(TaskStatus.FAILURE, null, new RMException("parseObject is null"));
			return;
		}
		parseObject.saveEventually(new SaveCallback() {
			
			@Override
			public void done(ParseException e) {
				//String objectId = null;
				if(e== null){
					
					try {
						parseObject.refresh();
					} catch (ParseException e1) {
						RMLog.unexpected(getClass(), "ParseException on refresh");
						e1.printStackTrace();
					}
					callback.onComplete(TaskStatus.SCUCCESS,parseObject, null);
				}else{
					callback.onComplete(TaskStatus.FAILURE,null, new RMException(e));
				}
			}
		});
		
	}

	/**
	 * For Parcelable 	
	 */
		

	@Override
	public int describeContents() {
		return 0;
	}
	
	public void writeParentToParcel(Parcel dest) {
		saveParseObject(dest, parseObject);
	}
	
	public void readParent(Parcel in) {
		parseObject = readPaseObject(in);
	}
	
	public static void saveParseObject(Parcel dest, ParseObject pObject){
		if(pObject == null){
			RMLog.unexpected(RMBaseValueObject.class, "parseObject null while trying to writeParentToParcel");
		}
		dest.writeString(pObject.getObjectId());
		dest.writeString(pObject.getClassName());
	}
	
	public static ParseObject readPaseObject(Parcel in){
		String objectid = in.readString();
		String className = in.readString();
		ParseObject pObject = new ParseObject(className);
		pObject.setObjectId(objectid);
		return pObject;
	}
}
