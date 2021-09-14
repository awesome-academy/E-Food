package com.sun.efood.data.source.utils

interface OnDataCallback<T> {
    fun onSuccess(data: T)
    fun onFailure(e: Exception?)
}
