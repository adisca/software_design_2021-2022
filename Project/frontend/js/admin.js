$(document).ready(function() {

    $("#btnBack").on("click", function() {
        window.location = "/project/login.html";
    });

    function getQualifications() {
        $.get("http://localhost:8080/project/util/qualification", function(data) {
            console.log(data);
            for (d in data) {
                $("#qualificationSelect").append("<option value=" + data[d] + ">" + data[d] + "</option>");
            }
        });
    }

    function getDays() {
        $.get("http://localhost:8080/project/util/day", function(data) {
            console.log(data);
            for (d in data) {
                $("#daySelect").append("<option value=" + data[d] + ">" + data[d] + "</option>");
            }
        });
    }

    function prepareHourSelect(id) {
        for (var i = 0; i < 24; i++)
            $(id).append("<option value=" + String(i).padStart(2, '0') +">" + i + "</option>");
    }

    function prepareMinuteSelect(id) {
        for (var i = 0; i < 60; i++)
            $(id).append("<option value=" + String(i).padStart(2, '0') + ">" + i + "</option>");
    }

    function getActivities() {
        $.get("http://localhost:8080/project/admin/activity", function(data) {
            console.log(data);
            $("#activityList").empty();
            for (d in data) {
                $("#activityList").append("<div class='activityLabel'><div>ID: "+ data[d].id +
                                            "</div><div>Name: " + data[d].name +
                                            "</div><div>NbPeople: " + data[d].nbPeople +
                                            "</div><div>MinTime: " + data[d].minTime +
                                            "</div><div>IntervalStart: " + data[d].intervalStart +
                                            "</div><div>IntervalEnd: " + data[d].intervalEnd +
                                            "</div><div>Day: " + data[d].day +
                                            "</div><div>Qualification: " + data[d].qualification +
                                            "</div></div><br>");
            }
        });
    }

    $("#addBtnActivity").on("click", function() {
        var body = JSON.stringify({
            name: $("#activityName").val(),
            nbPeople: parseInt($("#activityNbPeople").val()),
            minTime: $("#activityMinTimeHour").val() + ":" + $("#activityMinTimeMinute").val() + ":00",
            intervalStart: $("#activityIntervalStartHour").val() + ":" + $("#activityIntervalStartMinute").val() + ":00",
            intervalEnd: $("#activityIntervalEndHour").val() + ":" + $("#activityIntervalEndMinute").val() + ":00",
            qualification: ($("#qualificationSelect").val() === "" ? null : $("#qualificationSelect").val()),
            day: ($("#daySelect").val() === "" ? null : $("#daySelect").val())
        });
        console.log(body);
        $.ajax({
            type: "POST",
            url: "http://localhost:8080/project/admin/activity",
            data: body,
            dataType: 'json',
            contentType: "application/json; charset=utf-8",
            success: function(data) {
                console.log(data);
            }
        });
    });

    prepareHourSelect("#activityMinTimeHour");
    prepareMinuteSelect("#activityMinTimeMinute");

    prepareHourSelect("#activityIntervalStartHour");
    prepareMinuteSelect("#activityIntervalStartMinute");

    prepareHourSelect("#activityIntervalEndHour");
    prepareMinuteSelect("#activityIntervalEndMinute");

    getQualifications();
    getDays();

    getActivities();
});