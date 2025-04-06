import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { auth } from "./firebaseconfig";
import { signOut, onAuthStateChanged } from "firebase/auth";
import "./Dashboard.css";

function Dashboard() {
  const [user, setUser] = useState(null);
  const [darkMode, setDarkMode] = useState(false);
  const navigate = useNavigate();

  // 👇 Check auth and redirect
  useEffect(() => {
    const unsubscribe = onAuthStateChanged(auth, (currentUser) => {
      if (currentUser) {
        setUser(currentUser);
      } else {
        navigate("/signin");
      }
    });
    return () => unsubscribe();
  }, [navigate]);

  // 👇 Function to redirect to Spring Boot index.html
  const goToMainPage = () => {
    window.location.href = "http://localhost:8080/";
  };

  const handleLogout = async () => {
    await signOut(auth);
    navigate("/signin");
  };

  return (
    <div className={`dashboard-container ${darkMode ? "dark" : ""}`}>
      <div className="dark-mode-toggle">
        <label className="switch">
          <input
            type="checkbox"
            checked={darkMode}
            onChange={() => setDarkMode(!darkMode)}
          />
          <span className="slider"></span>
        </label>
      </div>

      <h2>Welcome to TetraCare+</h2>
      {user ? (
        <p className="user-email">Logged in as: {user.email}</p>
      ) : (
        <p className="loading">Loading user...</p>
      )}

      {/* 👉 Add Button to Go to Main Page */}
      <button onClick={goToMainPage} className="logout-btn" style={{ backgroundColor: "#007bff", marginTop: "20px" }}>
        Go to Hospital Main Page
      </button>

      <button onClick={handleLogout} className="logout-btn">
        Logout
      </button>
    </div>
  );
}

export default Dashboard;
