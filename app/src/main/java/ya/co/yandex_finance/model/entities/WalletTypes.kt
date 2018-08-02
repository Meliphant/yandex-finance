package ya.co.yandex_finance.model.entities

enum class WalletTypes(val title: String, val iconUrl: String) {
    CASH("Cash", "wallet_cash"),
    CARD("Card", "wallet_card");

    override fun toString() = title
}
