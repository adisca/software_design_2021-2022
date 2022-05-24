$(document).ready(function() {

    function getQualifications() {
        $.get("http://localhost:8080/project/util/qualification", function(data) {
            console.log(data);
            for (d in data) {
                $("#qualificationSelect").append("<option value=" + data[d] + ">" + data[d] + "</option>");
            }
        });
    }

    $("#loginCli").on("click", function() {
        var body = JSON.stringify({ username: $("#username").val(), password: $("#password").val() });
        console.log(body);
        $.ajax({
            type: "POST",
            url: "http://localhost:8080/project/login/user",
            data: body,
            dataType: 'json',
            contentType: "application/json; charset=utf-8",
            success: function(data) {
                console.log(data);
                window.location = "/project/user.html";
            }
        });
    });

    $("#signupCli").on("click", function() {
        var qualification = $("#qualificationSelect").val();
        if (qualification === "")
            qualification = null;
        var body = JSON.stringify({
            username: $("#username").val(),
            password: $("#password").val(),
            firstName: $("#firstname").val(),
            lastName: $("#lastname").val(),
            qualification: qualification
        });
        console.log(body);
        $.ajax({
            type: "POST",
            url: "http://localhost:8080/project/signup/user",
            data: body,
            dataType: 'json',
            contentType: "application/json; charset=utf-8",
            success: function(data) {
                console.log(data);
            }
        });
    });

    $("#loginAdm").on("click", function() {
        var body = JSON.stringify({ username: $("#username").val(), password: $("#password").val() });
        console.log(body);
        $.ajax({
            type: "POST",
            url: "http://localhost:8080/project/login/admin",
            data: body,
            dataType: 'json',
            contentType: "application/json; charset=utf-8",
            success: function(data) {
                    console.log(data);
                    window.location = "/project/admin.html";
            }
        });
    });

    $("#signupAdm").on("click", function() {
        var body = JSON.stringify({ username: $("#username").val(), password: $("#password").val() });
        console.log(body);
        $.ajax({
            type: "POST",
            url: "http://localhost:8080/project/signup/admin",
            data: body,
            dataType: 'json',
            contentType: "application/json; charset=utf-8",
            success: function(data) {
                console.log(data);
            }
        });
    });

    getQualifications();

});