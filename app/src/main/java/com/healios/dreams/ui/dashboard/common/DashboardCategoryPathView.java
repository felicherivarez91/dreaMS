package com.healios.dreams.ui.dashboard.common;

import android.app.Activity;
import android.content.Context;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.healios.dreams.R;

import java.util.ArrayList;
import java.util.List;

public class DashboardCategoryPathView extends RelativeLayout implements ViewTreeObserver.OnGlobalLayoutListener {

    private final String TAG = DashboardCategoryPathView.class.getSimpleName();

    //region: Path Params
    private List<PointF> pointsToChallenges = new ArrayList<>();
    private float totalHeight = 0;

    private int challengeNumber = 14;

    //Modify this value to change top offSet
    private float topOffset = 50f * getScreenDensity();
    //Modify this value to change curve separation
    private float curveSeparation = 100.0f * getScreenDensity();
    private float currentY = topOffset;

    private float firstQ = 0;
    private float quarterSize = 0;
    private float centerX = 0;
    private float thirdQ = 0;

    private float curveSeparationTwoThird = (curveSeparation * 2) / 3;

    private Path createdPath = new Path();
    //endregion

    private CategoryPathView pathView;
    private CategoryChallengesView challengesView;
    private RelativeLayout wrapperLayout;
    private ScrollView scrollView;
    //region: Constructor

    public DashboardCategoryPathView(Context context) {
        super(context);
        init(null);
    }

    public DashboardCategoryPathView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }


    public DashboardCategoryPathView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    //endregion

    private void init(AttributeSet attrs) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.custom_view_dashboard_category_path_view, this, true);

        attachViews();
        initView();
    }

    private void attachViews() {
        scrollView = findViewById(R.id.scroll_view_dashboard_category_path_view);
        wrapperLayout = findViewById(R.id.relativeLayout_dashboard_category_path_view_wrapper_layout);
        pathView = findViewById(R.id.path_view_dashboard_category_path_view_path);
        challengesView = findViewById(R.id.challenges_view_dashboard_category_path_view_challenges);
    }

    private void initView() {

        // Set layout change observer to challenges View
        ViewTreeObserver challengesViewViewTreeObserver = challengesView.getViewTreeObserver();
        challengesViewViewTreeObserver.addOnGlobalLayoutListener(this);


        // Initialize Path Params
        initializePathParameters();

        // Calculate path
        Path path = calculatePath();

        // Set path to Path View
        pathView.setPath(path);

        // Place challenges in View
        placeChallengesInView();

    }

    @Override
    public void onGlobalLayout() {

        //Resize Path View
        resizePathView();

        challengesView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
    }

    private void resizePathView() {

        int challengesViewHeight = challengesView.getHeight();
        pathView.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, challengesViewHeight));

    }

    private void placeChallengesInView() {
        challengesView.setPointsToChallenges(pointsToChallenges);
    }

    private void initializePathParameters() {
        Log.v("CategoryPathView", "setPath");
        firstQ = getWindowSize().x / 4;
        centerX = getWindowSize().x / 2;
        quarterSize = firstQ * 0.25f;
        thirdQ = centerX + firstQ;
    }

    //region: Calculate Path
    private Path calculatePath() {

        if (challengeNumber > 0) {
            PointF startPoint = new PointF(centerX, currentY);

            //Create Path
            createdPath.moveTo(startPoint.x, startPoint.y);
            addToPointToChallenges(startPoint);
        }

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
            createFifthStretch();

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

    private void createFifthStretch() {
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
    //endregion

    //region: Utils
    private Point getWindowSize() {
        Display display = ((Activity) getContext()).getWindowManager().getDefaultDisplay();
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