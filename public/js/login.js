"use strict"

const loginObj = new Vue({
    el: "#loginWeb",
    data: {
        username: "",
    },
    methods: {
        login: function () {
            loginHelper(
                loginObj.username,
            );
        }
    }
});

function loginHelper (username) {
    axios.post('/login', {
        username: username,
    }).then(function (response) {
        console.log(response);
        localStorage.setItem('CurrentUser', response.data);
        window.location.href = "game.html";
    }).catch(function (error) {
        console.log(error);
    });
}
