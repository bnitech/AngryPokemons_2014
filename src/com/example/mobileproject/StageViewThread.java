package com.example.mobileproject;

import java.io.File;
import java.util.ArrayList;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.SurfaceHolder;

public class StageViewThread extends Thread {
	
	StageActivity stageActivity;

	Boolean myRun, myPaused = false;
	Boolean pokemonShot = false;
	Boolean balckhollLife = false;
	

	int myDiplayWith, myDiplayHeght;
	int backgroundX = 0, backgroundSpeed, backgroudCycle;
	int floor[], floorMax = 200;
	int blockFloorWith = 300, blockAirWith = 250, blockEmptyWith = 400;
	int blackhollWith=700,blackhollHeight=700;
	int blockMax = 20, monsterMax = 10, itemMax=1;
	int pokemonSkillMax = 30, pokemonSkillDelay = 10, pokemonSkillGauge = 1, tempGauge;
	int monterDelay;
	int jiuWith = 100, jiuHeight = 200;
	int rNum;
	int pokemonSelect = 0;
	int tempTime;
	int gameScore;

	RectF warning, HPItem ,jiu,blackholl;
	RectF pokemonHPBar;

	Paint paint = new Paint();
	Bitmap background, jiuBitmap, pikachuBitmap, pairyBitmap, ggobugiBitmap,
			blockFloorBitmap, blockAirBitmap, bossBitmap;
	Bitmap poketmonSkillBitmap[], monsterBitmap[], HPItemBitmap,blackhollBitmap;

	SurfaceHolder mySurfaceHolder;
	StageView myStageview;

	File bitmapfile;

	Pokemon pokemon;

	Boss boss;

	BitmapFactory.Options options;

