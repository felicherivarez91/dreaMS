package com.healios.dreams.util;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import com.healios.dreams.DreaMSApp;

public class DataBindingAdapters {

    @BindingAdapter("android:src")
    public static void setDrawableByName(ImageView view, String name) {

        Context appContext = DreaMSApp.getInstance().getApplicationContext();
        String packageName = appContext.getPackageName();

        Uri uri = Uri.parse("android.resource://" + packageName + "/drawable/" + name);

        if (uri == null) {
            view.setImageURI(null);
        } else {
            view.setImageURI(uri);
        }
    }

    @BindingAdapter("android:src")
    public static void setImageUri(ImageView view, Uri imageUri) {
        view.setImageURI(imageUri);
    }

    @BindingAdapter("android:src")
    public static void setImageDrawable(ImageView view, Drawable drawable) {
        view.setImageDrawable(drawable);
    }

    @BindingAdapter("android:src")
    public static void setImageResource(ImageView imageView, int resource){
        imageView.setImageResource(resource);
    }

    @BindingAdapter("android:textResource")
    public static void setTextResource(TextView textView, String resourceName){

        Context appContext = DreaMSApp.getInstance().getApplicationContext();
        String packageName = appContext.getPackageName();

        int resource = appContext.getResources().getIdentifier(resourceName, "string", packageName);
        textView.setText(resource);
    }
}