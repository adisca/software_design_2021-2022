import React, { useState } from "react";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";

export default class AdminPage extends React.Component {
  restaurant = "";

  constructor(props) {
    super(props);
    this.state = {
      loading: true
    }
  }

  async getRestaurants() {
    let response = await fetch("http://localhost:8080/foodpanda/restaurant",
            {
                method: "GET",
                headers: {
                'Accept': 'application/json, text/plain',
                'Content-Type': 'application/json;charset=UTF-8'
                }
            }
    );
    let responseJson = await response.json()
    console.log(responseJson);
    let divs = [];
    for(var x in responseJson) {
      divs.push(<div key={responseJson[x].id}>{responseJson[x].id} {responseJson[x].name} </div>);
    }
    this.setState({ loading: false });
    // return (divs);
  }

  validateForm() {
    return this.restaurant.length > 0;
  }

  async handleSubmit(event) {
    event.preventDefault();

    console.log("Adding...");
    let response = await fetch("http://localhost:8080/foodpanda/restaurant",
        {
            method: "POST",
            headers: {
            'Accept': 'application/json, text/plain',
            'Content-Type': 'application/json;charset=UTF-8'
            },
            body:JSON.stringify({ name: this.restaurant }),
        }
    );
    let responseJson = await response.json()
    console.log(responseJson.status);
  }

  render() {
  return(
    <div>
      <Form onSubmit={this.handleSubmit}>
                <Form.Group size="lg" controlId="restaurant">
              <Form.Label>Restaurant</Form.Label>
              <Form.Control
                autoFocus
                type="restaurant"
                value={this.restaurant}
                onChange={(e) => this.restaurant = e.target.value}
              />
            </Form.Group>
            <Button block size="lg" type="submit" name="addRest" disabled={!this.validateForm()}>
              Add
            </Button>
      </Form>
      <Button block size="lg" name="showRest" onClick={this.getRestaurants}>
        Show
      </Button>
      <div>
        {this.state.loading ? <div> loading... </div> : <div> LOADED </div>}
      </div>
  </div>
    );
  }

}