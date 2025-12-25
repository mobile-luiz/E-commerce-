package com.sldagemapp.myapplication

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailScreen(
    viewModel: EcommerceViewModel,
    navController: NavController
) {
    val selectedProduct by viewModel.selectedProduct.collectAsState()

    if (selectedProduct == null) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
        return
    }

    val product = selectedProduct!!
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalhes do Produto") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Voltar")
                    }
                }
            )
        },
        bottomBar = {
            Surface(
                tonalElevation = 8.dp,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Button(
                        onClick = { viewModel.addToCart(product) },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Adicionar ao Carrinho")
                    }

                    FilledTonalButton(
                        onClick = { /* TODO: Buy Now */ },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Comprar Agora")
                    }
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(scrollState)
        ) {
            // Product Image
            AsyncImage(
                model = product.imageUrl,
                contentDescription = product.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                contentScale = ContentScale.Crop
            )

            // Product Info
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = product.name,
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Rating and Reviews
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Filled.Star,
                        contentDescription = "Rating",
                        tint = Color(0xFFFFC107),
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = product.rating.toString(),
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "(${product.reviewCount} avaliações)",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Gray
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Price
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "R$ ${String.format("%.2f", product.price)}",
                        style = MaterialTheme.typography.displaySmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )

                    product.originalPrice?.let { originalPrice ->
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            text = "R$ ${String.format("%.2f", originalPrice)}",
                            style = MaterialTheme.typography.titleLarge,
                            color = Color.Gray,
                            modifier = Modifier.drawStrikeThrough()
                        )
                    }

                    product.discount?.let { discount ->
                        Spacer(modifier = Modifier.width(12.dp))
                        Surface(
                            color = Color.Red.copy(alpha = 0.1f),
                            shape = MaterialTheme.shapes.small
                        ) {
                            Text(
                                text = "$discount% OFF",
                                color = Color.Red,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Description
                Text(
                    text = "Descrição",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = product.description,
                    style = MaterialTheme.typography.bodyLarge,
                    lineHeight = MaterialTheme.typography.bodyLarge.lineHeight * 1.2
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Stock Status
                Surface(
                    color = if (product.inStock) Color.Green.copy(alpha = 0.1f)
                    else Color.Red.copy(alpha = 0.1f),
                    shape = MaterialTheme.shapes.small,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Surface(
                            color = if (product.inStock) Color.Green else Color.Red,
                            shape = androidx.compose.foundation.shape.CircleShape,
                            modifier = Modifier.size(8.dp)
                        ) {}
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = if (product.inStock) "Em estoque" else "Esgotado",
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
        }
    }
}