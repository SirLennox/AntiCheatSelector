# AntiCheatSelector
A Minecraft AntiCheat Selector for Minecraft AntiCheat Test Servers [Spigot 1.8.8]

## Installation

Download the latest plugin version and drag it into your `plugins` folder.

## Configuration

Start the server and then stop it. Go into `plugins/AntiCheatSelector` and open the `config.yml`.

There you can add new AntiCheats.

## Permissions

### Command Permissions

The permission for each command is:

`anticheatselector.command.<Command>`

For Example:

`anticheatselector.command.anticheat`

#### Note

For a list of commands execute `/help AntiCheatSelector`

### AntiCheat Permissions

The permission for each anticheat is:

`anticheatselector.anticheat.<AntiCheatName>`

#### Note

The AntiCheat Name is the name of the anticheat you defined in the `config.yml`.

### Config Permission

The permission for each config is:

`anticheatselector.config.<Config>`

For example:

`anticheatselector.config.kick`

