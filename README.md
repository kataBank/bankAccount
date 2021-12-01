# Bank Account

 Bank Account est une application  bancaire permettant à un client d'éffectuer les opérations suivantes dans son compte Bancaire:
 - Dépot
 - Retrait
 - Impression d'historique

## Pour commencer

L'application est développée avec un front sous Angular 9 et un backend Spring boot 2.6. .
La partie sécurité est gérée par Spring sécurity, qui assure l'authentification  et l'authorisation d'accès aux ressources.
Nous avons utilisé H2 comme base de données.

NB: Pour des besoins de simplification, Angular (Front) est buildé et chargé comme ressource static coté Backend (Spring Boot). 
 Ce qui permet après exécution du back, à l'application via l'url **localhost:8080:login**

### Pré-requis (environnement)

- Java 8
- Angular ClI 9.1.8
- Node 12.18.3
- Gradle 6.9.1
- Spring boot 
- Spring Security
- H2
- IntelliJ
- Vscode

### Dependence

## Démarrage backend

- ./gradlew bootRun

## Démarrage front

-  ng serve

## Accès à l'application coté backend ( backend + Front ) 

- localhost:8080/login

## Accès à l'application coté front

- localhost:4200/login

