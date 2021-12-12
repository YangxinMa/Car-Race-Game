"use strict"

const loginObj = new Vue({
    el: "#loginWeb",
    data: {
        user: "",
    },
    methods: {
        login: loginHelper,
    }
});

function loginHelper(){
    axios.post('/login', loginObj.user).then(function (response) {
        console.log(response);
        localStorage.setItem('CurrentUser', response.data);
        window.location.href = "game.html";
    }).catch(function (error) {
        console.log(error);
    });
}
