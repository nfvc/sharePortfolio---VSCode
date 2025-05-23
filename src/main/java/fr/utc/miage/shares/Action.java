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

import java.util.Objects;

/**
 * This class embeds thecommon behavior of any Action object.
 *
 * @author David Navarre &lt;David.Navarre at irit.fr&gt;
 */
public abstract class Action {

    private final String libelle;

    /**
     * Récupère le libellé de l'action.
     *
     * @return le libellé de l'action
     */
    public String getLibelle() {
        return libelle;
    }

    /**
     * Construit un objet Action à partir d'un libellé.
     *
     * @param libelle le libellé de l'action
     */
    protected Action(final String libelle) {
        this.libelle = libelle;
    }

    /**
     * Calcule la valeur de l'action pour un jour donné.
     *
     * @param j le jour pour lequel calculer la valeur
     * @return la valeur de l'action au jour donné
     */
    public abstract float valeur(Jour j);

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.libelle);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Action other = (Action) obj;
        return Objects.equals(this.libelle, other.libelle);
    }

    @Override
    public String toString() {
        return this.getLibelle();
    }
}
