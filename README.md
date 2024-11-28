Electronic Store App
Welcome to the Electronic Store App repository! This project is a modern, feature-based Android application for shopping electronic products. Built entirely with Jetpack Compose and following Clean Architecture principles, the app uses MVVM and MVI patterns for efficient state management and separation of concerns.

📱 Features
User Authentication: Smooth and secure login and registration flows.
Product Catalog: Browse and search a wide range of electronics.
Product Details: View detailed product specifications and reviews.
Cart Management: Add, remove, and manage items in the cart.
Order Checkout: Streamlined process for placing orders.
User Profile: Manage account settings and view order history.
Dynamic Navigation: Feature-aware navigation system.

🛠️ Tech Stack
Languages & Frameworks
Kotlin: Clean and concise Android development.
Jetpack Compose: Declarative and fully XML-free UI development.
Architecture

Feature-Based Clean Architecture:
Each feature has its own data, domain, and presentation layers.
MVVM (Model-View-ViewModel): For reactive UI updates and modularized screens.
MVI (Model-View-Intent): Efficient State Management and Reducing Callback Functions

Libraries & Tools
Navigation: Compose Navigation with custom utilities for modular navigation.
Dependency Injection: Dagger Hilt for scalable dependency management.
Networking: Retrofit for API interactions.
Database: Room for local persistence.
Reactive Programming: Flow and StateFlow for consistent state management.

📂 Project Structure
plaintext
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

📂 cart
 ┣ 📂 data              # Data sources, repositories, models
 ┣ 📂 domain            # Use cases, business logic
 ┗ 📂 presentation      # ViewModels and Composables

 
⚙️ Setup Instructions
Clone this repository:
bash
Copy code
git clone https://github.com/your-username/electronic-store-app.git
cd electronic-store-app
Open the project in Android Studio.

Sync the Gradle files.

Run the app on an emulator or connected device.

🌟 Images
Feature	Screenshot 
Authentication	
Home	![foto1](https://github.com/user-attachments/assets/318dbb17-666c-4a34-9642-bf16bcbedeba)
![foto2](https://github.com/user-attachments/assets/1b7521ea-7d63-4ab9-ab43-28d24b07822b)

Product Details	![foto3](https://github.com/user-attachments/assets/0090cfef-7f34-41c4-834c-1470133c4fb9)
![foto4](https://github.com/user-attachments/assets/e7ccce00-2faa-40cb-9d4d-c35b41a26631)

Cart	![foto6](https://github.com/user-attachments/assets/54c8a87c-bc4b-4a41-858d-546d0eb975e6)

Profile	![profile1](https://github.com/user-attachments/assets/cbc032fb-b4f8-4840-9f1f-0e878e1104c3)
![auth2](https://github.com/user-attachments/assets/ed2d6833-8840-4d2c-8781-a6f4421584e5)
![auth1](https://github.com/user-attachments/assets/fb2af3e3-87ac-4d16-82f0-78b110224e06)


🚀 Features in Development
Push notifications
👨‍💻 Contributions
Contributions are welcome! Feel free to submit a pull request or open an issue for improvements or feature requests.

📄 License
This project is licensed under the MIT License.

🙌 Acknowledgments
Special thanks to Teknasyon for mentorship and guidance during development.
