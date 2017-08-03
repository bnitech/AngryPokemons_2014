package com.example.mobileproject;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;

public class StartActivity extends ActionBarActivity {

	Button btnStart;
	FrameLayout frame;
	StartView startView;
	Drawable drawable;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		setTheme(android.R.style.Theme_NoTitleBar_Fullscreen);
		
		setContentView(R.layout.activity_start);
		
		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);
		
		int pxWith = metrics.widthPixels;
		int pxHeight = metrics.heightPixels;
		
		startView = new StartView(this,pxWith,pxHeight);
		
		frame = (FrameLayout)findViewById(R.id.startFrame);
		
		frame.addView(startView);
		
		
		
		
		btnStart = (Button)findViewById(R.id.btnStart);
		
		drawable = ((Button)findViewById(R.id.btnStart)).getBackground();
		drawable.setAlpha(70);
		
		btnStart.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i= new Intent(StartActivity.this, MainActivity.class);
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
	

	
}
