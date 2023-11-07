package Binary_image_representation;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

// This task was to count magpies on a picture and then display them on a new picture while removing the rest of the image.
// I used an algorithm that compares the reference image with the test image and if the pixels match then it's a magpie.

public class CountMagpies {
    int[][] referenceBinaryImage;
    int[][] testBinaryImage;
    int[][] newBinaryImage;
    int width;
    int height;
    int ref_width;
    int ref_height;

    public CountMagpies() {
        referenceBinaryImage = ImageToArray(LoadImage("ref_image.tif"));
        testBinaryImage = ImageToArray(LoadImage("test_image.tif"));
        width = testBinaryImage.length;
        height = testBinaryImage[0].length;
        ref_width = referenceBinaryImage.length;
        ref_height = referenceBinaryImage[0].length;
        newBinaryImage = ClearImage(GetMagpies());
        DisplayImage();
    }

    final BufferedImage LoadImage(final String fileName) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(fileName));
        } catch (IOException e) {
            System.out.println("Error loading image: " + e.getMessage());
        }
        return img;
    }

    private int[][] ImageToArray(BufferedImage img) {
        int width = img.getWidth(), height = img.getHeight();
        int[][] binaryArray = new int[height][width];
        int max_x = 0, min_x = width, max_y = 0, min_y = height;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int p = img.getRGB(x, y), a = (p >> 24) & 0xff, r = (p >> 16) & 0xff, g = (p >> 8) & 0xff,b = p & 0xff;
                if (r == 0 && g == 0 && b == 0 && a == 255) {
                    binaryArray[y][x] = 0;
                } else {
                    binaryArray[y][x] = 1;
                    if (x > max_x) {max_x = x;}
                    else if (x < min_x) {min_x = x;}
                    if (y > max_y) {max_y = y;}
                    else if (y < min_y) {min_y = y;}
                }
            }
        }
        int[][] croppedArray = new int[max_y - min_y + 1][max_x - min_x + 1];
        for (int y = min_y; y <= max_y; y++) {
            for (int x = min_x; x <= max_x; x++) {
                croppedArray[y - min_y][x - min_x] = binaryArray[y][x];
            }
        }
        return croppedArray;
    }

    public ArrayList<int[]> GetMagpies() {
        ArrayList<int[]> MagpiesCoordinates = new ArrayList<int[]>();
        for (int y = 0; y < height - ref_height; y++) {
            for (int x = 0; x < width - ref_width; x++) {
                boolean is_magpie = true;
                for (int i = 0; i < ref_width; i++) {
                    for (int j = 0; j < ref_height; j++) {
                        try {
                            if (testBinaryImage[x + i][y + j] != 1 && referenceBinaryImage[i][j] == 1) {
                                is_magpie = false;
                                break;
                            }
                        } catch (ArrayIndexOutOfBoundsException e) {
                            is_magpie = false;
                            break;
                        }
                    }
                }
                if (is_magpie) {
                    int[] coordinates = { x, y };
                    MagpiesCoordinates.add(coordinates);
                }
            }
        }
        System.out.println("Number of magpies: " + MagpiesCoordinates.size());
        return MagpiesCoordinates;
    }

    public int[][] ClearImage(ArrayList<int[]> MagpiesCoordinates) {
        int[][] newBinaryImage = new int[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                newBinaryImage[y][x]=0;
            }
        }
        for(int[] coords : MagpiesCoordinates) {
            for (int i = 0; i < ref_width; i++) {
                for (int j = 0; j < ref_height; j++) {
                    if (referenceBinaryImage[i][j] == 1) {
                        newBinaryImage[coords[0] + i][coords[1] + j] = 1;
                    }
                }
            }
        }
        return newBinaryImage;
    }

    public void DisplayImage() {
        try {
            var image = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
            Color white = new Color(255, 255, 255);
            int rgb = white.getRGB();
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    if (newBinaryImage[y][x] == 1) {
                        image.setRGB(x, y, rgb);
                    }
                }
            }
            File f = new File("final_image.tif");
            ImageIO.write(image, "tif", f);
        } catch (IOException e) {
            System.out.println("Error saving image: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new CountMagpies(); //dwie sroki mają lekko inne ogony od reszty dlatego nie są wykrywane
    }
}