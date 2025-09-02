# Posts Viewer App

An Android app built with **Kotlin**, **Jetpack Compose**, **Hilt**, **Retrofit**, and **Room** to display posts from a public API. This project demonstrates **MVVM architecture**, domain-driven design, offline caching, and modern UI/UX.

---

## Features

- Fetch posts from [JSONPlaceholder API](https://jsonplaceholder.typicode.com/posts)
- Display posts in a clean, scrollable list
- Tap on a post to view details
- Pull-to-refresh posts
- Search posts by title
- Offline caching with Room
- Loading and error handling
- Modern Compose UI with Material Design 3
- Hilt for dependency injection

---

## Screenshots

*(Replace the placeholders with your screenshots)*

![Post List](screenshots/post_list.png)
![Post Detail](screenshots/post_detail.png)

---

## Architecture

- **MVVM** with **ViewModel + StateFlow**
- **Domain Layer**: `Post` model and `PostsRepository` interface
- **Data Layer**: Room entities, Retrofit DTOs, Repository implementation
- **Presentation Layer**: Composable screens observing StateFlow

---

## Libraries Used

- Kotlin + Coroutines
- Jetpack Compose (Material 3)
- Navigation Compose
- Hilt (DI)
- Retrofit + Gson converter
- OkHttp Logging Interceptor
- Room + KTX
- Accompanist SwipeRefresh

---

## How to Run

1. Clone the repository
```bash
git clone https://github.com/zfml/PostsViewerApp.git
```
2. Open the project in **Android Studio**
3. Sync Gradle to download dependencies
4. Run the app on an emulator or device

---

## Folder Structure

```
com.example.postsviewer
├── data
│ ├── local (Room entities and DAOs)
│ ├── remote (Retrofit API)
│ ├── Mappers.kt
│ └── PostsRepositoryImpl.kt
├── domain
│ ├── Post.kt
│ └── PostsRepository.kt
├── ui
│ ├── PostListViewModel.kt
│ ├── PostDetailViewModel.kt
│ └── screens (Composable screens)
├── di (Hilt modules)
├── MainActivity.kt
└── PostsViewerApp.kt
```

---

## Contributing

Contributions are welcome! Please fork the repository, make your changes, and open a pull request.

---

## License

MIT License
