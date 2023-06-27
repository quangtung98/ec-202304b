"use strict";

$(function () {
  // Get all quote elements inside the article
  const itemName = $(".page-title").text();

  // Get a url of the current page
  const currentPageUrl = window.location.href;

  $("#postTwitter").on("click", function () {
    window.location = makeTweetableUrl(itemName, currentPageUrl);
    // console.log(makeTweetableUrl(itemName, currentPageUrl));
  });
});

function makeTweetableUrl(itemName, currentPageUrl) {
  const text1 = "この商品をおすすめしています！\n";
  const text2 = "\n#らくらくコーヒー #" + itemName + "\n";
  const tweetableText =
    "https://twitter.com/intent/tweet?text=" +
    encodeURIComponent(text1) +
    encodeURIComponent(currentPageUrl) +
    encodeURIComponent(text2);
  return tweetableText;
}
