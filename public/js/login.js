"use strict"

const loginObj = new Vue({
    el: "#loginWeb",
    data: {
        username: "",
        password: "",
    },
    methods: {
        login: function () {
            loginHelper(
                loginObj.username,
                loginObj.password,
            );
        }
    }
});

function loginHelper (username, password) {
    axios.post('/login', {
        username: username,
        password: password,
    }).then(function (response) {
        console.log(response);
        if (response.status === 200) {
            window.location.href = "home.html";
        }
    }).catch(function (error) {
        console.log(error);
    });
}
