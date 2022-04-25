var total = 0.;

$(".table tbody tr:visible td:nth-child(3)").each(function () {

total += parseFloat($(this).text());
$("#total").html(total);
});

