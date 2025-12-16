package com.example.birthdaywishesapp.ui.pager

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.birthdaywishesapp.viewmodel.BirthdayViewModel
import kotlinx.coroutines.delay

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BirthdayPager(viewModel: BirthdayViewModel) {

    val pages by viewModel.pages.collectAsState()
    val pagerState = rememberPagerState { pages.size }


    /*  Auto-slide every 5 seconds */
    LaunchedEffect(pages.size) {
        while (true) {

            // ⏸ Pause on last page (do NOT exit)
            if (pagerState.currentPage == pages.lastIndex) {
                delay(500) // small idle delay to avoid busy loop
                continue
            }

            delay(5000)

            // Move forward safely (NO looping)
            val nextPage = pagerState.currentPage + 1
            pagerState.animateScrollToPage(nextPage)
        }
    }


    /*  VERY IMPORTANT: notify ViewModel on page change */
    LaunchedEffect(pagerState.currentPage) {
        viewModel.onPageChanged(pagerState.currentPage)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { index ->
            Image(
                painter = painterResource(id = pages[index].imageRes),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }
    }
}
