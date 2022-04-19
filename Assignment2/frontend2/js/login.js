$(document).ready(function() {
    $("#login").on("click", function() {
        var body = JSON.stringify({ credential: $("#username").val(), password: $("#password").val() });
        console.log(body);
        $.ajax({
            type: "POST",
            url: "http://localhost:8080/foodpanda/client/login",
            data: body,
            dataType: 'json',
            contentType: "application/json; charset=utf-8",
            success: function(data) {
                console.log(data);
                if (data.status)
                    window.location = "/foodpanda/adminRestaurants.html";
            }
        });
    })
    $("#signup").on("click", function() {
        var body = JSON.stringify({ credential: $("#username").val(), password: $("#password").val() });
        console.log(body);
        $.ajax({
            type: "POST",
            url: "http://localhost:8080/foodpanda/client/signup",
            data: body,
            dataType: 'json',
            contentType: "application/json; charset=utf-8",
            success: function(data) {
                console.log(data);
            }
        });
    })
});