package com.healios.dreams.ui.general;

import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import androidx.annotation.Nullable;

import com.healios.dreams.R;

public class CircularProgressBarView extends View {

    private final String TAG = CircularProgressBarView.class.getSimpleName();

    private final int angleCorrection = 270;

    private float mPercent = 75;
    private float mStrokeWidth;
    private int mProgressBackBgColor = 0xffe1e1e1;
    private float mStartAngle = 0;
    private int mBgColor = getResources().getColor(R.color.white);
    private int mFgColorStart = getResources().getColor(R.color.colorPrimary);
    private int mFgColorEnd = getResources().getColor(R.color.colorPrimary);
    private int mTimeLinesColor = 0xffe1e1e1;
    private int numOfSeconds = 60;

    private Paint backgroundPatinBrush;
    private Paint paintBrush;
    private RectF mOval;
    private LinearGradient mShader;

    private ObjectAnimator animator;
    private boolean showTimeLines;

    public CircularProgressBarView(Context context) {
        super(context);
        init(null);
    }

    public CircularProgressBarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public CircularProgressBarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        if (attrs != null) {
            initWithAttrs(attrs);
        }
        createBackgroundPaintBrush();
        createPaintBrush();
    }

    private void initWithAttrs(AttributeSet attrs) {
        TypedArray styledAttributes = getContext().getTheme().obtainStyledAttributes(attrs,
                R.styleable.CircularProgressBar,
                0, 0);
        try {

            mProgressBackBgColor = styledAttributes.getColor(R.styleable.CircularProgressBar_progressBarBackgroundColor, 0xffe1e1e1);

            mBgColor = styledAttributes.getColor(R.styleable.CircularProgressBar_backgroundColor, 0xffe1e1e1);
            mFgColorEnd = styledAttributes.getColor(R.styleable.CircularProgressBar_foregroundColorEnd, 0xffff4800);
            mFgColorStart = styledAttributes.getColor(R.styleable.CircularProgressBar_foregroundColorStart, 0xffffe400);
            mPercent = styledAttributes.getFloat(R.styleable.CircularProgressBar_percent, 75);
            mStartAngle = styledAttributes.getFloat(R.styleable.CircularProgressBar_startAngle, 0) + angleCorrection;
            mStrokeWidth = styledAttributes.getDimensionPixelSize(R.styleable.CircularProgressBar_strokeWidth, dpsToPixels(16));

            // Second lines
            showTimeLines = styledAttributes.getBoolean(R.styleable.CircularProgressBar_showSecondTimeLines, false);
            mTimeLinesColor = styledAttributes.getColor(R.styleable.CircularProgressBar_secondTimeLineColor, 0xffe1e1e1);
            numOfSeconds = styledAttributes.getInteger(R.styleable.CircularProgressBar_numOfSeconds, 60);


        } finally {
            styledAttributes.recycle();
        }
    }

    private void createPaintBrush() {
        paintBrush = new Paint();
        paintBrush.setAntiAlias(true);
        paintBrush.setStyle(Paint.Style.STROKE);
        paintBrush.setStrokeWidth(mStrokeWidth);
        paintBrush.setStrokeCap(Paint.Cap.ROUND);
    }

    private void createBackgroundPaintBrush() {
        backgroundPatinBrush = new Paint();
        backgroundPatinBrush.setAntiAlias(true);
        backgroundPatinBrush.setStyle(Paint.Style.FILL);
        backgroundPatinBrush.setStrokeCap(Paint.Cap.ROUND);
        backgroundPatinBrush.setColor(mProgressBackBgColor);
    }
    //region: Draw Canvas

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawBackground(canvas);

        drawCircularProgressBar(canvas);

        // Draw the time lines
        if (showTimeLines)
            drawTimeLines(canvas);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        updateOval();

        mShader = new LinearGradient(mOval.left, mOval.top, mOval.left, mOval.bottom, mFgColorStart, mFgColorEnd, Shader.TileMode.MIRROR);

    }

    private void drawBackground(Canvas canvas) {

        float w = getWidth();
        float h = getHeight();

        int xp = getPaddingLeft() + getPaddingRight();
        int yp = getPaddingBottom() + getPaddingTop();

        float usableWidth = w - xp;
        float usableHeight = h - yp;

        float centerX = xp + (usableWidth / 2);
        float centerY = yp + (usableHeight / 2);

        float radius = (Math.min(usableWidth, usableHeight) / 2);

        canvas.drawCircle(centerX, centerY, radius, backgroundPatinBrush);

    }

    private void drawCircularProgressBar(Canvas canvas) {
        paintBrush.setShader(null);
        paintBrush.setColor(mBgColor);

        // Draw stats background circle
        canvas.drawArc(mOval, 0, 360, false, paintBrush);

        // Draw stats foreground circle with gradient colors
        paintBrush.setShader(mShader);
        canvas.drawArc(mOval, mStartAngle, mPercent * 3.6f, false, paintBrush);
    }

    private void drawTimeLines(Canvas canvas) {

        float w = getWidth();
        float h = getHeight();

        int xp = getPaddingLeft() + getPaddingRight();
        int yp = getPaddingBottom() + getPaddingTop();

        float usableWidth = w - xp;
        float usableHeight = h - yp;

        float radius = (Math.min(usableWidth, usableHeight) / 2) - 2 * mStrokeWidth;
        float centerX = xp + (usableWidth / 2);
        float centerY = yp + (usableHeight / 2);

        float pointSeparation = 360 / numOfSeconds;

        Paint linePaintBrush = new Paint();
        linePaintBrush.setColor(mTimeLinesColor);
        linePaintBrush.setStyle(Paint.Style.STROKE);
        linePaintBrush.setStrokeWidth(2);

        for (float angle = 0 + angleCorrection; angle < 360 + angleCorrection; angle = angle + pointSeparation) {
            float finalX = centerX + ((float) Math.cos(Math.toRadians(angle)) * radius); //convert angle to radians for finalX and y coordinates
            float finalY = centerY + ((float) Math.sin(Math.toRadians(angle)) * radius);
            canvas.drawLine(centerX, centerY, finalX, finalY, linePaintBrush); //draw a line from center point back to the point
        }

        // Draw inner circle
        canvas.drawCircle(centerX, centerX, radius - (mStrokeWidth / 1.5f), backgroundPatinBrush);
    }

    //endregion

    //region: Setters && Getters
    public float getPercent() {
        return mPercent;
    }

    public void setPercent(float mPercent) {
        this.mPercent = mPercent;
        refreshTheLayout();
    }

    public float getStrokeWidth() {
        return mStrokeWidth;
    }

    public void setStrokeWidth(float mStrokeWidth) {
        this.mStrokeWidth = mStrokeWidth;
        paintBrush.setStrokeWidth(mStrokeWidth);
        updateOval();
        refreshTheLayout();
    }

    public void setStrokeWidthDp(float dp) {
        this.mStrokeWidth = dpsToPixels(dp);
        paintBrush.setStrokeWidth(mStrokeWidth);
        updateOval();
        refreshTheLayout();
    }

    public int getFgColorStart() {
        return mFgColorStart;
    }

    public void setFgColorStart(int mFgColorStart) {
        this.mFgColorStart = mFgColorStart;
        mShader = new LinearGradient(mOval.left, mOval.top,
                mOval.left, mOval.bottom, mFgColorStart, mFgColorEnd, Shader.TileMode.MIRROR);
        refreshTheLayout();
    }

    public int getFgColorEnd() {
        return mFgColorEnd;
    }

    public void setFgColorEnd(int mFgColorEnd) {
        this.mFgColorEnd = mFgColorEnd;
        mShader = new LinearGradient(mOval.left, mOval.top,
                mOval.left, mOval.bottom, mFgColorStart, mFgColorEnd, Shader.TileMode.MIRROR);
        refreshTheLayout();
    }


    public float getStartAngle() {
        return mStartAngle;
    }

    public void setStartAngle(float mStartAngle) {
        this.mStartAngle = mStartAngle + angleCorrection;
        refreshTheLayout();
    }

    //endregion

    private void updateOval() {
        int xp = getPaddingLeft() + getPaddingRight();
        int yp = getPaddingBottom() + getPaddingTop();
        mOval = new RectF(getPaddingLeft() + mStrokeWidth, getPaddingTop() + mStrokeWidth,
                getPaddingLeft() + (getWidth() - xp) - mStrokeWidth,
                getPaddingTop() + (getHeight() - yp) - mStrokeWidth);
    }

    public void refreshTheLayout() {
        invalidate();
        requestLayout();
    }


    //region: Animation
    public void animateIndeterminate() {
        animateIndeterminate(800, new AccelerateDecelerateInterpolator());
    }

    public void animateIndeterminate(int durationOneCircle,
                                     TimeInterpolator interpolator) {
        animator = ObjectAnimator.ofFloat(this, "startAngle", getStartAngle(), getStartAngle() + 360);
        if (interpolator != null) animator.setInterpolator(interpolator);
        animator.setDuration(durationOneCircle);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setRepeatMode(ValueAnimator.RESTART);
        animator.start();
    }

    public void stopAnimateIndeterminate() {
        if (animator != null) {
            animator.cancel();
            animator = null;
        }
    }
    //endregion

    //region: Utils
    private int dpsToPixels(float dp) {
        return (int) (getContext().getResources().getDisplayMetrics().density * dp + 0.5f);
    }
    //endregion
}
