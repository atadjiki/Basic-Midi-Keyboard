package edu.utah.arashtadjiki.keyboard;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Arash Tadjiki on 8/31/16.
 * Project 1 - CS4530
 * Professor: Matt Stoker
 */
public class KnobView extends View {

//    Gravity _gravity = 0;

    public interface OnAngleChangedListener{
        void onAngleChanged(float theta);
    }
    float _theta = 0.0f;
    RectF _knobRect = new RectF();
    OnAngleChangedListener _angleChangedListener = null;

    public KnobView(Context context) {
        super(context);
    }

//    public void setGravity(Gravity gravity){
//        _gravity = gravity;
//    }

    public void setTheta(float theta){
        _theta = theta;
        invalidate();
    }

    public float getTheta(){
        return _theta;
    }

    public void setOnAngleChangedListener(OnAngleChangedListener listener){}


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        PointF touchPoint = new PointF();
        touchPoint.x = event.getX();
        touchPoint.y = event.getY();

        //TODO: touchpoint -> theta

        float theta = (float) Math.atan2(touchPoint.y - _knobRect.centerY(), touchPoint.x - _knobRect.centerX());

        setTheta(theta);
//        _angleChangedListener.onAngleChanged(theta);

        return true;//super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        _knobRect.left = getPaddingLeft();
        _knobRect.top = getPaddingTop();
        _knobRect.right = getWidth() - getPaddingRight();
        _knobRect.bottom = _knobRect.width();

        float offset = (getHeight() - _knobRect.height())*0.5f;
        _knobRect.top += offset;
        _knobRect.bottom += offset;

        float radius = _knobRect.width() * 0.35f;
      // _theta = (float) Math.PI * 0.25f;

        PointF nibCenter = new PointF();
        nibCenter.x = _knobRect.centerX() + radius * (float)Math.cos((double) _theta);
        nibCenter.y = _knobRect.centerY() + radius * (float)Math.sin((double) _theta);

        float nibRadius = radius * 0.2f;

        RectF nibRect = new RectF();
        nibRect.left = nibCenter.x - nibRadius;
        nibRect.top = nibCenter.y - nibRadius;
        nibRect.right = nibCenter.x + nibRadius;
        nibRect.bottom = nibCenter.y + nibRadius;

        Paint knobPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        knobPaint.setColor(Color.GREEN);
        Paint nibPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        nibPaint.setColor(Color.YELLOW);

        canvas.drawOval(_knobRect, knobPaint);
        canvas.drawOval(nibRect, nibPaint);
    }

}
