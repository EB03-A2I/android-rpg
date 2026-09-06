package fr.univ_reims.a2i.android_rpg.modele

/**
 * Etats possibles d'un [Personnage] pendant un combat (fiche 8, `sealed class`).
 *
 * Hierarchie fermee : les seules formes concevables sont declarees ici, ce qui
 * permet un `when` exhaustif sans branche `else` (voir [decrireEtat]).
 */
sealed class EtatPersonnage {
    /** Pret a agir. */
    object EnVie : EtatPersonnage()

    /** Ne peut pas agir pendant [toursRestants] tours. */
    data class Etourdi(val toursRestants: Int) : EtatPersonnage()

    /** Plus de PV : hors combat. */
    object Vaincu : EtatPersonnage()
}
