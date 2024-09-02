package org.unizd.rma.saric.domain.interfaces.usecases

import org.unizd.rma.saric.domain.models.MemoriesRequestEntity

interface CreateMemoriesUseCase {
    suspend fun execute(memories: MemoriesRequestEntity)
}