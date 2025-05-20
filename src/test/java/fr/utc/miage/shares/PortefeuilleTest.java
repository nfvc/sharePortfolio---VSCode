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
     * client.
    */
    private static final Client CLIENT = new Client("Aymane", "Akcha", "Toulouse", "0456377890", "akcha@outlook.fr", "akcha");

    /**
     * Test Constructeur
    */
     @Test
    final void testConstructorSuccess() {
        //Arrange
        final Portefeuille portefeuille = new Portefeuille(CLIENT);

        //Action
        final String expectedNomClient = "Aymane";
        final String expectedPrenomClient = "Akcha";
        


        Assertions.assertAll("Vérification infos du constructeur",
            () -> Assertions.assertEquals(expectedNomClient, portefeuille.getClient().getNom(), "Le nom correspond à celui du constructeur"),
            () -> Assertions.assertEquals(expectedPrenomClient, portefeuille.getClient().getPrenom(), "Le prénom correspond à celui du constructeur")
        );
    }

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
            () -> Assertions.assertTrue(actualActions.isEmpty(), "Aucune action présente")
        );
    }

    @Test
    void testProvisionnerPositif() {
        //Arrange
        Portefeuille portefeuille = new Portefeuille(CLIENT);
        final double valeurattendue = 100;
        final double valeurajoutee = 100;

        //Action
        boolean success = portefeuille.provisionner(valeurajoutee);

        //Assert
        Assertions.assertAll("Ajout d'une valeur négative",
            () -> Assertions.assertTrue(success, "L'ajout à été réussi"),
            () -> Assertions.assertEquals(valeurattendue, portefeuille.getSolde(), "Le solde à atteint la valeur attendu")
        );
    }

    @Test
    void testProvisionnerNegatif() {
        Portefeuille portefeuille = new Portefeuille(CLIENT);
        final double valeurAjoutee = -50;

        boolean nosuccess = portefeuille.provisionner(valeurAjoutee);

        Assertions.assertAll("Ajout d'une valeur négative",
            () -> Assertions.assertFalse(nosuccess, "L'ajout échoue"),
            () -> Assertions.assertEquals(0.0, portefeuille.getSolde(), "Le solde n'a pas changée")
        );
    }

    
}
