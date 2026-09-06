package fr.univ_reims.a2i.android_rpg.modele

/**
 * Un personnage du jeu : le heros, un allie ou un ennemi.
 *
 * Construit fiche apres fiche : constructeur primaire et `init` (fiche 3),
 * arme optionnelle (fiche 1), inventaire (fiche 5), etat (fiche 8),
 * fabrique dans le `companion object` (fiche 3).
 *
 * @param pvMax PV maximum ; par defaut la valeur initiale de [pv]. Sert de
 *              plafond aux soins (voir [soigner]) et de reference a la barre de vie.
 */
class Personnage(
    val nom: String,
    var pv: Int,
    var arme: Arme? = null,
    val pvMax: Int = pv,
) {
    init {
        require(pv >= 0) { "Les PV initiaux ne peuvent pas etre negatifs" }
        require(pvMax >= pv) { "pvMax doit etre au moins egal aux PV initiaux" }
    }

    /** Bouclier equipe, `null` si le personnage combat sans protection. */
    var bouclier: Bouclier? = null

    /** Etat courant en combat (fiche 8). */
    var etat: EtatPersonnage = EtatPersonnage.EnVie

    /** Objets ramasses (fiche 5). */
    val inventaire: MutableList<Objet> = mutableListOf()

    fun ramasser(objet: Objet) {
        inventaire.add(objet)
    }

    companion object {
        const val PV_GOBELIN = 8

        /** Fabrique un ennemi standard sans repeter les valeurs par defaut. */
        fun creerGobelin(
            nom: String = "Gobelin",
            pv: Int = PV_GOBELIN,
            arme: Arme? = null,
        ): Personnage = Personnage(nom, pv, arme)
    }
}
