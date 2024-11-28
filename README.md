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
## ⚙️ Setup Instructions

1## ⚙️ Setup Instructions

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
![auth1](https://github.com/user-attachments/assets/85b0679d-dd46-4333-b276-a77b0a1102e0)
<br>
![auth2](https://github.com/user-attachments/assets/8ff3d7af-3ce4-4a64-a7b7-a00454d630e3)
<br>
Home: 
![foto1](https://github.com/user-attachments/assets/9c8c291f-f67e-448c-98b9-1491e91f90d5)
<br>
![foto2](https://github.com/user-attachments/assets/4d1c8e12-eb82-4879-8c67-268e0aa9bc53)
<br>

Product Details:
![foto3](https://github.com/user-attachments/assets/18aedd35-0be3-4c46-8aa1-2ac3e55333a2)
<br>
![foto4](https://github.com/user-attachments/assets/0070944d-93e3-49bc-85e7-ad4badacddf7)
<br>
![foto5](https://github.com/user-attachments/assets/dd36326e-38b9-427d-8214-a39bff874dcb)
<br>

Cart:
![foto6](https://github.com/user-attachments/assets/5c2365d2-2f73-45e5-b209-1d9066444496)
<br>
Profile:
![profile1](https://github.com/user-attachments/assets/c0b8cda8-cbea-4e0f-98c5-5dc98b610a5a)
---
🚀 Features in Development
Push Notifications: Stay updated with the latest deals and offers.
<br>
---
<br>
👨‍💻 Contributions
Contributions are welcome! 
<br>
Feel free to submit a pull request or open an issue for improvements or feature requests.
---
🙌 Acknowledgments
Special thanks to Teknasyon for mentorship and guidance during development.
