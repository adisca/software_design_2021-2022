import AdminPage from './admin';
import './App.css';
import Login from './login'
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom'

function App() {
  return (
    <Router>
      <div>
        <Switch>
          <Route exact path="/">
            <Login/>
          </Route>
          <Route path="/admin">
            <AdminPage/>
          </Route>
        </Switch>
      </div>
    </Router>
  );
}

export default App;
