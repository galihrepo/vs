package com.example.videostreaming;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

public class FragmentTwo extends Fragment implements SurfaceHolder.Callback {
	
//	private MediaController vidControl;
//	private VideoView vidView;
	private ProgressDialog dialog;
	private MediaPlayer mediaPlayer;
	private SurfaceHolder vidHolder;
	private SurfaceView vidSurface;
	private final String vidAddress = "https://archive.org/download/ksnn_compilation_master_the_internet/ksnn_compilation_master_the_internet_512kb.mp4";
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_two, container, false);
		
		vidSurface = (SurfaceView) view.findViewById(R.id.surfaceView1);
		vidHolder = vidSurface.getHolder();
		vidHolder.addCallback(this);
		
		dialog = new ProgressDialog(getActivity());
		
		Toast.makeText(getActivity(), "onCreateView TWO", Toast.LENGTH_SHORT).show();
		
		return view;
	}

//	@Override
//	public void onPrepared(MediaPlayer arg0) {
//		// TODO Auto-generated method stub
//		mediaPlayer.start();
//	}

	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void surfaceCreated(SurfaceHolder arg0) {
		// TODO Auto-generated method stub
		new Loading().execute();
		
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder arg0) {
		// TODO Auto-generated method stub
		
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
			    mediaPlayer.setDisplay(vidHolder);
			    mediaPlayer.setDataSource(vidAddress);
			    mediaPlayer.prepare();
			    mediaPlayer.setOnPreparedListener(this);
			    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
			} 
			catch(Exception e){
			    e.printStackTrace();
			}
			
			return null;
		}

		@Override
		public void onPrepared(MediaPlayer arg0) {
			// TODO Auto-generated method stub
			mediaPlayer.start();
		}
		
	}
}
