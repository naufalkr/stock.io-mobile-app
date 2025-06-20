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
    val quantity: Int = 0
)

enum class Screen {
    HOME, MARKET, PORTFOLIO, PROFILE
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
        InvestmentAsset(
            id = "1",
            name = "Saham BBCA",
            code = "BBCA",
            currentPrice = 8750.0,
            priceChangePercent = Random.nextDouble(-5.0, 5.0),
            icon = Icons.Filled.BarChart,
            color = PrimaryBlue
        ),
        InvestmentAsset(
            id = "2",
            name = "Saham BBRI",
            code = "BBRI",
            currentPrice = 4520.0,
            priceChangePercent = Random.nextDouble(-5.0, 5.0),
            icon = Icons.Filled.BarChart,
            color = Green40
        ),
        InvestmentAsset(
            id = "3",
            name = "Emas 24K",
            code = "GOLD",
            currentPrice = 985000.0,
            priceChangePercent = Random.nextDouble(-2.0, 2.0),
            icon = Icons.Filled.Star,
            color = Color(0xFFFFD700)
        ),
        InvestmentAsset(
            id = "4",
            name = "Bitcoin",
            code = "BTC",
            currentPrice = 750000000.0,
            priceChangePercent = Random.nextDouble(-10.0, 10.0),
            icon = Icons.Filled.Star,
            color = Orange40
        ),
        InvestmentAsset(
            id = "5",
            name = "Reksadana Saham",
            code = "RDS",
            currentPrice = 2500.0,
            priceChangePercent = Random.nextDouble(-1.0, 1.0),
            icon = Icons.Filled.PieChart,
            color = Purple40
        )
    )
}
