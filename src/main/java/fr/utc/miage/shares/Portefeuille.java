/*
 * Copyright 2025 David Navarre <David.Navarre@irit.fr>.
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

import java.util.HashMap;
import java.util.Map;

public class Portefeuille {

    /**
     * Le client propriétaire du portefeuille
     */
    private final Client client;
    private final HashMap<Action, Integer> actions;
    private double solde;

    public Portefeuille(Client client) {
        this.client = client;
        this.actions = new HashMap<>();
        this.solde = 0;
    }

    /**
     * Récupère le client propriétaire du portefeuille.
     *
     * @return Le client propriétaire
     */
    public final Client getClient() {
        return client;
    }

    public Historique getHistorique() {
        return historique;
    }

    public final double getSolde() {
        return solde;
    }

    /**
     * Récupère la liste des actions détenues.
     *
     * @return La map des actions avec leurs quantités
     */
    public final Map<Action, Integer> getActions() {
        return actions;
    }

    /**
     * Ajoute de l'argent au solde du portefeuille.
     *
     * @param argent Le montant à ajouter au solde (doit être positif)
     * @return true si le provisionnement a réussi, false sinon
     */
    public final boolean provisionner(final double argent) {
        if (argent > 0) {
            this.solde += argent;
            return true;
        }
        return false;
    }

    /**
     * Achète des actions pour le portefeuille.
     *
     * @param action L'action à acheter
     * @param nombre Le nombre d'actions à acheter
     * @param jour Le jour de l'achat
     * @return true si l'achat a réussi, false sinon
     */
    public final boolean acheterAction(final Action action, final int nombre, final Jour jour) {
        float montant = nombre * action.valeur(jour);
        if (this.solde >= montant && nombre > 0) {
            this.solde -= montant;
            this.actions.put(action, this.actions.getOrDefault(action, 0) + nombre);

            Transaction transaction = new Transaction(action, nombre, jour, montant, Transaction.Type.ACHAT);
            this.historique.ajouterTransaction(transaction);

            return true;
        }
        return false;
    }

    /**
     * Calcule la valeur totale du portefeuille au jour actuel.
     * Additionne la valeur de toutes les actions détenues en fonction de leur cours du jour.
     *
     * @return La valeur totale du portefeuille
     */
    public final double consulterPortefeuille() {
        Jour dateJour = new Jour(java.time.LocalDate.now().getYear(), java.time.LocalDate.now().getDayOfMonth());
        double res = 0;
        for (Map.Entry<Action, Integer> entrer : this.actions.entrySet()) {
            Action action = entrer.getKey();
            int nombre = entrer.getValue();
            res = res + (action.valeur(dateJour) * nombre);
        }
        return res;
    }

    public final boolean vendreAction(final Action action, final int nombre, final Jour jour) {
        if (nombre < 0)
            return false;

        if (this.actions.getOrDefault(action, 0) >= nombre) {
            float montant = nombre * action.valeur(jour);
            this.solde += montant;
            this.actions.put(action, this.actions.getOrDefault(action, 0) - nombre);
            return true;
        }

        return false;
    }
}
