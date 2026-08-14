# Attribute Modifier
Complete control over any item's attributes vanilla or modded inside the configuration.

## Features
- Modify any attribute from any item.
- Add entries in the configuration file.
- You may modify these attributes:
  - Max Health
  - Follow Range
  - Knockback Resistance
  - Movement Speed
  - Flying Speed (fixed this one for you)
  - Attack Damage
  - Attack Speed
  - Armor
  - Armor Toughness
  - Luck

## Dependencies
Expanded Events on CurseForge: https://www.curseforge.com/minecraft/mc-mods/expanded-events

Mixin Booter is also required.

## Configuration
The file is named attributeModifiers, its a JSON file.

Examples:

```java
{
  "minecraft:diamond_hoe": {
    "maxHealth": 10.0,
    "followRange": 60.0,
    "knockbackResistance": 2.0,
    "movementSpeed": 0.7,
    "flyingSpeed": 0.15,
    "attackDamage": 10.0,
    "attackSpeed": 2.0,
    "armor": 2.0,
    "armorToughness": 1.0,
    "luck": 5.0
  },
  "minecraft:diamond_chestplate": {
    "armor": 100.0,
    "armorToughness": 10.0
  },
  "minecraft:diamond_pickaxe": {
    "movementSpeed": 1.1,
    "attackDamage": -1.0,
    "attackSpeed": -1.0
  },
  "minecraft:diamond_sword": {
    "maxHealth": 10.0,
    "attackDamage": -1.0,
    "armor": 5.0,
    "luck": 5.0
  }
}
```
(The values are stored as Floats).

You can add as many attributes as you want, but you can't repeat them.

There is no set maximum or minimum value, negative values will work, but not for Attack Damage, Flying Speed, and Follow Range attributes.

Movement Speed and Attack Speed attribute are percentages, a value of 1.1 gives the player +110% speed, a value of -0.6 -60% speed and so on. 

For Attack Speed a value of -60% equals 1.6 attack speed (average speed). I know its quirky, but it works so Im letting it be for now.

A value of 0 removes the attribute. To remove Attack Damage, Flying Speed, or Follow Range attributes use -1 or any negative value.

### What is an attribute?
Attributes control certain numeric properties of players, mobs, and armor stands. Every entity has a base value for each of its attributes, and modifiers that determine the final value ([source: wiki](https://minecraft.wiki/w/Attribute)).

### Copyright
<a target="_blank" href="https://icons8.com/icon/1501/plus">Plus</a> icon by <a target="_blank" href="https://icons8.com">Icons8</a>

### Tags
Weapon damage, weapon speed, player movement speed, attack damage, attack speed, weapon attributes
