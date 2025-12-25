package com.sldagemapp.myapplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.text.NumberFormat
import java.util.*

class EcommerceViewModel : ViewModel() {

    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products: StateFlow<List<Product>> = _products

    private val _cartItems = MutableStateFlow<List<CartItem>>(emptyList())
    val cartItems: StateFlow<List<CartItem>> = _cartItems

    private val _selectedProduct = MutableStateFlow<Product?>(null)
    val selectedProduct: StateFlow<Product?> = _selectedProduct

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    private val _filteredProducts = MutableStateFlow<List<Product>>(emptyList())
    val filteredProducts: StateFlow<List<Product>> = _filteredProducts

    private val _selectedCategory = MutableStateFlow<String?>(null)
    val selectedCategory: StateFlow<String?> = _selectedCategory

    private val _showAddedToCartMessage = MutableStateFlow(false)
    val showAddedToCartMessage: StateFlow<Boolean> = _showAddedToCartMessage

    // Formatador de moeda brasileira
    private val currencyFormatter = NumberFormat.getCurrencyInstance(Locale("pt", "BR")).apply {
        maximumFractionDigits = 2
        minimumFractionDigits = 2
    }

    init {
        loadProducts()
        setupFilters()
    }

    private fun loadProducts() {
        viewModelScope.launch {
            _isLoading.value = true
            // Simular carregamento de API
            delay(1000)

            val sampleProducts = listOf(
                Product(
                    id = 1,
                    name = "Smartphone Android Premium",
                    description = "Smartphone com tela AMOLED 6.5\", 256GB, câmera tripla 108MP, processador Snapdragon 8 Gen 2, bateria de 5000mAh com carregamento rápido de 67W.",
                    price = 3299.99,
                    originalPrice = 3999.99,
                    discount = 18,
                    category = "Eletrônicos",
                    imageUrl = "https://images.unsplash.com/photo-1598327105666-5b89351aff97?w=800&auto=format&fit=crop",
                    rating = 4.7,
                    reviewCount = 328,
                    tags = listOf("Tendência", "Mais vendido", "Promoção"),
                    colors = listOf("Preto", "Branco", "Azul"),
                    sizes = listOf("Único")
                ),
                Product(
                    id = 2,
                    name = "Notebook Ultrabook Pro",
                    description = "Notebook ultrafino com tela 14\" 2.8K OLED, processador Intel i7 13ª geração, 16GB RAM, SSD 1TB, placa de vídeo dedicada RTX 4050, autonomia de até 10 horas.",
                    price = 7899.99,
                    originalPrice = 8999.99,
                    discount = 12,
                    category = "Eletrônicos",
                    imageUrl = "https://images.unsplash.com/photo-1496181133206-80ce9b88a853?w-800&auto=format&fit=crop",
                    rating = 4.9,
                    reviewCount = 156,
                    tags = listOf("Premium", "Novidade"),
                    colors = listOf("Prata", "Cinza Espacial"),
                    sizes = listOf("Único")
                ),
                Product(
                    id = 3,
                    name = "Camiseta Premium Algodão Pima",
                    description = "Camiseta 100% algodão pima de altíssima qualidade, corte slim fit, costura reforçada, disponível em diversas cores vibrantes. Ideal para uso casual ou esportivo.",
                    price = 129.90,
                    originalPrice = 159.90,
                    discount = 19,
                    category = "Vestuário",
                    imageUrl = "https://images.unsplash.com/photo-1521572163474-6864f9cf17ab?w=800&auto=format&fit=crop",
                    rating = 4.5,
                    reviewCount = 432,
                    tags = listOf("Básico", "Coleção Verão"),
                    colors = listOf("Branco", "Preto", "Azul Marinho", "Verde Militar", "Vermelho"),
                    sizes = listOf("P", "M", "G", "GG")
                ),
                Product(
                    id = 4,
                    name = "Fone Bluetooth Noise Cancelling",
                    description = "Fones de ouvido com cancelamento ativo de ruído, bateria de 30h, carregamento rápido, microfone integrado para chamadas claras e som surround premium.",
                    price = 699.90,
                    originalPrice = 899.90,
                    discount = 22,
                    category = "Eletrônicos",
                    imageUrl = "https://images.unsplash.com/photo-1505740420928-5e560c06d30e?w=800&auto=format&fit=crop",
                    rating = 4.6,
                    reviewCount = 289,
                    tags = listOf("Áudio", "Tecnologia"),
                    colors = listOf("Preto", "Branco", "Azul"),
                    sizes = listOf("Único")
                ),
                Product(
                    id = 5,
                    name = "Tênis Esportivo Running Pro",
                    description = "Tênis para corrida com tecnologia de amortecimento avançada, solado antiderrapante, material respirável e design moderno. Perfeito para atletas e amantes de esportes.",
                    price = 349.90,
                    originalPrice = 449.90,
                    discount = 22,
                    category = "Esportes",
                    imageUrl = "https://images.unsplash.com/photo-1542291026-7eec264c27ff?w=800&auto=format&fit=crop",
                    rating = 4.8,
                    reviewCount = 567,
                    tags = listOf("Esportivo", "Conforto"),
                    colors = listOf("Preto/Vermelho", "Branco/Azul", "Cinza/Laranja"),
                    sizes = listOf("36", "37", "38", "39", "40", "41", "42", "43", "44")
                ),
                Product(
                    id = 6,
                    name = "Relógio Smartwatch Elite",
                    description = "Smartwatch com monitoramento de saúde, GPS integrado, resistência à água 5ATM, tela AMOLED sempre ligada e mais de 100 modos de esporte.",
                    price = 1299.90,
                    originalPrice = 1599.90,
                    discount = 19,
                    category = "Eletrônicos",
                    imageUrl = "https://images.unsplash.com/photo-1523275335684-37898b6baf30?w=800&auto=format&fit=crop",
                    rating = 4.7,
                    reviewCount = 198,
                    tags = listOf("Smart", "Saúde"),
                    colors = listOf("Preto", "Prata", "Rose Gold"),
                    sizes = listOf("Pequeno", "Grande")
                ),
                Product(
                    id = 7,
                    name = "Mochila Executiva Premium",
                    description = "Mochila para trabalho com compartimento para notebook até 17\", organização inteligente, material à prova d'água e design profissional moderno.",
                    price = 289.90,
                    originalPrice = 349.90,
                    discount = 17,
                    category = "Acessórios",
                    imageUrl = "https://images.unsplash.com/photo-1553062407-98eeb64c6a62?w=800&auto=format&fit=crop",
                    rating = 4.4,
                    reviewCount = 123,
                    tags = listOf("Trabalho", "Organização"),
                    colors = listOf("Preto", "Cinza", "Azul Escuro"),
                    sizes = listOf("Único")
                ),
                Product(
                    id = 8,
                    name = "Perfume Essência Exclusive",
                    description = "Fragrância exclusiva com notas de madeira e frutas cítricas, duração de até 12 horas, frasco elegante em vidro lapidado.",
                    price = 459.90,
                    originalPrice = 599.90,
                    discount = 23,
                    category = "Beleza",
                    imageUrl = "https://images.unsplash.com/photo-1541643600914-78b084683601?w=800&auto=format&fit=crop",
                    rating = 4.6,
                    reviewCount = 89,
                    tags = listOf("Luxo", "Coleção Limitada"),
                    colors = listOf("Transparente"),
                    sizes = listOf("50ml", "100ml")
                )
            )

            _products.value = sampleProducts
            _filteredProducts.value = sampleProducts
            _isLoading.value = false
        }
    }

