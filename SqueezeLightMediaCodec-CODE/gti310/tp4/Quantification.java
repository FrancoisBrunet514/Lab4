/******************************************************
 * Cours:   GTI310
 * Session: A2018
 * Groupe: 01
 * Projet: Laboratoire #3
 * �tudiant(e)s:
 *  Brunet, Fran�ois
 *  Patault, Quentin
 * Professeur :			St�phane Coulombe
 * Nom du fichier :		Quantification.java
 * Date cr�� :			2018-10-15
 * Date dern. modif. :	2018-10-28
 ********************************************************
 * Historique des modifications
 ********************************************************
 * 2018-12-09 Cr�ation de la classe
 * 2018-12-11 Implementation des methodes et commentaires
 *******************************************************/
/**
 * @author Quentin Patault & Fran�ois Brunet
 * @since 2018-12-13
 *
 * Cette classe  permet d'effecteur une quantification sur les matrices
 */

package gti310.tp4;
import gti310.tp4.Main;

public class Quantification {

	private static final int Qy[][] = {
			 {36, 31, 30, 36, 44, 50, 51, 61},
			 {32, 32, 34, 39, 46, 58, 60, 55},
			 {34, 33, 36, 34, 40, 57, 69, 56},
			 {34, 37, 32, 39, 51, 87, 80, 62},
			 {38, 32, 37, 56, 68, 109, 103, 77},
			 {34, 35, 55, 64, 81, 104, 113, 92},
			 {49, 64, 78, 87, 103, 121, 120, 101},
			 {72, 92, 95, 98, 112, 100, 103, 95}
			 };
	private static final int Quv[][] = {
			{37, 38, 44, 47, 95, 95, 95, 95},
			{38, 41, 46, 66, 95, 95, 95, 95},
			{44, 46, 56, 95, 95, 95, 95, 95},
			{47, 66, 95, 95, 95, 95, 95, 95},
			{95, 95, 95, 95, 95, 95, 95, 95},
			{95, 95, 95, 95, 95, 95, 95, 95},
			{95, 95, 95, 95, 95, 95, 95, 95},
			{95, 95, 95, 95, 95, 95, 95, 95}
					
	};


	/**
	 * M�thoe permettant de Quantifier un bloc d'un matrice
	 * @param blocEntree
	 * @param couche
	 * @return Un bloc quantifi�
	 *
	 * Analyse de complexite : O(N^2)
	 */
	
	public static double[][] Quantifier(double[][] blocEntree,int couche) {
		
		double alpha=0;
		double[][] blocSortie = new double[8][8];

		/*
		Prise en compte du facteur de qualit� et calcul de alpha (voir formule)
		 */
		
		if(1 <= Main.facteurQualite && Main.facteurQualite<=50) {
			
			alpha=50/Main.facteurQualite;
		}else if(50 < Main.facteurQualite && Main.facteurQualite<=99) {

			alpha=(200-2*Main.facteurQualite)/100;

		}else if(Main.facteurQualite==100) {
			
			alpha=-1;
			System.out.print("La quantification n'aura pas lieu : facteur de qualit� = 100");

		}else {
			System.out.print("Erreur : facteur de qualit� ");
		}
	
		if(alpha!=-1) {
			
			for (int u = 0; u < 8; u++) {

				for (int v = 0; v < 8; v++) {
					
					
					if(couche==Main.Y) {
						blocSortie[u][v]=Math.round(blocEntree[u][v]/(alpha*Qy[u][v]));
					}
					else {
						blocSortie[u][v]=Math.round(blocEntree[u][v]/(alpha*Quv[u][v]));
					}
					

				}

			}
			
			
		}
		
		return blocSortie;
	}
			
}
