package liste;

public class ListeSimple {
    private long size;
    Noeud tete;

    /**
     * Donne le nombre d'éléments de la liste.
     * @return le nombre d'éléments actuellement présents dans la liste
     */
    public long getSize() {
        return size;
    }

    /**
     * Ajoute un élément en tête de liste.
     * @param element l'entier à ajouter
     */
    public void ajout(int element) {
        tete = new Noeud(element, tete);
        size++;
    }

    /**
     * Remplace la première occurrence d'un élément par une nouvelle valeur.
     * @param element l'élément recherché
     * @param nouvelleValeur la valeur qui remplace l'élément trouvé
     */
    public void modifiePremier(Object element, Object nouvelleValeur) {
        Noeud courant = tete;
        while (courant != null && !courant.getElement().equals(element))
            courant = courant.getSuivant();
        if (courant != null)
            courant.setElement(nouvelleValeur);
    }

    /**
     * Remplace toutes les occurrences d'un élément par une nouvelle valeur.
     * @param element l'élément recherché
     * @param nouvelleValeur la valeur qui remplace chaque élément trouvé
     */
    public void modifieTous(Object element, Object nouvelleValeur) {
        Noeud courant = tete;
        while (courant != null) {
            if (courant.getElement().equals(element))
                courant.setElement(nouvelleValeur);
            courant = courant.getSuivant();
        }
    }

    /**
     * Donne une représentation textuelle de la liste.
     * @return la liste des éléments sous forme de chaîne de caractères
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("ListeSimple(");
        Noeud n = tete;
        while (n != null) {
            sb.append(n);
            n = n.getSuivant();
            if (n != null)
                sb.append(", ");
        }
        sb.append(")");
        return sb.toString();
    }

    /**
     * Supprime la première occurrence d'un élément de la liste.
     * @param element l'élément à supprimer
     */
    public void supprimePremier(Object element) {
        if (tete != null) {
            if (tete.getElement().equals(element)) {
                tete = tete.getSuivant();
                size--;
                return;
            }
            Noeud precedent = tete;
            Noeud courant = tete.getSuivant();
            while (courant != null && !courant.getElement().equals(element)) {
                precedent = precedent.getSuivant();
                courant = courant.getSuivant();
            }
            if (courant != null) {
                precedent.setSuivant(courant.getSuivant());
                size--;
            }
        }
    }

    /**
     * Supprime toutes les occurrences d'un élément de la liste.
     * @param element l'entier à supprimer
     */
    public void supprimeTous(int element) {
       tete = supprimeTousRecurs(element, tete);
    }

    private Noeud supprimeTousRecurs(Object element, Noeud noeud) {
        if (noeud != null) {
            Noeud suiteListe = supprimeTousRecurs(element, noeud.getSuivant());
            if (noeud.getElement().equals(element)) {
                size--;
                return suiteListe;
            } else {
                noeud.setSuivant(suiteListe);
                return noeud;
            }
        } else return null;
    }

    /**
     * Donne l'avant-dernier nœud de la liste.
     * @return l'avant-dernier nœud, ou null si la liste a moins de deux éléments
     */
    public Noeud getAvantDernier() {
        if (tete == null || tete.getSuivant() == null)
            return null;
        else {
            Noeud courant = tete;
            Noeud suivant = courant.getSuivant();
            while (suivant.getSuivant() != null) {
                courant = suivant;
                suivant = suivant.getSuivant();
            }
            return courant;
        }
    }

    /**
     * Inverse l'ordre des éléments de la liste.
     */
    public void inverser() {
        Noeud precedent = null;
        Noeud courant = tete;
        while (courant != null) {
            Noeud next = courant.getSuivant();
            courant.setSuivant(precedent);
            precedent = courant;
            courant = next;
        }
        tete = precedent;
    }

    /**
     * Donne le nœud précédant un nœud donné de la liste.
     * @param r un nœud appartenant à la liste
     * @return le nœud précédant r dans la liste
     */
    public Noeud getPrecedent(Noeud r) {
    // la liste n'est pas vide puisqu'on transmet un Node de la liste et le Node existe obligatoirement
        Noeud precedent = tete;
        Noeud courant = precedent.getSuivant();
        while (courant != r) {
            precedent = courant;
            courant = courant.getSuivant();
        }
        return precedent;
    }

    /**
     * Échange la position de deux nœuds de la liste.
     * @param r1 le premier nœud à échanger
     * @param r2 le second nœud à échanger
     */
    public void echanger(Noeud r1, Noeud r2) {
        if (r1 == r2)
            return;
        Noeud precedentR1, precedentR2;
        if (r1 != tete && r2 != tete) {
            precedentR1 = getPrecedent(r1);
            precedentR2 = getPrecedent(r2);
            precedentR1.setSuivant(r2);
            precedentR2.setSuivant(r1);
        } else if (r1 == tete) {
            precedentR2 = getPrecedent(r2);
            precedentR2.setSuivant(tete);
            tete = r2;
        }
        else {
            precedentR1 = getPrecedent(r1);
            precedentR1.setSuivant(tete);
            tete = r1;
        }
        Noeud temp = r2.getSuivant();
        r2.setSuivant(r1.getSuivant());
        r1.setSuivant(temp);
    }

}