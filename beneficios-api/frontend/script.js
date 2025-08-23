const API_URL = 'http://localhost:8080/api/beneficiarios';

// Elementos do DOM
const form = document.getElementById('formBeneficiario');
const listaBeneficiarios = document.getElementById('listaBeneficiarios');
const statusElement = document.getElementById('status');

// Verificar status da API
async function verificarStatusAPI() {
    try {
        const response = await fetch(API_URL);
        statusElement.textContent = '🟢 API conectada';
        statusElement.className = 'verde';
        return true;
    } catch (error) {
        statusElement.textContent = '🔴 API não conectada';
        statusElement.className = 'vermelho';
        return false;
    }
}

// Carregar todos os beneficiários
async function carregarBeneficiarios() {
    try {
        const response = await fetch(API_URL);
        const beneficiarios = await response.json();
        exibirBeneficiarios(beneficiarios);
    } catch (error) {
        console.error('Erro ao carregar beneficiários:', error);
        listaBeneficiarios.innerHTML = '<p class="erro">Erro ao carregar beneficiários. Verifique se o backend está rodando.</p>';
    }
}

// Carregar apenas ativos
async function carregarAtivos() {
    try {
        const response = await fetch(API_URL + '/ativos');
        const beneficiarios = await response.json();
        exibirBeneficiarios(beneficiarios);
    } catch (error) {
        console.error('Erro ao carregar ativos:', error);
    }
}

// Carregar apenas inativos
async function carregarInativos() {
    try {
        const response = await fetch(API_URL + '/inativos');
        const beneficiarios = await response.json();
        exibirBeneficiarios(beneficiarios);
    } catch (error) {
        console.error('Erro ao carregar inativos:', error);
    }
}

// Exibir beneficiários na lista
function exibirBeneficiarios(beneficiarios) {
    if (beneficiarios.length === 0) {
        listaBeneficiarios.innerHTML = '<p>Nenhum beneficiário encontrado.</p>';
        return;
    }

    listaBeneficiarios.innerHTML = beneficiarios.map(beneficiario => `
        <div class="beneficiario-card ${beneficiario.status === 'INATIVO' ? 'inativo' : ''}">
            <div class="beneficiario-info">
                <strong>${beneficiario.nome}</strong>
                <div>NIS: ${beneficiario.nis}</div>
                <div>Status: ${beneficiario.status}</div>
                <div>ID: ${beneficiario.id}</div>
            </div>
            <div class="beneficiario-actions">
                ${beneficiario.status === 'ATIVO' ? 
                    `<button class="btn-inativar" onclick="inativarBeneficiario(${beneficiario.id})">Inativar</button>` :
                    `<button class="btn-ativar" onclick="ativarBeneficiario(${beneficiario.id})">Ativar</button>`
                }
                <button class="btn-excluir" onclick="excluirBeneficiario(${beneficiario.id})">Excluir</button>
            </div>
        </div>
    `).join('');
}

// Cadastrar novo beneficiário
async function cadastrarBeneficiario(event) {
    event.preventDefault();
    
    const nis = document.getElementById('nis').value;
    const nome = document.getElementById('nome').value;

    const beneficiario = {
        nis: nis,
        nome: nome
    };

    try {
        const response = await fetch(API_URL, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(beneficiario)
        });

        if (response.ok) {
            alert('Beneficiário cadastrado com sucesso!');
            form.reset();
            carregarBeneficiarios(); // Atualiza a lista
        } else {
            const error = await response.text();
            alert('Erro ao cadastrar: ' + error);
        }
    } catch (error) {
        console.error('Erro:', error);
        alert('Erro ao conectar com o servidor');
    }
}

// Inativar beneficiário
async function inativarBeneficiario(id) {
    if (confirm('Tem certeza que deseja inativar este beneficiário?')) {
        try {
            const response = await fetch(`${API_URL}/${id}/inativar`, {
                method: 'PATCH'
            });

            if (response.ok) {
                alert('Beneficiário inativado com sucesso!');
                carregarBeneficiarios();
            }
        } catch (error) {
            console.error('Erro:', error);
        }
    }
}

// Ativar beneficiário
async function ativarBeneficiario(id) {
    try {
        const response = await fetch(`${API_URL}/${id}/ativar`, {
            method: 'PATCH'
        });

        if (response.ok) {
            alert('Beneficiário ativado com sucesso!');
            carregarBeneficiarios();
        }
    } catch (error) {
        console.error('Erro:', error);
    }
}

// Excluir beneficiário
async function excluirBeneficiario(id) {
    if (confirm('Tem certeza que deseja excluir este beneficiário?')) {
        try {
            const response = await fetch(`${API_URL}/${id}`, {
                method: 'DELETE'
            });

            if (response.ok) {
                alert('Beneficiário excluído com sucesso!');
                carregarBeneficiarios();
            }
        } catch (error) {
            console.error('Erro:', error);
        }
    }
}

// Event Listeners
form.addEventListener('submit', cadastrarBeneficiario);

// Inicialização
document.addEventListener('DOMContentLoaded', function() {
    verificarStatusAPI();
    carregarBeneficiarios();
    
    // Verificar status a cada 30 segundos
    setInterval(verificarStatusAPI, 30000);
});