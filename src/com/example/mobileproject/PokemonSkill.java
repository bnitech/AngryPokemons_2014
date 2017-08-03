package com.example.mobileproject;

import android.graphics.RectF;
import android.util.Log;

public class PokemonSkill{

	int myWith,myHeight,displayWith,displayHeight;
	int mainTime=0,jumpTime=0,doubleJumpTime=0;
	int pokemonSelect=0,skillGauge=1;
	int life=0,damage=10;
	float myX,myY;
	
	boolean jump=false;
	
	
	RectF Bounds = new RectF();
	
	public PokemonSkill(int x,int y,int displayWith,int displayHeight) {
		
		this.displayWith = displayWith;
		this.displayHeight = displayHeight;
		
		myWith=100;
		myHeight=100;
		
		myX = x;
		myY = y;
		setSkill(0);
		setBounds(myX,myY);
	}
	public void move(){
		if(life>0){
			switch (pokemonSelect) {
			case 0:
				wave();
				if(myY>displayHeight+100 || myX > displayWith+100 ){
					life=0;
				}
				break;
			case 1:
				if(jump==true){
					jump();
				}else{
					myX+=10;
					myY+=10;
					if(myY>displayHeight+100 || myX > displayWith+100 ){
						life=0;
					}
				}
				break;
			case 2:
				myX+=10;
				if(myY>displayHeight+100 || myX > displayWith+100 ){
					life=0;
				}
				break;
			default:
				break;
			}
			
			
		}else{
			myX=displayWith+50;
			myY=displayHeight;
		}
		setBounds(myX,myY);
	}
	public void jump(){
		jumpTime++;
		if(jumpTime>=0 && jumpTime<=20){
			myX+=10;
			myY-=10;
		}else if(jumpTime>20 && jumpTime<=40){
			myX+=10;
			myY+=10;
		}else if(jumpTime>40){
			jumpTime=0;
			jump=false;
		}
		
	}
	public void wave(){
		jumpTime++;
		if(jumpTime>=0 && jumpTime<=10){
			myX+=10;
			myY-=10;
		}else if(jumpTime>10 && jumpTime<=20){
			myX+=10;
			myY+=10;
		}else if(jumpTime>20){
			jumpTime=0;
		}
		
	}
	
	
	public void setBounds(float x, float y){
		myX = x;
		myY = y;
		Bounds.set(myX,myY-myHeight,myX+myWith,myY);
	}
	
	public void setSkill(int select){
		pokemonSelect = select;
		switch (pokemonSelect) {
		case 0:
			life = 1;
			myWith = 200;
			myHeight = 100;
			damage=1;
			break;
		case 1:
			life = 1;
			myWith = 150;
			myHeight = 100;
			damage=70;
			break;
		case 2:
			life = 10;
			myWith = 100;
			myHeight = 70;
			damage= 4;
			break;
		default:
			break;
		}
		
	}
}
