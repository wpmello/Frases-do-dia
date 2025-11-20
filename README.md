# Frases do Dia

Um aplicativo Android para exibir frases motivacionais diariamente, permitindo que os usuários curtam e salvem suas frases favoritas.  

O app foi criado com foco em boas práticas de desenvolvimento, arquitetura moderna e escalabilidade, utilizando tecnologias recentes do ecossistema Android.

---

## 📱 Funcionalidades

- Exibição de uma frase diária aleatória.
- Curtir e favoritar frases.
- Visualizar uma lista de frases favoritas em uma tela dedicada.
- Persistência local usando Room.
- Atualização de frases via Firebase Remote Config.
- UI moderna desenvolvida em **Jetpack Compose**.
- Navegação fluida entre telas com **Jetpack Navigation**.

---

## 🏗 Arquitetura

O app segue o padrão **MVI (Model-View-Intent)**, garantindo:

- **ViewModel** para gerenciar o estado da UI.
- **Coroutines** e **Flow** para gerenciamento assíncrono e reativo de dados.
- **Hilt** para injeção de dependências, facilitando testes e modularidade.

---

## 🧰 Tecnologias Utilizadas

- Kotlin
- Jetpack Compose
- Jetpack Navigation
- ViewModel + LiveData/StateFlow
- Room Database
- Coroutines + Flow
- Firebase Remote Config
- Hilt (Injeção de Dependência)
- Material Design Components

---
## 📷 Screenshots

<table>
  <tr>
    <td><img src="Tela Inicial.jpeg" width="250"/></td>
    <td><img src="Tela de Favoritas.jpeg" width="250"/></td>
    <td><img src="Tela Inicial - 2.jpeg" width="250"/></td>
  </tr>
</table>
