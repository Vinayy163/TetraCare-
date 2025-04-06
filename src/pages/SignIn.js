import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { auth } from "../firebaseconfig";
import { signInWithEmailAndPassword } from "firebase/auth";
import "./SignIn.css";
import { getIdToken } from "firebase/auth";

function SignIn() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");
  const navigate = useNavigate();

  

  const handleSignIn = async (e) => {
    e.preventDefault();
    try {
      const userCredential = await signInWithEmailAndPassword(auth, email, password);
      const user = userCredential.user;
  
      const token = await user.getIdToken();
      console.log("Token:", token);
      // Send token to backend
      const response = await fetch("http://localhost:8080/auth/verify", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${token}`,
        },
      });
      
  
      if (response.ok) {
        console.log("✅ Token verified by backend");
        // Redirect to Spring Boot main page
        window.location.href = "http://localhost:3000/dashboard";
      } else {
        console.error("❌ Invalid token or user not authenticated.");
      }
    } catch (err) {
      setError("Invalid email or password!");
      console.error(err);
    }
  };
  const goToMainPage = () => {
    window.location.href = "http://localhost:8080/";
  };
  

  

  return (
    <div className="signin-container">
      <h2>Sign In</h2>
      {error && <p className="error-message">{error}</p>}

      <input
        type="email"
        placeholder="Enter Email"
        value={email}
        onChange={(e) => setEmail(e.target.value)}
      />
      <input
        type="password"
        placeholder="Enter Password"
        value={password}
        onChange={(e) => setPassword(e.target.value)}
      />

      <button onClick={goToMainPage}>Sign In</button>
    </div>
  );
}

export default SignIn;
