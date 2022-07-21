import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import javax.imageio.ImageIO;

public class StickerGenerator {
    void create(InputStream inputStream, String fileName) throws IOException {

        // leitura da imagem
        BufferedImage originalImage = ImageIO.read(inputStream);

        // cria a imagem em memoria com transparência e com tamanho novo
        int width = originalImage.getWidth();
        int height = originalImage.getHeight();
        int newHeight = height + 200;
        BufferedImage newImage = new BufferedImage(width, newHeight, BufferedImage.TRANSLUCENT);

        // copiar a imagem original pra nova imagem (em memoria)
        Graphics2D graphics = (Graphics2D) newImage.getGraphics();
        graphics.drawImage(originalImage, 0, 0, null);

        // configurar a fonte e o tamanho da fonte
        Font font = new Font(Font.SANS_SERIF, java.awt.Font.BOLD, 60);
        graphics.setColor(Color.RED);
        graphics.setFont(font);

        // escrever uma frase na nova imagem
        graphics.drawString("Topzera", width / 4, newHeight - 100);
        
        // escrever a nova imagem em um arquivo
        ImageIO.write(newImage, "png", new File(fileName));
    }
}
