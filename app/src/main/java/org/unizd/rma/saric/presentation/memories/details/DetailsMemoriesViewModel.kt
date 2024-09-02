package org.unizd.rma.saric.presentation.memories.details


import android.net.Uri
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.unizd.rma.saric.domain.models.MemoriesResponseEntity
import org.unizd.rma.saric.domain.interfaces.usecases.GetMemoriesUseCase
import javax.inject.Inject

@HiltViewModel
class DetailsMemoriesViewModel @Inject constructor(
    private val getMemoriesUseCase: GetMemoriesUseCase
) : ViewModel() {

    var memories: MemoriesResponseEntity? = MemoriesResponseEntity(
        id = 1,
        city = "",
        stars = 0,
        country = "",
        date = "",
        imageUri = Uri.parse("")
    )



    suspend fun loadMemoriesDetails(id: String) {
        try {
            memories = getMemoriesUseCase.execute(id.toInt())
        } catch (e: Exception) {
            // Handle the error (e.g., display an error message)
            memories = null
        }
    }


}






