# Gestion de Capteurs/Actionneurs – Spring Boot & Node-RED

Ce projet propose un webservice centralisé pour la gestion de capteurs et actionneurs, développé avec **Spring Boot** (Java 21).

## Prérequis

- **Java 21** installé
- **Docker** et **Docker Compose** installés

## Lancement du projet


1. **Lancer les services avec Docker Compose**  
   Depuis le dossier contenant le fichier `docker-compose.yml` :
   ```sh
   docker compose -f docker-compose.yml up -d --build
   ```

   Cela démarre tous les conteneurs Capteurs, Actionneur, Orchestrateur et EurekaServeur qui est accessible sur [http://localhost:8761](http://localhost:8761).

## Structure du projet

- `src/main/java/epita/conception/projet/model/` : Modèles de données (Sensor, Value, etc.)
- `src/main/java/epita/conception/projet/repository/` : Accès aux données (MongoRepository)
- `src/main/java/epita/conception/projet/controller/` : Contrôleurs REST
- `nodeRed/docker-compose.yml` : Déploiement du conteneur Node-RED
