package com.example.cleancodeinc.kotlinnewsapp.Common

import java.text.SimpleDateFormat
import java.util.*

object ISO8601Parser {

    fun parse(param: String): Date {
        var input = param
        val df = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssz")

        if (input.endsWith("Z")) {
            input = "${input.subSequence(0, input.length - 1)} + GMT-00:00"

        } else {
            val inset = 6
            val s0 = input.subSequence(0, input.length - inset)
            val s1 = input.subSequence(input.length - inset, input.length)

            input = "$s0 GMT + $s1"
        }

        return df.parse(input)

    }

    fun toString(date: Date): String {

        val df = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssz")
        val tz = TimeZone.getTimeZone("UTC")

        df.timeZone = tz

        val output = df.format(date)

        val inset0 = 9
        val inset1 = 6

        // Return the string from to
        val s0 = output.subSequence(0, output.length - inset0)
        val s1 = output.subSequence(output.length - inset1, output.length)

        var result = "$s0+$s1"

        result = result.replace("UTC".toRegex(), replacement = "+00:00")

        return result

    } //26 12


}