package id.my.sendiko.fintrack.category.core.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SaveCategoryResponse(

    @SerialName("category")
    val category: CategoryDto,

    @SerialName("message")
    val message: String,

    @SerialName("status")
    val status: Int
)