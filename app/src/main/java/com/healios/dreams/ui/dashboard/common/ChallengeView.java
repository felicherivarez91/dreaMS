package com.healios.dreams.ui.dashboard.common;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.healios.dreams.R;
import com.healios.dreams.databinding.CustomViewCategoryIconBinding;
import com.healios.dreams.model.Test;
import com.healios.dreams.model.challenge.metadata.ChallengeMetadata;
import com.healios.dreams.util.ChallengeUtils;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChallengeView extends RelativeLayout {

    private CircleImageView challengeImage;
    private ImageView doneImage;
    private View wrapperLayout;

    private Test test;
    private ChallengeMetadata challengeMetadataFromTest;
    private ChallengeViewListener listener;

    private CustomViewCategoryIconBinding binding;

    //region: Constructor
    public ChallengeView(Context context, ChallengeViewListener listener) {
        super(context);
        this.listener = listener;
        init(context,null);
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
        //binding = DataBindingUtil.inflate(inflater, R.layout.custom_view_category_icon, this, false);
        binding = CustomViewCategoryIconBinding.inflate(inflater, (ViewGroup) this.getRootView(), true);
    }

    public void setChallenge(Test test) {
        this.test = test;
        this.challengeMetadataFromTest = ChallengeUtils.Companion.getChallengeMetadataFromTest(test);
        binding.setTest(test);
        binding.setChallengeMetadata(challengeMetadataFromTest);

        bind();
    }

    private void bind() {
        binding.relativeLayoutCustomViewCategoryIconWrapperLayout.setOnClickListener(v -> {
            if (listener != null)
                listener.onChallengeIconClick(test);
        });

        setImageBorderColor();
    }

    private void setImageBorderColor() {
        if (test.getCompletedAt() != null) {
            binding.imageViewCustomViewCategoryIconImage.setBorderColor(getResources().getColor(R.color.categoryIconCompletedStatusColor));
        }else{
            if (challengeMetadataFromTest.getAvailable()){
                binding.imageViewCustomViewCategoryIconImage.setBorderColor(getResources().getColor(R.color.categoryIconUnCompletedStatusColor));
            }else {
                binding.imageViewCustomViewCategoryIconImage.setBorderColor(getResources().getColor(R.color.categoryIconUnavailableStatusColor));
            }
        }
    }

}
