addListener('USERJOINED',function (event) {
    var stringFromServer = event.data;
    // username*4
    var temp=stringFromServer.split(",");
    namelist=temp;
    // alert(stringFromServer);
    var table='<tr><th class="tg-s6z2">Spielernummer</th><th class="tg-baqh">Name</th><th class="tg-baqh">Status</th></tr>';
    for(var i=0; i<temp.length;i++){
        spieler[i]=temp[i];
        table+=('<tr><th class="tg-s6z2">'+(i+1)+'</th><th class="tg-baqh">'+temp[i]+'</th><th class="tg-baqh">beigetreten</th></tr>');
    }
    $("#tableBody").html(table); 
    if(temp.length==4){
    	document.getElementById("kiButton").setAttribute('onclick','');
    }

});

addListener('WINNER',function (event) { 
	var stringFromServer = event.data;
    var arr = stringFromServer.split(',');
    console.log(arr);
    console.log(stringFromServer);
    var winnerString="";
    for (var i=0; i<arr.lenght;i++){
    	winnerString += arr[i] + " hat das Spiel gewonnen" ;
    }
    console.log(winnerString);
    document.getElementById("label").innerHTML = winnerString;;
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
    changeButtonNames();
});
addListener('STARTARRAY',function (event) {
    var stringFromServer = event.data;
    var arr = stringFromServer.split(',');
    console.log(arr.length);
    console.log(+arr[0]);
    console.log(+arr[1]);
    console.log(typeof(+arr[0]));
    console.log(typeof(+arr[1]));
    if (arr.length == 349) {
	extractArray(arr);
    }
});

addListener('PLAYERLEFT', function(event) {
    var stringFromServer = event.data;
    playerMessage = stringFromServer;
    document.getElementById("label").innerHTML = "Ein Spieler hat das Spiel verlassen";
});
addListener('SUBMITGAME', function(event) {
    var stringFromServer = event.data;
    var arr = stringFromServer.split(',');
    console.log(stringFromServer);
    console.log(arr);
    console.log("Wuerfeld"+arr[2]+","+arr[3]);
    console.log("Farbiger Entscheider"+arr[70]);
    for (var i = 0; i < 349; i++) {
	number[i] = +arr[i];
    }
    // var number = Number(stringFromServer);
    // Hier wird ein Array uebergeben:

    extractArray(arr);
});

var spieler=new Array(4);
var playerAmount = 2;
var userNumber=1;
// 1 = Host, 2 = Spieler2 usw...
var number = [ 0, 0, 0, 0, 0, 0 ];
playerMessage = "";
var BlauWert = 1;
var GelbWert = 1;
var OrangeWert = 1;
var LilaWert = 1;
var GreenWert = 1;
var WeissWert = 1;
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
var img10 ="/gsClever/Images/Numbers/10.png";
var img12 = "/gsClever/Images/Numbers/12.png";
var img15 = "/gsClever/Images/Numbers/15.png";
var img18 = "/gsClever/Images/Numbers/18.png";

var empty = "/gsClever/Images/Empty.png";
var ximg = "/gsClever/Images/X.png";
var circleimg = "/gsClever/Images/circle.png";
var circleximg = "/gsClever/Images/circlex.png";
var statusWait = true;
var namelist;

var arrFields = [ 0, 0, 0, 0, 0, 0, 0, 0, 0 ];
var sentFields = [ 0, 0, 0, 0, 0, 0, 0, 0, 0 ];
var currentPlayer = 1;

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
    case 10:
    return img10
    case 12:
	return img12
    case 15:
	return img15
    case 18:
	return img18
    }
}

