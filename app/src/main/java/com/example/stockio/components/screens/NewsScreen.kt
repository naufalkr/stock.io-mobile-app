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
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stockio.model.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModernNewsScreen() {
    var selectedCategory by remember { mutableStateOf(NewsCategory.ALL) }
    var searchQuery by remember { mutableStateOf("") }
    val newsArticles = remember { getSampleNewsData() }
    
    val filteredNews = newsArticles.filter { article ->
        val matchesCategory = selectedCategory == NewsCategory.ALL || article.category == selectedCategory
        val matchesSearch = searchQuery.isEmpty() || 
                          article.title.contains(searchQuery, ignoreCase = true) ||
                          article.summary.contains(searchQuery, ignoreCase = true) ||
                          article.portal.contains(searchQuery, ignoreCase = true)
        matchesCategory && matchesSearch
    }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundGray)
            .padding(20.dp)
    ) {
        // Header
        Text(
            "Financial News",
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.Bold,
                color = TextPrimary
            ),
            modifier = Modifier.padding(bottom = 20.dp)
        )
        
        // Search Bar
        NewsSearchBar(
            searchQuery = searchQuery,
            onSearchQueryChange = { searchQuery = it },
            modifier = Modifier.padding(bottom = 20.dp)
        )
        
        // Category Tabs
        NewsCategoryTabs(
            selectedCategory = selectedCategory,
            onCategorySelected = { selectedCategory = it },
            modifier = Modifier.padding(bottom = 20.dp)
        )
        
        // News List
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(filteredNews) { article ->
                NewsCard(article = article)
            }
            
            if (filteredNews.isEmpty()) {
                item {
                    EmptyNewsState(
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
fun NewsSearchBar(
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
                "Search news...",
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
fun NewsCategoryTabs(
    selectedCategory: NewsCategory,
    onCategorySelected: (NewsCategory) -> Unit,
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
            NewsCategoryTab(
                text = "All",
                isSelected = selectedCategory == NewsCategory.ALL,
                onClick = { onCategorySelected(NewsCategory.ALL) },
                modifier = Modifier.weight(1f)
            )
            
            NewsCategoryTab(
                text = "IHSG",
                isSelected = selectedCategory == NewsCategory.IHSG,
                onClick = { onCategorySelected(NewsCategory.IHSG) },
                modifier = Modifier.weight(1f)
            )
            
            NewsCategoryTab(
                text = "Crypto",
                isSelected = selectedCategory == NewsCategory.CRYPTO,
                onClick = { onCategorySelected(NewsCategory.CRYPTO) },
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun NewsCategoryTab(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val backgroundColor = if (isSelected) {
        when (text) {
            "IHSG" -> PrimaryBlue
            "Crypto" -> Orange40
            else -> Green40
        }
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
fun NewsCard(
    article: NewsArticle,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { /* Handle click if needed */ },
        colors = CardDefaults.cardColors(containerColor = CardWhite),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // Breaking news badge
            if (article.isBreaking) {
                Row(
                    modifier = Modifier.padding(bottom = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .background(
                                color = Red40,
                                shape = RoundedCornerShape(12.dp)
                            )
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Text(
                            "BREAKING",
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        )
                    }
                }
            }
            
            // Category badge
            Row(
                modifier = Modifier.padding(bottom = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .background(
                            color = when (article.category) {
                                NewsCategory.IHSG -> PrimaryBlue.copy(alpha = 0.1f)
                                NewsCategory.CRYPTO -> Orange40.copy(alpha = 0.1f)
                                NewsCategory.ALL -> Green40.copy(alpha = 0.1f)
                            },
                            shape = RoundedCornerShape(8.dp)
                        )
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        article.category.name,
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.Medium,
                            color = when (article.category) {
                                NewsCategory.IHSG -> PrimaryBlue
                                NewsCategory.CRYPTO -> Orange40
                                NewsCategory.ALL -> Green40
                            }
                        )
                    )
                }
            }
            
            // Title
            Text(
                text = article.title,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = TextPrimary,
                    lineHeight = 20.sp
                ),
                modifier = Modifier.padding(bottom = 8.dp)
            )
            
            // Summary
            Text(
                text = article.summary,
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = TextSecondary,
                    lineHeight = 18.sp
                ),
                modifier = Modifier.padding(bottom = 12.dp)
            )
            
            // Portal and Date
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = article.portal,
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontWeight = FontWeight.Medium,
                        color = PrimaryBlue
                    )
                )
                
                Text(
                    text = article.publishedDate,
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = TextSecondary
                    )
                )
            }
        }
    }
}

@Composable
fun EmptyNewsState(
    searchQuery: String,
    category: NewsCategory
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            Icons.Filled.TrendingUp,
            contentDescription = "No news",
            tint = TextSecondary,
            modifier = Modifier.size(48.dp)
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Text(
            text = if (searchQuery.isEmpty()) {
                "No news available for ${category.name} category"
            } else {
                "No news found for \"$searchQuery\""
            },
            style = MaterialTheme.typography.bodyLarge.copy(
                color = TextSecondary,
                fontWeight = FontWeight.Medium
            )
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        Text(
            text = if (searchQuery.isEmpty()) {
                "Check back later for updates"
            } else {
                "Try different keywords"
            },
            style = MaterialTheme.typography.bodyMedium.copy(
                color = TextSecondary
            )
        )
    }
}