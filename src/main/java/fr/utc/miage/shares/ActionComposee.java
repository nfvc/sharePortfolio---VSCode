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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Représente une action composée, constituée d'un panier d'actions simples pondérées.
 */
public class ActionComposee extends Action {

    // Map des actions simples et leurs pourcentages associés (entre 0 et 100)
    private final Map<ActionSimple, Float> composition;

    /**
     * Construit une nouvelle action composée avec un libellé donné.
     * Initialise une composition vide.
     *
     * @param libelle le libellé de l'action composée
     */
    public ActionComposee(String libelle) {
        super(libelle);
        this.composition = new HashMap<>();
    }

    /**
     * Récupère la composition de l'action composée.
     *
     * @return une Map associant chaque action simple à son pourcentage dans la composition
     */
    public Map<ActionSimple,Float> getComposition() {
        return this.composition;
    }


    /**
     * Ajoute une action simple avec un pourcentage donné.
     * Si l'action est déjà présente ou si le total dépasse 100, elle n'est pas ajoutée.
     *
     * @param action l'action simple à ajouter
     * @param pourcentage le pourcentage associé
     */
    public void ajouterActionSimple(ActionSimple action, float pourcentage) {
        if (!composition.containsKey(action)) {
            float total = getPourcentageTotal() + pourcentage;
            if (total <= 100f) {
                composition.put(action, pourcentage);
            }
        }
    }

    /**
     * Supprime une action simple de la composition.
     *
     * @param action l'action à supprimer
     */
    public void supprimerActionSimple(ActionSimple action) {
        composition.remove(action);
    }

    /**
     * Modifie le pourcentage d'une action, si le total reste inferieur ou egal 100.
     *
     * @param action l'action à modifier
     * @param nouveauPourcentage le nouveau pourcentage à affecter
     */
    public void modifierPourcentage(ActionSimple action, float nouveauPourcentage) {
        if (composition.containsKey(action)) {
            float totalSansCetteAction = getPourcentageTotal() - composition.get(action);
            if (totalSansCetteAction + nouveauPourcentage <= 100f) {
                composition.put(action, nouveauPourcentage);
            }
        }
    }

    /**
     * Vérifie si la composition atteint exactement 100.
     *
     * @return true si total == 100 , false sinon
     */
    public boolean estComplete() {
        return getPourcentageTotal() == 100f;
    }

    /**
     * Retourne la valeur de l'action composée pour un jour donné.
     * C'est la somme pondérée des valeurs des actions simples.
     *
     * @param j le jour à évaluer
     * @return la valeur calculée
     */
    @Override
    public float valeur(Jour j) {
        float valeurTotale = 0f;
        for (Entry<ActionSimple, Float> entry : composition.entrySet()) {
            float pourcentage = entry.getValue();
            float valeur = entry.getKey().valeur(j);
            valeurTotale += (pourcentage / 100f) * valeur;
        }
        return valeurTotale;
    }

    /**
     * Calcule la somme des pourcentages actuellement enregistrés.
     *
     * @return le total des pourcentages
     */
    public float getPourcentageTotal() {
        float total = 0f;
        for (float p : composition.values()) {
            total += p;
        }
        return total;
    }

    /**
     * Vérifie si une action simple est déjà présente dans la composition.
     *
     * @param action l'action à vérifier
     * @return true si elle est présente, false sinon
     */
    public boolean contientAction(ActionSimple action) {
        return composition.containsKey(action);
    }

    /**
     * Récupère la liste des actions simples dans la composition.
     *
     * @return liste des actions simples
     */
    public List<ActionSimple> getActionsSimples() {
        return new ArrayList<>(composition.keySet());
    }

    /**
     * Affiche le contenu de la composition.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(getLibelle()).append(" :\n");
        for (Entry<ActionSimple, Float> entry : composition.entrySet()) {
            sb.append(" - ").append(entry.getKey().getLibelle())
              .append(" : ").append(entry.getValue()).append("%\n");
        }
        sb.append("Total : ").append(getPourcentageTotal()).append("%");
        if (!estComplete()) {
            sb.append(" (incomplet)");
        }
        return sb.toString();
    }

}
