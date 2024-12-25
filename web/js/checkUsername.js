// var elUsername = document.getElementById('username'),
//     elMsg      = document.getElementById('feedback');
//
// function checkUsername(username){
//     if (username === null || username === '' || username === 0){
//         elMsg.textContent = '用户名不能为空';
//         return;
//     }
//     if (username.length <= 5){
//         elMsg.textContent = '用户名长度必须大于5';
//         return;
//     }
// }
//
// elUsername.addEventListener('blur',function (){
//     var username = elUsername.value.trim();
//     checkUsername(username);
// })
window.onload = function() {
    var elUsername = document.getElementById('username'),
        elMsg = document.getElementById('feedback');

    function checkUsername(username) {
        if (username === null || username === '' || username === 0) {
            elMsg.textContent = '用户名不能为空';
            return;
        }
        if (username.length <= 5) {
            elMsg.textContent = '用户名长度必须大于5';
            return;
        }else{
            elMsg.textContent = "";
        }
    }

    elUsername.addEventListener('blur', function() {
        var username = elUsername.value.trim();
        checkUsername(username);
    });
};