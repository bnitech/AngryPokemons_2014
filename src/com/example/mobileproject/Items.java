package com.example.mobileproject;

import android.graphics.RectF;
import android.util.Log;

public class Items {

	int myFloor=0,floorMax,myWith,myHeight,displayWith,displayHeight;
	int speed,itemSelect=0;
	int HP=0,moveTime=0;
	float myX,myY;
	boolean life = false;
	
	RectF Bounds = new RectF();
	
	public Items(int floorMax, int displayWith, int displayHeight) {

		this.floorMax = floorMax-10;
		
		this.displayWith = displayWith;
		this.displayHeight = displayHeight;
		
		
		setChar(0);
	}
	public void move(){
		
		if(life==true){
			wave();
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
	
	public void wave(){
		moveTime++;
		if(moveTime>=0 && moveTime<=20){
			myX-=15;
			myY-=15;
		}else if(moveTime>20 && moveTime<=40){
			myX-=15;
			myY+=15;
		}else if(moveTime>40){
			moveTime=0;
		}
		
	}
	
	public void setBounds(float x, float y){
		myX = x;
		myY = y;
		Bounds.set(myX+20,myY-myHeight,myX+myWith,myY-50);
		
	}
	
	public void setChar(int select){
		itemSelect = select;
		switch (itemSelect) {
		case 0:
			myWith=70;
			myHeight=120;
			HP=1000;
			break;
		default:
			break;
		}
	}
}
