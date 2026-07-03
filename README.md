# 🍲 Recipe App


A modern, feature-rich Android application built with **Jetpack Compose** that allows users to explore, search, and manage recipes. This project demonstrates Clean Architecture, MVVM, and modern Android development practices.

---


## ✨ Features

- **🔍 Smart Search:** Quickly filter recipes by title or description in real-time.
- **📂 Recipe Categories:** Browse popular recipes with difficulty levels and ratings.
- **📝 Create Recipes:** Add your own recipes with custom ingredients and instructions.
- **🕒 Cooking Stats:** View preparation time and difficulty for every dish.
- **🖼️ Image Support:** Beautifully displayed recipe images using Coil.
- **💾 Local Persistence:** Recipes are stored locally using Room database.

## 🛠️ Tech Stack

- **UI:** [Jetpack Compose](https://developer.android.com/jetpack/compose) - Declarative UI framework.
- **Architecture:** Clean Architecture + MVVM (Model-View-ViewModel).
- **DI:** [Hilt](https://developer.android.com/training/dependency-injection/hilt-android) - Dependency injection for Android.
- **Database:** [Room](https://developer.android.com/training/data-storage/room) - SQLite abstraction layer.
- **Image Loading:** [Coil](https://coil-kt.github.io/coil/) - Kotlin-first image loading library.
- **Async:** [Kotlin Coroutines](https://kotlinlang.org/docs/coroutines-overview.html) & [Flow](https://kotlinlang.org/docs/flow.html).
- **Navigation:** [Jetpack Navigation Compose](https://developer.android.com/jetpack/compose/navigation).

## 🏗️ Project Structure

The project follows a modularized clean architecture approach:

```text
com.example.gioiovashvili
├── data           # Data Layer (Repositories, DB, Entities, DAOs)
├── domain         # Domain Layer (Models, Use Cases, Repository Interfaces)
└── presentation   # UI Layer (Screens, ViewModels, Components, Theme)
```
