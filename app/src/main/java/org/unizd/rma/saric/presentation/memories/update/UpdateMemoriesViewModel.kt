package org.unizd.rma.saric.presentation.memories.update

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.unizd.rma.saric.domain.interfaces.usecases.UpdateMemoriesUseCase
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class UpdateMemoriesViewModel @Inject constructor(
    private val updateMemoriesUseCase: UpdateMemoriesUseCase
): ViewModel() {
    private val _errorMessage = mutableStateOf("")
    private val _city = mutableStateOf("")
    private val _stars = mutableStateOf("5")
    private val _country = mutableStateOf("")
    private val calendar = Calendar.getInstance()
    private val _date = mutableStateOf(formatDate(calendar.time))
    private val _imageUri = mutableStateOf("")

    val city : String
        get() = _city.value

    val stars: String
        get() = _stars.value

    val country: String
        get() = _country.value

    val date: String
        get() = _date.value

    val imageUri: String
        get() = _imageUri.value

    val errorMessage: String
        get() = _errorMessage.value

    fun onCityChange(newCity: String) {
        _city.value = newCity
    }

    fun onStarsChange(newStars: String){
        _stars.value = newStars
    }

    fun onCountryChange(newCountry: String) {
        _country.value = newCountry
    }

    fun onDateChange(newDate: String) {
        _date.value = newDate
    }

    fun onImageUriChange(newImageUri: String) {
        _imageUri.value = newImageUri
    }



    suspend fun updateMemories(id: String) {
        val ide = id.toInt()
       try {
           updateMemoriesUseCase.execute(ide, _city.value, _stars.value.toInt(), _country.value, _date.value, _imageUri.value)
       } catch (e: Exception) {
           _errorMessage.value = "Error ${e.message}"
       }
    }
}

private fun formatDate(date: Date): String {
    val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return simpleDateFormat.format(date)
}
