import React, { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

function Login() {
  const [mail, setMail] = useState("");
  const [pass, setPass] = useState("");

  const navigate = useNavigate();

  const loginUser = async (e) => {
    e.preventDefault();

    try {
      const res = await axios.post("http://localhost:9091/user/login", {
        mail,
        pass,
      });

      if (res.data == null || res.data === "") {
        alert("Invalid Email or Password");
      } else {
        alert("Login Successful");

        localStorage.setItem("userId", res.data.id);
        localStorage.setItem("userName", res.data.name);
        localStorage.setItem("role", res.data.role);

        if (res.data.role === "ADMIN") {
          navigate("/admin");
        } else {
          navigate("/feedback");
        }
      }
    } catch (error) {
      alert("Server Error or Invalid Login");
      console.log(error);
    }
  };

  return (
    <div>
      <h2>Login Page</h2>

      <form onSubmit={loginUser}>
        <input
          type="email"
          placeholder="Enter Email"
          value={mail}
          onChange={(e) => setMail(e.target.value)}
        />
        <br />
        <br />

        <input
          type="password"
          placeholder="Enter Password"
          value={pass}
          onChange={(e) => setPass(e.target.value)}
        />
        <br />
        <br />

        <button type="submit">Login</button>
      </form>

      <p>
        New user? <a href="/register">Register</a>
      </p>
    </div>
  );
}

export default Login;