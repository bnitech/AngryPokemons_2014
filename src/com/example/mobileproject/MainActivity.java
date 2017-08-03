package com.example.mobileproject;

import java.io.IOException;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.FrameLayout;

public class MainActivity extends ActionBarActivity {

	View view;
	FrameLayout frame;
	Button btnStage,btnAbout;
	MediaPlayer bgm;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		setTheme(android.R.style.Theme_NoTitleBar_Fullscreen);
		
		setContentView(R.layout.activity_main);
		
		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);
		
		int pxWith = metrics.widthPixels;
		int pxHeight = metrics.heightPixels;
		
		
		frame =(FrameLayout)findViewById(R.id.mainFrame);
		
		view = new MainView(this,pxWith,pxHeight);
		frame.addView(view);
		
		btnStage = (Button)findViewById(R.id.btnStage);
		btnAbout = (Button)findViewById(R.id.btnAbout);
		
		bgm = MediaPlayer.create(this, R.raw.main);
		bgm.setVolume(0.2f, 0.2f);
		bgm.setLooping(true);
		bgm.start();
		
		
		btnStage.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent i= new Intent(MainActivity.this, StageActivity.class);
                startActivity(i);
                bgm.stop();
				
			}
		});
		btnAbout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i= new Intent(MainActivity.this, AboutActivity.class);
                startActivity(i);
				
			}
		});
		
	
	}
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {

		case KeyEvent.KEYCODE_MENU:
			return true;

		}
		return super.onKeyDown(keyCode, event);

	}
	@Override
	protected void onDestroy() {

		bgm.stop();
		
		super.onDestroy();
	}
	
}
