package org.unizd.rma.saric.data.interfaces

import org.unizd.rma.saric.domain.models.MemoriesRequestEntity
import org.unizd.rma.saric.domain.models.MemoriesResponseEntity

interface MemoriesDataSource {
    suspend fun getAll(): List<MemoriesResponseEntity>

    suspend fun getOne(id: Int): MemoriesResponseEntity?

    suspend fun delete(id: Int)

    suspend fun update(id: Int, city:String, stars:Int, country:String, date:String, imageUri:String)

    suspend fun create(data: MemoriesRequestEntity)
}