package com.example.mobileproject;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;


public class StartView extends View{

	int myDisplayWith,myDisplayHeght;
	Paint paint = new Paint();
	
	StageViewThread myThread; 
	
	RectF background;
	Bitmap backgroundBitmap;
	
	
	public StartView(Context context,int displayWith, int displayHeght) {
		super(context);
		
		myDisplayWith = displayWith;
		myDisplayHeght = displayHeght; 
		
		background = new RectF(0,0,myDisplayWith,myDisplayHeght);
		
		backgroundBitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.startbackground);
		backgroundBitmap = Bitmap.createScaledBitmap(backgroundBitmap, myDisplayWith, myDisplayHeght, true);
		
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		
		
		paint.setColor(Color.WHITE);
		canvas.drawBitmap(backgroundBitmap,0,0, paint);
		
		
		
		invalidate();
		
		super.onDraw(canvas);
	}

}