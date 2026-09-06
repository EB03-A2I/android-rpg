package fr.univ_reims.a2i.android_rpg.modele

/**
 * Journal partage du combat (fiche 3, `object`).
 *
 * Un seul exemplaire pour toute l'application : c'est le patron Singleton du GoF,
 * natif en Kotlin via `object`. Repris en fiche 16 comme point de comparaison
 * avec l'observation par `Flow`.
 */
object ChroniqueDeCombat {

    private val _messages = mutableListOf<String>()

    /** Messages enregistres, du plus ancien au plus recent (copie en lecture seule). */
    val messages: List<String> get() = _messages.toList()

    fun enregistrer(message: String) {
        _messages.add(message)
    }

    /** Vide le journal (utile entre deux parties). */
    fun reinitialiser() {
        _messages.clear()
    }
}
