package fr.utc.miage.shares;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PortefeuilleTest {

    /**
     * client.
    */
    private static final Client CLIENT = new Client("Aymane", "Akcha", "Toulouse", "0456377890", "akcha@outlook.fr", "akcha");
    /**
     * deuxieme client.
    */
    private static final Client DEUXIEME_CLIENT = new Client("Negroni","Giani", "Toulouse", "0987769065", "giani@outlook.fr", "giani");

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


        //Assert
        Assertions.assertEquals(expectedNomClient, portefeuille.getClient()
                .getNom(), "Basic construction");
        Assertions.assertEquals(expectedPrenomClient, portefeuille.getClient()
                .getPrenom(), "Basic construction");
    }
}