    private fun setupFilters() {
        combine(_products, _searchQuery, _selectedCategory) { products, query, category ->
            products.filter { product ->
                val matchesQuery = query.isEmpty() ||
                        product.name.contains(query, ignoreCase = true) ||
                        product.description.contains(query, ignoreCase = true) ||
                        product.category.contains(query, ignoreCase = true)

                val matchesCategory = category == null ||
                        category == "Todos" ||
                        product.category == category

                matchesQuery && matchesCategory
            }
        }.onEach { filtered ->
            _filteredProducts.value = filtered
        }.launchIn(viewModelScope)
    }

    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun setSelectedCategory(category: String?) {
        _selectedCategory.value = category
    }

    fun selectProduct(product: Product) {
        _selectedProduct.value = product
    }

    fun addToCart(product: Product) {
        viewModelScope.launch {
            val currentCart = _cartItems.value.toMutableList()
            val existingItem = currentCart.find { it.product.id == product.id }

            if (existingItem != null) {
                existingItem.quantity++
            } else {
                currentCart.add(CartItem(product, 1))
            }

            _cartItems.value = currentCart

            // Mostrar mensagem de sucesso
            _showAddedToCartMessage.value = true
            delay(2000) // Mostrar por 2 segundos
            _showAddedToCartMessage.value = false
        }
    }

