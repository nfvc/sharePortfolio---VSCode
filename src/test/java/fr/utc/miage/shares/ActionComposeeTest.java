/*
 * Copyright 2025 David Navarre &lt;David.Navarre at irit.fr&gt;.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package fr.utc.miage.shares;

import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ActionComposeeTest {

/**
 * Test Constructeur ActionComposee
 */
@Test
final void testConstructorSuccess() {
    // Arrange
    final String libelle = "France Télévisions";

    // Action
    final ActionComposee actionComposee = new ActionComposee(libelle);

    // Assert
    Assertions.assertAll("Vérification du constructeur d'ActionComposee",
        () -> Assertions.assertEquals(libelle, actionComposee.getLibelle(), "Le libellé correspond à celui du constructeur"),
        () -> Assertions.assertEquals(0f, actionComposee.getPourcentageTotal(), 0.01f, "La composition en %, de l'action est vide"),
        () -> Assertions.assertEquals(0f, actionComposee.valeur(new Jour(2024, 150)), 0.01f, "La valeur est 0.")
    );

}
/**
 * Test Accesseur getComposition()
 */
@Test
    void testAccessorsShouldWork() {

        // Arrange
        final String libelle = "France Télévisions";
        final ActionComposee actionComposee = new ActionComposee(libelle);

        // Action
        final String actualLibelle = actionComposee.getLibelle();
        final Map<ActionSimple, Float> actualComposition = actionComposee.getComposition();

        // Assert
        Assertions.assertAll("Test des accesseurs de ActionComposee",
            () -> Assertions.assertEquals(libelle, actualLibelle, "Le libellé doit correspondre à celui passé au constructeur"),
            () -> Assertions.assertNotNull(actualComposition, "La map de composition ne doit pas être null"),
            () -> Assertions.assertTrue(actualComposition.isEmpty(), "La composition doit être vide à l'initialisation")
        );
       
    }

    /**
        * Test de l'ajout d'une ActionSimple à une ActionComposee Réussi
    **/

@Test
void testAjouterSimplesSuccess() {
    // Arrange
    ActionComposee actionComposee = new ActionComposee("France Télévisions");
    ActionSimple france2 = new ActionSimple("France 2");
    ActionSimple france3 = new ActionSimple("France 3");
    ActionSimple france5 = new ActionSimple("Total Energie");

    // Act
    actionComposee.ajouterActionSimple(france2, 35f);
    actionComposee.ajouterActionSimple(france3, 50f);
    actionComposee.ajouterActionSimple(france5, 15f);

    // Assert
    Assertions.assertAll("Ajout de 3 actions simples à une action composée",
        () -> Assertions.assertEquals(3, actionComposee.getComposition().size(), "La composition doit contenir 3 actions"),
        () -> Assertions.assertEquals(35f, actionComposee.getComposition().get(france2), 0.01f),
        () -> Assertions.assertEquals(50f, actionComposee.getComposition().get(france3), 0.01f),
        () -> Assertions.assertEquals(15f, actionComposee.getComposition().get(france5), 0.01f),
        () -> Assertions.assertEquals(100f, actionComposee.getPourcentageTotal(), 0.01f, "Le pourcentage total doit être 100%"),
        () -> Assertions.assertTrue(actionComposee.estComplete(), "L'action composée doit être complète")
    );
}

/**
        * Test de l'ajout d'une ActionSimple à une ActionComposee non réussi
    **/
@Test
void testAjouterActionSimpleDoublon() {
    // Arrange
    ActionComposee actionComposee = new ActionComposee("TV Publique");
    ActionSimple france2 = new ActionSimple("France 2");
    actionComposee.ajouterActionSimple(france2, 40f);

    // Act (tentative de double ajout)
    actionComposee.ajouterActionSimple(france2, 50f); // ne doit pas écraser

    // Assert
    Assertions.assertAll("Ajout doublon ignoré",
        () -> Assertions.assertEquals(40f, actionComposee.getComposition().get(france2), 0.01f),
        () -> Assertions.assertEquals(40f, actionComposee.getPourcentageTotal(), 0.01f)
    );
}
    /**
        * Test de l'ajout d'une ActionSimple à une ActionComposee non réussi total dépassant les 100%
    **/

@Test
void testAjouterActionSimpleFail() {
    // Arrange
    ActionComposee actionComposee = new ActionComposee("TV Publique");
    ActionSimple france2 = new ActionSimple("France 2");
    ActionSimple france3 = new ActionSimple("France 3");

    actionComposee.ajouterActionSimple(france2, 90f);

    // Act (tentative d’ajout qui dépasse 100 %)
    actionComposee.ajouterActionSimple(france3, 20f); // total = 110 → refusé

    // Assert
    Assertions.assertAll("Ajout refusé si dépassement de 100%",
        () -> Assertions.assertTrue(actionComposee.getComposition().containsKey(france2)),
        () -> Assertions.assertFalse(actionComposee.getComposition().containsKey(france3)),
        () -> Assertions.assertEquals(90f, actionComposee.getPourcentageTotal(), 0.01f)
    );
}

