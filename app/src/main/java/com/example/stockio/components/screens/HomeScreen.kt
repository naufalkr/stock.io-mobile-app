package com.example.stockio.components.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.stockio.components.cards.*
import com.example.stockio.components.shared.*
import com.example.stockio.model.*
import java.text.NumberFormat
import java.util.Locale

@Composable
fun ModernHomeScreen(
    balance: Double,
    portfolioValue: Double,
    assets: List<InvestmentAsset>,
    marketAssets: List<InvestmentAsset>,
    isBalanceVisible: Boolean,
    onToggleBalance: () -> Unit,
    onAssetClick: (InvestmentAsset) -> Unit,
    onRefreshMarketData: () -> Unit,
    onNavigateToMarket: () -> Unit = {}, // Added navigation callback
    onNavigateToPortfolio: () -> Unit = {} // Added navigation callback
) {
    val currencyFormat = remember { NumberFormat.getCurrencyInstance(Locale("id", "ID")) }
    val totalValue = balance + portfolioValue

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(8.dp))
            
            BalanceCard(
                totalValue = totalValue,
                balance = balance,
                portfolioValue = portfolioValue,
                isBalanceVisible = isBalanceVisible,
                onToggleBalance = onToggleBalance,
                currencyFormat = currencyFormat
            )
        }

        item {
            QuickActionsCard()
        }

        item {
            SectionHeader(
                title = "Popular Assets",
                actionText = "View All",
                onActionClick = onNavigateToMarket // Changed to navigate to market
            )
        }

        items(marketAssets.take(3)) { asset ->
            MarketAssetCard(
                asset = asset,
                onClick = { onAssetClick(asset) }
            )
        }

        item {
            SectionHeader(
                title = "My Portfolio",
                actionText = "View Details",
                onActionClick = onNavigateToPortfolio // Changed to navigate to portfolio
            )
        }

        if (assets.isEmpty()) {
            item {
                EmptyPortfolioCard()
            }
        } else {
            items(assets.take(3)) { asset ->
                PortfolioAssetCard(asset = asset)
            }
        }
        
        item {
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}
