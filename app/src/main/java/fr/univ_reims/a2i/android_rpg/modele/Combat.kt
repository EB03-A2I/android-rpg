package fr.univ_reims.a2i.android_rpg.modele

/**
 * Logique de combat du fil rouge, en Kotlin pur (aucune dependance a Compose).
 *
 * Regroupe ce qui a ete construit des fiches 3 a 13 : attaque (fiche 3),
 * degats et transition d'etat (fiche 8), soin (fiche 8). La fiche 14 y ajoute
 * deux manoeuvres sans degat ([bousculer], [passerTour]) ; les fiches 15-16
 * branchent une interface Android par-dessus.
 */

/** Applique [degats] a [perso] (PV bornes a 0) et met a jour son [Personnage.etat]. */
fun subirDegats(perso: Personnage, degats: Int) {
    perso.pv = maxOf(0, perso.pv - degats)
    perso.etat = when {
        perso.pv <= 0 -> EtatPersonnage.Vaincu
        degats >= 10 -> EtatPersonnage.Etourdi(toursRestants = 2)
        else -> EtatPersonnage.EnVie
    }
}

/** Decrit l'etat courant de [perso] : `when` exhaustif sur la `sealed class` (fiche 8). */
fun decrireEtat(perso: Personnage): String = when (val etat = perso.etat) {
    is EtatPersonnage.EnVie -> "${perso.nom} est pret au combat"
    is EtatPersonnage.Etourdi -> "${perso.nom} est etourdi (encore ${etat.toursRestants} tour(s))"
    is EtatPersonnage.Vaincu -> "${perso.nom} est vaincu"
}

/** Le personnage attaque [cible] avec son arme, ou a mains nues (1 degat) s'il n'en a pas. */
fun Personnage.attaquer(cible: Personnage) {
    val degats = arme?.degats ?: 1
    subirDegats(cible, degats)
    ChroniqueDeCombat.enregistrer(
        "$nom attaque ${cible.nom} avec ${arme?.nom ?: "ses poings"} (-$degats PV)"
    )
}

/**
 * Rend [montant] PV au personnage, sans depasser [Personnage.pvMax].
 * Sans effet si le personnage est [EtatPersonnage.Vaincu] : on ne ressuscite pas.
 */
fun Personnage.soigner(montant: Int) {
    if (etat is EtatPersonnage.Vaincu) return
    pv = minOf(pvMax, pv + montant)
    etat = EtatPersonnage.EnVie
    ChroniqueDeCombat.enregistrer("$nom recupere $montant PV ($pv/$pvMax)")
}

/**
 * Bouscule [cible] pour la desequilibrer : elle passe [EtatPersonnage.Etourdi]
 * pour [tours] tours **sans perdre un seul PV**. Sans effet si [cible] est vaincue.
 *
 * Deuxieme voie vers l'etat `Etourdi` (l'autre etant un gros coup, voir
 * [subirDegats]) : ici l'etat change alors que les PV ne bougent pas.
 */
fun Personnage.bousculer(cible: Personnage, tours: Int = 2) {
    if (cible.etat is EtatPersonnage.Vaincu) return
    cible.etat = EtatPersonnage.Etourdi(toursRestants = tours)
    ChroniqueDeCombat.enregistrer("$nom bouscule ${cible.nom} : etourdi $tours tour(s)")
}

/**
 * Passe un tour : si le personnage est [EtatPersonnage.Etourdi], son etourdissement
 * se dissipe d'un tour (retour a [EtatPersonnage.EnVie] au dernier). Sans effet
 * sinon, et **ne touche jamais aux PV**.
 */
fun Personnage.passerTour() {
    val etatCourant = etat
    if (etatCourant !is EtatPersonnage.Etourdi) return
    etat = if (etatCourant.toursRestants > 1) {
        EtatPersonnage.Etourdi(etatCourant.toursRestants - 1)
    } else {
        EtatPersonnage.EnVie
    }
    ChroniqueDeCombat.enregistrer("$nom passe son tour ; ${decrireEtat(this)}")
}
