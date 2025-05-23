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

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TransactionTest {
    
    /**
     * Test Constructeur
     */
    @Test
    void testConstructorSuccess() {
        //Arrange
        Action action = new ActionSimple("Total");
        Jour jour = new Jour(2025, 143);
        
        //Act
        Transaction transaction = new Transaction(action, 3, jour, 150f, Transaction.Type.ACHAT);

        //Assert
        Assertions.assertAll("Transaction correctement créée",
            () -> Assertions.assertEquals(action, transaction.getAction(), "L'action correspond bien à celle du constructeur"),
            () -> Assertions.assertEquals(3, transaction.getQuantite(), "La quantité correspond bien à celle du constructeur"),
            () -> Assertions.assertEquals(jour, transaction.getJour(), "Le jour correspond bien à celui du constructeur"),
            () -> Assertions.assertEquals(150f, transaction.getMontant(), "Le montant correspond bien à celui du constructeur"),
            () -> Assertions.assertEquals(Transaction.Type.ACHAT, transaction.getType(), "Le type de transaction correspond bien à celui du constructeur")
        );
    }
}
