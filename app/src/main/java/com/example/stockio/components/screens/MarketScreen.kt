package com.example.stockio.components.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.stockio.components.cards.MarketAssetCard
import com.example.stockio.model.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModernMarketScreen(
    marketAssets: List<InvestmentAsset>,
    onAssetClick: (InvestmentAsset) -> Unit
) {
    var selectedCategory by remember { mutableStateOf(AssetCategory.IHSG) }
    var searchQuery by remember { mutableStateOf("") }
    
    val filteredAssets = marketAssets.filter { asset ->
        asset.category == selectedCategory && 
        (searchQuery.isEmpty() || 
         asset.name.contains(searchQuery, ignoreCase = true) ||
         asset.code.contains(searchQuery, ignoreCase = true))
    }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundGray)
            .padding(20.dp)
    ) {
        
        // Search Bar
        SearchBar(
            searchQuery = searchQuery,
            onSearchQueryChange = { searchQuery = it },
            modifier = Modifier.padding(bottom = 20.dp)
        )
        
        // Category Tabs
        ModernCategoryTabs(
            selectedCategory = selectedCategory,
            onCategorySelected = { selectedCategory = it },
            modifier = Modifier.padding(bottom = 20.dp)
        )
        
        // Asset List
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(filteredAssets) { asset ->
                MarketAssetCard(
                    asset = asset,
                    onClick = { onAssetClick(asset) }
                )
            }
            
            if (filteredAssets.isEmpty()) {
                item {
                    EmptyState(
                        searchQuery = searchQuery,
                        category = selectedCategory
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = searchQuery,
        onValueChange = onSearchQueryChange,
        modifier = modifier.fillMaxWidth(),
        placeholder = {
            Text(
                "Search stocks or crypto...",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = TextSecondary
                )
            )
        },
        leadingIcon = {
            Icon(
                Icons.Filled.Search,
                contentDescription = "Search",
                tint = TextSecondary
            )
        },
        trailingIcon = {
            if (searchQuery.isNotEmpty()) {
                Icon(
                    Icons.Filled.Clear,
                    contentDescription = "Clear",
                    tint = TextSecondary,
                    modifier = Modifier.clickable {
                        onSearchQueryChange("")
                    }
                )
            }
        },
        shape = RoundedCornerShape(16.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = PrimaryBlue,
            unfocusedBorderColor = DividerGray,
            focusedContainerColor = CardWhite,
            unfocusedContainerColor = CardWhite
        ),
        singleLine = true
    )
}

@Composable
fun ModernCategoryTabs(
    selectedCategory: AssetCategory,
    onCategorySelected: (AssetCategory) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = CardWhite),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
        ) {
            CategoryTab(
                text = "IHSG",
                isSelected = selectedCategory == AssetCategory.IHSG,
                onClick = { onCategorySelected(AssetCategory.IHSG) },
                modifier = Modifier.weight(1f)
            )
            
            CategoryTab(
                text = "Crypto",
                isSelected = selectedCategory == AssetCategory.CRYPTO,
                onClick = { onCategorySelected(AssetCategory.CRYPTO) },
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun CategoryTab(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val backgroundColor = if (isSelected) {
        if (text == "IHSG") PrimaryBlue else Orange40
    } else {
        Color.Transparent
    }
    
    val textColor = if (isSelected) {
        Color.White
    } else {
        TextSecondary
    }
    
    Box(
        modifier = modifier
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(12.dp)
            )
            .clickable { onClick() }
            .padding(vertical = 12.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                color = textColor
            )
        )
    }
}

@Composable
fun EmptyState(
    searchQuery: String,
    category: AssetCategory
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = if (searchQuery.isEmpty()) {
                "No data available for ${category.name} category"
            } else {
                "No results found for \"$searchQuery\""
            },
            style = MaterialTheme.typography.bodyLarge.copy(
                color = TextSecondary,
                fontWeight = FontWeight.Medium
            )
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        Text(
            text = if (searchQuery.isEmpty()) {
                "Data will be added soon"
            } else {
                "Try different keywords"
            },
            style = MaterialTheme.typography.bodyMedium.copy(
                color = TextSecondary
            )
        )
    }
}
