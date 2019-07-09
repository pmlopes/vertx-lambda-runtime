# Overview


This example contains 2 lambdas:

1. [ALBLambda.java](src/main/java/com/example/aws/lambdas/ALBLambda.java)
2. [APIGWLambda.java](src/main/java/com/example/aws/lambdas/APIGWLambda.java)

A lambda is just a java class implementing the `Lambda` interface. The request
comes as a `JsonObject` and it should reply back with a `JsonObject`.

In order to facilitate the work with Lambda events, some `POJO`s are available
to parse the JSON to typed java types. 
