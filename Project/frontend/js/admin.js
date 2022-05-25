$(document).ready(function() {
    // Must go through login at least once
    if (Cookies.get("id") === undefined)
        window.location = "/project/login.html";

    let groupId = -1;

    $("#btnBack").on("click", function() {
        window.location = "/project/login.html";
    });

    $("#adminId").text("Hello, admin with id " + Cookies.get("id"));

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

    function setupTimetableGroupLabels() {
        $(".timetableGroupLabel").on("click", function(e) {
            let groupIdAux = $(e.currentTarget).children()[0].textContent;
            groupId = parseInt(groupIdAux.substring(groupIdAux.search(/[0-9]+/)));

            $(".timetableGroupLabel").css("color", "");
            $(".timetableGroupLabel").css("border-color", "");

            $(this).css("color", "blue");
            $(this).css("border-color", "blue");

            $("#chosenGroupId").text(groupId);

            console.log("Chosen group:" + groupId);
        });
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

    function getTimetableGroups() {
        $.get("http://localhost:8080/project/admin/timetables", function(data) {
            console.log(data);
            $("#timetableGroupList").empty();
            for (d in data) {
                let toAppend = "";
                toAppend += ("<div class='timetableGroupLabel" + (data[d].official ? " timetableGroupChosenLabel" : "") + "'><div>ID: " + data[d].id + "</div>");

                for (t in data[d].timetables) {
                    toAppend += ("<div class='timetableLabel'><div>ID: " + data[d].timetables[t].id +
                                                "</div><div>IntervalStart: " + data[d].timetables[t].intervalStart +
                                                "</div><div>IntervalEnd: " + data[d].timetables[t].intervalEnd +
                                                "</div><div>ActivityID: " + data[d].timetables[t].activity.id +
                                                "</div><div>Activity: " + data[d].timetables[t].activity.name +
                                                "</div><div>UserID: " + data[d].timetables[t].user.id +
                                                "</div><div>User: " + data[d].timetables[t].user.username +
                                                "</div></div><br>");
                }
                
                toAppend += ("</div><br>");
                $("#timetableGroupList").append(toAppend);
            }
            setupTimetableGroupLabels();
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
                getActivities();
            }
        });
    });

    $("#btnGenerate").on("click", function() {
        $.post("http://localhost:8080/project/admin/timetables/generate", function(data) {
            console.log(data);
            getTimetableGroups();
        });
    });

    $("#btnMakeOfficial").on("click", function() {
        if (groupId !== -1) {
            $.post("http://localhost:8080/project/admin/timetables/pick/" + groupId, function(data) {
                console.log(data);
                groupId = -1;
                $("#chosenGroupId").text("*Selected Group ID*");
                getTimetableGroups();
            });
        } else
            console.log("Select a group first!");
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
    getTimetableGroups();
});