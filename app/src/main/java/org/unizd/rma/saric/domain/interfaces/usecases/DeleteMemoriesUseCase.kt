package org.unizd.rma.saric.domain.interfaces.usecases

interface DeleteMemoriesUseCase {
    suspend fun execute(id: Int)
}