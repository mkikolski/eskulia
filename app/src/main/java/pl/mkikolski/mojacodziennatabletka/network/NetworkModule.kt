package pl.mkikolski.mojacodziennatabletka.network

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

/**
 * Class responsible for handling network requests using Volley.
 *
 * @property requestQueue The request queue for managing network requests.
 * @property apiBaseUrl The base URL for the API.
 */
class NetworkModule(context: Context) {
    private var requestQueue: RequestQueue = Volley.newRequestQueue(context)

//    private val apiBaseUrl = "http://10.0.2.2:8000/" //instead of localhost in emulator
    private val apiBaseUrl = "http://192.168.1.105:8000/"

    /**
     * Sends a GET request to the specified endpoint.
     *
     * @param endpoint The endpoint to send the request to.
     * @param listener The listener to handle the response.
     * @param errorListener The listener to handle errors.
     */
    fun getRequest(endpoint: String, listener: (String) -> Unit, errorListener: (Throwable) -> Unit) {
        val url = apiBaseUrl + endpoint
        val stringRequest = StringRequest(Request.Method.GET, url,
            { response -> listener(response) },
            { error -> errorListener(error) })

        requestQueue.add(stringRequest)
    }

    /**
     * Sends a POST request to the specified endpoint with the given parameters.
     *
     * @param endpoint The endpoint to send the request to.
     * @param params The parameters to include in the request body.
     * @param listener The listener to handle the response.
     * @param errorListener The listener to handle errors.
     */
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