package org.unizd.rma.saric.presentation.memories.list

import android.net.Uri
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.unizd.rma.saric.domain.interfaces.usecases.DeleteMemoriesUseCase
import org.unizd.rma.saric.domain.interfaces.usecases.GetAllMemoriesUseCase
import org.unizd.rma.saric.domain.models.MemoriesResponseEntity
import javax.inject.Inject

data class MemoriesListResponseModel(
    val id: String,
    val city: String,
    val stars: String,
    val country: String,
    val date: String,
    val imageUri: Uri
)

fun MemoriesResponseEntity.toMemoriesListResponseModel():
        MemoriesListResponseModel {
    return MemoriesListResponseModel(
        id = id.toString(),
        city = city,
        stars = stars.toString(),
        country = country,
        date = date,
        imageUri = imageUri
    )
}

@HiltViewModel
class ListMemoriesViewModel @Inject constructor(
    private val getAllMemoriesUseCase: GetAllMemoriesUseCase,
    private val deleteMemoriesUseCase: DeleteMemoriesUseCase
): ViewModel() {
    private val _errorMessage = mutableStateOf("")
    private val _memories = mutableStateListOf<MemoriesListResponseModel>()

    val errorMessage : String
        get() = _errorMessage.value

    val memories: List<MemoriesListResponseModel>
        get() = _memories.toList()

    suspend fun getMemories() {

        try {
            _memories.clear()
            val list = getAllMemoriesUseCase.execute()
            _memories.addAll(list.map {
                it.toMemoriesListResponseModel()
            })
        }catch (e: Exception) {
            println("Error")
            _errorMessage.value = "Error ${e.message}"
        }
    }




    suspend fun deleteMemoriesById(id: String) {
        val ide = id.toInt()
        deleteMemoriesUseCase.execute(ide)
        getMemories()
    }






}