package com.sldagemapp.myapplication

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas

@Composable
fun ProductCard(
    product: com.sldagemapp.myapplication.Product,
    onProductClick: () -> Unit,
    onAddToCart: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onProductClick),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            // Product Image
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
            ) {
                AsyncImage(
                    model = product.imageUrl,
                    contentDescription = product.name,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop
                )

                // Discount Badge
                product.discount?.let { discount ->
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(8.dp)
                    ) {
                        Surface(
                            color = Color.Red,
                            shape = RoundedCornerShape(4.dp)
                        ) {
                            Text(
                                text = "-$discount%",
                                color = Color.White,
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Product Name
            Text(
                text = product.name,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                maxLines = 2
            )

            Spacer(modifier = Modifier.height(4.dp))

            // Rating
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    Icons.Filled.Star,
                    contentDescription = "Rating",
                    tint = Color(0xFFFFC107),
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = product.rating.toString(),
                    style = MaterialTheme.typography.labelMedium
                )
                Text(
                    text = " (${product.reviewCount})",
                    style = MaterialTheme.typography.labelMedium,
                    color = Color.Gray
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Price
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "R$ ${String.format("%.2f", product.price)}",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )

                product.originalPrice?.let { originalPrice ->
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "R$ ${String.format("%.2f", originalPrice)}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Gray,
                        modifier = Modifier.drawStrikeThrough()
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Add to Cart Button
            FilledTonalButton(
                onClick = onAddToCart,
                modifier = Modifier.fillMaxWidth(),
                enabled = product.inStock
            ) {
                Text(
                    text = if (product.inStock) "Adicionar ao Carrinho" else "Esgotado",
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun Modifier.drawStrikeThrough(): Modifier = this.then(
    Modifier.drawWithContent {
        drawContent()
        drawLine(
            color = Color.Gray,
            start = Offset(0f, size.height / 2),
            end = Offset(size.width, size.height / 2),
            strokeWidth = 1.dp.toPx()
        )
    }
)