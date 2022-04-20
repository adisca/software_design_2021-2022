$(document).ready(function() {
    // Must go through login at least once
    if (Cookies.get("adminName") === undefined || Cookies.get("adminId") === undefined)
        window.location = "/foodpanda/login.html";

    $("#adminName").text("Hi there, admin " + Cookies.get("adminName"));
    $("#adminId").text("With id " + Cookies.get("adminId"));
    $("#btnBack").on("click", function() {
        window.location = "/foodpanda/login.html";
    });

    let restId = -1;
    let restName = "";
    let menuId = -1;
    let menuName = "";

    function validateString(str) {
        if (str !== "")
            return true;
        return false;
    }

    function validateFloat(flt) {
        if (flt !== "" && flt.replace(/^[0-9]+\.?[0-9]*/, "ok") === "ok")
            return true;
        return false;
    }

    $("#restName").on("click", function() {
        if (restId !== -1) {
            Cookies.set("restaurantName", restName);
            Cookies.set("restaurantId", restId);
            window.location = "/foodpanda/restaurant/restaurantOrders.html";
        }
        else
            console.log("You shouldn't be able to do this");
    });

    $("#addBtnRest").on("click", function() {
        if (validateString($("#restaurantName").val())) {
            var body = JSON.stringify({
                name: $("#restaurantName").val(),
                location: $("#restaurantLocation").val(),
                zones: $("#restaurantZones").val()
            });
            console.log(body);
            $.ajax({
                type: "POST",
                url: "http://localhost:8080/foodpanda/admin/" + Cookies.get("adminId"),
                data: body,
                dataType: 'json',
                contentType: "application/json; charset=utf-8",
                success: function(data) {
                    console.log(data);
                    getRestaurants();
                }
            });
        }
        else {
            console.log("Validation failed");
        }

    });

    $("#addBtnMenu").on("click", function() {
        if (validateString($("#menu").val())) {
            var body = JSON.stringify({ name: $("#menu").val() });
            console.log(body);
            $.ajax({
                type: "POST",
                url: "http://localhost:8080/foodpanda/restaurant/" + restId,
                data: body,
                dataType: 'json',
                contentType: "application/json; charset=utf-8",
                success: function(data) {
                    console.log(data);
                    getMenus();
                }
            });
        }
        else {
            console.log("Validation failed");
        }
    });

    $("#addBtnFood").on("click", function() {
        if (validateString($("#foodInpName").val()) && validateFloat($("#foodInpPrice").val())) {
            var body = JSON.stringify({
                name: $("#foodInpName").val(),
                description: $("#foodInpDesc").val(),
                price: $("#foodInpPrice").val()
            });
            console.log(body);
            $.ajax({
                type: "POST",
                url: "http://localhost:8080/foodpanda/category/" + menuId,
                data: body,
                dataType: 'json',
                contentType: "application/json; charset=utf-8",
                success: function(data) {
                    console.log(data);
                    getFoods();
                }
            });
        }
        else {
            console.log("Validation failed");
        }
    });

    function setupRestLabels() {
        $(".restLabel").on("click", function(e) {
            let restId1 = $(e.currentTarget).children()[0].textContent;
            restId = parseInt(restId1.substring(restId1.search(/[0-9]+/)));
            restName = $(e.currentTarget).children()[1].textContent.replace("Name: ", "");

            console.log(restId + " with name " + restName);
            $("#restName").text(restName);
            $("#menuVeil").show();
            $("#foodVeil").hide();
            menuId = -1;
            menuName = "";
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

    function getRestaurants() {
        $.get("http://localhost:8080/foodpanda/admin/" + Cookies.get("adminId"), function(data) {
            console.log(data);
            $("#restaurantList").empty();
            for (d in data) {
                $("#restaurantList").append("<div class='restLabel'><div>ID: "+ data[d].id +
                                            "</div><div>Name: " + data[d].name +
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
        })
    }

    getRestaurants();
});