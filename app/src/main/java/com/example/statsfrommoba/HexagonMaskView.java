package com.example.statsfrommoba;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.Region;
import android.support.annotation.ColorInt;
import android.support.v4.content.res.ResourcesCompat;
import android.util.AttributeSet;
import android.support.v7.widget.AppCompatImageView;

public class HexagonMaskView extends AppCompatImageView {
    private Path hexagonPath;
    private Path hexagonBorderPath;
    private Paint mBorderPaint;

    private @ColorInt int hexagonInnerBackground;
    private @ColorInt int hexagonBorderColor;

    public HexagonMaskView(Context context) {
        super(context);
        init(null,0,0);
    }

    public HexagonMaskView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.HexagonMaskView, 0,0);

        hexagonBorderColor = a.getColor(R.styleable.HexagonMaskView_hexagonBorderColor, Color.GREEN);
        hexagonInnerBackground = a.getColor(R.styleable.HexagonMaskView_hexagonInnerBackgroundColor, Color.GREEN);

        init(attrs,0,0);
    }

    public HexagonMaskView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.HexagonMaskView, defStyleAttr,0);

        hexagonBorderColor = a.getColor(R.styleable.HexagonMaskView_hexagonBorderColor, Color.GREEN);
        hexagonInnerBackground = a.getColor(R.styleable.HexagonMaskView_hexagonInnerBackgroundColor, Color.GREEN);

        init(attrs, defStyleAttr,0);
    }

    private void init(AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        this.hexagonPath = new Path();
        this.hexagonBorderPath = new Path();

        this.mBorderPaint = new Paint();
        //this.mBorderPaint.setColor(Color.WHITE);
        this.mBorderPaint.setColor(hexagonInnerBackground);
        this.setBorderColor(hexagonBorderColor);
        this.mBorderPaint.setStrokeCap(Paint.Cap.ROUND);
        this.mBorderPaint.setStrokeWidth(50f);
        this.mBorderPaint.setStyle(Paint.Style.STROKE);
    }

    public void setRadius(float radius) {
        calculatePath(radius);
    }

    public void setBorderColor(int color) {
        this.mBorderPaint.setColor(color);
        invalidate();
    }

    private void calculatePath(float radius) {
        float halfRadius = radius / 2f;
        float triangleHeight = (float) (Math.sqrt(3.0) * halfRadius);
        float centerX = getMeasuredWidth() / 2f;
        float centerY = getMeasuredHeight() / 2f;

        this.hexagonPath.reset();
        this.hexagonPath.moveTo(centerX, centerY + radius);
        this.hexagonPath.lineTo(centerX - triangleHeight, centerY + halfRadius);
        this.hexagonPath.lineTo(centerX - triangleHeight, centerY - halfRadius);
        this.hexagonPath.lineTo(centerX, centerY - radius);
        this.hexagonPath.lineTo(centerX + triangleHeight, centerY - halfRadius);
        this.hexagonPath.lineTo(centerX + triangleHeight, centerY + halfRadius);
        this.hexagonPath.close();

        float radiusBorder = radius - 5f;
        float halfRadiusBorder = radiusBorder / 2f;
        float triangleBorderHeight = (float) (Math.sqrt(3.0) * halfRadiusBorder);

        this.hexagonBorderPath.reset();
        this.hexagonBorderPath.moveTo(centerX, centerY + radiusBorder);
        this.hexagonBorderPath.lineTo(centerX - triangleBorderHeight, centerY + halfRadiusBorder);
        this.hexagonBorderPath.lineTo(centerX - triangleBorderHeight, centerY - halfRadiusBorder);
        this.hexagonBorderPath.lineTo(centerX, centerY - radiusBorder);
        this.hexagonBorderPath.lineTo(centerX + triangleBorderHeight, centerY - halfRadiusBorder);
        this.hexagonBorderPath.lineTo(centerX + triangleBorderHeight, centerY + halfRadiusBorder);
        this.hexagonBorderPath.close();
        invalidate();
    }

    @Override
    public void onDraw(Canvas c) {
        c.drawPath(hexagonBorderPath, mBorderPaint);
        c.clipPath(hexagonPath, Region.Op.INTERSECT);
        c.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
        super.onDraw(c);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(width, height);
        calculatePath(Math.min(width / 2f, height / 2f) - 10f);
    }
}
