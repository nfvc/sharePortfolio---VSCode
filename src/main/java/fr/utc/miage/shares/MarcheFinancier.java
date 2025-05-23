/*
 * Copyright 2025 David Navarre &lt;David.Navarre at irit.fr&gt;.
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

import java.util.ArrayList;

/**
 * Représente le marché financier qui gère l'ensemble des actions disponibles.
 */
public class MarcheFinancier {

    private final ArrayList<Action> actions;

    public MarcheFinancier() {
        this.actions = new ArrayList<>();
    }

    public final ArrayList<Action> getActions() {
        return actions;
    }

    public final void setAction(final Action action) {
        this.actions.add(action);
    }

}