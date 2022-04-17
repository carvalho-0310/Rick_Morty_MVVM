# Rick Morty MVVM

[![CircleCI](https://circleci.com/gh/carvalho-0310/Rick_Morty_MVVM/tree/main.svg?style=svg)](https://circleci.com/gh/carvalho-0310/Rick_Morty_MVVM/tree/main)

Aplicativo de exemplo para aprofundar meus conhecimentos, com foco em Android, sobre arquitetura, bibliotecas conhecidas no mercado e testes. Além disso, busco usar esse aplicativo para me ajudar a encontrar uma vaga de emprego como desenvolvedor JR.

## Arquitetura

O Aplicativo usa o [padrão MVVM](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93viewmodel) e está dividido em pacotes conforme mostrado abaixo:

<img src="https://github.com/carvalho-0310/Rick_Morty_MVVM/blob/organize_the_folders/modulos.jpeg" width="600"/>

* __UI__ - Contém todas as classes relacionadas à UI (Activities, Adapters, etc.) a ideia dessa camada é delegar lógica para a viewmodel facilitando os testes unitários e ela só interpreta as modelos (State e Action) que vem da viewmodel.

* __Presentation__ - Esta camada está usando a biblioteca [View Model](https://developer.android.com/topic/libraries/architecture/viewmodel) para manter o  estado da tela mesmo se ela for recriada, utilizando [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) para expor as modelos (State e Action) para camada de UI  e sendo responsavel pela logica da tela.
Há modelo State é responsável por cuidar do estado da tela 
Há modelo de Action é responsável por cuidar das ações da tela, para não manter a ação salva no LiveData implementei a MutableAction para limpar evitando de a ação ser chamada novamente na recriação da tela 

* __Repository__ - Serve para abstrair as fontes de dados e tem a responsabilidade de cuidar das regras de dados.

* __Data Remote__ - Responsável por fazer a comunicação de uma fonte de dados remota,
neste projeto estou utilizando Retrofit e Gson.

* __Data Local__ - Responsável por fazer a comunicação de uma fonte de dados local, neste projeto estou utilizando a [biblioteca Room](https://developer.android.com/topic/libraries/architecture/room).

## Aplicativo

Esse aplicativo foi baseado em [The Rick and Morty API](https://rickandmortyapi.com/) para poder simular requisições ao servidor.

Basicamente o aplicativo lista os personagens do universo Rick and Morty e ao clicar em um usuário exibe mais detalhes sobre ele, como pode ser visto no gif a seguir.

<img src="https://github.com/carvalho-0310/Rick_Morty_MVVM/blob/main/rick-and-morty-mvvm-1.gif" width="300"/>

Já no segundo gif vemos a paginação dos personagens e  depois o mesmo cenário porém no caso de usuário sem internet.

<img src="https://github.com/carvalho-0310/Rick_Morty_MVVM/blob/main/rick-and-morty-mvvm-2.gif" width="300"/>

No último gif, a minha intenção é mostrar o fluxo do erro de conexão dos dois cenários:
1 - Tentativa de pegar as informações.
2 - Fechamento do aplicativo

<img src="https://github.com/carvalho-0310/Rick_Morty_MVVM/blob/main/rick-and-morty-mvvm-3.gif" width="300"/>

## Estratégia de testes

Para fazer os testes estou usando o padrão [Given-When-Then](https://en.wikipedia.org/wiki/Given-When-Then) para manter uma organização nos testes e facilitar a compreensão. Comecei os testes nas camadas mais baixas usando MockWebServer para ter um ambiente de teste controlado.

## Bibliotecas

* AppCompat
* ConstraintLayout
* Recyclerview
* View Binding
* [Glide](https://github.com/bumptech/glide)
* [Retrofit](https://square.github.io/retrofit/)
* [RXjava](https://reactivex.io/documentation)
* [Gson](https://github.com/google/gson)
* [Jetpack Libraries](https://developer.android.com/jetpack/) (View Model, Lifecycle e LiveData)
* [Koin](https://github.com/InsertKoinIO/koin)
* JUnit
* MockWebServer

## Biografia

A seguir apresento as principais fontes de onde venho me baseando para criar esse projeto:

* https://developer.android.com/
* https://medium.com/
* https://stackoverflow.com/
* https://refactoring.guru/design-patterns
* https://www.devmedia.com.br/
* https://www.youtube.com/c/ThiengoCalopsita
* https://www.youtube.com/c/LucasMontano
* https://www.youtube.com/c/DevVaiLonge
* https://www.youtube.com/c/DouglasMotta
* https://www.youtube.com/c/KaiqueOcanha
* [Palestra do Jonathan Gerrish](https://www.youtube.com/watch?v=VJi2vmaQe6w)
* [Livro Kotlin em Ação](https://g.co/kgs/rfE3vZ)

## Considerações finais

Como esse projeto é apenas para fins didáticos e está em processo de evolução que você pode acompanhar os próximos passos [aqui](https://github.com/carvalho-0310/Rick_Morty_MVVM/projects/1), sinta-se à vontade em me contatar se você tiver alguma dica para me passar ou quiser conversar sobre o código, ficaria feliz em trocar conhecimento com você.



