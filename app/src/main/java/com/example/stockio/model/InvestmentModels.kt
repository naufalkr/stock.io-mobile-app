package com.example.stockio.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.PieChart
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import kotlin.random.Random

data class InvestmentAsset(
    val id: String,
    val name: String,
    val code: String,
    val currentPrice: Double,
    val priceChangePercent: Double,
    val icon: ImageVector,
    val color: Color,
    val quantity: Int = 0,
    val category: AssetCategory = AssetCategory.STOCK,
    val description: String = "",
    val ipoDate: String = "",
    val marketCap: Double = 0.0,
    val volume: Long = 0,
    val priceHistory: List<Double> = emptyList()
)

enum class AssetCategory {
    STOCK, CRYPTO
}

enum class Screen {
    HOME, MARKET, PORTFOLIO, PROFILE, ASSET_DETAIL
}

// Color constants
val PrimaryBlue = Color(0xFF1565C0)
val SecondaryBlue = Color(0xFF42A5F5)
val AccentBlue = Color(0xFF64B5F6)
val Green40 = Color(0xFF4CAF50)
val Red40 = Color(0xFFE53935)
val Orange40 = Color(0xFFFF9800)
val Purple40 = Color(0xFF9C27B0)
val BackgroundGray = Color(0xFFF8FAFC)
val CardWhite = Color(0xFFFFFFFF)
val TextPrimary = Color(0xFF1E293B)
val TextSecondary = Color(0xFF64748B)
val DividerGray = Color(0xFFE2E8F0)

