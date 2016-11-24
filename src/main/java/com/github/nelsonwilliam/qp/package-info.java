/**
 * Este pacote contém todas as classes necessárias para a execução dos testes e
 * geração dos relatórios da Ordem de Serviço 2.
 *
 * {@link com.github.nelsonwilliam.qp.Calcular} permite a utilização do Parser
 * ({@link com.github.kyriosdata.parser}) para obter o valor de expressões dos
 * Testes. {@link com.github.nelsonwilliam.qp.Leitura} é responsável pela
 * obtenção das linhas do arquivo TXT contendo os testes, seja ele local ou
 * remoto. {@link com.github.nelsonwilliam.qp.Main} possui a lógica principal do
 * programa e checagem de erros nos parâmetros fornecidos.
 * {@link com.github.nelsonwilliam.qp.Relatorio} é responsável por chamar a
 * execução dos testes e gerar o arquivo relatório JSON ou HTML.
 * {@link com.github.nelsonwilliam.qp.Teste} guarda cada Teste e seus atributos
 * (expressão, variáveis, valor esperado, valor obtido), facilitando a
 * utilização da classe {@link com.github.nelsonwilliam.qp.Relatorio}.
 *
 * @see com.github.nelsonwilliam.qp.Calcular
 * @see com.github.nelsonwilliam.qp.Leitura
 * @see com.github.nelsonwilliam.qp.Main
 * @see com.github.nelsonwilliam.qp.Relatorio
 * @see com.github.nelsonwilliam.qp.Teste
 * @see com.github.kyriosdata.parser
 */
package com.github.nelsonwilliam.qp;
