package com.vpd.vpdmobile.utils

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.GregorianCalendar

fun dayStringFormat(msecs: Long): String? {
    val cal = GregorianCalendar()
    cal.time = Date(msecs)
    val dow = cal[Calendar.DAY_OF_WEEK]
    when (dow) {
        Calendar.MONDAY -> return "Monday"
        Calendar.TUESDAY -> return "Tuesday"
        Calendar.WEDNESDAY -> return "Wednesday"
        Calendar.THURSDAY -> return "Thursday"
        Calendar.FRIDAY -> return "Friday"
        Calendar.SATURDAY -> return "Saturday"
        Calendar.SUNDAY -> return "Sunday"
    }
    return "Unknown"
}

fun milliToDate(value: Long): String? {
    val formatter: DateFormat = SimpleDateFormat("dd/MM/yyyy")
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = value
    return formatter.format(calendar.time)
}