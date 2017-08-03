package com.example.mobileproject;

import android.graphics.RectF;
import android.util.Log;

public class Monsters {

	int myFloor=0,floorMax,myWith,myHeight,displayWith,displayHeight;
	int speed,monsterSelect=0;
	int HP=200,power=1,HP_1per=1,HP_nper=1,myWith_1per=1,HPBarWith=1;
	float myX,myY;
	boolean life = false;
	
	RectF Bounds = new RectF();
	
	public Monsters(int floorMax, int displayWith, int displayHeight) {

		this.floorMax = floorMax-10;
		
		this.displayWith = displayWith;
		this.displayHeight = displayHeight;
		
		
		setChar(0);
		HP_nper = HP/HP_1per;
		HPBarWith = myWith_1per * HP_nper;
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
		if(HP <= 0 ){
			this.life=false;
			myX=displayWith+50;
			HP=200;
		}
		
		setBounds(myX,myY);
		if(HP_1per!=0){
			HP_nper = HP/HP_1per;
		}
		HPBarWith = myWith_1per * HP_nper;
	}
	
	public void setBounds(float x, float y){
		myX = x;
		myY = y;
		Bounds.set(myX+20,myY-myHeight,myX+myWith,myY-50);
		
	}
	
	public void setChar(int select){
		monsterSelect = select;
		switch (monsterSelect) {
		case 0:
			myWith=350;
			myHeight=200;
			HP=600;
			power=500;
			speed=2;
			break;
		case 1:
			myWith=100;
			myHeight=100;
			HP=50;
			power=200;
			speed=20;
			break;
		case 2:
			myWith=200;
			myHeight=150;
			HP=350;
			power=400;
			speed=5;
			break;
		case 3:
			myWith=250;
			myHeight=150;
			HP=160;
			power=300;
			speed=15;
			break;
		case 4:
			myWith=100;
			myHeight=100;
			HP=50;
			power=200;
			speed=20;
			break;
		default:
			break;
		}
		HP_1per = HP/100;
		myWith_1per = myWith/100;
	}
	
	public void setDamage(int damage){
		HP-=damage;
	}
}
