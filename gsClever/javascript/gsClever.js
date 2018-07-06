addListener('USERJOINED',function (event) {
    var stringFromServer = event.data;
    // username*4
    var temp=stringFromServer.split(",");
    // alert(stringFromServer);
    var table='<tr><th class="tg-s6z2">Spielernummer</th><th class="tg-baqh">Name</th><th class="tg-baqh">Status</th></tr>';
    for(var i=0; i<temp.length;i++){
        spieler[i]=temp[i];
        table+=('<tr><th class="tg-s6z2">'+(i+1)+'</th><th class="tg-baqh">'+temp[i]+'</th><th class="tg-baqh">beigetreten</th></tr>');
    }
    $("#tableBody").html(table); 

});
addListener('NEWGAME',function (event) {
    // INPUT: Spielerzahl, UserNumber
    var stringFromServer = event.data;
    var arr = stringFromServer.split(',');
    console.log(arr.length);
    console.log(+arr[0]);
    console.log(+arr[1]);
    console.log(typeof(+arr[0]));
    console.log(typeof(+arr[1]));
    if (arr.length == 2) {
	playerAmount=+arr[0];
	setupGameByPlayerAmount();
	showview(2);
	userNumber=+arr[1];
    }
    document.getElementById("Lobby").style.visibility ="hidden";
    document.getElementById("spielfeld").style.visibility="visible";

});
addListener('STARTARRAY',function (event) {
    var stringFromServer = event.data;
    var arr = stringFromServer.split(',');
    console.log(arr.length);
    console.log(+arr[0]);
    console.log(+arr[1]);
    console.log(typeof(+arr[0]));
    console.log(typeof(+arr[1]));
    if (arr.length == 345) {
	extractArray(arr);
    }
});
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
    document.getElementById("label").innerHTML = "Ein Spieler hat das Spiel verlassen";
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

var spieler=new Array(4);
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


function setupGameByPlayerAmount(){
    switch(playerAmount){
    case 2:{
	document.getElementById("buttonSpieler3").style.visibility = "hidden";
	document.getElementById("buttonSpieler4").style.visibility = "hidden";
	break;
    }
    case 3:{
	document.getElementById("buttonSpieler4").style.visibility = "hidden";
	break;
    }
    case 4:{
	
	break;
    }
    }
}

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
   clearTray();

    for (var i = 0; i < 6; i++) {
	setDiceImg(x[i]);
    }
}

