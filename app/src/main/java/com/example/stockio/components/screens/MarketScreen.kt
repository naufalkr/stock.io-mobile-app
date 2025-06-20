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
    var selectedCategory by remember { mutableStateOf<AssetCategory?>(null) }
    
    val filteredAssets = if (selectedCategory != null) {
        marketAssets.filter { it.category == selectedCategory }
    } else {
        marketAssets
    }
    
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

        item {
            CategoryFilter(
                selectedCategory = selectedCategory,
                onCategorySelected = { selectedCategory = it }
            )
        }

        items(filteredAssets) { asset ->
            MarketAssetCard(
                asset = asset,
                onClick = { onAssetClick(asset) }
            )
        }
    }
}

@Composable
fun CategoryFilter(
    selectedCategory: AssetCategory?,
    onCategorySelected: (AssetCategory?) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // All button
        FilterChip(
            selected = selectedCategory == null,
            onClick = { onCategorySelected(null) },
            label = {
                Text(
                    text = "All",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Medium
                    )
                )
            },
            colors = FilterChipDefaults.filterChipColors(
                selectedContainerColor = PrimaryBlue,
                selectedLabelColor = androidx.compose.ui.graphics.Color.White,
                containerColor = androidx.compose.ui.graphics.Color.Transparent,
                labelColor = TextSecondary
            ),
            border = FilterChipDefaults.filterChipBorder(
                enabled = true,
                selected = selectedCategory == null,
                borderColor = if (selectedCategory == null) PrimaryBlue else DividerGray,
                selectedBorderColor = PrimaryBlue
            )
        )
        
        // Stock button
        FilterChip(
            selected = selectedCategory == AssetCategory.STOCK,
            onClick = { onCategorySelected(AssetCategory.STOCK) },
            label = {
                Text(
                    text = "Stocks",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Medium
                    )
                )
            },
            colors = FilterChipDefaults.filterChipColors(
                selectedContainerColor = PrimaryBlue,
                selectedLabelColor = androidx.compose.ui.graphics.Color.White,
                containerColor = androidx.compose.ui.graphics.Color.Transparent,
                labelColor = TextSecondary
            ),
            border = FilterChipDefaults.filterChipBorder(
                enabled = true,
                selected = selectedCategory == AssetCategory.STOCK,
                borderColor = if (selectedCategory == AssetCategory.STOCK) PrimaryBlue else DividerGray,
                selectedBorderColor = PrimaryBlue
            )
        )
        
        // Crypto button
        FilterChip(
            selected = selectedCategory == AssetCategory.CRYPTO,
            onClick = { onCategorySelected(AssetCategory.CRYPTO) },
            label = {
                Text(
                    text = "Crypto",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Medium
                    )
                )
            },
            colors = FilterChipDefaults.filterChipColors(
                selectedContainerColor = Orange40,
                selectedLabelColor = androidx.compose.ui.graphics.Color.White,
                containerColor = androidx.compose.ui.graphics.Color.Transparent,
                labelColor = TextSecondary
            ),
            border = FilterChipDefaults.filterChipBorder(
                enabled = true,
                selected = selectedCategory == AssetCategory.CRYPTO,
                borderColor = if (selectedCategory == AssetCategory.CRYPTO) Orange40 else DividerGray,
                selectedBorderColor = Orange40
            )
        )
    }
}
