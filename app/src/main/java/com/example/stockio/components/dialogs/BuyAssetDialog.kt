package com.example.stockio.components.dialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.stockio.model.*
import java.text.NumberFormat
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModernBuyAssetDialog(
    asset: InvestmentAsset,
    balance: Double,
    onBuy: (Double) -> Unit, // Changed from Int to Double to handle decimal quantities
    onDismiss: () -> Unit
) {
    var inputValue by remember { mutableStateOf("") }
    val currencyFormat = remember { NumberFormat.getCurrencyInstance(Locale("id", "ID")) }
    
    val isStock = asset.category == AssetCategory.IHSG
    val isValid: Boolean
    val totalCost: Double
    val quantity: Double
    
    if (isStock) {
        // For stocks: input is lot quantity
        val lotQuantity = inputValue.toIntOrNull() ?: 0
        quantity = lotQuantity.toDouble()
        totalCost = lotQuantity * asset.currentPrice
        isValid = lotQuantity > 0 && totalCost <= balance
    } else {
        // For crypto: input is rupiah amount
        val rupiaAmount = inputValue.toDoubleOrNull() ?: 0.0
        totalCost = rupiaAmount
        quantity = if (asset.currentPrice > 0) rupiaAmount / asset.currentPrice else 0.0
        isValid = rupiaAmount > 0 && rupiaAmount <= balance
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        modifier = Modifier.clip(RoundedCornerShape(20.dp)),
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .background(
                            asset.color.copy(alpha = 0.2f),
                            shape = CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = asset.icon,
                        contentDescription = asset.name,
                        tint = asset.color,
                        modifier = Modifier.size(20.dp)
                    )
                }
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    "Buy ${asset.name}",
                    style = MaterialTheme.typography.headlineSmall.copy(
                        color = TextPrimary,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                // Price info card
                Card(
                    colors = CardDefaults.cardColors(containerColor = BackgroundGray),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            if (isStock) "Price per lot" else "Current price",
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = TextSecondary
                            )
                        )
                        Text(
                            "${currencyFormat.format(asset.currentPrice)}${if (isStock) " / lot" else " / ${asset.code}"}",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = TextPrimary
                            )
                        )
                    }
                }

                // Input field
                OutlinedTextField(
                    value = inputValue,
                    onValueChange = { newValue ->
                        if (isStock) {
                            // For stocks: only allow integer input
                            if (newValue.isEmpty() || newValue.toIntOrNull() != null) {
                                inputValue = newValue
                            }
                        } else {
                            // For crypto: allow decimal input
                            if (newValue.isEmpty() || newValue.toDoubleOrNull() != null) {
                                inputValue = newValue
                            }
                        }
                    },
                    label = { 
                        Text(if (isStock) "Quantity (lots)" else "Amount (Rp)")
                    },
                    placeholder = {
                        Text(if (isStock) "Enter number of lots" else "Enter rupiah amount")
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = PrimaryBlue,
                        focusedLabelColor = PrimaryBlue
                    ),
                    prefix = if (!isStock) {{ Text("Rp ") }} else null
                )

                // Result card
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = if (totalCost > balance) Red40.copy(alpha = 0.1f) else Green40.copy(alpha = 0.1f)
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        if (isStock) {
                            Text(
                                "Total cost",
                                style = MaterialTheme.typography.bodySmall.copy(
                                    color = TextSecondary
                                )
                            )
                            Text(
                                currencyFormat.format(totalCost),
                                style = MaterialTheme.typography.titleLarge.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = if (totalCost > balance) Red40 else Green40
                                )
                            )
                        } else {
                            Text(
                                "You will get",
                                style = MaterialTheme.typography.bodySmall.copy(
                                    color = TextSecondary
                                )
                            )
                            Text(
                                "${String.format("%.8f", quantity)} ${asset.code}",
                                style = MaterialTheme.typography.titleLarge.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = if (totalCost > balance) Red40 else Green40
                                )
                            )
                        }
                        
                        if (totalCost > balance) {
                            Text(
                                "Insufficient balance",
                                style = MaterialTheme.typography.bodySmall.copy(
                                    color = Red40
                                )
                            )
                        }
                    }
                }
            }
        },
        confirmButton = {
            Button(
                onClick = { onBuy(quantity) },
                enabled = isValid,
                colors = ButtonDefaults.buttonColors(
                    containerColor = PrimaryBlue,
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.height(48.dp)
            ) {
                Text(
                    "Buy now", 
                    fontWeight = FontWeight.Bold
                )
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismiss,
                modifier = Modifier.height(48.dp)
            ) {
                Text(
                    "Cancel", 
                    color = TextSecondary,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    )
}
