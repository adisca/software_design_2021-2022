$(document).ready(function() {
    // Must go through login at least once
    if (Cookies.get("name") === undefined || Cookies.get("id") === undefined || Cookies.get("qualification") === undefined)
        window.location = "/project/login.html";

    var activityName = "";
    var activityId = -1;

    $("#userName").text("Hi there, user " + Cookies.get("name"));
    $("#userId").text("With id " + Cookies.get("id"));
    $("#userQualification").text("And qualification " + Cookies.get("qualification"));

    $("#btnBack").on("click", function() {
        window.location = "/project/login.html";
    });
    
    $("#btnViewOfficial").on("click", function() {
        window.location = "/project/userOfficialTimetable.html";
    });

    $("#btnSearch").on("click", function() {
        getValidActivities($("#searchName").val(), $("#searchDaySelect").val());
    });

    $("#btnChoose").on("click", function() {
        if (activityId !== -1) {
            let body = JSON.stringify(
                {
                    intervalStart: $("#activityIntervalStartHour").val() + ":" + $("#activityIntervalStartMinute").val() + ":00",
                    intervalEnd: $("#activityIntervalEndHour").val() + ":" + $("#activityIntervalEndMinute").val() + ":00",
                    user: { id: Cookies.get("id") },
                    activity: { id: activityId }
                });
            console.log(body);

            $.ajax({
                type: "POST",
                url: "http://localhost:8080/project/user/choose",
                data: body,
                dataType: 'json',
                contentType: "application/json; charset=utf-8",
                success: function(data) {
                    console.log(data);
                    getValidActivities();
                    getUserTimetables();
                }
            });
        }
    });

    function prepareHourSelect(id) {
        for (var i = 0; i < 24; i++)
            $(id).append("<option value=" + String(i).padStart(2, '0') +">" + i + "</option>");
    }

    function prepareMinuteSelect(id) {
        for (var i = 0; i < 60; i++)
            $(id).append("<option value=" + String(i).padStart(2, '0') + ">" + i + "</option>");
    }

    function getDays() {
        $.get("http://localhost:8080/project/util/day", function(data) {
            console.log(data);
            for (d in data) {
                $("#searchDaySelect").append("<option value=" + data[d] + ">" + data[d] + "</option>");
            }
        });
    }

    function setupActivityLabels() {
        $(".activityLabel").on("click", function(e) {
            let activityIdAux = $(e.currentTarget).children()[0].textContent;
            activityId = parseInt(activityIdAux.substring(activityIdAux.search(/[0-9]+/)));

            activityName = $(e.currentTarget).children()[1].textContent.replace("Name: ", "");

            $(".activityLabel").css("color", "black");
            $(this).css("color", "blue");

            $("#chosenActivityName").text(activityName);
            $("#chosenActivityId").text(activityId);

            console.log("Chosen activity:" + activityId);
        });
    }

    function getUserTimetables() {
        $.get("http://localhost:8080/project/user/" + Cookies.get("id") + "/chosen_timetables", function(data) {
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

    function getValidActivities(filterName = "", filterDay = "") {
        let myUrl = "";
        console.log(filterDay);
        if (filterName === "" && filterDay === "") {
            myUrl = "http://localhost:8080/project/user/activity/get_by_qualification";
            filterName = null;
            filterDay = null;
        }
        else {
            myUrl = "http://localhost:8080/project/user/activity/get_filtered";
            filterName = (filterName === "" ? null : filterName);
            filterDay = (filterDay === "" ? null : filterDay);
        }

        let body = JSON.stringify(
            {
                qualification: Cookies.get("qualification"),
                name: filterName,
                day: filterDay
            });
        console.log(body);

        $.ajax({
            type: "POST",
            url: myUrl,
            data: body,
            dataType: 'json',
            contentType: "application/json; charset=utf-8",
            success: function(data) {
                console.log(data);
                $("#activityList").empty();
                let ok = false;
                for (d in data) {
                    if (activityId === data[d].id)
                        ok = true;
                    $("#activityList").append("<div class='activityLabel' " + (activityId === data[d].id ? "style=color:blue" : "") +
                                                "><div>ID: "+ data[d].id +
                                                "</div><div>Name: " + data[d].name +
                                                "</div><div>NbPeople: " + data[d].nbPeople +
                                                "</div><div>MinTime: " + data[d].minTime +
                                                "</div><div>IntervalStart: " + data[d].intervalStart +
                                                "</div><div>IntervalEnd: " + data[d].intervalEnd +
                                                "</div><div>Day: " + data[d].day +
                                                "</div><div>Qualification: " + data[d].qualification +
                                                "</div></div><br>");
                }
                if (ok === false) {
                    activityId = -1;
                    activityName = "";
                    $("#chosenActivityName").text("*Activity Name*");
                    $("#chosenActivityId").text("*ID*");
                }
                setupActivityLabels();
            }
        });
    }

    prepareHourSelect("#activityIntervalStartHour");
    prepareMinuteSelect("#activityIntervalStartMinute");

    prepareHourSelect("#activityIntervalEndHour");
    prepareMinuteSelect("#activityIntervalEndMinute");

    getDays();
    getValidActivities();
    getUserTimetables();


});