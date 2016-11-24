# Ordem de serviço 2
Aula 12 de Construção de Software (ES, UFG, 2016-2).

## Requisitos
Este projeto foi desenvolvido para testar a qualidade do [Projeto Parser](https://github.com/kyriosdata/parser).

O teste de qualidade é feito através de arquivos de textos que seguem um padrão específico. Tais arquivos podem estar no próprio computador (local) ou em servidores/outras máquinas (remoto). Cada linha do arquivo deve possuir uma expressão matemática, o valor de suas variáveis (caso não tenha valores, as variáveis são assumidas como 0f) e o valor esperado dessa expressão com estas variáveis ([exemplo](http://lennusoft.16mb.com/open/testes.txt)).

Este projeto deverá utilizar o Parser para executar a expressão (com as variáveis fornecidas) e verificar se o valor obtido é de fato o valor esperado. Além disso, este projeto deve gerar um relatório (em formato JSON ou HTML) contendo – além de cada teste e seu resultado – o tempo total de execução dos tetes, o tempo médio de cada teste e a quantidade de memória empregada pela JVM durante apenas a execução do Parser.

Os requisitos detalhados podem ser encontrados [aqui](https://docs.google.com/document/d/1pVUQQdomKFIbkS92b1oGAVmAWpreywt0ic2LXgAhP70/edit#).

## Utilização
Após a compilação do projeto em um arquivo qp.jar, sua utilização segue o padrão:
```
java -jar qp.jar "Local do Arquivo"
```
O "Local do Arquivo" deve ser substituído pelo diretório de algum arquivo de testes .txt no próprio computador (ex.: C:\Users\Lana\Testes.txt) ou em algum endereço remoto (ex.: http://lennusoft.16mb.com/open/testes.txt). Em caso de arquivos remotos, o endereço deve obrigatóriamente deve começar com "http".

Este comando gera, por padrão, um arquivo relatório no formato JSON no mesmo diretório em que o programa qp.jar localiza-se (ou seja, não é no local do arquivo de testes). Caso queira um relatório no formato HTML, basta adicionar o parâmetro "-h" ao final do comando:
```
java -jar qp.jar "Local do Arquivo" -h
```
Desta forma, será gerado um relatório HTML (legível por humanos e browsers) no diretório do programa qp.jar.

## Lembretes
- `mvn package` para gerar o arquivo qp.jar na pasta target.
