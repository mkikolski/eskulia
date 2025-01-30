package pl.mkikolski.mojacodziennatabletka.data

import com.google.firebase.Firebase
import com.google.firebase.Timestamp
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await

class FirestoreRepository {
    private val db: FirebaseFirestore = Firebase.firestore

    suspend fun getUser(uid: String): User? {
        return db.collection("users").document(uid).get().await().toObject(User::class.java)
    }

    suspend fun updateUser(user: User) {
        db.collection("users").document(user.uid).set(user).await()
    }

    suspend fun getMedications(medicationIds: List<String>): List<Medication> {
        return try {
            db.collection("medications").whereIn("id", medicationIds).get().await().toObjects(Medication::class.java)
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun getBlogPosts(): List<BlogPost> {
        return try {
            db.collection("blog").get().await().toObjects(BlogPost::class.java).sortedBy { it.id }
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun createChat(): String {
        val message: ChatMessage = ChatMessage(true, "Hello, how can I help you?", Timestamp.now())
        val docRef = db.collection("messages").add(message).await()
        db.collection("messages").document(docRef.id).update("id", docRef.id).await()
        val chat = FullChat("", message.message, message.timestamp, listOf(docRef.id))
        val chatRef = db.collection("chats").add(chat).await()
        db.collection("chats").document(chatRef.id).update("id", chatRef.id).await()
        return chatRef.id
    }

    suspend fun getChatMessages(messageIds: List<String>): List<ChatMessage> {
        return try {
            db.collection("messages").whereIn("id", messageIds).get().await().toObjects(ChatMessage::class.java)
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun getUserChats(chatIds: List<String>): List<FullChat> {
        return try {
            db.collection("chats").whereIn("id", chatIds).get().await().toObjects(FullChat::class.java)
        } catch (e: Exception) {
            emptyList()
        }
    }
}