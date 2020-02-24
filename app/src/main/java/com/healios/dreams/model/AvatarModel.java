package com.healios.dreams.model;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

public class AvatarModel {

    private int avatarId;
    private int avatarResource;
    private int avatarCompleteImageResource;
    private boolean isSelected;


    public AvatarModel(int avatarId, int avatarResource, int avatarCompleteImageResource) {
        this.avatarId = avatarId;
        this.avatarResource = avatarResource;
        this.avatarCompleteImageResource = avatarCompleteImageResource;
        this.isSelected = false;
    }

    public int getAvatarId() {
        return avatarId;
    }

    public int getAvatarResource() {
        return avatarResource;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setAvatarId(int avatarId) {
        this.avatarId = avatarId;
    }

    public void setAvatarResource(int avatarResource) {
        this.avatarResource = avatarResource;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public int getAvatarCompleteImageResource() {
        return avatarCompleteImageResource;
    }

    public void setAvatarCompleteImageResource(int avatarCompleteImageResource) {
        this.avatarCompleteImageResource = avatarCompleteImageResource;
    }

    @BindingAdapter({"android:src"})
    public static void setImageViewResource(ImageView imageView, int resource) {
        imageView.setImageResource(resource);
    }

}
