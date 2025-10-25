async function carregarLogs() {
  try {
    const response = await fetch("http://localhost:8080/logs");
    if (!response.ok) throw new Error("Erro ao buscar logs");

    const logs = await response.json();
    const container = document.getElementById("logsContainer");
    container.innerHTML = '';

    if (logs.length === 0) {
      container.innerHTML = '<p class="text-center text-muted">Nenhuma alteração registrada.</p>';
      return;
    }

    logs.forEach(log => {
      container.innerHTML += `
        <div class="card mb-2 log-card">
          <div class="card-body">
            <p><strong>Produto:</strong> ${log.nomeProduto}</p>
            <p><strong>Ação:</strong> ${log.acao}</p>
            <p class="timestamp"><strong>Data:</strong> ${new Date(log.dataHora).toLocaleString()}</p>
          </div>
        </div>
      `;
    });

  } catch (erro) {
    console.error("Erro ao carregar logs:", erro);
    document.getElementById("logsContainer").innerHTML =
      '<p class="text-center text-danger">Não foi possível carregar os logs.</p>';
  }
}

window.addEventListener("DOMContentLoaded", carregarLogs);
