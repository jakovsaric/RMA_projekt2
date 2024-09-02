package org.unizd.rma.saric.data.interfaces

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RoomDatabase
import org.unizd.rma.saric.data.datasources.room.entities.MemoriesRoomEntity

@Database(entities = [MemoriesRoomEntity::class], version = 2)
abstract class MemoriesDatabase: RoomDatabase(){
    abstract val memoriesDao: MemoriesDao

    companion object {
        const val DATABASE_NAME = "memories_db"
    }
}

@Dao
interface MemoriesDao {

    @Query("SELECT * FROM memories")
    suspend fun getAll(): List<MemoriesRoomEntity>
    @Query("SELECT * FROM memories WHERE id = :id")
    suspend fun getById(id: Int): MemoriesRoomEntity?
    @Query("DELETE FROM memories WHERE id = :id")
    suspend fun deleteById(id: Int)
    @Query("UPDATE memories SET city = :city, stars = :stars, country = :country, date = :date, imageUri = :imageUri WHERE id = :id")
    suspend fun update(id: Int, city: String, stars: Int, country: String, date: String, imageUri: String)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(memories: MemoriesRoomEntity)
}