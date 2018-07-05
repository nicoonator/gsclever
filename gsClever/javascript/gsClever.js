addListener('standardEvent', function(event) {
    var stringFromServer = event.data;
    var arr = stringFromServer.split(',');
    // console.log(arr);

    if (arr.length == 11) {
	for (var i = 0; i < 9; i++) {
	    arrFields[i] = +arr[i];
	}
	playerMessage = arr[9];
	var str = arr[10];
	if (str == "HOST") {
	    console.log(arr[10]);
	    setVisible();
	}

	sentFields = [ 0, 0, 0, 0, 0, 0, 0, 0, 0 ];
	document.getElementById("Player").innerHTML = playerMessage;
	redraw();
    }
    statusWait = false;
});
addListener('START', function(event) {
    // TODO
});
addListener('PLAYERLEFT', function(event) {
    var stringFromServer = event.data;
    playerMessage = stringFromServer;
    document.getElementById("Player").innerHTML = playerMessage;
});
addListener(
	'CLOSE',
	function(event) {
	    document.getElementById("Player").innerHTML = "Spiel wurde vom Host beendet!";
	});
addListener('TESTWUERFELN', function(event) {
    var stringFromServer = event.data;
    var arr = stringFromServer.split(',');
    console.log(stringFromServer);
    console.log(arr);
    for (var i = 0; i < 6; i++) {
	number[i] = +arr[i];
    }
    // var number = Number(stringFromServer);
    // Hier wird ein Array uebergeben:

    setDice(number);
});

var playerAmount = 2;
var userNumber=0;
// 1 = Host, 2 = Spieler2 usw...
var number = [ 0, 0, 0, 0, 0, 0 ];
playerMessage = "";
var BlauWert = 0;
var GelbWert = 0;
var OrangeWert = 0;
var LilaWert = 0;
var GreenWert = 0;
var WeissWert = 0;
var Blau1 = "/gsClever/Images/Dice/1BLAU.jpg";
var Blau2 = "/gsClever/Images/Dice/2BLAU.jpg";
var Blau3 = "/gsClever/Images/Dice/3BLAU.jpg";
var Blau4 = "/gsClever/Images/Dice/4BLAU.jpg";
var Blau5 = "/gsClever/Images/Dice/5BLAU.jpg";
var Blau6 = "/gsClever/Images/Dice/6BLAU.jpg";
var Gelb1 = "/gsClever/Images/Dice/1GELB.jpg";
var Gelb2 = "/gsClever/Images/Dice/2GELB.jpg";
var Gelb3 = "/gsClever/Images/Dice/3GELB.jpg";
var Gelb4 = "/gsClever/Images/Dice/4GELB.jpg";
var Gelb5 = "/gsClever/Images/Dice/5GELB.jpg";
var Gelb6 = "/gsClever/Images/Dice/6GELB.jpg";
var Green1 = "/gsClever/Images/Dice/1GREEN.jpg";
var Green2 = "/gsClever/Images/Dice/2GREEN.jpg";
var Green3 = "/gsClever/Images/Dice/3GREEN.jpg";
var Green4 = "/gsClever/Images/Dice/4GREEN.jpg";
var Green5 = "/gsClever/Images/Dice/5GREEN.jpg";
var Green6 = "/gsClever/Images/Dice/6GREEN.jpg";
var Lila1 = "/gsClever/Images/Dice/1LILA.jpg";
var Lila2 = "/gsClever/Images/Dice/2LILA.jpg";
var Lila3 = "/gsClever/Images/Dice/3LILA.jpg";
var Lila4 = "/gsClever/Images/Dice/4LILA.jpg";
var Lila5 = "/gsClever/Images/Dice/5LILA.jpg";
var Lila6 = "/gsClever/Images/Dice/6LILA.jpg";
var Orange1 = "/gsClever/Images/Dice/1ORANGE.jpg";
var Orange2 = "/gsClever/Images/Dice/2ORANGE.jpg";
var Orange3 = "/gsClever/Images/Dice/3ORANGE.jpg";
var Orange4 = "/gsClever/Images/Dice/4ORANGE.jpg";
var Orange5 = "/gsClever/Images/Dice/5ORANGE.jpg";
var Orange6 = "/gsClever/Images/Dice/6ORANGE.jpg";
var White1 = "/gsClever/Images/Dice/1WHITE.jpg";
var White2 = "/gsClever/Images/Dice/2WHITE.jpg";
var White3 = "/gsClever/Images/Dice/3WHITE.jpg";
var White4 = "/gsClever/Images/Dice/4WHITE.jpg";
var White5 = "/gsClever/Images/Dice/5WHITE.jpg";
var White6 = "/gsClever/Images/Dice/6WHITE.jpg";
// TODO
var img1 = "/gsClever/Images/Numbers/1.png";
var img2 = "/gsClever/Images/Numbers/2.png";
var img3 = "/gsClever/Images/Numbers/3.png";
var img4 = "/gsClever/Images/Numbers/4.png";
var img5 = "/gsClever/Images/Numbers/5.png";
var img6 = "/gsClever/Images/Numbers/6.png";
var img8 = "/gsClever/Images/Numbers/8.png";
var img9 = "/gsClever/Images/Numbers/9.png";
var img12 = "/gsClever/Images/Numbers/12.png";
var img15 = "/gsClever/Images/Numbers/15.png";
var img18 = "/gsClever/Images/Numbers/18.png";

