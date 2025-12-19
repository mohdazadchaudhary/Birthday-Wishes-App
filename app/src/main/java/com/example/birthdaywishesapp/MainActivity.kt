package com.example.birthdaywishesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.birthdaywishesapp.ui.pager.BirthdayPager
import com.example.birthdaywishesapp.ui.theme.BirthdayWishesAppTheme
import com.example.birthdaywishesapp.ui.util.MusicPlayer
import com.example.birthdaywishesapp.viewmodel.BirthdayViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.compose.ui.platform.LocalLifecycleOwner


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            BirthdayWishesAppTheme {

                val viewModel: BirthdayViewModel = viewModel()
                val musicType by viewModel.musicType.collectAsState()
                val musicPlayer = remember { MusicPlayer(this) }

                val lifecycleOwner = LocalLifecycleOwner.current

                // 🎯 Lifecycle observer
                DisposableEffect(lifecycleOwner) {
                    val observer = LifecycleEventObserver { _, event ->
                        when (event) {
                            Lifecycle.Event.ON_STOP -> {
                                // App goes to background
                                musicPlayer.stop()
                            }
                            Lifecycle.Event.ON_START -> {
                                // App comes to foreground
                                musicPlayer.play(musicType)
                            }
                            else -> {}
                        }
                    }

                    lifecycleOwner.lifecycle.addObserver(observer)

                    onDispose {
                        lifecycleOwner.lifecycle.removeObserver(observer)
                        musicPlayer.stop()
                    }
                }

                // React to music changes (page based)
                DisposableEffect(musicType) {
                    musicPlayer.play(musicType)
                    onDispose { }
                }

                BirthdayPager(viewModel = viewModel)
            }
        }
    }
}
