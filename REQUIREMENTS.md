# Projeto MapFood

## Descrição

Para utilizar a metodologia CBL - Challenge Based Learning, os participantes do programa executarão um projeto real baseado nos aprendizados que recebem dos tutores, mentores e equipes da Code:Nation e da Movile. Para isso, os selecionados serão divididos em squads para execução do projeto MapFood.

O objetivo do squad é fornecer autonomia para que cada equipe tome decisões e que cada integrante descubra seu papel dentro do grupo, tornando-os aptos a resolver problemas reais por conta própria.

## Objetivo:

O objetivo do projeto é criar uma API para organizar os pedidos e gerar os melhores trajetos de entrega do iFood.

## Contextualização

Utilizando um conjunto de dados da plataforma iFood, contendo informações como estabelecimentos conveniados, suas localizações e produtos, os participantes deverão construir uma API para gerenciar essas informações, além de uma base com os motoristas que farão as entregas e respectivas listas de pedidos (contando com clientes e localização). A API deverá apresentar o melhor trajeto e também poderá trazer outras informações (a critério das squads), como gasto de cada estabelecimento com a quilometragem rodada, tempo de entrega, etc.

Ao final do programa, além de apresentar a API criada, cada squad deverá expor quais são os pontos de melhoria e quais seriam os próximos passos no projeto, caso fossem seguir adiante. 


## Requisitos técnicos obrigatórios

Para o projeto MapFood, é necessário que as squads se utilizem dos aprendizados repassados pelo programa AceleraDev, portanto a solução deve ser construída de acordo com os seguintes requisitos:

- Banco de dados;
- Desenvolvimento do backend e APIs com Java Spring Boot;
- Testes unitários são um bônus importante

## Definições do sistema MapFood

- Um motoboy pode levar no máximo 5 pedidos por entrega independente do número de itens do pedido;
- O tempo de preparação de cada pedido é 10 minutos;
- Consumo motocicleta: 42km/L
- Entende-se por pedido como uma solicitação de entrega feita por um usuário, sendo que o mesmo pode conter diversos itens (cachorro quente, batata frita, refrigerante, etc);
- Para o tempo de entrega, é importante considerar os seguintes parâmetros:
	- Deslocamento do motoboy até o estabelecimento;
	- Deslocamento do motoboy até a entrega do pedido ao cliente.

## Casos práticos do sistema

Para facilitar o entendimento da API que deverá ser construída, segue abaixo alguns casos práticos de possíveis interações que o sistema pode conter:

**Dado que** um consumidor selecionou um cachorro quente no estabelecimento X
**Quando** o pedido for realizado
**Então** deve-se identificar no sistema qual motoboy deve ser selecionado, qual o respectivo trajeto e informações adicionais do trajeto (ex: quilometragem, tempo de entrega…);

**Dado que** vários pedidos em localizações próximas foram solicitados em um mesmo estabelecimento e em horários próximos
**Quando** o sistema for selecionar o motoboy e respectiva rota
**Então** o mesmo pode levar em consideração a proximidade desses pedidos para utilizar o mesmo motoboy em tais entregas.

**Dado que** um restaurante deseje saber o tempo/quilometragem das entregas do seu estabelecimento
**Quando** o restaurante solicitar tais dados de determinada data
**Então** o sistema poderá gerar um relatório para o mesmo.

## Informações adicionais

É papel do squad definir quais atributos serão levados em consideração para definir o melhor trajeto. Alguns exemplos são: quilometragem, tempo de entrega, capacidade de buscar e entregar múltiplos pedidos em uma única rota, etc.

Com exceção dos requisitos técnicos obrigatórios, as funcionalidades e seus atributos podem ser alterados livremente por cada squad, desde que as alterações sejam justificáveis. Exemplos: em tempo de desenvolvimento, nova ideia que facilite o uso pelo usuário, melhorias na geração das rotas de entrega, etc.

É necessário que apenas uma pessoa do squad faça a submissão dos códigos para que os mentores possam fazer a avaliação.
