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
	var 1Blau = "/gsClever/images/dice/1Blau.png"
var 1Blau = "/gsClever/images/dice/1Blau.png"
var 1Blau = "/gsClever/images/dice/1Blau.png"
	var oImg = "/TicTacToe/images/o2.png";
	var statusWait = true;

	var arrFields = [0,0,0,0,0,0,0,0,0];
	var sentFields = [0,0,0,0,0,0,0,0,0];
	var currentPlayer = 0;

	function setField(x){
		sentFields[x]=1;
	}

	function getDiceImg(x){
		switch(x){
            case 0: return 1Blua;
            case 1: return;
            case 2: return;
        }
	}
    function getImg(x){
		if(x==1) return xImg;
		else if(x==2) return oImg;
		return emptyImg
	}
    function getNumberImg(x){
		if(x==1) return xImg;
		else if(x==2) return oImg;
		return emptyImg
	}
	
        
    (window).on('load',function () {
        // TODO: Code goes here
    }

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
	
