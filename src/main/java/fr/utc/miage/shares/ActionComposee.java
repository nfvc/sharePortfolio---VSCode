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

    // Map des actions simples et leurs pourcentages associés (0 à 100)
    private final Map<ActionSimple, Float> composition;

    public ActionComposee(String libelle) {
        super(libelle);
        this.composition = new HashMap<>();
    }

    /**
     * Ajoute une action simple avec un pourcentage donné.
     *
     * @param action       l'action simple à inclure dans la composition
     * @param pourcentage  le pourcentage attribué à cette action (entre 0 et 100)
     * @throws IllegalArgumentException si l'action est déjà présente ou si le total dépasse 100%
     */
    public void ajouterActionSimple(ActionSimple action, float pourcentage) {
        if (composition.containsKey(action)) {
            throw new IllegalArgumentException("Action déjà présente dans la composition.");
        }

        float total = getPourcentageTotal() + pourcentage;
        if (total > 100f) {
            throw new IllegalArgumentException("La somme des pourcentages dépasse 100%.");
        }

        composition.put(action, pourcentage);
    }

    /**
     * Retourne la somme totale des pourcentages actuels.
     */
    public float getPourcentageTotal() {
        float total = 0f;
        for (float p : composition.values()) {
            total += p;
        }
        return total;
    }

    /**
     * Calcule la valeur de l'action composée pour un jour donné.
     * C'est la somme pondérée des valeurs des actions simples.
     *
     * @param j le jour pour lequel on veut la valeur
     * @return la valeur calculée
     */
    @Override
    public float valeur(Jour j) {
        float valeurTotale = 0f;
        for (Entry<ActionSimple, Float> entry : composition.entrySet()) {
            ActionSimple action = entry.getKey();
            float pourcentage = entry.getValue();
            float valeurAction = action.valeur(j);
            valeurTotale += (pourcentage / 100f) * valeurAction;
        }
        return valeurTotale;
    }

    /**
     * Affiche la composition de l'action composée.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.getLibelle()).append(" :\n");
        for (Map.Entry<ActionSimple, Float> entry : composition.entrySet()) {
            sb.append(" - ")
              .append(entry.getKey().getLibelle())
              .append(" : ")
              .append(entry.getValue())
              .append("%\n");
        }
        return sb.toString();
    }
}
