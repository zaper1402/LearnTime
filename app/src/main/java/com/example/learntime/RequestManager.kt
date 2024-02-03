package com.example.learntime

import okhttp3.Callback
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.io.BufferedOutputStream
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL

object RequestManager {

    val okHttpClient = OkHttpClient()

    fun makeBasereq() {
        val url = URL("https://mocki.io/v1/061662d9-143c-44ca-abb7-5562f29ef933")
        val connection : HttpURLConnection? = url.openConnection() as? HttpURLConnection
        connection?:return
        connection.setRequestProperty("Content-Type","application/json")
        connection.doOutput = true
        connection.setChunkedStreamingMode(0)
        val op = BufferedOutputStream(connection.outputStream)
        val writer = OutputStreamWriter(op)
        val data : String? = null
        writer.write(data)
        writer.flush()
        writer.close()
        connection.disconnect()
    }

    fun makeRequest1(data: String, callback: Callback) {
        val json = JSONObject().put("name",data);
        val body = json.toString().toRequestBody("application/json".toMediaTypeOrNull())
        val req = Request.Builder()
            .url("https://mocki.io/v1/061662d9-143c-44ca-abb7-5562f29ef933")
//            .post(body)
            .get()
            .build()
        val call = okHttpClient.newCall(req)
        call.enqueue(callback)
    }
}