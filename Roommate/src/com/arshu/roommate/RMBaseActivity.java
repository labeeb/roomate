package com.arshu.roommate;

import com.arshu.roommate.parse.Mate;
import com.arshu.roommate.util.RMConstants;
import com.arshu.roommate.util.RMLog;

import android.app.Activity;
import android.os.Bundle;

public class RMBaseActivity extends Activity{
    private Mate mate ;

	public Mate getLoggedMate() {
		if(null == mate){
			Bundle bundle =getIntent().getExtras();;
			if(null != bundle){
				mate = bundle.getParcelable(RMConstants.BK_LOGGED_MATE);
			}else{
				RMLog.unexpected(getClass(), " RMBaseActivity with getIntent().getExtras() null");
			}
			return mate;
		}
		
		return mate;
	}
    
    

}
