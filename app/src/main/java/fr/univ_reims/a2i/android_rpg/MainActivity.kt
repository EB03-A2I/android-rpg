package fr.univ_reims.a2i.android_rpg

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.univ_reims.a2i.android_rpg.modele.Arme
import fr.univ_reims.a2i.android_rpg.modele.Personnage
import fr.univ_reims.a2i.android_rpg.modele.decrireEtat
import fr.univ_reims.a2i.android_rpg.ui.theme.AndroidrpgTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidrpgTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    EcranCombat(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

/**
 * Ecran de depart : affichage **statique** de l'etat du combat.
 *
 * Point de depart de la fiche 14 : aucune donnee n'est encore un `State`, aucun
 * bouton n'agit. La fiche 14 transforme `heros` / `gobelin` en
 * `remember { mutableStateOf(...) }` et ajoute un bouton "Attaquer" qui declenche
 * la recomposition.
 */
@Composable
fun EcranCombat(modifier: Modifier = Modifier) {
    val heros = Personnage("Aria", pv = 20, arme = Arme("Épée rouillée", 5))
    val gobelin = Personnage.creerGobelin(pv = 8)

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Text("Combat", style = MaterialTheme.typography.headlineMedium)

        Text("${heros.nom} : ${heros.pv}/${heros.pvMax} PV")
        Text("Arme : ${heros.arme?.description() ?: "aucune"}")

        Text("${gobelin.nom} : ${gobelin.pv}/${gobelin.pvMax} PV")

        Text(decrireEtat(heros))
    }
}

@Preview(showBackground = true)
@Composable
fun EcranCombatPreview() {
    AndroidrpgTheme {
        EcranCombat()
    }
}
