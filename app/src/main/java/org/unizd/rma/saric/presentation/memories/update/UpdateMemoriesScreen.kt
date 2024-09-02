package org.unizd.rma.saric.presentation.memories.update

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import kotlinx.coroutines.runBlocking
import org.unizd.rma.saric.R
import org.unizd.rma.saric.presentation.components.DropdownMenuUpdate
import org.unizd.rma.saric.presentation.components.TextInput
import org.unizd.rma.saric.presentation.components.showDatePickerUpdate
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Objects

@Composable
fun UpdateMemoriesScreen(
    navController: NavController,
    updateMemoriesViewModel: UpdateMemoriesViewModel = hiltViewModel(),
    id: String
) {
    Log.d("UpdateScreen", "Update")

    val context = LocalContext.current

    val memories = LocalContext.current
    var file = memories.createImageFile()
    val uri = FileProvider.getUriForFile(
        Objects.requireNonNull(memories),
        memories.packageName + ".provider",
        file
    )

    var capturedImageUri by remember {
        mutableStateOf<Uri>(Uri.EMPTY)
    }

    val cameraLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.TakePicture()) {
        Log.d("CreateContactScreen", "CameraLauncher $it")
        capturedImageUri = uri
        updateMemoriesViewModel.onImageUriChange(capturedImageUri.toString())
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()) {

        if(it) {
            Toast.makeText(memories, "Permission granted", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(memories, "Permission denied", Toast.LENGTH_SHORT).show()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Update memory")
                },
                actions = {
                    Button(onClick = {
                        runBlocking {
                            updateMemoriesViewModel.updateMemories(id)
                            navController.popBackStack()
                        }
                    }) {
                        Text(text = "Update")
                    }
                }
            )
        }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {

            Text(text = "Name of city")
            Spacer(modifier = Modifier.width(16.dp))
            TextInput(
                value = updateMemoriesViewModel.city,
                onChange = { value ->
                    updateMemoriesViewModel.onCityChange(value)
                },
                placeholder = "Enter city name"
            )

            Spacer(modifier = Modifier.width(16.dp))
            Spacer(modifier = Modifier.height(25.dp))

            Text(text = "Country")
            Spacer(modifier = Modifier.width(16.dp))

            TextInput(
                value = updateMemoriesViewModel.country,
                onChange = { value ->
                    updateMemoriesViewModel.onCountryChange(value)
                },
                placeholder = "Enter country name"
            )

            Spacer(modifier = Modifier.width(16.dp))
            Spacer(modifier = Modifier.height(25.dp))

            Text(text = "Choose rating")
            Spacer(modifier = Modifier.width(16.dp))

            DropdownMenuUpdate(updateMemoriesViewModel = updateMemoriesViewModel)

            Spacer(modifier = Modifier.width(16.dp))
            Spacer(modifier = Modifier.height(25.dp))

            showDatePickerUpdate(context = context, updateMemoriesViewModel = updateMemoriesViewModel)

            Spacer(modifier = Modifier.width(16.dp))
            Spacer(modifier = Modifier.height(25.dp))

            Button(onClick = {
                val permissionCheckResult = ContextCompat.checkSelfPermission(
                    memories,
                    Manifest.permission.CAMERA
                )

                if (permissionCheckResult == PackageManager.PERMISSION_GRANTED) {
                    cameraLauncher.launch(uri)
                } else {
                    permissionLauncher.launch(Manifest.permission.CAMERA)
                }
            }) {
                Text(text = "Add image")
            }

            Spacer(modifier = Modifier.width(16.dp))

            if (capturedImageUri.path?.isNotEmpty() == true) {
                Image(
                    modifier = Modifier.padding(16.dp, 16.dp),
                    painter = rememberImagePainter(capturedImageUri),
                    contentDescription = "Image"
                )
            } else {
                Image(
                    modifier = Modifier.padding(16.dp, 16.dp),
                    painter = painterResource(id = R.drawable.ic_image),
                    contentDescription = "Image"
                )
            }
        }
    }
}

fun Context.createImageFile(): File {
    val timeStamp = SimpleDateFormat("yyyy_MM_dd_HH-mm-ss").format(Date())
    val imageFileName = "image" + timeStamp + "_"
    return File.createTempFile(
        imageFileName,
        ".jpg",
        externalCacheDir
    )
}
