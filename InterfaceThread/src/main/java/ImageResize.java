import org.imgscalr.Scalr;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class ImageResize extends Thread {
    private File[] files;
    private String dstFolder;
    private long start;
    private int newWidth = 800;

    public ImageResize(File[] files, String dstFolder, long start) {
        this.files = files;
        this.dstFolder = dstFolder;
        this.start = start;
    }

    public void run(){
        resizeImage();
    }

    private void resizeImage() {
        try {
            for (File file : files) {
            BufferedImage image = ImageIO.read(file);
            if (image == null ) {
                continue;
            }
                int newHeight = (int) Math.round(
                    image.getHeight() / (image.getWidth() / (double) newWidth)
            );
            BufferedImage newImage = new BufferedImage(
                    newWidth, newHeight, BufferedImage.TYPE_INT_RGB
            );

            int widthStep = image.getWidth() / newWidth;
            int heightStep = image.getHeight() / newHeight;

            for (int x = 0; x < newWidth; x++) {
                for (int y = 0; y < newHeight; y++) {
                    int rgb = image.getRGB(x  * widthStep, y * heightStep);
                    newImage.setRGB(x, y, rgb);
                }
            }
            BufferedImage newImage2 = Scalr.resize(newImage
                    , 200,200);

            File newFile = new File(dstFolder + "/" + file.getName());
            ImageIO.write(newImage2, "jpg", newFile);
        }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println(
                Thread.currentThread().getName() + " - duration: " + (System.currentTimeMillis() - start));
    }
}