fun getSampleMarketData(): List<InvestmentAsset> {
    return listOf(
        // Stocks
        InvestmentAsset(
            id = "1",
            name = "Bank Central Asia",
            code = "BBCA",
            currentPrice = 8750.0,
            priceChangePercent = Random.nextDouble(-5.0, 5.0),
            icon = Icons.Filled.BarChart,
            color = PrimaryBlue,
            category = AssetCategory.STOCK,
            description = "Bank Central Asia (BCA) adalah salah satu bank swasta terbesar di Indonesia yang menyediakan layanan perbankan ritel dan komersial.",
            ipoDate = "31 Mei 2000",
            marketCap = 1050000000000.0,
            volume = 25000000,
            priceHistory = generatePriceHistory(8750.0, 100)
        ),
        InvestmentAsset(
            id = "2",
            name = "Bank Rakyat Indonesia",
            code = "BBRI",
            currentPrice = 4520.0,
            priceChangePercent = Random.nextDouble(-5.0, 5.0),
            icon = Icons.Filled.BarChart,
            color = Green40,
            category = AssetCategory.STOCK,
            description = "Bank Rakyat Indonesia (BRI) adalah bank milik negara yang fokus pada segmen mikro dan UMKM dengan jaringan terluas di Indonesia.",
            ipoDate = "10 November 2003",
            marketCap = 850000000000.0,
            volume = 35000000,
            priceHistory = generatePriceHistory(4520.0, 100)
        ),
        InvestmentAsset(
            id = "3",
            name = "Telkom Indonesia",
            code = "TLKM",
            currentPrice = 3890.0,
            priceChangePercent = Random.nextDouble(-4.0, 4.0),
            icon = Icons.Filled.BarChart,
            color = Color(0xFFE53935),
            category = AssetCategory.STOCK,
            description = "Telkom Indonesia adalah perusahaan telekomunikasi terbesar di Indonesia yang menyediakan layanan telekomunikasi dan digital.",
            ipoDate = "14 November 1995",
            marketCap = 380000000000.0,
            volume = 20000000,
            priceHistory = generatePriceHistory(3890.0, 100)
        ),
        InvestmentAsset(
            id = "4",
            name = "Goto Gojek Tokopedia",
            code = "GOTO",
            currentPrice = 86.0,
            priceChangePercent = Random.nextDouble(-8.0, 8.0),
            icon = Icons.Filled.BarChart,
            color = Color(0xFF00AA5B),
            category = AssetCategory.STOCK,
            description = "GoTo adalah platform ekosistem digital terbesar di Indonesia yang menggabungkan layanan Gojek dan Tokopedia.",
            ipoDate = "11 April 2022",
            marketCap = 150000000000.0,
            volume = 50000000,
            priceHistory = generatePriceHistory(86.0, 100)
        ),
        InvestmentAsset(
            id = "5",
            name = "Unilever Indonesia",
            code = "UNVR",
            currentPrice = 2450.0,
            priceChangePercent = Random.nextDouble(-3.0, 3.0),
            icon = Icons.Filled.BarChart,
            color = Color(0xFF0078D4),
            category = AssetCategory.STOCK,
            description = "Unilever Indonesia adalah perusahaan consumer goods yang memproduksi produk-produk rumah tangga dan perawatan pribadi.",
            ipoDate = "11 Januari 1982",
            marketCap = 340000000000.0,
            volume = 8000000,
            priceHistory = generatePriceHistory(2450.0, 100)
        ),
        
        // Cryptocurrencies
        InvestmentAsset(
            id = "6",
            name = "Bitcoin",
            code = "BTC",
            currentPrice = 750000000.0,
            priceChangePercent = Random.nextDouble(-10.0, 10.0),
            icon = Icons.Filled.Star,
            color = Orange40,
            category = AssetCategory.CRYPTO,
            description = "Bitcoin adalah cryptocurrency pertama dan terbesar di dunia yang beroperasi pada teknologi blockchain terdesentralisasi.",
            ipoDate = "3 Januari 2009",
            marketCap = 14500000000000.0,
            volume = 25000000000,
            priceHistory = generatePriceHistory(750000000.0, 100)
        ),
        InvestmentAsset(
            id = "7",
            name = "Ethereum",
            code = "ETH",
            currentPrice = 48000000.0,
            priceChangePercent = Random.nextDouble(-8.0, 8.0),
            icon = Icons.Filled.Star,
            color = Color(0xFF627EEA),
            category = AssetCategory.CRYPTO,
            description = "Ethereum adalah platform blockchain yang memungkinkan smart contracts dan aplikasi terdesentralisasi (DApps).",
            ipoDate = "30 Juli 2015",
            marketCap = 5800000000000.0,
            volume = 18000000000,
            priceHistory = generatePriceHistory(48000000.0, 100)
        ),
        InvestmentAsset(
            id = "8",
            name = "Binance Coin",
            code = "BNB",
            currentPrice = 4200000.0,
            priceChangePercent = Random.nextDouble(-6.0, 6.0),
            icon = Icons.Filled.Star,
            color = Color(0xFFF3BA2F),
            category = AssetCategory.CRYPTO,
            description = "Binance Coin adalah token utilitas yang digunakan di ekosistem Binance, exchange cryptocurrency terbesar di dunia.",
            ipoDate = "25 Juli 2017",
            marketCap = 630000000000.0,
            volume = 8500000000,
            priceHistory = generatePriceHistory(4200000.0, 100)
        ),
        InvestmentAsset(
            id = "9",
            name = "Cardano",
            code = "ADA",
            currentPrice = 8500.0,
            priceChangePercent = Random.nextDouble(-7.0, 7.0),
            icon = Icons.Filled.Star,
            color = Color(0xFF0033AD),
            category = AssetCategory.CRYPTO,
            description = "Cardano adalah blockchain proof-of-stake yang berfokus pada sustainability dan peer-reviewed research.",
            ipoDate = "1 Oktober 2017",
            marketCap = 295000000000.0,
            volume = 3200000000,
            priceHistory = generatePriceHistory(8500.0, 100)
        ),
        InvestmentAsset(
            id = "10",
            name = "Solana",
            code = "SOL",
            currentPrice = 2850000.0,
            priceChangePercent = Random.nextDouble(-12.0, 12.0),
            icon = Icons.Filled.Star,
            color = Color(0xFF9945FF),
            category = AssetCategory.CRYPTO,
            description = "Solana adalah blockchain berkecepatan tinggi yang mendukung smart contracts dan DApps dengan biaya transaksi rendah.",
            ipoDate = "16 Maret 2020",
            marketCap = 1350000000000.0,
            volume = 12000000000,
            priceHistory = generatePriceHistory(2850000.0, 100)
        )
    )
}

fun generatePriceHistory(currentPrice: Double, days: Int): List<Double> {
    val history = mutableListOf<Double>()
    var price = currentPrice * 0.8 // Start from 80% of current price
    
    for (i in 0 until days) {
        val change = Random.nextDouble(-0.05, 0.05) // ±5% daily change
        price *= (1 + change)
        history.add(price)
    }
    
    return history
}
