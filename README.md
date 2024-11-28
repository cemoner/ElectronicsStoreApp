# Electronic Store App

Welcome to the **Electronic Store App** repository! This project is a modern, feature-based Android application for shopping electronic products. Built entirely with Jetpack Compose and following Clean Architecture principles, the app uses MVVM and MVI patterns for efficient state management and separation of concerns.

---

## 📱 Features

- **User Authentication**: Smooth and secure login and registration flows.
- **Product Catalog**: Browse and search a wide range of electronics.
- **Product Details**: View detailed product specifications and reviews.
- **Cart Management**: Add, remove, and manage items in the cart.
- **Order Checkout**: Streamlined process for placing orders.
- **User Profile**: Manage account settings and view order history.
- **Dynamic Navigation**: Feature-aware navigation system.

---

## 🛠️ Tech Stack

### **Languages & Frameworks**
- **Kotlin**: Clean and concise Android development.
- **Jetpack Compose**: Declarative and fully XML-free UI development.

### **Architecture**
- **Feature-Based Clean Architecture**:  
  Each feature has its own `data`, `domain`, and `presentation` layers.
- **MVVM (Model-View-ViewModel)**: For reactive UI updates and modularized screens.
- **MVI (Model-View-Intent)**: Efficient state management and reduced callback functions.

### **Libraries & Tools**
- **Navigation**: Compose Navigation with custom utilities for modular navigation.
- **Dependency Injection**: Dagger Hilt for scalable dependency management.
- **Networking**: Retrofit for API interactions.
- **Database**: Room for local persistence.
- **Reactive Programming**: Flow and StateFlow for consistent state management.

---

## 📂 Project Structure

### Overall Structure

```plaintext
📂 app
 ┣ 📂 common             # Shared utilities (e.g., extensions, mappers)
 ┣ 📂 features           # Feature modules
 ┃ ┣ 📂 cart             # Cart feature (data, domain, presentation)
 ┃ ┣ 📂 home             # Home feature (data, domain, presentation)
 ┃ ┗ 📂 profile          # Profile-related features
 ┃   ┣ 📂 authentication # Authentication (login/register)
 ┃   ┗ 📂 profile        # User profile and settings
 ┣ 📂 main               # App-level classes (e.g., Application, entry point)
 ┣ 📂 mvi                # Base classes and utilities for MVI pattern
 ┗ 📂 navigation         # Navigation-related code (e.g., NavHost, destinations)
```
---
⚙️ Setup Instructions
Clone this repository:

bash
Copy code
git clone https://github.com/your-username/electronic-store-app.git
cd electronic-store-app
Open the project in Android Studio.

Sync the Gradle files.
Run the app on an emulator or connected device.
---
🌟 Images
Here are the links to the feature screenshots:

Authentication: Link
Home: Link
Product Details: Link
Cart: Link
Profile: Link
---
🚀 Features in Development
Push Notifications: Stay updated with the latest deals and offers.
---
👨‍💻 Contributions
Contributions are welcome! Feel free to submit a pull request or open an issue for improvements or feature requests.
---
🙌 Acknowledgments
Special thanks to Teknasyon for mentorship and guidance during development.
