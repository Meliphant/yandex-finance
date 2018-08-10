package ya.co.yandex_finance.model.entities

import ya.co.yandex_finance.R

enum class Categories(val titleId: Int, val iconUrl: String) {

    NOCATEGORY(R.string.category_no, "category_no"),
    TRAVEL(R.string.category_travel, "category_travel"),
    RESTAURANTS(R.string.category_restaurant, "category_restaurants"),
    GROCERIES(R.string.category_groceries, "category_groceries"),
    SUPERMARKETS(R.string.category_supermarkets, "category_groceries"),
    EDUCATION(R.string.category_education, "category_education"),
    CLOTHES(R.string.category_clothes, "category_clothes"),
    ENTERTAINMENT(R.string.category_entertainment, "category_entertainment"),
    TRANSPORT(R.string.category_transport, "category_transport"),
    HEALTH(R.string.category_health, "category_health"),
    MEDIA(R.string.category_media, "category_media"),
    OTHER(R.string.category_other, "category_other");
}
