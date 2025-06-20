package com.example.stockio.components.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stockio.model.*
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*

enum class ChartPeriod(val displayName: String, val days: Int) {
    WEEK("7D", 7),
    MONTH("30D", 30),
    ALL("All", 100)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AssetDetailScreen(
    asset: InvestmentAsset,
    onBackClick: () -> Unit,
    onBuyClick: () -> Unit
) {
    var selectedPeriod by remember { mutableStateOf(ChartPeriod.WEEK) }
    val scrollState = rememberScrollState()
    
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundGray)
        ) {
            // Top App Bar
            TopAppBar(
                title = { 
                    Text(
                        asset.code,
                        fontWeight = FontWeight.Bold,
                        color = TextPrimary
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            Icons.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = TextPrimary
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent
                )
            )
            
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
                    .padding(horizontal = 20.dp)
                    .padding(bottom = 100.dp)
            ) {
                // Asset Header
                AssetHeaderCard(asset)
                
                Spacer(modifier = Modifier.height(20.dp))
                
                // Chart Section
                ChartCard(
                    asset = asset,
                    selectedPeriod = selectedPeriod,
                    onPeriodSelected = { selectedPeriod = it }
                )
                
                Spacer(modifier = Modifier.height(20.dp))
                
                // Asset Info
                AssetInfoCard(asset)
                
                Spacer(modifier = Modifier.height(20.dp))
                
                // Description
                DescriptionCard(asset)
            }
        }
        
        // Floating Buy Button
        FloatingBuyButton(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(20.dp),
            onClick = onBuyClick
        )
    }
}

@Composable
fun AssetHeaderCard(asset: InvestmentAsset) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = CardWhite),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = asset.name,
                        style = MaterialTheme.typography.headlineSmall.copy(
                            fontWeight = FontWeight.Bold,
                            color = TextPrimary
                        )
                    )
                    Text(
                        text = asset.code,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = TextSecondary
                        )
                    )
                }
                
                Icon(
                    imageVector = asset.icon,
                    contentDescription = null,
                    tint = asset.color,
                    modifier = Modifier.size(40.dp)
                )
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                Column {
                    Text(
                        text = formatCurrency(asset.currentPrice),
                        style = MaterialTheme.typography.headlineMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = TextPrimary
                        )
                    )
                    
                    val changeColor = if (asset.priceChangePercent >= 0) Green40 else Red40
                    val changeText = if (asset.priceChangePercent >= 0) "+" else ""
                    
                    Text(
                        text = "$changeText${String.format("%.2f", asset.priceChangePercent)}%",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.Medium,
                            color = changeColor
                        )
                    )
                }
                
                Box(
                    modifier = Modifier
                        .background(
                            color = if (asset.category == AssetCategory.STOCK) PrimaryBlue else Orange40,
                            shape = RoundedCornerShape(20.dp)
                        )
                        .padding(horizontal = 12.dp, vertical = 6.dp)
                ) {
                    Text(
                        text = asset.category.name,
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontWeight = FontWeight.Medium,
                            color = Color.White
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun ChartCard(
    asset: InvestmentAsset,
    selectedPeriod: ChartPeriod,
    onPeriodSelected: (ChartPeriod) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = CardWhite),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Text(
                text = "Price Chart",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = TextPrimary
                )
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Period Selector
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                ChartPeriod.values().forEach { period ->
                    PeriodChip(
                        period = period,
                        isSelected = period == selectedPeriod,
                        onClick = { onPeriodSelected(period) },
                        modifier = Modifier.weight(1f)
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(20.dp))
              // Chart
            PriceChart(
                priceHistory = asset.priceHistory.takeLast(selectedPeriod.days),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp),
                color = asset.color
            )
        }
    }
}

@Composable
fun PeriodChip(
    period: ChartPeriod,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    FilterChip(
        selected = isSelected,
        onClick = onClick,
        label = {
            Text(
                text = period.displayName,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Medium
                )
            )
        },
        modifier = modifier,
        colors = FilterChipDefaults.filterChipColors(
            selectedContainerColor = PrimaryBlue,
            selectedLabelColor = Color.White,
            containerColor = Color.Transparent,
            labelColor = TextSecondary
        ),
        border = FilterChipDefaults.filterChipBorder(
            enabled = true,
            selected = isSelected,
            borderColor = if (isSelected) PrimaryBlue else DividerGray,
            selectedBorderColor = PrimaryBlue
        )
    )
}

@Composable
fun PriceChart(
    priceHistory: List<Double>,
    modifier: Modifier = Modifier,
    color: Color = PrimaryBlue
) {
    if (priceHistory.isEmpty()) return
    
    Canvas(modifier = modifier) {
        drawPriceChart(priceHistory, color)
    }
}

