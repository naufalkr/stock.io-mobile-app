package com.example.stockio.components.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.stockio.components.cards.MarketAssetCard
import com.example.stockio.model.*

@Composable
fun ModernMarketScreen(
    marketAssets: List<InvestmentAsset>,
    onAssetClick: (InvestmentAsset) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Text(
                "Pasar Investasi",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = TextPrimary
                ),
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }

        items(marketAssets) { asset ->
            MarketAssetCard(
                asset = asset,
                onClick = { onAssetClick(asset) }
            )
        }
    }
}
