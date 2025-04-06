import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import "./Home.css";

function Home() {
  const navigate = useNavigate();
  const [darkMode, setDarkMode] = useState(false);

  return (
    <div className={`home-container ${darkMode ? "dark" : ""}`}>
      {/* Dark Mode Toggle */}
      <div className="toggle-container">
        <span className="dark-mode-text">
          {darkMode ? "Disable Dark Mode" : "Enable Dark Mode"}
        </span>
        <label className="toggle-switch">
          <input
            type="checkbox"
            checked={darkMode}
            onChange={() => setDarkMode(!darkMode)}
          />
          <span className="slider"></span>
        </label>
      </div>

      {/* Fixed Logo Path */}
      <img src="/images.png" alt="TetraCare Logo" className="logo" />

      <h1>
        Welcome to <span className="highlight">TetraCare+</span>
      </h1>
      <p>Your trusted healthcare companion.</p>

      <button className="sign-in-btn" onClick={() => navigate("/auth-choice")}>
        Click here to sign in or sign up
      </button>
    </div>
  );
}

export default Home;
