package com.example.demo;

import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ImageBytesUtil {

    public static BufferedImage bytesToImage(byte[] bytes) throws IOException {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);

        return ImageIO.read(inputStream);
    }

    public static BufferedImage multipartToImage(MultipartFile file) {
        try {
            return bytesToImage(file.getBytes());
        } catch (IOException e) {
            return new BufferedImage(100, 100, BufferedImage.TYPE_3BYTE_BGR);
        }
    }


    public static byte[] imageToBytes(BufferedImage img) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        try {
            ImageIO.write(img, "jpg", output);
            return output.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return new byte[]{};
        }
    }

    public static BufferedImage resize(BufferedImage srcImage, int width, int height) {
        BufferedImage resized = new BufferedImage(width, height, srcImage.getType());
        Graphics2D g = resized.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(srcImage, 0, 0, width, height, 0, 0, srcImage.getWidth(), srcImage.getHeight(), null);
        g.dispose();
        return resized;

    }

}
