import './css/style.css';
import 'bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';
import { montaAuthentication } from './config/credenciais.js';

const URL_LOGIN = "http://localhost:8080/oauth2/token";

function handleLoginForm(event) {
  event.preventDefault();
  const credenciais = montaAuthentication();
  const username = document.getElementById("username");
  const senha = document.getElementById("senha");
  fazLogin(credenciais, username, senha);
}

function fazLogin(credenciais, username, senha){
  const data = new URLSearchParams();
  data.append('username',username);
  data.append('senha', senha);
  fetch(URL_LOGIN, {
    method: "POST",
    body: data,
    headers: {
      "Content-Type": "x-www-form-urlencoded",
      "Authorization": credenciais
    }
  })
    .then((response) => {
      if (response.ok) {
        alert("funcionou");
      }
      else{
        alert("Erro ao criar conta. Tente novamente.");
      }
    });
}
  
window.addEventListener("load", function() {
  document.querySelector("#login-form").addEventListener("submit", handleLoginForm);
});



  