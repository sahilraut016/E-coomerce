import React, { useEffect, useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

function Home() {
  const [products, setProducts] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    loadProducts();
  }, []);

  const loadProducts = async () => {
    try {
      const res = await axios.get("http://localhost:9090/api/products/all");
      setProducts(res.data);
    } catch (error) {
      console.log(error);
      alert("Backend not responding!");
    }
  };

  return (
    <div>
      <h2>Welcome {localStorage.getItem("userName")}</h2>

      <button onClick={() => navigate("/cart")}>Go To Cart</button>
      <button onClick={() => navigate("/orders")}>My Orders</button>

      <h3>Products List</h3>

      {products.length === 0 ? (
        <p>No products available</p>
      ) : (
        products.map((p) => (
          <div
            key={p.id}
            style={{
              border: "1px solid black",
              padding: "10px",
              margin: "10px",
              width: "300px",
            }}
          >
            <h4>{p.name}</h4>
            <p>₹{p.price}</p>
            <p>{p.sale ? "On Sale 🔥" : "Normal Product"}</p>

            <button onClick={() => navigate(`/product/${p.id}`)}>
              View Details
            </button>
          </div>
        ))
      )}
    </div>
  );
}

export default Home;