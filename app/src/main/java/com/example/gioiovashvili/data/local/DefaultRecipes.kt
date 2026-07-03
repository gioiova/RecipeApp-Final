package com.example.gioiovashvili.data.local

import com.example.gioiovashvili.domain.model.Recipe

object DefaultRecipes {
    val recipes: List<Recipe> = listOf(
        Recipe(
            id = "seed-margherita-pizza",
            title = "Classic Margherita Pizza",
            description = "A timeless Italian pizza with fresh mozzarella, basil, and rich tomato sauce on a crispy crust.",
            imageUrl = "https://images.unsplash.com/photo-1574071318508-1cdbab80d002?w=800",
            totalTimeMinutes = 45,
            difficulty = "Hard",
            rating = 4.8,
            ingredients = listOf(
                "Pizza dough",
                "Tomato sauce",
                "Fresh mozzarella",
                "Fresh basil leaves",
                "Olive oil",
                "Salt"
            ),
            instructions = listOf(
                "Preheat the oven to 250°C (480°F).",
                "Roll out the pizza dough on a floured surface.",
                "Spread tomato sauce evenly, leaving a small border.",
                "Add mozzarella slices and a drizzle of olive oil.",
                "Bake for 12–15 minutes until the crust is golden.",
                "Top with fresh basil and serve hot."
            )
        ),
        Recipe(
            id = "seed-garlic-pasta",
            title = "Creamy Garlic Pasta",
            description = "Silky fettuccine tossed in a rich, garlicky cream sauce — ready in under 30 minutes.",
            imageUrl = "https://images.unsplash.com/photo-1621996346565-e3dbc646d9a9?w=800",
            totalTimeMinutes = 25,
            difficulty = "Easy",
            rating = 4.6,
            ingredients = listOf(
                "Fettuccine pasta",
                "Heavy cream",
                "Garlic cloves",
                "Parmesan cheese",
                "Butter",
                "Salt and pepper"
            ),
            instructions = listOf(
                "Cook pasta in salted boiling water until al dente.",
                "Sauté minced garlic in butter until fragrant.",
                "Pour in cream and simmer for 3 minutes.",
                "Stir in grated parmesan until smooth.",
                "Toss pasta in the sauce and season to taste."
            )
        ),
        Recipe(
            id = "seed-chicken-caesar",
            title = "Grilled Chicken Caesar Salad",
            description = "Crisp romaine, juicy grilled chicken, parmesan shavings, and creamy Caesar dressing.",
            imageUrl = "https://images.unsplash.com/photo-1546793665-c74683f339c1?w=800",
            totalTimeMinutes = 30,
            difficulty = "Easy",
            rating = 4.5,
            ingredients = listOf(
                "Chicken breast",
                "Romaine lettuce",
                "Caesar dressing",
                "Parmesan cheese",
                "Croutons",
                "Lemon juice"
            ),
            instructions = listOf(
                "Season and grill chicken until fully cooked, then slice.",
                "Chop romaine lettuce and place in a large bowl.",
                "Add dressing and toss until coated.",
                "Top with chicken, croutons, and parmesan.",
                "Finish with a squeeze of lemon."
            )
        ),
        Recipe(
            id = "seed-chocolate-cookies",
            title = "Chocolate Chip Cookies",
            description = "Soft, chewy cookies loaded with melty chocolate chips — perfect for any occasion.",
            imageUrl = "https://images.unsplash.com/photo-1499636136210-6f4ee915583e?w=800",
            totalTimeMinutes = 35,
            difficulty = "Easy",
            rating = 4.9,
            ingredients = listOf(
                "All-purpose flour",
                "Butter",
                "Brown sugar",
                "Eggs",
                "Vanilla extract",
                "Chocolate chips"
            ),
            instructions = listOf(
                "Cream butter and sugar until light and fluffy.",
                "Beat in eggs and vanilla extract.",
                "Fold in flour and chocolate chips.",
                "Scoop dough onto a baking tray.",
                "Bake at 180°C (350°F) for 10–12 minutes.",
                "Cool on a rack before serving."
            )
        ),
        Recipe(
            id = "seed-veggie-stir-fry",
            title = "Rainbow Vegetable Stir Fry",
            description = "A colorful, healthy stir fry with crisp vegetables and a savory soy-ginger sauce.",
            imageUrl = "https://images.unsplash.com/photo-1512058564366-18510be2db19?w=800",
            totalTimeMinutes = 20,
            difficulty = "Easy",
            rating = 4.4,
            ingredients = listOf(
                "Bell peppers",
                "Broccoli florets",
                "Carrots",
                "Snow peas",
                "Soy sauce",
                "Fresh ginger",
                "Sesame oil"
            ),
            instructions = listOf(
                "Heat sesame oil in a wok over high heat.",
                "Add ginger and stir for 30 seconds.",
                "Toss in harder vegetables first, then softer ones.",
                "Pour in soy sauce and stir-fry for 3–4 minutes.",
                "Serve immediately over rice or noodles."
            )
        ),
        Recipe(
            id = "seed-khinkali",
            title = "Traditional Georgian Khinkali",
            description = "Juicy, spiced meat dumplings from the mountains of Georgia. The secret is the savory broth inside!",
            imageUrl = "https://images.unsplash.com/photo-1687686515394-5f5b7d50b01b?q=80&w=1111&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            totalTimeMinutes = 90,
            difficulty = "Hard",
            rating = 5.0,
            ingredients = listOf(
                "All-purpose flour",
                "Water",
                "Ground beef and pork mix",
                "Onions",
                "Fresh cilantro",
                "Black pepper and salt",
                "Cumin"
            ),
            instructions = listOf(
                "Knead a firm dough with flour, water, and salt; let it rest.",
                "Mix meat with finely chopped onions, cilantro, and spices.",
                "Gradually add water to the meat mixture until it's very juicy.",
                "Roll out dough circles and place a spoonful of meat in the center.",
                "Pleat the dough edges tightly to create the signature 'knob'.",
                "Boil in a large pot of salted water for 10-12 minutes until they float upside down."
            )
        )
    )
}
