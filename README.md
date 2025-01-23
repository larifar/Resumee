# RESUMEE: Resumos Inteligentes com IA
RESUMEE transforma suas anotações do Obsidian (ou arquivos Markdown) em resumos concisos de até uma página e gera 3 perguntas relevantes para otimizar seus estudos com o poder da IA Gemini.
#### Novas features
- Agendamento de revisões: sincronização com o Google Calendar para lembrar a revisão dos resumos em intervalos regulares (ex: 1, 7 e 21 dias).
## Tecnologias
- Java 17
- Google Cloud Vertex AI (com Gemini)
- Processamento de Markdown: java.nio.file (NIO)
- Compatível com Obsidian (requer apenas arquivos Markdown)
## Como posso utilizar:
- Ao clonar o projeto terão 4 passos básicos que irá precisar fazer:
### 1. Configuração dos Caminhos dos Arquivos:
Você precisará configurar dois caminhos: um para a pasta que contém seus arquivos Markdown originais e outro para onde os resumos serão salvos.
```
// Nos arquivos: ReadMd.java e VerifyFiles.java (caminho para as anotações)
private final String PATH_ANOTACOES = "C:\\Users\\eu\\Área de Trabalho\\Pasta Estudos";

// No arquivo: CreateMd.java (caminho para os resumos)
private final String PATH_RESUMOS = "C:\\Users\\eu\\Área de Trabalho\\Pasta Resumos";
```
### 2. Configuração da Conta Google Cloud e Autenticação
Para usar a IA Gemini, você precisa ter uma conta no Google Cloud e configurar um projeto. O guia a seguir explica o processo de autenticação e configuração das credenciais necessárias para acessar a API do Vertex AI: [documentação da API do Vertex AI](https://cloud.google.com/vertex-ai/generative-ai/docs/start/quickstarts/quickstart-multimodal?hl=pt-br#send-request-audio-video).
Também é necessário ativar a API do Google Calendar. Aqui está a [documentação API Google Calendar em Java](https://developers.google.com/calendar/api/quickstart/java?hl=pt-br).
### 3. Configuração do Projeto no código
No arquivo `RequestGemini.class`, você precisa inserir o ID do seu projeto do Google Cloud:
````java
private final String projectId = "meuprojeto-1234"; // Substitua pelo ID do seu projeto
````
Na pasta resources adicione a credencial do cliente Auth como um arquivo .json.
````java
-resources->client_secret.json // Substitua pela sua credencial, e não esqueça de mudar o nome do arquivo
````


## Como rodar o projeto
1. **Clone o repositório:**
    ```bash
    git clone https://github.com/larifar/Resumee.git
    cd Resumee
    ```
2. **Compile o projeto:**
    ```bash
    mvn compile
    ```
3. **Empacote o projeto:**
    ```
   mvn package
    ```
4. **Execute o JAR:**
   ```bash
    java -jar target/Resumee-1.0-SNAPSHOT-with-dependencies.jar
   ```

#### Observações
- Os resumos são gerados com base na última data de modificação dos arquivos. Se um arquivo for modificado após a geração do resumo, um novo resumo será criado.
#### Próximas features
- Integração com o Obsidian: Criar uma integração mais direta com o Obsidian

Obrigada, qualquer ajuda é bem-vinda.