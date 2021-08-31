let airplanes;
let airports;
let airplaneInfo;
let flightJSON;
let plateEl = $("#validationCustom01");
let typeEl = $("#validationCustom04");
let currentAirportEl = $("#validationCustom05");
let destinationsAirportsEl = $("#validationCustom06");
let currentFuelEl = $("#validationCustom07");
let tankCapacityEl = $("#validationCustom08");
$(document).ready(async function () {
    await doAjax("GET", "/api/airplane").then((data) => (airplanes = data));
    setOptions("flightPlate", airplanes, false);
});

$("#airplaneForm").submit(async function (e) {
    e.preventDefault();
    $("#set-flight-div").removeClass("d-none");
    let plate = $("select[name=plate] option").filter(":selected").val();
    let url = "/api/airplane/plate/" + plate;
    await doAjax("GET", url).then((data) => (airplaneInfo = data));
    await doAjax("GET", "/api/airport").then((data) => (airports = data));
    setOptions("validationCustom06", airports, true);
    plateEl.val(airplaneInfo.plate);
    plateEl.prop("readonly", true);
    typeEl.val(airplaneInfo.type);
    typeEl.prop("readonly", true);
    currentAirportEl.val(airplaneInfo.currentAirport.name);
    currentAirportEl.prop("readonly", true);
    currentFuelEl.val(airplaneInfo.currentFuel);
    currentFuelEl.prop("readonly", true);
    tankCapacityEl.val(airplaneInfo.tankCapacity);
    tankCapacityEl.prop("readonly", true);
});

$("#setFlightForm").submit(async function (e) {
    e.preventDefault();
    let plate = plateEl.val();
    let type = typeEl.val();
    let currentAirport = currentAirportEl.val();
    let destinationsAirports = $("select[name=destinationAirport] option")
        .filter(":selected")
        .val();
    let currentFuel = currentFuelEl.val();
    let tankCapacity = tankCapacityEl.val();
    let url = "api/airport/" + destinationsAirports;
    let destinationAirportId;
    await doAjax("GET", url).then((data) => (destinationAirportId = data.id));
    let flightJsObj = {
        id: airplaneInfo.id,
        plate,
        type,
        currentAirport: { id: airplaneInfo.currentAirport.id },
        destinationsAirports: [{ id: destinationAirportId }],
        currentFuel,
        tankCapacity,
    };
    console.log(flightJsObj);
    flightJSON = JSON.stringify(flightJsObj);
    getModal(
        "Hey,there!",
        "<p>Are you sure want to set the flight?.</p>",
        "YEP, I'M SURE:)",
        "btn-primary"
    );
});

function setOptions(selectionName, optionsData, isAirport) {
    let options = "";
    optionsData.forEach((element) => {
        options += `<option value="${
            isAirport ? element.name : element.plate
        }">${isAirport ? element.name : element.plate}</option>`;
    });
    $(`#${selectionName}`).html(options);
}

async function doAjax(requestType, url) {
    let result = await $.ajax({
        type: requestType,
        url: url,
    });
    return result;
}

$("#modal-confirmBtn").click(function (e) {
    e.preventDefault();
    console.log(flightJSON);
    $.ajax({
        type: "POST",
        url: "api/airplane/setflight",
        data: flightJSON,
        contentType: "application/json",
        success: function (response) {
            console.log(response);
            getModal(
                "Information",
                "The flight is set!",
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
