package com.arshu.roommate.parse;

import com.parse.ParseObject;

public abstract class RMParseObject {
	protected static final String OBJECTID= "objectid";
	protected String objectid;
	
	//abstract public ParseObject getParseObject();
	abstract public void saveInBackground(TaskCompleteCallBack callback);
	
	abstract protected void loadChildValues(ParseObject parseObject);
	private void loadValueParentValues(ParseObject parseObject){
		 this.objectid = parseObject.getObjectId();
	}
	final public void loadValuesFromParseobject(ParseObject parseObject){
		loadValueParentValues(parseObject);
		loadChildValues(parseObject);
	}
	
	
	public static void loadParentValues(RMParseObject rmParseObject,ParseObject parseObject){
		rmParseObject.objectid = parseObject.getObjectId();
		
	}
	
	public ParseObject getParseObject(){
		ParseObject parseObject = new ParseObject(this.getClass().getSimpleName());
		parseObject.put(OBJECTID, objectid);
		return parseObject;
	}
}
