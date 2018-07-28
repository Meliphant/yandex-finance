package ya.co.yandex_finance.repository.model.utils

enum class Categories {
    TRAVEL {
        override fun toString(): String = "Travel"
        override val iconUrl = "category_travel"
    },
    RESTAURANTS {
        override fun toString(): String = "Restaurant"
        override val iconUrl = "category_restaurants"
    };

    abstract override fun toString(): String
    abstract val iconUrl: String
}