package com.bxrbasov.app.util;

import lombok.experimental.UtilityClass;
import java.nio.file.Path;

@UtilityClass
public class ImageBasePath {

    private static final Path BASE_PATH = Path.of("C:\\Users\\admin\\OneDrive\\Рабочий стол\\ProjectsForGit\\nb-project");

    public static Path getBasePath() {
        return BASE_PATH;
    }

}
