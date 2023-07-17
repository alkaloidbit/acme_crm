USE `mspr`;
-- Insertion de données dans la table `client`
INSERT INTO `client` (`CODE_CLIENT`, `NOM`, `PRENOM`, `ADRESSE`, `CODE_POSTAL`, `VILLE`)
VALUES ('C001', 'Dupont', 'Jean', '123 Rue A', '12345', 'Paris'),
       ('C002', 'Martin', 'Sophie', '456 Rue B', '67890', 'Lyon'),
       ('C003', 'Lefevre', 'Paul', '789 Rue C', '54321', 'Marseille');

-- Insertion de données dans la table `commande`
INSERT INTO `commande` (`CODE_CLIENT`, `DATE`)
VALUES ('C001', '2023-07-09 10:00:00'),
       ('C002', '2023-07-09 11:30:00'),
       ('C003', '2023-07-09 14:15:00');
     
-- Insertion de données dans la table `produit`
INSERT INTO `produit` (`CODE_PRODUIT`, `LIBELLE_PRODUIT`, `DESCRIPTION`, `PRIX`)
VALUES ('P001', 'Produit 1', 'Description du produit 1', 10.99),
       ('P002', 'Produit 2', 'Description du produit 2', 19.99),
       ('P003', 'Produit 3', 'Description du produit 3', 15.49);

-- Insertion de données dans la table `detail_commande`
INSERT INTO `detail_commande` (`ID_COMMANDE`, `CODE_PRODUIT`, `QUANTITE`)
VALUES (1, 'P001', 2),
       (1, 'P002', 1),
       (2, 'P003', 3),
       (3, 'P001', 1),
       (3, 'P003', 2);
      
-- Insertion de données dans la table `role`
INSERT INTO `role` (`CODE_ROLE`, `LIBELLE_ROLE`)
VALUES ('R001', 'Admin'),
       ('R002', 'Utilisateur');

-- Insertion de données dans la table `utilisateur`
INSERT INTO `utilisateur` (`LOGIN`, `PSW`, `CODE_ROLE`)
VALUES ('admin', 'password123', 'R001'),
       ('user1', 'pass456', 'R002'),
       ('user2', 'pass789', 'R002');