fun DrawScope.drawPriceChart(priceHistory: List<Double>, color: Color) {
    if (priceHistory.size < 2) return
    
    val minPrice = priceHistory.minOrNull() ?: 0.0
    val maxPrice = priceHistory.maxOrNull() ?: 1.0
    val priceRange = if (maxPrice > minPrice) maxPrice - minPrice else 1.0
    
    // Create smooth curve path
    val path = Path()
    val stepX = size.width / (priceHistory.size - 1)
    val points = mutableListOf<Offset>()
      // Generate points
    priceHistory.forEachIndexed { index, price ->
        val x = index * stepX
        val y = size.height - ((price - minPrice) / priceRange * size.height * 0.8f) - size.height * 0.1f
        points.add(Offset(x, y.toFloat()))
    }
    
    // Create smooth curved path
    if (points.isNotEmpty()) {
        path.moveTo(points[0].x, points[0].y)
        
        for (i in 1 until points.size) {
            val prev = points[i - 1]
            val current = points[i]
            
            if (i == 1) {
                path.lineTo(current.x, current.y)
            } else {
                val midX = (prev.x + current.x) / 2
                path.quadraticBezierTo(prev.x, prev.y, midX, (prev.y + current.y) / 2)
                if (i == points.size - 1) {
                    path.lineTo(current.x, current.y)
                }
            }
        }
    }
    
    // Draw gradient fill
    val fillPath = Path().apply {
        addPath(path)
        lineTo(size.width, size.height)
        lineTo(0f, size.height)
        close()
    }
    
    drawPath(
        path = fillPath,
        brush = androidx.compose.ui.graphics.Brush.verticalGradient(
            colors = listOf(
                color.copy(alpha = 0.3f),
                color.copy(alpha = 0.1f),
                Color.Transparent
            ),
            startY = 0f,
            endY = size.height
        )
    )
    
    // Draw grid lines
    val gridColor = Color.Gray.copy(alpha = 0.2f)
    val gridLines = 4
    
    // Horizontal grid lines
    for (i in 1 until gridLines) {
        val y = (size.height / gridLines) * i
        drawLine(
            color = gridColor,
            start = Offset(0f, y),
            end = Offset(size.width, y),
            strokeWidth = 1.dp.toPx()
        )
    }
    
    // Vertical grid lines
    for (i in 1 until gridLines) {
        val x = (size.width / gridLines) * i
        drawLine(
            color = gridColor,
            start = Offset(x, 0f),
            end = Offset(x, size.height),
            strokeWidth = 1.dp.toPx()
        )
    }
    
    // Draw main line with shadow
    drawPath(
        path = path,
        color = Color.Black.copy(alpha = 0.1f),
        style = androidx.compose.ui.graphics.drawscope.Stroke(
            width = 4.dp.toPx(),
            pathEffect = androidx.compose.ui.graphics.PathEffect.cornerPathEffect(8.dp.toPx())
        )
    )
    
    drawPath(
        path = path,
        color = color,
        style = androidx.compose.ui.graphics.drawscope.Stroke(
            width = 3.dp.toPx(),
            cap = androidx.compose.ui.graphics.StrokeCap.Round,
            join = androidx.compose.ui.graphics.StrokeJoin.Round,
            pathEffect = androidx.compose.ui.graphics.PathEffect.cornerPathEffect(8.dp.toPx())
        )
    )
    
    // Draw data points
    points.forEachIndexed { index, point ->
        if (index % (points.size / 8).coerceAtLeast(1) == 0 || index == points.size - 1) {            // Outer circle (shadow)
            drawCircle(
                color = Color.Black.copy(alpha = 0.1f),
                radius = 6.dp.toPx(),
                center = Offset(point.x + 1.dp.toPx(), point.y + 1.dp.toPx())
            )
            
            // Main circle
            drawCircle(
                color = Color.White,
                radius = 5.dp.toPx(),
                center = point
            )
            
            // Inner circle
            drawCircle(
                color = color,
                radius = 3.dp.toPx(),
                center = point
            )
        }
    }
}

@Composable
fun AssetInfoCard(asset: InvestmentAsset) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = CardWhite),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Text(
                text = "Asset Information",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = TextPrimary
                )
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            InfoRow("Market Cap", formatCurrencyShort(asset.marketCap))
            InfoRow("Volume", NumberFormat.getNumberInstance(Locale.US).format(asset.volume))
            InfoRow(
                if (asset.category == AssetCategory.STOCK) "IPO Date" else "Launch Date",
                asset.ipoDate
            )
            InfoRow("Category", asset.category.name)
        }
    }
}

@Composable
fun InfoRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium.copy(
                color = TextSecondary
            )
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.Medium,
                color = TextPrimary
            )
        )
    }
}

@Composable
fun DescriptionCard(asset: InvestmentAsset) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = CardWhite),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Text(
                text = "About ${asset.name}",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = TextPrimary
                )
            )
            
            Spacer(modifier = Modifier.height(12.dp))
            
            Text(
                text = asset.description,
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = TextSecondary,
                    lineHeight = 20.sp
                )
            )
        }
    }
}

@Composable
fun FloatingBuyButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .height(56.dp)
            .clip(RoundedCornerShape(28.dp)),
        colors = ButtonDefaults.buttonColors(
            containerColor = PrimaryBlue,
            contentColor = Color.White
        ),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 8.dp,
            pressedElevation = 12.dp
        )
    ) {
        Text(
            text = "Buy Asset",
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold
            )
        )
    }
}

fun formatCurrency(amount: Double): String {
    val formatter = DecimalFormat("#,###")
    return "Rp ${formatter.format(amount)}"
}

fun formatCurrencyShort(amount: Double): String {
    val formatter = DecimalFormat("#,###")
    return when {
        amount >= 1_000_000_000 -> "Rp ${formatter.format(amount / 1_000_000_000)}B"
        amount >= 1_000_000 -> "Rp ${formatter.format(amount / 1_000_000)}M"
        amount >= 1_000 -> "Rp ${formatter.format(amount / 1_000)}K"
        else -> "Rp ${formatter.format(amount)}"
    }
}
