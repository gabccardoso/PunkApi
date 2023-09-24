const URL_CREDENCIAIS = "http://localhost:8080/credenciais";

async function getCredenciais() {
  const response = await fetch(URL_CREDENCIAIS);
  if (!response.ok) {
    throw new Error('Erro ao recuperar as credenciais');
  }
  const credentiais = await response.json();
  return credentiais;
}

function montaAuthentication(){
  const credenciais = getCredenciais();
  const clientId = credenciais.clientId;
  const clientSecret = credenciais.clientSecret;
  const authString = `${clientId}:${clientSecret}`;
  const encodedAuth = btoa(authString);
  const authorizationHeader = "Basic " + encodedAuth;
  return authorizationHeader;
}

export{montaAuthentication};