package com.example.mobileproject;

import android.graphics.RectF;
import android.util.Log;

public class Blocks {

	int myFloor=0,floorMax,myWith,myHeight,displayWith,displayHeight;
	int speed;
	float myX,myY;
	boolean life = false;
	
	
	
	RectF Bounds = new RectF();
	
	public Blocks(int blockwith, int blockHeight, int floorMax, int displayWith) {

		this.floorMax = floorMax-10;
		
		this.displayWith = displayWith;
				
		myWith=blockwith;
		myHeight=blockHeight;
		
		speed=10;
		
	}
	
	public void move(){
		if(life==true){
			myX-=speed;
		}else{
			myX=displayWith+50;
			myY=0;
		}
		if(Bounds.right < 0){
			this.life=false;
			myX=displayWith+50;
		}
		setBounds(myX,myY);

	}
	
	public void setBounds(float x, float y){
		myX = x;
		myY = y;
		Bounds.set(myX,myY-myHeight,myX+myWith,myY);
		
	}

}
