package fr.utc.miage.shares;

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
        


        //Assert
        Assertions.assertEquals(expectedNomClient, portefeuille.getClient()
                .getNom(), "Basic construction");
        Assertions.assertEquals(expectedPrenomClient, portefeuille.getClient()
                .getPrenom(), "Basic construction");
    }

    @Test
    void testProvisionnerPositif() {
        //Arrange
        Portefeuille portefeuille = new Portefeuille(CLIENT);
        final double valeurattendue = 100;
        final double valeurajouter = 100;

        //Action
        boolean success = portefeuille.provisionner(valeurajouter);
        //Assert
        Assertions.assertEquals(valeurattendue, portefeuille.getSolde(), "Ajout");
        Assertions.assertTrue(success);
    }

    
}
