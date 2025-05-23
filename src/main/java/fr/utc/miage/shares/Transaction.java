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

public class Transaction {
    public enum Type {ACHAT, VENTE}
    private Action action;
    private int quantite;
    private Jour jour;
    private float montant;
    private Type type;

    public Transaction(Action action, int quantite, Jour jour, float montant, Type type) {
        this.action = action;
        this.quantite = quantite;
        this.jour = jour;
        this.montant = montant;
        this.type = type;
    }

    public Action getAction() {
        return action;
    }

    public int getQuantite() {
        return quantite;
    }

    public Jour getJour() {
        return jour;
    }

    public float getMontant() {
        return montant;
    }

    public Type getType() {
        return type;
    }

}
