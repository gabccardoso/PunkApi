import './css/style.css';
import 'bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';
import { montaAuthentication } from './config/credenciais.js';

const URL_LOGIN = "http://localhost:8080/oauth2/token";

async function handleLoginForm(event) {
  event.preventDefault();
  const credenciais = await montaAuthentication();
  const username = document.getElementById("username").value;
  const senha = document.getElementById("password").value;
  fazLogin(credenciais, username, senha);
}

async function fazLogin(credenciais, username, senha){
  const data = new URLSearchParams();
  data.append('username',username);
  data.append('password', senha);
  data.append('grant_type', 'password');
  const response = await fetch(URL_LOGIN, {
    method: "POST",
    body: data,
    headers: {
      "Authorization": credenciais
    }
  });
  if (response.ok) {
    const responseJson = await response.json();
    const token = responseJson.access_token;
    
    if (token) {
      document.cookie = `token=${token}`;
      window.location.href = './telas/punk-Api.html';
    } else {
      alert("Token vazio. Erro ao fazer login. Tente novamente.");
    }
  } else {
    alert("Erro ao fazer login. Tente novamente.");
  }
    
}
  
window.addEventListener("load", function() {
  document.querySelector("#login-form").addEventListener("submit", handleLoginForm);
});



  