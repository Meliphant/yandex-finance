package ya.co.yandex_finance.model.entities

enum class Categories(val title: String, val iconUrl: String) {

    TRAVEL("Travel", "category_travel"),
    RESTAURANTS("Restaurant", "category_restaurants"),
    GROCERIES("Groceries", "category_groceries");

    override fun toString() = title
}
