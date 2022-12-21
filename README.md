# SampleSpaceApp

Simple app that fetches data from https://github.com/r-spacex/SpaceX-API. I focused on app architecture and not at all on app's design.

Compose is used for the UI layer, Retrofit with Gson is used for network layer and Room persistence library is used to get or save data to the database.

Dependency injection is handled by Koin.

App is divided to several modules.
* app main module - In this module we have just some main components like MainActivity and MyApplication class. Some of the code could be moved to another module (such as coreUi) but for simplicity I left it in this module.
* spaceData - Data layer for managing data in database and over the network. SpaceRepository is used as Single source of truth here. Could be also splitted in two different modules or it could be splitted to multiple feature modules but again or simplicity it is kept this way.
* spaceLaunch - Contains logic for displaying data about Space-X past launches (list and detail).
* spaceNavigation - Plain Kotlin module that contains components used for navigation in the app. NavigationManager is used throughout the app for handling navigation event, so the NavigationController does not have to passed all around the app.

##TODO
* build.gradle files to kts
* dependencies and versions refactoring
* Another screens
* writing data over the network
* unit and integration tests
* image loading and processing
