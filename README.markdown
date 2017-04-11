# APITest

This module aims at testing all accessible method signatures (include name, modifiers, parameters, return type and exceptions) in each API

### How to develop
1. Using `ApiScraper` we can find all APIs (package, interface, class)
2. Create a junit class, register all public & protected constructors and methods
3. Using `compareConstructors()` and `compareMethods()` in [`ApiTestUtils`](https://github.com/GabrielXia/ApiTest/blob/master/src/main/java/org/terasology/testUtil/ApiTestUtils.java) to compare signatures
4. See example [`ComponentFieldUriTest`](https://github.com/GabrielXia/ApiTest/blob/master/src/test/java/org/terasology/engine/ComponentFieldUriTest.java)

### TODO
1. Finish a test for an Api package
2. Implement a `Scraper` to list all the API tested comparing to what exists
3. Find an automatic way to register all methods e.g. write all method signatures to a file when registering
4. Finish all tests for all the Api