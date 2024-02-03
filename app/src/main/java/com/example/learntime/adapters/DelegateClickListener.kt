package com.example.learntime.adapters

interface DelegateClickListener {

    fun onClick(data : Any?, id : String, extraData: Any? = null)
}