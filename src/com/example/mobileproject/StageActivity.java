package com.example.mobileproject;

import java.util.EventListener;

import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.media.MediaDrm.OnEventListener;
import android.net.wifi.p2p.WifiP2pManager.ActionListener;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Event;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

public class StageActivity extends ActionBarActivity implements EventListener {

	public static Activity stageActivity;

	// 1920 X 1080

	MediaPlayer bgm;
	
	FrameLayout frame;
	StageView view;
	Button btnJump, btnAttack, btnChange;
	Drawable drawable;
	AlertDialog.Builder dialogBuilder;
	AlertDialog alert;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		stageActivity = StageActivity.this;
		
		bgm = MediaPlayer.create(this, R.raw.battle);
		bgm.setVolume(0.2f, 0.2f);
		bgm.setLooping(true);
		bgm.start();
		
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		setTheme(android.R.style.Theme_NoTitleBar_Fullscreen);

		setContentView(R.layout.activity_stage);

		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);

		int pxWith = metrics.widthPixels;
		int pxHeight = metrics.heightPixels;

		frame = (FrameLayout) findViewById(R.id.stageFrame);
		view = new StageView(this, pxWith, pxHeight);
		frame.addView(view);

		
		btnJump = (Button) findViewById(R.id.btnJump);
		btnAttack = (Button) findViewById(R.id.btnAttack);
		btnChange = (Button) findViewById(R.id.btnChange);

		drawable = ((Button) findViewById(R.id.btnJump)).getBackground();
		drawable.setAlpha(70);
		drawable = ((Button) findViewById(R.id.btnAttack)).getBackground();
		drawable.setAlpha(70);
		drawable = ((Button) findViewById(R.id.btnChange)).getBackground();
		drawable.setAlpha(70);

		dialogBuilder = new AlertDialog.Builder(this);
		dialogBuilder
				.setMessage("게임을 종료하시겠습니까?")
				.setCancelable(false)
				.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						System.exit(0);
						dialog.cancel();
					}
				})
				.setNegativeButton("No", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						view.myThread.onResume();
						dialog.cancel();
					}
				});
		
		alert = dialogBuilder.create();
		alert.setTitle("Angry Pokemons");
		alert.setIcon(R.drawable.ic_launcher);
		

		btnJump.setOnTouchListener(new OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {

				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					if (view.myThread.pokemon.fall == false) {
						if (view.myThread.pokemon.jump == false
								&& view.myThread.pokemon.doubleJump == false) {
							view.myThread.pokemon.jump = true;
						}

					}
					if (view.myThread.pokemon.jump == true
							&& view.myThread.pokemon.doubleJump == false) {
						if (view.myThread.pokemon.jumpTime > 10)

							view.myThread.pokemon.doubleJump = true;
					}
				}
				if (event.getAction() == MotionEvent.ACTION_UP) {

				}
				return false;
			}
		});

		btnAttack.setOnTouchListener(new OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {

				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					if (view.myThread.tempTime > view.myThread.pokemonSkillDelay) {
						if (view.myThread.pokemonShot == false) {
							view.myThread.pokemonShot = true;
							view.myThread.tempTime = 0;
						}
					}
				}
				if (event.getAction() == MotionEvent.ACTION_UP) {
					
				}
				return false;
			}
		});

		btnChange.setOnTouchListener(new OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {

				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					view.myThread.pokemonSelect++;
					if (view.myThread.pokemonSelect == 3)
						view.myThread.pokemonSelect = 0;
				}
				if (event.getAction() == MotionEvent.ACTION_UP) {

				}
				return false;
			}
		});

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {

		case KeyEvent.KEYCODE_BACK:
			if(view.myThread.myPaused==false){
				alert.show();
				view.myThread.onPause();
			}
			return true;
		case KeyEvent.KEYCODE_MENU:
			return true;

		}
		return super.onKeyDown(keyCode, event);

	}

	@Override
	protected void onDestroy() {

		bgm.stop();
		super.onDestroy();
	}

}
