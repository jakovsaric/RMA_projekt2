package org.unizd.rma.saric.data.datasources.room

import org.unizd.rma.saric.data.datasources.room.entities.toMemoriesResponseEntity
import org.unizd.rma.saric.data.datasources.room.entities.toMemoriesRoomEntity
import org.unizd.rma.saric.data.interfaces.MemoriesDao
import org.unizd.rma.saric.data.interfaces.MemoriesDataSource
import org.unizd.rma.saric.domain.models.MemoriesRequestEntity
import org.unizd.rma.saric.domain.models.MemoriesResponseEntity


class RoomMemoriesDataSource constructor(private val dao: MemoriesDao) : MemoriesDataSource {
    override suspend fun getAll(): List<MemoriesResponseEntity> {
        return dao.getAll().toList().map {
            it.toMemoriesResponseEntity()
        }
    }

    override suspend fun getOne(id: Int): MemoriesResponseEntity? {
        val entity = dao.getById(id)
        if (entity != null) {
            return entity.toMemoriesResponseEntity()
        }
        return null
    }

    override suspend fun delete(id: Int) {
        dao.deleteById(id)
    }

    override suspend fun update(id: Int, city:String, stars:Int, country:String, date:String, imageUri: String) {

        dao.update(id, city, stars, country, date, imageUri)
    }

    override suspend fun create(data: MemoriesRequestEntity) {
        dao.insert(data.toMemoriesRoomEntity())
    }

}