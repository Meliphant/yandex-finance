package ya.co.yandex_finance.repository.model.utils

enum class WalletTypes(val title: String, val iconUrl: String) {
    CASH("Cash", "wallet_cash"),
    CARD("Card", "wallet_card");

    override fun toString() = title
}