	ArrayList<Blocks> blockFloor = new ArrayList<Blocks>();
	ArrayList<Blocks> blockAir = new ArrayList<Blocks>();
	ArrayList<Blocks> blockEmpty = new ArrayList<Blocks>();
	ArrayList<Monsters> monster = new ArrayList<Monsters>();
	ArrayList<PokemonSkill> pokemonSkill = new ArrayList<PokemonSkill>();
	ArrayList<Items> items = new ArrayList<Items>();

	
	public StageViewThread(SurfaceHolder surfaceHolder, StageView stageView) {
		
		stageActivity = (StageActivity) StageActivity.stageActivity;

		
		monterDelay = 250;

		mySurfaceHolder = surfaceHolder;
		myStageview = stageView;

		myDiplayWith = stageView.myDiplayWith;
		myDiplayHeght = stageView.myDiplayHeght;

		poketmonSkillBitmap = new Bitmap[3];
		monsterBitmap = new Bitmap[5];

		floor = new int[5];
		for (int i = 0; i < floor.length; i++) {
			floor[i] = myDiplayHeght - (i * floorMax);
		}

		warning = new RectF(50, 50, myDiplayWith - 50, myDiplayHeght - 50);
		jiu = new RectF();
		HPItem = new RectF();
		pokemonHPBar = new RectF();
		blackholl = new RectF();
		
		
		pokemon = new Pokemon(floor[4], floorMax, myDiplayWith, myDiplayHeght);
		blackholl.set(pokemon.Bounds);
		
		boss = new Boss(floor[0], myDiplayWith, myDiplayHeght);

		
		for (int i = 0; i < blockMax; i++) {
			blockFloor.add(new Blocks(blockFloorWith, 100, floorMax, myDiplayWith));
			blockAir.add(new Blocks(blockAirWith, floorMax / 5, floorMax, myDiplayWith));
			blockEmpty.add(new Blocks(blockEmptyWith, 50, floorMax, myDiplayWith));
		}
		for (int i = 0; i < monsterMax; i++) {
			monster.add(new Monsters(floorMax, myDiplayWith, myDiplayHeght));
		}
		for (int i = 0; i < pokemonSkillMax; i++) {
			pokemonSkill.add(new PokemonSkill(myDiplayWith, myDiplayHeght,myDiplayWith, myDiplayHeght));
		}
		for (int i = 0; i < itemMax; i++) {
			items.add(new Items(floorMax,myDiplayWith, myDiplayHeght));
		}

		backgroundSpeed = 25;
		backgroudCycle = 7500 / backgroundSpeed;

		background = BitmapFactory.decodeResource(myStageview.getResources(),R.drawable.background);
		background = Bitmap.createScaledBitmap(background, 10000,myDiplayHeght, true);

		blockFloorBitmap = BitmapFactory.decodeResource(myStageview.getResources(), R.drawable.blockfloor);
		blockFloorBitmap = Bitmap.createScaledBitmap(blockFloorBitmap,blockFloor.get(0).myWith + 10,blockFloor.get(0).myHeight, true);

		blockAirBitmap = BitmapFactory.decodeResource(myStageview.getResources(), R.drawable.blockair);
		blockAirBitmap = Bitmap.createScaledBitmap(blockAirBitmap,blockAir.get(0).myWith + 10, blockAir.get(0).myHeight, true);

		options = new BitmapFactory.Options();

		options.inPreferredConfig = Config.ARGB_8888;
		
		blackhollBitmap = BitmapFactory.decodeResource(myStageview.getResources(),R.drawable.blackholl, options);
		HPItemBitmap = BitmapFactory.decodeResource(myStageview.getResources(),R.drawable.hpitem, options);
		jiuBitmap = BitmapFactory.decodeResource(myStageview.getResources(),R.drawable.jiu, options);
		pikachuBitmap = BitmapFactory.decodeResource(myStageview.getResources(), R.drawable.pikachu, options);
		pairyBitmap = BitmapFactory.decodeResource(myStageview.getResources(),R.drawable.pairy, options);
		ggobugiBitmap = BitmapFactory.decodeResource(myStageview.getResources(), R.drawable.ggobugi, options);
		monsterBitmap[0] = BitmapFactory.decodeResource(myStageview.getResources(), R.drawable.monster0, options);
		monsterBitmap[1] = BitmapFactory.decodeResource(myStageview.getResources(), R.drawable.monster1, options);
		monsterBitmap[2] = BitmapFactory.decodeResource(myStageview.getResources(), R.drawable.monster2, options);
		monsterBitmap[3] = BitmapFactory.decodeResource(myStageview.getResources(), R.drawable.monster3, options);
		monsterBitmap[4] = BitmapFactory.decodeResource(myStageview.getResources(), R.drawable.monster1, options);
		poketmonSkillBitmap[0] = BitmapFactory.decodeResource(myStageview.getResources(), R.drawable.lightning, options);
		poketmonSkillBitmap[1] = BitmapFactory.decodeResource(myStageview.getResources(), R.drawable.fireball, options);
		poketmonSkillBitmap[2] = BitmapFactory.decodeResource(myStageview.getResources(), R.drawable.iceflower, options);

		bossBitmap = BitmapFactory.decodeResource(myStageview.getResources(),R.drawable.boss1, options);
		bossBitmap = Bitmap.createScaledBitmap(bossBitmap, boss.myWith,boss.myHeight, true);

		jiuBitmap = Bitmap.createScaledBitmap(jiuBitmap, jiuWith, jiuHeight,true);
		

	}

	public void setRunning(Boolean run) {
		myRun = run;
	}

