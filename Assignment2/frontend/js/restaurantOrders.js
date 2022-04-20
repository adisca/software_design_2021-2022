$(document).ready(function() {
    // Must come from admin page
    if (Cookies.get("restaurantName") === undefined || Cookies.get("restaurantId") === undefined)
        window.location = "/foodpanda/adminRestaurants.html";

    $("#restaurantName").text("Orders for restaurant: " + Cookies.get("restaurantName"));
    $("#restaurantId").text("ID: " + Cookies.get("restaurantId"));
    $("#btnBack").on("click", function() {
        window.location = "/foodpanda/adminRestaurants.html";
    });

    // Filter
    $("#filterSelect").on("change", function(e) {
        console.log(e.currentTarget.value);
        getHistory(e.currentTarget.value);
    });

    // Refresh
    $("#historyBtn").on("click", function() {
        getHistory();
    });

    function getIDFromBtn(btn) {
        return $(btn.currentTarget).parent().parent().children()[0].textContent.replace("ID: ", "");
    }

    function setupStatusBtns() {
        $(".btnAccept").on("click", function(e) {
            $.ajax({
                type: "POST",
                url: "http://localhost:8080/foodpanda/orders/" + getIDFromBtn(e) + "/ACCEPTED",
                dataType: 'json',
                contentType: "application/json; charset=utf-8",
                success: function(data) {
                    console.log(data.status);
                    getHistory();
                }
            });
        });
        $(".btnDecline").on("click", function(e) {
            $.ajax({
                type: "POST",
                url: "http://localhost:8080/foodpanda/orders/" + getIDFromBtn(e) + "/DECLINED",
                dataType: 'json',
                contentType: "application/json; charset=utf-8",
                success: function(data) {
                    console.log(data.status);
                    getHistory();
                }
            });
        });
        $(".btnMaking").on("click", function(e) {
            $.ajax({
                type: "POST",
                url: "http://localhost:8080/foodpanda/orders/" + getIDFromBtn(e) + "/IN_MAKING",
                dataType: 'json',
                contentType: "application/json; charset=utf-8",
                success: function(data) {
                    console.log(data.status);
                    getHistory();
                }
            });
        });
        $(".btnTransit").on("click", function(e) {
            $.ajax({
                type: "POST",
                url: "http://localhost:8080/foodpanda/orders/" + getIDFromBtn(e) + "/IN_TRANSIT",
                dataType: 'json',
                contentType: "application/json; charset=utf-8",
                success: function(data) {
                    console.log(data.status);
                    getHistory();
                }
            });
        });
        $(".btnDelivered").on("click", function(e) {
            $.ajax({
                type: "POST",
                url: "http://localhost:8080/foodpanda/orders/" + getIDFromBtn(e) + "/DELIVERED",
                dataType: 'json',
                contentType: "application/json; charset=utf-8",
                success: function(data) {
                    console.log(data.status);
                    getHistory();
                }
            });
        });
    }

    function getHistory(filter="") {
        let myUrl = "";
        if (filter === "")
            myUrl = "http://localhost:8080/foodpanda/restaurant/" + Cookies.get("restaurantId") + "/orders";
        else
            myUrl = "http://localhost:8080/foodpanda/restaurant/" + Cookies.get("restaurantId") + "/orders/" + filter;
        $.get(myUrl, function(data) {
            console.log(data);
            $("#historyList").empty();
            for (d in data) {
                let strHistoryList = "";
                let total = 0.;
                strHistoryList = strHistoryList.concat("<div class='historyLabel' id='order" + data[d].id +
                                        "'><div>ID: " + data[d].id +
                                        "</div><div>Client: " + data[d].belongsTo.username +
                                        "</div><div>Status: " + data[d].status +
                                        `</div>
                                            <div class="veilBtnSet1" hidden>
                                                <button id="accept" class="btnAccept">Accept</button>
                                                <button id="accept" class="btnDecline">Decline</button>
                                            </div>
                                            <div class="veilBtnSet2" hidden>
                                                <button id="accept" class="btnMaking">Making</button>
                                                <button id="accept" class="btnTransit">Transit</button>
                                                <button id="accept" class="btnDelivered">Delivered</button>
                                            </div>
                                        <div>Foods:</div><div class='slightly_right'>`);
                for (f in data[d].foods) {
                    total += data[d].foods[f].price;
                    strHistoryList = strHistoryList.concat("<div><div>ID: " + data[d].foods[f].id +
                    "</div><div>Name: " + data[d].foods[f].name +
                    "</div><div>Price: " + data[d].foods[f].price +
                    "</div></div><br>");
                }
                strHistoryList = strHistoryList.concat("</div><div>Total:" + total + "$</div></div><br>");
                $("#historyList").append(strHistoryList);

                switch(data[d].status) {
                    case "PENDING":
                        $("#order" + data[d].id).children()[3].hidden = false;
                        break;
                    case "ACCEPTED":
                        $("#order" + data[d].id).children()[4].hidden = false;
                        break;
                    case "IN_MAKING":
                        $("#order" + data[d].id).children()[4].hidden = false;
                        $("#order" + data[d].id).children()[4].children[0].hidden = true;
                        break;
                    case "IN_TRANSIT":
                        $("#order" + data[d].id).children()[4].hidden = false;
                        $("#order" + data[d].id).children()[4].children[0].hidden = true;
                        $("#order" + data[d].id).children()[4].children[1].hidden = true;
                        break;
                    default:
                        break;
                }
            }
            setupStatusBtns();
        });
    }

    getHistory();

});