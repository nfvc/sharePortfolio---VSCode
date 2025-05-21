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
     * Supprime une action simple de la composition.
     *
     * @param action l'action à supprimer
     */
    public void supprimerActionSimple(ActionSimple action) {
        composition.remove(action);
    }

    /**
     * Modifie le pourcentage d'une action existante, si le total reste <= 100 %.
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
     * Vérifie si la composition atteint exactement 100 %.
     *
     * @return true si total == 100 %, false sinon
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
<<<<<<< HEAD



    public static void main(String[] args) {
        ActionSimple france2 = new ActionSimple("France 2");
        ActionSimple france3 = new ActionSimple("France 3");
        ActionSimple france5 = new ActionSimple("France 5");

        Jour j1 = new Jour(2024, 140);
        france2.enrgCours(j1, 100f);
        france3.enrgCours(j1, 200f);
        france5.enrgCours(j1, 50f);

        ActionComposee franceTV = new ActionComposee("France Télévision");

        franceTV.ajouterActionSimple(france2, 35f);
        franceTV.ajouterActionSimple(france3, 50f);
        franceTV.ajouterActionSimple(france5, 15f);

        System.out.println(franceTV);
        System.out.println("Valeur au jour J : " + franceTV.valeur(j1) + "€");
    }


=======
>>>>>>> cfc36cef29a5fa092a25ba78fede6def4912cf34
}