var ximg = "/gsClever/Images/X.png";
var circleimg = "/gsClever/Images/circle.png";
var circleximg = "/gsClever/Images/circlex.png";
var statusWait = true;

var arrFields = [ 0, 0, 0, 0, 0, 0, 0, 0, 0 ];
var sentFields = [ 0, 0, 0, 0, 0, 0, 0, 0, 0 ];
var currentPlayer = 0;

function setField(x) {
    sentFields[x] = 1;
}

function getDiceImg(x) {
    switch (x) {
    case 0:
	return
	

    case 1:
	return Blau1
    case 2:
	return Blau2
    case 3:
	return Blau3
    case 4:
	return Blau4
    case 5:
	return Blau5
    case 6:
	return Blau6

    case 7:
	return Gelb1
    case 8:
	return Gelb2
    case 9:
	return Gelb3
    case 10:
	return Gelb4
    case 11:
	return Gelb5
    case 12:
	return Gelb6

    case 13:
	return Green1
    case 14:
	return Green2
    case 15:
	return Green3
    case 16:
	return Green4
    case 17:
	return Green5
    case 18:
	return Green6

    case 19:
	return Lila1
    case 20:
	return Lila2
    case 21:
	return Lila3
    case 22:
	return Lila4
    case 23:
	return Lila5
    case 24:
	return Lila6

    case 25:
	return Orange1
    case 26:
	return Orange2
    case 27:
	return Orange3
    case 28:
	return Orange4
    case 29:
	return Orange5
    case 30:
	return Orange6

    case 31:
	return White1
    case 32:
	return White2
    case 33:
	return White3
    case 34:
	return White4
    case 35:
	return White5
    case 36:
	return White6
    }
}
function getImg(x) {
    switch (x) {
    case 0:
	return ximg
    case 1:
	return circleimg
    }
}
function getNumberImg(x) {
    switch (x) {
    case 1:
	return img1
    case 2:
	return img2
    case 3:
	return img3
    case 4:
	return img4
    case 5:
	return img5
    case 6:
	return img6
    case 8:
	return img8
    case 9:
	return img9
    case 12:
	return img12
    case 15:
	return img15
    case 18:
	return img18
    }
}

/*
 * (window).on('load',function () { // TODO: Code goes here }
 */

function updateGameState() {
    statusWait = true;
    sendDataToServer(sentFields);
}

