package org.unizd.rma.saric.domain.usecases.contact

import org.unizd.rma.saric.domain.interfaces.MemoriesRepository
import org.unizd.rma.saric.domain.interfaces.usecases.GetMemoriesUseCase
import org.unizd.rma.saric.domain.models.MemoriesResponseEntity

class GetMemoriesUseCaseImpl constructor(private val memoriesRepository: MemoriesRepository):
    GetMemoriesUseCase {
    override suspend fun execute(id: Int): MemoriesResponseEntity? {
        return memoriesRepository.getMemories(id)
    }
}