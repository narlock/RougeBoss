---
layout: page
title: Install Guide
permalink: /install/
---
<time>Aug 29, 2024</time>

# Local Installation
Rogue Boss is an application that opens a web server that takes in HTTP requests to "attack" the Boss. This can be used for different users in a community to have a fun, simple, and interactive game.

## Running the application
Rogue Boss is powered by [Java](https://java.com), and is required in order to run Rogue Boss. Rogue Boss is specifically built using Java 17, so you will need to install Java 17 or higher to run.

### Check Java installation

To check your version of Java, enter the following command in your terminal:
```sh
java --version
```

If your Java version says `17` as the major release version, then you should be able to run the application. An example from [OpenJDK](https://openjdk.org/) is provided below:
```
openjdk 17.0.8 2024-01-14
OpenJDK Runtime Environment (build 17.0.8+10)
OpenJDK 64-Bit Server VM (build 17.0.8+10, mixed mode, sharing)
```

### Download Latest Release
Once you have verified your installation of Java, download the latest `jar` file from the [releases](https://github.com/narlock/RogueBoss/releases) page.

When the latest `jar` file is downloaded, simply open a terminal in the directory that contains the `jar` file and run the following command:
```sh
java -jar rogue-1.0.0.jar
```
Note: You will need to replace the `rogue-1.0.0.jar` with the name of the `jar` file in the latest release. It should be in a similar format to this.

[Spring](https://spring.io/projects/spring-boot) will launch and begin to run the application on port `8081`. You will also see the graphical user interface appear.

### Functionally testing the application

With the application running on your local machine. You can functionally test the endpoint. If you have [curl](https://curl.se/) installed, you can use the following command:
```sh
curl --location 'http://localhost:8081/rb' \
--header 'Content-Type: application/json' \
--data '{
    "id": "123",
    "name": "Ant",
    "type": "EARTH",
    "model": "ANT",
    "weapon": 1,
    "powerUp": 1,
    "exp": 1
}'
```
When this command is executed, you will get a JSON payload back detailing the Rogue Boss event. The event will get sent to the event queue and will be processed on the graphical user interface to provide a visual animation.

Data for the boss will be stored in `Documents/narlock/RogueBoss/boss.json`. This file will persist the data of the Rogue Boss application when it is stopped and restarted.