package org.unizd.rma.saric

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import dagger.hilt.android.AndroidEntryPoint
import org.unizd.rma.saric.presentation.memories.create.CreateMemoriesScreen
import org.unizd.rma.saric.presentation.memories.create.CreateMemoriesViewModel
import org.unizd.rma.saric.presentation.memories.details.DetailsMemoriesScreen
import org.unizd.rma.saric.presentation.memories.details.DetailsMemoriesViewModel
import org.unizd.rma.saric.presentation.memories.list.ListMemoriesScreen
import org.unizd.rma.saric.presentation.memories.list.ListMemoriesViewModel
import org.unizd.rma.saric.presentation.memories.update.UpdateMemoriesScreen
import org.unizd.rma.saric.presentation.memories.update.UpdateMemoriesViewModel
import org.unizd.rma.saric.ui.theme.MemoriesTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MemoriesTheme {
                val navController = rememberNavController()
                Router(navController = navController)
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MemoriesTheme {
        Greeting("Android")
    }
}

@Composable
fun Router(navController: NavHostController) {
    val listMemoriesViewModel: ListMemoriesViewModel = hiltViewModel()
    val detailsMemoriesViewModel: DetailsMemoriesViewModel = hiltViewModel()
    val createMemoriesViewModel: CreateMemoriesViewModel = hiltViewModel()
    val updateMemoriesViewModel: UpdateMemoriesViewModel = hiltViewModel()

    NavHost(navController = navController, startDestination = "list") {

        composable("list") {
            ListMemoriesScreen(navController = navController, listMemoriesViewModel = listMemoriesViewModel)
        }

        composable("create") {
            CreateMemoriesScreen(navController = navController, createMemoriesViewModel)
        }

        composable("details/{id}") { backStackEntry ->
            val itemId = backStackEntry.arguments?.getString("id")
            if (itemId != null) {
                DetailsMemoriesScreen(navController = navController, detailsMemoriesViewModel = detailsMemoriesViewModel , id = itemId)
            }
        }

        // Fixed route
        composable("update/{id}") { backStackEntry ->
            val itemId = backStackEntry.arguments?.getString("id")
            if (itemId != null) {
                UpdateMemoriesScreen(navController = navController, updateMemoriesViewModel, itemId)
            }
        }
    }
}




