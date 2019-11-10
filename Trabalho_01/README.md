# Desenvolvimento de Software para Persistência 2019.2

No seu trabalho, você deverá executar as seguintes atividades:
1. Escreva uma aplicação (em JAVA) para ler o(s) arquivo(s) CSV dos dados escolhidos e grave-os
em um arquivo XML (arquivo destino) utilizando a API DOM. Seu XML não precisa usar todos os
dados abertos. Adicione valores como atributos, se achar mais conveniente do que uma tag interna
(você deve avaliar se faz mais sentido como uma tag interna ou como atributo). Se forem vários
arquivos CSV com tabelas relacionadas (chaves estrangeiras, relações tem-um, é-um), a árvore do
seu XML será mais complexa, com mais nós internos. Nesse caso, seu trabalho será melhor
avaliado.

2. Elabore arquivos DTD e XML Schema que validem o XML gerado. Você pode testar suas
validações com o programa xmllint, no terminal do Linux (também é possível instalá-lo no
Windows, mas em muitas versões do Linux ele já está instalado). Também é possível testar a
validação em http://xmlvalidator.new-studio.org/.

3. Escreva uma aplicação (em JAVA) que lê o XML acima utilizando a API SAX. Você deve ler os
dados do XML em objetos em memória. O resultado da sua aplicação deve ser a persistência desses
objetos em um arquivo JSON.
