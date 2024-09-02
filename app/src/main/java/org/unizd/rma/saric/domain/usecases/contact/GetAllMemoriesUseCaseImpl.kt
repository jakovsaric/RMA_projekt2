package org.unizd.rma.saric.domain.usecases.contact

import org.unizd.rma.saric.domain.interfaces.MemoriesRepository
import org.unizd.rma.saric.domain.interfaces.usecases.GetAllMemoriesUseCase
import org.unizd.rma.saric.domain.models.MemoriesResponseEntity

class GetAllMemoriesUseCaseImpl constructor(private val memoriesRepository: MemoriesRepository):
    GetAllMemoriesUseCase {
    override suspend fun execute(): List<MemoriesResponseEntity> {
        return memoriesRepository.getMemories()
    }
}