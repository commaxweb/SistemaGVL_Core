# Sistema GVL – Gestão de Vendas e Logística (Módulo Core Refatorado)

## 📌 Sobre o Projeto
Este repositório contém o módulo *Core* refatorado do **Sistema GVL (Gestão de Vendas e Logística)**, desenvolvido para a Etapa 6 do Projeto Integrador do curso Técnico em Desenvolvimento de Software (SENAC EAD).

O objetivo principal desta etapa foi realizar a refatoração do sistema desktop (Java Swing) anterior[cite: 4, 5], aplicando os princípios **SOLID** (especialmente o **SRP - Princípio da Responsabilidade Única**) e isolando as regras de negócio das camadas de interface e de persistência de dados. Essa arquitetura prepara o sistema para ser reutilizado em uma futura aplicação Web.

---

## 🛠️ Tecnologias Utilizadas
- **Linguagem:** Java (JDK 21)
- **IDE:** Apache NetBeans
- **Banco de Dados:** MySQL
- **Driver JDBC:** MySQL Connector/J
- **Versionamento:** Git e GitHub[cite: 4, 5]
- **Sistema Operacional:** Ubuntu Linux

---

## 🏗️ Estrutura do Projeto (Arquitetura e Pacotes)

```text
src/
└── br/com/gvl/
    ├── model/      # Entidades do sistema (Java Beans puras: Cliente, Fornecedor, Produto, Usuario)
    ├── service/    # Regras de Negócio (Validação de fornecedor, controle de estoque nas vendas)
    ├── dao/        # Acesso ao banco de dados MySQL via JDBC (Conexao, ProdutoDAO)
    └── main/       # Classe de testes da lógica de negócio via método main()
