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

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MarcheFinancierTest {

    @Test
    final void testMarcheFinancierConstructor(){
        MarcheFinancier marcheFinancier = new MarcheFinancier();

        assertNotNull(marcheFinancier);
        assertNotNull(marcheFinancier.getActions());
        assertEquals(0, marcheFinancier.getActions().size());
    }

    @Test
    final void testGetActions() {
        ActionSimple action1 = new ActionSimple("TotalEnergies");
        ActionSimple action2 = new ActionSimple("Air Liquide");

        MarcheFinancier marcheFinancier = new MarcheFinancier();
        ArrayList<Action> listAction = new ArrayList<>();
        listAction.add(action1);
        listAction.add(action2);

        marcheFinancier.getActions().add(action1);
        marcheFinancier.getActions().add(action2);

        assertNotNull(marcheFinancier);
        assertEquals(listAction.size(), marcheFinancier.getActions().size());
    }

    @Test
    final void testSetActions() {
        // Arrange
        MarcheFinancier marcheFinancier = new MarcheFinancier();
        ArrayList<Action> listAction = new ArrayList<>();
        ActionSimple action1 = new ActionSimple("TotalEnergies");

        listAction.add(action1);
        marcheFinancier.setAction(action1);

        assertNotNull(marcheFinancier.getActions());
        assertEquals(listAction.size(), marcheFinancier.getActions().size());
    }
}