/**
        * Test de la supression d'une ActionSimple à une ActionComposee réussi(action présente).
    **/

@Test
void testSupprimerActionSimpleSucess() { 
    // Arrange
    ActionComposee actionComposee = new ActionComposee("TV Publique");
    ActionSimple france2 = new ActionSimple("France 2");
    actionComposee.ajouterActionSimple(france2, 40f);

    // Act
    actionComposee.supprimerActionSimple(france2);

    // Assert
    Assertions.assertAll("Suppression d'une action présente",
        () -> Assertions.assertFalse(actionComposee.getComposition().containsKey(france2), "L'action ne doit plus être présente"),
        () -> Assertions.assertEquals(0f, actionComposee.getPourcentageTotal(), 0.01f, "Le total doit être mis à jour")
    );
}

    /**
        * Test de la supression d'une ActionSimple à une ActionComposee non réussi(action qui n'existe).
    **/

@Test
void testSupprimerActionSimpleFail() {
    // Arrange
    ActionComposee actionComposee = new ActionComposee("TV Publique");
    ActionSimple france2 = new ActionSimple("France 2");

    // Act
    actionComposee.supprimerActionSimple(france2); // rien à supprimer

    // Assert
    Assertions.assertAll("Suppression silencieuse d'une action absente",
        () -> Assertions.assertTrue(actionComposee.getComposition().isEmpty(), "La composition reste vide"),
        () -> Assertions.assertEquals(0f, actionComposee.getPourcentageTotal(), 0.01f, "Le total reste à 0")
    );
}
    /**
        * Test de la supression d'une ActionSimple Suppression parmi plusieurs actions.
    **/

@Test
void testSupprimerActionSimple() {
    // Arrange
    ActionComposee actionComposee = new ActionComposee("TV Publique");
    ActionSimple france2 = new ActionSimple("France 2");
    ActionSimple france3 = new ActionSimple("France 3");

    actionComposee.ajouterActionSimple(france2, 40f);
    actionComposee.ajouterActionSimple(france3, 50f);

    // Act
    actionComposee.supprimerActionSimple(france2);

    // Assert
    Assertions.assertAll("Suppression partielle",
        () -> Assertions.assertFalse(actionComposee.getComposition().containsKey(france2), "France 2 supprimée"),
        () -> Assertions.assertTrue(actionComposee.getComposition().containsKey(france3), "France 3 encore présente"),
        () -> Assertions.assertEquals(50f, actionComposee.getPourcentageTotal(), 0.01f, "Total mis à jour")
    );
}


/*L’action est présente et le nouveau pourcentage est compatible avec le total → la modification doit réussir*/



@Test
void testModifierPourcentageSucess() {
    // Arrange
    ActionComposee actionComposee = new ActionComposee("TV Publique");
    ActionSimple france2 = new ActionSimple("France 2");
    ActionSimple france3 = new ActionSimple("France 3");

    actionComposee.ajouterActionSimple(france2, 40f);
    actionComposee.ajouterActionSimple(france3, 50f);

    // Act
    actionComposee.modifierPourcentage(france2, 30f); // total = 30 + 50 = 80

    // Assert
    Assertions.assertAll("Modification valide",
        () -> Assertions.assertEquals(30f, actionComposee.getComposition().get(france2), 0.01f, "Le % de France 2 doit être modifié"),
        () -> Assertions.assertEquals(80f, actionComposee.getPourcentageTotal(), 0.01f, "Total mis à jour")
    );
}

/*Si l’action n’est pas présente dans la composition → la méthode ne fait rien.*/

@Test
void testModifierPourcentageFail() {

    // Arrange
    ActionComposee actionComposee = new ActionComposee("TV Publique");
    ActionSimple france2 = new ActionSimple("France 2");

    // Act
    actionComposee.modifierPourcentage(france2, 40f);

    // Assert
    Assertions.assertAll("Aucune modification si action absente",
        () -> Assertions.assertFalse(actionComposee.getComposition().containsKey(france2), "L'action ne doit pas être ajoutée"),
        () -> Assertions.assertEquals(0f, actionComposee.getPourcentageTotal(), 0.01f, "Le total reste à 0")
    );
}

/*La nouvelle valeur combinée ferait dépasser 100% → la modification est ignorée*/

@Test
void testModifierPourcentageFailTotalSuperieur() {
    // Arrange
    ActionComposee actionComposee = new ActionComposee("TV Publique");
    ActionSimple france2 = new ActionSimple("France 2");
    ActionSimple france3 = new ActionSimple("France 3");

    actionComposee.ajouterActionSimple(france2, 40f);
    actionComposee.ajouterActionSimple(france3, 50f);

    // Act (tentative de monter France 2 à 60% → total = 110%)
    actionComposee.modifierPourcentage(france2, 60f);

    // Assert
    Assertions.assertAll("Modification refusée si dépassement",
        () -> Assertions.assertEquals(40f, actionComposee.getComposition().get(france2), 0.01f, "Ancien % conservé"),
        () -> Assertions.assertEquals(90f, actionComposee.getPourcentageTotal(), 0.01f)
    );
}


