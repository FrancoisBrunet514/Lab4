/******************************************************
 * Cours:   GTI310
 * Session: A2018
 * Groupe: 01
 * Projet: Laboratoire #3
 * Étudiant(e)s:
 *  Brunet, François
 *  Patault, Quentin
 * Professeur :			Stéphane Coulombe
 * Nom du fichier :		ZigZag.java
 * Date créé :			2018-10-15
 * Date dern. modif. :	2018-10-28
 ********************************************************
 * Historique des modifications
 ********************************************************
 * 2018-12-10 Création de la classe
 * 2018-12-12 Implementation des methodes et commentaires
 *******************************************************/
/**
 * @author Quentin Patault & François Brunet
 * @since 2018-12-13
 *
 * Cette classe  permet d'effecteur un Zigzag sur un bloc
 */
package gti310.tp4;

public class Zigzag {

	private static final int POSITIONS[][] = { { 0, 2, 3, 9, 10, 20, 21, 35 }, { 1, 4, 8, 11, 19, 22, 34, 36 },
			{ 5, 7, 12, 18, 23, 33, 37, 48 }, { 6, 13, 17, 24, 32, 38, 47, 49 }, { 14, 16, 25, 31, 39, 46, 50, 57 },
			{ 15, 26, 30, 40, 45, 51, 57, 58 }, { 27, 29, 41, 44, 52, 55, 59, 62 },
			{ 28, 42, 43, 53, 54, 60, 61, 63 } };


	/**
	 * Méthode qui fait le ZigZag d'un bloc
	 * @param blocEntree
	 * @return bloc ZigZagué
	 *
	 * Analyse de complexite : O(N^2)
	 */
	public static double[] Zigzag(double[][] blocEntree) {

		double[] blocSortie = new double[64];

		for (int i = 0; i < 8; i++) {
			
			for (int j = 0; j < 8; j++) {
			
				blocSortie[ POSITIONS[i][j] ]= blocEntree[i][j];

			}
		}

		return blocSortie;
	}
}