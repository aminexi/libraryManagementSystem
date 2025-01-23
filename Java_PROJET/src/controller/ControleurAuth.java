package controller;

import model.UtilisateurModel;
import view.GestionBibliothequeView;
import exceptions.AuthException;


public class ControleurAuth {

    private UtilisateurModel utilisateurModel;
    private GestionBibliothequeView vue;

    public ControleurAuth(UtilisateurModel utilisateurModel, GestionBibliothequeView vue) {
        this.utilisateurModel = utilisateurModel;
        this.vue = vue;
    }

    public void authentifier(String nomUtilisateur, String motDePasse) throws AuthException {
        try {
            if (nomUtilisateur.equals("admin") && motDePasse.equals("admin")) {
                
                vue.setVisible(true);
            } else {
                throw new AuthException("Nom d'utilisateur ou mot de passe incorrect.");
            }
        } catch (Exception e) {
            throw new AuthException("Erreur lors de l'authentification.");
        }
    }
}
