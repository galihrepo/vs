package com.example.videostreaming;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class FragmentThree extends Fragment {
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_three, container, false);
		
		Toast.makeText(getActivity(), "onCreateView THREE", Toast.LENGTH_SHORT).show();
		
		return view;
	}
}
