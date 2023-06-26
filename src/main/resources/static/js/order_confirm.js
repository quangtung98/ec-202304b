"use strict";
$(function () {
  if ($('input:radio[name="paymentMethod"]:checked').val() === "2") {
    $("#creditCardDiv").css("display", "block");
    console.log("A");
  }
  $(".showcreditdiv").on("change", function () {
    console.log($('input:radio[name="paymentMethod"]:checked').val());
    console.log("A");
    if ($('input:radio[name="paymentMethod"]:checked').val() === "1") {
      $("#creditCardDiv").css("display", "none");
    } else if ($('input:radio[name="paymentMethod"]:checked').val() === "2") {
      $("#creditCardDiv").css("display", "block");
    } else {
      $("#creditCardDiv").css("display", "none");
    }
  });
});
