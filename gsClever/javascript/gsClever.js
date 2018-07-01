addListener('standardEvent', function(event) {
    var stringFromServer = event.data;
    var arr = stringFromServer.split(',');
    // console.log(arr);

    if(arr.length==11){
        for(var i=0; i<9; i++) { arrFields[i] = +arr[i]; }
        playerMessage = arr[9];
        var str = arr[10];
        if(str=="HOST"){
            console.log(arr[10]);
            setVisible();
        }



        sentFields = [0,0,0,0,0,0,0,0,0];
        document.getElementById("Player").innerHTML = playerMessage;
        redraw();
    }
    statusWait = false;
});
addListener('START', function(event){
    var stringFromServer = event.data;
    var arr = stringFromServer.split(',');
    playerMessage = arr[9];
    document.getElementById("Player").innerHTML = playerMessage;
    if(arr[10]=="HOST") setVisible();
    statusWait = false;
});
addListener('PLAYERLEFT', function(event){
    var stringFromServer = event.data;
    playerMessage = stringFromServer;
    document.getElementById("Player").innerHTML = playerMessage;
});
addListener('CLOSE', function(event){
    document.getElementById("Player").innerHTML = "Spiel wurde vom Host beendet!";
});
addListener('TESTWUERFELN', function(event){
    var stringFromServer = event.data;
    var arr = stringFromServer.split(',');
    playerMessage = arr[212];
    //Schleife hier
    setDice(1);
    if(arr[10]=="HOST") setVisible();
    statusWait = false;
});

playerMessage = "";
var White1 = "/gsClever/Images/Dice/1White.jpg";
var White2 = "/gsClever/Images/Dice/2White.jpg";
var White3 = "/gsClever/Images/Dice/3White.jpg";
var White4 = "/gsClever/Images/Dice/4White.jpg";
var White5 = "/gsClever/Images/Dice/5White.jpg";
var White6 = "/gsClever/Images/Dice/6White.jpg";
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
var img1 ="/gsClever/Images/Numbers/1.png";
var img2 ="/gsClever/Images/Numbers/2.png";
var img3 ="/gsClever/Images/Numbers/3.png";
var img4 ="/gsClever/Images/Numbers/4.png";
var img5 ="/gsClever/Images/Numbers/5.png";
var img6 ="/gsClever/Images/Numbers/6.png";
var img8 ="/gsClever/Images/Numbers/8.png";
var img9 ="/gsClever/Images/Numbers/9.png";
var img12 ="/gsClever/Images/Numbers/12.png";
var img15 ="/gsClever/Images/Numbers/15.png";
var img18 ="/gsClever/Images/Numbers/18.png";

var ximg = "/gsClever/Images/X.png";
var circleimg = "/gsClever/Images/circle.png";
var statusWait = true;

var arrFields = [0,0,0,0,0,0,0,0,0];
var sentFields = [0,0,0,0,0,0,0,0,0];
var currentPlayer = 0;

function setField(x){
    sentFields[x]=1;
}

function getDiceImg(x){
    switch(x){
        case 0: return
        case 1: return White1
        case 2: return White2
        case 3: return White3
        case 4: return White4
        case 5: return White5
        case 6: return White6
        
        case 7: return Gelb1
        case 8: return Gelb2
        case 9: return Gelb3
        case 10: return Gelb4
        case 11: return Gelb5
        case 12: return Gelb6
        
        case 13: return Green1
        case 14: return Green2
        case 15: return Green3
        case 16: return Green4
        case 17: return Green5
        case 18: return Green6
        
        case 19: return Lila1
        case 20: return Lila2
        case 21: return Lila3
        case 22: return Lila4
        case 23: return Lila5
        case 24: return Lila6
        
        case 25: return Orange1
        case 26: return Orange2
        case 27: return Orange3
        case 28: return Orange4
        case 29: return Orange5
        case 30: return Orange6
        
        case 31: return White1
        case 32: return White2
        case 33: return White3
        case 34: return White4
        case 35: return White5
        case 36: return White6
    }
}
function getImg(x){
    switch(x){
    case 0: return ximg
    case 1: return circleimg
    }
}
function getNumberImg(x){
    switch(x){
    case 1: return img1
    case 2: return img2
    case 3: return img3
    case 4: return img4
    case 5: return img5
    case 6: return img6
    case 8: return img8
    case 9: return img9
    case 12: return img12
    case 15: return img15
    case 18: return img18
    }
}


/*
 * (window).on('load',function () { // TODO: Code goes here }
 */


    
    
function updateGameState(){
    statusWait = true;
    sendDataToServer(sentFields);
}

function restart(){
    statusWait = true;
    sendDataToServer("RESTART");
}
function setVisible(){
    document.getElementById("restartButton").style.visibility ="visible";	
    document.getElementById("closeButton").style.visibility ="visible";
}
function closeGame(){
    sendDataToServer("CLOSE");
}
function wuerfeln(){
    sendDataToServer("WUERFELN");
}
function nachwuerfeln(){
    sendDataToServer("NACHWUERFELN");
}
function zusatzwuerfeln(){
    sendDataToServer("ZUSATZWUERFELN");
}
function showview(x){
    // TODO
}
function skip(){
    sendDataToServer("SKIP");
}
function setDice(x){
    if(x>0 && x<=6){
	document.getElementById("wuerfelfeldsilber3").src = getDiceImg(x);
    } 
    if(x>6 && x<=12){
	document.getElementById("wuerfelfeldsilber2").src = getDiceImg(x);
    } 
    if(x>12 && x<=18){
	document.getElementById("wuerfelfeldsilber6").src = getDiceImg(x);
    } 
    if(x>18 && x<=24){
	document.getElementById("wuerfelfeldsilber5").src = getDiceImg(x);
    } 
    if(x>24 && x<=30){
	document.getElementById("wuerfelfeldsilber4").src = getDiceImg(x);
    } 
    if(x>30 && x<=36){
	document.getElementById("wuerfelfeldsilber1").src = getDiceImg(x);
    } 
    
}

