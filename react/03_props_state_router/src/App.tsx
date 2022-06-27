import React from 'react';
import './App.css';
import Menu from './components/Menu';
import Telo from './components/Telo';
import Noga from './components/Noga';
import About from './components/About';
import More from './components/More';

import { BrowserRouter as Router, Switch, Route, Redirect } from 'react-router-dom';

interface Element {
  id: number;
  zakljuceno: boolean;
  vsebina: string;
}

function App() {

  const [elementi, setElementi] = React.useState<Element[]>([]);
  elementi.push({ id: 0, zakljuceno: false, vsebina: 'Pomij posodo' });
  elementi.push({ id: 1, zakljuceno: true, vsebina: 'Posesaj stanovanje' });
  elementi.push({ id: 2, zakljuceno: false, vsebina: 'Pripravi kosilo' });

  return (

    <Router>
      <div className="App">
        <Menu naslov="Seznam opravil"></Menu>
        <Switch>
          <Route path="/about">
            <About />
          </Route>
          <Route path="/o-nas">
            <Redirect to="/about" />
          </Route>
          <Route exact path="/">
            <Telo elementi={elementi} />
          </Route>
          <Route path="/more/:id">
            <More elementi={elementi} />
          </Route>
          <Route path="/404">
            <h2>404 - Not found</h2>
            <div>Oops, page doesn't exist!</div>
          </Route>
          <Route path="*">
            <Redirect to="/404" />
          </Route>
        </Switch>
        <Noga avtor="Janez Novak" />
      </div>
    </Router>

  );
}

export default App;
