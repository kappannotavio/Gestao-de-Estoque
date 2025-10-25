document.getElementById("loginForm").addEventListener("submit", async (event) => {
  event.preventDefault();

  const user = document.getElementById("usuario").value;
  const senha = document.getElementById("senha").value;
  
  try {
    const response = await fetch("http://localhost:8080/login", {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify({ usuario: user, senha: senha })
    });

    if (response.ok) {
      alert("Login feito com sucesso!");
      window.location.href = "index";
    } else {
      alert("Usuário ou senha incorretos!");
    }
  } catch (erro) {
    console.error("Erro na requisição:", erro);
  }
});