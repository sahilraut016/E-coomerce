import React, { useEffect, useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

function Cart() {
  const [items, setItems] = useState([]);
  const [productsMap, setProductsMap] = useState({});
  const navigate = useNavigate();

  useEffect(() => {
    loadCart();
  }, []);

  const loadCart = async () => {
    const userId = localStorage.getItem("userId");

    const cartRes = await axios.get(
      `http://localhost:9090/api/cart/items/${userId}`
    );

    setItems(cartRes.data);

    // Load products for each cart item
    let map = {};
    for (let item of cartRes.data) {
      const productRes = await axios.get(
        `http://localhost:9090/api/products/${item.productId}`
      );
      map[item.productId] = productRes.data;
    }
    setProductsMap(map);
  };

  const removeItem = async (itemId) => {
    await axios.delete(`http://localhost:9090/api/cart/remove/${itemId}`);
    alert("Item Removed");
    loadCart();
  };

  const placeOrder = async () => {
    const userId = localStorage.getItem("userId");

    const res = await axios.post(
      `http://localhost:9090/api/orders/place/${userId}`
    );

    if (res.data == null || res.data === "") {
      alert("Cart is Empty!");
    } else {
      alert("Order Placed Successfully!");
      navigate("/orders");
    }
  };

  return (
    <div>
      <h2>My Cart</h2>

      <button onClick={() => navigate("/home")}>Back to Home</button>

      {items.length === 0 ? (
        <h3>No items in cart</h3>
      ) : (
        items.map((item) => (
          <div
            key={item.id}
            style={{
              border: "1px solid black",
              margin: "10px",
              padding: "10px",
            }}
          >
            <h4>{productsMap[item.productId]?.name}</h4>
            <p>Price: ₹{productsMap[item.productId]?.price}</p>
            <p>Quantity: {item.quantity}</p>

            <button onClick={() => removeItem(item.id)}>Remove</button>
          </div>
        ))
      )}

      <br />
      <button onClick={placeOrder}>Place Order</button>
    </div>
  );
}

export default Cart;