package com.arshu.roommate.parse;

import com.arshu.roommate.exception.RMException;
import com.arshu.roommate.util.RMConstants;

public interface TaskCompleteCallBack {
	
	public void onComplete(RMConstants.TaskStatus status, RMException exception);
}
