/**
 * Cours:   GTI310
 * Session: A2018
 * Groupe: 01
 * Projet: Laboratoire #3
 * �tudiant(e)s:
 * Brunet, Fran�ois
 * Patault, Quentin
 * Professeur :			St�phane Coulombe
 * Nom du fichier :		Main.java
 * Date cr�� :			2018-10-15
 * Date dern. modif. :	2018-10-28
 * *******************************************************
 * Historique des modifications
 * *******************************************************
 * 2018-12-08 Cr�ation de la classe
 * 2018-12-14 Implementation des methodes et commentaires
 *
 * @author Quentin Patault & Fran�ois Brunet
 * @since 2018-12-13
 * <p>
 * La classe pour lancer l'encodeur/d�codeur PPM to SZL
 */
/**
 * @author Quentin Patault & Fran�ois Brunet
 * @since 2018-12-13
 *
 * La classe pour lancer l'encodeur/d�codeur PPM to SZL
 */


package gti310.tp4;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

/**
 * The Main class is where the different functions are called to either encode a
 * PPM file to the Squeeze-Light format or to decode a Squeeze-Ligth image into
 * PPM format. It is the implementation of the simplified JPEG block diagrams.
 *
 * @author Fran�ois Caron
 */
public class Main {


    /*
     * The entire application assumes that the blocks are 8x8 squares.
     */
    public static final int BLOCK_SIZE = 8;

    /*
     * The number of dimensions in the color spaces.
     */
    public static final int COLOR_SPACE_SIZE = 3;

    /*
     * The RGB color space.
     */
    public static final int R = 0;
    public static final int G = 1;
    public static final int B = 2;

    /*
     * The YUV color space.
     */
    public static final int Y = 0;
    public static final int U = 1;
    public static final int V = 2;


	/*
	Le facteur de qualit�
	 */

    public static double facteurQualite = 50;

