package be.devriendt.advent.s2015;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by Dennis on 5/12/2015.
 */
public class Day2SurfaceArea {

    public static int shoppingListDimensionsToSurfaceArea(String resourcePath) throws URISyntaxException, IOException {
        return Files.lines(Paths.get(Day2SurfaceArea.class.getResource(resourcePath).toURI()))
                .mapToInt(l -> Day2SurfaceArea.dimensionsToSurfaceArea(l))
                .sum();
    }

    public static int dimensionsToSurfaceArea(String input) {
        int[] dimensions = inputStringToArray(input);
        return dimensionsToSurfaceArea(dimensions[0], dimensions[1], dimensions[2]);
    }

    public static int dimensionsToSurfaceArea(int h, int w, int l) {
        int a = l * w;
        int b = w * h;
        int c = h * l;

        return 2 * a + 2 * b + 2 * c + Math.min(a, Math.min(b, c));
    }

    public static int shoppingListDimensionsToRibbonLength(String resourcePath) throws URISyntaxException, IOException {
        return Files.lines(Paths.get(Day2SurfaceArea.class.getResource(resourcePath).toURI()))
                .mapToInt(l -> Day2SurfaceArea.dimensionsToRibbonLength(l))
                .sum();
    }

    public static int dimensionsToRibbonLength(String input) {
        int[] dimensions = inputStringToArray(input);
        return dimensionsToRibbonLength(dimensions[0], dimensions[1], dimensions[2]);
    }

    public static int dimensionsToRibbonLength(int h, int w, int l) {
        int a = 2 * (l + w);
        int b = 2 * (w + h);
        int c = 2 * (h + l);

        int smallestPerimeter = Math.min(a, Math.min(b, c));
        return smallestPerimeter + h * w * l;
    }

    private static int[] inputStringToArray(String input) {
        String[] parts = input.split("x");
        return new int[] {Integer.parseInt(parts[0]),
                Integer.parseInt(parts[1]),
                Integer.parseInt(parts[2])};
    }
}
