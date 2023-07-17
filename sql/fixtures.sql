INSERT INTO `role` (CODE_ROLE, LIBELLE_ROLE) VALUES ("admin", "Admin");
INSERT INTO `role` (CODE_ROLE, LIBELLE_ROLE) VALUES ("user", "Utilisateur");
INSERT INTO utilisateur (LOGIN, PSW, CODE_ROLE) VALUES ("fred", "foo", "admin");

INSERT INTO client (CODE_CLIENT, NOM, PRENOM, ADRESSE, CODE_POSTAL, VILLE) VALUES ("0001", "Client", "Fred", "123 rue de la paix", 75009, "Paris");

INSERT INTO commande (CODE_CLIENT, `DATE`) values ("0001", '2023-07-06');

INSERT INTO detail_commande (ID_COMMANDE, CODE_PRODUIT, QUANTITE) values ("0001", "0001", 1);

INSERT INTO produit (CODE_PRODUIT, LIBELLE_PRODUIT, DESCRIPTION, PRIX) VALUES ("0001", "Imprimate 3D", "Imprime tout en 3d", 200);
SELECT * FROM utilisateur u ;
SELECT * from commande c;
