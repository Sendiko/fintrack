package id.my.sendiko.fintrack.category.core.data.dto

import id.my.sendiko.fintrack.category.core.domain.model.Category
import id.my.sendiko.fintrack.category.core.domain.model.CategoryWithTransactions
import id.my.sendiko.fintrack.transaction.core.data.dto.TransactionsDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CategoryDtoWithTransactions(

    @SerialName("createdAt")
    val createdAt: String,

    @SerialName("name")
    val name: String,

    @SerialName("id")
    val id: String,

    @SerialName("transactions")
    val transactions: List<TransactionsDto>,

    @SerialName("userId")
    val userId: String,

    @SerialName("updatedAt")
    val updatedAt: String
) {
    fun toDomain() = Category(
        id = id,
        name = name
    )

    fun toDomainWithTransaction() = CategoryWithTransactions(
        category = this.toDomain(),
        transactions = this.transactions.map { it.toDomain() }
    )
}