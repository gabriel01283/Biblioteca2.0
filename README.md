# Biiblioteca
===EM ANDAMENTO===
# Sistema de Gerencimento de Biblioteca
## Descrição do Projeto
O **Sistema de Gerenciamento de Biblioteca** é uma aplicação desenvolvida em **Java**, estruturada com o padrão de arquitetura **MVC (Model-View-Controller)**.  
Seu objetivo é facilitar o controle de livros, alunos e funcionários de uma biblioteca, permitindo o **empréstimo de obras**, **gerenciamento de acervos** e **controle de multas** por atraso.

O projeto foi desenvolvido com foco em **boas práticas de programação orientada a objetos** e no uso de **padrões de projeto** para garantir flexibilidade, reutilização e manutenção eficiente do código.

---

## Usuários do Sistema

### Aluno
- Visualiza os livros disponíveis.  
- Realiza **empréstimos** de livros.  
- Pode ser **multado** em caso de atraso na devolução.  

### Funcionário
- **Cadastra e remove livros** do sistema.  
- **Altera o status** dos livros (disponível, emprestado, atrasado etc).  
- **Gera multas** para alunos com devolução fora do prazo.  

---

## Padrões de Projeto Utilizados

### **MVC (Model-View-Controller)**
Organiza o sistema em três camadas principais:
- **Model:** Classes de domínio e regras de negócio (`Livro`, `Aluno`, `Funcionario` etc).  
- **View:** Interface de interação com o usuário.  
- **Controller:** Controla a comunicação entre Model e View, aplicando as regras do sistema.  

---

### **Factory**
Responsável pela **criação de objetos** de forma centralizada e desacoplada.  
Facilita a criação de diferentes tipos de usuários (como `Aluno` e `Funcionario`) sem modificar o código principal.
### **state**
-	Implementado na classe Livro para representar o estado atual do livro.
-	Permite identificar se o livro está disponível, emprestado ou atrasado, além de controlar as mudanças de estado conforme ações do sistema.
Assim, o comportamento do livro muda dinamicamente sem precisar de condicionais extensas no código.

### **Observer**
- Usado para monitorar o estado dos livros.
- Quando um novo livro é criado ou seu estado é alterado, os observadores são notificados (por exemplo, o sistema pode atualizar automaticamente a lista de livros disponíveis).
-	Isso facilita a comunicação entre as partes do sistema sem criar dependências diretas.
  
### **Singleton**
- Aplicado à classe Bibliotecário para garantir que apenas uma instância exista em todo o sistema.
- Isso impede a criação de múltiplos bibliotecários simultaneamente, mantendo o controle centralizado das operações administrativas (como verificar logins, livros e multas).

## Tecnologias Utilizadas
-java



## Instalação

1- Clone o repositorio:https://github.com/gabriel01283/Biblioteca2.0

2- Abra o projeto na IDE ja instalada no seu sistema

4- execute o projeto


## Diagrama de Casos de Uso:
<img width="374" height="759" alt="image" src="https://github.com/user-attachments/assets/39f05928-414c-481b-8b14-0f3a917b0bde" />


## Diagrama de Classes:

<img width="407" height="577" alt="image" src="https://github.com/user-attachments/assets/3e98a273-e6d9-4bcb-8ffa-c26cd26da810" />

## Autora
- Gabriel Saraiva Sampaio (https://github.com/gabriel01283)
- Maria Clara Nascimento Silva (https://github.com/clarrinha)
