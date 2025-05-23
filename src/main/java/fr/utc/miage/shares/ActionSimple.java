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

import java.util.HashMap;
import java.util.Map;

/**
 * Allows the creation of simple Action objects.
 *
 * @author David Navarre &lt;David.Navarre at irit.fr&gt;
 */
public class ActionSimple extends Action {

    private static final int DEFAULT_ACTION_VALUE = 0;

    /**
     * Attribut de lien, pour le cours de l'action
     */
    private final Map<Jour, Float> mapCours;

    /**
     * Constructeur d'une action simple.
     * Initialise une nouvelle action avec un libellé donné et une map vide pour stocker les cours.
     *
     * @param libelle Le nom/libellé de l'action à créer
     */
    public ActionSimple(final String libelle) {
        super(libelle);
        this.mapCours = new HashMap<>();
    }

    /**
     * Enregistre le cours d'une action pour un jour donné.
     * L'enregistrement n'est possible que si aucun cours n'existe déjà pour ce jour.
     *
     * @param j Le jour pour lequel on souhaite enregistrer le cours
     * @param v La valeur du cours de l'action à enregistrer
     */
    public void enrgCours(final Jour j, final float v) {
        if (!this.mapCours.containsKey(j)) {
            this.mapCours.put(j, v);
        }
    }

    /**
     * Retourne la valeur de l'action pour un jour donné.
     *
     * @param j Le jour pour lequel on souhaite connaître la valeur de l'action
     * @return La valeur de l'action au jour j si elle existe, sinon retourne la valeur par défaut (0)
     */
    @Override
    public float valeur(final Jour j) {
        if (this.mapCours.containsKey(j)) {
            return this.mapCours.get(j);
        } else {
            return DEFAULT_ACTION_VALUE;
        }
    }
}
