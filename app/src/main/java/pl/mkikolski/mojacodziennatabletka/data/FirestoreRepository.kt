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
        return db.collection("medications").whereIn("id", medicationIds).get().await().toObjects(Medication::class.java)
    }
}