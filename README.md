# AppGate Test

## Introduction

This repository contains the source code of the app requested by AppGate. This application lets a user log in and save the login information even if they close the app

## Arquitecture

The current project followed a clean architecture approach with MVVM pattern on the presentation layer, use cases for the domain layer and a repository pattern for the data layer. This decision allows that each feature is independent from each other, so if you need to reuse some logic from one feature into another you just have to inject the use case with the logic into the view model from the other feature. Also, following the repository pattern on the data layer allows us to always relay on the same data and maintain only one source of truth. Additionally, the separation of concerns makes it really easy to introduce changes on each of the layers as long as they maintain the contracts, so you can change the way the current TimeZone is fetched without changing the view layer or you can change the way you get the Login information and perform some additional validation on the use case.

![Clean arquitecture diagram](https://devexperto.com/wp-content/uploads/2018/10/clean-architecture-graph.png)

## Features implemented
* Required features:
    * Log a user in and display all the login results historically.
    * Save this information per user logged in
* Technical features:
    * Dependency injection
    * Multilayered architecture

## Opportunities for Improvement
* Use Retrofit which is the industry standard for consuming API requests in a save and fast way.
* Although I'm using Java's CompletableFuture, I think Kotlin Coroutines or RxJava would be a better and faster way to use phone resources in order to handle multithreading.
* Add unit tests, due to lack of time and family issues this was not included in the base project
* Use Compose to build UI, it's faster to render and maintain by reusing components all over the project

## How to run
Clone the project and run the app normally using Android Studio.
