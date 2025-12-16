package com.example.birthdaywishesapp.data.repository

import com.example.birthdaywishesapp.R
import com.example.birthdaywishesapp.data.model.BirthdayPage

class BirthdayRepository {

    fun getPages(): List<BirthdayPage> = listOf(
        BirthdayPage(0, R.drawable.page1),
        BirthdayPage(1, R.drawable.page2),
        BirthdayPage(2, R.drawable.page3),
        BirthdayPage(3, R.drawable.page4),
        BirthdayPage(4, R.drawable.page5)
    )
}
