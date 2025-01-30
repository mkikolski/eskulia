package pl.mkikolski.mojacodziennatabletka.network

import android.content.Context
import com.google.gson.Gson
import pl.mkikolski.mojacodziennatabletka.data.Medicine

class MedicineApi(context: Context) {
    private val networkModule = NetworkModule(context)

    fun sendMessage(message: String, chatId: String, listener: (String) -> Unit, errorListener: (Throwable) -> Unit) {
        val endpoint = "chatbot/mock/"
        val body = mapOf("user_msg" to message, "doc_id" to chatId)
        networkModule.postRequest(endpoint, body, { response ->

            val responseMessage = try {
                Gson().fromJson(response, String::class.java)
            } catch (e: Exception) {
                null
            }

            listener(responseMessage ?: "")
        }, errorListener)
    }


    fun getMedicineByName(name: String, listener: (Medicine?) -> Unit, errorListener: (Throwable) -> Unit) {
        val endpoint = "api/medicine/name/$name"
        networkModule.getRequest(endpoint, { response ->

            val medicine = try {
                Gson().fromJson(response, Medicine::class.java)
            } catch (e: Exception) {
                null
            }

            listener(medicine)
        }, errorListener)
    }


    fun getMedicineByCode(code: String, listener: (Medicine?) -> Unit, errorListener: (Throwable) -> Unit) {
        val endpoint = "api/medicine/code/$code"
        networkModule.getRequest(endpoint, { response ->

            val medicine = try {
                Gson().fromJson(response, Medicine::class.java)
            } catch (e: Exception) {
                null
            }

            listener(medicine)
        }, errorListener)
    }
}