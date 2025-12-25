Um aplicativo de e-commerce completo desenvolvido em Kotlin com Jetpack Compose, demonstrando as melhores práticas de desenvolvimento Android com arquitetura moderna e interface fluida.

✨ Funcionalidades
🏠 Tela Principal
Listagem de produtos em grid responsivo

Busca de produtos

Filtro por categorias

Indicador de itens no carrinho

Design responsivo e moderno

📱 Detalhes do Produto
Visualização completa do produto

Galeria de imagens

Avaliações e classificação

Informações de estoque

Preço com desconto destacado

🛒 Sistema de Carrinho
Adicionar/remover produtos

Ajustar quantidades

Cálculo automático do total

Indicador de quantidade no ícone do carrinho   


 Arquitetura
Model-View-ViewModel (MVVM)
text
📁 Data Layer
├── Product.kt (Data Class)
└── CartItem.kt (Data Class)

📁 ViewModel Layer
└── EcommerceViewModel.kt (Lógica de negócio)

📁 UI Layer
├── HomeScreen.kt
├── ProductDetailScreen.kt
├── CartScreen.kt
├── PaymentScreen.kt
└── OrderConfirmationScreen.kt


📁 Estrutura do Projeto
text
app/
├── src/main/java/com/sldagemapp/myapplication/
│   ├── MainActivity.kt
│   ├── EcommerceViewModel.kt
│   ├── Navigation.kt
│   ├── models/
│   │   ├── Product.kt
│   │   └── CartItem.kt
│   └── screens/
│       ├── HomeScreen.kt
│       ├── ProductCard.kt
│       ├── ProductDetailScreen.kt
│       ├── CartScreen.kt
│       ├── PaymentScreen.kt
│       └── OrderConfirmationScreen.kt
