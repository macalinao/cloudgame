# CloudGame
A flexible, scalable minigame framework for the Bukkit API.

[![Stories in Ready](https://badge.waffle.io/simplyianm/cloudgame.png?label=ready&title=Ready)](https://waffle.io/simplyianm/cloudgame)
[![Build Status](https://travis-ci.org/simplyianm/cloudgame.svg?branch=master)](https://travis-ci.org/simplyianm/cloudgame)

## Compiling
CloudGame uses Gradle to manage building the JAR. To compile, just run either `gradlew` (*nix) or `gradlew.bat` (Windows) and everything should get built. The JAR should end up as `build/CloudGame.jar`.

## Repository Info

### Maven
```xml
<repositories>
  <repository>
    <id>sonatype-oss</id>
    <url>https://oss.sonatype.org/content/groups/public/</url>
  </repository>
</repositories>

<dependencies>
  <dependency>
    <groupId>pw.ian</groupId>
    <artifactId>cloudgame</artifactId>
    <version>0.1.0-SNAPSHOT</version>
  </dependency>
</dependencies>
```

### Gradle
```groovy
repositories {
  maven { url 'https://oss.sonatype.org/content/groups/public/' }
}

dependencies {
  compile 'pw.ian:cloudgame:0.1.0-SNAPSHOT'
}
```

## License

Copyright (c) 2014 Ian Macalinao. Released under the GPLv3.
