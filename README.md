
# AI-Powered Checkout System

This repository contains the complete codebase for an AI-Powered Checkout System, designed to automate and streamline the retail checkout process. The system integrates various technologies, including a web application for user management and purchase history, Android applications for store management and spending tracking, a YOLOv11 model for object detection, and Arduino-based RFID readers for item identification.




## Features

The AI-Powered Checkout System is comprised of several interconnected components, each contributing to a seamless and efficient checkout experience:

### 1. Web Application (Flask)

- **User Authentication:** Secure login and signup functionalities with password reset options.
- **Admin Dashboard:** Allows administrators to manage inventory by updating item counts in real-time.
- **User Dashboard:** Provides users with an overview of their balance and access to their purchase history.
- **Purchase History:** Detailed records of all transactions, including item names, quantities, prices, dates, and categories.
- **Automated Categorization:** Items in purchase history are automatically categorized (e.g., Electronics, Food & Beverages, Health & Fitness, Clothing, Home & Kitchen).
- **Firebase Integration:** Utilizes Firebase Realtime Database for user data, purchase history, and inventory management.
- **Email Notifications:** Sends password reset links via email.

### 2. Android Applications

#### a. AutoBill (Store Management App)

- **User Management:** Functionality to view and manage user information.
- **Transaction History:** Access to a comprehensive list of all transactions.

#### b. SpendingManagerApp (User Spending Tracker)

- **User Authentication:** Integrates with Firebase Authentication for user sign-in.
- **Dashboard:** Displays user's spending overview.
- **Transaction Tracking:** Allows users to track their individual transactions.

### 3. YOLOv11 Object Detection

- **AI-Powered Product Recognition:** Employs a YOLOv11 model for accurate and real-time detection of products during the checkout process.
- **Automated Item Identification:** Reduces manual input and speeds up the checkout flow.

### 4. Arduino-based RFID System

- **RFID Readers:** Utilizes multiple RFID readers (e.g., RFID_1, RFID_2, RFID_3) for identifying items with RFID tags.
- **Seamless Integration:** Works in conjunction with the object detection system to ensure accurate item identification and prevent errors.




## Technologies Used

This project leverages a variety of modern technologies to deliver its functionalities:

- **Backend:** Python (Flask framework)
- **Database:** Firebase Realtime Database
- **Authentication:** Flask-Login, Firebase Authentication
- **Email Services:** Flask-Mail
- **Object Detection:** YOLOv11
- **Hardware Integration:** Arduino, RFID
- **Frontend (Web):** HTML, CSS, JavaScript, Jinja2 templates
- **Frontend (Android):** Java, Kotlin (for SpendingManagerApp)
- **Package Management (Python):** pip (with `requirements.txt`)
- **Security:** Werkzeug Security (for password hashing), bcrypt




## Installation

To set up and run the AI-Powered Checkout System, follow the instructions for each component:

### 1. Web Application

1.  **Clone the repository:**
    ```bash
    git clone https://github.com/Ducanhngo/AI-Powered-Checkout-System.git
    cd AI-Powered-Checkout-System/Final_version/Webapp
    ```
2.  **Set up Firebase:**
    -   Go to the Firebase Console and create a new project.
    -   Set up a Realtime Database.
    -   Generate a new private key for your service account (Project settings > Service accounts > Generate new private key).
    -   Rename the downloaded JSON file to `auto-checkout-b3ea1-firebase-adminsdk-fbsvc-5b3a708bd2.json` and place it in the `Final_version/Webapp` directory.
    -   Update the `databaseURL` in `app.py` to match your Firebase project's database URL.
3.  **Configure Flask-Mail:**
    -   In `app.py`, update the `MAIL_USERNAME` and `MAIL_PASSWORD` with your Gmail credentials. Ensure that "Less secure app access" is enabled for your Gmail account or use an App Password if 2-Factor Authentication is enabled.
4.  **Install Python dependencies:**
    ```bash
    pip install -r requirements.txt
    ```
5.  **Run the Flask application:**
    ```bash
    python app.py
    ```
    The web application will typically run on `http://127.0.0.1:5000/`.

### 2. Android Applications

#### a. AutoBill

1.  Open the `AI-Powered-Checkout-System/AutoBill` project in Android Studio.
2.  Build and run the application on an Android emulator or a physical device.

#### b. SpendingManagerApp

1.  Open the `AI-Powered-Checkout-System/SpendingManagerApp/home/ubuntu/SpendingManagerApp` project in Android Studio.
2.  Build and run the application on an Android emulator or a physical device.

### 3. YOLOv11 Object Detection

(Details for setting up and training the YOLOv11 model are not explicitly provided in the repository, but typically involve):

1.  **Install Ultralytics YOLO:**
    ```bash
    pip install ultralytics
    ```
2.  **Prepare your dataset:** Gather and annotate images of the products you want to detect.
3.  **Train the model:** Use the `ultralytics` library to train a YOLOv11 model on your custom dataset. The `my_model` directory suggests a pre-trained model might be included, but retraining or fine-tuning may be necessary for specific use cases.

### 4. Arduino-based RFID System

1.  **Hardware Setup:** Connect your RFID readers (e.g., RC522) to your Arduino board as per the circuit diagrams (not provided in the repo, but standard for RFID modules).
2.  **Upload Arduino sketches:**
    -   Open the `.ino` files located in `AI-Powered-Checkout-System/Final_version/Arduino/RFID_1`, `RFID_2/scanning`, and `RFID_3` in the Arduino IDE.
    -   Select the correct Arduino board and port.
    -   Upload the sketches to your Arduino boards.




## Usage

Once all components are set up and running:

-   **Web Application:** Access the web application through your browser to manage users, view purchase history, and for administrators, to manage inventory.
-   **Android Applications:**
    -   **AutoBill:** Use this app for store management tasks, including viewing user details and transaction logs.
    -   **SpendingManagerApp:** Users can track their personal spending and view their transaction history on their mobile devices.
-   **Checkout Process:** When a customer places items on the checkout counter:
    1.  The YOLOv11 model detects and identifies the products.
    2.  The RFID readers scan RFID-tagged items for additional verification and identification.
    3.  The system processes the identified items, calculates the total, and updates the inventory and user purchase history via the Firebase backend.

## Contributing

Contributions are welcome! If you have suggestions for improvements or new features, please feel free to:

1.  Fork the repository.
2.  Create a new branch (`git checkout -b feature/YourFeature`).
3.  Make your changes.
4.  Commit your changes (`git commit -m 'Add some feature'`).
5.  Push to the branch (`git push origin feature/YourFeature`).
6.  Open a Pull Request.



