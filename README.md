[![Open Source Love](https://badges.frapsoft.com/os/v1/open-source.svg?v=103)](https://github.com/ellerbrock/open-source-badges/)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![Build Status](https://travis-ci.org/williamsgomess/apurador-de-meta.svg?branch=master)](https://travis-ci.org/williamsgomess/apurador-de-meta)
[![Build Status](https://semaphoreci.com/api/v1/williamsgomess/apurador-de-meta/branches/master/badge.svg)](https://semaphoreci.com/williamsgomess/apurador-de-meta)
![GitHub package version](https://img.shields.io/github/package-json/v/badges/shields.svg)


# Apurador de Metas

Apresenta a meta de um determinado vendedor

## Começando

Para abrir e esta aplicação em seu ambiente, você precisa ter (obrigatoriamente) uma JDK8 ou JDK9 instalada na sua máquina.
Além do maven, IDE Eclipse (recomendado) e JavaFX SceneBuilder junto com a biblioteca jFoenix importada nele.
* [JAVA-JDK](http://www.oracle.com/technetwork/pt/java/javase/downloads/index.html) - Download JDK8.
* [SceneBuilder](http://www.oracle.com/technetwork/java/javafxscenebuilder-1x-archive-2199384.html) - Download SceneBuilder
* [JFoenix](http://www.jfoenix.com/) - Download biblioteca JFoenix

Feito esse passos, crie uma pasta na sua raiz C:/ como o nome apurador-de-metas, e em seguida crie um arquivo .txt como o nome usuarios.txt.

Após criar o diretório e importar esse projeto em sua IDE(preferência eclipse) enquantos as depedências estão sendo baixadas vá ao arquivo db.properties e configure seu acesso ao banco de dados(seguindo o padrão dos SELECT's das classes DAOs.

Pronto! Pode usar a vontade, e modificar.

### Pré-requisitos

Tem Java JDK instalado, além do SceneBuilde e a IDE Eclipse

[Como importar jFoenix no SceneBuilder](http://www.jfoenix.com/documentation.html#Setup)

No Maven
```
<dependency>
   <groupId>com.jfoenix</groupId>
   <artifactId>jfoenix</artifactId>
   <version>9.0.4</version>
</dependency>
```
## Desenvolvimento

Para rodar ativamente em sua empresa, você deve revisar as classe model do pacote ```br.com.centrocar.apurador.modelo``` e atualizar conforme suas necessidades, além de modifiacar suas alterações nos demais pacotes.

## Construído com:

* [JavaSE](https://docs.oracle.com/javase/8/docs/api/overview-summary.html) - Linguagem usada
* [Maven](https://maven.apache.org/) - Gerenciador de depend~encias usado
* [JavaFX](http://www.oracle.com/technetwork/pt/java/javafx/overview/index.html) - Plataforma GUI usada
* [JFoenix](http://www.jfoenix.com/) - Biblioteca GUI usada
* [SceneBuilder](http://www.oracle.com/technetwork/java/javafxscenebuilder-1x-archive-2199384.html) - Editor GUI - usada
* [Eclpise](http://www.eclipse.org/downloads/) - IDE usada

## Versão

Usado o [SemVer](http://semver.org/) para versionamento. Para avaliar as versões, veja as [tags no repositório](https://github.com/williamsgomess/apurador-de-meta/tags). 

## Autores

* **Williams Gomes** - *Trabalho inicial e final* - [williamsgomess](https://github.com/williamsgomess)

Veja quem [contribuiu](https://github.com/williamsgomess/apurador-de-meta/graphs/contributors) com esse projeto.

## License

Este projeto está licenciado sob a licença Apache 2.0 - Veja o arquivo [LICENSE.md](LICENSE.md) para mais detalhes.

## Agradecimentos

* Deus
* Familia
* Noiva
* Amigos

