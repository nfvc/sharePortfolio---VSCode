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

    public final boolean provisionner(final double argent) {
        if (argent > 0) {
            this.solde += argent;
            return true;
        }
        return false;
    }

    public final boolean acheterAction(final Action action, final int nombre, final Jour jour) {
        float montant = nombre * action.valeur(jour);
        if (this.solde >= montant && nombre > 0) {
            this.solde -= montant;
            this.actions.put(action, this.actions.getOrDefault(action, 0) + nombre);
            return true;
        }
        return false;
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
