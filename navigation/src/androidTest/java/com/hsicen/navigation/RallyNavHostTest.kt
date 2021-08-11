package com.hsicen.navigation

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * 作者：hsicen  8/11/21 17:12
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：ComposeDemo
 */
class RallyNavHostTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    lateinit var navController: NavHostController


    @Before
    fun setupRallyNavHost() {
        composeTestRule.setContent {
            navController = rememberNavController()
            RallyNavHost(navController)
        }
    }

    @Test
    fun rallyNavHost() {
        composeTestRule
            .onNodeWithContentDescription("Overview Screen")
            .assertIsDisplayed()
    }

}