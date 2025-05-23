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
