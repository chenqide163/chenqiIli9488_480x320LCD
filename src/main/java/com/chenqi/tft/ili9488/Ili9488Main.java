package com.chenqi.tft.ili9488;

import org.apache.log4j.Logger;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Ili9488Main {
    private static Logger LOG = Logger.getLogger(Ili9488Main.class);

    public static void main(String[] args) throws IOException {
        LOG.debug("com.chenqi.tft.Ili9488Main.Ili9488Main rum");
        Ili9488Driver.getInstance().drawImg16BitColor(getBufferImage());
        while (true){
            try {
                Thread.sleep(100000000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static BufferedImage getBufferImage() throws IOException {
        int width = Ili9488Driver.HEIGHT;
        int height = Ili9488Driver.WIDTH;

        //定义一个BufferedImage对象，用于保存缩小后的位图
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics graphics = bufferedImage.getGraphics();

        String path = Ili9488Main.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        String imgPath = path.substring(0, path.lastIndexOf(File.separator) + 1) + File.separator + "test.jpg";
        System.out.println("imgPath = " + imgPath);
        //读取原始位图
        Image srcImage = ImageIO.read(new File(imgPath));

        //将原始位图缩小后绘制到bufferedImage对象中
        graphics.drawImage(srcImage, 0, 0, width, height, null);
        //将bufferedImage对象输出到磁盘上

        return bufferedImage;
    }
}
