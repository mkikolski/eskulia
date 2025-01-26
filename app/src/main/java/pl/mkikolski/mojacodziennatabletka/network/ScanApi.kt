package pl.mkikolski.mojacodziennatabletka.network

import com.google.gson.Gson
import android.content.Context
import pl.mkikolski.mojacodziennatabletka.data.ScanData

class ScanApi(context: Context) {
    private val networkModule = NetworkModule(context)

    fun getScanData(scan_code: String, listener: (ScanData?) -> Unit, errorListener: (Throwable) -> Unit) {
        val endpoint = "api/scan/?code=$scan_code"
        networkModule.getRequest(endpoint, { response ->

            val scanData = try {
                Gson().fromJson(response, ScanData::class.java)
            } catch (e: Exception) {
                null
            }

            listener(scanData)
        }, errorListener)
    }
}
