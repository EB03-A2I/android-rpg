# android-rpg — fil rouge des fiches 14 à 16 (Kotlin, EB03)

Projet Android Studio servant de base commune aux fiches **14 (Compose — bases)**,
**15 (Compose — ViewModel)** et **16 (patrons de conception)** du cours EB03 (Ingé A2I, URCA).

Il reprend le *fil rouge* des notebooks (un petit RPG au tour par tour) et le porte
sur Android. Le projet grandit fiche par fiche : on part de cet état, chaque fiche
ajoute une couche.

## Prise en main

1. Suivre l'annexe **« Prise en main d'Android Studio »** pour installer l'IDE et le SDK.
2. Cloner ce dépôt dans Android Studio (`File > New > Project from Version Control`).
3. Laisser Gradle se synchroniser, puis lancer l'app sur un émulateur ou un appareil.

Configuration : `compileSdk`/`targetSdk` 37, `minSdk` 24, Kotlin DSL, Jetpack Compose
(Material 3). Package : `fr.univ_reims.a2i.android_rpg`.

## Ce que contient la base

### `modele/` — le fil rouge en Kotlin pur (aucune dépendance à Compose)

| Fichier | Contenu | Fiche d'origine |
|---|---|---|
| `Objet.kt` | `interface Objet`, `data class Arme`, `data class Bouclier` | 3–4 |
| `EtatPersonnage.kt` | `sealed class EtatPersonnage` (`EnVie` / `Etourdi` / `Vaincu`) | 8 |
| `Personnage.kt` | `class Personnage` (pv, pvMax, arme, bouclier, état, inventaire) + `companion object` (`creerGobelin`) | 3, 5, 8 |
| `ChroniqueDeCombat.kt` | `object ChroniqueDeCombat` — journal partagé (Singleton) | 3 |
| `Combat.kt` | `attaquer`, `soigner`, `subirDegats`, `decrireEtat` | 3, 8 |

Ce modèle ne doit pas être modifié par les fiches 14–16 : elles branchent une
interface Android **par-dessus**.

### `MainActivity.kt` — écran de départ

`EcranCombat` affiche l'état du combat de façon **statique** : pas encore de `State`,
pas de bouton actif. C'est le point de départ de la fiche 14.

## Corrigés

Les corrigés vivent sur une branche locale `corriges`, non poussée tant que
l'enseignant ne souhaite pas les publier.
