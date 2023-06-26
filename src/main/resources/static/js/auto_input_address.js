"use strict";
$(function () {
  // ［検索］ボタンクリックで検索開始
  $("#get_address_btn").on("click", function () {
    $("#addactive").addClass("active");
    $("#addactive2").addClass("active");
    $.ajax({
      url: "https://zipcoda.net/api",
      type: "GET",
      dataType: "json",
      data: {
        zipcode: $("#zipcode").val(),
      },
      async: true,
    })
      .done(function (data) {
        // レスポンスデータがdataに入る
        // 検索成功時にはページに結果を反映
        // コンソールに取得データを表示
        console.log(data);
        console.dir(JSON.stringify(data));
        $("#pref").val(data.items[0].components[0]);
        $("#municipalities").val(data.items[0].components[1]);
        $("#address").val(data.items[0].components[2]);
      })
      .fail(function (XMLHttpRequest, textStatus, errorThrown) {
        // 検索失敗時には、その旨をダイアログ表示
        alert("正しい結果を得られませんでした。");
        console.log("XMLHttpRequest : " + XMLHttpRequest.status);
        console.log("textStatus     : " + textStatus);
        console.log("errorThrown    : " + errorThrown.message);
      });
  });
});
