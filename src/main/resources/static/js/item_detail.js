"use strict";
$(function () {
  $(".calcPrice").on("change", function () {
    let sizePrice = 0;
    console.log($(".size:checked").val());
    let toppingCount = $("#topping input:checkbox:checked").length;
    let toppingPrice = 0;
    if ($(".size:checked").val() === "M") {
      sizePrice += $("#sizeM").text();
      toppingPrice = 200 * toppingCount;
    } else if ($(".size:checked").val() === "L") {
      sizePrice += $("#sizeL").text();
      toppingPrice = 300 * toppingCount;
    }

    let pizaNum = $("#pizanum").val();
    let totalPrice = (parseInt(sizePrice) + parseInt(toppingPrice)) * pizaNum;
    $("#total-price").text(totalPrice);
  });
});