    fun addToCartWithQuantity(product: Product, quantity: Int) {
        viewModelScope.launch {
            val currentCart = _cartItems.value.toMutableList()
            val existingItem = currentCart.find { it.product.id == product.id }

            if (existingItem != null) {
                existingItem.quantity += quantity
            } else {
                currentCart.add(CartItem(product, quantity))
            }

            _cartItems.value = currentCart

            _showAddedToCartMessage.value = true
            delay(2000)
            _showAddedToCartMessage.value = false
        }
    }

    fun removeFromCart(cartItem: CartItem) {
        viewModelScope.launch {
            val currentCart = _cartItems.value.toMutableList()
            currentCart.remove(cartItem)
            _cartItems.value = currentCart
        }
    }

    fun updateQuantity(cartItem: CartItem, newQuantity: Int) {
        viewModelScope.launch {
            if (newQuantity <= 0) {
                removeFromCart(cartItem)
                return@launch
            }

            val currentCart = _cartItems.value.toMutableList()
            val index = currentCart.indexOf(cartItem)
            if (index != -1) {
                currentCart[index] = cartItem.copy(quantity = newQuantity)
                _cartItems.value = currentCart
            }
        }
    }

    fun clearCart() {
        viewModelScope.launch {
            _cartItems.value = emptyList()
        }
    }

    fun getCartTotal(): Double {
        return _cartItems.value.sumOf { it.product.price * it.quantity }
    }

    fun getCartTotalFormatted(): String {
        return formatCurrency(getCartTotal())
    }

    fun getCartItemsCount(): Int {
        return _cartItems.value.sumOf { it.quantity }
    }

    // Funções de formatação
    fun formatCurrency(value: Double): String {
        return currencyFormatter.format(value)
    }

    fun formatCurrency(value: String): String {
        return try {
            currencyFormatter.format(value.toDouble())
        } catch (e: Exception) {
            "R$ 0,00"
        }
    }

    fun calculateDiscountPercentage(originalPrice: Double, currentPrice: Double): Int {
        return ((originalPrice - currentPrice) / originalPrice * 100).toInt()
    }

    fun getCategories(): List<String> {
        return listOf("Todos") + _products.value.map { it.category }.distinct().sorted()
    }

    fun getProductById(id: Int): Product? {
        return _products.value.find { it.id == id }
    }

    fun getCartItemById(productId: Int): CartItem? {
        return _cartItems.value.find { it.product.id == productId }
    }

    fun getSimilarProducts(product: Product): List<Product> {
        return _products.value
            .filter { it.category == product.category && it.id != product.id }
            .take(4)
    }

    fun getFeaturedProducts(): List<Product> {
        return _products.value
            .filter { it.rating >= 4.5 }
            .sortedByDescending { it.reviewCount }
            .take(6)
    }

    fun getTopRatedProducts(): List<Product> {
        return _products.value
            .sortedByDescending { it.rating }
            .take(8)
    }

    fun getOnSaleProducts(): List<Product> {
        return _products.value
            .filter { it.discount != null && it.discount!! > 15 }
            .sortedByDescending { it.discount }
            .take(6)
    }
}

// Atualize a classe Product para adicionar novos campos
