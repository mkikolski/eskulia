package pl.mkikolski.mojacodziennatabletka.network

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class NetworkModule(context: Context) {
    private var requestQueue: RequestQueue = Volley.newRequestQueue(context)

    private val apiBaseUrl = "http://10.0.2.2:8000/" //instead of localhost in emulator


    fun getRequest(endpoint: String, listener: (String) -> Unit, errorListener: (Throwable) -> Unit) {
        val url = apiBaseUrl + endpoint
        val stringRequest = StringRequest(Request.Method.GET, url,
            { response -> listener(response) },
            { error -> errorListener(error) })

        requestQueue.add(stringRequest)
    }


    fun postRequest(endpoint: String, params: Map<String, String>, listener: (String) -> Unit, errorListener: (Throwable) -> Unit) {
        val url = apiBaseUrl + endpoint
        val stringRequest = object : StringRequest(Request.Method.POST, url,
            { response -> listener(response) },
            { error -> errorListener(error) }) {
            override fun getParams(): Map<String, String> {
                return params
            }
        }

        requestQueue.add(stringRequest)
    }
}