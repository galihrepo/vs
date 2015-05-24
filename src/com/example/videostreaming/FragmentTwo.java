package com.example.videostreaming;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

public class FragmentTwo extends Fragment implements SurfaceHolder.Callback, OnClickListener {
	
	private final String KEY_BUNDLE = "duration";
	
	private ProgressDialog dialog;
	private MediaPlayer mediaPlayer;
	private SurfaceHolder vidHolder;
	private SurfaceView vidSurface;
	
	private Button btnPlay;
	private Button btnPause;
	private Button btnResume;
	private Button btnReset;
	
	private SeekBar seekbar;
	
	private int position = 0;
	
//	@Override
//	public void onStart() {
//		// TODO Auto-generated method stub
//		super.onStart();
//	}
//	//==========================
//	// Handling screen rotation
//	//==========================
//	@Override
//	public void onSaveInstanceState(Bundle outState) {
//		// TODO Auto-generated method stub
//		if (mediaPlayer != null && mediaPlayer.isPlaying()) {
//			outState.putInt(KEY_BUNDLE, mediaPlayer.getCurrentPosition());
//			mediaPlayer.pause();
//			Toast.makeText(getActivity(), "onSaveState", Toast.LENGTH_SHORT).show();
//		}
//		Toast.makeText(getActivity(), "onSaveState", Toast.LENGTH_SHORT).show();
//		super.onSaveInstanceState(outState);
//	}
//
//	// Fragment doesn't have onRestoreInstanceState
//	// we will get the same result using onActivityCreated
//	@Override
//	public void onCreate(Bundle savedInstanceState) {
//		// TODO Auto-generated method stub
//		super.onCreate(savedInstanceState);
//		if (savedInstanceState != null) {
////			if (mediaPlayer != null && mediaPlayer.isPlaying()) {
////				mediaPlayer.seekTo(savedInstanceState.getInt(KEY_BUNDLE));
////				mediaPlayer.start();
//				Toast.makeText(getActivity(), "onActivityCreated", Toast.LENGTH_SHORT).show();
////			}
//		}
//	}
//	//==========================
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_two, container, false);
		
		btnPlay = (Button) view.findViewById(R.id.btnPlay);
		btnPause = (Button) view.findViewById(R.id.btnPause);
		btnResume = (Button) view.findViewById(R.id.btnResume);
		btnReset = (Button) view.findViewById(R.id.btnReset);
		
		btnPlay.setOnClickListener(this);
		btnPause.setOnClickListener(this);
		btnResume.setOnClickListener(this);
		btnReset.setOnClickListener(this);
		
		vidSurface = (SurfaceView) view.findViewById(R.id.surfaceView1);
		vidHolder = vidSurface.getHolder();
		vidHolder.addCallback(this);
		
		dialog = new ProgressDialog(getActivity());
		
//		Toast.makeText(getActivity(), "onCreateView TWO", Toast.LENGTH_SHORT).show();
		
		return view;
	}

	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
//		Toast.makeText(getActivity(), "surfaceChanged", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void surfaceCreated(SurfaceHolder arg0) {
		// TODO Auto-generated method stub
//		Toast.makeText(getActivity(), "surfaceCreated", Toast.LENGTH_SHORT).show();
//		if (mediaPlayer != null && mediaPlayer.isPlaying()) {
			// do nothing
//		} else {
			new Loading().execute();
//		}
		
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder arg0) {
		// TODO Auto-generated method stub
//		Toast.makeText(getActivity(), "surfaceDestroyed", Toast.LENGTH_SHORT).show();
	}
	
	private class Loading extends AsyncTask<Void, Void, Void> implements OnPreparedListener {

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			if (dialog.isShowing()) {
				dialog.dismiss();
			}
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			dialog.setTitle("loading..");
			dialog.show();
		}

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			try {
			    mediaPlayer = new MediaPlayer();
//				mediaPlayer = MediaPlayer.create(getActivity(), Uri.parse(Url.VIDEO_STORAGE));
			    mediaPlayer.setDisplay(vidHolder);
			    mediaPlayer.setDataSource(getActivity(), Uri.parse(Url.VIDEO_STORAGE));
			    mediaPlayer.prepare();
			    mediaPlayer.setOnPreparedListener(this);
			    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
			} 
			catch(Exception e){
			    Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
			}
			
			return null;
		}

		@Override
		public void onPrepared(MediaPlayer arg0) {
			// TODO Auto-generated method stub
//			mediaPlayer.start();
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int id = v.getId();
		if (id == btnPlay.getId()) {
			mediaPlayer.start();
		} else if (id == btnPause.getId()) {
			mediaPlayer.pause();
			position = mediaPlayer.getCurrentPosition();
		} else if (id == btnResume.getId()) {
			mediaPlayer.seekTo(position);
			mediaPlayer.start();
		} else if (id == btnReset.getId()) {
//			mediaPlayer.pause();
//			position = 0;
			mediaPlayer.reset();
		}
	}
}
