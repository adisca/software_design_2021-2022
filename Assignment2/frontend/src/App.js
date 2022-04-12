import './App.css';
import React from "react";
import Login from './login';

function App() {
  // let signUpHandler = e => {
  //   e.preventDefault();

  //   let username = "Adi";
  //   let pass = "1234";
  //   console.log(username + " " + pass);

  //   let response = fetch("http://localhost:8080/foodpanda/signup",
  //     {
  //       method: "POST",
  //       headers: {
  //         'Accept': 'application/json, text/plain',
  //         'Content-Type': 'application/json;charset=UTF-8'
  //       },
  //       body:JSON.stringify({ credential: username, password: pass }),
  //     }
  //   );
  //   console.log(response.body);
  // };

// let backup = <div className="form" onSubmit={signUpHandler}>
//   <form>
//     <div className="input-container">
//       <label>Username </label>
//       <input type="text" id="uname" required />
//     </div>
//     <div className="input-container">
//       <label>Password </label>
//       <input type="password" id="pass" required />
//     </div>
//     <div className="button-container">
//       <input type="submit" name='login' />
//       <input type="submit" name='sigup' />
//     </div>
//   </form>
// </div>;

  return (Login());
}

export default App;
