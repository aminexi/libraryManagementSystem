package controller;

import exceptions.EmpruntNotFoundException;
import exceptions.LivreNotFoundException;
import exceptions.UtilisateurNotFoundException;
import model.*;
import view.GestionBibliothequeView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class ControleurRapport {

    private final LivreModel livreModel;
    private final UtilisateurModel utilisateurModel;
    private final EmpruntModel empruntModel;
    private final RetourModel retourModel;
    private final GestionBibliothequeView vue;

    public ControleurRapport(LivreModel livreModel, UtilisateurModel utilisateurModel, EmpruntModel empruntModel, RetourModel retourModel, GestionBibliothequeView vue) {
        this.livreModel = livreModel;
        this.utilisateurModel = utilisateurModel;
        this.empruntModel = empruntModel;
        this.retourModel = retourModel;
        this.vue = vue;
        addListeners();
    }

    private void addListeners() {
        vue.getGenererRapportBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                genererRapportComplet();
            }
        });
    }

    private void genererRapportComplet() {
        StringBuilder rapport = new StringBuilder("Rapport complet de la bibliothèque\n\n");
        rapport.append(genererStatistiquesGenerales())
                .append("\n")
                .append(genererRapportLivres())
                .append("\n")
                .append(genererRapportUtilisateurs())
                .append("\n")
                .append(genererRapportEmprunts())
                .append("\n")
                .append(genererRapportRetours());

        vue.getRapportArea().setText(rapport.toString());
    }

    private String genererStatistiquesGenerales() {
        StringBuilder stats = new StringBuilder("Statistiques générales :\n");
        stats.append("Nombre total de livres : ").append(livreModel.getListe().size()).append("\n");
        stats.append("Nombre de livres disponibles : ").append(livreModel.getListe().stream().filter(Livre::isDisponible).count()).append("\n");
        stats.append("Nombre total d'utilisateurs : ").append(utilisateurModel.getListe().size()).append("\n");
        stats.append("Nombre total d'emprunts : ").append(empruntModel.getListe().size()).append("\n");
        stats.append("Nombre total de retours : ").append(retourModel.getListe().size()).append("\n");
        return stats.toString();
    }

    private String genererRapportLivres() {
        StringBuilder rapport = new StringBuilder("Rapport des livres :\n");

        // Livres les plus empruntés
        Map<Livre, Long> livresEmpruntes = empruntModel.getListe().stream()
                .collect(Collectors.groupingBy(emprunt -> {
                    try {
                        return livreModel.rechercherParID(emprunt.getLivreId());
                    } catch (LivreNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }, Collectors.counting()));

        rapport.append("Top 5 des livres les plus empruntés :\n");
        livresEmpruntes.entrySet().stream()
                .sorted(Map.Entry.<Livre, Long>comparingByValue().reversed())
                .limit(5)
                .forEach(entry -> rapport.append("- ").append(entry.getKey().getTitre())
                        .append(" (").append(entry.getValue()).append(" emprunts)\n"));

        // Genres les plus populaires
        Map<String, Long> genresPopulaires = livreModel.getListe().stream()
                .collect(Collectors.groupingBy(Livre::getGenre, Collectors.counting()));

        rapport.append("\nTop 3 des genres les plus populaires :\n");
        genresPopulaires.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(3)
                .forEach(entry -> rapport.append("- ").append(entry.getKey())
                        .append(" (").append(entry.getValue()).append(" livres)\n"));

        return rapport.toString();
    }

    private String genererRapportUtilisateurs() {
        StringBuilder rapport = new StringBuilder("Rapport des utilisateurs :\n");

        // Utilisateurs les plus actifs
        Map<Utilisateur, Long> utilisateursActifs = empruntModel.getListe().stream()
                .collect(Collectors.groupingBy(emprunt -> {
                    try {
                        return utilisateurModel.rechercherParId(emprunt.getUtilisateurId());
                    } catch (UtilisateurNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }, Collectors.counting()));

        rapport.append("Top 5 des utilisateurs les plus actifs :\n");
        utilisateursActifs.entrySet().stream()
                .sorted(Map.Entry.<Utilisateur, Long>comparingByValue().reversed())
                .limit(5)
                .forEach(entry -> rapport.append("- ").append(entry.getKey().getNom())
                        .append(" (").append(entry.getValue()).append(" emprunts)\n"));

        return rapport.toString();
    }

    private String genererRapportEmprunts() {
        StringBuilder rapport = new StringBuilder("Rapport des emprunts :\n");

        long empruntsEnCours = empruntModel.getListe().stream()
                .filter(emprunt -> !retourModel.getListe().stream()
                        .anyMatch(retour -> retour.getEmpruntId() == emprunt.getId()))
                .count();

        rapport.append("Nombre d'emprunts en cours : ").append(empruntsEnCours).append("\n");

        // Durée moyenne des emprunts
        OptionalDouble dureeMoyenne = retourModel.getListe().stream()
                .mapToLong(retour -> {
                    Emprunt emprunt = null;
                    try {
                        emprunt = empruntModel.rechercherParID(retour.getEmpruntId());
                    } catch (EmpruntNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    return LocalDate.parse(retour.getDateRetour()).toEpochDay() - LocalDate.parse(emprunt.getDateEmprunt()).toEpochDay();
                })
                .average();

        rapport.append("Durée moyenne des emprunts : ")
                .append(dureeMoyenne.isPresent() ? String.format("%.2f jours", dureeMoyenne.getAsDouble()) : "N/A")
                .append("\n");

        return rapport.toString();
    }

    private String genererRapportRetours() {
        StringBuilder rapport = new StringBuilder("Rapport des retours :\n");

        // Calcul des pénalités totales
        double penalitesTotales = retourModel.getListe().stream()
                .mapToDouble(Retour::getPenalite)
                .sum();

        rapport.append("Total des pénalités : ").append(String.format("%.2f DH", penalitesTotales)).append("\n");

        // Nombre de retours en retard
        long retoursEnRetard = retourModel.getListe().stream()
                .filter(retour -> retour.getPenalite() > 0)
                .count();

        rapport.append("Nombre de retours en retard : ").append(retoursEnRetard).append("\n");

        return rapport.toString();
    }
}