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
 * 2018-12-10 Création de la classe
 * 2018-12-10 Implementation des methodes et commentaires
 *******************************************************/
/**
 * @author Quentin Patault & François Brunet
 * @since 2018-12-13
 *
 * Cette classe nous permet d'effecteur le sous echantillonage d'une matrice U ou V. Elle permet aussi d'extraire une matrice Y d'une matrice à 3 dimensions
 */

package gti310.tp4;

public class Echantillonnage {

    /**
     * Méthode permettant de convertir la matrice YUV en 4:4:2 (sous echantillonage)
     * @param matrice
     * @param couche
     * @return
     *
     * Analyse de complexite : O(N^2)
     */
    public static int[][] sousEchantillonne(int[][][] matrice, int couche){

        int h=matrice[0].length;
        int w=matrice[0][0].length;

        int[][] matriceRetour = new int[h][w];
        int k=0,l=0;
        for (int i=0;i<h;i=i+2){

            for (int j=0;j<w;j=j+2){

                matriceRetour[k][l]= matrice[couche][i][j];

                l++;
            }
            l=0;
            k++;
        }
        return matriceRetour;
    }

    /**
     * Méthode permettant d'extraire une matrice Y d'une matrice à 3 dimensions.
     * @param matrice
     * @param couche
     * @return
     *
     * Analyse de complexite : O(N^2)
     */

    public static int[][] isolerY(int[][][] matrice, int couche){
        int h=matrice[0].length;
        int w=matrice[0][0].length;

        int[][] matriceY = new int[h][w];

        for (int i=0;i<h;i++){

            for (int j=0;j<w;j++){

                matriceY[i][j]= matrice[couche][i][j];

            }
        }
        return matriceY;
    }
}
