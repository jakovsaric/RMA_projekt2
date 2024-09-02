package org.unizd.rma.saric.domain.interfaces

import org.unizd.rma.saric.domain.models.MemoriesRequestEntity
import org.unizd.rma.saric.domain.models.MemoriesResponseEntity

interface MemoriesRepository {
    suspend fun getMemories(): List<MemoriesResponseEntity>
    suspend fun getMemories(id: Int): MemoriesResponseEntity?
    suspend fun deleteMemories(id: Int)
    suspend fun updateMemories(id: Int, city:String, stars:Int, country:String, date:String, imageUri:String)
    suspend fun createMemories(data: MemoriesRequestEntity)
}