function restart() {
    statusWait = true;
    sendDataToServer("RESTART");
}
function setVisible() {
    document.getElementById("restartButton").style.visibility = "visible";
    document.getElementById("closeButton").style.visibility = "visible";
}
function closeGame() {
    sendDataToServer("CLOSE");
}
function wuerfeln() {
    sendDataToServer("WUERFELN");
}
function nachwuerfeln() {
    sendDataToServer("NACHWUERFELN");
}
function zusatzwuerfeln() {
    sendDataToServer("ZUSATZWUERFELN");
}
function showview(x) {
    switch (x) {
    case 2: {
	document.getElementById("spieler2").style.visibility = "visible";
	document.getElementById("spieler3").style.visibility = "hidden";
	document.getElementById("spieler4").style.visibility = "hidden";
	break;
    }
	;
    case 3: {
	document.getElementById("spieler2").style.visibility = "hidden";
	document.getElementById("spieler3").style.visibility = "visible";
	document.getElementById("spieler4").style.visibility = "hidden";
	break;
    }
    case 4: {
	document.getElementById("spieler2").style.visibility = "hidden";
	document.getElementById("spieler3").style.visibility = "hidden";
	document.getElementById("spieler4").style.visibility = "visible";
	break;
    }
    }
}
function skip() {
    sendDataToServer("SKIP");
}
function setDice(x) {
    // Hier wird das Silberfeld zurueckgesetzt wenn die neuen Wuerfel geladen
    // werden
    document.getElementById("wuerfelfeldsilber1").src = "";
    document.getElementById("wuerfelfeldsilber2").src = "";
    document.getElementById("wuerfelfeldsilber3").src = "";
    document.getElementById("wuerfelfeldsilber4").src = "";
    document.getElementById("wuerfelfeldsilber5").src = "";
    document.getElementById("wuerfelfeldsilber6").src = "";

    for (var i = 0; i < 6; i++) {
	setDiceImg(x[i]);
    }
}

function clearTray() {
    // Hier wird das Silberfeld zurueckgesetzt wenn die neuen Wuerfel geladen
    // werden
    document.getElementById("wuerfelfeldsilber1").src = "";
    document.getElementById("wuerfelfeldsilber2").src = "";
    document.getElementById("wuerfelfeldsilber3").src = "";
    document.getElementById("wuerfelfeldsilber4").src = "";
    document.getElementById("wuerfelfeldsilber5").src = "";
    document.getElementById("wuerfelfeldsilber6").src = "";
}

function setDiceImg(x) {
    if (x > 0 && x <= 6) {
	document.getElementById("wuerfelfeldsilber3").src = getDiceImg(x);
    }
    if (x > 6 && x <= 12) {
	document.getElementById("wuerfelfeldsilber2").src = getDiceImg(x);
    }
    if (x > 12 && x <= 18) {
	document.getElementById("wuerfelfeldsilber6").src = getDiceImg(x);
    }
    if (x > 18 && x <= 24) {
	document.getElementById("wuerfelfeldsilber5").src = getDiceImg(x);
    }
    if (x > 24 && x <= 30) {
	document.getElementById("wuerfelfeldsilber4").src = getDiceImg(x);
    }
    if (x > 30 && x <= 36) {
	document.getElementById("wuerfelfeldsilber1").src = getDiceImg(x);
    }
}
function setDiceOnTray(x) {
    if (x > 0 && x <= 6) {
	document.getElementById("wuerfelfeldsilber3").src = getDiceImg(x);
    }
    if (x > 6 && x <= 12) {
	document.getElementById("wuerfelfeldsilber2").src = getDiceImg(x);
    }
    if (x > 12 && x <= 18) {
	document.getElementById("wuerfelfeldsilber6").src = getDiceImg(x);
    }
    if (x > 18 && x <= 24) {
	document.getElementById("wuerfelfeldsilber5").src = getDiceImg(x);
    }
    if (x > 24 && x <= 30) {
	document.getElementById("wuerfelfeldsilber4").src = getDiceImg(x);
    }
    if (x > 30 && x <= 36) {
	document.getElementById("wuerfelfeldsilber1").src = getDiceImg(x);
    }
}

