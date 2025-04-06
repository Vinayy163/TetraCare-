import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Home from "./Home";
import AuthChoice from "./AuthChoice";
import Signup from "./SignUp.js";
import Signin from "./pages/SignIn"; // Change `SignIn` to `Signin` (if the file is named like that)
import Dashboard from './Dashboard';




function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/auth-choice" element={<AuthChoice />} />
        <Route path="/signup" element={<Signup />} />
        <Route path="/signin" element={<Signin />} />
        <Route path="/dashboard" element={<Dashboard />} />
      </Routes>
    </Router>
  );
}

export default App;
