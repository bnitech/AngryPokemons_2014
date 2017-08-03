package com.example.mobileproject;

import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;


public class AboutActivity extends ActionBarActivity {
	
	Activity stageActivity = this;
	
	Button btnLeft,btnRight,btnExit; 
	TextView textPage;

	FrameLayout frame;
	AboutView aboutView;
	
	Drawable drawable;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		setTheme(android.R.style.Theme_NoTitleBar_Fullscreen);
		
		setContentView(R.layout.activity_about);
		
		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);
		
		int pxWith = metrics.widthPixels;
		int pxHeight = metrics.heightPixels;
		
		aboutView = new AboutView(this,pxWith,pxHeight);
		frame = (FrameLayout)findViewById(R.id.aboutFrame);
		frame.addView(aboutView);
		
		drawable = ((Button) findViewById(R.id.btnLeft)).getBackground();
		drawable.setAlpha(70);
		drawable = ((Button) findViewById(R.id.btnRight)).getBackground();
		drawable.setAlpha(70);
		drawable = ((Button) findViewById(R.id.btnExit)).getBackground();
		drawable.setAlpha(70);
		
		btnLeft = (Button)findViewById(R.id.btnLeft);
		btnRight = (Button)findViewById(R.id.btnRight);
		btnExit = (Button)findViewById(R.id.btnExit);
		textPage = (TextView)findViewById(R.id.textPage);
		
		textPage.setText("0"+"/"+aboutView.pageMax);
		
		
		btnLeft.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				if(aboutView.currentPage > aboutView.pageMin){
					aboutView.currentPage--;
					String temp = String.valueOf(aboutView.currentPage);
					if(aboutView.currentPage==1)
						textPage.setText(" "+temp+"/"+aboutView.pageMax+" ");
					else
						textPage.setText(temp+"/"+aboutView.pageMax);
				}
			}
		});
		btnRight.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				if(aboutView.currentPage < aboutView.pageMax){
					aboutView.currentPage++;
					String temp = String.valueOf(aboutView.currentPage);
					if(aboutView.currentPage==1)
						textPage.setText(" "+temp+"/"+aboutView.pageMax+" ");
					else
						textPage.setText(temp+"/"+aboutView.pageMax);
				}
			}
		});
		btnExit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				stageActivity.finish();
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