function setDiceOnTray(x) {
    if (x > 0 && x <= 6) {
	document.getElementById("wuerfelfeldblue").src = getDiceImg(x);
    }
    if (x > 6 && x <= 12) {
	document.getElementById("wuerfelfeldyellow").src = getDiceImg(x);
    }
    if (x > 12 && x <= 18) {
	document.getElementById("wuerfelfeldorange").src = getDiceImg(x);
    }
    if (x > 18 && x <= 24) {
	document.getElementById("wuerfelfeldgreen").src = getDiceImg(x);
    }
    if (x > 24 && x <= 30) {
	document.getElementById("wuerfelfeldpurple").src = getDiceImg(x);
    }
    if (x > 30 && x <= 36) {
	document.getElementById("wuerfelfeldwhite").src = getDiceImg(x);
    }
}
function clearWuerfelfeld () {
    // Hier wird das Silberfeld zurueckgesetzt wenn die neuen Wuerfel geladen
    // werden
    document.getElementById("wuerfelfeldwhite").src = "";
    document.getElementById("wuerfelfeldyellow").src = "";
    document.getElementById("wuerfelfeldblue").src = "";
    document.getElementById("wuerfelfeldorange").src = "";
    document.getElementById("wuerfelfeldgreen").src = "";
    document.getElementById("wuerfelfeldpurple").src = "";
}
function setRound(x) {
    var elements = document.getElementsByClassName('Rundefeld1');
    for (var i = 0; i < elements.length; i++) {
	elements[i].style.visibility = "visible";
    }
    if (x > 1) {
	elements = document.getElementsByClassName('Rundefeld2');
	for (var i = 0; i < elements.length; i++) {
	    elements[i].style.visibility = "visible";
	}
    }
    if (x > 2) {
	elements = document.getElementsByClassName('Rundefeld3');
	for (var i = 0; i < elements.length; i++) {
	    elements[i].style.visibility = "visible";
	}
    }
    if (x > 3) {
	elements = document.getElementsByClassName('Rundefeld4');
	for (var i = 0; i < elements.length; i++) {
	    elements[i].style.visibility = "visible";
	}
    }
    if (x > 4) {
	elements = document.getElementsByClassName('Rundefeld5');
	for (var i = 0; i < elements.length; i++) {
	    elements[i].style.visibility = "visible";
	}
    }
    if (x > 5) {
	elements = document.getElementsByClassName('Rundefeld6');
	for (var i = 0; i < elements.length; i++) {
	    elements[i].style.visibility = "visible";
	}
    }
}

function currentPlayerField(){
    if (currentPlayer==userNumber){
	return "spieler1";
    }
    else{ // Wenn ich nicht am wuerfeln bin
	switch (currentPlayer){
        	case 1: {
        	    // hier bin ich NICHT user1
        	    switch(userNumber){
        	    case 2: return "spieler2";
        	    case 3: return "spieler3";
        	    case 4: return "spieler4";
        	    }
        	    break;
        	}
        	case 2: {
        	    switch(userNumber){
        	    case 1: return "spieler2";
        	    case 3: return "spieler3";
        	    case 4: return "spieler4";
        	    }
        	    break;
        	}
        	case 3: {
        	    switch(userNumber){
        	    case 2: return "spieler2";
        	    case 1: return "spieler3";
        	    case 4: return "spieler4";
        	    }
        	    break;
        	}
        	case 4: {
        	    switch(userNumber){
        	    case 2: return "spieler2";
        	    case 3: return "spieler3";
        	    case 1: return "spieler4";
        	    }
        	    break;
        	}
	}
    }
}

function getFieldID(SpielerNR){
    if (SpielerNR==userNumber){
    	return "spieler1";
    }
    else{
    	switch (SpielerNR){
			case 1: {
			    // hier bin ich NICHT user1
			    switch(userNumber){
			    case 2: return "spieler2";
			    case 3: return "spieler3";
			    case 4: return "spieler4";
			    }
			    break;
			}
			case 2: {
			    switch(userNumber){
			    case 1: return "spieler2";
			    case 3: return "spieler3";
			    case 4: return "spieler4";
			    }
			    break;
			}
			case 3: {
			    switch(userNumber){
			    case 2: return "spieler2";
			    case 1: return "spieler3";
			    case 4: return "spieler4";
			    }
			    break;
			}
			case 4: {
			    switch(userNumber){
			    case 2: return "spieler2";
			    case 3: return "spieler3";
			    case 1: return "spieler4";
			    }
			    break;
			}
    	}
    }
}

