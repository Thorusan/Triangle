package com.example.si01016726.triangle;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class CanvasView extends View {
	private Path mPath;
	Context context;
	private Paint mPaint;
	private Paint emphasizePaint;
	private float mX, mY;
	private static final float TOLERANCE = 5;
	private Point pointA;
	private Point pointB;
	private Point pointC;
	private int emphasize = -1;

	public CanvasView(Context c, AttributeSet attrs) {
		super(c, attrs);
		context = c;

		// we set a new Path
		mPath = new Path();

        // and we set a new Paint with the desired attributes
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeWidth(4f);

		emphasizePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		emphasizePaint.setColor(ContextCompat.getColor(getContext(), R.color.colorAccent));
		emphasizePaint.setStyle(Paint.Style.FILL_AND_STROKE);
		emphasizePaint.setStrokeWidth(8f); // use dps, this will look different od diferent densities
	}

	public void setEmphasizeLine(int line) {
		emphasize = line;
		invalidate();
	}

	// override onSizeChanged
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);

		pointA = new Point(getPaddingLeft(),getHeight() - getPaddingBottom());
		pointB = new Point(getWidth() - getPaddingRight(),getHeight() - getPaddingBottom());
		pointC = new Point(getWidth() - getPaddingRight(),getPaddingTop());

		// 1-2 = a
		// 2-3 = b
		// 3-1 = c

		mPath.reset();
		mPath.moveTo(pointA.x, pointA.y);
		mPath.lineTo(pointB.x, pointB.y);
		mPath.lineTo(pointC.x, pointC.y);
		mPath.lineTo(pointA.x, pointA.y);
		mPath.close();

	}

	// override onDraw
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		// draw the mPath with the mPaint on the canvas when onDraw
		//canvas.drawPath(mPath, mPaint);
		canvas.drawPath(mPath, mPaint);

		if (emphasize == 1) {
			canvas.drawLine(pointA.x, pointA.y, pointB.x, pointB.y, emphasizePaint);
		}
		else if (emphasize == 2) {
			canvas.drawLine(pointB.x, pointB.y, pointC.x, pointC.y, emphasizePaint);
		}
		else if (emphasize == 3) {
			canvas.drawLine(pointA.x, pointA.y, pointC.x, pointC.y, emphasizePaint);
		}

		//canvas.drawLine(point1_draw.x,point1_draw.y,point2_draw.x,point2_draw.y, paint);


	}


}