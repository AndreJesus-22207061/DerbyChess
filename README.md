# Projeto_LP2-22207061-22208623

Projeto no âmbito da cadeira de Linguagens de Programação II , jogo de "xadrez" programado em Java e Kotlin.

 Criadores:
 Tomás Nave /
 André Jesus

 Imagem Jogo:

![printJogo](https://github.com/AndreJesus-22207061/Projeto_LP2-22207061-22208623/assets/127102331/57a73c25-3150-4a2e-b122-b7125460685a)

Peça Criada Sapo Encantado:

A peça que criámos é o Sapo Encantado, e quando é capturada tem o valor de 5 pontos, a movimentação desta peça é parecida a de um sapo, isto é pode "saltar" por cima de peças da sua equipa, caso estejam entre o caminho dela, o Sapo Encantado anda num máximo de duas casas em qualquer direção, vertical, horizontal e diagonal, e como referimos esta pode saltar por cima apenas de peças da mesma equipa caso estas estejam no caminho, se tentar saltar por cima de uma peça da equipa contrária o movimento é inválido.


Imagem Créditos:

![image](https://github.com/AndreJesus-22207061/Projeto_LP2-22207061-22208623/assets/127041806/f006670f-fb11-4cfa-be01-331c83516390)


Diagrama UML:




![UML drawio](https://github.com/AndreJesus-22207061/Projeto_LP2-22207061-22208623/assets/127102331/b854af00-44d2-4550-aa30-35e5df21a493)

Modelação do Projeto:

No nosso codigo temos a classe gameManager que contem as funcoes principais do nosso programa , onde temos o nosso tabuleiro também uma classe criada por nos. Esta classe tabuleiro contem tudo o que precisamos para guardar informacoes sobre o jogo.
Dentro desta classe tabuleiro temos um array Bidimensional que contem a localizacao das pecas e as respetivas pecas. Temos também 3 listas de pecas, uma que contem todas as pecas do jogo muito utilizada para se fazer as pesquisas para as estatisticas onde temos de percorrer todas as pecas do jogo , e temos as outras duas listas cada uma contendo apenas as pecas de cada equipa de maneira a podermos obter informacoes mais relativas apenas a cada equipa. Depois ainda dentro do tabuleiro temos uma lista de contadores, essa lista tendo um contador para cada equipa em jogo onde temos informacoes sobre as jogadas validas e invalidas. Temos tambem no tabuleiro  um contadorRondas que tem informacoes mais gerais sobre as rondas do jogo. Por fim temos no tabuleiro uma stack com o historico das jogadas, estas jogadas sao uma classe criada por nos com todas as informacoes necessarias para repor o jogo uma ronda para tras. A nossa classe peca é uma classe pai onde temos varios filhos desta classe que sao todos os tipos de peca deste jogo onde cada filho tem o seu tipo de movimento , as suas caracteristicas proprias. Por fim dentro da classe peca temos um CountJogadas que tem as informacoes relativas a jogadas validas e invalidas dessa peca , ou seja no nosso modelo temos um CountJogadas para cada equipa e temos um countJoagadas para cada peca em jogo tendo assim acesso as jogadas Invalidas e validas de cada peca e de cada equipa como um todo. Para implementar a funcao getints criamos uma classe comparable , a que gostamos de chamar suguestao com todas as informacoes necessarias de uma suguestao de jogada. Por fim temos um ficheiro chamada Statisticks.kt em linguagem kotlin que contem as funcoes principais das estatisticas , dentro de cada uma dessas funcoes chamamos um metodo "query" que esta na classe tabuleiro que devolve as informacoes necessarias de estatisticas para cada uma das 5 funcoes.
