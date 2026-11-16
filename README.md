# 🎯 QuizMaster

Aplicativo Android de perguntas e respostas desenvolvido em *Kotlin* com *Jetpack Compose*. O QuizMaster permite jogar quizzes, visualizar resultados e gerenciar um banco de perguntas por meio de uma área administrativa.

> *Projeto acadêmico em grupo.* Este README descreve as funcionalidades identificadas e testadas até o momento. A equipe deve revisar as atribuições dos integrantes e confirmar as funcionalidades ainda não validadas.

## 📱 Funcionalidades

### Área do jogador
- Tela inicial com acesso à área do jogador e à área administrativa.
- Menu de navegação do jogador.
- Quiz com perguntas e alternativas cadastradas no aplicativo.
- Cálculo de acertos e exibição da pontuação ao finalizar.
- Telas de *Ranking* e *Histórico* presentes na navegação do projeto.

> *Observação:* a persistência e o comportamento completo de Ranking e Histórico ainda precisam ser confirmados pela equipe.

### Área administrativa
- Tela de login *simulado* para demonstração acadêmica.
- Acesso de teste: usuário admin e senha 1234.
- Validação de credenciais com mensagem de erro para dados incorretos.
- Tela administrativa com acesso ao gerenciamento de perguntas.
- Cadastro e exclusão de perguntas.
- Armazenamento local das perguntas com Room.

> ⚠️ *Atenção:* as credenciais são fixas no código e o login não oferece segurança real. O Firebase Authentication não foi configurado para essa demonstração.

## 🛠️ Tecnologias utilizadas

- *Kotlin* — linguagem principal.
- *Jetpack Compose* — construção das interfaces.
- *Navigation Compose* — navegação entre telas.
- *Room* — banco de dados local para persistência de perguntas.
- *ViewModel* — organização do estado e da lógica do quiz.
- *Arquitetura MVVM* — separação entre dados, interface e lógica de apresentação.
- *Android Studio* — ambiente de desenvolvimento.
- *Git e GitHub* — versionamento e colaboração.

## 🗂️ Organização do código

text
app/src/main/java/com/example/quizmaster/
├── MainActivity.kt
├── data/
│   └── local/
│       ├── BancoDeDadosProvider.kt
│       ├── Pergunta.kt
│       ├── PerguntaDao.kt
│       └── QuizDatabase.kt
├── ui/
│   ├── navigation/
│   │   ├── BarraNavegacao.kt
│   │   ├── Navegacao.kt
│   │   └── Rotas.kt
│   ├── screens/
│   │   ├── TelaInicial.kt
│   │   ├── TelaMenu.kt
│   │   ├── TelaQuiz.kt
│   │   ├── TelaResultado.kt
│   │   ├── TelaRanking.kt
│   │   ├── TelaHistorico.kt
│   │   ├── TelaAdmin.kt
│   │   ├── TelaGerenciarPerguntas.kt
│   │   └── TelaLoginAdmin.kt
│   └── theme/
└── viewmodel/
    └── QuizViewModel.kt


A localização exata de QuizViewModel.kt deve ser conferida na versão final do repositório.

## ▶️ Como executar

1. Clone o repositório do projeto:

   bash
   git clone https://github.com/Vitor-vt/QuizMaster.git
   

2. Abra o projeto no *Android Studio*. Se houver mais de uma pasta de projeto Android no repositório, selecione a que contém o módulo app e os arquivos Gradle correspondentes.
3. Aguarde a sincronização do Gradle.
4. Inicie um emulador Android ou conecte um dispositivo compatível.
5. Selecione a configuração app e clique em *Run ▶️*.

*Ambiente usado nos testes:* emulador Pixel 9 Pro com Android 15 (API 35).

## 🧪 Testes realizados

- [x] Compilação e execução do aplicativo no emulador.
- [x] Navegação entre as telas principais.
- [x] Cadastro de perguntas.
- [x] Persistência das perguntas após fechar e reabrir o aplicativo.
- [x] Exclusão de perguntas de teste.
- [x] Exibição de perguntas cadastradas no quiz.
- [x] Cálculo e exibição da pontuação.
- [x] Rejeição de credenciais administrativas incorretas.
- [x] Acesso administrativo com credenciais simuladas corretas.
- [x] Revisão final das telas de Ranking e Histórico.
- [x] Revisão dos avisos da análise estática do Android Studio.

## 👥 Equipe e contribuições

### Guilherme — Banco de dados e área administrativa
- Implementação do login administrativo simulado.
- Integração e testes do gerenciamento de perguntas, do Room e do fluxo do quiz.
- Cadastro e exclusão de perguntas e verificação da persistência local.
- Testes do aplicativo no emulador Android.
- Preparação do Pull Request #2.

### Rick — Lógica do Quiz e ViewModels
- Implementação da lógica de apresentação das perguntas e alternativas.
- Controle das respostas selecionadas pelo jogador.
- Desenvolvimento da lógica de pontuação.
- Gerenciamento do estado do quiz por meio dos ViewModels.
- Integração entre a lógica do quiz e as telas do aplicativo.

### Rafael — Banco de dados Room
- Responsabilidade originalmente associada ao banco de dados Room.
- A implementação final também recebeu contribuições de Guilherme.
- *Confirmar com a equipe a divisão exata das tarefas.*

### Vitor-vt — Repositório, telas e navegação
- Administração do repositório GitHub do projeto.
- Há um commit de implementação de telas e navegação associado a essa conta.

## 🔀 Colaboração e integração

O trabalho de banco de dados, gerenciamento de perguntas e login simulado foi enviado à branch:

feature/firebase-admin-guilherme

Pull Request para revisão e integração à branch principal:

**[PR #2 — Room, gerenciamento de perguntas e login simulado](https://github.com/Vitor-vt/QuizMaster/pull/2)**

A abertura do Pull Request *não significa que ele já foi mesclado*. O líder deve revisar as alterações e realizar a integração quando apropriado.

## 📌 Observações finais

O QuizMaster foi desenvolvido com foco na prática de desenvolvimento Android, organização de telas, navegação, persistência local e colaboração em equipe. O login administrativo é intencionalmente simulado, conforme a orientação informada para o trabalho acadêmico.

---
