import React, { useEffect, useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

function Orders() {
  const [orders, setOrders] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    loadOrders();
  }, []);

  const loadOrders = async () => {
    const userId = localStorage.getItem("userId");

    const res = await axios.get(
      `http://localhost:9090/api/orders/user/${userId}`
    );

    setOrders(res.data);
  };

  return (
    <div>
      <h2>My Orders</h2>

      <button onClick={() => navigate("/home")}>Back to Home</button>

      {orders.length === 0 ? (
        <h3>No Orders Found</h3>
      ) : (
        orders.map((o) => (
          <div
            key={o.id}
            style={{
              border: "1px solid black",
              margin: "10px",
              padding: "10px",
            }}
          >
            <h4>Order ID: {o.id}</h4>
            <p>Total Amount: ₹{o.totalAmount}</p>
            <p>Order Date: {o.orderDate}</p>
          </div>
        ))
      )}
    </div>
  );
}

export default Orders;