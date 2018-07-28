package ya.co.yandex_finance.repository.model.utils

enum class WalletTypes {
    CASH {
        override fun toString(): String = "Cash" //todo: get the name from string?
        override val iconUrl = "wallet_cash"
    },
    CARD {
        override fun toString(): String = "Card"
        override val iconUrl = "wallet_card"
    };

    abstract override fun toString(): String
    abstract val iconUrl: String
}