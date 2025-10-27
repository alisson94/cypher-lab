document.addEventListener('DOMContentLoaded', () => {
    const eyeIcons = document.querySelectorAll('.eye-icon');

    eyeIcons.forEach(icon => {
        
        icon.addEventListener('click', () => {
            
            const passwordInput = icon.parentElement.querySelector('input');

            if (passwordInput.type === 'password') {
                passwordInput.type = 'text';
                icon.classList.replace('bx-hide', 'bx-show');
            } else {
                passwordInput.type = 'password';
                icon.classList.replace('bx-show', 'bx-hide');
            }
        });
    });
    // Pega o botão de cadastro
    const btnCadastrar = document.getElementById("btn-cadastrar");

    // Adiciona um 'ouvinte' de clique no botão
    btnCadastrar.addEventListener("click", (evento) => {
        // Previne o comportamento padrão do formulário (que recarrega a página)
        evento.preventDefault(); 

        // 1. Coleta os dados dos campos
        const email = document.getElementById("email-cadastro").value;
        const senha = document.getElementById("senha-cadastro").value;

        // 2. Monta o objeto (DTO) que o backend espera
        const dadosUsuario = {
            email: email,
            senha: senha
        };

        // 3. Configura a requisição 'fetch'
        const opcoes = {
            method: "POST", // Método HTTP
            headers: {
                "Content-Type": "application/json" // Avisa ao backend que estamos enviando JSON
            },
            body: JSON.stringify(dadosUsuario) // Converte o objeto JS em uma string JSON
        };

        // 4. Envia a requisição para o seu backend
        fetch("http://localhost:8080/auth/register", opcoes)
            .then(response => {
                if (response.ok) { // Status 200-299
                    return response.text(); // Pega a mensagem de sucesso
                } else {
                    // Se o backend retornou um erro (ex: email já existe)
                    return response.text().then(texto => Promise.reject(texto));
                }
            })
            .then(data => {
                // Sucesso!
                alert("Sucesso: " + data); // Exibe "Usuário registrado com sucesso!"
                // Você pode redirecionar o usuário ou limpar o formulário aqui
            })
            .catch(error => {
                // Erro!
                alert("Erro: " + error); // Exibe "Erro: Email já cadastrado!"
            });
    });
});