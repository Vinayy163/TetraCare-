// Import the functions you need from the SDKs you need
import { initializeApp } from "firebase/app";
import { getAuth } from "firebase/auth"; // Import getAuth

// Your web app's Firebase configuration
const firebaseConfig = {
  apiKey: "AIzaSyDgq8pMwUXxeudE1lnpkr9wVxKio3Ei8oU",
  authDomain: "tetracarepluss.firebaseapp.com",
  projectId: "tetracarepluss",
  storageBucket: "tetracarepluss.appspot.com",
  messagingSenderId: "132866903979",
  appId: "1:132866903979:web:b01bb2d5385c1948658cee"
};

// Initialize Firebase
const app = initializeApp(firebaseConfig);

// Initialize Firebase Authentication
const auth = getAuth(app);

// Export app and auth if you need to use them in other modules.
export { app, auth };

