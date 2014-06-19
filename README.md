# MattKOTH
King of the hill minigame.

## Setup
Creating a new KOTH arena is simple with the plugin. First, use the following command:

`/koth setregion <hill> <arena>`

where `<hill>` is the name of the WorldGuard region representing the hill and `<arena>` is the name of the WorldGuard region representing the arena.

The region should be created. If it isn't, that sucks. (contact me)

Next, you need to set some spawn points. Use the following command to set a spawn:

`/koth setspawn <#>`

where `<#>` is a number from 1 - 5.

Next, you should set a name for the arena. Use `/arenasetname <name>` to set a name. This is optional.

Now you're done! To start a new KOTH on an arena, use the command `/koth start <arena>` where `<arena>` is the name of the region. Note: this is not the same as the name.

To stop a KOTH in progress, use `/koth stop`. Nobody will win. You can also use this to stop a KOTH timer.
