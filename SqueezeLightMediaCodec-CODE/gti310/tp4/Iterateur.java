/******************************************************
 * Cours:   GTI310
 * Session: A2018
 * Groupe: 01
 * Projet: Laboratoire #3
 * Étudiant(e)s:
 *  Brunet, François
 *  Patault, Quentin
 * Professeur :			Stéphane Coulombe
 * Nom du fichier :		Iterateur.java
 * Date créé :			2018-10-15
 * Date dern. modif. :	2018-10-28
 ********************************************************
 * Historique des modifications
 ********************************************************
 * 2018-12-08 Création de la classe
 * 2018-12-10 Implementation des methodes et commentaires
 *******************************************************/
/**
 * @author Quentin Patault & François Brunet
 * @since 2018-12-13
 *
 * Cette classe nous permet d'avoir un itérateur sur nos matrices. Il permet de parcourir l'image en bloc de 8x8
 */


package gti310.tp4;

public class Iterateur {

	int x = 0;
	int y = 0;
	int[][] matrice;
	int couche;

	public Iterateur(int[][]matrice,int couche) {

		this.matrice=matrice;
		this.couche=couche;
		//this.next();
	}

    /**
     * Méthode qui renvoie le prochain bloc 8x8 d'une matrice
     * @return
     *
     * Analyse de complexite : O(N^2)
     */

	public double[][] next() {

		double[][] bloc = new double[8][8];

		if (couche == Main.Y) {

			if (x >= 256) {
				this.x = 0;
			}
			if (y >= 256) {
				this.y = 0;
			}
		}else{

			if (x >= 128) {
				this.x = 0;
			}
			if (y >= 128) {
				this.y = 0;
			}
		}


		for (int i = x, k=0; i < x+8; i++,k++) {

			for (int j = y,l=0; j < y+8; j++,l++) {

				bloc[k][l] = matrice[i][j];
				//System.out.print(bloc[k][l]+" ");

			}
			//System.out.println("");
		}
		x=x+8;
		y=y+8;

		return bloc;
	}

}
