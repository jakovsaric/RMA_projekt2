package org.unizd.rma.saric.domain.models

import android.net.Uri


data class MemoriesResponseEntity(
    val id: Int,
    val city: String,
    val stars: Int,
    val country: String,
    val date: String,
    val imageUri: Uri
)

data class MemoriesRequestEntity(
    val id: Int? = null,
    val city: String,
    val stars: Int,
    val country: String,
    val date: String,
    val imageUri: String
)