function setDiceOnField(fieldNR, dice){
    var nodes = document.getElementById(currentPlayerField()).childNodes;
    switch (fieldNR){
    case 1: {
	for(var i=0; i<nodes.length; i++) {
	    if (nodes[i].id == 'Wuerfelfeld1') {
		switch (dice){
		case 1:{
		    nodes[i].src=getDiceImg(BlauWert);
		    break;
		}
		case 2:{
		    nodes[i].src=getDiceImg(GelbWert);
		    break;
		}
		case 3:{
		    nodes[i].src=getDiceImg(OrangeWert);
		    break;
		}
		case 4:{
		    nodes[i].src=getDiceImg(LilaWert);
		    break;
		}
		case 5:{
		    nodes[i].src=getDiceImg(GreenWert);
		    break;
		}
		case 6:{
		    nodes[i].src=getDiceImg(WeissWert);
		    break;
		}
		}
	     }
	}
	break;
    }
    case 2: {
	for(var i=0; i<nodes.length; i++) {
	    if (nodes[i].id == 'Wuerfelfeld2') {
		switch (dice){
		case 1:{
		    nodes[i].src=getDiceImg(BlauWert);
		    break;
		}
		case 2:{
		    nodes[i].src=getDiceImg(GelbWert);
		    break;
		}
		case 3:{
		    nodes[i].src=getDiceImg(OrangeWert);
		    break;
		}
		case 4:{
		    nodes[i].src=getDiceImg(LilaWert);
		    break;
		}
		case 5:{
		    nodes[i].src=getDiceImg(GreenWert);
		    break;
		}
		case 6:{
		    nodes[i].src=getDiceImg(WeissWert);
		    break;
		}
		}
	     }
	}
	break;
    }
    case 3: {
	for(var i=0; i<nodes.length; i++) {
	    if (nodes[i].id == 'Wuerfelfeld3') {
		switch (dice){
		case 1:{
		    nodes[i].src=getDiceImg(BlauWert);
		    break;
		}
		case 2:{
		    nodes[i].src=getDiceImg(GelbWert);
		    break;
		}
		case 3:{
		    nodes[i].src=getDiceImg(OrangeWert);
		    break;
		}
		case 4:{
		    nodes[i].src=getDiceImg(LilaWert);
		    break;
		}
		case 5:{
		    nodes[i].src=getDiceImg(GreenWert);
		    break;
		}
		case 6:{
		    nodes[i].src=getDiceImg(WeissWert);
		    break;
		}
		}
	     }
	}
	break;
    }
    }
}


function setNachwuerfelX(SpielerNR, anz /*anzahlkreise*/){
    //TODO BUGFIX
	var nodes = document.getElementById(getFieldID(SpielerNR)).childNodes;
	if (anz >= 1){
	    for(var i=0; i<nodes.length; i++) {
		    if (nodes[i].className == "Nachwuerfeln1") {
			nodes[i].src=circleximg;
		    }
	}
	}
	if (anz >= 2){
	    for(var i=0; i<nodes.length; i++) {
		    if (nodes[i].className== 'Nachwuerfeln2') {
			nodes[i].src=circleximg;
		    }
	}
	}
        if (anz >= 3){
            for(var i=0; i<nodes.length; i++) {
		    if (nodes[i].className == 'Nachwuerfeln3') {
			nodes[i].src=circleximg;
		    }
        }
        }
        if (anz >= 4){
            for(var i=0; i<nodes.length; i++) {
		    if (nodes[i].className== 'Nachwuerfeln4') {
			nodes[i].src=circleximg;
		    }
        }
        }
        if (anz >= 5){
            for(var i=0; i<nodes.length; i++) {
		    if (nodes[i].className== 'Nachwuerfeln5') {
			nodes[i].src=circleximg;
		    }
        }
        }
        if (anz >= 6){
            for(var i=0; i<nodes.length; i++) {
		    if (nodes[i].className== 'Nachwuerfeln6') {
			nodes[i].src=circleximg;
		    }
        }
        }
        if (anz >= 7){
            for(var i=0; i<nodes.length; i++) {
		    if (nodes[i].className== 'Nachwuerfeln7') {
			nodes[i].src=circleximg;
		    }
        }
    }
}
function setNachwuerfelKreis(SpielerNR, anz /*anzahlkreise*/){
    //TODO BUGFIX
	var nodes = document.getElementById(getFieldID(SpielerNR)).childNodes;
	if (anz >= 1){
	    for(var i=0; i<nodes.length; i++) {
		    if (nodes[i].className == "Nachwuerfeln1") {
			nodes[i].src=circleimg;
		    }
	}
	}
	if (anz >= 2){
	    for(var i=0; i<nodes.length; i++) {
		    if (nodes[i].className== 'Nachwuerfeln2') {
			nodes[i].src=circleimg;
		    }
	}
	}
        if (anz >= 3){
            for(var i=0; i<nodes.length; i++) {
		    if (nodes[i].className == 'Nachwuerfeln3') {
			nodes[i].src=circleimg;
		    }
        }
        }
        if (anz >= 4){
            for(var i=0; i<nodes.length; i++) {
		    if (nodes[i].className== 'Nachwuerfeln4') {
			nodes[i].src=circleimg;
		    }
        }
        }
        if (anz >= 5){
            for(var i=0; i<nodes.length; i++) {
		    if (nodes[i].className== 'Nachwuerfeln5') {
			nodes[i].src=circleimg;
		    }
        }
        }
        if (anz >= 6){
            for(var i=0; i<nodes.length; i++) {
		    if (nodes[i].className== 'Nachwuerfeln6') {
			nodes[i].src=circleimg;
		    }
        }
        }
        if (anz >= 7){
            for(var i=0; i<nodes.length; i++) {
		    if (nodes[i].className== 'Nachwuerfeln7') {
			nodes[i].src=circleimg;
		    }
        }
    }
}

