package org.unizd.rma.saric.domain.interfaces.usecases

interface UpdateMemoriesUseCase {
    suspend fun execute(id: Int, city:String, stars:Int, country:String, date:String, imageUri: String)
}