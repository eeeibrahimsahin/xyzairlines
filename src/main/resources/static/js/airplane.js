let willDeleteAirplaneId;

$(document).ready(function () {
    $.ajax({
        type: "GET",
        url: "/api/airplane",
        success: function (response) {
            console.log(response);
        },
    });
    $("#table").DataTable({
        ajax: {
            url: "/api/airplane",
            dataSrc: "",
        },
        columns: [
            { data: "id" },
            { data: "plate" },
            { data: "type" },
            { data: "currentAirport.name" },
            {
                data: function (data) {
                    let stringData = "";
                    data.destinationsAirports.forEach((element) => {
                        stringData += element.name + "<br/>";
                    });
                    return stringData;
                },
            },
            { data: "currentFuel" },
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
        url: "/api/airplane",
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
    willDeleteAirplaneId = rowData.id;
    getModal(
        "Information",
        "The airplane will be deleted!",
        "Yes I'm sure",
        "btn-primary"
    );
});
$("#modal-confirmBtn").click(function (e) {
    e.preventDefault();
    $.ajax({
        type: "DELETE",
        url: "/api/airplane/" + willDeleteAirplaneId,
        success: function (response) {
            getModal(
                "Information",
                "The airplane is deleted!",
                "hello",
                "btn-primary",
                true
            );
        },
    });
});