function setZusatzwuerfelX(SpielerNR, anz /*anzahlkreise*/){
    //TODO BUGFIX
	var nodes = document.getElementById(getFieldID(SpielerNR)).childNodes;
	if (anz >= 1){
	    for(var i=0; i<nodes.length; i++) {
		    if (nodes[i].className == "Zusatzwuerfeln1") {
			nodes[i].src=circleximg;
		    }
	}
	}
	if (anz >= 2){
	    for(var i=0; i<nodes.length; i++) {
		    if (nodes[i].className== 'Zusatzwuerfeln2') {
			nodes[i].src=circleximg;
		    }
	}
	}
        if (anz >= 3){
            for(var i=0; i<nodes.length; i++) {
		    if (nodes[i].className == 'Zusatzwuerfeln3') {
			nodes[i].src=circleximg;
		    }
        }
        }
        if (anz >= 4){
            for(var i=0; i<nodes.length; i++) {
		    if (nodes[i].className== 'Zusatzwuerfeln4') {
			nodes[i].src=circleximg;
		    }
        }
        }
        if (anz >= 5){
            for(var i=0; i<nodes.length; i++) {
		    if (nodes[i].className== 'Zusatzwuerfeln5') {
			nodes[i].src=circleximg;
		    }
        }
        }
        if (anz >= 6){
            for(var i=0; i<nodes.length; i++) {
		    if (nodes[i].className== 'Zusatzwuerfeln6') {
			nodes[i].src=circleximg;
		    }
        }
        }
        if (anz >= 7){
            for(var i=0; i<nodes.length; i++) {
		    if (nodes[i].className== 'Zusatzwuerfeln7') {
			nodes[i].src=circleximg;
		    }
        }
    }
}
function setZusatzwuerfelKreis(SpielerNR, anz /*anzahlkreise*/){
    //TODO BUGFIX
	var nodes = document.getElementById(getFieldID(SpielerNR)).childNodes;
	if (anz >= 1){
	    for(var i=0; i<nodes.length; i++) {
		    if (nodes[i].className == "Zusatzwuerfeln1") {
			nodes[i].src=circleimg;
		    }
	}
	}
	if (anz >= 2){
	    for(var i=0; i<nodes.length; i++) {
		    if (nodes[i].className== 'Zusatzwuerfeln2') {
			nodes[i].src=circleimg;
		    }
	}
	}
        if (anz >= 3){
            for(var i=0; i<nodes.length; i++) {
		    if (nodes[i].className == 'Zusatzwuerfeln3') {
			nodes[i].src=circleimg;
		    }
        }
        }
        if (anz >= 4){
            for(var i=0; i<nodes.length; i++) {
		    if (nodes[i].className== 'Zusatzwuerfeln4') {
			nodes[i].src=circleimg;
		    }
        }
        }
        if (anz >= 5){
            for(var i=0; i<nodes.length; i++) {
		    if (nodes[i].className== 'Zusatzwuerfeln5') {
			nodes[i].src=circleimg;
		    }
        }
        }
        if (anz >= 6){
            for(var i=0; i<nodes.length; i++) {
		    if (nodes[i].className== 'Zusatzwuerfeln6') {
			nodes[i].src=circleimg;
		    }
        }
        }
        if (anz >= 7){
            for(var i=0; i<nodes.length; i++) {
		    if (nodes[i].className== 'Zusatzwuerfeln7') {
			nodes[i].src=circleimg;
		    }
        }
    }
}

