# Electronic Store App

Welcome to the **Electronic Store App** repository! This project is a modern, feature-based Android application for shopping electronic products. Built entirely with Jetpack Compose and following Clean Architecture principles, the app uses MVVM and MVI patterns for efficient state management and separation of concerns.

---

## 📱 Features

- **User Authentication**: Smooth and secure login and registration flows.
- **Product Catalog**: Browse and search a wide range of electronics.
- **Product Details**: View detailed product specifications and reviews.
- **Cart Management**: Add, remove, and manage items in the cart.
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
- **Jetpack Compose**: Jetpack Compose for Declarative and Stateful UI
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
## ⚙️ Setup Instructions

1. **Clone this repository**:
   ```bash
   git clone https://github.com/your-username/electronic-store-app.git
   cd electronic-store-app
2. Open the project in Android Studio.
3. Sync the Gradle files.
4. Run the app on an emulator or connected device.

---
🌟 Images
Here are the links to the feature screenshots:

Authentication: 
<br>
![auth1](https://github.com/user-attachments/assets/85b0679d-dd46-4333-b276-a77b0a1102e0)
![auth2](https://github.com/user-attachments/assets/8ff3d7af-3ce4-4a64-a7b7-a00454d630e3)
<br>
---
Home:
<br>
![home](https://github.com/user-attachments/assets/9ff97b50-aa98-4862-80ba-a5d47c72579d)
<br>
---
Product Details:
<br>
![product1](https://github.com/user-attachments/assets/a55030db-2a6c-4069-b8f5-2a03a7ff1256)
![product2](https://github.com/user-attachments/assets/10eae111-692d-4395-8cff-4a5839ece837)
<br>
---
Cart & Profile:
<br>
![cart](https://github.com/user-attachments/assets/f7d983b7-9f96-4c9f-8cff-3d928a3ed4ce)
![profile1](https://github.com/user-attachments/assets/c0b8cda8-cbea-4e0f-98c5-5dc98b610a5a)
---
🚀 Features in Development
Push Notifications: Stay updated with the latest deals and offers.
<br>
---
<br>
👨‍💻 Contributions
<br>
Contributions are welcome! 
<br>
Feel free to submit a pull request or open an issue for improvements or feature requests.
<br>
---
🙌 Acknowledgments
Special thanks to Teknasyon for mentorship and guidance during development.
