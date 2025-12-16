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

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            BirthdayWishesAppTheme {

                //  ViewModel
                val viewModel: BirthdayViewModel = viewModel()

                // Observe music state
                val musicType by viewModel.musicType.collectAsState()

                //  Music player (remembered once)
                val musicPlayer = remember { MusicPlayer(this) }

                //  React to music changes
                DisposableEffect(musicType) {
                    musicPlayer.play(musicType)
                    onDispose { }
                }

                //  Cleanup on app exit
                DisposableEffect(Unit) {
                    onDispose {
                        musicPlayer.stop()
                    }
                }

                //  UI
                BirthdayPager(viewModel = viewModel)
            }
        }
    }
}
