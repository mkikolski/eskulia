package pl.mkikolski.mojacodziennatabletka.data

import com.google.firebase.Firebase
import com.google.firebase.Timestamp
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await

/**
 * Repository class for interacting with Firestore database.
 */
class FirestoreRepository {
    private val db: FirebaseFirestore = Firebase.firestore

    /**
     * Retrieves a user document from Firestore.
     *
     * @param uid The unique identifier of the user.
     * @return The User object if found, otherwise null.
     */
    suspend fun getUser(uid: String): User? {
        return db.collection("users").document(uid).get().await().toObject(User::class.java)
    }

    /**
     * Updates a user document in Firestore.
     *
     * @param user The User object to be updated.
     */
    suspend fun updateUser(user: User) {
        db.collection("users").document(user.uid).set(user).await()
    }

    /**
     * Retrieves a list of medications from Firestore based on their IDs.
     *
     * @param medicationIds The list of medication IDs.
     * @return A list of Medication objects.
     */
    suspend fun getMedications(medicationIds: List<String>): List<Medication> {
        return try {
            db.collection("medications").whereIn("id", medicationIds).get().await().toObjects(Medication::class.java)
        } catch (e: Exception) {
            emptyList()
        }
    }

    /**
     * Adds a new medication document to Firestore.
     *
     * @param medication The Medication object to be added.
     * @return The ID of the newly added medication document.
     */
    suspend fun addMedication(medication: Medication): String {
        val docRef = db.collection("medications").add(medication).await()
        db.collection("medications").document(docRef.id).update("id", docRef.id).await()
        return docRef.id
    }

    /**
     * Retrieves a list of blog posts from Firestore.
     *
     * @return A list of BlogPost objects.
     */
    suspend fun getBlogPosts(): List<BlogPost> {
        return try {
            db.collection("blog").get().await().toObjects(BlogPost::class.java).sortedBy { it.id }
        } catch (e: Exception) {
            emptyList()
        }
    }

    /**
     * Creates a new chat document in Firestore with an initial message.
     *
     * @return The ID of the newly created chat document.
     */
    suspend fun createChat(): String {
        val message: ChatMessage = ChatMessage(true, "Hello, how can I help you?", Timestamp.now())
        val docRef = db.collection("messages").add(message).await()
        db.collection("messages").document(docRef.id).update("id", docRef.id).await()
        val chat = FullChat("", message.message, message.timestamp, listOf(docRef.id))
        val chatRef = db.collection("chats").add(chat).await()
        db.collection("chats").document(chatRef.id).update("id", chatRef.id).await()
        return chatRef.id
    }

    /**
     * Retrieves a list of chat messages from Firestore based on their IDs.
     *
     * @param messageIds The list of message IDs.
     * @return A list of ChatMessage objects.
     */
    suspend fun getChatMessages(messageIds: List<String>): List<ChatMessage> {
        return try {
            db.collection("messages").whereIn("id", messageIds).get().await().toObjects(ChatMessage::class.java)
        } catch (e: Exception) {
            emptyList()
        }
    }

    /**
     * Retrieves a list of user chats from Firestore based on their IDs.
     *
     * @param chatIds The list of chat IDs.
     * @return A list of FullChat objects.
     */
    suspend fun getUserChats(chatIds: List<String>): List<FullChat> {
        return try {
            db.collection("chats").whereIn("id", chatIds).get().await().toObjects(FullChat::class.java)
        } catch (e: Exception) {
            emptyList()
        }
    }
}