$("#get-airplane").click(function (e) {
    e.preventDefault();
    pageRedirect("airplane");
});
$("#get-airport").click(function (e) {
    e.preventDefault();
    pageRedirect("airport");
});
function pageRedirect(pageName) {
    window.location.href = `./${pageName}`;
}