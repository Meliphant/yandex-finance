package ya.co.yandex_finance.repository.model.utils

enum class MyCurrency {
    USD {
        override fun toString(): String = "USD"
        override val rate = 1
        override val sign = '$'
    },
    RUB {
        override fun toString(): String = "RUB"
        override val rate = 60
        override val sign = '₽'
    };

    abstract override fun toString(): String
    abstract val rate: Int //todo: delete?
    abstract val sign: Char
}