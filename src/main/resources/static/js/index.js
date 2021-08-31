$("#get-airplane").click(function (e) {
    e.preventDefault();
    pageRedirect("airplane");
});
$("#get-airport").click(function (e) {
    e.preventDefault();
    pageRedirect("airport");
});
$("#get-set-flight").click(function (e) {
    e.preventDefault();
    pageRedirect("set_flight");
});

function pageRedirect(pageName) {
    window.location.href = `./${pageName}`;
}
