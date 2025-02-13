package pl.mkikolski.mojacodziennatabletka.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel class for managing user-related data and operations.
 *
 * @property repository The Firestore repository for accessing user data.
 */
class UserViewModel(private val repository: FirestoreRepository) : ViewModel() {
    private val _userState = MutableStateFlow<User?>(null)
    val userState = _userState.asStateFlow()

    private val _userMedicationsState = MutableStateFlow<List<Medication>>(emptyList())
    val userMedicationsState = _userMedicationsState.asStateFlow()

    private val _blogPosts = MutableStateFlow<List<BlogPost>>(emptyList())
    val blogPosts = _blogPosts.asStateFlow()

    private val _chats = MutableStateFlow<List<FullChat>>(emptyList())
    val chats = _chats.asStateFlow()

    private val _chatMessages = MutableStateFlow<List<ChatMessage>>(emptyList())
    val chatMessages = _chatMessages.asStateFlow()

    /**
     * Retrieves user data and associated information from the repository.
     *
     * @param uid The unique identifier of the user.
     */
    fun getUser(uid: String) {
        viewModelScope.launch {
            _userState.value = repository.getUser(uid)
            _userMedicationsState.value = repository.getMedications(_userState.value?.medicationIds ?: emptyList())
            _blogPosts.value = repository.getBlogPosts()
            _chats.value = repository.getUserChats(_userState.value?.chatIds ?: emptyList())
        }
    }

    /**
     * Adds a new medication for the user and navigates to the home screen.
     *
     * @param medication The medication to be added.
     * @param navController The navigation controller for navigating between screens.
     */
    fun addMedication(medication: Medication, navController: NavHostController) {
        viewModelScope.launch {
            val medId = repository.addMedication(medication)
            val updatedUser = _userState.value?.copy(medicationIds = _userState.value?.medicationIds?.plus(medId) ?: listOf(medId))
            repository.updateUser(updatedUser!!)
            _userMedicationsState.value = repository.getMedications(_userState.value?.medicationIds ?: emptyList())
            navController.navigate("home")
        }
    }

    /**
     * Creates a new chat for the user and navigates to the chat detail screen.
     *
     * @param navController The navigation controller for navigating between screens.
     */
    fun addChat(navController: NavHostController) {
        viewModelScope.launch {
            val chatId = repository.createChat()
            val updatedUser = _userState.value?.copy(chatIds = _userState.value?.chatIds?.plus(chatId) ?: listOf(chatId))
            repository.updateUser(updatedUser!!)
            _chats.value = repository.getUserChats(updatedUser.chatIds)
            navController.navigate("chat_detail/$chatId")
        }
    }

    /**
     * Retrieves chat messages based on the provided message IDs.
     *
     * @param msgIds The list of message IDs to retrieve.
     */
    fun getMessages(msgIds: List<String>) {
        viewModelScope.launch {
            _chatMessages.value = repository.getChatMessages(msgIds)
        }
    }

    /**
     * Updates the user data in the repository.
     *
     * @param user The user data to be updated.
     */
    fun updateUser(user: User) {
        viewModelScope.launch {
            repository.updateUser(user)
            _userState.value = user
        }
    }
}