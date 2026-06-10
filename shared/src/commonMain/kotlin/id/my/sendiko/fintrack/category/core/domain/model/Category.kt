package id.my.sendiko.fintrack.category.core.domain.model

data class Category(
    val id: String,
    val name: String,
) {
    override fun toString(): String {
        return name
    }
}