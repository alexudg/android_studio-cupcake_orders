/***
 * author: Alejandro Ramirez Macias
 * email: alexudg@gmail.com
 * facebook: https://facebook.com/planetsistemas
 * date: april 2024
 * ide: Android Studio 2023.1.1
 * jdk: 11.0.20
 * android sdk: 14.0 (34)
 */

package com.planetsistemas.cupcakeorders

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.planetsistemas.cupcakeorders.ui.theme.Android_Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Android_Theme {
                GlobalScreen()
            }
        }
    }
}