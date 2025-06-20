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
    val category: AssetCategory = AssetCategory.IHSG,
    val description: String = "",
    val ipoDate: String = "",
    val marketCap: Double = 0.0,
    val volume: Long = 0,
    val priceHistory: List<Double> = emptyList(),
    // Additional comprehensive data
    val openPrice: Double = 0.0,
    val highPrice: Double = 0.0,
    val lowPrice: Double = 0.0,
    val previousClose: Double = 0.0,
    val eps: Double = 0.0, // Earnings per Share
    val pe: Double = 0.0, // Price to Earnings
    val pbv: Double = 0.0, // Price to Book Value
    val dividendYield: Double = 0.0,
    val beta: Double = 0.0,
    val sector: String = "",
    val industry: String = "",
    val employees: Int = 0,
    val website: String = "",
    val ceo: String = "",
    val headquarters: String = "",
    val yearLow: Double = 0.0,
    val yearHigh: Double = 0.0,
    val avgVolume: Long = 0,
    val sharesOutstanding: Long = 0,
    val freeCashFlow: Double = 0.0,
    val totalDebt: Double = 0.0,
    val revenue: Double = 0.0,
    val netIncome: Double = 0.0,
    val totalAssets: Double = 0.0,
    val totalEquity: Double = 0.0,
    // For crypto specific data
    val circulatingSupply: Long = 0,
    val maxSupply: Long = 0,
    val hashRate: String = "",
    val blockTime: String = "",
    val consensus: String = ""
)

