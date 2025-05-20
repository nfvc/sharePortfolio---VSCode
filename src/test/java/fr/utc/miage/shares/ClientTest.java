package fr.utc.miage.shares;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClientTest {

    private Client client;

    @BeforeEach
    void setUp() {
        Client client = new Client("Dupont", "Jean", "3 rue manufacture", "jean.dupont@email.com", "0123456789", "password123");
    }

    @Test
    void testConstructorAndGetters() {
        assertEquals("Dupont", client.getNom());
        assertEquals("Jean", client.getPrenom());
        assertEquals("3 rue manufacture", client.getAdresse());
        assertEquals("jean.dupont@email.com", client.getMail());
        assertEquals("0123456789", client.getTelephone());
        assertEquals("password123", client.getPassword());
    }

    @Test
    void testSetNom() {
        client.setNom("Martin");
        assertEquals("Martin", client.getNom());
    }

    @Test
    void testSetPrenom() {
        client.setPrenom("Luc");
        assertEquals("Luc", client.getPrenom());
    }

    @Test
    void testSetAdresse() {
        client.setAdresse("2 rue arsenal");
        assertEquals("2 rue arsenal", client.getAdresse());
    }

    @Test
    void testSetMail() {
        client.setMail("luc.martin@email.com");
        assertEquals("luc.martin@email.com", client.getMail());
    }

    @Test
    void testSetTelephone() {
        client.setTelephone("0987654321");
        assertEquals("0987654321", client.getTelephone());
    }

    @Test
    void testSetPassword() {
        client.setPassword("newPassword456");
        assertEquals("newPassword456", client.getPassword());
    }
}