	public void run() {

		Canvas canvas = null;
		int mainTime = 1,rotateTime=0;
		tempTime = 0;
		float degree=0,degreeGap=60;
		balckhollLife=true;

		blockFloor.get(0).setBounds(0, floor[0]);
		blockFloor.get(0).life = true;
		for (int i = 1; i < 10; i++) {
			blockFloor.get(i).setBounds(blockFloor.get(i - 1).Bounds.right,	floor[0]);
			blockFloor.get(i).life = true;
		}

		try {
			sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		while (myRun) {

			try {

				canvas = mySurfaceHolder.lockCanvas(null);

				synchronized (mySurfaceHolder) {
					mainTime++;
					tempTime++;

					// ////////////////////////////////////////////////////////////////////////////////////////////

					pokemon.move();

					for (int i = 0; i < blockMax; i++) {
						blockAir.get(i).move();
						blockFloor.get(i).move();
						blockEmpty.get(i).move();
					}

					for (int i = 0; i < monsterMax; i++) {
						monster.get(i).move();
					}
					for (int i = 0; i < pokemonSkillMax; i++) {
						pokemonSkill.get(i).move();
					}
					for (int i = 0; i < itemMax; i++) {
						items.get(i).move();
					}

					// /////////////////////////////////////////////////////////////////////////////////////////////////

					
					
					canvas.drawBitmap(background, backgroundX, 0, paint);
					
					String bossTime = String.valueOf(monterDelay/10-1);
					paint.setStyle(Paint.Style.FILL);
					paint.setColor(Color.BLACK); 
					paint.setTextSize(100);
					canvas.drawText("보스 출몰 : "+bossTime, myDiplayWith/2-10, 100, paint);
					
					
					
					
					for (int i = 0; i < blockMax; i++) {
						canvas.drawBitmap(blockFloorBitmap,	blockFloor.get(i).Bounds.left,blockFloor.get(i).Bounds.top, paint);
						canvas.drawBitmap(blockAirBitmap,blockAir.get(i).Bounds.left,blockAir.get(i).Bounds.top, paint);
					}

					

					for (int i = 0; i < monsterMax; i++) {
						monsterBitmap[monster.get(i).monsterSelect] 
								= Bitmap.createScaledBitmap(monsterBitmap[monster.get(i).monsterSelect],monster.get(i).myWith,monster.get(i).myHeight, true);
						canvas.drawBitmap(monsterBitmap[monster.get(i).monsterSelect],monster.get(i).Bounds.left,monster.get(i).Bounds.top - 50, paint);
					}

					
					if (monterDelay < 30) {
						boss.move();
						canvas.drawBitmap(bossBitmap, boss.Bounds.left,	boss.Bounds.top, paint);
					}

					
					
					paint.setColor(Color.rgb(255, 0, 0));
					paint.setStrokeWidth(20);
					canvas.drawLine(pokemon.Bounds.left,pokemon.Bounds.top - 20, pokemon.Bounds.left+ pokemon.HPBarWith,pokemon.Bounds.top - 20, paint);
					for (int i = 0; i < monsterMax; i++) {
						canvas.drawLine(monster.get(i).Bounds.left,	monster.get(i).Bounds.top - 80,
								monster.get(i).Bounds.left+ monster.get(i).HPBarWith,monster.get(i).Bounds.top - 80, paint);
					}
					paint.setColor(Color.rgb(200, 00, 150));
					paint.setStrokeWidth(50);
					canvas.drawLine(boss.Bounds.left, boss.Bounds.top - 50,boss.Bounds.left + boss.HPBarWith,boss.Bounds.top - 50, paint);
					
					for (int i = 0; i < itemMax; i++) {
						HPItemBitmap = Bitmap.createScaledBitmap(HPItemBitmap,items.get(i).myWith,items.get(i).myHeight,true);
						canvas.drawBitmap(HPItemBitmap, items.get(i).Bounds.left,items.get(i).Bounds.top, paint);
					}
					
					
					if(balckhollLife==true){
						
						if(blackhollWith > 0){
							rotateTime++;
							blackhollBitmap = Bitmap.createScaledBitmap(blackhollBitmap, blackhollWith, blackhollHeight, true);
							canvas.drawBitmap(blackhollBitmap, blackholl.left-100,blackholl.top-100, paint);
							blackhollWith-=5;
							blackhollHeight-=5;
						}else{
							rotateTime=0;
							blackhollBitmap = BitmapFactory.decodeResource(myStageview.getResources(),R.drawable.blackholl, options);
							blackhollWith=10;
							blackhollHeight=10;
							balckhollLife=false;
							
						}
					}else{
						if (boss.HP <=0) {
							
							if(blackhollWith < 700 ){
								blackhollWith+=5;
								blackhollHeight+=5;
							}
							blackhollBitmap = Bitmap.createScaledBitmap(blackhollBitmap, blackhollWith, blackhollHeight, true);
							canvas.drawBitmap(blackhollBitmap,myDiplayWith-500 ,floor[4], paint);
						}
					}
					
					
					for (int i = 0; i < pokemonSkillMax; i++) {
						poketmonSkillBitmap[pokemonSkill.get(i).pokemonSelect] 
								= Bitmap.createScaledBitmap(poketmonSkillBitmap[pokemonSkill.get(i).pokemonSelect],pokemonSkill.get(i).myWith,pokemonSkill.get(i).myHeight, true);
						canvas.drawBitmap(poketmonSkillBitmap[pokemonSkill.get(i).pokemonSelect],pokemonSkill.get(i).Bounds.left,pokemonSkill.get(i).Bounds.top, paint);
					}

					
					
					if (pokemon.Bounds.right > myDiplayWith -300) {
						rotateTime++;
						if(rotateTime>0 && rotateTime<100){
							degree+=degreeGap;
							degree%=360;
							canvas.rotate(degree,pokemon.Bounds.left+50,pokemon.Bounds.top+pokemon.myHeight/2);
							
							degreeGap+=1;
						}	
					}else{
						if(rotateTime>0 && rotateTime<50){
							degree+=degreeGap;
							degree%=360;
							canvas.rotate(degree,pokemon.Bounds.left+50,pokemon.Bounds.top+pokemon.myHeight/2);
							
							degreeGap-=1;
						}
					}
					
					canvas.drawBitmap(jiuBitmap, jiu.left, jiu.top, paint);
					
					
					
					
					pokemon.setChar(pokemonSelect);

					switch (pokemonSelect) {
					case 0:
						pikachuBitmap = Bitmap.createScaledBitmap(pikachuBitmap, pokemon.myWith,pokemon.myHeight, true);
						canvas.drawBitmap(pikachuBitmap, pokemon.Bounds.left,pokemon.Bounds.top, paint);
						break;
					case 1:
						pairyBitmap = Bitmap.createScaledBitmap(pairyBitmap,pokemon.myWith, pokemon.myHeight, true);
						canvas.drawBitmap(pairyBitmap, pokemon.Bounds.left,	pokemon.Bounds.top, paint);
						break;
					case 2:
						ggobugiBitmap = Bitmap.createScaledBitmap(ggobugiBitmap, pokemon.myWith,pokemon.myHeight, true);
						canvas.drawBitmap(ggobugiBitmap, pokemon.Bounds.left,pokemon.Bounds.top, paint);
						break;

					default:
						break;
					}

					

					// /////////////////////////////////////////////////////////////////////////////////////////////////////

					jiu.set(pokemon.Bounds.left - jiuWith - 10,	pokemon.Bounds.bottom - jiuHeight - 10,	pokemon.Bounds.left, pokemon.Bounds.bottom);

					if (pokemonShot == true) {
						for (int i = 0; i < pokemonSkillMax; i++) {
							int front = i - 1;
							if (front == -1) {
								front = pokemonSkillMax - 1;
							}
							if (pokemonSkill.get(i).life == 0) {
								 pokemonSkill.get(i).skillGauge = pokemonSkillGauge;
								pokemonSkill.get(i).setSkill(pokemonSelect);
								pokemonSkill.get(i).setBounds(pokemon.Bounds.right,	pokemon.Bounds.bottom - 100);
								break;
							}
							pokemonSkillGauge = 1;
						}

						pokemonShot = false;
					}

					if (mainTime % 600 == 0) {
						for (int i = 0; i < itemMax; i++) {
							rNum = (int) (Math.random() * 4) + 1;
							
							if (items.get(i).life == false) {
								items.get(i).setChar(0);
								items.get(i).setBounds(myDiplayWith,floor[rNum]);
								items.get(i).life = true;
								break;
							}
							
						}
					}
					
					if (mainTime % 23 == 0) {
						for (int i = 0; i < blockMax; i++) {
							int front = i - 1;
							rNum = (int) (Math.random() * 4) + 1;
							if (front == -1) {
								front = blockMax - 1;
							}
							if (blockAir.get(i).life == false) {
								if (rNum < 4) {
									blockAir.get(i).setBounds(myDiplayWith,	floor[rNum]);
									blockAir.get(i).life = true;
								}
								break;
							}
						}
					}
					if (monterDelay > 10) {
						if (mainTime % monterDelay == 0) {
							for (int i = 0; i < monsterMax; i++) {
								int front = i - 1;
								if (front == -1) {
									front = monsterMax - 1;
								}

								if (monster.get(i).life == false) {
									rNum = (int) (Math.random() * 4);
									monster.get(i).setChar(rNum);

									rNum = (int) (Math.random() * 3) + 1;
									monster.get(i).setBounds(myDiplayWith,
											floor[rNum]);
									monster.get(i).life = true;

									monterDelay -= 10;

									if (monterDelay < 30) {
										boss.life = true;
									}
									break;
								}
							}
						}
					}
					for (int i = 0; i < blockMax; i++) {
						int front = i - 1;
						if (front == -1) {
							front = blockMax - 1;
						}
						rNum = (int) (Math.random() * 10);
						if (blockFloor.get(i).life == false) {
							if (rNum == 0) {
								blockEmpty.get(i).setBounds(blockFloor.get(front).Bounds.right,	floor[0]);
								blockEmpty.get(i).life = true;
								blockFloor.get(i).setBounds(blockEmpty.get(i).Bounds.right,	floor[0]);
								blockFloor.get(i).life = true;
							} else {
								blockFloor.get(i).setBounds(blockFloor.get(front).Bounds.right,	floor[0]);
								blockFloor.get(i).life = true;
							}
						}

					}

					for (int i = 0; i < blockMax; i++) {
						if (pokemon.Bounds.right > blockAir.get(i).Bounds.left
								&& pokemon.Bounds.left < blockAir.get(i).Bounds.right- pokemon.myWith / 2
								&& pokemon.Bounds.bottom > blockAir.get(i).Bounds.top
								&& pokemon.Bounds.bottom < blockAir.get(i).Bounds.bottom) {
							pokemon.myY -= 15;
							pokemon.fall = false;
							break;
						}
					}
					for (int i = 0; i < blockMax; i++) {
						if (pokemon.Bounds.right > blockFloor.get(i).Bounds.left
								&& pokemon.Bounds.left < blockFloor.get(i).Bounds.right	- pokemon.myWith / 2
								&& pokemon.Bounds.bottom > blockFloor.get(i).Bounds.top
								&& pokemon.Bounds.bottom < blockFloor.get(i).Bounds.bottom) {
							pokemon.myY -= 15;
							pokemon.fall = false;
							break;
						}
					}

					for (int j = 0; j < pokemonSkillMax; j++) {
						for (int i = 0; i < blockMax; i++) {
							if (pokemonSkill.get(j).Bounds.right > blockAir	.get(i).Bounds.left
									&& pokemonSkill.get(j).Bounds.left < blockAir.get(i).Bounds.right- pokemon.myWith / 2
									&& pokemonSkill.get(j).Bounds.bottom > blockAir.get(i).Bounds.top
									&& pokemonSkill.get(j).Bounds.bottom < blockAir.get(i).Bounds.bottom) {
								pokemonSkill.get(j).jump = true;
								break;
							}
						}
					}

					for (int j = 0; j < pokemonSkillMax; j++) {
						for (int i = 0; i < blockMax; i++) {
							if (pokemonSkill.get(j).Bounds.right > blockFloor.get(i).Bounds.left
									&& pokemonSkill.get(j).Bounds.left < blockFloor.get(i).Bounds.right- pokemon.myWith / 2
									&& pokemonSkill.get(j).Bounds.bottom > blockFloor.get(i).Bounds.top
									&& pokemonSkill.get(j).Bounds.bottom < blockFloor.get(i).Bounds.bottom) {
								pokemonSkill.get(j).jump = true;
								break;
							}
						}
					}

					for (int j = 0; j < pokemonSkillMax; j++) {
						for (int i = 0; i < monsterMax; i++) {
							if (pokemonSkill.get(j).Bounds.right > monster.get(i).Bounds.left
									&& pokemonSkill.get(j).Bounds.left < monster.get(i).Bounds.right
									&& pokemonSkill.get(j).Bounds.bottom > monster.get(i).Bounds.top
									&& pokemonSkill.get(j).Bounds.top < monster.get(i).Bounds.bottom) {

								monster.get(i).setDamage(pokemonSkill.get(j).damage);
								
								if (pokemonSkill.get(j).pokemonSelect == 1) {
									pokemonSkill.get(j).life--;
								}
								if (pokemonSkill.get(j).pokemonSelect == 2) {
									pokemonSkill.get(j).myX -= 100;
									monster.get(i).myX+=20;
									pokemonSkill.get(j).life--;
								}

							}
						}
					}

					for (int j = 0; j < pokemonSkillMax; j++) {
						if (pokemonSkill.get(j).Bounds.right > boss.Bounds.left
								&& pokemonSkill.get(j).Bounds.left < boss.Bounds.right
								&& pokemonSkill.get(j).Bounds.bottom > boss.Bounds.top
								&& pokemonSkill.get(j).Bounds.bottom < boss.Bounds.bottom) {

							boss.setDamage(pokemonSkill.get(j).damage);
							if (pokemonSkill.get(j).pokemonSelect == 1) {
								pokemonSkill.get(j).life--;
							}
							if (pokemonSkill.get(j).pokemonSelect == 2) {
								pokemonSkill.get(j).myX -= 100;
								pokemonSkill.get(j).life--;
								boss.myX+=3;
							}
							break;
						}
					}

					if (pokemon.Bounds.right > boss.Bounds.left + 50) {
						pokemon.HP -= boss.power;
						boss.myX += 200;
					}

					for (int i = 0; i < itemMax; i++) {
						if (pokemon.Bounds.right > items.get(i).Bounds.left
								&& pokemon.Bounds.left < items.get(i).Bounds.right
								&& pokemon.Bounds.bottom > items.get(i).Bounds.top
								&& pokemon.Bounds.top < items.get(i).Bounds.bottom) {
							
							if((pokemon.HP + items.get(i).HP)>pokemon.HPMax){
								pokemon.HP = pokemon.HPMax;
							}else{
								pokemon.HP += items.get(i).HP;
							}
							
							items.get(i).life=false;
						}
					}
					
					for (int i = 0; i < monsterMax; i++) {
						if (pokemon.Bounds.right > monster.get(i).Bounds.left
								&& pokemon.Bounds.left < monster.get(i).Bounds.right
								&& pokemon.Bounds.bottom > monster.get(i).Bounds.top
								&& pokemon.Bounds.top < monster.get(i).Bounds.bottom) {

							pokemon.HP -= monster.get(i).power;
							monster.get(i).life = false;

							paint.setColor(Color.RED);
							paint.setStyle(Paint.Style.STROKE);
							paint.setStrokeWidth(200);
							canvas.drawRect(warning, paint);

							try {
								sleep(50);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

							paint.setColor(Color.RED);
							paint.setStyle(Paint.Style.STROKE);
							paint.setStrokeWidth(100);
							canvas.drawRect(warning, paint);

							break;
						}

						if (monster.get(i).myX < -10) {
							pokemon.HP -= monster.get(i).power;
							monster.get(i).life = false;

							paint.setColor(Color.RED);
							paint.setStyle(Paint.Style.STROKE);
							paint.setStrokeWidth(200);
							canvas.drawRect(warning, paint);

							try {
								sleep(50);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

							paint.setColor(Color.RED);
							paint.setStyle(Paint.Style.STROKE);
							paint.setStrokeWidth(100);
							canvas.drawRect(warning, paint);
						}
					}

					// ///////////////////////////////////////////////////////////////////////////////////////////////

					if (boss.HP > 0) {
						backgroundX -= backgroundSpeed;
					} else {
						
						pokemon.myX += 5;
						for (int j = 0; j < blockMax; j++) {
							blockEmpty.get(j).myWith=1;
						}
						blockEmptyWith = 1;
						if (pokemon.Bounds.right > myDiplayWith) {
							setRunning(false);
							stageActivity.finish();
						}
					}

					if (mainTime % backgroudCycle == 0) {
						backgroundX = 0;
					}
					if (pokemon.HP < 0) {
						setRunning(false);
						stageActivity.finish();
					}
					if (pokemon.myY > myDiplayHeght + 500) {
						setRunning(false);
						stageActivity.finish();

					}
					if (mainTime == 1000000)
						mainTime = 1;

					while (myPaused) {
						try {
							mySurfaceHolder.wait();
						} catch (InterruptedException e) {
						}
					}

				}
			} finally {
				if (canvas != null)
					mySurfaceHolder.unlockCanvasAndPost(canvas);
			}

		}

		super.run();
	}

	public void onPause() {
		synchronized (mySurfaceHolder) {
			myPaused = true;
		}
	}

	public void onResume() {
		synchronized (mySurfaceHolder) {
			myPaused = false;
			mySurfaceHolder.notifyAll();
		}
	}

}
