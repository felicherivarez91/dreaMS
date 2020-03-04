package com.healios.dreams.ui.general

import androidx.databinding.BindingAdapter
import com.healios.dreams.DreaMSApp
import com.healios.dreams.R

class CircularProgressBarViewBindingAdapters {
    companion object{

        @JvmStatic
        @BindingAdapter("percent")
        fun setDayValue(view: CircularProgressBarView?, value: Int) {
            if (view != null) {
                view.percent = value.toFloat()
            }
        }

        @JvmStatic
        @BindingAdapter("foregroundColorEnd")
        fun setForegroundColorEmd(view: CircularProgressBarView?, colorResource: Int) {
            if (view != null) {
                view.fgColorEnd = colorResource
            }
        }

        @JvmStatic
        @BindingAdapter("foregroundColorStart")
        fun setForegroundColorStart(view: CircularProgressBarView?, colorResource: Int) {
            if (view != null) {
                view.fgColorStart = colorResource
            }
        }

        @JvmStatic
        @BindingAdapter("backgroundColor")
        fun setBackgroundColor(view: CircularProgressBarView?, colorResource: Int) {
            if (view != null) {
                view.bgColor = colorResource
            }
        }
        @JvmStatic
        @BindingAdapter("progressBarBackgroundColor")
        fun setProgressBarBackgroundColor(view: CircularProgressBarView?, colorResource: Int) {
            if (view != null) {
                view.progressBackBgColor = colorResource
            }
        }


    }
}