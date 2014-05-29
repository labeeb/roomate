package com.arshu.roommate.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.arshu.roommate.R;
import com.arshu.roommate.util.RMLog;

public class AddRoomFragment extends RMBaseFragment implements OnClickListener {
	private Button btnSave;
	private EditText etRoomName;
	private EditText etRoomDescription;


    public AddRoomFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add_room, container, false);
        btnSave = (Button) rootView.findViewById(R.id.fragment_addroom_btn_save);
        etRoomName = (EditText) rootView.findViewById(R.id.fragment_addroom_et_roomname);
        etRoomDescription = (EditText) rootView.findViewById(R.id.fragment_addroom_et_roomdescription);
        
        btnSave.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
//        ((HomeActivity) activity).onSectionAttached(
//                getArguments().getInt(ARG_SECTION_NUMBER));
    }

	@Override
	public void onClick(View v) {
		String name = etRoomName.getText().toString(); 
		String description = etRoomDescription.getText().toString();
		RMLog.d("test", "name:"+name+"and description:"+description);
//		Room room = new Room(name, description);
//		room.saveInBackground(new TaskCompleteCallBack() {
//			
//			@Override
//			public void onComplete(TaskStatus status, RMException exception) {
//				RMLog.d("Save", "on Complete of save Room in "+this.getClass().getSimpleName()+" status:"+status);
//			}
//		});
	}
}


