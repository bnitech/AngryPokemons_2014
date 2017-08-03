package com.example.mobileproject;

import android.graphics.RectF;
import android.util.Log;

public class Pokemon{

	int myFloor,floorMax,myWith,myHeight,displayWith,displayHeight;
	int mainTime=0,jumpTime=0,doubleJumpTime=0;
	int HP,HPMax,HP_1per,HP_nper,myWith_1per,HPBarWith;
	float myX,myY;
	boolean jump=false,doubleJump=false,fall=false;
	
	
	RectF Bounds = new RectF();
	
	public Pokemon(int floor,int floorMax, int displayWith, int displayHeight) {
		
		this.floorMax = floorMax;

		this.displayWith = displayWith;
		this.displayHeight = displayHeight;
		
		myFloor = floor;
		
		myX = this.floorMax;
		myY = myFloor;
		HPMax=2000;
		HP=HPMax;
		
		HP_1per = HP/100;
		myWith_1per = floorMax/100;
		
		setChar(0);
		
		setBounds(myX,myFloor);
	}
	public void move(){
		mainTime++;
		
		if(Bounds.top < -15){
			myY+=15;
		}
		
		if(jump==true){
			jump();
		}else{
			if(doubleJump==true){
				doubleJump();
			}else{
				myY+=15;
			}
		}
		setBounds(myX,myY);
		
		HP_nper = HP/HP_1per;
		HPBarWith = myWith_1per * HP_nper;
	}
	public void jump(){
		jumpTime++;
		if(jumpTime>=0 && jumpTime<=13){
			myY-=20;
		}else if(jumpTime>13 && jumpTime<=25){
			if(doubleJump==true && doubleJumpTime==0){
				jump=false;
				return;
			}
			myY+=15;
		}else if(jumpTime>25){
			doubleJumpTime=0;
			jumpTime=0;
			doubleJump=false;
			jump=false;
			fall=true;
		}
		
	}
	
	public void doubleJump(){
		doubleJumpTime++;
		if(doubleJumpTime>=0 && doubleJumpTime<20)
			myY-=10;
		else if(doubleJumpTime>20 && doubleJumpTime<30){
			myY+=15;
		}
		else if(doubleJumpTime>=30){
			jump=true;
			
		}
		setBounds(myX,myY);
	}
	
	public void setBounds(float x, float y){
		myX = x;
		myY = y;
		Bounds.set(myX,myY-myHeight-10,myX+myWith,myY);
		
	}
	public void setChar(int select){
		switch (select) {
		case 0:
			myWith = floorMax;
			myHeight = floorMax;
			break;
		case 1:
			myWith = floorMax-20;
			myHeight = floorMax;
			break;
		case 2:
			myWith = floorMax;
			myHeight = floorMax-20;
			break;
		default:
			break;
		}
//		myWith_1per = myWith/100;
	}
}
