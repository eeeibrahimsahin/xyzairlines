let airplaneJSON;
let airplaneTypes;
let airportLands;
let aiports;
let airportJSON;
$(document).ready(async function () {
    await doAjax("GET", "/api/constants").then((data) => {
        airplaneTypes = data.airplaneTypes;
        airportLands = data.airportLands;
    });
    await doAjax("GET", "/api/airport").then((data) => (airports = data));
    setOptions("validationCustom04", airplaneTypes, false);
    setOptions("validationCustom05", airports, true);
    setOptions("validationCustom06", airports, true);
});

function setOptions(selectionName, optionsData, isAirport) {
    let options = "";
    optionsData.forEach((element) => {
        options += `<option value="${isAirport ? element.name : element}">${
            isAirport ? element.name : element
        }</option>`;
    });
    $(`#${selectionName}`).html(options);
}

$("#airplaneForm").submit(function (e) {
    e.preventDefault();
    createJSON();
    getModal(
        "Hey,there!",
        "<p>Are you sure want to create the airplane?.</p>",
        "YEP, I'M SURE:)",
        "btn-primary"
    );
});

async function createJSON() {
    let plate = $("#validationCustom01").val();
    let type = $("select[name=type] option").filter(":selected").val();
    let currentAirport = $("select[name=currentAirport] option")
        .filter(":selected")
        .val();
    let destinationsAirports = $("select[name=destinationAirport] option")
        .filter(":selected")
        .val();
    let currentFuel = $("#validationCustom07").val();
    let tankCapacity = $("#validationCustom08").val();
    let url1 = "api/airport/" + currentAirport;
    let url2 = "api/airport/" + destinationsAirports;
    let currentAirportId;
    await doAjax("GET", url1).then((data) => (currentAirportId = data.id));

    let destinationAirportId;
    await doAjax("GET", url2).then((data) => (destinationAirportId = data.id));

    let airplaneJsObj = {
        plate,
        type,
        currentAirport: { id: currentAirportId },
        destinationsAirports: [{ id: destinationAirportId }],
        currentFuel,
        tankCapacity,
    };
    airplaneJSON = JSON.stringify(airplaneJsObj);
}

$("#modal-confirmBtn").click(function (e) {
    e.preventDefault();
    console.log(airplaneJSON);
    $.ajax({
        type: "POST",
        url: "api/airplane",
        data: airplaneJSON,
        contentType: "application/json",
        success: function (response) {
            console.log(response);
            getModal(
                "Information",
                "The airplane is created!",
                "hello",
                "btn-primary",
                true
            );
        },
    });
});

function getModal(header, text, confirmationWord, buttonColor, deleteButton) {
    $("#modal-confirmBtn").show();
    $("#modal-confirmBtn").addClass(buttonColor);
    $("#modal-title").html(header);
    $("#modal-confirmBtn").html(confirmationWord);
    $("#modal-body").html(text);
    $("#modal").modal("show");
    if (deleteButton == true) {
        $("#modal-confirmBtn").hide();
        $("#modal-closeBtn").html("Close");
    }
}

async function doAjax(requestType, url) {
    let result = await $.ajax({
        type: requestType,
        url: url,
    });
    return result;
}
