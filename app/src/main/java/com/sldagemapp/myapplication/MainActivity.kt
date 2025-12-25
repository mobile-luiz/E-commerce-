package com.sldagemapp.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.lifecycleScope
import com.sldagemapp.myapplication.ui.theme.EcommerceAppTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    private val viewModel: EcommerceViewModel by viewModels()
    private var showSplashScreen by mutableStateOf(true)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            EcommerceAppTheme {
                if (showSplashScreen) {
                    SplashScreen()
                } else {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        EcommerceNavigation(viewModel = viewModel)
                    }
                }
            }
        }

        // Simular carregamento inicial
        lifecycleScope.launchWhenCreated {
            delay(1500)
            showSplashScreen = false
        }
    }

    @Composable
    private fun SplashScreen() {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.primary,
                            MaterialTheme.colorScheme.secondary
                        )
                    )
                ),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                // Logo
                Icon(
                    imageVector = Icons.Filled.ShoppingCart,
                    contentDescription = "Logo",
                    modifier = Modifier.size(100.dp),
                    tint = Color.White
                )

                // Texto
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "STYLESHOP",
                        style = MaterialTheme.typography.displayLarge.copy(
                            fontWeight = FontWeight.ExtraBold,
                            color = Color.White
                        ),
                        letterSpacing = 2.sp
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Elegância em cada compra",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Medium,
                            color = Color.White.copy(alpha = 0.9f)
                        )
                    )
                }

                // Loading
                CircularProgressIndicator(
                    modifier = Modifier.size(40.dp),
                    color = Color.White,
                    strokeWidth = 3.dp
                )
            }
        }
    }
}