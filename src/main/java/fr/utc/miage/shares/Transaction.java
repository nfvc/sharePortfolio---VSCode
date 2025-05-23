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

/**
 * Représente une transaction d'achat ou de vente d'actions.
 */
public class Transaction {

    /**
     * Types de transactions possibles : achat ou vente.
     */
    public enum Type {
        /**
         * Transaction d'achat d'actions.
         */
        ACHAT,
        /**
         * Transaction de vente d'actions.
         */
        VENTE
    }

    private Action action;

    private int quantite;

    private Jour jour;

    private float montant;

    private Type type;


    /**
     * Crée une nouvelle transaction.
     *
     * @param action l'action concernée par la transaction
     * @param quantite le nombre d'actions
     * @param jour le jour de la transaction
     * @param montant le montant total de la transaction
     * @param type le type de transaction (achat ou vente)
     */
    public Transaction(Action action, int quantite, Jour jour, float montant, Type type) {
        this.action = action;
        this.quantite = quantite;
        this.jour = jour;
        this.montant = montant;
        this.type = type;
    }

    /**
     * Récupère l'action concernée par la transaction.
     *
     * @return l'action concernée
     */
    public Action getAction() {
        return action;
    }

    /**
     * Récupère la quantité d'actions de la transaction.
     *
     * @return la quantité d'actions
     */
    public int getQuantite() {
        return quantite;
    }

    /**
     * Récupère le jour de la transaction.
     *
     * @return le jour de la transaction
     */
    public Jour getJour() {
        return jour;
    }

    /**
     * Récupère le montant total de la transaction.
     *
     * @return le montant de la transaction
     */
    public float getMontant() {
        return montant;
    }

    /**
     * Récupère le type de la transaction.
     *
     * @return le type (achat ou vente)
     */
    public Type getType() {
        return type;
    }

}
