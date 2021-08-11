package com.hsicen.navigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navDeepLink
import com.hsicen.navigation.data.UserData
import com.hsicen.navigation.ui.accounts.AccountsBody
import com.hsicen.navigation.ui.accounts.SingleAccountBody
import com.hsicen.navigation.ui.bills.BillsBody
import com.hsicen.navigation.ui.components.RallyTabRow
import com.hsicen.navigation.ui.overview.OverviewBody
import com.hsicen.navigation.ui.theme.RallyTheme


class RallyActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RallyApp()
        }
    }
}

@Composable
fun RallyApp() {
    RallyTheme {
        val allScreens = RallyScreen.values().toList()
        val navController = rememberNavController()
        val backstackEntry = navController.currentBackStackEntryAsState()
        val currentScreen = RallyScreen.fromRoute(backstackEntry.value?.destination?.route)

        Scaffold(
            topBar = {
                RallyTabRow(
                    allScreens = allScreens,
                    onTabSelected = { screen ->
                        navController.navigate(screen.name)
                    },
                    currentScreen = currentScreen
                )
            }
        ) { innerPadding ->
            RallyNavHost(navController, Modifier.padding(innerPadding))
        }
    }
}

@Composable
fun RallyNavHost(navController: NavHostController, modifier: Modifier = Modifier) {
    val accountsName = RallyScreen.Accounts.name
    NavHost(
        navController = navController,
        startDestination = RallyScreen.Overview.name,
        modifier = modifier
    ) {

        composable(RallyScreen.Overview.name) {
            OverviewBody(onClickSeeAllAccounts = {
                navController.navigate(RallyScreen.Accounts.name)
            }, onClickSeeAllBills = {
                navController.navigate(RallyScreen.Bills.name)
            }, onAccountClick = {
                navController.navigate("$accountsName/$it")
            })
        }

        composable(RallyScreen.Accounts.name) {
            AccountsBody(accounts = UserData.accounts) {
                navController.navigate("$accountsName/$it")
            }
        }

        composable(RallyScreen.Bills.name) {
            BillsBody(bills = UserData.bills)
        }

        composable(
            route = "$accountsName/{name}",
            arguments = listOf(
                navArgument("name") {
                    type = NavType.StringType
                }
            ),
            deepLinks = listOf(navDeepLink {
                uriPattern = "rally://$accountsName/{name}"
            })
        ) { entry ->
            val accountName = entry.arguments?.getString("name")
            val userAccount = UserData.getAccount(accountName)
            SingleAccountBody(account = userAccount)
        }
    }
}
