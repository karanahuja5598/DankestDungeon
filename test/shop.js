

export function renderIAPs (el) {

	var html = "<button class = 'close' onclick = 'overlay()'></button><ul>";

	//Add all the normal items
	html += "<li>" +
	"<h3>Pink Headband</h3>" +
	"<p><img src = 'assets/buypinkheadband.png' /> Real ninjas wear pink! Let the colour of femininity and tenderness guide you on your path to greatness.</p>" +
	"<button id='pinkHeadband' type='button' " +
	"onclick='purchaseHeadband(\"pinkHeadband\", 5)'>5 <img class = 'shurikenPrice' src = 'assets/shurikenscore.png' /></button>" +
	"</li>";

	html += "<li>" +
	"<h3>Blue Headband</h3>" +
	"<p><img src = 'assets/buyblueheadband.png' /> It is said that the colour blue brings depth and stability. Maybe this headband will stop you falling off the rope so much?</p>" +
	"<button id = 'blueHeadband' type='button' " +
	"onclick='purchaseHeadband(\"blueHeadband\", 5)'>5 <img class = 'shurikenPrice' src = 'assets/shurikenscore.png' /></button>" +
	"</li>";

	html += "<li>" +
	"<h3>Level Pack</h3>" +
	"<p><img src = 'assets/buylevels.png' /> A ninja gets bored playing on the same old levels all the time. Purchase this to unlock 4 new levels to play on!</p>" +
	"<button id = 'levels' type='button' " +
	"onclick='purchaseLevels(5)'>5 <img class = 'shurikenPrice' src = 'assets/shurikenscore.png' /></button>" +
	"</li>";

	html += "<li>" +
	"<h3>Gold Ninja</h3>" +
	"<p><img src = 'assets/buygoldninja.png' /> The Gold Ninja is legend. It is prophecy that those who control the Gold Ninja will rise to the top of the leaderboards.</p>" +
	"<button id = 'goldninja' type='button' " +
	"onclick='purchaseNinja(5)'>5 <img class = 'shurikenPrice' src = 'assets/shurikenscore.png' /></button>" +
	"</li>";

	html += "</ul>";
	el.innerHTML = html;

	overlay();
};

export function overlay() {
	el = document.getElementById("overlay");
	el.style.visibility = (el.style.visibility == "visible") ? "hidden" : "visible";
	window.scrollTo(0, 0);
}

