# CloudGame
A flexible, scalable minigame framework for the Bukkit API.

[![Stories in Ready](https://badge.waffle.io/simplyianm/cloudgame.png?label=ready&title=Ready)](https://waffle.io/simplyianm/cloudgame)
[![Build Status](https://travis-ci.org/simplyianm/cloudgame.svg?branch=master)](https://travis-ci.org/simplyianm/cloudgame)

## Why?
CloudGame allows server owners to manage all of their minigames from one plugin, without conflicts. Everything is
consistent between different minigames, from the commands to the messages displayed. However, everything is
also customizable, allowing people to make their server however they'd like.

### Developers
Other minigame frameworks were too complicated or too specific to create games. Some people want one command
per game, others want an entire minigame minus one feature, and others want something that looks completely custom.

CloudGame seeks to fix this problem.

Using an innovative "mixin" system, developers can mix and match different parts of games as they please.
You could create a Team Deathmatch that has a 10 minute time limit, and by deleting one line of code you
could get rid of that countdown.

Mixins are basically plugins that a game can use to change how the game works. They provide listeners
and data that power the game.

Another huge point of CloudGame is that **everything is an event**. All game logic is handled in events
to allow mixins to modify how the game operates. If you want to send an extra message when a player leaves
a game, you can just subscribe to the `GameLeaveEvent` and send a message there.

CloudGame is extremely flexible and built for developers who want a powerful yet extensible boilerplate for their minigames.

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
