package pl.mkikolski.mojacodziennatabletka.data

import android.os.Bundle
import android.util.Log
import androidx.compose.runtime.saveable.Saver
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import kotlinx.serialization.Serializable

@Serializable
data class UserRegistrationData(
    val email: String,
    val password: String,
    val username: String = "",
    val age: Int = 25,
    val reasons: List<String> = listOf(),
    val usesBuiltinAvatar: Boolean = true,
    val avatarUrl: String = "R.drawable.avatar_1"
) {
    suspend fun createFirebaseUser(authProvider: FirebaseAuth): String? {
        try {
            authProvider.createUserWithEmailAndPassword(email, password).await()
            Log.d("UserRegistrationData", "User created successfully")
            return authProvider.currentUser?.uid
        } catch (e: Exception) {
            Log.d("UserRegistrationData", "User creation failed: ${e.message}")
            return null
        }
    }

    suspend fun createEmptyUserData(db: FirebaseFirestore, uid: String) {
        try {
            db.collection("users").document(uid).set(
                User(
                    uid = uid,
                    email = email,
                    username = username,
                    age = age,
                    medicationIds = listOf(),
                    chatIds = listOf(),
                    avatarUrl = avatarUrl,
                    notificationIds = listOf()
                )
            ).await()
        } catch (e: Exception) {
            Log.d("UserRegistrationData", "User data creation failed: ${e.message}")
        }
    }
}

val UserRegistrationDataSaver: Saver<UserRegistrationData, Bundle> = Saver(
    save = {
        Bundle().apply {
            putString("email", it.email)
            putString("password", it.password)
            putString("username", it.username)
            putInt("age", it.age)
            putStringArray("reasons", it.reasons.toTypedArray())
            putBoolean("usesBuiltinAvatar", it.usesBuiltinAvatar)
            putString("avatarUrl", it.avatarUrl)
        }
    },
    restore = {
        UserRegistrationData(
            email = it.getString("email")!!,
            password = it.getString("password")!!,
            username = it.getString("username")!!,
            age = it.getInt("age"),
            reasons = it.getStringArray("reasons")!!.toList(),
            usesBuiltinAvatar = it.getBoolean("usesBuiltinAvatar"),
            avatarUrl = it.getString("avatarUrl")!!
        )
    }
)

