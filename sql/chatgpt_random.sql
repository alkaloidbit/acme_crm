-- Insertion de données dans la table `client`
INSERT INTO `client` (`CODE_CLIENT`, `NOM`, `PRENOM`, `ADRESSE`, `CODE_POSTAL`, `VILLE`)
SELECT
    CONCAT('C', LPAD(ROW_NUMBER() OVER (ORDER BY rand()), 3, '0')),
    CONCAT('Nom', LPAD(ROW_NUMBER() OVER (ORDER BY rand()), 3, '0')),
    CONCAT('Prénom', LPAD(ROW_NUMBER() OVER (ORDER BY rand()), 3, '0')),
    CONCAT(ROW_NUMBER() OVER (ORDER BY rand()), ' Rue'),
    LPAD(FLOOR(RAND() * 100000), 5, '0'),
    'Ville'
FROM
    information_schema.tables;

-- Insertion de données dans la table `commande`
INSERT INTO `commande` (`CODE_CLIENT`, `DATE`)
SELECT
    `CODE_CLIENT`,
    NOW() - INTERVAL FLOOR(RAND() * 30) DAY
FROM
    `client`
LIMIT 25;

-- Insertion de données dans la table `detail_commande`
INSERT INTO `detail_commande` (`ID_COMMANDE`, `CODE_PRODUIT`, `QUANTITE`)
SELECT
    `ID_COMMANDE`,
    CONCAT('P', LPAD(FLOOR(1 + RAND() * 4), 3, '0')),
    FLOOR(1 + RAND() * 10)
FROM
    `commande`
LIMIT 25;

-- Insertion de données dans la table `produit`
INSERT INTO `produit` (`CODE_PRODUIT`, `LIBELLE_PRODUIT`, `DESCRIPTION`, `PRIX`)
SELECT
    CONCAT('P', LPAD(ROW_NUMBER() OVER (ORDER BY rand()), 3, '0')),
    CONCAT('Produit', LPAD(ROW_NUMBER() OVER (ORDER BY rand()), 3, '0')),
    CONCAT('Description du produit', LPAD(ROW_NUMBER() OVER (ORDER BY rand()), 3, '0')),
    FLOOR(10 + RAND() * 40)
FROM
    information_schema.tables;

-- Insertion de données dans la table `role`
INSERT INTO `role` (`CODE_ROLE`, `LIBELLE_ROLE`)
SELECT
    CONCAT('R', LPAD(ROW_NUMBER() OVER (ORDER BY rand()), 3, '0')),
    CONCAT('Role', LPAD(ROW_NUMBER() OVER (ORDER BY rand()), 3, '0'))
FROM
    information_schema.tables;

-- Insertion de données dans la table `utilisateur`
INSERT INTO `utilisateur` (`LOGIN`, `PSW`, `CODE_ROLE`)
SELECT
    CONCAT('user', LPAD(ROW_NUMBER() OVER (ORDER BY rand()), 3, '0')),
    CONCAT('pass', LPAD(ROW_NUMBER() OVER (ORDER BY rand()), 3, '0')),
    `CODE_ROLE`
FROM
    `role`
LIMIT 25;

