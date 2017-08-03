package com.example.mobileproject;

import android.graphics.RectF;
import android.util.Log;

public class Boss {

	int myFloor=0,floorMax,myWith,myHeight,displayWith,displayHeight;
	int speed,monsterSelect=0;
	int HP,HP_1per=1,HP_nper=1,myWith_1per=1,HPBarWith=1;
	int sumDamage=0,power;
	float myX,myY;
	boolean life = false;
	
	RectF Bounds = new RectF();
	
	public Boss(int floor, int displayWith, int displayHeight) {

		
		this.displayWith = displayWith;
		this.displayHeight = displayHeight;
		
		myX = displayWith+50;
		myY = floor;
		
		myWith = (displayWith/5)*2;
		myHeight = (displayHeight/5)*4 ;
		HP= 15000;
		speed = 3;
		power = 500;
		
		HP_1per = HP/100;
		myWith_1per = myWith/100;
		
		
		HP_nper = HP/HP_1per;
		HPBarWith = myWith_1per * HP_nper;
		
		setBounds(myX,myY);
	}
	public void move(){
		if(life==true){
			myX-=speed;
		}else{
			myX=displayWith+50;
			myY=0;
		}
		
		if(HP <= 0 ){
			this.life=false;
			myX=displayWith+50;
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
	
	
	public void setDamage(int damage){
		sumDamage += damage;
		HP-=damage;
		myX+=2;
		if(sumDamage>500 && Bounds.right < displayWith){
			myX+=200;
			sumDamage=0;
		}
	}
}
