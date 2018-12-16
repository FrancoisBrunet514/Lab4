/******************************************************
 * Cours:   GTI310
 * Session: A2018
 * Groupe: 01
 * Projet: Laboratoire #3
 * Étudiant(e)s:
 *  Brunet, François
 *  Patault, Quentin
 * Professeur :			Stéphane Coulombe
 * Nom du fichier :		Converter.java
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
 * Cette classe nous permet de convertir une image RGB en YUV et de YUV à RGB
 */

package gti310.tp4;

public class Converter {

    /**
     * Fonction permettant de convertir nos valeurs RGB en YUV
     * @param imageRGB
     * @return
     *
     * Analyse de complexite : O(N^2)
     */
    public static int[][][] convertToYUV(int[][][] imageRGB) {

        // Variables de couleurs
        double Y,U,V;

        // Variables de largeur et hauteur
        int h,w;
        h=imageRGB[0].length;
        w=imageRGB[0][0].length;

        // Tableau pour les valeurs converties en YUV
        int[][][] imageYUV = new int[Main.COLOR_SPACE_SIZE][h][w];

        for (int i=0;i<h;i++){

            for (int j=0;j<w;j++){

                // Calculs des valeurs YUV
                Y=0.299*imageRGB[Main.R][i][j]+0.587*imageRGB[Main.G][i][j]+0.114*imageRGB[Main.B][i][j];

                // On arrondi a l'entier plus haut le plus proche
                imageYUV[Main.Y][i][j]=(int) java.lang.Math.round(Y);

            }

        }

        int k=0,l=0;
        for (int i=0;i<h;i=i+2){

            for (int j=0;j<w;j=j+2){

                // Calculs des valeurs YUV
                U=0.492*(imageRGB[Main.B][i][j]- imageYUV[Main.Y][i][j]);
                V=0.877*(imageRGB[Main.R][i][j]- imageYUV[Main.Y][i][j]);

                imageYUV[Main.U][k][l]=(int) java.lang.Math.round(U);
                imageYUV[Main.U][k][l+1]=(int) java.lang.Math.round(U);
                imageYUV[Main.U][k+1][l]=(int) java.lang.Math.round(U);
                imageYUV[Main.U][k+1][l+1]=(int) java.lang.Math.round(U);

                imageYUV[Main.V][k][l]=(int) java.lang.Math.round(V);
                imageYUV[Main.V][k][l+1]=(int) java.lang.Math.round(V);
                imageYUV[Main.V][k+1][l]=(int) java.lang.Math.round(V);
                imageYUV[Main.V][k+1][l+1]=(int) java.lang.Math.round(V);

                l=l+2;
            }
            l=0;
            k=k+2;
        }

        return imageYUV;
    }



    /**
     * Fonction permettant de convertir nos valeurs YUV en RGB
     * @param imageYUV
     * @return
     *
     * Analyse de complexite : O(N^2)
     */
    public static int[][][] convertToRGB(int[][][] imageYUV) {

        double R,G,B;

        int h,w;
        h=imageYUV[0].length;
        w=imageYUV[0][0].length;
        int[][][] imageRGB = new int[Main.COLOR_SPACE_SIZE][h][w];

        for (int i=0;i<h;i++){

            for (int j=0;j<w;j++){

                B = (imageYUV[Main.U][i][j]/0.492)+imageYUV[Main.Y][i][j];
                R = (imageYUV[Main.V][i][j]/0.877)+imageYUV[Main.Y][i][j];
                G = (imageYUV[Main.Y][i][j] - (0.299 * R ) - (0.114 * B))/0.587;

                imageRGB[Main.R][i][j]=(int) Math.round(R);
                imageRGB[Main.G][i][j]=(int) Math.round(G);
                imageRGB[Main.B][i][j]=(int) Math.round(B);

            }
        }

        return imageRGB;
    }
}
