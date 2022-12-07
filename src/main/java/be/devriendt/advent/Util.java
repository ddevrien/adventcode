package be.devriendt.advent;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Util {

    public static List<String> getContent(String resourcePath) throws URISyntaxException, IOException {
        return getLinesStream(resourcePath).collect(Collectors.toList());
    }

    public static List<Integer> getContentAsIntegers(String resourcePath) throws URISyntaxException, IOException {
        return getLinesStream(resourcePath)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    public static List<Long> getContentAsLongs(String resourcePath) throws URISyntaxException, IOException {
        return getLinesStream(resourcePath)
                .map(Long::parseLong)
                .collect(Collectors.toList());
    }

    public static String getContentSingleLine(String resourcePath) throws URISyntaxException, IOException {
        return getLinesStream(resourcePath).findFirst().get();
    }

    private static Stream<String> getLinesStream(String resourcePath) throws IOException, URISyntaxException {
        return Files.lines(Paths.get(Util.class.getResource(resourcePath).toURI()));
    }

}
