$(document).ready(function() {
    // Must go through login at least once
    if (Cookies.get("clientName") === undefined || Cookies.get("clientId") === undefined)
        window.location = "/foodpanda/login.html";

    $("#clientName").text("Hi there, client " + Cookies.get("clientName"));
    $("#clientId").text("With id " + Cookies.get("clientId"));
    $("#btnBack").on("click", function() {
        window.location = "/foodpanda/login.html";
    });

    let restId = -1;
    let restName = "";
    let menuId = -1;
    let menuName = "";
    let order = [];

    $("#btnSearch").on("click", function() {
        getRestaurants($("#searchName").val());
    });

    // Refresh
    $("#historyBtn").on("click", function() {
        getHistory();
    });

    // Place Order
    $("#orderBtn").on("click", function() {
        let foodDTOs = []
        if (order.length !== 0) {
            for (o in order) {
                while (order[o].count >= 1) {
                    foodDTOs.push({id: order[o].id, name: order[o].name, description: "", price: order[o].price});
                    order[o].count -= 1;
                }
            }
            order = [];
            getOrder();

            var body = JSON.stringify({
                    status: "PENDING",
                    belongsTo: {id: Cookies.get("clientId"), name: Cookies.get("clientName")},
                    foods: foodDTOs
                });
            console.log(body);
            $.ajax({
                type: "POST",
                url: "http://localhost:8080/foodpanda/client/" + Cookies.get("clientId"),
                data: body,
                dataType: 'json',
                contentType: "application/json; charset=utf-8",
                success: function(data) {
                    console.log(data);
                    getHistory();
                }
            });
        }
        else
            console.log("Empty order");
    });

    function setupRestLabels() {
        $(".restLabel").on("click", function(e) {
            let restId1 = $(e.currentTarget).children()[0].textContent;
            restId1 = parseInt(restId1.substring(restId1.search(/[0-9]+/)));
            if (restId !== restId1) {
                order = [];
                getOrder();
                menuId = -1;
                menuName = "";
                $("#foodVeil").hide();
            }
            restId = restId1;
            restName = $(e.currentTarget).children()[1].textContent.replace("Name: ", "");
            console.log(restId+ " with name " + restName);
            $("#restName").text(restName);
            $("#menuVeil").show();
            getMenus();
        });
    }

    function setupMenuLabels() {
        $(".menuLabel").on("click", function(e) {
            let menuId1 = $(e.currentTarget).children()[0].textContent;
            menuId = parseInt(menuId1.substring(menuId1.search(/[0-9]+/)));
            menuName = $(e.currentTarget).children()[1].textContent.replace("Category: ", "");

            console.log(menuId + " with name " + menuName);
            $("#menuName").text(menuName);
            $("#foodVeil").show();
            getFoods();
        });
    }

    function setupFoodLabels() {
        $(".foodLabel").on("click", function(e) {
            let foodId = $(e.currentTarget).children()[0].textContent;
            foodId = parseInt(foodId.substring(foodId.search(/[0-9]+/)));
            let foodName = $(e.currentTarget).children()[1].textContent.replace("Name: ", "");
            let foodPrice = $(e.currentTarget).children()[2].textContent;
            foodPrice = parseFloat(foodPrice.substring(foodPrice.search(/[0-9]+\.?[0-9]*/)).replace("$", ""));
            console.log(foodId + " " + foodName + " " + foodPrice);
            let unique = true;
            for (o in order) {
                if (order[o].id === foodId) {
                    order[o].count += 1;
                    unique = false;
                    break;
                }
            }
            if (unique === true)
                order.push({id: foodId,
                            name: foodName,
                            price: foodPrice,
                            count: 1
                        })
            getOrder();
        });
    }

    function setupOrderLabels() {
        $(".orderLabel").on("click", function(e) {
            let orderId = parseInt($(e.currentTarget).children()[0].textContent.replace("ID: ", ""));
            console.log(orderId);
            for (o in order) {
                if (order[o].id === orderId) {
                    if (order[o].count <= 1)
                        order.splice(o, 1);
                    else
                        order[o].count -= 1;
                    break;
                }
            }
            getOrder();
        });
    }

    function getOrder() {
        $("#orderList").empty();
        for (o in order) {
            $("#orderList").append("<div class='orderLabel'><div>ID: " + order[o].id +
                                    "</div><div>Name: " + order[o].name +
                                    "</div><div>Price: " + order[o].price +
                                    "$</div><div>Count: " + order[o].count +
                                    "</div></div><br>");
        }
        setupOrderLabels();
    }

    function getHistory() {
        $.get("http://localhost:8080/foodpanda/client/" + Cookies.get("clientId"), function(data) {
            console.log(data);
            $("#historyList").empty();
            for (d in data) {
                let total = 0.;
                let strHistoryList = "";
                strHistoryList = strHistoryList.concat("<div class='historyLabel'><div>ID:" + data[d].id +
                                        "</div><div>Status:" + data[d].status +
                                        "</div><div>Foods:</div><div class='slightly_right'>");
                for (f in data[d].foods) {
                    total += data[d].foods[f].price;
                    strHistoryList = strHistoryList.concat("<div><div>ID: " + data[d].foods[f].id +
                    "</div><div>Name: " + data[d].foods[f].name +
                    "</div><div>Price: " + data[d].foods[f].price +
                    "</div></div><br>");
                }
                strHistoryList = strHistoryList.concat("</div><div>Total: " + total + "$</div></div><br>");
                $("#historyList").append(strHistoryList);
            }
        });
    }

    function getRestaurants(filter="") {
        let myUrl = "";
        if (filter === "")
            myUrl = "http://localhost:8080/foodpanda/restaurant";
        else
            myUrl = "http://localhost:8080/foodpanda/restaurant/search/" + filter;
        $.get(myUrl, function(data) {
            console.log(data);
            $("#restaurantList").empty();
            for (d in data) {
                $("#restaurantList").append("<div class='restLabel'><div>ID: "+ data[d].id +
                                            "</div><div>Name: " + data[d].name +
                                            "</div><div>Owner: " + data[d].belongsTo.name +
                                            "</div><div>Location: " + data[d].location +
                                            "</div><div>Zones: " + data[d].zones +
                                            "</div></div><br>");
            }
            setupRestLabels();
        });
    }

    function getMenus() {
        $.get("http://localhost:8080/foodpanda/restaurant/" + restId, function(data) {
            console.log(data);
            $("#menuList").empty();
            for (d in data) {
                $("#menuList").append("<div class='menuLabel'><div>ID: " + data[d].id +
                                        "</div><div>Category: " + data[d].name + "</div></div><br>");
            }
            setupMenuLabels();
        })
    }

    function getFoods() {
        $.get("http://localhost:8080/foodpanda/category/" + menuId, function(data) {
            console.log(data);
            $("#foodList").empty();
            for (d in data) {
                $("#foodList").append("<div class='foodLabel'><div>ID: " + data[d].id +
                                        "</div><div>Name: " + data[d].name +
                                        "</div><div>Price: " + data[d].price +
                                        "$</div><div>Desc: " + data[d].description +
                                        "</div></div><br>");
            }
            setupFoodLabels();
        })
    }

    getRestaurants();
    getHistory();
});