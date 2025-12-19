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
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.example.birthdaywishesapp.viewmodel.BirthdayViewModel
import kotlinx.coroutines.delay

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BirthdayPager(viewModel: BirthdayViewModel) {

    val pages by viewModel.pages.collectAsState()
    val pagerState = rememberPagerState { pages.size }

    /* ✅ Lifecycle awareness (MOVED UP) */
    val lifecycleOwner = LocalLifecycleOwner.current
    var isForeground by remember { mutableStateOf(true) }

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            isForeground = when (event) {
                Lifecycle.Event.ON_START -> true
                Lifecycle.Event.ON_STOP -> false
                else -> isForeground
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    /* ⏱ Auto-slide every 5 seconds (LOGIC SAME) */
    LaunchedEffect(pages.size, isForeground) {
        while (isForeground) {

            // ⏸ Pause on last page (do NOT exit)
            if (pagerState.currentPage == pages.lastIndex) {
                delay(500)
                continue
            }

            delay(5000)

            // Move forward safely (NO looping)
            val nextPage = pagerState.currentPage + 1
            pagerState.animateScrollToPage(nextPage)
        }
    }

    /* 📡 Notify ViewModel on page change */
    LaunchedEffect(pagerState.currentPage) {
        viewModel.onPageChanged(pagerState.currentPage)
    }

    /* 🎬 UI */
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
