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

playerMessage = "";
var Blau1 = "/gsClever/Images/dice/1Blau.png";
var Blau2 = "/gsClever/Images/dice/2Blau.png";
var Blau3 = "/gsClever/Images/dice/3Blau.png";
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
        case 0: return;
        case 1: return Blau1;
        case 2: return Blau2;
    }
}
function getImg(x){
    switch(x){
    case 0: return ximg;
    case 1: return circleimg;
}
function getNumberImg(x){
    switch(x){
    case 1: return img1;
    case 2: return img2;
    case 3: return img3;
    case 4: return img4;
    case 5: return img5;
    case 6: return img6;
    case 8: return img8;
    case 9: return img9;
    case 12: return img12;
    case 15: return img15;
    case 18: return img18;
    
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

