package com.photo.customviewproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

	private SpeedometerView speedometer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		speedometer = findViewById(R.id.speed);

		findViewById(R.id.btn_minus).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				speedometer.decrement();
			}
		});

		findViewById(R.id.btn_plus).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				speedometer.increment();
			}
		});
	}
}