    /**
     * The application's entry point.
     *
     * @param args
     */
    public static void main(String[] args) {
		

		/*
		Collection pour les DC, AC et AC une fois le codage RLE effectu�
		 */
        List DC_List = new LinkedList();
        List AC_List = new LinkedList();

        List RLE_List = new LinkedList();


		/*
    Menu pour le choix de l'image et le facteur de qualit�
     */

        System.out.println("Squeeze Light Media Codec ! \n\nVoulez-vous encoder ou d�coder une image (E/D) ?");
        Scanner scanner = new Scanner(System.in);
        String choixUtilisateur = scanner.nextLine();

        if (choixUtilisateur.equals("D")) {

            System.out.println("Cette fonctionnalit� est toujours en cours d'impl�mentation");
            System.exit(0);

        } else if (choixUtilisateur.equals("E")) {

        }
        System.out.println("Veuillez choisir une image en .ppm . \n\n" +
                "L'image doit se trouver � la racine du dossier (avec .project)");

        scanner = new Scanner(System.in);
        String imageDepart = scanner.nextLine();

        System.out.println("Choississez maintenant le facteur de qualit� entre 1 et 100.");

        scanner = new Scanner(System.in);
        facteurQualite = Double.parseDouble(scanner.nextLine());

        System.out.println("Vous avez choisi : " + imageDepart + " avec comme facteur de qualit� : " + facteurQualite);

        System.out.println("Veuillez choisir le nom de l'image de sortie. \n\n"
                + "L'image de sortie se trouvera � la racine du dossier (avec .project)");

        scanner = new Scanner(System.in);
        String imageSortie = scanner.nextLine();

		/*
		 On converti l'image en .PPM en YUV
		 */

        int[][][] matriceYUV = Converter.convertToYUV(PPMReaderWriter.readPPMFile(imageDepart));


		/*
		On extrait la matrice Y et on sous �chantillone les matrices U et V pour r�aliser le 4.2.2
		 */

        int[][] matriceY = Echantillonnage.isolerY(matriceYUV, Y);
        int[][] matriceU = Echantillonnage.sousEchantillonne(matriceYUV, U);
        int[][] matriceV = Echantillonnage.sousEchantillonne(matriceYUV, V);


		/*
		Variable symbolisant un bloc 8x8 et un bloc une fois le Zigzag effectu�
		 */

        double[][] bloc;
        double[] blocZigZaged;


		/*
		On cr�� un it�rateur pour la matrice Y
		 */
        Iterateur iterateurY = new Iterateur(matriceY, Main.Y);
        bloc = iterateurY.next();
		


		/*
		D�but du traitement de matrice Y
		 */
        for (int i = 0; i < matriceY.length / 8; i++) {

            bloc = CalculDCT.DCT2D(bloc);
            bloc = Quantification.Quantifier(bloc, Main.Y);
            blocZigZaged = Zigzag.Zigzag(bloc);


			/*
			On s�pare les AC et DC

			Complexit� : O(N)
			 */
            for (int j = 0; j < 64; j++) {

                if (j == 0) {
                    DC_List.add(blocZigZaged[j]);
                } else {
                    AC_List.add(blocZigZaged[j]);

                }

                System.out.print(blocZigZaged[j] + " ");

            }
            System.out.println("");

            bloc = iterateurY.next();
        }
        System.out.println("");


		/*
		On cr�� un it�rateur pour la matrice U
		 */

        Iterateur iterateurU = new Iterateur(matriceU, Main.U);
        bloc = iterateurU.next();

		/*
		D�but du traitement de matrice U
		 */

        for (int i = 0; i < matriceU.length / 8 / 2; i++) {

            bloc = CalculDCT.DCT2D(bloc);
            bloc = Quantification.Quantifier(bloc, Main.U);
            blocZigZaged = Zigzag.Zigzag(bloc);


			/*
			On s�pare les AC et DC
			Complexit� : O(N)
			 */
            for (int j = 0; j < 64; j++) {

                if (j == 0) {
                    DC_List.add(blocZigZaged[j]);
                } else {
                    AC_List.add(blocZigZaged[j]);

                }

                System.out.print(blocZigZaged[j] + " ");

            }
            System.out.println("");

            bloc = iterateurU.next();
        }
        System.out.println("");


		/*
		On cr�� un it�rateur pour la matrice V
		 */

        Iterateur iterateurV = new Iterateur(matriceV, Main.V);
        bloc = iterateurV.next();


		/*
		D�but du traitement de matrice V
		 */

        for (int i = 0; i < matriceV.length / 8 / 2; i++) {

            bloc = CalculDCT.DCT2D(bloc);
            bloc = Quantification.Quantifier(bloc, Main.V);
            blocZigZaged = Zigzag.Zigzag(bloc);


			/*
			On s�pare les AC et DC
			Complexit� : O(N)
			 */
            for (int j = 0; j < 64; j++) {

                if (j == 0) {
                    DC_List.add(blocZigZaged[j]);
                } else {
                    AC_List.add(blocZigZaged[j]);

                }

                System.out.print(blocZigZaged[j] + " ");

            }
            System.out.println("");

            bloc = iterateurV.next();
        }


		/*
		Codage RLE des AC obtenus pr�c�demment
		Complexit� : O(N)
		 */

        double runLength = 0;
        for (int j = 0; j < AC_List.size(); j++) {


            double val = Double.parseDouble(AC_List.get(j).toString());


            if (val != 0) {

                RLE_List.add(runLength);
                RLE_List.add(val);
                runLength = 0;
            } else {
                runLength++;
            }


        }

		/*
		Insertion du "End of Block (0,0)" --> EOB
		 */
        RLE_List.add(0);
        RLE_List.add(0);


		/*
		Ecriture des DC avec la classe Entropy
		Complexit� : O(N)
		 */
        for (int j = 0; j < DC_List.size(); j++) {

            double val = Double.parseDouble(DC_List.get(j).toString());

            Entropy.writeDC((int) val);
        }


		/*
		Ecriture des AC (apres codage RLE) avec la classe Entropy
		Complexit� : O(N)
		 */
        for (int j = 0; j < RLE_List.size(); j = j + 2) {

            runLength = Double.parseDouble(RLE_List.get(j).toString());
            double val = Double.parseDouble(RLE_List.get(j + 1).toString());

            Entropy.writeAC((int) runLength, (int) val);
        }


        System.out.println("");


		/*
		Cr�ation du fichier SZL
		 */
        SZLReaderWriter.writeSZLFile(imageSortie, matriceY.length, matriceY.length, 50);


    }


}
