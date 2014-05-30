package com.arshu.roommate.fragment;

import java.util.List;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.arshu.roommate.R;
import com.arshu.roommate.exception.RMException;
import com.arshu.roommate.parse.Room;
import com.arshu.roommate.task.GetAllRooms;
import com.arshu.roommate.task.GetAllRooms.GetRoomsCallBack;
import com.arshu.roommate.util.RMLog;

public class RoomListFragment extends RMBaseFragment {
	
	private ListView listview;
	 
	 
    public RoomListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_listroom, container, false);
        
         listview= (ListView) rootView.findViewById(R.id.listrroom_lv_rooms);
        
        getActivity().setTitle(getCurrentMate().getEmail());
        
        new GetAllRooms(getCurrentMate(), new GetRoomsCallBack() {
			
			@Override
			public void onSuccess(List<Room> rooms) {
				RMLog.d(RoomListFragment.class, "No of rooms:"+rooms.size());
				
				ArrayAdapter<Room> listAdapter = new ArrayAdapter<Room>(getActivity(), android.R.layout.simple_list_item_1, rooms); 
				listview.setAdapter(listAdapter);
			}
			
			@Override
			public void onError(RMException exception) {
				// TODO Auto-generated method stub
				
			}
		});
        
        return rootView;
    }
 
}


