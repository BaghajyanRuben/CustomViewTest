package com.photo.customviewproject;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

import java.time.format.TextStyle;

public class SpeedometerView extends View {
	private final int MAX_VALUE = 120;

	private int maxValue = MAX_VALUE;
	private int minValue;
	private int height;
	private int wight;
	private Paint displayPaint;
	private Paint arrowPaint;
	private Paint textPaint;
	private int currentSpeed;

	public SpeedometerView(Context context) {
		super(context);
		init(null);
	}

	public SpeedometerView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(attrs);
	}

	public SpeedometerView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init(attrs);
	}

	public SpeedometerView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
		init(attrs);
	}

	private void init(AttributeSet attrs){

		displayPaint = new Paint();
		arrowPaint = new Paint();
		textPaint = new Paint();

		textPaint.setColor(Color.WHITE);
		textPaint.setTextSize(36);

		if (attrs == null){
			displayPaint.setColor(Color.GRAY);
			arrowPaint.setColor(Color.WHITE);
			return;
		}

		TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.SpeedometerView);

		int displayColor = typedArray.getColor(R.styleable.SpeedometerView_display_color, Color.GRAY);
		int arrowColor = typedArray.getColor(R.styleable.SpeedometerView_arrow_color, Color.WHITE);
		minValue = typedArray.getInt(R.styleable.SpeedometerView_min_value, 0);
		maxValue = typedArray.getInt(R.styleable.SpeedometerView_max_value, MAX_VALUE);

		displayPaint.setColor(displayColor);
		arrowPaint.setColor(arrowColor);
		typedArray.recycle();
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		height = getMeasuredHeight();
		wight = getMeasuredWidth();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		canvas.drawArc(0, 0, wight, height, 180, 180, true, displayPaint);
		canvas.drawArc(0 + wight/10,
				0 + wight/10,
				wight - wight/10,
				height - wight/10,
				180,
				currentSpeed,
				true,
				arrowPaint);
		canvas.drawText("max " + maxValue, wight/2, wight/10, textPaint);
	}

	public void increment(){
		if (currentSpeed >= maxValue){
			return;
		}
		currentSpeed += 10;
		invalidate();
	}

	public void decrement(){
		if (currentSpeed <= minValue){
			return;
		}
		currentSpeed -= 10;
		invalidate();
	}
}
