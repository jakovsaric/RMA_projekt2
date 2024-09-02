package org.unizd.rma.saric.data.datasources.room.entities

import android.net.Uri
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.unizd.rma.saric.domain.models.MemoriesRequestEntity
import org.unizd.rma.saric.domain.models.MemoriesResponseEntity


@Entity(tableName = "memories")
data class MemoriesRoomEntity(
    @PrimaryKey
    val id: Int? = null,
    val city: String,
    val country: String,
    val date: String,
    val imageUri: String,
    val stars: Int
)

fun MemoriesRoomEntity.toMemoriesResponseEntity(): MemoriesResponseEntity {
    return MemoriesResponseEntity(
        id = id!!,
        city = city,
        stars = stars,
        country = country,
        date = date,
        imageUri = Uri.parse(imageUri)

    )
}

fun MemoriesRequestEntity.toMemoriesRoomEntity(): MemoriesRoomEntity{
    return MemoriesRoomEntity(
        id = id,
        city = city,
        stars = stars,
        country = country,
        date = date,
        imageUri = imageUri
    )
}