package com.sldagemapp.myapplication

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun EcommerceNavigation(viewModel: EcommerceViewModel) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        // Tela inicial
        composable("home") {
            HomeScreen(
                viewModel = viewModel,
                navController = navController
            )
        }

        // Detalhes do produto
        composable(
            "product/{productId}",
            arguments = listOf(navArgument("productId") { type = NavType.IntType })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getInt("productId")
            ProductDetailScreen(
                viewModel = viewModel,
                navController = navController,
              //  productId = productId
            )
        }

        // Carrinho
        composable("cart") {
            CartScreen(
                viewModel = viewModel,
                navController = navController
            )
        }

        // Checkout
     //   composable("checkout") {
      //      CheckoutScreen(
      //          viewModel = viewModel,
       //         navController = navController
       //     )
      //  }

        // Pedidos
        composable("orders") {
            OrdersScreen(
                viewModel = viewModel,
                navController = navController
            )
        }

        // Perfil
        composable("profile") {
            ProfileScreen(
                viewModel = viewModel,
                navController = navController
            )
        }

        // Favoritos
        composable("favorites") {
            FavoritesScreen(
                viewModel = viewModel,
                navController = navController
            )
        }

        // Categorias
        composable("categories") {
            CategoriesScreen(
                viewModel = viewModel,
                navController = navController
            )
        }

        // Busca
        composable("search") {
            SearchScreen(
                viewModel = viewModel,
                navController = navController
            )
        }

        // Configurações
        composable("settings") {
            SettingsScreen(
                viewModel = viewModel,
                navController = navController
            )
        }

        // Sobre
        composable("about") {
            AboutScreen(
                viewModel = viewModel,
                navController = navController
            )
        }

        // Ajuda/FAQ
        composable("help") {
            HelpScreen(
                viewModel = viewModel,
                navController = navController
            )
        }

        // Detalhes do pedido
        composable(
            "order/{orderId}",
            arguments = listOf(navArgument("orderId") { type = NavType.StringType })
        ) { backStackEntry ->
            val orderId = backStackEntry.arguments?.getString("orderId")
            OrderDetailScreen(
                viewModel = viewModel,
                navController = navController,
                orderId = orderId
            )
        }

        // Endereços
        composable("addresses") {
            AddressesScreen(
                viewModel = viewModel,
                navController = navController
            )
        }

        // Formas de pagamento
        composable("payment-methods") {
            PaymentMethodsScreen(
                viewModel = viewModel,
                navController = navController
            )
        }

        // Cupons
        composable("coupons") {
            CouponsScreen(
                viewModel = viewModel,
                navController = navController
            )
        }

        // Notificações
        composable("notifications") {
            NotificationsScreen(
                viewModel = viewModel,
                navController = navController
            )
        }
    }
}

// Telas básicas de gerenciamento (você pode implementar depois)

@Composable
fun OrdersScreen(
    viewModel: EcommerceViewModel,
    navController: androidx.navigation.NavController
) {
    androidx.compose.material3.Text("Tela de Pedidos")
}

@Composable
fun ProfileScreen(
    viewModel: EcommerceViewModel,
    navController: androidx.navigation.NavController
) {
    androidx.compose.material3.Text("Tela de Perfil")
}

@Composable
fun FavoritesScreen(
    viewModel: EcommerceViewModel,
    navController: androidx.navigation.NavController
) {
    androidx.compose.material3.Text("Tela de Favoritos")
}

@Composable
fun CategoriesScreen(
    viewModel: EcommerceViewModel,
    navController: androidx.navigation.NavController
) {
    androidx.compose.material3.Text("Tela de Categorias")
}

@Composable
fun SearchScreen(
    viewModel: EcommerceViewModel,
    navController: androidx.navigation.NavController
) {
    androidx.compose.material3.Text("Tela de Busca")
}

@Composable
fun SettingsScreen(
    viewModel: EcommerceViewModel,
    navController: androidx.navigation.NavController
) {
    androidx.compose.material3.Text("Tela de Configurações")
}

@Composable
fun AboutScreen(
    viewModel: EcommerceViewModel,
    navController: androidx.navigation.NavController
) {
    androidx.compose.material3.Text("Sobre o App")
}

@Composable
fun HelpScreen(
    viewModel: EcommerceViewModel,
    navController: androidx.navigation.NavController
) {
    androidx.compose.material3.Text("Ajuda/FAQ")
}

@Composable
fun OrderDetailScreen(
    viewModel: EcommerceViewModel,
    navController: androidx.navigation.NavController,
    orderId: String?
) {
    androidx.compose.material3.Text("Detalhes do Pedido: $orderId")
}

@Composable
fun AddressesScreen(
    viewModel: EcommerceViewModel,
    navController: androidx.navigation.NavController
) {
    androidx.compose.material3.Text("Endereços")
}

@Composable
fun PaymentMethodsScreen(
    viewModel: EcommerceViewModel,
    navController: androidx.navigation.NavController
) {
    androidx.compose.material3.Text("Formas de Pagamento")
}

@Composable
fun CouponsScreen(
    viewModel: EcommerceViewModel,
    navController: androidx.navigation.NavController
) {
    androidx.compose.material3.Text("Cupons")
}

@Composable
fun NotificationsScreen(
    viewModel: EcommerceViewModel,
    navController: androidx.navigation.NavController
) {
    androidx.compose.material3.Text("Notificações")
}