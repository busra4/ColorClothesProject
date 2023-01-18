package com.example.colorrecg;

import static android.content.ContentValues.TAG;

import android.graphics.Color;
import android.nfc.Tag;
import android.util.Log;
import android.widget.ArrayAdapter;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ColorUtil {

    //renklerin konulacağı liste oluşturuldu
    ArrayList<ColorName> content = new ArrayList<ColorName>();

    //kullanılan renkler listeye eklendi
    public ColorUtil(){
        content.add(new ColorName("Alice Blue",0xF0, 0xF8,0xFF));
        content.add(new ColorName("siyah",0, 0,0));
        content.add(new ColorName("beyaz",255, 255,255));
        content.add(new ColorName("AliceBlue", 0xF0, 0xF8, 0xFF));
        content.add(new ColorName("AntiqueWhite", 0xFA, 0xEB, 0xD7));
        content.add(new ColorName("Aqua", 0x00, 0xFF, 0xFF));
        content.add(new ColorName("Aquamarine", 0x7F, 0xFF, 0xD4));
        content.add(new ColorName("Azure", 0xF0, 0xFF, 0xFF));
        content.add(new ColorName("Beige", 0xF5, 0xF5, 0xDC));
        content.add(new ColorName("Bisque", 0xFF, 0xE4, 0xC4));
        content.add(new ColorName("siyah", 0x00, 0x00, 0x00));
        content.add(new ColorName("BlanchedAlmond", 0xFF, 0xEB, 0xCD));
        content.add(new ColorName("Blue", 0x00, 0x00, 0xFF));
        content.add(new ColorName("BlueViolet", 0x8A, 0x2B, 0xE2));
        content.add(new ColorName("Brown", 0xA5, 0x2A, 0x2A));
        content.add(new ColorName("BurlyWood", 0xDE, 0xB8, 0x87));
        content.add(new ColorName("CadetBlue", 0x5F, 0x9E, 0xA0));
        content.add(new ColorName("Chartreuse", 0x7F, 0xFF, 0x00));
        content.add(new ColorName("Chocolate", 0xD2, 0x69, 0x1E));
        content.add(new ColorName("Coral", 0xFF, 0x7F, 0x50));
        content.add(new ColorName("CornflowerBlue", 0x64, 0x95, 0xED));
        content.add(new ColorName("Cornsilk", 0xFF, 0xF8, 0xDC));
        content.add(new ColorName("Crimson", 0xDC, 0x14, 0x3C));
        content.add(new ColorName("Cyan", 0x00, 0xFF, 0xFF));
        content.add(new ColorName("DarkBlue", 0x00, 0x00, 0x8B));
        content.add(new ColorName("DarkCyan", 0x00, 0x8B, 0x8B));
        content.add(new ColorName("DarkGoldenRod", 0xB8, 0x86, 0x0B));
        content.add(new ColorName("DarkGray", 0xA9, 0xA9, 0xA9));
        content.add(new ColorName("DarkGreen", 0x00, 0x64, 0x00));
        content.add(new ColorName("DarkKhaki", 0xBD, 0xB7, 0x6B));
        content.add(new ColorName("DarkMagenta", 0x8B, 0x00, 0x8B));
        content.add(new ColorName("DarkOliveGreen", 0x55, 0x6B, 0x2F));
        content.add(new ColorName("DarkOrange", 0xFF, 0x8C, 0x00));
        content.add(new ColorName("DarkOrchid", 0x99, 0x32, 0xCC));
        content.add(new ColorName("DarkRed", 0x8B, 0x00, 0x00));
        content.add(new ColorName("DarkSalmon", 0xE9, 0x96, 0x7A));
        content.add(new ColorName("DarkSeaGreen", 0x8F, 0xBC, 0x8F));
        content.add(new ColorName("DarkSlateBlue", 0x48, 0x3D, 0x8B));
        content.add(new ColorName("DarkSlateGray", 0x2F, 0x4F, 0x4F));
        content.add(new ColorName("DarkTurquoise", 0x00, 0xCE, 0xD1));
        content.add(new ColorName("DarkViolet", 0x94, 0x00, 0xD3));
        content.add(new ColorName("DeepPink", 0xFF, 0x14, 0x93));
        content.add(new ColorName("DeepSkyBlue", 0x00, 0xBF, 0xFF));
        content.add(new ColorName("DimGray", 0x69, 0x69, 0x69));
        content.add(new ColorName("DodgerBlue", 0x1E, 0x90, 0xFF));
        content.add(new ColorName("FireBrick", 0xB2, 0x22, 0x22));
        content.add(new ColorName("FloralWhite", 0xFF, 0xFA, 0xF0));
        content.add(new ColorName("ForestGreen", 0x22, 0x8B, 0x22));
        content.add(new ColorName("Fuchsia", 0xFF, 0x00, 0xFF));
        content.add(new ColorName("Gainsboro", 0xDC, 0xDC, 0xDC));
        content.add(new ColorName("GhostWhite", 0xF8, 0xF8, 0xFF));
        content.add(new ColorName("Gold", 0xFF, 0xD7, 0x00));
        content.add(new ColorName("GoldenRod", 0xDA, 0xA5, 0x20));
        content.add(new ColorName("Gray", 0x80, 0x80, 0x80));
        content.add(new ColorName("Green", 0x00, 0x80, 0x00));
        content.add(new ColorName("GreenYellow", 0xAD, 0xFF, 0x2F));
        content.add(new ColorName("HoneyDew", 0xF0, 0xFF, 0xF0));
        content.add(new ColorName("HotPink", 0xFF, 0x69, 0xB4));
        content.add(new ColorName("IndianRed", 0xCD, 0x5C, 0x5C));
        content.add(new ColorName("Indigo", 0x4B, 0x00, 0x82));
        content.add(new ColorName("Ivory", 0xFF, 0xFF, 0xF0));
        content.add(new ColorName("Khaki", 0xF0, 0xE6, 0x8C));
        content.add(new ColorName("Lavender", 0xE6, 0xE6, 0xFA));
        content.add(new ColorName("LavenderBlush", 0xFF, 0xF0, 0xF5));
        content.add(new ColorName("LawnGreen", 0x7C, 0xFC, 0x00));
        content.add(new ColorName("LemonChiffon", 0xFF, 0xFA, 0xCD));
        content.add(new ColorName("LightBlue", 0xAD, 0xD8, 0xE6));
        content.add(new ColorName("LightCoral", 0xF0, 0x80, 0x80));
        content.add(new ColorName("LightCyan", 0xE0, 0xFF, 0xFF));
        content.add(new ColorName("LightGoldenRodYellow", 0xFA, 0xFA, 0xD2));
        content.add(new ColorName("LightGray", 0xD3, 0xD3, 0xD3));
        content.add(new ColorName("LightGreen", 0x90, 0xEE, 0x90));
        content.add(new ColorName("LightPink", 0xFF, 0xB6, 0xC1));
        content.add(new ColorName("LightSalmon", 0xFF, 0xA0, 0x7A));
        content.add(new ColorName("LightSeaGreen", 0x20, 0xB2, 0xAA));
        content.add(new ColorName("LightSkyBlue", 0x87, 0xCE, 0xFA));
        content.add(new ColorName("LightSlateGray", 0x77, 0x88, 0x99));
        content.add(new ColorName("LightSteelBlue", 0xB0, 0xC4, 0xDE));
        content.add(new ColorName("LightYellow", 0xFF, 0xFF, 0xE0));
        content.add(new ColorName("Lime", 0x00, 0xFF, 0x00));
        content.add(new ColorName("LimeGreen", 0x32, 0xCD, 0x32));
        content.add(new ColorName("Linen", 0xFA, 0xF0, 0xE6));
        content.add(new ColorName("Magenta", 0xFF, 0x00, 0xFF));
        content.add(new ColorName("Maroon", 0x80, 0x00, 0x00));
        content.add(new ColorName("MediumAquaMarine", 0x66, 0xCD, 0xAA));
        content.add(new ColorName("MediumBlue", 0x00, 0x00, 0xCD));
        content.add(new ColorName("MediumOrchid", 0xBA, 0x55, 0xD3));
        content.add(new ColorName("MediumPurple", 0x93, 0x70, 0xDB));
        content.add(new ColorName("MediumSeaGreen", 0x3C, 0xB3, 0x71));
        content.add(new ColorName("MediumSlateBlue", 0x7B, 0x68, 0xEE));
        content.add(new ColorName("MediumSpringGreen", 0x00, 0xFA, 0x9A));
        content.add(new ColorName("MediumTurquoise", 0x48, 0xD1, 0xCC));
        content.add(new ColorName("MediumVioletRed", 0xC7, 0x15, 0x85));
        content.add(new ColorName("MidnightBlue", 0x19, 0x19, 0x70));
        content.add(new ColorName("MintCream", 0xF5, 0xFF, 0xFA));
        content.add(new ColorName("MistyRose", 0xFF, 0xE4, 0xE1));
        content.add(new ColorName("Moccasin", 0xFF, 0xE4, 0xB5));
        content.add(new ColorName("NavajoWhite", 0xFF, 0xDE, 0xAD));
        content.add(new ColorName("Navy", 0x00, 0x00, 0x80));
        content.add(new ColorName("OldLace", 0xFD, 0xF5, 0xE6));
        content.add(new ColorName("Olive", 0x80, 0x80, 0x00));
        content.add(new ColorName("OliveDrab", 0x6B, 0x8E, 0x23));
        content.add(new ColorName("Orange", 0xFF, 0xA5, 0x00));
        content.add(new ColorName("OrangeRed", 0xFF, 0x45, 0x00));
        content.add(new ColorName("Orchid", 0xDA, 0x70, 0xD6));
        content.add(new ColorName("PaleGoldenRod", 0xEE, 0xE8, 0xAA));
        content.add(new ColorName("PaleGreen", 0x98, 0xFB, 0x98));
        content.add(new ColorName("PaleTurquoise", 0xAF, 0xEE, 0xEE));
        content.add(new ColorName("PaleVioletRed", 0xDB, 0x70, 0x93));
        content.add(new ColorName("PapayaWhip", 0xFF, 0xEF, 0xD5));
        content.add(new ColorName("PeachPuff", 0xFF, 0xDA, 0xB9));
        content.add(new ColorName("Peru", 0xCD, 0x85, 0x3F));
        content.add(new ColorName("Pink", 0xFF, 0xC0, 0xCB));
        content.add(new ColorName("Plum", 0xDD, 0xA0, 0xDD));
        content.add(new ColorName("PowderBlue", 0xB0, 0xE0, 0xE6));
        content.add(new ColorName("Purple", 0x80, 0x00, 0x80));
        content.add(new ColorName("Red", 0xFF, 0x00, 0x00));
        content.add(new ColorName("RosyBrown", 0xBC, 0x8F, 0x8F));
        content.add(new ColorName("RoyalBlue", 0x41, 0x69, 0xE1));
        content.add(new ColorName("SaddleBrown", 0x8B, 0x45, 0x13));
        content.add(new ColorName("Salmon", 0xFA, 0x80, 0x72));
        content.add(new ColorName("SandyBrown", 0xF4, 0xA4, 0x60));
        content.add(new ColorName("SeaGreen", 0x2E, 0x8B, 0x57));
        content.add(new ColorName("SeaShell", 0xFF, 0xF5, 0xEE));
        content.add(new ColorName("Sienna", 0xA0, 0x52, 0x2D));
        content.add(new ColorName("Silver", 0xC0, 0xC0, 0xC0));
        content.add(new ColorName("SkyBlue", 0x87, 0xCE, 0xEB));
        content.add(new ColorName("SlateBlue", 0x6A, 0x5A, 0xCD));
        content.add(new ColorName("SlateGray", 0x70, 0x80, 0x90));
        content.add(new ColorName("Snow", 0xFF, 0xFA, 0xFA));
        content.add(new ColorName("SpringGreen", 0x00, 0xFF, 0x7F));
        content.add(new ColorName("SteelBlue", 0x46, 0x82, 0xB4));
        content.add(new ColorName("Tan", 0xD2, 0xB4, 0x8C));
        content.add(new ColorName("Teal", 0x00, 0x80, 0x80));
        content.add(new ColorName("Thistle", 0xD8, 0xBF, 0xD8));
        content.add(new ColorName("Tomato", 0xFF, 0x63, 0x47));
        content.add(new ColorName("Turquoise", 0x40, 0xE0, 0xD0));
        content.add(new ColorName("Violet", 0xEE, 0x82, 0xEE));
        content.add(new ColorName("Wheat", 0xF5, 0xDE, 0xB3));
        content.add(new ColorName("White", 0xFF, 0xFF, 0xFF));
        content.add(new ColorName("WhiteSmoke", 0xF5, 0xF5, 0xF5));
        content.add(new ColorName("Yellow", 0xFF, 0xFF, 0x00));
        content.add(new ColorName("YellowGreen", 0x9A, 0xCD, 0x32));
    }
    //alınan değerler rgb'ye dönüştürülür
    //eğer uygun renk bulunamazsa "no matched color name" yazısı ekrana gelir
    public String getNameFromRgb(int r, int g, int b) {
        ColorName closestMatch = null;
        int minMSE = Integer.MAX_VALUE;
        int mse;
        for (ColorName c : content) {
            mse = c.computeMSE(r, g, b);
            if (mse < minMSE) {
                minMSE = mse;
                closestMatch = c;
            }
        }

        if (closestMatch != null) {
            return closestMatch.getName();
        } else {
            return "no matched color name ";
        }
    }

    //gerekli tanımlamalar yapılır
    public class ColorName {
        public int r,g,b;
        public String name;

        public ColorName(String name, int r, int g, int b) {
            this.r = r;
            this.g= g;
            this.b = b;
            this.name = name;
        }

        public int computeMSE (int pixR, int pixG, int pixB){

            return (int) (((pixR-r) * (pixR-r) + (pixG-g) * (pixG-g) + (pixB -b) * (pixB-b)) + 3);

        }

        public int getR() {
            return r;
        }public int getG() {
            return g;
        }
        public int getB(){
            return b;
        }

        public String getName(){
            return name;
        }
    }
}
