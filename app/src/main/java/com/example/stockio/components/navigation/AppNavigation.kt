package com.example.stockio.components.navigation

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stockio.model.*
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.foundation.BorderStroke



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModernTopAppBar(onNotificationClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp),
        shape = RoundedCornerShape(0.dp),
        colors = CardDefaults.cardColors(containerColor = CardWhite),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(CardWhite)
                .padding(top = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Logo Container
                Card(
                    modifier = Modifier.size(48.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = PrimaryBlue
                    ),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Filled.TrendingUp,
                            contentDescription = "Stockio Logo",
                            tint = Color.White,
                            modifier = Modifier.size(28.dp)
                        )
                    }
                }
                
                // App Name
                Text(
                    text = "Stockio",
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.ExtraBold,
                        color = PrimaryBlue,
                        fontSize = 28.sp
                    )
                )
            }
        }
    }
}

@Composable
fun ModernBottomNavigationBar(currentScreen: Screen, onScreenSelected: (Screen) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp),
        shape = RoundedCornerShape(0.dp),
        colors = CardDefaults.cardColors(containerColor = CardWhite),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(CardWhite)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Home Item
                ModernNavItem(
                    icon = Icons.Filled.Home,
                    label = "Beranda",
                    isSelected = currentScreen == Screen.HOME,
                    onClick = { onScreenSelected(Screen.HOME) }
                )
                
                // Market Item
                ModernNavItem(
                    icon = Icons.Filled.TrendingUp,
                    label = "Pasar",
                    isSelected = currentScreen == Screen.MARKET,
                    onClick = { onScreenSelected(Screen.MARKET) }
                )
                
                // Portfolio Item
                ModernNavItem(
                    icon = Icons.Filled.AccountBalanceWallet,
                    label = "Portofolio",
                    isSelected = currentScreen == Screen.PORTFOLIO,
                    onClick = { onScreenSelected(Screen.PORTFOLIO) }
                )
                
                // Profile Item
                ModernNavItem(
                    icon = Icons.Filled.Person,
                    label = "Profil",
                    isSelected = currentScreen == Screen.PROFILE,
                    onClick = { onScreenSelected(Screen.PROFILE) }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModernNavItem(
    icon: ImageVector,
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val animatedColor by animateColorAsState(
        targetValue = if (isSelected) PrimaryBlue else TextSecondary,
        label = "nav_color"
    )
    
    val animatedBackgroundAlpha by animateFloatAsState(
        targetValue = if (isSelected) 0.12f else 0f,
        label = "nav_background"
    )
    
    val animatedIconBackgroundAlpha by animateFloatAsState(
        targetValue = if (isSelected) 0.15f else 0f,
        label = "nav_icon_background"
    )
    
    Column(
        modifier = Modifier
            .size(width = 70.dp, height = 66.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Main container
        Card(
            onClick = onClick,
            modifier = Modifier
                .size(width = 64.dp, height = 52.dp),
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(
                containerColor = PrimaryBlue.copy(alpha = animatedBackgroundAlpha)
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = if (isSelected) 2.dp else 0.dp
            ),
            border = if (isSelected) {
                BorderStroke(1.dp, PrimaryBlue.copy(alpha = 0.2f))
            } else null
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 6.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // Icon Container
                Box(
                    modifier = Modifier
                        .size(28.dp)
                        .background(
                            color = PrimaryBlue.copy(alpha = animatedIconBackgroundAlpha),
                            shape = RoundedCornerShape(8.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = label,
                        tint = animatedColor,
                        modifier = Modifier.size(18.dp)
                    )
                }
                
                Spacer(modifier = Modifier.height(3.dp))
                
                // Label
                Text(
                    text = label,
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Medium,
                        fontSize = 10.sp
                    ),
                    color = animatedColor,
                    maxLines = 1
                )
            }
        }
    }
}
