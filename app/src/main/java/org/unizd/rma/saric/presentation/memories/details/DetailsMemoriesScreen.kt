package org.unizd.rma.saric.presentation.memories.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import org.unizd.rma.saric.domain.models.MemoriesResponseEntity


@Composable
fun DetailsMemoriesScreen(
    navController: NavController,
    detailsMemoriesViewModel: DetailsMemoriesViewModel,
    id: String
) {

    LaunchedEffect(Unit,
        block = {
            detailsMemoriesViewModel.loadMemoriesDetails(id)
        })
    var memories: MemoriesResponseEntity? = detailsMemoriesViewModel.memories


    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Details of memory")
                },
                actions = {
                    Button(onClick = {
                        navController.popBackStack()
                    }) {
                        Text(text = "Back")
                    }
                }
            )
        }
    ) {
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ){
            Text(text = "Name of city")
            Spacer(modifier = Modifier.width(16.dp))
            Text(memories!!.city)
            Spacer(modifier = Modifier.width(16.dp))
            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "Name of country")
            Spacer(modifier = Modifier.width(16.dp))
            Text(memories.country.toString())
            Spacer(modifier = Modifier.width(16.dp))
            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "Stars")
            Spacer(modifier = Modifier.width(16.dp))
            Text(memories.stars.toString())
            Spacer(modifier = Modifier.width(16.dp))
            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "Date")
            Spacer(modifier = Modifier.width(16.dp))
            Text(memories.date)
            Spacer(modifier = Modifier.width(16.dp))
            Spacer(modifier = Modifier.height(16.dp))

            Spacer(modifier = Modifier.width(16.dp))

            if (memories.imageUri.path?.isNotEmpty() == true) {
                Image(
                    modifier = Modifier.padding(16.dp, 16.dp),
                    painter = rememberImagePainter(memories.imageUri),
                    contentDescription = "Image")
            } else {
                Image(
                    modifier = Modifier.padding(16.dp, 16.dp),
                    painter = painterResource(id = org.unizd.rma.saric.R.drawable.ic_image),
                    contentDescription = "Image")

            }

        }
    }
}