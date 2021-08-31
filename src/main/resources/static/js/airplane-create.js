let airplaneJSON;

// Example starter JavaScript for disabling form submissions if there are invalid fields
(function () {
    'use strict'
  
    // Fetch all the forms we want to apply custom Bootstrap validation styles to
    var forms = document.querySelectorAll('.needs-validation')
  
    // Loop over them and prevent submission
    Array.prototype.slice.call(forms)
      .forEach(function (form) {
        form.addEventListener('submit', function (event) {
          if (!form.checkValidity()) {
            event.preventDefault()
            event.stopPropagation()
          }
  
          form.classList.add('was-validated')
        }, false)
      })
  })()



  $("#airplaneForm").submit(function (e) { 
      e.preventDefault();
      let plate = $("#validationCustom01").val()
      let type = $('select[name=type] option').filter(':selected').val()
      let currentAirport = $('select[name=currentAirport] option').filter(':selected').val()
      let destinationsAirports =  $('select[name=destinationAirport] option').filter(':selected').val()
      let currentFuel = $("#validationCustom07").val();
      let tankCapacity = $("#validationCustom08").val()

let currentAirportId;
let destinationAirportId;
$.ajax({
    type: "GET",
    url: "api/airport/"+currentAirport,
    success: function (response) {
        console.log(response)
        currentAirportId=response.id;
    }
});
      $.ajax({
          type: "GET",
          url: "api/airport/"+destinationsAirports,
          success: function (response) {
              console.log(response)
              destinationAirportId=response.id;
          }
      });

      let airplaneJsObj = {
          plate,
          type,
          "currentAirport":{"id":currentAirportId},
          "destinationsAirports":[
              {"id":destinationAirportId}
          ],
          currentFuel,
          tankCapacity
      }




      airplaneJSON = JSON.stringify(airplaneJsObj);

      console.log(airplaneJsObj)
      getModal("Hey,there!", "<p>Are you sure want to create the airplane?.</p>", "YEP, I'M SURE:)", "btn-primary");
  });


  $("#modal-confirmBtn").click(function(e) {
    e.preventDefault();
    $.ajax({
        type: "POST",
        url: "api/airplane",
        data: airplaneJSON,
        contentType: "application/json",
        success: function(response) {
            console.log(response)
            getModal("Information", "The airplane is created!", "hello", "btn-primary", true)
        }
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