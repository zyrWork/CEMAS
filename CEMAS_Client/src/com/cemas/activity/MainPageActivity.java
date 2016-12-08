package com.cemas.activity;


import com.cemas.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

@SuppressLint("NewApi")
public class MainPageActivity extends FragmentActivity {

	public static void actionStart(Context context, String uID) {
		Intent intent = new Intent(context, MainPageActivity.class);
		intent.putExtra("uID", uID);

		context.startActivity(intent);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_page);
	}

}
