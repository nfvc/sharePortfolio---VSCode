/*
 * Copyright 2024 David Navarre &lt;David.Navarre at irit.fr&gt;.
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

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import fr.utc.miage.shares.Transaction.Type;

class HistoriqueTest {
    
    private static final Transaction TRANSACTION_ACHAT = new Transaction( new ActionSimple("Total"),3,new Jour(2025, 143),150,Type.ACHAT);
    private static final Transaction TRANSACTION_VENTE = new Transaction( new ActionSimple("Total"),3,new Jour(2025, 143),150,Type.VENTE);

    /**
     * Test afficher l'historique de transaction
     */
    @Test
    void testGetTransactions() {
        // Arrange
        Historique historique = new Historique();

        // Act
        historique.ajouterTransaction(TRANSACTION_ACHAT);
        List<Transaction> transactions = historique.getTransactions();

        // Assert
        Assertions.assertAll("La transaction est affichée dans l'historique",
            () -> Assertions.assertFalse(transactions.isEmpty(), "L'historique ne doit pas être vide"),
            () -> Assertions.assertEquals(1, transactions.size(), "L'historique doit contenir une transaction"),
            () -> Assertions.assertEquals(TRANSACTION_ACHAT, transactions.get(0), "La transaction dans l'historique est la même que celle que je viens d'ajouter")
        );
    }

    /**
     * Test afficher historique vide
     */
    @Test
    void testHistoriqueVide() {
        // Arrange
        Historique historique = new Historique();

        // Act
        List<Transaction> transactions = historique.getTransactions();

        // Assert
        Assertions.assertTrue(transactions.isEmpty(), "L'historique est vide");
    }
    
    /**
     * Test Ajouter une transaction d'achat à l'historique
     */
    @Test
    void testAjouterTransactionAchatReussie() {
        // Arrange
        Historique historique = new Historique();

        // Act
        historique.ajouterTransaction(TRANSACTION_ACHAT);

        // Assert
        Assertions.assertAll("La transaction à été ajoutée dans l'historique",
            () -> Assertions.assertEquals(1, historique.getTransactions().size(), "Une transaction doit être ajoutée"),
            () -> Assertions.assertTrue(historique.getTransactions().contains(TRANSACTION_ACHAT), "L'historique doit contenir la transaction ajoutée")
        );
    }

    /**
     * Test Ajouter une transaction de vente à l'historique
     */
    @Test
    void testAjouterTransactionVenteReussie() {
        // Arrange
        Historique historique = new Historique();

        // Act
        historique.ajouterTransaction(TRANSACTION_VENTE);

        // Assert
        Assertions.assertAll("La transaction à été ajoutée dans l'historique",
            () -> Assertions.assertEquals(1, historique.getTransactions().size(), "Une transaction doit être ajoutée"),
            () -> Assertions.assertTrue(historique.getTransactions().contains(TRANSACTION_VENTE), "L'historique doit contenir la transaction ajoutée")
        );
    }
}
