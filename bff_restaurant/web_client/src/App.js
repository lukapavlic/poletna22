import logo from './logo.svg';
import './App.css';
import { useEffect, useState } from 'react';

function App() {

  const [menus, setMenus] = useState([]);
  const [orders, setOrders] = useState([]);

  useEffect(() => {
    fetch(`http://localhost:9002/bff/menus`, {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
      },
    }).then(function (response) {
      return response.json();
    }).then(function (data) {
      setMenus(data);
    });
    fetch(`http://localhost:9002/bff/orders`, {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
      },
    }).then(function (response) {
      return response.json();
    }).then(function (data) {
      setOrders(data);
    });
  }, []);

  return (
    <div className="App">
      <table>
        <tr>
          <th>MENUS</th>
          <th>ORDERS</th>
        </tr>
        <tr>
          <td><pre>
            {JSON.stringify(menus, null, 4).replace(/["{[,\}\]]/g, "")}
          </pre></td>
          <td><pre>
            {JSON.stringify(orders, null, 4).replace(/["{[,\}\]]/g, "")}
          </pre></td>
        </tr>
      </table>
    </div>
  );
}

export default App;
