import React, { useEffect, useState } from "react";
import axios from "axios";
import { useParams, useNavigate } from "react-router-dom";

function ProductDetails() {
  const { id } = useParams();
  const [product, setProduct] = useState({});
  const navigate = useNavigate();

  const loadProduct = async () => {
    const res = await axios.get(`http://localhost:9090/api/products/${id}`);
    setProduct(res.data);
  };

  useEffect(() => {
    loadProduct();
  }, [id]);

  const addToCart = async () => {
    const userId = localStorage.getItem("userId");

    await axios.post(
      `http://localhost:9090/api/cart/add?userId=${userId}&productId=${id}`
    );

    alert("Product Added to Cart!");
    navigate("/cart");
  };

  return (
    <div>
      <h2>Product Details</h2>

      <button onClick={() => navigate("/home")}>Home</button>
      <button onClick={() => navigate("/cart")}>Go To Cart</button>
      <button onClick={() => navigate("/orders")}>My Orders</button>

      <hr />

      <h3>{product.name}</h3>
      <p><b>Description:</b> {product.description}</p>
      <p><b>Price:</b> ₹{product.price}</p>

      <button onClick={addToCart}>Add To Cart</button>
    </div>
  );
}

export default ProductDetails;