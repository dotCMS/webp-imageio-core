package webp;

import com.luciad.imageio.webp.WebP;
import com.luciad.imageio.webp.WebPReadParam;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.FileImageInputStream;
import org.junit.BeforeClass;
import org.junit.Test;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class DecodeTest {
  
    @BeforeClass
    public static void loadWebP() {
        WebP.loadNative();
    }
  @Test
  public void test_reading() throws IOException {
        String inputWebpPath = "test_pic/test.webp";
        String outputJpgPath = "test_pic/test_decode.jpg";
        String outputJpegPath = "test_pic/test_decode.jpeg";
        String outputPngPath = "test_pic/test_decode.png";

        
        new File(outputPngPath).delete();
        new File(outputJpgPath).delete();
        new File(outputJpegPath).delete();
        
        // Obtain a WebP ImageReader instance
        ImageReader reader = ImageIO.getImageReadersByMIMEType("image/webp").next();

        // Configure decoding parameters
        WebPReadParam readParam = new WebPReadParam();
        readParam.setBypassFiltering(true);

        // Configure the input on the ImageReader
        reader.setInput(new FileImageInputStream(new File(inputWebpPath)));

        // Decode the image
        BufferedImage image = reader.read(0, readParam);

        ImageIO.write(image, "png", new File(outputPngPath));
        ImageIO.write(image, "jpg", new File(outputJpgPath));
        ImageIO.write(image, "jpeg", new File(outputJpegPath));

    }
}
