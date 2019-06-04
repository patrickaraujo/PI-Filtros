import java.awt.Color;
import java.awt.Image;
import java.io.File;
/**
 * 
 */
import java.io.IOException;

/**
 * @author arauj
 *
 */
public class Filtros_Tests {

	/**
	 * @param args
	 * @throws IOException 
	 */
	
	public static void mascara (int height, int width, Picture picture1, Picture picture2, int[][] m2, String nomeArquivo) throws IOException {
		
		for (int y = 1; y < height - 1; y++) {
            for (int x = 1; x < width - 1; x++) {
                Color c00 = picture1.get(x-1, y-1);
                Color c01 = picture1.get(x-1, y);
                Color c02 = picture1.get(x-1, y+1);
                Color c10 = picture1.get(x, y-1);
                Color c11 = picture1.get(x, y);
                Color c12 = picture1.get(x, y+1);
                Color c20 = picture1.get(x+1, y-1);
                Color c21 = picture1.get(x+1, y);
                Color c22 = picture1.get(x+1, y+1);
                int r = (m2[0][0])*c00.getRed()	+	(m2[0][1])*c01.getRed()	+	(m2[0][2])*c02.getRed()	+
                		(m2[1][0])*c10.getRed()	+	(m2[1][1])*c11.getRed()	+	(m2[1][2])*c12.getRed()	+
                		(m2[2][0])*c20.getRed() +   (m2[2][1])*c21.getRed() +	(m2[2][2])*c22.getRed();
                int g = (m2[0][0])*c00.getGreen()	+	(m2[0][1])*c01.getGreen() +	(m2[0][2])*c02.getGreen() +
                		(m2[1][0])*c10.getGreen()	+	(m2[1][1])*c11.getGreen() +	(m2[1][2])*c12.getGreen() +
                		(m2[2][0])*c20.getGreen()	+	(m2[2][1])*c21.getGreen() +	(m2[2][2])*c22.getGreen();
                int b = (m2[0][0])*c00.getBlue()	+	(m2[0][1])*c01.getBlue()	+	(m2[0][2])*c02.getBlue() +
                		(m2[1][0])*c10.getBlue()	+	(m2[1][1])*c11.getBlue()	+	(m2[1][2])*c12.getBlue() +
                		(m2[2][0])*c20.getBlue()	+	(m2[2][1])*c21.getBlue()	+	(m2[2][2])*c22.getBlue();
                r = Math.min(255, Math.max(0, r));
                g = Math.min(255, Math.max(0, g));
                b = Math.min(255, Math.max(0, b));
                Color color = new Color(r, g, b);
                picture2.set(x, y, color);
            }
            picture2.show();
            picture2.saveFile(nomeArquivo);
        }
	}
	
	public static void main(String[] args) throws IOException{
		String nomeArquivo = "img.jpeg";
		File arquivo = new File(nomeArquivo);
		if(!arquivo.exists()) {
			System.out.println("Arquivo \""+nomeArquivo+"\" não encontrado");
			System.exit(0);
		}
        Picture picture1 = new Picture(arquivo);   // original
        Picture picture2 = new Picture(picture1);   // filtered
        int width  = picture1.width();
        int height = picture1.height();
        
        int m1[][] =	{	{0, 1, 0},
							{1, -4, 1},
							{0, 1, 0},
						};
        int m2[][] =	{	{1, 1, 1},
							{1, -8, 1},
							{1, 1, 1},
						};
        int m3[][] =	{	{0, -1, 0},
							{-1, 4, -1},
							{0, -1, 0},
						};
        int m4[][] =	{	{-1, -1, -1},
							{-1, 8, -1},
							{-1, -1, -1}
						};
        int sobelH[][] =	{	{-1, -2 , -1},
        						{0, 0, 0},
        						{1, 2 , 1}
        					};
        int sobelV[][] =	{	{-1, 0, 1},
        						{-2, 0, 2},
        						{-1, 0, 1}
        					};

        picture1.show();
        picture2.show();

        //	mascara(height, width, picture1, picture2, m1, "landscape_Mascara1.jpg");
        //	mascara(height, width, picture1, picture2, m2, "landscape_Mascara2.jpg");
        //	mascara(height, width, picture1, picture2, m3, "landscape_Mascara3.jpg");
        //	mascara(height, width, picture1, picture2, m4, "landscape_Mascara4.jpg");
        
        //	mascara(height, width, picture1, picture2, sobelV, "landscape_MascaraSobelV.jpg");
        mascara(height, width, picture1, picture2, sobelH, "landscape_MascaraSobelH.jpg");
    }

}
