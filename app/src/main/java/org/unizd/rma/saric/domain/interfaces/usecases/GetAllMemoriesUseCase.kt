package org.unizd.rma.saric.domain.interfaces.usecases

import org.unizd.rma.saric.domain.models.MemoriesResponseEntity

interface GetAllMemoriesUseCase {
    suspend fun execute(): List<MemoriesResponseEntity>
}