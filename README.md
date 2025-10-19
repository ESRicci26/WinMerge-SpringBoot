# 🟨 Projeto: Analise Dois Arquivos - Comparador de Arquivos Estilo WinMerge

## 🧾 Descrição

**WinMerge-SpringBoot** é uma aplicação **Java 11 / Spring Boot / Thymeleaf** que compara dois arquivos texto (`.txt`) e exibe visualmente as diferenças lado a lado, de forma semelhante ao **WinMerge**.  
As partes diferentes são **realçadas em cores** e apresentadas em uma interface web intuitiva e responsiva.  

> 💡 Ideal para desenvolvedores, analistas e profissionais que precisam visualizar alterações em arquivos texto com clareza e simplicidade.

---

## 🛠️ Tecnologias Utilizadas

| Camada | Tecnologias |
|:--|:--|
| **Backend** | Java 11, Spring Boot 2.7.18 |
| **Frontend** | HTML5, CSS3, Thymeleaf |
| **Build** | Maven |
| **Servidor Web** | Tomcat embutido (via Spring Boot) |

---

## 📁 Estrutura do Projeto

```
WinMergeSpringBoot/
│
├── pom.xml
├── README.md
│
├── src/
│   ├── main/
│   │   ├── java/com/javaricci/WinMerge/
│   │   │   ├── WinMergeSpringBootApplication.java
│   │   │   └── Controller/
│   │   │       └── FileController.java
│   │   │
│   │   ├── resources/
│   │   │   ├── templates/
│   │   │   │   ├── index.html
│   │   │   │   └── analyze.html
│   │   │   └── static/css/
│   │   │       └── style.css
│   │   │
│   │   └── application.properties (opcional)
│   │
│   └── test/ (não utilizado)
│
└── target/ (gerado após build)
```

---

## ⚙️ Como Executar Localmente

### 🔹 Pré-requisitos
- **Java 11** instalado e configurado (`java -version`)
- **Maven 3.6+** instalado (`mvn -version`)

---

### 🔹 Passos para execução

4️⃣ **Acessar no navegador**
```
http://localhost:8080/
```

---

## 🧠 Funcionamento Interno

### 🔹 1. Upload dos arquivos
Na página inicial (`index.html`), o usuário seleciona dois arquivos `.txt` e envia via formulário `multipart/form-data` para o endpoint `/analyze`.

---

### 🔹 2. Comparação das linhas
O **controller** (`FileController.java`) lê ambos os arquivos em memória, quebra o conteúdo em linhas e compara linha por linha.

```java
List<String> lines1 = Arrays.asList(content1.split("\r?\n"));
List<String> lines2 = Arrays.asList(content2.split("\r?\n"));
```

---

### 🔹 3. Análise palavra a palavra
Cada linha é dividida em palavras, e diferenças são destacadas usando a classe CSS `.diff`.

```java
if (!w1.equals(w2)) {
    sb.append("<span class='diff'>").append(escapeHtml(w1)).append("</span>");
}
```

---

### 🔹 4. Exibição lado a lado
O `analyze.html` mostra o conteúdo dos dois arquivos lado a lado, com destaque colorido para diferenças.

```html
<div class="diff-container">
    <div class="file-column">
        <h3>Arquivo 1</h3>
        <pre th:utext="${file1Content}"></pre>
    </div>
    <div class="file-column">
        <h3>Arquivo 2</h3>
        <pre th:utext="${file2Content}"></pre>
    </div>
</div>
```

---

## 🎨 Interface Visual

| Tela | Descrição |
|:--|:--|
| **Tela Inicial** | Permite selecionar dois arquivos `.txt` e iniciar a comparação. |
| **Tela de Resultado** | Exibe os arquivos lado a lado com diferenças destacadas. |

### 💻 Exemplo Visual

```
Arquivo 1                         Arquivo 2
---------------------------------------------
Este é um teste                   Este é um teste
de comparação                     de comparação simples
entre dois textos.                entre dois textos diferentes.
```

Diferenças:
- A palavra **“simples”** está destacada em **amarelo/vermelho**  
- A frase extra aparece com fundo **amarelado** na coluna correspondente

---

## 🧩 Principais Classes

| Classe | Descrição |
|:--|:--|
| `WinMergeSpringBootApplication` | Classe principal que inicializa o Spring Boot |
| `FileController` | Controlador responsável por receber os uploads, comparar os arquivos e gerar o HTML resultante |
| `index.html` | Tela inicial com formulário de upload |
| `analyze.html` | Tela de comparação com exibição lado a lado |
| `style.css` | Arquivo de estilo com realce de diferenças |

---

## 🎨 Destaque Visual (CSS)

As diferenças são destacadas por meio da classe `.diff`:

```css
.diff {
    background-color: #fff1a8;
    color: #b30000;
    font-weight: 600;
    border-radius: 3px;
    padding: 0 2px;
}
```

---

## 🚀 Possíveis Melhorias Futuras

- [ ] Adicionar **comparação por caractere** (modo detalhado)  
- [ ] Implementar **download do resultado** em HTML ou PDF  
- [ ] Permitir **comparação de arquivos maiores** (>10 MB) com paginação  
- [ ] Criar **testes unitários** com JUnit  
- [ ] Adicionar **tema escuro (dark mode)**  

---

## 👨‍💻 Autor

**Edilson Salvador Ricci**  
💼 Desenvolvedor Java / C / C++  
🌐 [GitHub](https://github.com/ESRicci)  
✉️ Contato: esricci26@gmail.com

---

## 📝 Licença

Este projeto é distribuído sob a licença **MIT**.  
Sinta-se livre para usar, modificar e distribuir com os devidos créditos.  

```
MIT License © 2025 Edilson Salvador Ricci
```
