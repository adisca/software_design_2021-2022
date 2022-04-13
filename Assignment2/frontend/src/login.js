import React, { useState } from "react";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";


export default function Login() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  function validateForm() {
    return username.length > 0 && password.length > 0;
  }

  async function handleSubmit(event) {
    event.preventDefault();
    console.log(username + " " + password);

    if (event.nativeEvent.submitter.name === "signupClient") {
        console.log("Sign up Cli");
        let response = await fetch("http://localhost:8080/foodpanda/client/signup",
            {
                method: "POST",
                headers: {
                'Accept': 'application/json, text/plain',
                'Content-Type': 'application/json;charset=UTF-8'
                },
                body:JSON.stringify({ credential: username, password: password }),
            }
        );
        let responseJson = await response.json()
        console.log(responseJson.status);
    }
    else if(event.nativeEvent.submitter.name === "loginClient") {
        console.log("Log in Cli");
        let response = await fetch("http://localhost:8080/foodpanda/client/login",
            {
                method: "POST",
                headers: {
                'Accept': 'application/json, text/plain',
                'Content-Type': 'application/json;charset=UTF-8'
                },
                body:JSON.stringify({ credential: username, password: password }),
            }
        );
        let responseJson = await response.json()
        console.log(responseJson.status);
    }
    else if(event.nativeEvent.submitter.name === "signupAdmin") {
      console.log("Sign up Adm");
      let response = await fetch("http://localhost:8080/foodpanda/admin/signup",
          {
              method: "POST",
              headers: {
              'Accept': 'application/json, text/plain',
              'Content-Type': 'application/json;charset=UTF-8'
              },
              body:JSON.stringify({ credential: username, password: password }),
          }
      );
      let responseJson = await response.json()
      console.log(responseJson.status);
  }
  else if(event.nativeEvent.submitter.name === "loginAdmin") {
    console.log("Log in Adm");
    let response = await fetch("http://localhost:8080/foodpanda/admin/login",
        {
            method: "POST",
            headers: {
            'Accept': 'application/json, text/plain',
            'Content-Type': 'application/json;charset=UTF-8'
            },
            body:JSON.stringify({ credential: username, password: password }),
        }
    );
    let responseJson = await response.json()
    console.log(responseJson.status);
  }
}

  return (
    <div className="Login">
      <Form onSubmit={handleSubmit}>
        <Form.Group size="lg" controlId="username">
          <Form.Label>Username</Form.Label>
          <Form.Control
            autoFocus
            type="username"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
          />
        </Form.Group>
        <Form.Group size="lg" controlId="password">
          <Form.Label>Password</Form.Label>
          <Form.Control
            type="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />
        </Form.Group>
        <Button block size="lg" type="submit" name="loginClient" disabled={!validateForm()}>
        Log in *client*
        </Button>
        <Button block size="lg" type="submit" name="signupClient" disabled={!validateForm()}>
        Sign up *client*
        </Button>
        <Button block size="lg" type="submit" name="loginAdmin" disabled={!validateForm()}>
        Log in *admin*
        </Button>
        <Button block size="lg" type="submit" name="signupAdmin" disabled={!validateForm()}>
        Sign up *admin*
        </Button>
      </Form>
    </div>
  );
}