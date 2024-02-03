package com.example.learntime

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class MainRepository {

    suspend fun makeReq1(data: String) : String {
        return withContext(Dispatchers.IO) {
            suspendCoroutine<String> { continuation ->
                RequestManager.makeRequest1(data,object : Callback{
                    override fun onFailure(call: Call, e: IOException) {
                        Log.d("ashir","onFailure ${e.message}")
                        continuation.resume("Fail")
                    }

                    override fun onResponse(call: Call, response: Response) {
                        Log.d("ashir","onResponse ${response.body?.string()}")
                        continuation.resume("Success")
                    }
                })
            }
        }
    }
}