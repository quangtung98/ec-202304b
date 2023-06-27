"use strict";
$(function () {
  if ($('input:radio[name="paymentMethod"]:checked').val() === "2") {
    $("#creditCardDiv").css("display", "block");
    $("#creditCardId").prop("disabled", false);
    $("#monthOfExpiry").prop("disabled", false);
    $("#yearOfExpiry").prop("disabled", false);
    $("#cardHolder").prop("disabled", false);
    $("#securityCode").prop("disabled", false);
  }
  $(".showcreditdiv").on("change", function () {
    console.log($('input:radio[name="paymentMethod"]:checked').val());
    console.log("A");
    if ($('input:radio[name="paymentMethod"]:checked').val() === "2") {
      $("#creditCardDiv").css("display", "block");
      $("#creditCardId").prop("disabled", false);
      $("#monthOfExpiry").prop("disabled", false);
      $("#yearOfExpiry").prop("disabled", false);
      $("#cardHolder").prop("disabled", false);
      $("#securityCode").prop("disabled", false);
    } else {
      $("#creditCardDiv").css("display", "none");
      $("#creditCardId").prop("disabled", true);
      $("#monthOfExpiry").prop("disabled", true);
      $("#yearOfExpiry").prop("disabled", true);
      $("#cardHolder").prop("disabled", true);
      $("#securityCode").prop("disabled", true);
    }
  });
});
