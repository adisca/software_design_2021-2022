$(document).ready(function() {
    // Must go through login at least once
    if (Cookies.get("name") === undefined || Cookies.get("id") === undefined || Cookies.get("qualification") === undefined)
        window.location = "/project/login.html";
    
    $("#btnBack").on("click", function() {
        window.location = "/project/user.html";
    });

    function getOfficialTimetable() {
        $.get("http://localhost:8080/project/user/" + Cookies.get("id") + "/official_timetable", function(data) {
            console.log(data);
            $("#timetableList").empty();
            for (d in data) {
                $("#timetableList").append("<div class='timetableLabel'><div>ID: " + data[d].id +
                                            "</div><div>IntervalStart: " + data[d].intervalStart +
                                            "</div><div>IntervalEnd: " + data[d].intervalEnd +
                                            "</div><div>ActivityID: " + data[d].activity.id +
                                            "</div><div>Activity: " + data[d].activity.name +
                                            "</div></div><br>");
            }
        });
    }

    getOfficialTimetable();
});