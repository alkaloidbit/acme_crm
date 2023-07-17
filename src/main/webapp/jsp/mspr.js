/**
 * Fonction qui renvoie vrai si le champ saisi est un entier.
 * Faux sinon.
 */
function isEntier(champ) {
	var longueur = champ.length;
	for (var i = 0; i < longueur; i++) {
		if (champ.substring(i, i + 1) == ".") return false;
	}
	if (isNaN(champ)) return false;
	return true;
}
/**
 * Fonction qui renvoie vrai si le champ est au format date (JJ/MM/AAAA).
 */
function isDateFormat(champ) {
	if (champ.length != 10) return false;
	if (isEntier(champ.substr(0, 2)) == false || isEntier(champ.substr(3, 2)) == false || isEntier(champ.substr(6, 4)) == false) return false;
	if (champ.substr(2, 1) != "/" || champ.substr(5, 1) != "/") return false;
	if (parseInt(champ.substr(0, 2), 10) < 1 || parseInt(champ.substr(0, 2), 10) > 31) return false;
	if (parseInt(champ.substr(3, 2), 10) < 1 || parseInt(champ.substr(3, 2), 10) > 12) return false;
	if (parseInt(champ.substr(6, 4), 10) < 1950 || parseInt(champ.substr(6, 4), 10) > 2100) return false;
	return true;
}
/**
 * Fonction qui recharge la frame menu (gauche.jsp).
 */
function loadMenu() { parent.frames['leftFrame'].location.reload(); }

/**
 * 	Fonction qui compare deux dates.
 *	Return >= 0 si dateFin >= dateDebut.
 */
function compareDate(dateDebut, dateFin) {
	var dd = dateDebut.substr(3, 2) + '/' + dateDebut.substr(0, 2) + '/' + dateDebut.substr(6, 4);
	var df = dateFin.substr(3, 2) + '/' + dateFin.substr(0, 2) + '/' + dateFin.substr(6, 4);
	var dureeDebut = Date.parse(dd);
	var dureeFin = Date.parse(df);
	var comparaison = dureeFin - dureeDebut;
	return comparaison;
}
/**
 * Fonction qui renvoie vrai si le champ est null ou vide.
 */
function isNull(champ) {
	if (champ == "" || champ == null) {	return true; }
	return false;
}
/**
 * Fonction qui renvoie vrai si le champ contient exactement le nombre n de caracteres.
 */
function isLengthOk(champ, n) {
	if (champ.length != n) return false;
	return true;
}
/**
 * Fonction qui renvoie vrai si le champ est un entier >= 0
 */
function isEntierPos(champ) {
	if (!isEntier(champ)) return false;
	if (parseInt(champ) < 0) return false;
	return true;
}
