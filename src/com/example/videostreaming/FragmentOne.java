package com.example.videostreaming;

import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

public class FragmentOne extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_one, container, false);
		
		String vidAddress = "https://archive.org/download/ksnn_compilation_master_the_internet/ksnn_compilation_master_the_internet_512kb.mp4";
		Uri vidUri = Uri.parse(vidAddress);
		
		MediaController vidControl = new MediaController(getActivity());
		
		VideoView vidView = (VideoView) view.findViewById(R.id.videoView1);
		vidView.setVideoURI(vidUri);
		vidView.start();
		
		vidControl.setAnchorView(vidView);
		vidView.setMediaController(vidControl);
		
		Toast.makeText(getActivity(), "onCreateView ONE", Toast.LENGTH_SHORT).show();
		
		return view;
	}

}
