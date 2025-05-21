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

class PortefeuilleTest {

    /**
     * Client.
     */
    private static final Client CLIENT = new Client("Aymane", "Akcha", "Toulouse", "0456377890", "akcha@outlook.fr",
            "akcha");

    /**
     * Test Constructeur
     */
    @Test
    final void testConstructorSuccess() {
        // Arrange
        final Portefeuille portefeuille = new Portefeuille(CLIENT);

        // Action
        final String expectedNomClient = "Aymane";
        final String expectedPrenomClient = "Akcha";

        Assertions.assertAll("Vérification infos du constructeur",
                () -> Assertions.assertEquals(expectedNomClient, portefeuille.getClient().getNom(),
                        "Le nom correspond à celui du constructeur"),
                () -> Assertions.assertEquals(expectedPrenomClient, portefeuille.getClient().getPrenom(),
                        "Le prénom correspond à celui du constructeur"));
    }

    /**
     * Test Accesseur
     */
    @Test
    void testAccessorsShouldWork() {

        // Arrange
        final Portefeuille portefeuille = new Portefeuille(CLIENT);

        // Action
        final Client actualClient = portefeuille.getClient();
        final double actualSolde = portefeuille.getSolde();
        final Map<Action, Integer> actualActions = portefeuille.getActions();

        // Assert
        Assertions.assertAll("Test des accesseurs de Portefeuille",
                () -> Assertions.assertEquals(CLIENT, actualClient, "Le client doit correspondre à CLIENT"),
                () -> Assertions.assertEquals(0.0, actualSolde, "Solde à 0.0"),
                () -> Assertions.assertNotNull(actualActions, "La HashMap n'est pas null"),
                () -> Assertions.assertTrue(actualActions.isEmpty(), "Aucune action présente"));
    }

    /**
     * Test d'approvisionnement du portefeuille avec une valeur positive
     */
    @Test
    void testProvisionnerPositif() {
        // Arrange
        Portefeuille portefeuille = new Portefeuille(CLIENT);
        final double valeurattendue = 100;
        final double valeurajoutee = 100;

        // Action
        boolean success = portefeuille.provisionner(valeurajoutee);

        // Assert
        Assertions.assertAll("Ajout d'une valeur négative",
                () -> Assertions.assertTrue(success, "L'ajout à été réussi"),
                () -> Assertions.assertEquals(valeurattendue, portefeuille.getSolde(),
                        "Le solde à atteint la valeur attendu"));
    }

    /**
     * Test d'approvisionnement du portefeuille avec une valeur négative
     */
    @Test
    void testProvisionnerNegatif() {
        // Arrange
        Portefeuille portefeuille = new Portefeuille(CLIENT);
        final double valeurAjoutee = -50;

        // Action
        boolean success = portefeuille.provisionner(valeurAjoutee);

        // Assert
        Assertions.assertAll("Ajout d'une valeur négative",
                () -> Assertions.assertFalse(success, "L'ajout échoue"),
                () -> Assertions.assertEquals(0.0, portefeuille.getSolde(), "Le solde n'a pas changée"));
    }

    /**
     * Test d'achat d'actions avec un solde insuffisant
     */
    @Test
    final void testAchatActionSoldeInsuffisant() {
        // Arrange
        final double solde = 149;
        final float valeuraction = 50;
        final Jour jour = new Jour(2025, 143);
        final Portefeuille portefeuille = new Portefeuille(CLIENT);
        final ActionSimple action = new ActionSimple("Total");

        action.enrgCours(jour, valeuraction);
        portefeuille.provisionner(solde);

        // Action
        boolean success = portefeuille.acheterAction(action, 6, jour);

        // Assert
        Assertions.assertFalse(success, "Achat refusée, solde insuffisant");
    }

    /**
     * Test d'achat réussi et solde débité
     */
    @Test
    final void testAchatActionReussi() {
        // Arrange
        final double solde = 151;
        final float valeuraction = 50;
        final Jour jour = new Jour(2025, 143);
        final Portefeuille portefeuille = new Portefeuille(CLIENT);
        final ActionSimple action = new ActionSimple("Total");

        action.enrgCours(jour, valeuraction);
        portefeuille.provisionner(solde);

        // Action
        boolean success = portefeuille.acheterAction(action, 3, jour);

        // Assert
        Assertions.assertAll("Achat réussi",
                () -> Assertions.assertTrue(success, "Achat réussi"),
                () -> Assertions.assertEquals(solde - (3 * valeuraction), portefeuille.getSolde(),
                        "Le solde a été débité"));
    }

    /**
     * Test d'achat d'action avec un nombre négatif ou nul
     */
    @Test
    final void testAchatActionNegatif() {
        // Arrange
        final double solde = 151;
        final float valeuraction = 50;
        final Jour jour = new Jour(2025, 143);
        final Portefeuille portefeuille = new Portefeuille(CLIENT);
        final ActionSimple action = new ActionSimple("Total");

        action.enrgCours(jour, valeuraction);
        portefeuille.provisionner(solde);

        // Action
        boolean success = portefeuille.acheterAction(action, -4, jour);

        // Assert
        Assertions.assertFalse(success, "Achat refusée, le nombre d'action est négatif ou nul");
    }

    @Test
    final void testVenteReussie() {
        // Arrange
        final double solde = 1000;
        final float valAction = 160;
        final Jour jour = new Jour(2025, 130);
        final Portefeuille portefeuille = new Portefeuille(CLIENT);
        final ActionSimple action = new ActionSimple("Airbus");
        action.enrgCours(jour, valAction);
        portefeuille.provisionner(solde);
        portefeuille.acheterAction(action, 4, jour);

        // Action
        boolean success = portefeuille.vendreAction(action, 3, jour);

        // Assert
        Assertions.assertTrue(success, "Vente reussie");
        Assertions.assertEquals(1, portefeuille.getActions().getOrDefault(action, 0));
    }

    @Test
    final void testVenteEchouePasAssezDaction() {
        // Arrange
        final double solde = 1000;
        final float valAction = 160;
        final Jour jour = new Jour(2025, 130);
        final Portefeuille portefeuille = new Portefeuille(CLIENT);
        final ActionSimple action = new ActionSimple("Airbus");
        action.enrgCours(jour, valAction);
        portefeuille.provisionner(solde);
        portefeuille.acheterAction(action, 2, jour);

        // Action
        boolean success = portefeuille.vendreAction(action, 4, jour);

        // Assert
        Assertions.assertFalse(success, "Vente echouée, pas assez d'action possédée");
    }

    @Test
    final void testVenteEchouePasAction() {
        // Arrange
        final double solde = 1000;
        final float valAction = 160;
        final float valActionDeux = 50;
        final Jour jour = new Jour(2025, 130);
        final Portefeuille portefeuille = new Portefeuille(CLIENT);
        final ActionSimple action = new ActionSimple("Airbus");
        final ActionSimple actionDeux = new ActionSimple("total");
        action.enrgCours(jour, valAction);
        action.enrgCours(jour, valActionDeux);
        portefeuille.provisionner(solde);
        portefeuille.acheterAction(actionDeux, 2, jour);

        // Action
        boolean success = portefeuille.vendreAction(action, 1, jour);

        // Assert
        Assertions.assertFalse(success, "Vente echouée, action non possédée");
    }
}