function clearTray() {
    // Hier wird das Silberfeld zurueckgesetzt wenn die neuen Wuerfel geladen
    // werden
    document.getElementById("wuerfelfeldsilber1").src = "#";
    document.getElementById("wuerfelfeldsilber2").src = "#";
    document.getElementById("wuerfelfeldsilber3").src = "#";
    document.getElementById("wuerfelfeldsilber4").src = "#";
    document.getElementById("wuerfelfeldsilber5").src = "#";
    document.getElementById("wuerfelfeldsilber6").src = "#";
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

function setDiceOnField(x) {
    if (x > 0 && x <= 6) {
	document.getElementById("wuerfelfeldblue").src = getDiceImg(x);
    }
    if (x > 6 && x <= 12) {
	document.getElementById("wuerfelfeldyellow").src = getDiceImg(x);
    }
    if (x > 12 && x <= 18) {
	document.getElementById("wuerfelfeldgreen").src = getDiceImg(x);
    }
    if (x > 18 && x <= 24) {
	document.getElementById("wuerfelfeldpurple").src = getDiceImg(x);
    }
    if (x > 24 && x <= 30) {
	document.getElementById("wuerfelfeldorange").src = getDiceImg(x);
    }
    if (x > 30 && x <= 36) {
	document.getElementById("wuerfelfeldwhite").src = getDiceImg(x);
    }
}
function clearWuerfelfeld () {
    // Hier wird das Silberfeld zurueckgesetzt wenn die neuen Wuerfel geladen
    // werden
    document.getElementById("wuerfelfeldwhite").src = "#";
    document.getElementById("wuerfelfeldyellow").src = "#";
    document.getElementById("wuerfelfeldblue").src = "#";
    document.getElementById("wuerfelfeldorange").src = "#";
    document.getElementById("wuerfelfeldgreen").src = "#";
    document.getElementById("wuerfelfeldpurple").src = "#";
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
			    case 3: return "spieler2";
			    case 4: return "spieler2";
			    }
			    break;
			}
			case 3: {
			    switch(userNumber){
			    case 2: return "spieler3";
			    case 1: return "spieler3";
			    case 4: return "spieler3";
			    }
			    break;
			}
			case 4: {
			    switch(userNumber){
			    case 2: return "spieler4";
			    case 3: return "spieler4";
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


function setNachwuerfelnX(SpielerNR, anz /* anzahlkreise */){
    // TODO BUGFIX
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
function setNachwuerfelnKreis(SpielerNR, anz /* anzahlkreise */){
    // TODO BUGFIX
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

function setZusatzwuerfelnX(SpielerNR, anz /* anzahlkreise */){
    // TODO BUGFIX
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
function setZusatzwuerfelnKreis(SpielerNR, anz /* anzahlkreise */){
    // TODO BUGFIX
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

// TEMPLATE
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

function getYellowFieldByID(x){
    return "yellowfield"+(x+1);
}
function getBlueFieldByID(x){
    return "bluefield"+(x+2);
}
function getGreenFieldByID(x){
    return "greenfield"+(x+1);
}
function getOrangeFieldByID(x){
    return "orangefield"+(x+1);
}
function getPurpleFieldByID(x){
    return "purplefield"+(x+1);
}

function setYellowX(SpielerNR, x){
    var nodes = document.getElementById(getFieldID(SpielerNR)).childNodes;
    for(var i=0; i<nodes.length; i++) {
	    if (nodes[i].className == getYellowFieldByID(x)) {
		nodes[i].src=ximg;
	    }
    }	
}
function setBlueX(SpielerNR, x){
    var nodes = document.getElementById(getFieldID(SpielerNR)).childNodes;
    for(var i=0; i<nodes.length; i++) {
	    if (nodes[i].className == getBlueFieldByID(x)) {
		nodes[i].src=ximg;
	    }
    }	
}
function setGreenX(SpielerNR, x){
    var nodes = document.getElementById(getFieldID(SpielerNR)).childNodes;
        for(var j=0; j<x;j++){
        	for(var i=0; i<nodes.length; i++) {
        	    if (nodes[i].className == getGreenFieldByID(j)) {
        		nodes[i].src=ximg;
        	    }
            }	
        }
}
// Spieler1 Feld:0 Wert von: x[i]
function setOrange(SpielerNR, field, value){
    if(value){
        var nodes = document.getElementById(getFieldID(SpielerNR)).childNodes;
        for(var i=0; i<nodes.length; i++) {
    	    if (nodes[i].className == getOrangeFieldByID(field)) {
    		nodes[i].src=getNumberImg(value);
    	    }
        }	
    }
}
function setPurple(SpielerNR, field, value){
    if(value){
        var nodes = document.getElementById(getFieldID(SpielerNR)).childNodes;
        for(var i=0; i<nodes.length; i++) {
    	    if (nodes[i].className == getPurpleFieldByID(field)) {
    		nodes[i].src=getNumberImg(value);
    	    }
        }	
    }
}
function setNachwuerfelnClickable(x){
    if(x==1){
	document.getElementById("buttonNachwurf").setAttribute('onclick','Clicked("NACHWUERFELN")()');
    }else{
	document.getElementById("buttonNachwurf").setAttribute('onclick','');
    }
}
function setZusatzwuerfelnClickable(x){
    if(x==1){
	document.getElementById("buttonZusatzWuerfel").setAttribute('onclick','Clicked("ZUSATZWUERFELN")()');
    }else{
	document.getElementById("buttonZusatzWuerfel").setAttribute('onclick','');
    }
}
function setWuerfelnClickable(x){
    if(x==1){
	document.getElementById("buttonwurf").setAttribute('onclick','Clicked("WUERFELN")()');
    }else{
	document.getElementById("buttonwurf").setAttribute('onclick','');
    }
}
function setColorDecider(x){
    if(x){
	document.getElementById("wuerfelfeldEntscheider1").setAttribute('onclick','Clicked("COLORDECIDER")');
    }else{
	document.getElementById("wuerfelfeldEntscheider1").setAttribute('onclick','');
    }
}
function setWhiteDecider(x){
    if(x){
	document.getElementById("wuerfelfeldEntscheider2").src=getDiceImg(WeissWert);
	document.getElementById("wuerfelfeldEntscheider2").setAttribute('onclick','Clicked("WHITEDECIDER")');
    }else{
	document.getElementById("wuerfelfeldEntscheider2").src=getDiceImg("#");
	document.getElementById("wuerfelfeldEntscheider2").setAttribute('onclick','');
    }
}
function setColorDeciderColor(x){
    switch (x){
    case 0:{
	document.getElementById("wuerfelfeldEntscheider1").src="#";
	break;
    }
    case 1:{
	document.getElementById("wuerfelfeldEntscheider1").src=getDiceImg(BlauWert);
    	break;
        }
    case 2:{
	document.getElementById("wuerfelfeldEntscheider1").src=getDiceImg(GelbWert);
    	break;
    }
    case 3:{
	document.getElementById("wuerfelfeldEntscheider1").src=getDiceImg(OrangeWert);
    	break;
    }
    case 4:{
	document.getElementById("wuerfelfeldEntscheider1").src=getDiceImg(LilaWert);
    	break;
    }
    case 5:{
	document.getElementById("wuerfelfeldEntscheider1").src=getDiceImg(GreenWert);
    	break;
    }
    }
}
function setYellowClickable(SpielerNR, FieldID, value){
    if(userNumber==SpielerNR){
	var nodes = document.getElementById("spieler1").childNodes;
	var fieldName=getYellowFieldByID(FieldID);
	switch (FieldID){
    	case 0:{
    	for(var i=0; i<nodes.length; i++) {
    	    if (nodes[i].className == fieldName) {
    		if(value==1){
    		    nodes[i].setAttribute('onclick','Clicked("CLICKYELLOW0")');
    		} else{
    		    nodes[i].setAttribute('onclick','');
    		}
    	    }}
    	    break;
    	}
    	case 1:{
        	for(var i=0; i<nodes.length; i++) {
        	    if (nodes[i].className == fieldName) {
        		if(value==1){
        		    nodes[i].setAttribute('onclick','Clicked("CLICKYELLOW1")');
        		} else{
        		    nodes[i].setAttribute('onclick','');
        		}
        	    }}
        	    break;
        	}
    	case 2:{
        	for(var i=0; i<nodes.length; i++) {
        	    if (nodes[i].className == fieldName) {
        		if(value==1){
        		    nodes[i].setAttribute('onclick','Clicked("CLICKYELLOW2")');
        		} else{
        		    nodes[i].setAttribute('onclick','');
        		}
        	    }}
        	    break;
        	}
    	case 3:{
        	for(var i=0; i<nodes.length; i++) {
        	    if (nodes[i].className == fieldName) {
        		if(value==1){
        		    nodes[i].setAttribute('onclick','Clicked("CLICKYELLOW3")');
        		} else{
        		    nodes[i].setAttribute('onclick','');
        		}
        	    }}
        	    break;
        	}
    	case 4:{
        	for(var i=0; i<nodes.length; i++) {
        	    if (nodes[i].className == fieldName) {
        		if(value==1){
        		    nodes[i].setAttribute('onclick','Clicked("CLICKYELLOW4")');
        		} else{
        		    nodes[i].setAttribute('onclick','');
        		}
        	    }}
        	    break;
        	}
    	case 5:{
        	for(var i=0; i<nodes.length; i++) {
        	    if (nodes[i].className == fieldName) {
        		if(value==1){
        		    nodes[i].setAttribute('onclick','Clicked("CLICKYELLOW5")');
        		} else{
        		    nodes[i].setAttribute('onclick','');
        		}
        	    }}
        	    break;
        	}
    	case 6:{
        	for(var i=0; i<nodes.length; i++) {
        	    if (nodes[i].className == fieldName) {
        		if(value==1){
        		    nodes[i].setAttribute('onclick','Clicked("CLICKYELLOW6")');
        		} else{
        		    nodes[i].setAttribute('onclick','');
        		}
        	    }}
        	    break;
        	}
    	case 7:{
        	for(var i=0; i<nodes.length; i++) {
        	    if (nodes[i].className == fieldName) {
        		if(value==1){
        		    nodes[i].setAttribute('onclick','Clicked("CLICKYELLOW7")');
        		} else{
        		    nodes[i].setAttribute('onclick','');
        		}
        	    }}
        	    break;
        	}
    	case 8:{
        	for(var i=0; i<nodes.length; i++) {
        	    if (nodes[i].className == fieldName) {
        		if(value==1){
        		    nodes[i].setAttribute('onclick','Clicked("CLICKYELLOW8")');
        		} else{
        		    nodes[i].setAttribute('onclick','');
        		}
        	    }}
        	    break;
        	}
    	case 9:{
        	for(var i=0; i<nodes.length; i++) {
        	    if (nodes[i].className == fieldName) {
        		if(value==1){
        		    nodes[i].setAttribute('onclick','Clicked("CLICKYELLOW9")');
        		} else{
        		    nodes[i].setAttribute('onclick','');
        		}
        	    }}
        	    break;
        	}
    	case 10:{
        	for(var i=0; i<nodes.length; i++) {
        	    if (nodes[i].className == fieldName) {
        		if(value==1){
        		    nodes[i].setAttribute('onclick','Clicked("CLICKYELLOW10")');
        		} else{
        		    nodes[i].setAttribute('onclick','');
        		}
        	    }}
        	    break;
        	}
    	case 11:{
    	for(var i=0; i<nodes.length; i++) {
    	    if (nodes[i].className == fieldName) {
    		if(value==1){
    		    nodes[i].setAttribute('onclick','Clicked("CLICKYELLOW11")');
    		} else{
    		    nodes[i].setAttribute('onclick','');
    		}
    	    }}
    	    break;
    	}
	}
}
}
function Clicked(Data){
    sendDataToServer(Data);
}
function setBlueClickable(SpielerNR, FieldID, value){
    if(userNumber==SpielerNR){
	var nodes = document.getElementById("spieler1").childNodes;
	var fieldName=getBlueFieldByID(FieldID);
	switch (FieldID){
    	case 0:{
    	for(var i=0; i<nodes.length; i++) {
    	    if (nodes[i].className == fieldName) {
    		if(value==1){
    		    nodes[i].setAttribute('onclick','Clicked("CLICKBLUE0")');
    		} else{
    		    nodes[i].setAttribute('onclick','');
    		}
    	    }}
    	    break;
    	}
    	case 1:{
        	for(var i=0; i<nodes.length; i++) {
        	    if (nodes[i].className == fieldName) {
        		if(value==1){
        		    nodes[i].setAttribute('onclick','Clicked("CLICKBLUE1")');
        		} else{
        		    nodes[i].setAttribute('onclick','');
        		}
        	    }}
        	    break;
        	}
    	case 2:{
        	for(var i=0; i<nodes.length; i++) {
        	    if (nodes[i].className == fieldName) {
        		if(value==1){
        		    nodes[i].setAttribute('onclick','Clicked("CLICKBLUE2")');
        		} else{
        		    nodes[i].setAttribute('onclick','');
        		}
        	    }}
        	    break;
        	}
    	case 3:{
        	for(var i=0; i<nodes.length; i++) {
        	    if (nodes[i].className == fieldName) {
        		if(value==1){
        		    nodes[i].setAttribute('onclick','Clicked("CLICKBLUE3")');
        		} else{
        		    nodes[i].setAttribute('onclick','');
        		}
        	    }}
        	    break;
        	}
    	case 4:{
        	for(var i=0; i<nodes.length; i++) {
        	    if (nodes[i].className == fieldName) {
        		if(value==1){
        		    nodes[i].setAttribute('onclick','Clicked("CLICKBLUE4")');
        		} else{
        		    nodes[i].setAttribute('onclick','');
        		}
        	    }}
        	    break;
        	}
    	case 5:{
        	for(var i=0; i<nodes.length; i++) {
        	    if (nodes[i].className == fieldName) {
        		if(value==1){
        		    nodes[i].setAttribute('onclick','Clicked("CLICKBLUE5")');
        		} else{
        		    nodes[i].setAttribute('onclick','');
        		}
        	    }}
        	    break;
        	}
    	case 6:{
        	for(var i=0; i<nodes.length; i++) {
        	    if (nodes[i].className == fieldName) {
        		if(value==1){
        		    nodes[i].setAttribute('onclick','Clicked("CLICKBLUE6")');
        		} else{
        		    nodes[i].setAttribute('onclick','');
        		}
        	    }}
        	    break;
        	}
    	case 7:{
        	for(var i=0; i<nodes.length; i++) {
        	    if (nodes[i].className == fieldName) {
        		if(value==1){
        		    nodes[i].setAttribute('onclick','Clicked("CLICKBLUE7")');
        		} else{
        		    nodes[i].setAttribute('onclick','');
        		}
        	    }}
        	    break;
        	}
    	case 8:{
        	for(var i=0; i<nodes.length; i++) {
        	    if (nodes[i].className == fieldName) {
        		if(value==1){
        		    nodes[i].setAttribute('onclick','Clicked("CLICKBLUE8")');
        		} else{
        		    nodes[i].setAttribute('onclick','');
        		}
        	    }}
        	    break;
        	}
    	case 9:{
        	for(var i=0; i<nodes.length; i++) {
        	    if (nodes[i].className == fieldName) {
        		if(value==1){
        		    nodes[i].setAttribute('onclick','Clicked("CLICKBLUE9")');
        		} else{
        		    nodes[i].setAttribute('onclick','');
        		}
        	    }}
        	    break;
        	}
    	case 10:{
        	for(var i=0; i<nodes.length; i++) {
        	    if (nodes[i].className == fieldName) {
        		if(value==1){
        		    nodes[i].setAttribute('onclick','Clicked("CLICKBLUE10")');
        		} else{
        		    nodes[i].setAttribute('onclick','');
        		}
        	    }}
        	    break;
        	}
	}
}
}
function setGreenClickable(SpielerNR, value){
    if(userNumber==SpielerNR){
	var nodes = document.getElementById("spieler1").childNodes;
	for(var i=0; i<nodes.length; i++) {
	    if (nodes[i].className == "greenOnClick") {
		if(value==1){
		    nodes[i].setAttribute('onclick','Clicked("CLICKGREEN")');
		} else{
		    nodes[i].setAttribute('onclick','');
		}
	    }
	}
    }
}
function setOrangeClickable(SpielerNR, value){
    if(userNumber==SpielerNR){
	var nodes = document.getElementById("spieler1").childNodes;
	for(var i=0; i<nodes.length; i++) {
	    if (nodes[i].className == "orangeOnClick") {
		if(value==1){
		    nodes[i].setAttribute('onclick','Clicked("CLICKORANGE")');
		} else{
		    nodes[i].setAttribute('onclick','');
		}
	    }
	}
    }
}
function setPurpleClickable(SpielerNR, value){
    if(userNumber==SpielerNR){
	var nodes = document.getElementById("spieler1").childNodes;
	for(var i=0; i<nodes.length; i++) {
	    if (nodes[i].className == "purpleOnClick") {
		if(value==1){
		    nodes[i].setAttribute('onclick','Clicked("CLICKPURPLE")');
		} else{
		    nodes[i].setAttribute('onclick','');
		}
	    }
	}
    }
}
function extractArray(x) {
    // erst die Silberplatte loeschen
    clearTray();
    var limit = 345;
    switch (playerAmount) {
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
	    setRound(+x[i]);
	    if(+x[i]!=0){
		showview(+x[i]);
	    }
	    break;
	}
	case 1: {
	    currentPlayer = +x[i];
	    break;
	}
	case 2: {
	    BlauWert= +x[i];
	    break;
	}
	case 3: {
	    GelbWert= +x[i]+6;
	    break;
	}
	case 4: {
	    OrangeWert= +x[i]+24;
	    break;
	}
	case 5: {
	    LilaWert= +x[i]+18;
	    break;
	}
	case 6: {
	    GreenWert= +x[i]+12;
	    break;
	}
	case 7: {
	    WeissWert= +x[i]+30;
	    break;
	}
	case 8: {
	    setDiceOnField(1,+x[i]);
	    break;
	}
	case 9: {
	    setDiceOnField(2,+x[i]);
	    break;
	}
	case 10: {
	    setDiceOnField(3,+x[i]);
	    break;
	}
	case 11: {
	    if(+x[i]==1) setDiceOnTray(BlauWert);
	    break;
	}
	case 12: {
	    if(+x[i]==1) setDiceOnTray(GelbWert);
	    break;
	}
	case 13: {
	    if(+x[i]==1) setDiceOnTray(OrangeWert);
	    break;
	}
	case 14: {
	    if(+x[i]==1) setDiceOnTray(LilaWert);
	    break;
	}
	case 15: {
	    if(+x[i]==1) setDiceOnTray(GreenWert);
	    break;
	}
	case 16: {
	    if(+x[i]==1) setDiceOnTray(WeissWert);
	    break;
	}
	// START SPIELER1 DATA
	case 17:{
	    setNachwuerfelnKreis(1, +x[i]);
	    break;
	}
	case 18:{
		setNachwuerfelnX(1, +x[i]);
	    break;
	}
	case 19:{
		setZusatzwuerfelnKreis(1, +x[i]);
	    break;
	}
	case 20:{
		setZusatzwuerfelnX(1, +x[i]);
	    break;
	}
	case 21:{
		if(+x[i]==1){
			setYellowX(1, 0);
		}
	    break;
	}
	case 22:{
		if(+x[i]==1){
			setYellowX(1, 1);
		}
	    break;
	}
	
	case 23:{
		if(+x[i]==1){
			setYellowX(1, 2);
		}
	    break;
	}
	case 24:{
		if(+x[i]==1){
			setYellowX(1, 3);
		}
	    break;
	}
	case 25:{
		if(+x[i]==1){
			setYellowX(1, 4);
		}
	    break;
	}
	case 26:{
		if(+x[i]==1){
			setYellowX(1, 5);
		}
	    break;
	}
	case 27:{
		if(+x[i]==1){
			setYellowX(1, 6);
		}
	    break;
	}
	case 28:{
		if(+x[i]==1){
			setYellowX(1, 7);
		}
	    break;
	}
	case 29:{
		if(+x[i]==1){
			setYellowX(1, 8);
		}
	    break;
	}
	case 30:{
		if(+x[i]==1){
			setYellowX(1, 9);
		}
	    break;
	}
	case 31:{
		if(+x[i]==1){
			setYellowX(1, 10);
		}
	    break;
	}
	case 32:{
		if(+x[i]==1){
			setYellowX(1, 11);
		}
	    break;
	}
	case 33:{
		if(+x[i]==1){
			setBlueX(1, 0);
		}
	    break;
	}
	case 34:{
		if(+x[i]==1){
			setBlueX(1, 1);
		}
	    break;
	}
	case 35:{
		if(+x[i]==1){
			setBlueX(1, 2);
		}
	    break;
	}
	case 36:{
		if(+x[i]==1){
			setBlueX(1, 3);
		}
	    break;
	}
	case 37:{
		if(+x[i]==1){
			setBlueX(1, 4);
		}
	    break;
	}
	case 38:{
		if(+x[i]==1){
			setBlueX(1, 5);
		}
	    break;
	}
	case 39:{
		if(+x[i]==1){
			setBlueX(1, 6);
		}
	    break;
	}
	case 40:{
		if(+x[i]==1){
			setBlueX(1, 7);
		}
	    break;
	}
	case 41:{
		if(+x[i]==1){
			setBlueX(1, 8);
		}
	    break;
	}
	case 42:{
		if(+x[i]==1){
			setBlueX(1, 9);
		}
	    break;
	}
	
	case 43:{
		if(+x[i]==1){
			setBlueX(1, 10);
		}
	    break;
	}
	case 44:{
		setGreenX(1, +x[i]);
	    break;
	}
	case 45:{
	       	// Spieler1 Feld:0 Wert von: +x[i]
	    	setOrange(1,0, +x[i]);
	    break;
	}
	 case 46:{
	    	setOrange(1,1, +x[i]);
	 break;
	    }
	 case 47:{
	    	setOrange(1,2, +x[i]);
	 break;
	    }
	 case 48:{
	    	setOrange(1,3, +x[i]);
	 break;
	    }
	 case 49:{
	    	setOrange(1,4, +x[i]);
	 break;
	    }
	 case 50:{
	    	setOrange(1,5, +x[i]);
	 break;
	    }
	 case 51:{
	    	setOrange(1,6, +x[i]);
	 break;
	    }
	 case 52:{
	    	setOrange(1,7, +x[i]);
	 break;
	    } 
	 case 53:{
	    	setOrange(1,8, +x[i]);
	 break;
	    }
	 case 54:{
	    	setOrange(1,9, +x[i]);
	 break;
	    }
	 case 55:{
	    	setOrange(1,10, +x[i]);
	}
	case 56:{
	    	setPurple(1,0, +x[i]);
	break;
	   }
	case 57:{
	    	setPurple(1,1, +x[i]);
	break;
	    	}
	
	case 58:{
    	setPurple(1,2, +x[i]);
break;
    	}
	case 59:{
    	setPurple(1,3, +x[i]);
break;
    	}
	case 60:{
    	setPurple(1,4, +x[i]);
break;
    	}
	case 61:{
    	setPurple(1,5, +x[i]);
break;
    	}
	case 62:{
    	setPurple(1,6, +x[i]);
break;
    	}
	case 63:{
    	setPurple(1,7, +x[i]);
break;
    	}
	case 64:{
    	setPurple(1,8, +x[i]);
break;
    	}
	case 65:{
    	setPurple(1,9, +x[i]);
break;
    	}
	
	case 66:{
		setPurple(1,10, +x[i]);
	}
	case 67:{
	    if(userNumber==1){
		setNachwuerfelnClickable(+x[i]);
	    }
	    break;
	    }
	case 68:{
	    if(userNumber==1){
		setZusatzwuerfelnClickable(+x[i]);
	    }
	    break;
	    }
	case 69:{
	    if(userNumber==1){
		setWuerfelnClickable(+x[i]);
	    }
	    break;
	    }
	case 70:{
	    if(userNumber==1){
		setColorDecider(+x[i]);
	    }
	    break;
	    }
	case 71:{
	    if(userNumber==1){
		setWhiteDecider(+x[i]);
	    }
	    break;
	    }
	case 72:{
	    if(userNumber==1){
		setColorDeciderColor(+x[i]);
	    }
	    break;
	    }
	case 73:{
		setYellowClickable(1, 0,+x[i]);
	    break;
	}
	case 74:{
	    	setYellowClickable(1, 1,+x[i]);
	    break;
	}
	case 75:{
		setYellowClickable(1, 2,+x[i]);
	    break;
	}
	case 76:{
		setYellowClickable(1, 3,+x[i]);
	    break;
	}
	case 77:{
		setYellowClickable(1, 4,+x[i]);
	    break;
	}
	case 78:{
		setYellowClickable(1, 5,+x[i]);
	    break;
	}
	case 79:{
		setYellowClickable(1, 6,+x[i]);
	    break;
	}
	case 80:{
		setYellowClickable(1, 7,+x[i]);
	    break;
	}
	case 81:{
		setYellowClickable(1, 8,+x[i]);
	    break;
	}
	case 82:{
		setYellowClickable(1, 9,+x[i]);
	    break;
	}
	case 83:{
		setYellowClickable(1, 10,+x[i]);
	    break;
	}
	case 84:{
		setYellowClickable(1, 11,+x[i]);
	    break;
	}
	case 85:{
		setBlueClickable(1, 0,+x[i]);
	    break;
	}
	case 86:{
		setBlueClickable(1, 1,+x[i]);
	    break;
	}
	case 87:{
		setBlueClickable(1, 2,+x[i]);
	    break;
	}
	case 88:{
		setBlueClickable(1, 3,+x[i]);
	    break;
	}
	case 89:{
		setBlueClickable(1, 4,+x[i]);
	    break;
	}
	case 90:{
		setBlueClickable(1, 5,+x[i]);
	    break;
	}
	case 91:{
		setBlueClickable(1, 6,+x[i]);
	    break;
	}
	case 92:{
		setBlueClickable(1, 7,+x[i]);
	    break;
	}
	case 93:{
	    setBlueClickable(1, 8,+x[i]);
	    break;
	}
	case 94:{
	    setBlueClickable(1, 9,+x[i]);
	    break;
	}
	
	case 95:{
	    setBlueClickable(1, 10,+x[i]);
	    break;
	}
	case 96:{
	    setGreenClickable(1, +x[i]);
	    break;
	}
	case 97:{
	    setOrangeClickable(1, +x[i]);
	    break;
	}
	case 98:{
	    setPurpleClickable(1, +x[i]);
	    break;
	}
	// ENDE SPIELER 1
	// START SPIELER2 DATA
	case 99:{
	    setNachwuerfelnKreis(2, +x[i]);
	    break;
	}
	case 100:{
		setNachwuerfelnX(2, +x[i]);
	    break;
	}
	case 101:{
		setZusatzwuerfelnKreis(2, +x[i]);
	    break;
	}
	case 102:{
		setZusatzwuerfelnX(2, +x[i]);
	    break;
	}
	case 103:{
		if(+x[i]==1){
			setYellowX(2, 0);
		}
	    break;
	}
	case 104:{
		if(+x[i]==1){
			setYellowX(2, 1);
		}
	    break;
	}
	
	case 105:{
		if(+x[i]==1){
			setYellowX(2, 2);
		}
	    break;
	}
	case 106:{
		if(+x[i]==1){
			setYellowX(2, 3);
		}
	    break;
	}
	case 107:{
		if(+x[i]==1){
			setYellowX(2, 4);
		}
	    break;
	}
	case 108:{
		if(+x[i]==1){
			setYellowX(2, 5);
		}
	    break;
	}
	case 109:{
		if(+x[i]==1){
			setYellowX(2, 6);
		}
	    break;
	}
	case 110:{
		if(+x[i]==1){
			setYellowX(2, 7);
		}
	    break;
	}
	case 111:{
		if(+x[i]==1){
			setYellowX(2, 8);
		}
	    break;
	}
	case 112:{
		if(+x[i]==1){
			setYellowX(2, 9);
		}
	    break;
	}
	case 113:{
		if(+x[i]==1){
			setYellowX(2, 10);
		}
	    break;
	}
	case 114:{
		if(+x[i]==1){
			setYellowX(2, 11);
		}
	    break;
	}
	case 115:{
		if(+x[i]==1){
			setBlueX(2, 0);
		}
	    break;
	}
	case 116:{
		if(+x[i]==1){
			setBlueX(2, 1);
		}
	    break;
	}
	case 117:{
		if(+x[i]==1){
			setBlueX(2, 2);
		}
	    break;
	}
	case 118:{
		if(+x[i]==1){
			setBlueX(2, 3);
		}
	    break;
	}
	case 119:{
		if(+x[i]==1){
			setBlueX(2, 4);
		}
	    break;
	}
	case 120:{
		if(+x[i]==1){
			setBlueX(2, 5);
		}
	    break;
	}
	case 121:{
		if(+x[i]==1){
			setBlueX(2, 6);
		}
	    break;
	}
	case 122:{
		if(+x[i]==1){
			setBlueX(2, 7);
		}
	    break;
	}
	case 123:{
		if(+x[i]==1){
			setBlueX(2, 8);
		}
	    break;
	}
	case 124:{
		if(+x[i]==1){
			setBlueX(2, 9);
		}
	    break;
	}
	
	case 125:{
		if(+x[i]==1){
			setBlueX(2, 10);
		}
	    break;
	}

	case 126:{
		setGreenX(2, +x[i]);
	    break;
	}
	case 127:{
    	setOrange(2,0, +x[i]);
    	break;
	}
	case 128:{
    	setOrange(2,1, +x[i]);
    	break;
	}
	case 129:{
    	setOrange(2,2, +x[i]);
    	break;
	}
	case 130:{
    	setOrange(2,3, +x[i]);
    	break;
	}
	
	case 131:{
    	setOrange(2,4, +x[i]);
    	break;
	}	
	case 132:{
    	setOrange(2,5, +x[i]);
    	break;
	}	
	case 133:{
    	setOrange(2,6, +x[i]);
    	break;
	}	
	case 134:{
    	setOrange(2,7, +x[i]);
    	break;
	}	
	case 135:{
    	setOrange(2,8, +x[i]);
    	break;
	}	
	case 136:{
    	setOrange(2,9, +x[i]);
    	break;
}	
	case 137:{
    	setOrange(2,10, +x[i]);
    	break;
	}	
	case 138:{
    	setPurple(2,0, +x[i]);
break;
    	}
	case 139:{
    	setPurple(2,1, +x[i]);
break;
    	}
	case 140:{
    	setPurple(2,2, +x[i]);
break;
    	}
	case 141:{
    	setPurple(2,3, +x[i]);
break;
    	}
	case 142:{
    	setPurple(2,4, +x[i]);
break;
    	}
	case 143:{
	    setPurple(2,5, +x[i]);
	    break;
	    	    	}
	case 144:{
	setPurple(2,6, +x[i]);
break;
	}
case 145:{
	setPurple(2,7, +x[i]);
break;
	}
case 146:{
	setPurple(2,8, +x[i]);
break;
	}
case 147:{
	setPurple(2,9, +x[i]);
break;
	}
case 148:{
	setPurple(2,10, +x[i]);
break;
	}
case 149:{
    if(userNumber==2){
	setNachwuerfelnClickable(+x[i]);
    }
    break;
    }
case 150:{
    if(userNumber==2){
	setZusatzwuerfelnClickable(+x[i]);
    }
    break;
    }
case 151:{
    if(userNumber==2){
	setWuerfelnClickable(+x[i]);
    }
    break;
    }
case 152:{
    if(userNumber==2){
	setColorDecider(+x[i]);
    }
    break;
    }
case 153:{
    if(userNumber==2){
	setWhiteDecider(+x[i]);
    }
    break;
    }
case 154:{
    if(userNumber==2){
	setColorDeciderColor(+x[i]);
    }
    break;
    }
case 155:{
	setYellowClickable(2, 0,+x[i]);
    break;
}
case 156:{
    setYellowClickable(2, 1,+x[i]);
    break;
}
case 157:{
	setYellowClickable(2, 2,+x[i]);
    break;
}
case 158:{
	setYellowClickable(2, 3,+x[i]);
    break;
}
case 159:{
	setYellowClickable(2, 4,+x[i]);
    break;
}
case 160:{
	setYellowClickable(2, 5,+x[i]);
    break;
}
case 161:{
	setYellowClickable(2, 6,+x[i]);
    break;
}
case 162:{
	setYellowClickable(2, 7,+x[i]);
    break;
}
case 163:{
	setYellowClickable(2, 8,+x[i]);
    break;
}
case 164:{
	setYellowClickable(2, 9,+x[i]);
    break;
}
case 165:{
	setYellowClickable(2, 10,+x[i]);
    break;
}
case 166:{
	setYellowClickable(2, 11,+x[i]);
    break;
}
case 167:{
	setBlueClickable(2, 0,+x[i]);
    break;
}
case 168:{
	setBlueClickable(2, 1,+x[i]);
    break;
}
case 169:{
	setBlueClickable(2, 2,+x[i]);
    break;
}
case 170:{
	setBlueClickable(2, 3,+x[i]);
    break;
}
case 171:{
	setBlueClickable(2, 4,+x[i]);
    break;
}
case 172:{
	setBlueClickable(2, 5,+x[i]);
    break;
}
case 173:{
	setBlueClickable(2, 6,+x[i]);
    break;
}
case 174:{
	setBlueClickable(2, 7,+x[i]);
    break;
}
case 175:{
    setBlueClickable(2, 8,+x[i]);
    break;
}
case 176:{
    setBlueClickable(2, 9,+x[i]);
    break;
}
case 177:{
    setBlueClickable(2, 10,+x[i]);
    break;
}
case 178:{
    setGreenClickable(2, +x[i]);
    break;
}
case 179:{
    setOrangeClickable(2, +x[i]);
    break;
}
case 180:{
    setPurpleClickable(2, +x[i]);
    break;
}
//ENDE SPIELER 2
// START SPIELER3 DATA








	}
    }
}

function closeLobby(){
    
    	sendDataToServer("STARTGAME");
	 //Das darf hier eigentlich erst passieren wenn>2 spieler da sind
	 


}