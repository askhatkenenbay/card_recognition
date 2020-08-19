import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Temp {
    public static String FOLDER_NAME = "/home/askhat/java_test_task/imgs/";
    public static int WIDTH = 360;
    public static int HEIGHT = 100;
    public static Color BLACK = new Color(35, 35, 38);
    public static Color BLACK_S = new Color(16, 16, 18);
    public static Color RED = new Color(205, 73, 73);
    public static Color RED_S = new Color(96, 34, 34);
    public static Color WHITE = new Color(255, 255, 255);
    public static Color WHITE_S = new Color(120, 120, 120);

    public static void main(String[] args) {
        File folder = new File(FOLDER_NAME);
        File[] files = folder.listFiles();
        BufferedImage image;
        int missCount = 0;
        try {
            for (File file : files) {
                image = ImageIO.read(file);
                String res = getCards(image);
                if (res.contains("_")) {
                    System.out.println("MISS: " + file.getName() + ":" + res);
                    missCount++;
                } else {
                    System.out.println(file.getName() + ":" + res);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Success:" + ((files.length - missCount) / files.length) * 100);
        System.out.println("Miss_Count:" + missCount);
    }

    public static String getCards(BufferedImage image) {
        StringBuilder res = new StringBuilder();
        BufferedImage cut = image.getSubimage(140, 580, WIDTH, HEIGHT);
        List<BufferedImage> cards = divide(cut);
        for (BufferedImage curr : cards) {
            int pixel = curr.getRGB(55, 20);
            Color color = new Color(pixel, true);
            if (color.equals(WHITE) || color.equals(WHITE_S)) {
                res.append(getCardNum(curr)).append(getCardType(curr));
            }
        }
        return res.toString();
    }

    public static List<BufferedImage> divide(BufferedImage image) {
        List<BufferedImage> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            BufferedImage cut = image.getSubimage(i * 72, 0, 72, 100);
            list.add(cut);
        }
        return list;
    }

    public static String getCardNum(BufferedImage image) {
        image = image.getSubimage(5, 10, 30, 25);
        if (isTwo(image)) {
            return "2";
        }
        if (isThree(image)) {
            return "3";
        }
        if (isFour(image)) {
            return "4";
        }
        if (isFive(image)) {
            return "5";
        }
        if (isSix(image)) {
            return "6";
        }
        if (isSeven(image)) {
            return "7";
        }
        if (isTen(image)) {
            return "10";
        }
        if (isJack(image)) {
            return "J";
        }
        if (isKing(image)) {
            return "K";
        }
        if (isAce(image)) {
            return "A";
        }
        if (isNine(image)) {
            return "9";
        }
        if (isEight(image)) {
            return "8";
        }
        if (isQueen(image)) {
            return "Q";
        }
        return "_";
    }

    public static boolean isWhite(BufferedImage image, int x, int y) {
        int pixel = image.getRGB(x, y);
        Color color = new Color(pixel, true);
        return color.equals(WHITE) || color.equals(WHITE_S);
    }

    public static boolean isTwo(BufferedImage image) {
        for (int i = 11, j = 4; i <= 19; i++) {
            if (isWhite(image, i, j)) {
                return false;
            }
        }
        for (int i = 19, j = 6; j <= 12; j++) {
            if (isWhite(image, i, j)) {
                return false;
            }
        }
        for (int i = 19, j = 12, k = 0; k < 7; i--, j++, k++) {
            if (isWhite(image, i, j)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isThree(BufferedImage image) {
        for (int i = 10, j = 4; i <= 20; i++) {
            if (isWhite(image, i, j)) {
                return false;
            }
        }
        for (int i = 19, j = 6, k = 0; k < 7; i--, j++, k++) {
            if (isWhite(image, i, j)) {
                return false;
            }
        }
        for (int i = 17, j = 13, k = 0; k < 4; i++, j++, k++) {
            if (isWhite(image, i, j)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isFour(BufferedImage image) {
        for (int i = 20, j = 5; j <= 18; j++) {
            if (isWhite(image, i, j)) {
                return false;
            }
        }
        for (int i = 10, j = 18; i <= 24; i++) {
            if (isWhite(image, i, j)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isFive(BufferedImage image) {
        int x = 10;
        int y = 4;
        for (int i = 10, j = 4, k = 0; k < 9; i++, j++, k++) {
            if (isWhite(image, i, y) || isWhite(image, x, j)) {
                return false;
            }
        }
        for (int i = 8, j = 17; i <= 14; i++) {
            if (!isWhite(image, i, j)) {
                return false;
            }
        }
        for (int i = 14, j = 8; i <= 26; i++) {
            if (!isWhite(image, i, j)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isSix(BufferedImage image) {
        for (int i = 17, j = 9; i <= 28; i++) {
            if (!isWhite(image, i, j)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isSeven(BufferedImage image) {
        for (int i = 10, j = 4; i <= 21; i++) {
            if (isWhite(image, i, j)) {
                return false;
            }
        }
        return !isWhite(image, 19, 8) && !isWhite(image, 14, 19);
    }

    public static boolean isEight(BufferedImage image) {
        return !isWhite(image, 15, 4) && !isWhite(image, 20, 8) && !isWhite(image, 15, 13) && !isWhite(image, 21, 18) && !isWhite(image, 15, 23) && !isWhite(image, 8, 17) && !isWhite(image, 9, 7) ;
    }

    public static boolean isNine(BufferedImage image) {
        for (int i = 2, j = 17; i <= 11; i++) {
            if (!isWhite(image, i, j)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isTen(BufferedImage image) {
        for (int i = 9, j = 3; j <= 21; j++) {
            if (isWhite(image, i, j)) {
                return false;
            }
        }
        for (int i = 15, j = 8, k = 29; j <= 17; j++) {
            if (isWhite(image, i, j) || isWhite(image, k, j)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isJack(BufferedImage image) {
        for (int i = 8, j = 4, k = 19; j <= 17; j++) {
            if (!isWhite(image, i, j)) {
                return false;
            }
            if (isWhite(image, k, j)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isQueen(BufferedImage image) {
        for (int i = 28, j = 11; j < 15; j++) {
            if (isWhite(image, i, j)) {
                return false;
            }
        }
        for (int i = 17, j = 4; i < 21; i++) {
            if (isWhite(image, i, j)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isKing(BufferedImage image) {
        for (int i = 10, j = 4; j <= 22; j++) {
            if (isWhite(image, i, j)) {
                return false;
            }
        }
        for (int i = 15, j = 2; j <= 6; j++) {
            if (!isWhite(image, i, j)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isAce(BufferedImage image) {
        for (int i = 8, j = 18; i <= 18; i++) {
            if (isWhite(image, i, j)) {
                return false;
            }
        }
        return true;
    }

    public static String getCardType(BufferedImage image) {
        int pixel = image.getRGB(45, 75);
        Color color = new Color(pixel, true);
        if (color.equals(BLACK) || color.equals(BLACK_S)) {
            color = new Color(image.getRGB(40, 57), true);
            if (color.equals(BLACK) || color.equals(BLACK_S)) {
                return "c";
            } else {
                return "s";
            }
        } else if (color.equals(RED) || color.equals(RED_S)) {
            color = new Color(image.getRGB(45, 60), true);
            if (color.equals(RED) || color.equals(RED_S)) {
                return "d";
            } else {
                return "h";
            }
        }
        return "|card|";
    }
}
