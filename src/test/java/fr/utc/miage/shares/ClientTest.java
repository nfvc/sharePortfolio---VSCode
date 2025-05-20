package fr.utc.miage.shares;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.naming.InsufficientResourcesException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClientTest {

    private static final String NOM = "Dupont";
    private static final String PRENOM = "Jean";
    private static final String ADRESSE = "3 rue manufacture";
    private static final String MAIL = "jean.dupont@email.com";
    private static final String TELEPHONE = "0123456789";
    private static final String PASSWORD = "password123";


    private static final String NOM2 = "Martin";
    private static final String PRENOM2 = "Luc";
    private static final String ADRESSE2 = "2 rue arsenal";
    private static final String MAIL2 = "luc.martin@email.com";
    private static final String TELEPHONE2 = "0987654321";
    private static final String PASSWORD2 = "newPassword456";




    @Test
    void testConstructor() {
        final Client client = new Client(NOM,PRENOM,ADRESSE,MAIL,TELEPHONE,PASSWORD);

        final String nom_attendu = "Dupont";
        final String prenom_attendu = "Jean";
        final String adresse_attendu = "3 rue manufacture";
        final String mail_attendu = "jean.dupont@email.com";
        final String tel_attendu = "0123456789";
        final String password_attendu = "password123";

        assertEquals(nom_attendu, client.getNom());
        assertEquals(prenom_attendu, client.getPrenom());
        assertEquals(adresse_attendu, client.getAdresse());
        assertEquals(mail_attendu, client.getMail());
        assertEquals(tel_attendu, client.getTelephone());
        assertEquals(password_attendu, client.getPassword());
    }

    @Test
    void testSetNom() {
        final Client client = new Client(NOM,PRENOM,ADRESSE,MAIL,TELEPHONE,PASSWORD);

        final String nom_attendu = NOM2;
        client.setNom(NOM2);

        assertEquals(nom_attendu, client.getNom());
    }

    @Test
    void testSetPrenom() {
        final Client client = new Client(NOM,PRENOM,ADRESSE,MAIL,TELEPHONE,PASSWORD);

        final String prenom_attendu = PRENOM2;
        client.setPrenom(PRENOM2);

        assertEquals(prenom_attendu, client.getPrenom());
    }

    @Test
    void testSetAdresse() {
        final Client client = new Client(NOM,PRENOM,ADRESSE,MAIL,TELEPHONE,PASSWORD);

        final String adresse_attendu = ADRESSE2;
        client.setAdresse(ADRESSE2);

        assertEquals(adresse_attendu, client.getAdresse());
    }

    @Test
    void testSetMail() {
        final Client client = new Client(NOM,PRENOM,ADRESSE,MAIL,TELEPHONE,PASSWORD);

        final String mail_attendu = MAIL2;
        client.setMail(MAIL2);

        assertEquals(mail_attendu, client.getMail());
    }

    @Test
    void testSetTelephone() {
        final Client client = new Client(NOM,PRENOM,ADRESSE,MAIL,TELEPHONE,PASSWORD);

        final String tel_attendu = TELEPHONE2;
        client.setTelephone(TELEPHONE2);

        assertEquals(tel_attendu, client.getTelephone());
    }

    @Test
    void testSetPassword() {
        final Client client = new Client(NOM,PRENOM,ADRESSE,MAIL,TELEPHONE,PASSWORD);

        final String mdp_attendu = PASSWORD2;
        client.setPassword(PASSWORD2);

        assertEquals(mdp_attendu, client.getPassword());
    }
}
