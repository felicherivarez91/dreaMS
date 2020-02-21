package com.healios.dreams.model

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.healios.dreams.DreaMSApp
import com.healios.dreams.util.resIdByName

data class CountryModel(
    @JvmField val countryCode: String,
    @JvmField val telephoneCountryCode: String,
    @JvmField val telephoneMask: String,
    @JvmField val countryName: String,
    @JvmField val countryFlag: String,
    @JvmField val validationRegex: String) {

    var isSelectedCountry: Boolean = false


}