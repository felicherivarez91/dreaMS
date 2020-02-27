package com.healios.dreams.util

import android.text.format.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit


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

        fun dayDifferenceBetween(firstDate: Date, secondDate:Date): Int {
            val diff: Long = secondDate.time - firstDate.time
            return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS).toInt()
        }

        fun dayDifferenceBetween(firstDateString: String, secondDateString: String): Int {
            val firstDate = getDateFromDateString(firstDateString)
            val secondDate = getDateFromDateString(secondDateString)
            val diff: Long = secondDate.time - firstDate.time
            return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS).toInt()
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

        fun getDayOfWeekOfToday():Int{
            return when (Calendar.getInstance().get(Calendar.DAY_OF_WEEK)){
                Calendar.MONDAY -> 1
                Calendar.TUESDAY -> 2
                Calendar.WEDNESDAY -> 3
                Calendar.THURSDAY -> 4
                Calendar.FRIDAY -> 5
                Calendar.SATURDAY -> 6
                Calendar.SUNDAY -> 7
                else -> 0
            }
        }

        fun getDateStringFromDateIncrementedOnDays(startDatString: String, numOfDays:Int) : String{
            val date = getDateFromDateString(startDatString)
            val cal = Calendar.getInstance()
            cal.time = date
            cal.add(Calendar.DAY_OF_YEAR, numOfDays)
            val inFormat = SimpleDateFormat(DEFAULT_FORMAT_DATE)
            return inFormat.format(cal.time)
        }



        fun getTodayDate(): Date {
            return Calendar.getInstance().time
        }

        fun getDateFromDateString(dateString: String) : Date {
            val inFormat = SimpleDateFormat(DEFAULT_FORMAT_DATE)
           return inFormat.parse(dateString)
        }
    }
}