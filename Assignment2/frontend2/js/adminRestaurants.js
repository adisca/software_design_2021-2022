$(document).ready(function() {
    $("#addBtn").on("click", function() {
        var body = JSON.stringify({ name: $("#restaurant").val() });
        console.log(body);
        $.ajax({
            type: "POST",
            url: "http://localhost:8080/foodpanda/restaurant",
            data: body,
            dataType: 'json',
            contentType: "application/json; charset=utf-8",
            success: function(data) {
                console.log(data);
                getRestaurants();
            }
        });

    });

    function getRestaurants() {
        $.get("http://localhost:8080/foodpanda/restaurant", function(data) {
            console.log(data);
            $("#restaurantList").empty();
            for (d in data) {
                $("#restaurantList").append("<div class='restLabel'>" + data[d].id + " " + data[d].name + "</div>");
            }
            $(".restLabel").on("click", function(e) {
                console.log(parseInt(e.currentTarget.textContent));
            });
        });
    }

    getRestaurants();
});