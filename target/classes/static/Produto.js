const container = document.getElementById("produtosContainer");

// -------------------- CARREGAR PRODUTOS --------------------
async function carregarProdutos() {
  try {
    const response = await fetch("http://localhost:8080/system");
    if (!response.ok) throw new Error("Erro ao buscar produtos");

    const produtos = await response.json();
    container.innerHTML = "";

    produtos.forEach((produto, index) => {
      const card = document.createElement("div");
      card.classList.add("card", "col-md-3", "m-2");

      card.innerHTML = `
        <div class="card-body">
          <h5 class="card-title">${produto.nome}</h5>
          <p class="card-text">Categoria: ${produto.categoria}</p>
          <p class="card-text">Fornecedor: ${produto.fornecedor}</p>
          <p class="card-text">Valor: R$ ${produto.valor.toFixed(2)}</p>
          <p class="card-text">Quantidade: ${produto.quantidade}</p>
          <div class="d-flex justify-content-between mt-3">
            <button class="btn btn-sm btn-primary btn-edit">Editar</button>
            <button class="btn btn-sm btn-danger btn-delete">Excluir</button>
          </div>
        </div>
      `;

      // adicionar eventos aos botões
      card.querySelector(".btn-edit").addEventListener("click", () => abrirEditar(produto));
      card.querySelector(".btn-delete").addEventListener("click", () => removerProduto(produto.id));

      container.appendChild(card);
    });
  } catch (erro) {
    console.error("Erro ao carregar produtos:", erro);
    container.innerHTML = "<p>Não foi possível carregar os produtos.</p>";
  }
}

// -------------------- CADASTRAR PRODUTO --------------------
async function cadastrarProduto(event) {
  event.preventDefault(); // previne o reload do form

  const produto = {
    nome: document.getElementById("produtoNome").value,
    categoria: document.getElementById("produtoCategoria").value,
    fornecedor: document.getElementById("produtoFornecedor").value,
    valor: parseFloat(document.getElementById("produtoValor").value),
    quantidade: parseInt(document.getElementById("produtoQuantidade").value)
  };

  try {
    const response = await fetch("http://localhost:8080/system", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(produto)
    });

    if (!response.ok) throw new Error("Erro ao cadastrar produto");

    document.getElementById("formProduto").reset();
    carregarProdutos();
    alert("Produto cadastrado com sucesso!");
  } catch (erro) {
    console.error(erro);
    alert("Erro ao cadastrar produto.");
  }
}

// -------------------- REMOVER PRODUTO --------------------
async function removerProduto(id) {
  if (!confirm("Tem certeza que deseja excluir este produto?")) return;

  try {
    const response = await fetch(`http://localhost:8080/system/${id}`, {
      method: "DELETE"
    });
    if (!response.ok) throw new Error("Erro ao deletar produto");

    carregarProdutos();
    alert("Produto removido com sucesso!");
  } catch (erro) {
    console.error(erro);
    alert("Erro ao remover produto.");
  }
}

// -------------------- EDITAR PRODUTO --------------------
function abrirEditar(produto) {
  document.getElementById("editProdutoId").value = produto.id;
  document.getElementById("editProdutoNome").value = produto.nome;
  document.getElementById("editProdutoCategoria").value = produto.categoria;
  document.getElementById("editProdutoFornecedor").value = produto.fornecedor;
  document.getElementById("editProdutoValor").value = produto.valor;
  document.getElementById("editProdutoQuantidade").value = produto.quantidade;

  const modal = new bootstrap.Modal(document.getElementById("modalEditProduto"));
  modal.show();
}

async function salvarEdicao(event) {
  event.preventDefault();

  const id = document.getElementById("editProdutoId").value;
  const produtoAtualizado = {
    nome: document.getElementById("editProdutoNome").value,
    categoria: document.getElementById("editProdutoCategoria").value,
    fornecedor: document.getElementById("editProdutoFornecedor").value,
    valor: parseFloat(document.getElementById("editProdutoValor").value),
    quantidade: parseInt(document.getElementById("editProdutoQuantidade").value)
  };

  try {
    const response = await fetch(`http://localhost:8080/system/${id}`, {
      method: "PUT",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(produtoAtualizado)
    });

    if (!response.ok) throw new Error("Erro ao atualizar produto");

    carregarProdutos();
    const modal = bootstrap.Modal.getInstance(document.getElementById("modalEditProduto"));
    modal.hide();
    alert("Produto atualizado com sucesso!");
  } catch (erro) {
    console.error(erro);
    alert("Erro ao atualizar produto.");
  }
}

// -------------------- EVENTOS --------------------
document.getElementById("formProduto").addEventListener("submit", cadastrarProduto);
document.getElementById("formEditProduto").addEventListener("submit", salvarEdicao);
window.addEventListener("DOMContentLoaded", carregarProdutos);