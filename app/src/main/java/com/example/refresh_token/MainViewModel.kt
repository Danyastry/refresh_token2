package com.example.refresh_token

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: Repository
) : ViewModel() {

    private val _newData = MutableStateFlow<ResponseModel?>(null)
    val newData: StateFlow<ResponseModel?> get() = _newData

    fun getSomeData() {
        viewModelScope.launch {
            repository.getSomeData()
                .onEach {
                    _newData.value = it
                }.catch {
                    _newData.value = ResponseModel(
                        message = it.message
                    )
                }.collect()
        }
    }
}