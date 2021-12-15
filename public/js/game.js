"use strict";

// Ref: Based on our CMPT 213 Assignment to develop and add more feature

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
    mounted() {
        if (sessionStorage.getItem("time") != null) {
            window.clearInterval(sessionStorage.getItem("time"));
        }
        let time = this.timer();
        sessionStorage.setItem("time", time);
    },
    methods: {
        newGame: createNewGame,
        carMove: sendMoveCars,
        endGame: stopAndUploadRecord,
        changeSpeed: function(speedLevel) {
            if (sessionStorage.getItem("time") != null) {
                window.clearInterval(sessionStorage.getItem("time"));
            }
            let time = null;
            if (speedLevel === 1) {
               time = setInterval(() => {
                   sendMoveCars();
               }, 400);
            } else if (speedLevel === 2) {
                time = setInterval(() => {
                    sendMoveCars();
                }, 200);
            } else {
                time = setInterval(() => {
                    sendMoveCars();
                }, 100);
            }
            sessionStorage.setItem("time", time)
        },
        locationMatches: function(loc, x, y) {
            return loc.x === x && loc.y === y;
        },
        timer: function() {
            return setInterval(() => {
                sendMoveCars();
            }, 400)
        },
    }
});

// Ref： Based on our CMPT 213 Assignment to modify
window.addEventListener("keydown", function(e) {
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
        initTable();
    }).catch(function (error) {
        console.log(error);
    });
}

function initTable() {
    setTimeout(() => {
        $("#rankTable").DataTable({});
    }, 100)
}

// Ref： Based on our CMPT 213 Assignment code to modify
function createNewGame() {
    axios.post('/game', {}).then(function (response) {
            gameObj.game = response.data;
            gameObj.moveFlag = true;
            loadGameBoard();
        }).catch(function (error) {
            console.log(error);
        });
}

// Ref： Based on our CMPT 213 Assignment code to modify
function loadGame() {
    axios.get('/game/' + gameObj.game.gameId, {}).then(function (response) {
            console.log(response);
            gameObj.game = response.data;
        }).catch(function (error) {
            console.log(error);
        });
}

// Ref： Based on our CMPT 213 Assignment code to modify
function loadGameBoard() {
    axios.get('/game/' + gameObj.game.gameId + "/board", {}).then(function (response) {
            console.log(response);
            gameObj.board = response.data;
        }).catch(function (error) {
            console.log(error);
        });
}

// Ref： Based on our CMPT 213 Assignment code to modify
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

// Ref： Based on our CMPT 213 Assignment code to modify
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
        })
        .catch(function (error) {
            console.log(error);
        });
}

function stopAndUploadRecord() {
    axios.post('/game/' + gameObj.game.gameId + "/stop", gameObj.user).then(function (response) {
        console.log(response);
        gameObj.game = null;
        gameObj.board = null;
        gameObj.moveFlag = true;
        gameObj.rank = null;
        window.location.href = window.location.href
        if (sessionStorage.getItem("time") != null) {
            window.clearInterval(sessionStorage.getItem("time"));
        }
    }).catch(function (error) {
        console.log(error);
    });
}


