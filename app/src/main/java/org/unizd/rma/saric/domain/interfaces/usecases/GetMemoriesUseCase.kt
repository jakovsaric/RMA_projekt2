package org.unizd.rma.saric.domain.interfaces.usecases

import org.unizd.rma.saric.domain.models.MemoriesResponseEntity

interface GetMemoriesUseCase {
    suspend fun execute(id: Int): MemoriesResponseEntity?
}