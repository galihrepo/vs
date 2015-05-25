package com.example.videostreaming;

import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.VideoView;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class FragmentFive extends Fragment implements OnClickListener, OnSeekBarChangeListener {
	
	private final long UPDATE_SEEKBAR = 1; 
	private int duration;
	
	private Uri vidUri;
	private String vidAddress;
	
	private VideoView vidView;
	private SeekBar seekbar;
	
	private Button btnStart;
	private Button btnPause;
	
	private Handler handler;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_five, container, false);
		
		seekbar = (SeekBar) view.findViewById(R.id.seekBar1);
		vidView = (VideoView) view.findViewById(R.id.videoView1);
		btnStart = (Button) view.findViewById(R.id.button1);
		btnPause = (Button) view.findViewById(R.id.button2);
		
		handler = new Handler();
		
		vidAddress = Url.VIDEO_STORAGE;
//		vidAddress = Url.VIDEO_ONLINE;
		vidUri = Uri.parse(vidAddress);
		vidView.setVideoURI(vidUri);
		
		btnStart.setOnClickListener(this);
		btnPause.setOnClickListener(this);
		
		seekbar.setOnSeekBarChangeListener(this);
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				while(true) {
					try {
						Thread.sleep(UPDATE_SEEKBAR);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					handler.post(new Runnable() {
						@Override
						public void run() {
							// TODO Auto-generated method stub
							if (vidView != null) {
								duration = vidView.getDuration();
								seekbar.setMax(duration);
								seekbar.setProgress(vidView.getCurrentPosition());
							}
							
						}
					});
				}
			}
		}).start();

		
		return view;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int id = v.getId();
		if (id == btnStart.getId()) {
			vidView.start();			
		} else {
			vidView.pause();
			vidView.seekTo(vidView.getCurrentPosition());
		}
	}

	@Override
	public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStartTrackingTouch(SeekBar arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStopTrackingTouch(SeekBar arg0) {
		// TODO Auto-generated method stub
		int position = arg0.getProgress();
		vidView.seekTo(position);
		if (vidView.isPlaying()) {
			vidView.start();
		}
	}
}
