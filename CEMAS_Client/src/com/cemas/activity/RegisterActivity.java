package com.cemas.activity;

import com.cemas.R;
import com.cemas.bean.User;
import com.cemas.service.UserService;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends Activity {

	EditText editText_uID;
	EditText editText_uPassword;
	EditText editText_uName;
	EditText editText_uAge;
	EditText editText_uGender;
	EditText editText_uSchoolNumber;
	EditText editText_uEmail;
	EditText editText_uIdentify;
	EditText editText_uTele;

	User bean;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
		// 按钮初始化
		Button returnBtn = (Button) findViewById(R.id.btn_return);
		Button registerBtn = (Button) findViewById(R.id.btn_register);
		// 文本框初始化
		editText_uID = (EditText) findViewById(R.id.editText_uID);
		editText_uPassword = (EditText) findViewById(R.id.editText_uPassword);
		editText_uName = (EditText) findViewById(R.id.editText_uName);
		editText_uAge = (EditText) findViewById(R.id.editText_uAge);
		editText_uGender = (EditText) findViewById(R.id.editText_uGender);
		editText_uSchoolNumber = (EditText) findViewById(R.id.editText_uSchoolNumber);
		editText_uEmail = (EditText) findViewById(R.id.editText_uEmail);
		editText_uIdentify = (EditText) findViewById(R.id.editText_uIdentify);
		editText_uTele = (EditText) findViewById(R.id.editText_uTele);

		bean = new User();

		returnBtn.setOnClickListener(new OnClickListener() {
			// 返回按钮点击事件
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(RegisterActivity.this,
						UserLoginActivity.class);
				startActivity(intent);
			}
		});

		registerBtn.setOnClickListener(new OnClickListener() {
			// 注册按钮点击事件
			@Override
			public void onClick(View v) {
				if (RegisterActivity.this.editText_uID.getText().toString()
						.trim() == ""
						|| RegisterActivity.this.editText_uPassword.getText()
								.toString().trim() == ""
						|| RegisterActivity.this.editText_uSchoolNumber
								.getText().toString().trim() == ""
						|| RegisterActivity.this.editText_uTele.getText()
								.toString().trim() == "") {
					Toast.makeText(RegisterActivity.this, "请填写必填项！！！",
							Toast.LENGTH_LONG).show();
				} else {
					bean.setuAge(RegisterActivity.this.editText_uAge.getText()
							.toString().trim());
					bean.setuEmail(RegisterActivity.this.editText_uEmail
							.getText().toString().trim());
					bean.setuGender(RegisterActivity.this.editText_uGender
							.getText().toString().trim());
					bean.setuID(RegisterActivity.this.editText_uID.getText()
							.toString().trim());
					bean.setuIdentify(RegisterActivity.this.editText_uIdentify
							.getText().toString().trim());

					bean.setuName(RegisterActivity.this.editText_uName
							.getText().toString().trim());
					bean.setuPassword(RegisterActivity.this.editText_uPassword
							.getText().toString().trim());
					bean.setuSchoolNumber(RegisterActivity.this.editText_uSchoolNumber
							.getText().toString().trim());
					bean.setuScore("0");
					bean.setuTele(RegisterActivity.this.editText_uTele
							.getText().toString().trim());
				}
				executeRegister();
			}
		});

	}

	private void executeRegister() {
		new RegisterTask().execute();
	}

	private class RegisterTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		@Override
		protected Void doInBackground(Void... arg0) {
			// TODO Auto-generated method stub

			UserService.insertUser(bean);

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			// 回调
			onRegisterComplete();
		}
	}

	private void onRegisterComplete() {
		Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT)
				.show();

		Intent intent = new Intent(RegisterActivity.this,
				UserLoginActivity.class);
		startActivity(intent);
	}
}
