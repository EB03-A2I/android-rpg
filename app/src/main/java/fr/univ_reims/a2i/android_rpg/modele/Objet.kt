package fr.univ_reims.a2i.android_rpg.modele

/**
 * Tout ce qui peut se trouver dans l'inventaire d'un [Personnage].
 *
 * Contrat commun introduit en fiche 4 (interfaces et polymorphisme) :
 * un nom et une description lisible, quel que soit le type concret.
 */
interface Objet {
    val nom: String
    fun description(): String
}

/** Arme equipable : ajoute [degats] a une attaque (fiche 3, `data class`). */
data class Arme(override val nom: String, val degats: Int) : Objet {
    override fun description() = "$nom (+$degats degats)"
}

/** Bouclier equipable : ajoute [defense] a la protection (fiche 4). */
data class Bouclier(override val nom: String, val defense: Int) : Objet {
    init { require(defense >= 0) { "La defense ne peut pas etre negative" } }
    override fun description() = "$nom (+$defense defense)"
}
