package view;

import javax.swing.*;
import java.awt.*;

public class VueRapport extends JPanel {
    private JButton genererRapportBtn;
    private JTextArea rapportArea;

    public VueRapport() {
        // Initialiser les composants de la vue
        setLayout(new BorderLayout());
        setBackground(new Color(240, 240, 240));

        // Initialiser le bouton de génération de rapport
        genererRapportBtn = new JButton("Générer Rapport");
        styleButton(genererRapportBtn);

        // Initialiser la zone de texte pour afficher le rapport
        rapportArea = new JTextArea();
        rapportArea.setEditable(false);
        rapportArea.setFont(new Font("Arial", Font.PLAIN, 14));
        rapportArea.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));

        // Ajouter les composants à la vue
        JPanel rapportPanel = new JPanel(new BorderLayout());
        rapportPanel.setBackground(new Color(240, 240, 240));
        rapportPanel.add(genererRapportBtn, BorderLayout.NORTH);
        rapportPanel.add(new JScrollPane(rapportArea), BorderLayout.CENTER);

        add(rapportPanel, BorderLayout.CENTER);
    }

    private void styleButton(JButton button) {
        button.setBackground(new Color(0, 120, 215));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setFocusPainted(false);
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(0, 90, 170));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(0, 120, 215));
            }
        });
    }

    public JButton getGenererRapportBtn() {
        return genererRapportBtn;
    }

    public JTextArea getRapportArea() {
        return rapportArea;
    }
}
