package com.example.birthdaywishesapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.birthdaywishesapp.data.repository.BirthdayRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class BirthdayViewModel : ViewModel() {

    private val repository = BirthdayRepository()

    private val _pages = MutableStateFlow(repository.getPages())
    val pages: StateFlow<List<com.example.birthdaywishesapp.data.model.BirthdayPage>> = _pages

    private val _currentPage = MutableStateFlow(0)
    val currentPage: StateFlow<Int> = _currentPage

   //private val _musicType = MutableStateFlow(MusicType.NONE)
    private val _musicType = MutableStateFlow(MusicType.BACKGROUND)

    val musicType: StateFlow<MusicType> = _musicType

    fun onPageChanged(page: Int) {
        _currentPage.value = page

        _musicType.value = when (page) {
            4 -> MusicType.CELEBRATION   // Page 5
            in 0..3 -> MusicType.BACKGROUND
            else -> MusicType.NONE
        }
    }
}
