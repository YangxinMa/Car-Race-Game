"use strict";

const gameObj = new Vue({
    el: "#gameApp",
    data: {
        user: "",
        rankTitle: ['Rank', 'User', 'Score'],
        rank: null,
        moveFlag: true,
        game: null,
        board: null,
    },
    created: function() {
      setInterval(this.timer, 1000);
    },
    methods: {
        newGame: createNewGame,
        carMove: sendMoveCars,
        endGame: stopAndUploadRecord,
        locationMatches: function(loc, x, y) {
            return loc.x == x && loc.y == y;
        },
        timer: function() {
            sendMoveCars();
            console.log("time")
        },
    }
});

window.addEventListener("keydown", function(e) {
    console.log("Key pressed: " + e.keyCode);
    switch(e.keyCode) {
        case 37: sendMove("MOVE_LEFT"); break;
        case 38: sendMove("MOVE_UP"); break;
        case 39: sendMove("MOVE_RIGHT"); break;
        case 40: sendMove("MOVE_DOWN"); break;
    }
});

$(document).ready(function() {
    loadUser();
    loadRankBoard();
    $("button#newGame").click(function(){
        $(this).hide();
    });
});

function loadUser() {
    gameObj.user = localStorage.getItem('CurrentUser');
    console.log("Current user: " + gameObj.user);
}

function loadRankBoard(){
    axios.get('/rank', {}).then(function (response) {
        console.log(response);
        gameObj.rank = response.data;
    }).catch(function (error) {
        console.log(error);
    });
}

function createNewGame() {
    axios.post('/game', {}).then(function (response) {
            gameObj.game = response.data;
            gameObj.moveFlag = true;
            loadGameBoard();
        }).catch(function (error) {
            console.log(error);
        });
}

function loadGame() {
    axios.get('/game/' + gameObj.game.gameId, {}).then(function (response) {
            console.log(response);
            gameObj.game = response.data;
        }).catch(function (error) {
            console.log(error);
        });
}
function loadGameBoard() {
    axios.get('/game/' + gameObj.game.gameId + "/board", {}).then(function (response) {
            console.log(response);
            gameObj.board = response.data;
        }).catch(function (error) {
            console.log(error);
        });
}

function sendMoveCars() {
    if (gameObj.game.isGameLost || gameObj.game.stopByUser) {
        console.log("Stop all the cars.");
        return;
    }

    axios.post('/game/' + gameObj.game.gameId + "/move", "MOVE_CARS")
        .then(function (response) {
            console.log(response);
            gameObj.moveFlag = true;
            loadGameBoard();
            loadGame();
        })
        .catch(function (error) {
            console.log(error);
        });
}

function sendMove(direction) {
    if (!gameObj.moveFlag) {
        console.log("Not player's turn yet!");
        return;
    }

    if (gameObj.game.isGameLost || gameObj.game.stopByUser) {
        console.log("Unable to make move after game has ended.");
        return;
    }

    axios.post('/game/' + gameObj.game.gameId + "/move", direction)
        .then(function (response) {
            console.log(response);
            loadGameBoard();
            loadGame();

            gameObj.moveFlag = false;
            setTimeout(sendMoveCars, 100);
        })
        .catch(function (error) {
            console.log(error);
        });
}

function stopAndUploadRecord() {
    axios.post('/game/' + gameObj.game.gameId + "/stop", gameObj.user).then(function (response) {
        console.log(response);
        gameObj.game = null;
        gameObj.moveFlag = false;
        window.location.href = "index.html";
    }).catch(function (error) {
        console.log(error);
    });
}


