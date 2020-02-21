package com.healios.dreams.util

import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.Drawable


fun Context.resIdByName(resIdName: String?, resType: String): Drawable {
    resIdName?.let {
        val resourceId = resources.getIdentifier(it, resType, packageName)
        return resources.getDrawable(resourceId, theme)
    }
    throw Resources.NotFoundException()
}