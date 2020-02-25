package com.healios.dreams.ui.dashboard.common;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.healios.dreams.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChallengeView extends RelativeLayout {

    public enum ChallengeStatus {
        Completed,
        Uncompleted,
        Unavailable
    }

    private CircleImageView challengeImage;
    private ImageView doneImage;

    //region: Constructor
    public ChallengeView(Context context) {
        super(context);
        init(context, null);
    }

    public ChallengeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ChallengeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }
    //endregion

    private void init(Context context, AttributeSet attrs) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.custom_view_category_icon, this, true);
        attachView();
    }

    private void attachView() {
        challengeImage = findViewById(R.id.imageView_custom_view_category_icon_image);
        doneImage = findViewById(R.id.imageView_custom_view_category_icon_done_image);
    }

    public void setStatus(ChallengeStatus status) {
        switch (status) {
            case Completed:
                challengeImage.setBorderColor(getResources().getColor(R.color.categoryIconCompletedStatusColor));
                break;
            case Uncompleted:
                challengeImage.setBorderColor(getResources().getColor(R.color.categoryIconUnCompletedStatusColor));
                break;
            case Unavailable:
                challengeImage.setBorderColor(getResources().getColor(R.color.categoryIconUnavailableStatusColor));
                break;
        }

    }

}
