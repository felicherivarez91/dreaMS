package com.healios.dreams.util

import android.text.format.DateFormat
import androidx.core.os.ConfigurationCompat
import com.healios.dreams.DreaMSApp
import java.text.SimpleDateFormat
import java.util.*

class DreaMSDateUtils {

    companion object {

        val DEFAULT_FORMAT_DATE:String = "yyyy-MM-dd"

        fun getDayOfTheWeekFromDateString(dateString: String) : String {
            val date: Date = getDateFromDateString(dateString)
            val dayOfTheWeek = DateFormat.format("EEEE", date) as String // Friday
            return dayOfTheWeek
        }

        fun getDayFromDateString(dateString: String) : String {
            val date: Date = getDateFromDateString(dateString)
            val day = DateFormat.format("dd", date) as String // 05
            return day
        }

        fun getMonthStringFromDateString(dateString: String) : String {
            val date: Date = getDateFromDateString(dateString)
            val monthString = DateFormat.format("MMM", date) as String // Jun
            //val monthNumber = DateFormat.format("MM", date) as String // 06
            return monthString
        }

        fun getMonthNumberFromDateString(dateString: String) : String {
            val date: Date = getDateFromDateString(dateString)
            val monthNumber = DateFormat.format("MM", date) as String // 06
            return monthNumber
        }

        fun getYearFromDateString(dateString: String) : String {
            val date: Date = getDateFromDateString(dateString)
            val year = DateFormat.format("yyyy", date) as String // 2013
            return year
        }

        fun getCurrentWeek(weekStartsOn:String, weekEndsOn: String): String {
            val starts = getDayFromDateString(weekStartsOn)
            val ends = getDayFromDateString(weekEndsOn)
            val startMonth = getMonthStringFromDateString(weekStartsOn)
            val endsMonth = getMonthStringFromDateString(weekEndsOn)
            return  String.format("%s %s - %s %s", startMonth, starts, endsMonth, ends)
        }

        fun getTodayDateString(): String{
            val date = Calendar.getInstance().time
            val inFormat = SimpleDateFormat(DEFAULT_FORMAT_DATE)
            return inFormat.format(date)
        }

        fun getTodayDate(): Date {
            return Calendar.getInstance().time
        }

        private fun getDateFromDateString(dateString: String) : Date {
            val inFormat = SimpleDateFormat(DEFAULT_FORMAT_DATE)
           return inFormat.parse(dateString)
        }
    }
}