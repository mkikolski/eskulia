package pl.mkikolski.mojacodziennatabletka.data

import android.os.Bundle
import android.util.Log
import androidx.compose.runtime.saveable.Saver
import androidx.core.net.toUri
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.storage
import kotlinx.coroutines.tasks.await
import kotlinx.serialization.Serializable

/**
 * Data class representing user registration data collected during registration flow.
 *
 * @property email The email address of the user.
 * @property password The password of the user.
 * @property username The username of the user.
 * @property age The age of the user.
 * @property reasons The reasons for registration.
 * @property usesBuiltinAvatar Indicates if the user uses a built-in avatar.
 * @property avatarUrl The URL of the user's avatar.
 */
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
    /**
     * Creates a Firebase user with the provided authentication provider.
     *
     * @param authProvider The Firebase authentication provider.
     * @return The UID of the created user, or null if creation failed.
     */
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

    /**
     * Creates an empty user data document in Firestore.
     *
     * @param db The Firestore database instance.
     * @param uid The UID of the user.
     */
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

    /**
     * Uploads the user's profile picture to Firebase Storage.
     *
     * @param uid The UID of the user.
     * @return The download URL of the uploaded profile picture.
     */
    suspend fun uploadProfilePictureToFirebaseStorage(uid: String): String {
        val storage = Firebase.storage.reference
        val fileName = "profile_pictures/$uid.jpg"
        val imageRef = storage.child(fileName)

        val task = imageRef.putFile(avatarUrl.toUri())
        task.await()
        return imageRef.downloadUrl.await().toString()
    }
}

/**
 * Saver object for UserRegistrationData.
 */
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

