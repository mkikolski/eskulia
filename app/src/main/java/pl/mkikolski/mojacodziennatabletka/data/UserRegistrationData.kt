package pl.mkikolski.mojacodziennatabletka.data

import android.os.Bundle
import androidx.compose.runtime.saveable.Saver
import kotlinx.serialization.Serializable

@Serializable
data class UserRegistrationData(
    val email: String,
    val password: String,
    val age: Int = 25,
    val reasons: List<String> = listOf(),
    val usesBuiltinAvatar: Boolean = true,
    val avatarUrl: String = "R.drawable.avatar_1"
)

val UserRegistrationDataSaver: Saver<UserRegistrationData, Bundle> = Saver(
    save = {
        Bundle().apply {
            putString("email", it.email)
            putString("password", it.password)
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
            age = it.getInt("age"),
            reasons = it.getStringArray("reasons")!!.toList(),
            usesBuiltinAvatar = it.getBoolean("usesBuiltinAvatar"),
            avatarUrl = it.getString("avatarUrl")!!
        )
    }
)

