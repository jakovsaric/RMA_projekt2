package org.unizd.rma.saric.domain.repositories

import org.unizd.rma.saric.data.interfaces.MemoriesDataSource
import org.unizd.rma.saric.domain.interfaces.MemoriesRepository
import org.unizd.rma.saric.domain.models.MemoriesRequestEntity
import org.unizd.rma.saric.domain.models.MemoriesResponseEntity


class MemoriesRepositoryImpl constructor(private val memoriesDataSource: MemoriesDataSource):
    MemoriesRepository {
    override suspend fun getMemories(): List<MemoriesResponseEntity> {
        return memoriesDataSource.getAll()
    }

    override suspend fun getMemories(id: Int): MemoriesResponseEntity? {
        return memoriesDataSource.getOne(id)
    }

    override suspend fun deleteMemories(id: Int) {
        return memoriesDataSource.delete(id)
    }

    override suspend fun updateMemories(id: Int, city:String, stars:Int, country:String, date:String, imageUri: String) {

        memoriesDataSource.update(id, city, stars, country, date, imageUri)
    }

    override suspend fun createMemories(data: MemoriesRequestEntity) {
        memoriesDataSource.create(data)
    }

}