package com.hsicen.theme.ui.finish

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Surface

/**
 * This activity demonstrates the final state of the codelab. You can run it from the included
 * run configuration.
 */
class FinishActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface {
                Home()
            }
        }
    }
}
