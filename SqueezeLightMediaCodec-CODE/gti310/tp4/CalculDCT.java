/******************************************************
 * Cours:   GTI310
 * Session: A2018
 * Groupe: 01
 * Projet: Laboratoire #3
 * Étudiant(e)s:
 *  Brunet, François
 *  Patault, Quentin
 * Professeur :			Stéphane Coulombe
 * Nom du fichier :		CalculDCT.java
 * Date créé :			2018-10-15
 * Date dern. modif. :	2018-10-28
 ********************************************************
 * Historique des modifications
 ********************************************************
 * 2018-12-09 Création de la classe
 * 2018-12-10 Implementation des methodes et commentaires
 *******************************************************/
/**
 * @author Quentin Patault & François Brunet
 * @since 2018-12-13
 *
 * Cette classe nous permet d'effecteur la DCT et DCT Inverse d'un bloc 8x8
 */




package gti310.tp4;


public class CalculDCT {


	/**
	 * Méthode pour réaliser la DCT d'un bloc
	 * @param blocEntree
	 * @return un bloc tranformé
	 *
	 * Analyse de complexite : O(N^4)
	 */
	public static double[][] DCT2D(double[][] blocEntree) {

		double[][] blocSortie = new double[blocEntree.length][blocEntree[0].length];

		for (int v = 0; v < 8; v++) {

			for (int u = 0; u < 8; u++) {


				/*
				Cette boucle réprésente sigma de i=0 à 7 (voir formule de la DCT
				 */
				for (int i = 0; i < 8; i++) {

					/*
					Cette boucle réprésente sigma de j=0 à 7 (voir formule de la DCT
				 	*/
					for (int j = 0; j < 8; j++) {

						blocSortie[v][u] += ((double) blocEntree[i][j])
								* Math.cos(((double) (2 * i + 1) * (double) u * Math.PI) / (double) 16)
								* Math.cos(((double) (2 * j + 1) * (double) v * Math.PI) / (double) 16);

					}

				}

				/*

				Utilisation de l'opérateur ternaire pour réaliser la fonction C(w) qui est en dehors des sommes (voir formule)
				 */

				blocSortie[v][u] *= (double) (0.25) * ((u == 0) ? ((double) 1.0 / Math.sqrt(2)) : (double) 1.0)
						* ((v == 0) ? ((double) 1.0 / Math.sqrt(2)) : (double) 1.0);

			}

		}
		return blocSortie;

	}


	/**
	 * Méthode pour réaliser la DCT Inverse d'un bloc
	 * @param blocEntree
	 * @return un bloc transformé
	 *
	 * Analyse de complexite : O(N^4)
	 */
	public static double[][] DCT2DInverse(double[][] blocEntree) {

		double[][] blocSortie = new double[blocEntree.length][blocEntree[0].length];

		for (int v = 0; v < 8; v++) {

			for (int u = 0; u < 8; u++) {

				for (int i = 0; i < 8; i++) {

					for (int j = 0; j < 8; j++) {

						blocSortie[v][u] += ((double) blocEntree[i][j])
								* Math.cos(((double) (2 * i + 1) * (double) u * Math.PI) / (double) 16)
								* Math.cos(((double) (2 * j + 1) * (double) v * Math.PI) / (double) 16);

					}

				}

				blocSortie[v][u] *= (double) (0.25) * ((u == 0) ? ((double) 1.0 / Math.sqrt(2)) : (double) 1.0)
						* ((v == 0) ? ((double) 1.0 / Math.sqrt(2)) : (double) 1.0);

			}

		}
		return blocSortie;

	}


}
