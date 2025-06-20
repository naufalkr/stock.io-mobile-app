package com.example.stockio.components.navigation

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Close


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModernTopAppBar(onProfileClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp)
            .height(80.dp),
        shape = RoundedCornerShape(0.dp),
        colors = CardDefaults.cardColors(containerColor = CardWhite),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {        
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(CardWhite)
                .padding(top = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            // Profile Avatar (Left)
            Box(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 16.dp)
            ) {
                Card(
                    modifier = Modifier
                        .size(40.dp)
                        .clickable { onProfileClick() },
                    shape = RoundedCornerShape(8.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = PrimaryBlue.copy(alpha = 0.15f)
                    ),
                    border = BorderStroke(1.dp, PrimaryBlue.copy(alpha = 0.2f))
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = "Menu",
                            tint = PrimaryBlue,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            }
            
            // Center Logo
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
                    text = "Stock.io",
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
            .height(80.dp), // Increased height for better spacing
        shape = RoundedCornerShape(0.dp),
        colors = CardDefaults.cardColors(containerColor = CardWhite),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(CardWhite)
                .padding(vertical = 8.dp) // Added vertical padding
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Home Item
                CleanNavItem(
                    icon = Icons.Filled.Home,
                    label = "Home",
                    isSelected = currentScreen == Screen.HOME,
                    onClick = { onScreenSelected(Screen.HOME) }
                )
                
                // Market Item
                CleanNavItem(
                    icon = Icons.Filled.TrendingUp,
                    label = "Market",
                    isSelected = currentScreen == Screen.MARKET,
                    onClick = { onScreenSelected(Screen.MARKET) }
                )
                
                // Portfolio Item
                CleanNavItem(
                    icon = Icons.Filled.AccountBalanceWallet,
                    label = "Portfolio",
                    isSelected = currentScreen == Screen.PORTFOLIO,
                    onClick = { onScreenSelected(Screen.PORTFOLIO) }
                )
                
                // News Item
                CleanNavItem(
                    icon = Icons.Filled.Article,
                    label = "News",
                    isSelected = currentScreen == Screen.NEWS,
                    onClick = { onScreenSelected(Screen.NEWS) }
                )
            }
        }
    }
}

@Composable
fun CleanNavItem(
    icon: ImageVector,
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val animatedColor by animateColorAsState(
        targetValue = if (isSelected) PrimaryBlue else TextSecondary,
        label = "nav_color"
    )
    
    val animatedScale by animateFloatAsState(
        targetValue = if (isSelected) 1.1f else 1.0f,
        label = "nav_scale"
    )
    
    Column(
        modifier = Modifier
            .clickable { onClick() }
            .padding(vertical = 4.dp, horizontal = 8.dp)
            .graphicsLayer {
                scaleX = animatedScale
                scaleY = animatedScale
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Simple icon without background container
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = animatedColor,
            modifier = Modifier.size(24.dp)
        )
        
        Spacer(modifier = Modifier.height(4.dp))
        
        // Label
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall.copy(
                fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal,
                fontSize = 10.sp
            ),
            color = animatedColor,
            maxLines = 1
        )
        
        // Selection indicator
        if (isSelected) {
            Spacer(modifier = Modifier.height(2.dp))
            Box(
                modifier = Modifier
                    .size(4.dp)
                    .background(
                        color = PrimaryBlue,
                        shape = CircleShape
                    )
            )
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

@Composable
fun ProfileSidebar(
    user: User,
    onProfileClick: () -> Unit,
    onLogoutClick: () -> Unit,
    onDismiss: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable { onDismiss() }
    ) {
        Card(
            modifier = Modifier
                .width(280.dp)
                .fillMaxHeight()
                .clickable(enabled = false) { /* Prevent clicks through the card */ },
            colors = CardDefaults.cardColors(containerColor = CardWhite),
            shape = RoundedCornerShape(topEnd = 24.dp, bottomEnd = 24.dp),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 24.dp)
            ) {
                // Header with profile info
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp, vertical = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Profile avatar
                    Box(
                        modifier = Modifier
                            .size(80.dp)
                            .background(
                                brush = Brush.radialGradient(
                                    colors = listOf(
                                        PrimaryBlue.copy(alpha = 0.3f),
                                        PrimaryBlue.copy(alpha = 0.1f)
                                    )
                                ),
                                shape = CircleShape
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Person,
                            contentDescription = "Profile",
                            tint = PrimaryBlue,
                            modifier = Modifier.size(40.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = user.name,
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold,
                            color = TextPrimary
                        )
                    )

                    Text(
                        text = user.email,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = TextSecondary
                        )
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))
                Divider(color = DividerGray, thickness = 1.dp)

                // Menu items
                SidebarMenuItem(
                    icon = Icons.Filled.Person,
                    title = "Profile",
                    onClick = onProfileClick
                )
                
                SidebarMenuItem(
                    icon = Icons.Filled.Settings,
                    title = "Settings",
                    onClick = { /* Will be implemented later */ }
                )
                
                SidebarMenuItem(
                    icon = Icons.Filled.Info,
                    title = "About App",
                    onClick = { /* Will be implemented later */ }
                )
                
                SidebarMenuItem(
                    icon = Icons.Filled.Help,
                    title = "Help",
                    onClick = { /* Will be implemented later */ }
                )

                Spacer(modifier = Modifier.weight(1f))
                Divider(color = DividerGray, thickness = 1.dp)
                
                // Logout button
                SidebarMenuItem(
                    icon = Icons.Filled.ExitToApp,
                    title = "Logout",
                    textColor = Red40,
                    iconColor = Red40,
                    onClick = onLogoutClick
                )
            }
        }
    }
}

@Composable
fun SidebarMenuItem(
    icon: ImageVector,
    title: String,
    textColor: Color = TextPrimary,
    iconColor: Color = PrimaryBlue,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 12.dp, horizontal = 24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(iconColor.copy(alpha = 0.1f), RoundedCornerShape(8.dp)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                tint = iconColor,
                modifier = Modifier.size(20.dp)
            )
        }
        
        Spacer(modifier = Modifier.width(16.dp))
        
        Text(
            text = title,
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Medium,
                color = textColor
            )
        )
    }
}
