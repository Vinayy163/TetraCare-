import React from "react";
import { useNavigate } from "react-router-dom";
import "./AuthChoice.css";

function AuthChoice() {
  const navigate = useNavigate();

  return (
    <div className="auth-container">
      <h1 className="auth-heading">Welcome to TetraCare+</h1>
      <p className="auth-text">Are you a new user or an existing user?</p>

      <div className="auth-buttons">
        <button className="signup-btn" onClick={() => navigate("/signup")}>
          New User? Sign Up
        </button>
        <button className="signin-btn" onClick={() => navigate("/signin")}>
          Already have an account? Sign In
        </button>
      </div>
    </div>
  );
}

export default AuthChoice;
