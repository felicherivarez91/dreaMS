package com.healios.dreams.ui.dashboard;


import android.content.Context;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import com.healios.dreams.R;

import java.util.List;

public class CategoryChallengesView extends RelativeLayout {

    private final String TAG = CategoryChallengesView.class.getSimpleName();

    private final int CATEGORY_CHALLENGE_HEIGHT = 70;
    private final int CATEGORY_CHALLENGE_WIDTH = CATEGORY_CHALLENGE_HEIGHT;

    private RelativeLayout mChallengesGroupView;

    private List<PointF> pointsToChallenges;

    //region: Constructor
    public CategoryChallengesView(Context context) {
        super(context);
        init(context, null);

    }

    public CategoryChallengesView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public CategoryChallengesView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }
    //endregion

    private void init(Context context, AttributeSet attrs) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.custom_view_challenges_view, this, true);

        attachView();
    }

    private void attachView() {
        mChallengesGroupView = findViewById(R.id.custom_view_challenges_view_challenges_group_view);
    }


    public void setPointsToChallenges(List<PointF> pointsToChallenges) {
        this.pointsToChallenges = pointsToChallenges;
        this.placeChallengesInView();
    }


    private void placeChallengesInView() {

        for (PointF centerPoint : pointsToChallenges) {
            // Place Current Challenge
            this.placeChallenge(centerPoint);
        }
    }

    private void placeChallenge(PointF centerPoint) {

        ChallengeView challengeView = new ChallengeView(this.getContext());

        /*
        int random = new Random().nextInt(3) + 1;
        if (random == 1) {
            challengeView.setStatus(ChallengeView.ChallengeStatus.Completed);
        } else if (random == 2) {
            challengeView.setStatus(ChallengeView.ChallengeStatus.Uncompleted);
        } else {
            challengeView.setStatus(ChallengeView.ChallengeStatus.Unavailable);
        }
         */

        LayoutParams layoutParams = new LayoutParams((int) (CATEGORY_CHALLENGE_WIDTH * getScreenDensity()), (int) (CATEGORY_CHALLENGE_HEIGHT * getScreenDensity()));

        layoutParams.setMarginStart((int) ((int) centerPoint.x - (CATEGORY_CHALLENGE_WIDTH / 2 * getScreenDensity())));
        layoutParams.topMargin = (int) ((int) centerPoint.y - (CATEGORY_CHALLENGE_HEIGHT / 2 * getScreenDensity()));

        mChallengesGroupView.addView(challengeView, layoutParams);

    }

    private float getScreenDensity() {
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        return metrics.density;
    }

}
