package pl.mkikolski.mojacodziennatabletka.data

import com.google.firebase.Firebase
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

    suspend fun getChatMessages(chatId: String): List<ChatMessage> {
        return try {
            db.collection("chats").document(chatId).collection("messages").get().await().toObjects(ChatMessage::class.java)
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