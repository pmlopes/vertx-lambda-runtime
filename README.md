# vertx lambda

## Run native Lambda's with Vert.x

*Disclaimer - This project should be considered a POC and has not been tested or verified for production use.
If you decided to run this on production systems you do so at your own risk.*

## Building this Runtime

This runtime should give you all the boilerplate code for:

* AWS Lambda
* Knative
* OpenFaaS

## Compile the Runtime Classes

```
$ mvn package
```

## Writing your lambda

Create a project and add the dependency:

```xml
<dependency>
  <groupId>xyz.jetdrone</groupId>
  <artifactId>vertx.lambda</artifactId>
  <version>LATEST</version>
</dependency>
```

Create a lambda as:

```java
package com.example;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;
import xyz.jetdrone.vertx.lambda.Lambda;

public class MyLambda implements Lambda<Buffer> {
  @Override
  public void handle(Message<Buffer> event) {
    System.out.println("HEADERS: " + event.headers());
    System.out.println("BODY: " + event.body());

    // Here your business logic...

    event.reply("OK");
  }
}
```

And register your class in the service loader `src/main/resources/META-INF/services/xyz.jetdrone.vertx.lambda.Lambda`:

```
# List all available Lambdas here
com.example.knativelambda.MyLambda
```

## Build

### AWS Lambda

In your `pom.xml` add the plugins:

```xml
<build>
  <plugins>
    <plugin>
      <groupId>com.oracle.substratevm</groupId>
      <artifactId>native-image-maven-plugin</artifactId>
      <version>${graal.version}</version>
      <executions>
        <execution>
          <goals>
            <goal>native-image</goal>
          </goals>
          <phase>package</phase>
        </execution>
      </executions>
      <configuration>
        <mainClass>xyz.jetdrone.vertx.lambda.knative.Bootstrap</mainClass>
        <imageName>bootstrap</imageName>
        <buildArgs>--report-unsupported-elements-at-runtime --allow-incomplete-classpath --no-server</buildArgs>
      </configuration>
    </plugin>
    <plugin>
      <groupId>org.apache.maven.plugins</groupId>
      <artifactId>maven-assembly-plugin</artifactId>
      <version>3.1.1</version>
      <dependencies>
        <dependency>
          <groupId>xyz.jetdrone</groupId>
          <artifactId>vertx.lambda</artifactId>
          <version>LATEST</version>
        </dependency>
      </dependencies>
      <configuration>
        <descriptorRefs>
          <descriptorRef>aws-function</descriptorRef>
        </descriptorRefs>
      </configuration>
      <executions>
        <execution>
          <phase>package</phase>
          <goals>
            <goal>single</goal>
          </goals>
        </execution>
      </executions>
    </plugin>
  </plugins>
</build>
```

The first plugin will compile your code to a `native-image` and the second
will `zip` your function as required by AWS.

### Knative

In your `pom.xml` add the plugins:

```xml
<build>
  <plugins>
    <plugin>
      <groupId>com.oracle.substratevm</groupId>
      <artifactId>native-image-maven-plugin</artifactId>
      <version>${graal.version}</version>
      <executions>
        <execution>
          <goals>
            <goal>native-image</goal>
          </goals>
          <phase>package</phase>
        </execution>
      </executions>
      <configuration>
        <mainClass>xyz.jetdrone.vertx.lambda.knative.Bootstrap</mainClass>
        <imageName>bootstrap</imageName>
        <buildArgs>--report-unsupported-elements-at-runtime --allow-incomplete-classpath --no-server</buildArgs>
      </configuration>
    </plugin>
    <plugin>
      <groupId>org.apache.maven.plugins</groupId>
      <artifactId>maven-dependency-plugin</artifactId>
      <version>3.1.1</version>
      <executions>
        <execution>
          <id>unpack</id>
          <phase>package</phase>
          <goals>
            <goal>unpack</goal>
          </goals>
          <configuration>
            <artifactItems>
              <artifactItem>
                <groupId>xyz.jetdrone</groupId>
                <artifactId>vertx.lambda</artifactId>
                <version>LATEST</version>
                <type>jar</type>
                <overWrite>false</overWrite>
                <outputDirectory>${project.build.directory}</outputDirectory>
                <includes>**/knative/*</includes>
              </artifactItem>
            </artifactItems>
          </configuration>
        </execution>
      </executions>
    </plugin>
  </plugins>
</build>
```

The first plugin will compile your code to a `native-image` and the second
will `unpack` a default `service.yaml` to be used to deploy your lambda.

### OpenFaaS

In your `pom.xml` add the plugin:

```xml
<build>
  <plugins>
    <plugin>
      <groupId>com.oracle.substratevm</groupId>
      <artifactId>native-image-maven-plugin</artifactId>
      <version>${graal.version}</version>
      <executions>
        <execution>
          <goals>
            <goal>native-image</goal>
          </goals>
          <phase>package</phase>
        </execution>
      </executions>
      <configuration>
        <mainClass>xyz.jetdrone.vertx.lambda.knative.Bootstrap</mainClass>
        <imageName>bootstrap</imageName>
        <buildArgs>--report-unsupported-elements-at-runtime --allow-incomplete-classpath --no-server</buildArgs>
      </configuration>
    </plugin>
  </plugins>
</build>
```

The plugin will compile your code to a `native-image`.

## Next steps

Lambdas are exposed to `vert.x` event bus, however for `knative` and `openFaaS` you might
need to map the functions to URLs too. In this case you can override the method `alias`:

```java
public class MyLambda implements Lambda<Buffer> {
  @Override
  public String alias() {
    return "/";
  }
}
```

In this case the mapping is not base on the fully qualified class name, but on the given
alias.

# Finally

Deploy and profit!
