package com.healios.dreams.model;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

public class AvatarModel {







    @BindingAdapter({"android:src"})
    public static void setImageViewResource(ImageView imageView, int resource) {
        imageView.setImageResource(resource);
    }

}
