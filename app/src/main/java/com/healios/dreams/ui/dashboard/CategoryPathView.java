package com.healios.dreams.ui.dashboard;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

import androidx.annotation.Nullable;

public class CategoryPathView extends View {

    private Path createdPath = null;

    private Paint pathPaintBrush;
    private Context context;

    //region:Constructor
    public CategoryPathView(Context context) {
        super(context);
        init(context, null);
    }

    public CategoryPathView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public CategoryPathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        this.context = context;
    }

    public void setPath(Path path) {
        this.createdPath = path;
        //this.drawPath(canvas);
    }
    //endregion

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        this.createPathPaintBrush();

        drawPath(canvas);

    }

    private void createPathPaintBrush() {
        this.pathPaintBrush = new Paint();
        this.pathPaintBrush.setColor(Color.LTGRAY);
        this.pathPaintBrush.setStyle(Paint.Style.STROKE);
        this.pathPaintBrush.setStrokeWidth(5);
        this.pathPaintBrush.setPathEffect(new DashPathEffect(new float[]{18 * getScreenDensity(), 9 * getScreenDensity()}, 50));
    }

    private void drawPath(Canvas canvas) {
        if (canvas != null) {
            if (createdPath != null) {
                canvas.drawPath(createdPath, pathPaintBrush);
            }
        }
    }

    private float getScreenDensity() {
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        return metrics.density;
    }

    //endregion
}
