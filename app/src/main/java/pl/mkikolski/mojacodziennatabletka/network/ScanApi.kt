package pl.mkikolski.mojacodziennatabletka.network

import com.google.gson.Gson
import android.content.Context
import pl.mkikolski.mojacodziennatabletka.data.ScanData

/**
 * Class responsible for interacting with the Scan API.
 *
 * @property context The context of the application.
 */
class ScanApi(context: Context) {
    private val networkModule = NetworkModule(context)

    /**
     * Retrieves scan data by scan code.
     *
     * @param scan_code The code of the scan.
     * @param listener The listener to handle the retrieved scan data.
     * @param errorListener The listener to handle errors.
     */
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
