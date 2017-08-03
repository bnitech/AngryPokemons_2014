package com.example.mobileproject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.media.AudioManager;
import android.media.SoundPool;
import android.view.View;

public class MainView extends View{

	int myDisplayWith, myDisplayHeight;
	
	Bitmap backgroundBitmap;
	Paint paint;
	
	public MainView(Context context, int displayWith, int displayHeight) {
		super(context);
		
		paint = new Paint();
		
		myDisplayWith = displayWith;
		myDisplayHeight = displayHeight;
		
		
		backgroundBitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.mainbackground);
		backgroundBitmap = Bitmap.createScaledBitmap(backgroundBitmap, myDisplayWith, myDisplayHeight, true);
		
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawBitmap(backgroundBitmap, 0, 0,paint);
		
		
		super.onDraw(canvas);
	}
}
