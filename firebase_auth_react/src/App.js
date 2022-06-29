import React, { useEffect, useState } from 'react';
import './App.css';
import { app } from './firebaseConfig.js';
import {
  getAuth,
  signInWithEmailAndPassword,
  signOut,
  onAuthStateChanged
} from 'firebase/auth';
import { getFirestore, collection, getDocs, query } from "firebase/firestore";

function App() {
  const db = getFirestore(app);
  const auth = getAuth();
  const [data, setData] = useState({
    email: '',
    password: '',
    firsName: '',
    lastName: '',
  })
  const handleInputs = (event) => {
    let inputs = { [event.target.name]: event.target.value }

    setData({ ...data, ...inputs })
  }

  const login = () => {
    signInWithEmailAndPassword(auth, data.email, data.password).then(result => {
      fetch(`http://localhost:8080/getData/${result.user.email}`, {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${result.user.accessToken}`,
        },
      }).then(response => {
        console.log("Podatki uporabnika:")
        console.log(response.json());
      });
    }
    ).catch(error => console.log(error));
  }

  const handlelogout = () => {
    signOut(auth);
  }

  const getDataFromReact = async () => {
    const q = query(collection(db, "users"));

    const querySnapshot = await getDocs(q);
    querySnapshot.forEach((doc) => {
      console.log(doc.id, " => ", doc.data());
    });
  }

  useEffect(() => {
    onAuthStateChanged(auth, (data) => {
      if (data) {
        console.log("Logged In");
      }
      else {
        console.log('Not Logged In')
      }
    })
  }, [])

  return (
    <div className="App">
      <header className="App-header">
        <input
          placeholder="Email"
          name="email"
          type="email"
          className="input-fields"
          onChange={event => handleInputs(event)}
        />
        <input
          placeholder="Password"
          name="password"
          type="password"
          className="input-fields"
          onChange={event => handleInputs(event)}
        />

        <button onClick={login}>Log In</button>
        <button onClick={handlelogout}>Log out</button>
        <button onClick={getDataFromReact}>Get Data From ReactApp</button>
      </header>
    </div>
  );
}

export default App;
