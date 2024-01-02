# Projeto_LP2-22207061-22208623

Projeto no âmbito da cadeira de Linguagens de Programação II , jogo de "xadrez" programado em Java e Kotlin.

 Criadores:
 Tomás Nave /
 André Jesus

 Video explicativo:

 https://youtu.be/GS9t6hF_n4s

 Imagem Jogo:

![printJogo](https://github.com/AndreJesus-22207061/Projeto_LP2-22207061-22208623/assets/127102331/57a73c25-3150-4a2e-b122-b7125460685a)

Peça Criada Sapo Encantado:

A peça que criámos é o Sapo Encantado, e quando é capturada tem o valor de 5 pontos, a movimentação desta peça é parecida a de um sapo, isto é pode "saltar" por cima de peças da sua equipa, caso estejam entre o caminho dela, o Sapo Encantado anda num máximo de duas casas em qualquer direção, vertical, horizontal e diagonal, e como referimos esta pode saltar por cima apenas de peças da mesma equipa caso estas estejam no caminho, se tentar saltar por cima de uma peça da equipa contrária o movimento é inválido.


Imagem Créditos:

![image](https://github.com/AndreJesus-22207061/Projeto_LP2-22207061-22208623/assets/127041806/f006670f-fb11-4cfa-be01-331c83516390)


Diagrama UML:


![UML drawio (2)](https://github.com/AndreJesus-22207061/Projeto_LP2-22207061-22208623/assets/127102331/2b31ee8f-7d2e-4ebd-8b99-991615f35940)




Modelação do Projeto:

No nosso código temos a classe GameManager que contém as funções principais do nosso programa, onde temos o nosso tabuleiro também foi uma classe criada por nós. Esta classe tabuleiro contém tudo o que precisamos para guardar informações sobre o jogo.
Dentro desta classe Tabuleiro temos um array bidimensional que contém a localização das peças e as respetivas peças. Temos ainda 3 listas de peças, uma que contem todas as peças do jogo muito utilizada para se fazer as pesquisas para as estatisticas onde temos de percorrer todas as pecas do jogo, e temos as outras duas listas cada uma contendo apenas as peças de cada equipa de maneira a podermos obter informações mais relativas a cada equipa. Depois ainda dentro do tabuleiro temos uma lista de contadores, essa lista tem um contador para cada equipa em jogo onde temos informações sobre as jogadas válidas e inválidas. Temos tambem no tabuleiro um contadorRondas que tem informações mais gerais sobre as rondas do jogo. Por fim temos no tabuleiro uma stack com o histórico das jogadas, estas jogadas são uma classe criada por nós com todas as informações necessárias para realizar o undo. A nossa classe peça é uma classe pai que tem vários filhos que sao todos os tipos de peça deste jogo onde cada filho tem o seu tipo de movimento e as suas caracteristicas próprias. Por fim dentro da classe Peca temos um CountJogadas que tem as informações relativas a jogadas válidas e inválidas dessa peça, ou seja no nosso modelo temos um CountJogadas para cada equipa e temos um countJoagadas para cada peca em jogo tendo assim acesso às jogadas inválidas e válidas de cada peça e de cada equipa como um todo. Para implementar a função getints criámos uma classe comparable, a que gostamos de chamar sugestão com todas as informações necessárias de uma sugestão de jogada. Por fim temos um ficheiro chamado Statisticks.kt programado em kotlin que contém as funções principais das estatisticas, dentro de cada uma dessas funções chamamos um método "query" que está na classe Tabuleiro que devolve as informações necessárias de estatisticas para cada uma das 5 funções.