enum class AssetCategory {
    IHSG, CRYPTO
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
        // IHSG Stocks
        InvestmentAsset(
            id = "1",
            name = "Bank Central Asia",
            code = "BBCA",
            currentPrice = 8750.0,
            priceChangePercent = Random.nextDouble(-5.0, 5.0),
            icon = Icons.Filled.BarChart,
            color = PrimaryBlue,
            category = AssetCategory.IHSG,
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
            category = AssetCategory.IHSG,
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
            category = AssetCategory.IHSG,
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
            category = AssetCategory.IHSG,
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
            category = AssetCategory.IHSG,
            description = "Unilever Indonesia adalah perusahaan consumer goods yang memproduksi produk-produk rumah tangga dan perawatan pribadi.",
            ipoDate = "11 Januari 1982",
            marketCap = 340000000000.0,
            volume = 8000000,
            priceHistory = generatePriceHistory(2450.0, 100)
        ),
        InvestmentAsset(
            id = "6",
            name = "Adaro Energy",
            code = "ADRO",
            currentPrice = 2890.0,
            priceChangePercent = Random.nextDouble(-6.0, 6.0),
            icon = Icons.Filled.BarChart,
            color = Color(0xFF795548),
            category = AssetCategory.IHSG,
            description = "Adaro Energy adalah perusahaan pertambangan batu bara terbesar di Indonesia dengan operasi terintegrasi dari hulu hingga hilir.",
            ipoDate = "16 Juli 2008",
            marketCap = 520000000000.0,
            volume = 85000000,
            priceHistory = generatePriceHistory(2890.0, 100)
        ),
        InvestmentAsset(
            id = "7",
            name = "Bank Mandiri",
            code = "BMRI",
            currentPrice = 9150.0,
            priceChangePercent = Random.nextDouble(-4.0, 4.0),
            icon = Icons.Filled.BarChart,
            color = Color(0xFF1976D2),
            category = AssetCategory.IHSG,
            description = "Bank Mandiri adalah bank terbesar di Indonesia yang menyediakan layanan perbankan universal untuk segmen retail dan korporasi.",
            ipoDate = "14 Juli 2003",
            marketCap = 1200000000000.0,
            volume = 22000000,
            priceHistory = generatePriceHistory(9150.0, 100)
        ),
        InvestmentAsset(
            id = "8",
            name = "Astra International",
            code = "ASII",
            currentPrice = 5100.0,
            priceChangePercent = Random.nextDouble(-3.0, 3.0),
            icon = Icons.Filled.BarChart,
            color = Color(0xFF673AB7),
            category = AssetCategory.IHSG,
            description = "Astra International adalah konglomerat terbesar di Indonesia dengan bisnis otomotif, alat berat, pertambangan, dan jasa keuangan.",
            ipoDate = "4 April 1990",
            marketCap = 780000000000.0,
            volume = 18000000,
            priceHistory = generatePriceHistory(5100.0, 100)
        ),
        InvestmentAsset(
            id = "9",
            name = "Indofood CBP Sukses Makmur",
            code = "ICBP",
            currentPrice = 9025.0,
            priceChangePercent = Random.nextDouble(-2.0, 2.0),
            icon = Icons.Filled.BarChart,
            color = Color(0xFFFF5722),
            category = AssetCategory.IHSG,
            description = "Indofood CBP adalah perusahaan makanan dan minuman terbesar di Indonesia dengan brand terkenal seperti Indomie dan Chitato.",
            ipoDate = "7 Oktober 2010",
            marketCap = 890000000000.0,
            volume = 12000000,
            priceHistory = generatePriceHistory(9025.0, 100)
        ),
        InvestmentAsset(
            id = "10",
            name = "Kalbe Farma",
            code = "KLBF",
            currentPrice = 1555.0,
            priceChangePercent = Random.nextDouble(-3.0, 3.0),
            icon = Icons.Filled.BarChart,
            color = Color(0xFF009688),
            category = AssetCategory.IHSG,
            description = "Kalbe Farma adalah perusahaan farmasi terbesar di Indonesia yang memproduksi obat-obatan dan produk kesehatan.",
            ipoDate = "30 Juli 1991",
            marketCap = 290000000000.0,
            volume = 28000000,
            priceHistory = generatePriceHistory(1555.0, 100)
        ),
        InvestmentAsset(
            id = "11",
            name = "Gudang Garam",
            code = "GGRM",
            currentPrice = 18100.0,
            priceChangePercent = Random.nextDouble(-4.0, 4.0),
            icon = Icons.Filled.BarChart,
            color = Color(0xFFD32F2F),
            category = AssetCategory.IHSG,
            description = "Gudang Garam adalah produsen rokok kretek terbesar di Indonesia dengan berbagai merek rokok populer.",
            ipoDate = "27 Agustus 1990",
            marketCap = 420000000000.0,
            volume = 3500000,
            priceHistory = generatePriceHistory(18100.0, 100)
        ),
        InvestmentAsset(
            id = "12",
            name = "Indocement Tunggal Prakarsa",
            code = "INTP",
            currentPrice = 9400.0,
            priceChangePercent = Random.nextDouble(-5.0, 5.0),
            icon = Icons.Filled.BarChart,
            color = Color(0xFF607D8B),
            category = AssetCategory.IHSG,
            description = "Indocement adalah produsen semen terbesar kedua di Indonesia dengan kapasitas produksi yang tersebar di berbagai wilayah.",
            ipoDate = "5 Desember 1989",
            marketCap = 650000000000.0,
            volume = 8500000,
            priceHistory = generatePriceHistory(9400.0, 100)
        ),
        InvestmentAsset(
            id = "13",
            name = "Perusahaan Gas Negara",
            code = "PGAS",
            currentPrice = 1350.0,
            priceChangePercent = Random.nextDouble(-6.0, 6.0),
            icon = Icons.Filled.BarChart,
            color = Color(0xFF4CAF50),
            category = AssetCategory.IHSG,
            description = "PGN adalah perusahaan transportasi dan distribusi gas bumi terbesar di Indonesia dengan jaringan pipa gas yang luas.",
            ipoDate = "15 Desember 2003",
            marketCap = 275000000000.0,
            volume = 45000000,
            priceHistory = generatePriceHistory(1350.0, 100)
        ),
        InvestmentAsset(
            id = "14",
            name = "XL Axiata",
            code = "EXCL",
            currentPrice = 2750.0,
            priceChangePercent = Random.nextDouble(-5.0, 5.0),
            icon = Icons.Filled.BarChart,
            color = Color(0xFF9C27B0),
            category = AssetCategory.IHSG,
            description = "XL Axiata adalah operator telekomunikasi seluler terbesar kedua di Indonesia yang menyediakan layanan voice dan data.",
            ipoDate = "29 September 2005",
            marketCap = 180000000000.0,
            volume = 32000000,
            priceHistory = generatePriceHistory(2750.0, 100)
        ),
        
        // Cryptocurrencies
        InvestmentAsset(
            id = "15",
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
            id = "16",
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
            id = "17",
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
            id = "18",
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
            id = "19",
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
