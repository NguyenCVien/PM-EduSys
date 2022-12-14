package com.polypro.utils;

import java.awt.Image;
import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.swing.ImageIcon;


public class XImage {
    public static Image getAppIcon() {
        String file = "/images/fpt.png";
        return new ImageIcon(XImage.class.getResource(file)).getImage();
    }
    
    //sao chep file logo chuyende vao thu muc logo
    public static void save(File src) {
        File dst = new File("images", src.getName());
        if(!dst.getParentFile().exists()) {
            dst.getParentFile().mkdirs();
            //Tao thu muc logos neu chua ton tai
        }
        try {
            Path from = Paths.get(src.getAbsolutePath());
            Path to = Paths.get(dst.getAbsolutePath());
            Files.copy(from,to,StandardCopyOption.REPLACE_EXISTING);
            //Copy file vao thu muc logos
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static ImageIcon read(String fileName) {
        File path = new File("images", fileName);
        return new ImageIcon(path.getAbsolutePath());
    }
}
