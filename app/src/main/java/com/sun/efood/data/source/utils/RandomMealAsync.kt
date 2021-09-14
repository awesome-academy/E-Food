package com.sun.efood.data.source.utils

import android.os.AsyncTask
import com.sun.efood.data.model.Meal

const val DRINK_REQUEST_NUMBER = 5

class RandomMealAsync(
    private val callback: OnDataCallback<Meal>,
    private val handle: () -> Meal?
) : AsyncTask<Unit, Meal, Unit>() {

    override fun doInBackground(vararg params: Unit?) {
        for (i in 0 until DRINK_REQUEST_NUMBER) {
            publishProgress(handle())
        }
    }

    override fun onProgressUpdate(vararg values: Meal?) {
        super.onProgressUpdate(*values)
        values[0]?.apply(callback::onSuccess)
    }
}
