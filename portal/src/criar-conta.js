document.addEventListener("DOMContentLoaded", function () {
  const createAccountForm = document.getElementById("create-account-form");
  const URL_CRIA_CONTA = "http://localhost:8080/usuario/cria";

  createAccountForm.addEventListener("submit", function (e) {
    e.preventDefault();
    const username = document.getElementById("new-username").value;
    const senha = document.getElementById("new-password").value;
    const confirmaSenha = document.getElementById("confirm-password").value;

    let data = JSON.stringify({
      username: username,
      senha: senha
    });

    if(senha != confirmaSenha){alert("As senhas devem ser iguais. Tente novamente.");}
    else{
      fetch(URL_CRIA_CONTA, {
        method: "POST",
        body: data,
        headers: {
          "Content-Type": "application/json"
        }
      })
        .then((response) => {
          if (response.ok) {
            window.location.href ='../index.html';
          }
          else{
            alert("Erro ao criar conta. Tente novamente.");
          }
        });
    }
  });
});
