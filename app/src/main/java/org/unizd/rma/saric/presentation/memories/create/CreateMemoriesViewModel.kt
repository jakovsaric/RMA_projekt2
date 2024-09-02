package org.unizd.rma.saric.presentation.memories.create

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.unizd.rma.saric.domain.interfaces.usecases.CreateMemoriesUseCase
import org.unizd.rma.saric.domain.models.MemoriesRequestEntity
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import javax.inject.Inject


@HiltViewModel
class CreateMemoriesViewModel @Inject constructor(
    private val createMemoriesUseCase: CreateMemoriesUseCase
): ViewModel() {
    private val _errorMessage = mutableStateOf("")
    private val _city = mutableStateOf("")
    private val _country = mutableStateOf("")
    private val _stars = mutableStateOf("5")
    private val calendar = Calendar.getInstance()
    private val _date = mutableStateOf(formatDate(calendar.time))
    private val _imageUri = mutableStateOf("")

    val city : String
        get() = _city.value

    val country: String
        get() = _country.value

    val stars: String
        get() = _stars.value

    val date: String
        get() = _date.value

    val imageUri: String
        get() = _imageUri.value

    val errorMessage: String
        get() = _errorMessage.value

    fun onCityChange(newName: String) {
        _city.value = newName
    }

    fun onCountryChange(newCountry: String){
        _country.value = newCountry
    }

    fun onStarsChange(newStars: String) {
        _stars.value = newStars
    }

    fun onDateChange(newDate: String) {
        _date.value = newDate
    }

    fun onImageUriChange(newImageUri: String) {
        _imageUri.value = newImageUri
    }

    suspend fun createMemories() {
        try {
            createMemoriesUseCase.execute(
                MemoriesRequestEntity(id = null, city = _city.value,
                stars = _stars.value.toInt(), country = _country.value, date =_date.value, imageUri = _imageUri.value)
            )

        } catch (e: Exception) {
            _errorMessage.value = "Error ${e.message}"
        }
    }
}

private fun formatDate(date: Date): String {
    val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return simpleDateFormat.format(date)
}