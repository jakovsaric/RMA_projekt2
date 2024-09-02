package org.unizd.rma.saric.presentation.components

import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.ExposedDropdownMenuDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import org.unizd.rma.saric.presentation.memories.update.UpdateMemoriesViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DropdownMenuUpdate(updateMemoriesViewModel: UpdateMemoriesViewModel) {
    var isExpanded by remember {
        mutableStateOf(false)
    }



    ExposedDropdownMenuBox(
        expanded = isExpanded,
        onExpandedChange = { isExpanded = it }) {

        TextField(
            value = updateMemoriesViewModel.stars,
            onValueChange = {
                // This can be left empty, as the value is managed by the ViewModel
            },
            readOnly = true,
            colors = ExposedDropdownMenuDefaults.textFieldColors(),
        )

        ExposedDropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false }) {

            listOf("1", "2", "3", "4", "5").forEach { category ->
                DropdownMenuItem(
                    onClick = {
                        updateMemoriesViewModel.onStarsChange(category)
                        isExpanded = false
                    }) {

                    Text(text = category)
                }
            }
        }
    }
}