function changeButtonNames(){
		switch(userNumber){
			case 1:{ 
				document.getElementById("buttonSpieler2").value=namelist[1];
				document.getElementById("buttonSpieler3").value=namelist[2];
				document.getElementById("buttonSpieler4").value=namelist[3];
		    	break;
		    }
		    case 2:{ 
		    	document.getElementById("buttonSpieler2").value=namelist[0];
				document.getElementById("buttonSpieler3").value=namelist[2];
				document.getElementById("buttonSpieler4").value=namelist[3];
		    	break;
		    }
		    case 3:{ 
		    	document.getElementById("buttonSpieler2").value=namelist[1];
				document.getElementById("buttonSpieler3").value=namelist[0];
				document.getElementById("buttonSpieler4").value=namelist[3];
		    	break;
		    }
		    case 4:{ 
		    	document.getElementById("buttonSpieler2").value=namelist[1];
				document.getElementById("buttonSpieler3").value=namelist[2];
				document.getElementById("buttonSpieler4").value=namelist[0];
		    	break;
		    }
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

function addKI(){
	sendDataToServer("ADDKI");
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

function clearField(){
	var nodes = document.getElementById(currentPlayerField()).childNodes;
	for(var i=0; i<nodes.length; i++) {
		if (nodes[i].id == 'Wuerfelfeld1') {
			nodes[i].src= empty;
		}
		if (nodes[i].id == 'Wuerfelfeld2') {
			nodes[i].src= empty;
		}
		if (nodes[i].id == 'Wuerfelfeld3') {
			nodes[i].src= empty;
		}
	}
}

function clearPlace(){
    document.getElementById("wuerfelfeldwhite").src = empty;
    document.getElementById("wuerfelfeldyellow").src = empty;
    document.getElementById("wuerfelfeldblue").src = empty;
    document.getElementById("wuerfelfeldorange").src = empty;
    document.getElementById("wuerfelfeldgreen").src = empty;
    document.getElementById("wuerfelfeldpurple").src = empty;
}

function clearTray() {
    // Hier wird das Silberfeld zurueckgesetzt wenn die neuen Wuerfel geladen
    // werden
    document.getElementById("wuerfelfeldsilber1").src = empty;
    document.getElementById("wuerfelfeldsilber2").src = empty;
    document.getElementById("wuerfelfeldsilber3").src = empty;
    document.getElementById("wuerfelfeldsilber4").src = empty;
    document.getElementById("wuerfelfeldsilber5").src = empty;
    document.getElementById("wuerfelfeldsilber6").src = empty;
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

function setDiceOnPlace(x) {
    if (x==0){
    }
    if (x==1){
	document.getElementById("wuerfelfeldblue").src = getDiceImg(GelbWert);
    }
    if (x==2) {
	document.getElementById("wuerfelfeldyellow").src = getDiceImg(BlauWert);
    }
    if (x==3) {
	document.getElementById("wuerfelfeldgreen").src = getDiceImg(GreenWert);
    }
    if (x==4) {
	document.getElementById("wuerfelfeldpurple").src = getDiceImg(OrangeWert);
    }
    if (x==5) {
	document.getElementById("wuerfelfeldorange").src = getDiceImg(LilaWert);
    }
    if (x==6) {
	document.getElementById("wuerfelfeldwhite").src = getDiceImg(WeissWert);
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

function setDiceOnField(fieldNR, dice){
     var nodes = document.getElementById(currentPlayerField()).childNodes;
     switch (fieldNR){
     case 1: {
 	for(var i=0; i<nodes.length; i++) {
 	    if (nodes[i].id == 'Wuerfelfeld1') {
 		switch (dice){
 		case 1:{
 		    nodes[i].src=getDiceImg(GelbWert);
 		    break;
 		}
 		case 2:{
 		    nodes[i].src=getDiceImg(BlauWert);
 		    break;
 		}
 		case 3:{
 		    nodes[i].src=getDiceImg(GreenWert);
 		    break;
 		}
 		case 4:{
 		    nodes[i].src=getDiceImg(OrangeWert);
 		    break;
 		}
 		case 5:{
 		    nodes[i].src=getDiceImg(LilaWert);
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
 		    nodes[i].src=getDiceImg(GelbWert);
 		    break;
 		}
 		case 2:{
 		    nodes[i].src=getDiceImg(BlauWert);
 		    break;
 		}
 		case 3:{
 		    nodes[i].src=getDiceImg(GreenWert);
 		    break;
 		}
 		case 4:{
 		    nodes[i].src=getDiceImg(OrangeWert);
 		    break;
 		}
 		case 5:{
 		    nodes[i].src=getDiceImg(LilaWert);
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
 		    nodes[i].src=getDiceImg(GelbWert);
 		    break;
 		}
 		case 2:{
 		    nodes[i].src=getDiceImg(BlauWert);
 		    break;
 		}
 		case 3:{
 		    nodes[i].src=getDiceImg(GreenWert);
 		    break;
 		}
 		case 4:{
 		    nodes[i].src=getDiceImg(OrangeWert);
 		    break;
 		}
 		case 5:{
 		    nodes[i].src=getDiceImg(LilaWert);
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
function clearWuerfelfeld () {
    // Hier wird das Silberfeld zurueckgesetzt wenn die neuen Wuerfel geladen
    // werden
    document.getElementById("wuerfelfeldwhite").src = empty;
    document.getElementById("wuerfelfeldyellow").src = empty;
    document.getElementById("wuerfelfeldblue").src = empty;
    document.getElementById("wuerfelfeldorange").src = empty;
    document.getElementById("wuerfelfeldgreen").src = empty;
    document.getElementById("wuerfelfeldpurple").src = empty;
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
	document.getElementById("buttonNachwurf").setAttribute('onclick',"Clicked('NACHWUERFELN')");
    }else{
	document.getElementById("buttonNachwurf").setAttribute('onclick','');
    }
}
function setZusatzwuerfelnClickable(x){
    if(x==1){
	document.getElementById("buttonZusatzWuerfel").setAttribute('onclick',"Clicked('ZUSATZWUERFELN')");
    }else{
	document.getElementById("buttonZusatzWuerfel").setAttribute('onclick','');
    }
}
function setWuerfelnClickable(x){
    if(x==1){
	document.getElementById("buttonwurf").setAttribute('onclick','Clicked("WUERFELN")');
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
	document.getElementById("wuerfelfeldEntscheider2").src=empty;
	document.getElementById("wuerfelfeldEntscheider2").setAttribute('onclick','');
    }
}
function setColorDeciderColor(x){
    console.log(x);
    switch (x){
    case 0:{
	document.getElementById("wuerfelfeldEntscheider1").src=empty;
	break;
    }
    case 1:{
	document.getElementById("wuerfelfeldEntscheider1").src=getDiceImg(GelbWert);
    	break;
        }
    case 2:{
	document.getElementById("wuerfelfeldEntscheider1").src=getDiceImg(BlauWert);
    	break;
    }
    case 3:{
	document.getElementById("wuerfelfeldEntscheider1").src=getDiceImg(GreenWert);
    	break;
    }
    case 4:{
	document.getElementById("wuerfelfeldEntscheider1").src=getDiceImg(OrangeWert);
    	break;
    }
    case 5:{
	document.getElementById("wuerfelfeldEntscheider1").src=getDiceImg(LilaWert);
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
function showCurrentPlayer(){
	if (namelist.lenght!=0){
		document.getElementById("labelCurrentPlayer").innerHTML = "Aktiver Spieler: " + namelist[currentPlayer-1];
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
    clearPlace();
    clearField();
    var limit = 345;
    switch (playerAmount) {
    case 2: {
	limit = 181;
	break;
    }
    case 3: {
	limit = 264;
	break;
    }
    }
    for (var i = 0; i < limit; i++) {
	switch (i) {
	case 0: {
	    setRound(+x[i]);
	    if(+x[i]!=0){
	    }
	    break;
	}
	case 1: {
	    currentPlayer = +x[i];
	    showview(+x[i]);
	    showCurrentPlayer();
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
	    //TODO
	    break;
	}
	case 9: {
	    setDiceOnField(2,+x[i]);
	  //TODO
	    break;
	}
	case 10: {
	    setDiceOnField(3,+x[i]);
	  //TODO
	    break;
	}
	case 11: {
	    if(+x[i]==1){ 
		setDiceOnTray(BlauWert);
		} 
	    else if(+x[8]!=2 && +x[9]!=2 && +x[10]!=2){
		    setDiceOnPlace(2);
	    }
	    break;
	}
	case 12: {
	    if(+x[i]==1){ 
		setDiceOnTray(GelbWert);
		} else if(+x[8]!=1 && +x[9]!=1 && +x[10]!=1){
		    setDiceOnPlace(1);
		}
	    break;
	}
	case 13: {
	    if(+x[i]==1){ 
		setDiceOnTray(OrangeWert);
		} else if(+x[8]!=4 && +x[9]!=4 && +x[10]!=4){
		    setDiceOnPlace(4);
		}
	    break;
	}
	case 14: {
	    if(+x[i]==1){ 
		setDiceOnTray(LilaWert);
		} else if(+x[8]!=5 && +x[9]!=5 && +x[10]!=5){
		    setDiceOnPlace(5);
		}
	    break;
	}
	case 15: {
	    if(+x[i]==1){ 
		setDiceOnTray(GreenWert);
		} else if(+x[8]!=3 && +x[9]!=3 && +x[10]!=3){
		    setDiceOnPlace(3);
		}
	    break;
	}
	case 16: {
	    if(+x[i]==1){ 
		setDiceOnTray(WeissWert);
		} else if(+x[8]!=6 && +x[9]!=6 && +x[10]!=6){
		    setDiceOnPlace(6);
		}
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
		break;
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
		if(x[i]==1){
			document.getElementById("label").innerHTML = "Du darfst jetzt Wuerfeln!";
		} else{
			document.getElementById("label").innerHTML = "";
		}
	    }
	    break;
	    }
	case 70:{
	    console.log("BEGINN ENTSCHEIDER");
	    console.log(+x[i]);
		if(userNumber==1){
		setColorDecider(+x[i]);
	    }
	    break;
	    }
	case 71:{
		console.log(+x[i]);
		if(userNumber==1){
		setWhiteDecider(+x[i]);
	    }
	    break;
	    }
	case 72:{
		console.log(+x[i]);
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
	if(x[i]==1){
		document.getElementById("label").innerHTML = "Du darfst jetzt Wuerfeln!";
	} else{
		document.getElementById("label").innerHTML = "";
	}
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

case 181:{
    setNachwuerfelnKreis(3, +x[i]);
    break;
}
case 182:{
	setNachwuerfelnX(3, +x[i]);
    break;
}
case 183:{
	setZusatzwuerfelnKreis(3, +x[i]);
    break;
}
case 184:{
	setZusatzwuerfelnX(3, +x[i]);
    break;
}
case 185:{
	if(+x[i]==1){
		setYellowX(3, 0);
	}
    break;
}
case 186:{
	if(+x[i]==1){
		setYellowX(3, 1);
	}
    break;
}

case 187:{
	if(+x[i]==1){
		setYellowX(3, 2);
	}
    break;
}
case 188:{
	if(+x[i]==1){
		setYellowX(3, 3);
	}
    break;
}
case 189:{
	if(+x[i]==1){
		setYellowX(3, 4);
	}
    break;
}
case 190:{
	if(+x[i]==1){
		setYellowX(3, 5);
	}
    break;
}
case 191:{
	if(+x[i]==1){
		setYellowX(3, 6);
	}
    break;
}
case 192:{
	if(+x[i]==1){
		setYellowX(3, 7);
	}
    break;
}
case 193:{
	if(+x[i]==1){
		setYellowX(3, 8);
	}
    break;
}
case 194:{
	if(+x[i]==1){
		setYellowX(3, 9);
	}
    break;
}
case 195:{
	if(+x[i]==1){
		setYellowX(3, 10);
	}
    break;
}
case 196:{
	if(+x[i]==1){
		setYellowX(3, 11);
	}
    break;
}
case 197:{
	if(+x[i]==1){
		setBlueX(3, 0);
	}
    break;
}
case 198:{
	if(+x[i]==1){
		setBlueX(3, 1);
	}
    break;
}
case 199:{
	if(+x[i]==1){
		setBlueX(3, 2);
	}
    break;
}
case 200:{
	if(+x[i]==1){
		setBlueX(3, 3);
	}
    break;
}
case 201:{
	if(+x[i]==1){
		setBlueX(3, 4);
	}
    break;
}
case 202:{
	if(+x[i]==1){
		setBlueX(3, 5);
	}
    break;
}
case 203:{
	if(+x[i]==1){
		setBlueX(3, 6);
	}
    break;
}
case 204:{
	if(+x[i]==1){
		setBlueX(3, 7);
	}
    break;
}
case 205:{
	if(+x[i]==1){
		setBlueX(3, 8);
	}
    break;
}
case 206:{
	if(+x[i]==1){
		setBlueX(3, 9);
	}
    break;
}
case 207:{
	if(+x[i]==1){
		setBlueX(3, 10);
	}
    break;
}
case 208:{
	setGreenX(3, +x[i]);
    break;
}
case 209:{
	setOrange(3,0, +x[i]);
	break;
}
case 210:{
	setOrange(3,1, +x[i]);
	break;
}
case 211:{
	setOrange(3,2, +x[i]);
	break;
}
case 212:{
	setOrange(3,3, +x[i]);
	break;
}
case 213:{
	setOrange(3,4, +x[i]);
	break;
}	
case 214:{
	setOrange(3,5, +x[i]);
	break;
}	
case 215:{
	setOrange(3,6, +x[i]);
	break;
}	
case 216:{
	setOrange(3,7, +x[i]);
	break;
}	
case 217:{
	setOrange(3,8, +x[i]);
	break;
}	
case 218:{
	setOrange(3,9, +x[i]);
	break;
}	
case 219:{
	setOrange(3,10, +x[i]);
	break;
}	
case 220:{
	setPurple(3,0, +x[i]);
break;
	}
case 221:{
	setPurple(3,1, +x[i]);
break;
	}
case 222:{
	setPurple(3,2, +x[i]);
break;
	}
case 223:{
	setPurple(3,3, +x[i]);
break;
	}
case 224:{
	setPurple(3,4, +x[i]);
break;
	}
case 225:{
    setPurple(3,5, +x[i]);
    break;   	    	}
case 226:{
	setPurple(3,6, +x[i]);
break;
}
case 227:{
	setPurple(3,7, +x[i]);
break;
}
case 228:{
	setPurple(3,8, +x[i]);
break;
}
case 229:{
	setPurple(3,9, +x[i]);
break;
}
case 230:{
	setPurple(3,10, +x[i]);
break;
}
case 231:{
    if(userNumber==3){
	setNachwuerfelnClickable(+x[i]);
    }
    break;
    }
case 232:{
    if(userNumber==3){
	setZusatzwuerfelnClickable(+x[i]);
    }
    break;
    }
case 233:{
    if(userNumber==3){
	setWuerfelnClickable(+x[i]);
	if(x[i]==1){
		document.getElementById("label").innerHTML = "Du darfst jetzt Wuerfeln!";
	} else{
		document.getElementById("label").innerHTML = "";
	}
    }
    break;
    }
case 234:{
    if(userNumber==3){
	setColorDecider(+x[i]);
    }
    break;
    }
case 235:{
    if(userNumber==3){
	setWhiteDecider(+x[i]);
    }
    break;
    }
case 236:{
    if(userNumber==3){
	setColorDeciderColor(+x[i]);
    }
    break;
    }
case 237:{
	setYellowClickable(3, 0,+x[i]);
    break;
}
case 238:{
    setYellowClickable(3, 1,+x[i]);
    break;
}
case 239:{
	setYellowClickable(3, 2,+x[i]);
    break;
}
case 240:{
	setYellowClickable(3, 3,+x[i]);
    break;
}
case 241:{
	setYellowClickable(3, 4,+x[i]);
    break;
}
case 242:{
	setYellowClickable(3, 5,+x[i]);
    break;
}
case 243:{
	setYellowClickable(3, 6,+x[i]);
    break;
}
case 244:{
	setYellowClickable(3, 7,+x[i]);
    break;
}
case 245:{
	setYellowClickable(3, 8,+x[i]);
    break;
}
case 246:{
	setYellowClickable(3, 9,+x[i]);
    break;
}
case 247:{
	setYellowClickable(3, 10,+x[i]);
    break;
}
case 248:{
	setYellowClickable(3, 11,+x[i]);
    break;
}
case 249:{
	setBlueClickable(3, 0,+x[i]);
    break;
}
case 250:{
	setBlueClickable(3, 1,+x[i]);
    break;
}
case 251:{
	setBlueClickable(3, 2,+x[i]);
    break;
}
case 252:{
	setBlueClickable(3, 3,+x[i]);
    break;
}
case 253:{
	setBlueClickable(3, 4,+x[i]);
    break;
}
case 254:{
	setBlueClickable(3, 5,+x[i]);
    break;
}
case 255:{
	setBlueClickable(3, 6,+x[i]);
    break;
}
case 256:{
	setBlueClickable(3, 7,+x[i]);
    break;
}
case 257:{
    setBlueClickable(3, 8,+x[i]);
    break;
}
case 258:{
    setBlueClickable(3, 9,+x[i]);
    break;
}
case 259:{
    setBlueClickable(3, 10,+x[i]);
    break;
}
case 260:{
    setGreenClickable(3, +x[i]);
    break;
}
case 261:{
    setOrangeClickable(3, +x[i]);
    break;
}
case 262:{
    setPurpleClickable(3, +x[i]);
    break;
}
//ENDE SPIELER 3
//START SPIELER4 DATA
case 263:{
    setNachwuerfelnKreis(4, +x[i]);
    break;
}
case 264:{
	setNachwuerfelnX(4, +x[i]);
    break;
}
case 265:{
	setZusatzwuerfelnKreis(4, +x[i]);
    break;
}
case 266:{
	setZusatzwuerfelnX(4, +x[i]);
    break;
}
case 267:{
	if(+x[i]==1){
		setYellowX(4, 0);
	}
    break;
}
case 268:{
	if(+x[i]==1){
		setYellowX(4, 1);
	}
    break;
}

case 269:{
	if(+x[i]==1){
		setYellowX(4, 2);
	}
    break;
}
case 270:{
	if(+x[i]==1){
		setYellowX(4, 3);
	}
    break;
}
case 271:{
	if(+x[i]==1){
		setYellowX(4, 4);
	}
    break;
}
case 272:{
	if(+x[i]==1){
		setYellowX(4, 5);
	}
    break;
}
case 273:{
	if(+x[i]==1){
		setYellowX(4, 6);
	}
    break;
}
case 274:{
	if(+x[i]==1){
		setYellowX(4, 7);
	}
    break;
}
case 275:{
	if(+x[i]==1){
		setYellowX(4, 8);
	}
    break;
}
case 276:{
	if(+x[i]==1){
		setYellowX(4, 9);
	}
    break;
}
case 277:{
	if(+x[i]==1){
		setYellowX(4, 10);
	}
    break;
}
case 278:{
	if(+x[i]==1){
		setYellowX(4, 11);
	}
    break;
}
case 279:{
	if(+x[i]==1){
		setBlueX(4, 0);
	}
    break;
}
case 280:{
	if(+x[i]==1){
		setBlueX(4, 1);
	}
    break;
}
case 281:{
	if(+x[i]==1){
		setBlueX(4, 2);
	}
    break;
}
case 282:{
	if(+x[i]==1){
		setBlueX(4, 3);
	}
    break;
}
case 283:{
	if(+x[i]==1){
		setBlueX(4, 4);
	}
    break;
}
case 284:{
	if(+x[i]==1){
		setBlueX(4, 5);
	}
    break;
}
case 285:{
	if(+x[i]==1){
		setBlueX(4, 6);
	}
    break;
}
case 286:{
	if(+x[i]==1){
		setBlueX(4, 7);
	}
    break;
}
case 287:{
	if(+x[i]==1){
		setBlueX(4, 8);
	}
    break;
}
case 288:{
	if(+x[i]==1){
		setBlueX(4, 9);
	}
    break;
}
case 289:{
	if(+x[i]==1){
		setBlueX(4, 10);
	}
    break;
}
case 290:{
	setGreenX(4, +x[i]);
    break;
}
case 291:{
	setOrange(4,0, +x[i]);
	break;
}
case 292:{
	setOrange(4,1, +x[i]);
	break;
}
case 293:{
	setOrange(4,2, +x[i]);
	break;
}
case 294:{
	setOrange(4,3, +x[i]);
	break;
}
case 295:{
	setOrange(4,4, +x[i]);
	break;
}	
case 296:{
	setOrange(4,5, +x[i]);
	break;
}	
case 297:{
	setOrange(4,6, +x[i]);
	break;
}	
case 298:{
	setOrange(4,7, +x[i]);
	break;
}	
case 299:{
	setOrange(4,8, +x[i]);
	break;
}	
case 300:{
	setOrange(4,9, +x[i]);
	break;
}	
case 301:{
	setOrange(4,10, +x[i]);
	break;
}
case 302:{
	setPurple(4,0, +x[i]);
break;
	}
case 303:{
	setPurple(4,1, +x[i]);
break;
	}
case 304:{
	setPurple(4,2, +x[i]);
break;
	}
case 305:{
	setPurple(4,3, +x[i]);
break;
	}
case 306:{
	setPurple(4,4, +x[i]);
break;
	}
case 307:{
    setPurple(4,5, +x[i]);
    break;   	    	}
case 308:{
	setPurple(4,6, +x[i]);
break;
}
case 309:{
	setPurple(4,7, +x[i]);
break;
}
case 310:{
	setPurple(4,8, +x[i]);
break;
}
case 311:{
	setPurple(4,9, +x[i]);
break;
}
case 312:{
	setPurple(4,10, +x[i]);
break;
}
case 313:{
    if(userNumber==4){
	setNachwuerfelnClickable(+x[i]);
    }
    break;
    }
case 314:{
    if(userNumber==4){
	setZusatzwuerfelnClickable(+x[i]);
    }
    break;
    }
case 315:{
    if(userNumber==4){
	setWuerfelnClickable(+x[i]);
	if(x[i]==1){
		document.getElementById("label").innerHTML = "Du darfst jetzt Wuerfeln!";
	} else{
		document.getElementById("label").innerHTML = "";
	}
    }
    break;
    }
case 316:{
    if(userNumber==4){
	setColorDecider(+x[i]);
    }
    break;
    }
case 317:{
    if(userNumber==4){
	setWhiteDecider(+x[i]);
    }
    break;
    }
case 318:{
    if(userNumber==4){
	setColorDeciderColor(+x[i]);
    }
    break;
    }
case 319:{
	setYellowClickable(4, 0,+x[i]);
    break;
}
case 320:{
    setYellowClickable(4, 1,+x[i]);
    break;
}
case 321:{
	setYellowClickable(4, 2,+x[i]);
    break;
}
case 322:{
	setYellowClickable(4, 3,+x[i]);
    break;
}
case 323:{
	setYellowClickable(4, 4,+x[i]);
    break;
}
case 324:{
	setYellowClickable(4, 5,+x[i]);
    break;
}
case 325:{
	setYellowClickable(4, 6,+x[i]);
    break;
}
case 326:{
	setYellowClickable(4, 7,+x[i]);
    break;
}
case 327:{
	setYellowClickable(4, 8,+x[i]);
    break;
}
case 328:{
	setYellowClickable(4, 9,+x[i]);
    break;
}
case 329:{
	setYellowClickable(4, 10,+x[i]);
    break;
}
case 330:{
	setYellowClickable(4, 11,+x[i]);
    break;
}
case 331:{
	setBlueClickable(4, 0,+x[i]);
    break;
}
case 332:{
	setBlueClickable(4, 1,+x[i]);
    break;
}
case 333:{
	setBlueClickable(4, 2,+x[i]);
    break;
}
case 334:{
	setBlueClickable(4, 3,+x[i]);
    break;
}
case 335:{
	setBlueClickable(4, 4,+x[i]);
    break;
}
case 336:{
	setBlueClickable(4, 5,+x[i]);
    break;
}
case 337:{
	setBlueClickable(4, 6,+x[i]);
    break;
}
case 338:{
	setBlueClickable(4, 7,+x[i]);
    break;
}
case 339:{
    setBlueClickable(4, 8,+x[i]);
    break;
}
case 340:{
    setBlueClickable(4, 9,+x[i]);
    break;
}
case 341:{
    setBlueClickable(4, 10,+x[i]);
    break;
}
case 342:{
    setGreenClickable(4, +x[i]);
    break;
}
case 343:{
    setOrangeClickable(4, +x[i]);
    break;
}
case 344:{
    setPurpleClickable(4, +x[i]);
    break;
}
}
	
    }
    switch (userNumber){
    case 1: {
    		if(+x[345]==1){
    			document.getElementById("buttonUeberspringen").setAttribute('onclick','skip()');
    		} else{
    			document.getElementById("buttonUeberspringen").setAttribute('onclick','');
    		}
    	break;
    }
    case 2:{
    	    if(+x[346]==1){
    	    	document.getElementById("buttonUeberspringen").setAttribute('onclick','skip()');
    		} else{
    			document.getElementById("buttonUeberspringen").setAttribute('onclick','');;
    		}
    	break;
    }
    case 3: {
    	    if(+x[347]==1){
    	    	document.getElementById("buttonUeberspringen").setAttribute('onclick','skip()');
    		} else{
    			document.getElementById("buttonUeberspringen").setAttribute('onclick','');
    		}
    	break;
    }
    case 4:{
    	    if(+x[348]==1){
    	    	document.getElementById("buttonUeberspringen").setAttribute('onclick','skip()');
    		} else{
    			document.getElementById("buttonUeberspringen").setAttribute('onclick','');
    		}
    	break;
    }
    }
}

function closeLobby(){
    
    	sendDataToServer("STARTGAME");

	 


}