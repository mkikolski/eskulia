package pl.mkikolski.mojacodziennatabletka.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UserViewModel(private val repository: FirestoreRepository) : ViewModel() {
    private val _userState = MutableStateFlow<User?>(null)
    val userState = _userState.asStateFlow()

    private val _userMedicationsState = MutableStateFlow<List<Medication>>(emptyList())
    val userMedicationsState = _userMedicationsState.asStateFlow()

    private val _blogPosts = MutableStateFlow<List<BlogPost>>(emptyList())
    val blogPosts = _blogPosts.asStateFlow()

    private val _chats = MutableStateFlow<List<FullChat>>(emptyList())
    val chats = _chats.asStateFlow()

    fun getUser(uid: String) {
        viewModelScope.launch {
            _userState.value = repository.getUser(uid)
            _userMedicationsState.value = repository.getMedications(_userState.value?.medicationIds ?: emptyList())
            _blogPosts.value = repository.getBlogPosts()
            _chats.value = repository.getUserChats(_userState.value?.chatIds ?: emptyList())
        }
    }

    fun updateUser(user: User) {
        viewModelScope.launch {
            repository.updateUser(user)
            _userState.value = user
        }
    }
}