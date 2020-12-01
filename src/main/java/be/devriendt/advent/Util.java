package be.devriendt.advent;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class Util {

    public static List<String> getContent(String resourcePath) throws URISyntaxException, IOException {
        return Files.lines(Paths.get(Util.class.getResource(resourcePath).toURI()))
                .collect(Collectors.toList());
    }

}
