package com.healios.dreams.ui.dashboard;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CategoryPathView extends View {

    //region: Params
    private List<PointF> pointsToChallenges = new ArrayList<>();
    private float totalHeight = 0;

    private int challengeNumber = 10;

    //Modify this value to change top offSet
    private float topOffset = 30f;
    //Modify this value to change curve separation
    private float curveSeparation = 100.0f * getScreenDensity();
    private float currentY = topOffset;

    private float firstQ = 0;
    private float quarterSize = 0;
    private float centerX = 0;
    private float thirdQ = 0;
    private int pathColor;

    private float curveSeparationTwoThird = (curveSeparation * 2) / 3;

    private Path createdPath = new Path();
    //endregion

    Paint pathPaintBrush;
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
    }

    private void init(Context context, AttributeSet attrs) {
        this.context = context;
        initializeParameters();
    }

    private void initializeParameters() {
        firstQ = getWindowSize().x / 4;
        centerX = getWindowSize().x / 2;
        quarterSize = firstQ * 0.25f;
        thirdQ = centerX + firstQ;

        //Path color
        pathColor = Color.LTGRAY;
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
        this.pathPaintBrush.setColor(pathColor);
        this.pathPaintBrush.setStyle(Paint.Style.STROKE);
        this.pathPaintBrush.setStrokeWidth(5);
        this.pathPaintBrush.setPathEffect(new DashPathEffect(new float[]{18 * getScreenDensity(), 9 * getScreenDensity()}, 50));
    }

    private void drawPath(Canvas canvas) {
        Path path = getPath();

        if (path != null) {
            canvas.drawPath(path, pathPaintBrush);
        }
    }

    private Path getPath() {
        if (challengeNumber < 0)
            return null;

        PointF startPoint = new PointF(centerX, currentY);

        //Create Path
        createdPath.moveTo(startPoint.x, startPoint.y);
        addToPointToChallenges(startPoint);

        while (challengeNumber > 0) {

            drawFirstStretch();
            currentY = currentY + curveSeparation;
            createSecondStretch();

            if (challengeNumber == 0) {
                break;
            }

            createThirdStretch();

            if (challengeNumber == 0) {
                break;
            }

            createForthStretch();
            createFithStretch();

            if (challengeNumber == 0) {
                break;
            }
        }

        int lastPointIndex = pointsToChallenges.size() - 1;
        PointF lastPoint = pointsToChallenges.get(lastPointIndex);
        totalHeight = lastPoint.y;

        return createdPath;
    }

    private void drawFirstStretch() {
        PointF lineEnd = new PointF();
        lineEnd.x = firstQ;
        lineEnd.y = currentY;

        createdPath.lineTo(lineEnd.x, lineEnd.y);
        PointF curveStart = new PointF(firstQ, currentY);
        createdPath.moveTo(curveStart.x, curveStart.y);
    }

    private void createSecondStretch() {
        float currentYThird = currentY - curveSeparationTwoThird;
        PointF curveEnd = new PointF(firstQ, currentY);

        PointF controlPoint1 = new PointF(quarterSize, currentYThird);
        PointF controlPoint2 = new PointF(quarterSize * 3, currentY);

        addCurve(controlPoint1, controlPoint2, curveEnd);

        addToPointToChallenges(curveEnd);
    }

    private void createThirdStretch() {
        createdPath.moveTo(firstQ, currentY);
        PointF lineEnd = new PointF(thirdQ, currentY);
        createdPath.lineTo(lineEnd.x, lineEnd.y);
        addToPointToChallenges(lineEnd);
    }

    private void createForthStretch() {
        PointF curveStart = new PointF(thirdQ, currentY);
        createdPath.moveTo(curveStart.x, curveStart.y);

        currentY = currentY + curveSeparation;
        float currentYThird = currentY - curveSeparationTwoThird;
        PointF curveEnd = new PointF(thirdQ, currentY);

        PointF controlPoint1 = new PointF(thirdQ + (quarterSize * 3), currentYThird);
        PointF controlPoint2 = new PointF(thirdQ + quarterSize, currentY);
        addCurve(controlPoint1, controlPoint2, curveEnd);
    }

    private void createFithStretch() {
        PointF lineEnd = new PointF(centerX, currentY);
        createdPath.lineTo(lineEnd.x, lineEnd.y);
        addToPointToChallenges(lineEnd);
    }

    private void addCurve(PointF controlPoint1, PointF controlPoint2, PointF endPoint) {
        createdPath.cubicTo(controlPoint1.x, controlPoint1.y, controlPoint2.x, controlPoint2.y, endPoint.x, endPoint.y);
    }

    private void addToPointToChallenges(PointF point) {
        pointsToChallenges.add(point);
        challengeNumber = challengeNumber - 1;
    }

    //region: Utils
    private Point getWindowSize() {
        Display display = ((Activity) this.context).getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size;
    }

    private float getScreenDensity() {
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        return metrics.density;
    }
    //endregion
}
