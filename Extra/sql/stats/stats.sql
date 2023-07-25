-- CA by year
select YEAR(c.`DATE`), sum(p.PRIX*dc.quantite) from produit p
inner join detail_commande dc
on dc.CODE_PRODUIT = p.CODE_PRODUIT
inner join commande c
on c.ID_COMMANDE = dc.ID_COMMANDE
GROUP BY YEAR(c.`DATE`);
