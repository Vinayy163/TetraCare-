import React, { useState } from "react";
import { logIn } from "./auth"; // Import your login function

const Login = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(false);

  const handleLogin = async (e) => {
    e.preventDefault();
    setError(null);
    setLoading(true);

    if (password.length < 6) {
      setError("Password must be at least 6 characters.");
      setLoading(false);
      return;
    }

    try {
      await logIn(email, password);
      alert("Login successful!");
      setEmail("");
      setPassword("");
    } catch (error) {
      setError(error.message);
    }

    setLoading(false);
  };

  return (
    <div style={styles.container}>
      <h2>Login</h2>
      <p>Enter your credentials to log in.</p>
      <form onSubmit={handleLogin} style={styles.form}>
        <input
          type="email"
          placeholder="Enter email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
          required
          style={styles.input}
        />
        <input
          type="password"
          placeholder="Enter password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          required
          style={styles.input}
        />
        {error && <p style={styles.error}>{error}</p>}
        <button type="submit" style={styles.button} disabled={loading}>
          {loading ? "Logging in..." : "Login"}
        </button>
      </form>
    </div>
  );
};

// Inline styles
const styles = {
  container: { textAlign: "center", maxWidth: "400px", margin: "auto", padding: "20px" },
  form: { display: "flex", flexDirection: "column", gap: "10px" },
  input: { padding: "10px", fontSize: "16px", borderRadius: "5px", border: "1px solid #ccc" },
  button: { padding: "10px", fontSize: "16px", cursor: "pointer", backgroundColor: "#007bff", color: "white", border: "none", borderRadius: "5px" },
  error: { color: "red", fontSize: "14px" },
};

export default Login;
