package com.sun.efood.data.source.remote.utils

import com.sun.efood.R
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

fun connectData(urlLink: String): String {
    val url = URL(urlLink)
    val stringBuilder = StringBuilder()
    var urlOpenConnection: HttpURLConnection? = null
    var inputStreamReader: InputStreamReader? = null
    var bufferedReader: BufferedReader? = null
    try {
        urlOpenConnection = url.openConnection() as HttpURLConnection
        inputStreamReader = InputStreamReader(urlOpenConnection.inputStream)
        bufferedReader = BufferedReader(inputStreamReader)
        bufferedReader.forEachLine {
            stringBuilder.append(it)
        }
    } catch (e: IOException) {
        throw Exception((R.string.error_common).toString())
    } finally {
        urlOpenConnection?.disconnect()
        inputStreamReader?.close()
    }
    return stringBuilder.toString()
}
