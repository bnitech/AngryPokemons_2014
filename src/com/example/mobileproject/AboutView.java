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


public class AboutView extends View{

	int myDisplayWith,myDisplayHeght;
	int currentPage=0,pageMax,pageMin;
 	
	Paint paint = new Paint();
	
	StageViewThread myThread; 
	
	RectF background;
	Bitmap aboutBitmap[];
	
	
	public AboutView(Context context,int displayWith, int displayHeght) {
		super(context);
		
		myDisplayWith = displayWith;
		myDisplayHeght = displayHeght; 
		
		aboutBitmap = new Bitmap[5];
		pageMin =0;
		pageMax =5-1;
		
		background = new RectF(0,0,myDisplayWith,myDisplayHeght);
		
		aboutBitmap[0] = BitmapFactory.decodeResource(this.getResources(), R.drawable.about0);
		aboutBitmap[1] = BitmapFactory.decodeResource(this.getResources(), R.drawable.about1);
		aboutBitmap[2] = BitmapFactory.decodeResource(this.getResources(), R.drawable.about2);
		aboutBitmap[3] = BitmapFactory.decodeResource(this.getResources(), R.drawable.about3);
		aboutBitmap[4] = BitmapFactory.decodeResource(this.getResources(), R.drawable.about4);
		
		for (int i = 0; i < aboutBitmap.length; i++) {
			aboutBitmap[i] = Bitmap.createScaledBitmap(aboutBitmap[i], myDisplayWith-300, myDisplayHeght-100, true);
		}
		
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		
		
		paint.setColor(Color.WHITE);
		canvas.drawRect(background, paint);
		canvas.drawBitmap(aboutBitmap[currentPage],150,100, paint);
		
		
		
		invalidate();
		
		super.onDraw(canvas);
	}

}