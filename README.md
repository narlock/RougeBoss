# Rogue Boss
Simple "Boss Battle" simulator for building communities.

![Apache Maven](https://img.shields.io/badge/Apache%20Maven-C71A36?style=for-the-badge&logo=Apache%20Maven&logoColor=white)
![Spring Boot 3](https://img.shields.io/badge/spring_boot_3-%236DB33F.svg?style=for-the-badge&logo=spring-boot&logoColor=white)
![Java 17](https://img.shields.io/badge/java_17-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)

## Application Description

Rogue Boss is an application that implements mini "boss battles" for users to interact with a front end. A boss is chosen randomly from a list, given a type (see types). Users can attack the boss via an open-front end implementation provided by developers that utilize Rogue Boss. Rogue Boss acts as a back end application that can receive RESTful API requests to attack the boss while also storing boss data locally using a JSON format. Rogue Boss also implements a simple GUI that contains a simple animation and health bar indicating the current status of each events that are send via the API.

This application as it stands leaves choices for other developers on how they want to call the API. This application is not responsible for providing a front end interface for calling the API.

## GUI Screenshots
![RB1](./README%20Assets/RB1.png)
![RB2](./README%20Assets/RB2.png)

## Application Context
![Context Diagram](./README%20Assets/context.png)

Rogue Boss contains three different main components: the REST API, for frontend interfaces to interact with, the User Interface, for displaying events received from the REST API, and the JSON datastore, stored at __/home/Documents/narlock/RogueBoss/boss.json__ contains the information of the current boss. This is used to persist the data when the application starts and shuts down.

## API Specification

### GET `/rban`
Pings the Rogue Boss application. This should be utilized to ensure connection to the API is stable and that the GUI displays the correct boss information. When the application is started, the application will call this endpoint to ping the graphics panel.

**Response**
```
HTTP/1.1 200 OK
Content-Type: text/plain

Success
```
### POST `/rb`
Send an attack request. Request body should contain unique information with respect to the calling user. The current supported models are `TRINITY` and `ANT`.

**Request**
```json
POST /rb

{
    "id": "123",
    "name": "Trinity",
    "type": "DARK",
    "model": "TRINITY",
    "weapon": 1,
    "powerUp": 1,
    "exp": 2000
}
```

**Response**
```json
HTTP/1.1 200 OK
Content-Type: application/json

{
    "note": "Critical hit! 6 damage dealt.",
    "slain": false,
    "boss": {
        "level": 3,
        "bossType": "SLIME",
        "type": "DARK",
        "health": 1290,
        "damageList": [
            {
                "id": "123",
                "damage": 6
            }
        ],
        "name": "DARK SLIME LV3"
    }
}
```

The `damageList` attribute of the response will contain information of each user attacking the boss. The `damage` sub-attribute will add the new damage number indicated by the `note` field.

## Typing
This game has a simple typing system. Certain types are super/not very effective against one another.

- 🔥 **FIRE** is weak to **WATER**, but super effective against **EARTH**.
- 💧 **WATER** is weak to **EARTH**, but super effective against **FIRE**.
- 🪨 **EARTH** is weak to **FIRE**, but super effective against **WATER**.
- 🔮 **PSYCHIC** is weak to **LIGHT**, but super effective against **DARK**.
- 💫 **LIGHT** is weak to **DARK**, but super effective against **PSYCHIC**.
- 🌑 **DARK** is weak to **PSYCHIC**, but super effective against **LIGHT**.


## Future Improvements
- Unit Testing
- Add new character models
- Add new animations (utilizing `weapon` attribute)

## Artwork Usage
The artwork in narlock's Rogue Boss were created by __narlock__. Please ask for permission if you plan to use these assets for your own projects.
