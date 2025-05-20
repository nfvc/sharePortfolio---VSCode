package fr.utc.miage.shares;

import java.util.HashMap;
import java.util.Map;

public class Portefeuille {
    
    private final Client client;

    private final HashMap<Action, Integer> actions;

    private double solde;

    public Portefeuille(Client client) {
        this.client = client;
        this.actions = new HashMap<>();
        this.solde = 0;
    }

    public final Client getClient() {
        return client;
    }

    public final double getSolde() {
        return solde;
    }

    public final Map<Action, Integer> getActions() {
        return actions;
    }

}
