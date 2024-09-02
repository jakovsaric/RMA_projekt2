package org.unizd.rma.saric.domain.usecases.contact

import org.unizd.rma.saric.domain.interfaces.MemoriesRepository
import org.unizd.rma.saric.domain.interfaces.usecases.UpdateMemoriesUseCase

class UpdateMemoriesUseCaseImpl constructor(private val memoriesRepository: MemoriesRepository):
    UpdateMemoriesUseCase {
    override suspend fun execute(id: Int, city:String, stars:Int, country:String, date:String, imageUri: String) {
        return memoriesRepository.updateMemories(id, city, stars, country, date, imageUri)
    }
}