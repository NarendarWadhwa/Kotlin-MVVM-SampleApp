package com.example.findme.base

interface BaseNavigator {

    fun onInternetError();

    fun onError(message: String)
}