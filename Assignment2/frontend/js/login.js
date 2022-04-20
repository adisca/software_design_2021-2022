$(document).ready(function() {
    $("#loginCli").on("click", function() {
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
                if (data.id !== -1) {
                    Cookies.set("clientName", $("#username").val());
                    Cookies.set("clientId", data.id);
                    window.location = "/foodpanda/clientRestaurants.html";
                }
            }
        });
    });

    $("#signupCli").on("click", function() {
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
    });

    $("#loginAdm").on("click", function() {
        var body = JSON.stringify({ credential: $("#username").val(), password: $("#password").val() });
        console.log(body);
        $.ajax({
            type: "POST",
            url: "http://localhost:8080/foodpanda/admin/login",
            data: body,
            dataType: 'json',
            contentType: "application/json; charset=utf-8",
            success: function(data) {
                console.log(data);
                if (data.id !== -1) {
                    Cookies.set("adminName", $("#username").val());
                    Cookies.set("adminId", data.id);
                    window.location = "/foodpanda/adminRestaurants.html";
                }
            }
        });
    });

    $("#signupAdm").on("click", function() {
        var body = JSON.stringify({ credential: $("#username").val(), password: $("#password").val() });
        console.log(body);
        $.ajax({
            type: "POST",
            url: "http://localhost:8080/foodpanda/admin/signup",
            data: body,
            dataType: 'json',
            contentType: "application/json; charset=utf-8",
            success: function(data) {
                console.log(data);
            }
        });
    });
});