package pl.mkikolski.mojacodziennatabletka.network

import android.content.Context
import com.google.gson.Gson
import pl.mkikolski.mojacodziennatabletka.data.Medicine

/**
 * Class responsible for interacting with the Medicine API.
 *
 * @property context The context of the application.
 */
class MedicineApi(context: Context) {
    private val networkModule = NetworkModule(context)

    /**
     * Sends a message to the chatbot.
     *
     * @param message The message to be sent.
     * @param chatId The ID of the chat.
     * @param listener The listener to handle the response message.
     * @param errorListener The listener to handle errors.
     */
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

    /**
     * Retrieves medicine information by name.
     *
     * @param name The name of the medicine.
     * @param listener The listener to handle the retrieved medicine.
     * @param errorListener The listener to handle errors.
     */
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

    /**
     * Retrieves medicine information by code.
     *
     * @param code The code of the medicine.
     * @param listener The listener to handle the retrieved medicine.
     * @param errorListener The listener to handle errors.
     */
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