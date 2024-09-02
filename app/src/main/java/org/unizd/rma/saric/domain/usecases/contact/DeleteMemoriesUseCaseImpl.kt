package org.unizd.rma.saric.domain.usecases.contact

import org.unizd.rma.saric.domain.interfaces.MemoriesRepository
import org.unizd.rma.saric.domain.interfaces.usecases.DeleteMemoriesUseCase

class DeleteMemoriesUseCaseImpl constructor(private val memoriesRepository: MemoriesRepository):
    DeleteMemoriesUseCase {
    override suspend fun execute(id: Int) {
        memoriesRepository.deleteMemories(id)
    }
}