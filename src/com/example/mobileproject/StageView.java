package com.example.mobileproject;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.FrameLayout;


public class StageView extends SurfaceView implements SurfaceHolder.Callback{

	int myDiplayWith,myDiplayHeght;
	float DiplayDiv = 160;
	float char_x = DiplayDiv,X=0;
	Paint paint = new Paint();
	
	StageViewThread myThread; 
	

	public StageView(Context context,int diplayWith, int diplayHeght) {
		super(context);
		
		myDiplayWith = diplayWith;
		myDiplayHeght = diplayHeght; 
		
		getHolder().addCallback(this);
		myThread = new StageViewThread(getHolder(),this);
	
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		
		myThread.setRunning(true);
		myThread.start();
	
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {

		boolean retry = true;
		myThread.setRunning(false);
		while(retry){
			try {
				myThread.join();
				retry = false;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			
		}
		
	}
	
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
	}

	
}
