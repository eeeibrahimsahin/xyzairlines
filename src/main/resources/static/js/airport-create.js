// Example starter JavaScript for disabling form submissions if there are invalid fields
(function () {
    "use strict";

    // Fetch all the forms we want to apply custom Bootstrap validation styles to
    var forms = document.querySelectorAll(".needs-validation");

    // Loop over them and prevent submission
    Array.prototype.slice.call(forms).forEach(function (form) {
        form.addEventListener(
            "submit",
            function (event) {
                if (!form.checkValidity()) {
                    event.preventDefault();
                    event.stopPropagation();
                }

                form.classList.add("was-validated");
            },
            false
        );
    });
})();

let airplanes;
let airports;
let airportJSON;
$(document).ready(async function () {
    await doAjax("GET", "/api/airplane").then((data) => (airplanes = data));
    await doAjax("GET", "/api/airport").then((data) => (airports = data));
    setOptions("validationCustom06", airplanes, false);
    setOptions("validationCustom05", airports, true);
});

function setOptions(selectionName, optionsData, isAirport) {
    let options = "";
    optionsData.forEach((element) => {
        options += `<option value="${isAirport ? element.land : element.id}">${
            isAirport ? element.land : element.plate
        }</option>`;
    });
    $(`#${selectionName}`).html(options);
}

$("#airportForm").submit(async function (e) {
    e.preventDefault();
    await createJSON();
    getModal(
        "Hey,there!",
        "<p>Are you sure want to create the airport?.</p>",
        "YEP, I'M SURE:)",
        "btn-primary"
    );
});

async function createJSON() {
    let name = $("#validationCustom01").val();
    let land = $("select[name=land] option").filter(":selected").val();
    let airplane = $("select[name=airplane] option").filter(":selected").val();
    let jsObj = {
        name,
        land,
        airplanes: [{ id: airplane }],
    };
    airportJSON = JSON.stringify(jsObj);
}

$("#modal-confirmBtn").click(function (e) {
    e.preventDefault();
    console.log(airportJSON);
    $.ajax({
        type: "POST",
        url: "/api/airport",
        data: airportJSON,
        contentType: "application/json",
        success: function (response) {
            console.log(response);
            getModal(
                "Information",
                "The airport is created!",
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
