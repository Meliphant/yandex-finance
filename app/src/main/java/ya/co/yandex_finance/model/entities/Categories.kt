package ya.co.yandex_finance.model.entities

enum class Categories(val title: String, val iconUrl: String) {

    TRAVEL("Travel", "category_travel"),
    RESTAURANTS("Restaurant", "category_restaurants"),
    GROCERIES("Groceries", "category_groceries"),
    SUPERMARKETS("Supermarkets", "category_supermarkets"),
    EDUCATION("Education", "category_education"),
    CLOTHES("Clothes and accessories", "category_clothes"),
    ENTERTAINMENT("Entertainment", "category_entertainment"),
    TRANSPORT("Transport", "category_transport"),
    HEALTH("Health and beauty", "category_health"),
    MEDIA("Media", "category_media"),
    OTHER("Other", "category_other");

    override fun toString() = title
}