//TEMPLATE
function f(SpielerNR, x){
    switch (x){
    case 1:{
	break;
    }
    case 2:{
	break;
    }
    case 3:{
	break;
    }
    case 4:{
	break;
    }
    }
}

function extractArray(x) {
    // erst die Silberplatte loeschen
    clearTray();
    var limit = 345;
    switch (PlayerAmount) {
    case 2: {
	limit = 180;
	break;
    }
    case 3: {
	limit = 263
	break;
    }
    }
    for (var i = 0; i < limit; i++) {
	switch (i) {
	case 0: {
	    setRound(x[i]);
	    if(x[i]!=0){
		showview(x[i]);
	    }
	    break;
	}
	case 1: {
	    currentplayer = x[i];
	    break;
	}
	case 2: {
	    BlauWert= x[i];
	    break;
	}
	case 3: {
	    GelbWert= x[i]+6;
	    break;
	}
	case 4: {
	    OrangeWert= x[i]+24;
	    break;
	}
	case 5: {
	    LilaWert= x[i]+18;
	    break;
	}
	case 6: {
	    GreenWert= x[i]+12;
	    break;
	}
	case 7: {
	    WeissWert= x[i]+30;
	    break;
	}
	case 8: {
	    setDiceOnField(1,x[i]);
	    break;
	}
	case 9: {
	    setDiceOnField(2,x[i]);
	    break;
	}
	case 10: {
	    setDiceOnField(3,x[i]);
	    break;
	}
	case 11: {
	    if(x[i]==1) setDiceOnTray(BlauWert);
	    break;
	}
	case 12: {
	    if(x[i]==1) setDiceOnTray(GelbWert);
	    break;
	}
	case 13: {
	    if(x[i]==1) setDiceOnTray(OrangeWert);
	    break;
	}
	case 14: {
	    if(x[i]==1) setDiceOnTray(LilaWert);
	    break;
	}
	case 15: {
	    if(x[i]==1) setDiceOnTray(GreenWert);
	    break;
	}
	case 16: {
	    if(x[i]==1) setDiceOnTray(WeissWert);
	    break;
	}
	// START SPIELER1 DATA
	case 17:{
	    setNachwuerfelnKreis(1, x[i]);
	    setNachwuerfelnKreis(2,x[99])
	    break;
	}
	case 18:{
		setNachwuerfelnX(1, x[i]);
	    break;
	}
	case 19:{
		setZusatzwuerfelnKreis(1, x[i]);
	    break;
	}
	case 20:{
		setZusatzwuerfelnX(1, x[i]);
	    break;
	}
	case 21:{
		if(x[i]==1){
			setYellowX(1, 0);
		}
	    break;
	}
	case 22:{
		if(x[i]==1){
			setYellowX(1, 1);
		}
	    break;
	}
	//TODO 22-31
	case 32:{
		if(x[i]==1){
			setYellowX(1, 11);
		}
	    break;
	}
	case 33:{
		if(x[i]==1){
			setBlueX(1, 0);
		}
	    break;
	}
	case 34:{
		if(x[i]==1){
			setBlueX(1, 1);
		}
	    break;
	}
	//TODO 35-42
	case 43:{
		if(x[i]==1){
			setBlueX(1, 10);
		}
	    break;
	}
	case 44:{
		setGreenX(1, x[i]);
	    break;
	}
	}
    }
}


function closeLobby(){
	
	 document.getElementById("Lobby").style.visibility ="hidden";
	 document.getElementById("spielfeld").style.visibility="visible"


}