/* Cas idéal : la somme des pourcentages vaut exactement 100 → doit retourner true */

@Test
void testEstCompleteSucess() {
    // Arrange
    ActionComposee actionComposee = new ActionComposee("TV Publique");
    ActionSimple france2 = new ActionSimple("France 2");
    ActionSimple france3 = new ActionSimple("France 3");

    actionComposee.ajouterActionSimple(france2, 60f);
    actionComposee.ajouterActionSimple(france3, 40f);

    // Act + Assert
    Assertions.assertTrue(actionComposee.estComplete(), "La composition est complète à 100%");
}

/* Cas courant : les pourcentages ne totalisent pas 100 → doit retourner false*/

@Test
void testEstCompleteTotalInferieur() {
    // Arrange
    ActionComposee actionComposee = new ActionComposee("TV Publique");
    ActionSimple france2 = new ActionSimple("France 2");

    actionComposee.ajouterActionSimple(france2, 80f);

    // Act + Assert
    Assertions.assertFalse(actionComposee.estComplete(), "La composition est incomplète (< 100%)");
}


/* Aucun élément ajouté → total 0 → doit retourner false. */

@Test
void testEstCompleteFailTotalZero() {
    // Arrange
    ActionComposee actionComposee = new ActionComposee("TV Vide");

    // Act + Assert
    Assertions.assertFalse(actionComposee.estComplete(), "Aucune action → composition non complète");
}

/*L’action est présente dans la composition */

@Test
void testContientActionSucess() {
    // Arrange
    ActionComposee actionComposee = new ActionComposee("Média");
    ActionSimple france2 = new ActionSimple("France 2");

    actionComposee.ajouterActionSimple(france2, 50f);

    // Act & Assert
    Assertions.assertTrue(actionComposee.contientAction(france2), "L'action France 2 est bien présente");
}

/* L’action n’est pas présente */

@Test
void testContientActionAbsente() {
    // Arrange
    ActionComposee actionComposee = new ActionComposee("Média");
    ActionSimple france2 = new ActionSimple("France 2");
    ActionSimple france3 = new ActionSimple("France 3");

    actionComposee.ajouterActionSimple(france2, 50f);

    // Act & Assert
    Assertions.assertFalse(actionComposee.contientAction(france3), "L'action France 3 ne doit pas être présente");
}

/* Composition vide */

@Test
void testContientActionCompositionVide() {
    // Arrange
    ActionComposee actionComposee = new ActionComposee("Média");
    ActionSimple france2 = new ActionSimple("France 2");

    // Act & Assert
    Assertions.assertFalse(actionComposee.contientAction(france2), "Aucune action ajoutée → doit retourner false");
}

/* Valeur normale avec plusieurs actions simples */

@Test
void testValeurSucess() {
    // Arrange
    ActionComposee actionComposee = new ActionComposee("France Télévisions");
    ActionSimple france2 = new ActionSimple("France 2");
    ActionSimple france3 = new ActionSimple("France 3");

    Jour jour = new Jour(2024, 120);
    france2.enrgCours(jour, 100f); // cours à 100
    france3.enrgCours(jour, 50f);  // cours à 50

    actionComposee.ajouterActionSimple(france2, 60f); // 60%
    actionComposee.ajouterActionSimple(france3, 40f); // 40%

    // Act
    float valeur = actionComposee.valeur(jour);

    // Assert
    // expected = (0.6 * 100) + (0.4 * 50) = 60 + 20 = 80
    Assertions.assertEquals(80f, valeur, 0.01f, "La valeur doit être la somme pondérée");
}


/* Une action n’a pas de cours enregistré pour ce jour */

@Test
void testValeurSansCours() {
    // Arrange
    ActionComposee actionComposee = new ActionComposee("TV Mixte");
    ActionSimple france2 = new ActionSimple("France 2");
    ActionSimple france3 = new ActionSimple("France 3");

    Jour jour = new Jour(2024, 130);
    france2.enrgCours(jour, 80f);
    // france3 n’a pas de cours ce jour-là

    actionComposee.ajouterActionSimple(france2, 70f);
    actionComposee.ajouterActionSimple(france3, 30f);

    // Act
    float valeur = actionComposee.valeur(jour);

    // Assert
    // expected = 0.7 * 80 = 56
    Assertions.assertEquals(56f, valeur, 0.01f, "La valeur doit ignorer les actions sans cours");
}

/* Aucune action dans la composition */

@Test
void testValeurAbsente() {
    // Arrange
    ActionComposee actionComposee = new ActionComposee("TV Vide");
    Jour jour = new Jour(2024, 100);

    // Act
    float valeur = actionComposee.valeur(jour);

    // Assert
    Assertions.assertEquals(0f, valeur, 0.01f, "Sans actions, la valeur doit être 0");
}


}
