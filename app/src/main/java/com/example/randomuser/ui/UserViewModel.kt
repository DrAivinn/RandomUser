package com.example.randomuser.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.randomuser.domain.models.User
import com.example.randomuser.domain.usecase.GetAllUsersUseCase
import com.example.randomuser.domain.usecase.GetRandomUserUseCase
import com.example.randomuser.domain.usecase.GetUserByIdUseCase
import com.example.randomuser.ui.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch

@HiltViewModel
class UserViewModel @Inject constructor(
    private val getRandomUserUseCase: GetRandomUserUseCase,
    private val getAllUsersUseCase: GetAllUsersUseCase,
    private val getUserByIdUseCase: GetUserByIdUseCase
) :
    ViewModel() {

    private val _users = MutableStateFlow<List<User>>(emptyList())
    val users: StateFlow<List<User>> = _users

    private val _currentUser = MutableStateFlow<User?>(null)
    val currentUser: StateFlow<User?> = _currentUser


    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    fun loadUsers() {
        _uiState.value = UiState.Loading
        viewModelScope.launch {
            try {
                val usersList = getAllUsersUseCase()
                _users.value = usersList
                _uiState.value = UiState.Success
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.localizedMessage ?: "Ошибка при загрузке данных")
            }
        }
    }


    fun getRandomUser(gender: String, nationality: String) {
        _uiState.value = UiState.Loading
        viewModelScope.launch {
            try {
                getRandomUserUseCase(gender, nationality)
                _uiState.value = UiState.Success
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.localizedMessage ?: "Unknown error")
            }
        }
    }

    fun getUserById(userId: Int) {
        _uiState.value = UiState.Loading
        viewModelScope.launch {
            try {
                val user = getUserByIdUseCase(userId)
                _currentUser.value = user
                _uiState.value = UiState.Success
            } catch (e: Exception) {
                _uiState.value = UiState.Error(
                    e.localizedMessage ?: "Unknown error"
                )
            }
        }
    }
}