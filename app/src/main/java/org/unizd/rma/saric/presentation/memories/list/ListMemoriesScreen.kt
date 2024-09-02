package org.unizd.rma.saric.presentation.memories.list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.runBlocking

@Composable
fun ListMemoriesScreen(
    navController: NavController,
    listMemoriesViewModel: ListMemoriesViewModel = hiltViewModel<ListMemoriesViewModel>()
) {
    LaunchedEffect(Unit) {
        listMemoriesViewModel.getMemories()
    }

    var showDialog by remember { mutableStateOf(false) }
    var memoryToDelete by remember { mutableStateOf<String?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "List of memories")
                },
                actions = {
                    Spacer(modifier = Modifier.width(10.dp))
                    Button(onClick = {
                        navController.navigate("create")
                    }) {
                        Text(text = "New")
                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .horizontalScroll(rememberScrollState())
        ) {
            LazyColumn(modifier = Modifier.fillMaxHeight()) {
                var num = 1
                items(listMemoriesViewModel.memories) { item ->
                    Box(
                        modifier = Modifier
                            .padding(5.dp)
                            .background(if (num % 2 == 0) Color.DarkGray else Color.Gray)
                            .clickable { /* Handle row click if needed */ }
                    ) {
                        Row {
                            Text(text = num.toString())
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(text = item.city)
                            Spacer(modifier = Modifier.width(10.dp))

                            Button(onClick = {
                                navController.navigate("details/${item.id}")
                            }) {
                                Text(text = "See details")
                            }

                            Spacer(modifier = Modifier.width(10.dp))

                            Button(onClick = {
                                memoryToDelete = item.id
                                showDialog = true
                            }) {
                                Text(text = "Delete")
                            }

                            Spacer(modifier = Modifier.width(10.dp))

                            Button(onClick = {
                                navController.navigate("update/${item.id}")
                            }) {
                                Text(text = "Update")
                            }
                        }
                    }
                    num += 1
                }
            }
        }
    }

    // Delete Confirmation Dialog
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text(text = "Delete Memory") },
            text = { Text(text = "Are you sure you want to delete this memory?") },
            confirmButton = {
                Button(
                    onClick = {
                        memoryToDelete?.let {
                            runBlocking {
                                listMemoriesViewModel.deleteMemoriesById(it)
                                navController.navigate("list") // Refresh the list screen
                            }
                        }
                        showDialog = false // Close the dialog
                    }
                ) {
                    Text("Yes")
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        showDialog = false // Close the dialog without deleting
                    }
                ) {
                    Text("No")
                }
            }
        )
    }
}
