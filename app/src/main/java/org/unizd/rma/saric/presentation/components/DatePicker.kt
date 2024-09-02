package org.unizd.rma.saric.presentation.components

import android.app.DatePickerDialog
import android.content.Context
import android.widget.DatePicker
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.unit.dp
import org.unizd.rma.saric.presentation.memories.create.CreateMemoriesViewModel
import java.text.SimpleDateFormat


import java.util.*
@Composable
fun showDatePicker(context: Context, createMemoriesViewModel: CreateMemoriesViewModel) {

    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    val initialDate = remember { mutableStateOf(formatDate(calendar.time)) }
    val date = remember { mutableStateOf(initialDate.value) }


    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            val formattedDate = "$dayOfMonth/${month + 1}/$year"
            date.value = formattedDate
            println(formattedDate)
            createMemoriesViewModel.onDateChange(formattedDate)
        }, year, month, day
    )

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = "Selected Date: ${date.value}")
        Spacer(modifier = Modifier.size(16.dp))
        Button(onClick = {
            datePickerDialog.show()
        }) {
            Text(text = "Open Date Picker")
        }
    }
}

// Function to format the date as needed
private fun formatDate(date: Date): String {
    val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return simpleDateFormat.format(date)
}