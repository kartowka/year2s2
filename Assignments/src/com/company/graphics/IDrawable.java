package com.company.graphics;

import java.awt.Graphics;
import java.io.IOException;

public interface IDrawable {
    public final static String FOLDER_PATH = System.getProperty("user.dir");
    //public final static String PICTURE_PATH = FOLDER_PATH+"/src/com/company/graphics/";  // MacOS
    public final static String PICTURE_PATH = FOLDER_PATH+"\\Assignments\\src\\com\\company\\graphics\\";  // Windows
    public void loadImages (String nm) throws IOException;
    public void drawObject (Graphics g);
}
