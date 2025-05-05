# 🎬 Movie App

MovieApp is an Android application built using the [The Movie Database (TMDb)](https://www.themoviedb.org/) API. It allows users to explore current and upcoming movies, view detailed information, and access cast details.

---

## 📸 Screenshots

![Image](https://github.com/user-attachments/assets/635122bc-2b0d-47b8-9ac2-c7e032f53ff5)
![Image](https://github.com/user-attachments/assets/f901c66a-2789-4cc6-bf60-49b705fb4933)
![Image](https://github.com/user-attachments/assets/5eb470f5-51ef-4660-8ee3-76853a942992)

---

## 📦 Project Structure

```plaintext
movieapp/
├── model/                  # Data models
├── network/                # Retrofit and API services
├── ui/
│   ├── home/               # Main screen (now playing and upcoming movies)
│   ├── detail/             # Movie detail screen and cast list
│   └── Cast/               # Cast detail screen
├── util/                   # Helper classes (Constants, Glide Extensions, etc.)
└── MainActivity.kt         # Main activity containing the navigation host
```

---

## 🚀 Features

- 🎞 Display now playing and upcoming movies
- 📋 Show movie details such as description, rating, runtime, genre, release date, and cast
- 🎭 Cast detail screen: name, character, and profile image
- 🔍 SearchView to filter movies by title
- 💡 MVVM (Model-View-ViewModel)architecture and use of ViewModel
- 🔗 Networking via Retrofit
- 🖼 Image loading and transformation with Glide
- 🔄 Asynchronous operations using Kotlin Coroutine

---

## 🧰 Technologies Used and Their Purpose

| Technology / Library        | Purpose / Usage |
|-----------------------------|------------------------|
| **Kotlin**                  | Primary programming language used for Android development. |
| **ViewModel**               | To store and manage UI-related data independent of lifecycle. |
| **LiveData**                | To observe and automatically update UI based on data changes. |
| **Navigation Component**    | Manages navigation between fragments safely and predictably. |
| **SafeArgs**                | Enables type-safe navigation and argument passing between fragments. |
| **Retrofit**                | For making HTTP requests to fetch data from the TMDb API. |
| **OkHttp & Logging Interceptor** | For logging HTTP request details and handling network calls. |
| **Glide**                   | To load, cache, and display images from the internet. |
| **Glide Transformations**   | Image loading and transformation with Glide. |
| **Coroutines**              | For performing asynchronous operations in a readable way. |
| **TMDb API**                | To retrieve information about movies, cast, and details from TMDb. |


- **Jetpack Components**
  - ViewModel
  - LiveData
  - Navigation Component + SafeArgs
- **Retrofit & OkHttp**
- **Glide & Glide Transformations**
- **Coroutines**
- **TMDb API**

---

## 📄 Navigation Flow

- **HomeFragment** → When a movie is selected → **DetailFragment**
- **DetailFragment** → When a cast member is selected → **CastFragment**

---

## 🤝 Teşekkürler

- [TMDb API](https://www.themoviedb.org/documentation/api)
- [Glide](https://github.com/bumptech/glide)
- [Retrofit](https://square.github.io/retrofit/)
