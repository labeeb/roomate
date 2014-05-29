package com.arshu.roommate;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseInstallation;
import com.parse.PushService;

public class RoomateApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();

		// Add your initialization code here
		Parse.initialize(this, "zSAILDjKHPoIdBrIsPI3SUqkrEx1yGadJpS69kCh", "dlZFvk3f8zl3ANRso2L1N3P76l84G5N8Z3FmaZ5R");

		
		PushService.setDefaultPushCallback(this, HomeActivity.class);

		ParseInstallation.getCurrentInstallation().saveInBackground();

//		ParseUser.enableAutomaticUser();
//		ParseACL defaultACL = new ParseACL();
//	    
//		// If you would like all objects to be private by default, remove this line.
//		defaultACL.setPublicReadAccess(true);
//		
//		ParseACL.setDefaultACL(defaultACL, true);
//		
//		//PushService.subscribe(this, "Giants", MainActivity.class);
//		
//		PushService.setDefaultPushCallback(this, HomeActivity.class);
//		
//		ParseInstallation.getCurrentInstallation().saveInBackground();
	}

}
