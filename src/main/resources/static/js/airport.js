let willDeleteAirportId;
$(document).ready(function () {
    $.ajax({
        type: "GET",
        url: "/api/airport",
        success: function (response) {
            console.log(response);
        },
    });
    $("#table").DataTable({
        ajax: {
            url: "/api/airport",
            dataSrc: "",
        },
        columns: [
            { data: "id" },
            { data: "name" },
            { data: "land" },
            {
                data: function (data) {
                    let stringData = "";
                    data.airplanes.forEach((element) => {
                        stringData += element.plate + "<br/>";
                    });
                    return stringData;
                },
            },
            {
                data: null,
                defaultContent: '<i class="far fa-edit"></i>',
                orderable: false,
                searchable: false,
            },
            {
                data: null,
                defaultContent: '<i class="far fa-trash-alt"></i>',
                orderable: false,
            },
        ],
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

$("#table").on("click", ".fa-edit", function () {
    rowData = table.row($(this).parents("tr").first()).data();
    let rowDataJSON = JSON.stringify(rowData);
    console.log(rowData);
    $.ajax({
        type: "PUT",
        url: "/api/airport",
        data: rowDataJSON,
        contentType: "application/json",
        success: function (response) {
            console.log(response);
            pageRedirect();
        },
    });
});
$("#table").on("click", ".fa-trash-alt", function () {
    rowData = $("#table").DataTable().row($(this).parents("tr").first()).data();
    willDeleteAirportId = rowData.id;
    getModal(
        "Information",
        "The airport will be deleted!",
        "Yes I'm sure",
        "btn-primary"
    );
});
$("#modal-confirmBtn").click(function (e) {
    e.preventDefault();
    $.ajax({
        type: "DELETE",
        url: "/api/airport/" + willDeleteAirportId,
        success: function (response) {
            getModal(
                "Information",
                "The airport is deleted!",
                "hello",
                "btn-primary",
                true
            );
        },
    });
});

$("#modal-closeBtn").click(function (e) {
    e.preventDefault();
    location.reload();
});
