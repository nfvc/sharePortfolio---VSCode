package fr.utc.miage.shares;

import java.util.ArrayList;
import java.util.List;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Représente une action composée, constituée d'un panier d'actions simples pondérées.
 */
public class ActionComposee extends Action {

     // Map des actions simples et leurs pourcentages associés (entre 0 et 100)
    private final Map<ActionSimple, Float> composition;

    public ActionComposee(String libelle) {
        super(libelle);
        this.composition = new HashMap<>();
    }

    /**
     * Ajoute une action simple avec un pourcentage donné.
     * Si l'action est déjà présente ou si le total dépasse 100 %, elle n'est pas ajoutée.
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
     */

    public float getPourcentageTotal() {
        float total = 0f;
        for (float p : composition.values()) {
            total += p;
        }
        return total;
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
        return sb.toString();
    }
}
