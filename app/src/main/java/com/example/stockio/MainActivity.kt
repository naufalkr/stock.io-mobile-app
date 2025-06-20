package com.example.stockio

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.stockio.components.screens.*
import com.example.stockio.components.navigation.*
import com.example.stockio.components.dialogs.*
import com.example.stockio.model.*
import com.example.stockio.ui.theme.stockioTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            stockioTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = BackgroundGray
                ) {
                    stockioInvestasiApp()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun stockioInvestasiApp() {
    val currentScreen = remember { mutableStateOf(Screen.HOME) }
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    // State untuk data investasi
    var balance by remember { mutableStateOf(10000000.0) } // Saldo awal 10 juta
    var portfolioValue by remember { mutableStateOf(0.0) }
    var assets by remember { mutableStateOf(emptyList<InvestmentAsset>()) }
    var marketAssets by remember { mutableStateOf(getSampleMarketData()) }
    var showBuyDialog by remember { mutableStateOf(false) }
    var selectedAsset by remember { mutableStateOf<InvestmentAsset?>(null) }
    var isBalanceVisible by remember { mutableStateOf(true) }

    // Update portfolio value ketika assets berubah
    LaunchedEffect(assets) {
        portfolioValue = assets.sumOf { it.currentPrice * it.quantity }
    }

    Scaffold(
        containerColor = BackgroundGray,
        topBar = {
            if (currentScreen.value != Screen.ASSET_DETAIL) {
                ModernTopAppBar(
                    onNotificationClick = {
                        scope.launch {
                            snackbarHostState.showSnackbar("Notifikasi ditampilkan")
                        }
                    }
                )
            }
        },
        bottomBar = {
            if (currentScreen.value != Screen.ASSET_DETAIL) {
                ModernBottomNavigationBar(currentScreen = currentScreen.value) { screen ->
                    currentScreen.value = screen
                }
            }
        },
        floatingActionButton = {
            if (currentScreen.value == Screen.HOME) {
                FloatingActionButton(
                    onClick = { /* Aksi tambah cepat */ },
                    containerColor = PrimaryBlue,
                    contentColor = Color.White,
                    modifier = Modifier.shadow(16.dp, shape = CircleShape)
                ) {
                    Icon(Icons.Filled.Add, "Tambah cepat", modifier = Modifier.size(24.dp))
                }
            }
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(if (currentScreen.value == Screen.ASSET_DETAIL) PaddingValues(0.dp) else innerPadding)) {
            when (currentScreen.value) {
                Screen.HOME -> ModernHomeScreen(
                    balance = balance,
                    portfolioValue = portfolioValue,
                    assets = assets,
                    marketAssets = marketAssets,
                    isBalanceVisible = isBalanceVisible,
                    onToggleBalance = { isBalanceVisible = !isBalanceVisible },
                    onAssetClick = { asset ->
                        selectedAsset = asset
                        currentScreen.value = Screen.ASSET_DETAIL
                    },
                    onRefreshMarketData = {
                        marketAssets = getSampleMarketData()
                        scope.launch {
                            snackbarHostState.showSnackbar("Data pasar diperbarui")
                        }
                    }
                )
                Screen.MARKET -> ModernMarketScreen(
                    marketAssets = marketAssets,
                    onAssetClick = { asset ->
                        selectedAsset = asset
                        currentScreen.value = Screen.ASSET_DETAIL
                    }
                )
                Screen.PORTFOLIO -> ModernPortfolioScreen(
                    assets = assets,
                    portfolioValue = portfolioValue,
                    onSellAsset = { asset ->
                        assets = assets.filter { it.id != asset.id }
                        balance += asset.currentPrice * asset.quantity
                        scope.launch {
                            snackbarHostState.showSnackbar("Aset berhasil dijual")
                        }
                    }
                )
                Screen.PROFILE -> ModernProfileScreen()
                
                Screen.ASSET_DETAIL -> selectedAsset?.let { asset ->
                    AssetDetailScreen(
                        asset = asset,
                        onBackClick = {
                            currentScreen.value = Screen.MARKET
                        },
                        onBuyClick = {
                            showBuyDialog = true
                        }
                    )
                }
            }

            // Dialog beli aset
            selectedAsset?.let { asset ->
                if (showBuyDialog) {
                    ModernBuyAssetDialog(
                        asset = asset,
                        balance = balance,
                        onBuy = { quantity ->
                            if (quantity > 0) {
                                val existingAsset = assets.find { it.id == asset.id }
                                if (existingAsset != null) {
                                    assets = assets.map {
                                        if (it.id == asset.id) {
                                            it.copy(quantity = it.quantity + quantity)
                                        } else {
                                            it
                                        }
                                    }
                                } else {
                                    assets = assets + asset.copy(quantity = quantity)
                                }
                                balance -= asset.currentPrice * quantity
                                scope.launch {
                                    snackbarHostState.showSnackbar("Pembelian berhasil!")
                                }
                            }
                            showBuyDialog = false
                        },
                        onDismiss = { showBuyDialog = false }
                    )
                }
            }
        }
    }
}