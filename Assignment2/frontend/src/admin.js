
export default function Login() {
    const [restaurant, setRestaurant] = useState("");
  
    function validateForm() {
      return restaurant.length > 0;
    }
  
    async function handleSubmit(event) {
        event.preventDefault();
  
        console.log("Add restaurant " + restaurant);
        let response = await fetch("http://localhost:8080/foodpanda/restaurant/add",
            {
                method: "POST",
                headers: {
                'Accept': 'application/json, text/plain',
                'Content-Type': 'application/json;charset=UTF-8'
                },
                body:JSON.stringify({ name: restaurant }),
            }
        );
        let responseJson = await response.json()
        console.log(responseJson.status);
    }
  
    return (
      <div className="Login">
        <Form onSubmit={handleSubmit}>
          <Form.Group size="lg" controlId="restaurant">
            <Form.Label>Restaurant</Form.Label>
            <Form.Control
              autoFocus
              type="restaurant"
              value={restaurant}
              onChange={(e) => setRestaurant(e.target.value)}
            />
          </Form.Group>
          <Button block size="lg" type="submit" name="add" disabled={!validateForm()}>
          Add restaurant
          </Button>
        </Form>
      </div>
    );
  }