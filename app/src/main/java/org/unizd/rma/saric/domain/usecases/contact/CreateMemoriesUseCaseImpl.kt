package org.unizd.rma.saric.domain.usecases.contact

import org.unizd.rma.saric.domain.interfaces.MemoriesRepository
import org.unizd.rma.saric.domain.interfaces.usecases.CreateMemoriesUseCase
import org.unizd.rma.saric.domain.models.MemoriesRequestEntity

class CreateMemoriesUseCaseImpl constructor(private val memoriesRepository: MemoriesRepository):
    CreateMemoriesUseCase {
    override suspend fun execute(memories: MemoriesRequestEntity) {
        memoriesRepository.createMemories(memories)
    }
}