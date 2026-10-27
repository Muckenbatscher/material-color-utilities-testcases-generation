import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import dynamiccolor.ColorSpec;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class MyTest {
    public static void main(String[] args) throws IOException {
        String targetDirectoryPath;
        if (args.length > 0) {
            targetDirectoryPath = args[0];
        } else {
            System.out.print("Enter target directory path: ");
            BufferedReader inReader = new BufferedReader(new InputStreamReader(System.in));
            targetDirectoryPath = inReader.readLine();
        }

        var targetDirectory = Path.of(targetDirectoryPath);
        var objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
        var testThemePermutations = TestThemePermutationCreator.CreatePermutations();
        for (var permutation : testThemePermutations) {
            var testCases = CreateFromPermutation(permutation);
            SaveTestThemeCases(testCases, permutation, targetDirectory, objectMapper);
        }
    }

    private static List<Map<String, Object>> CreateFromPermutation(TestThemePermutation permutation) {
        var testCases = new ArrayList<Map<String, Object>>();
        for (int colorIndex = 0; colorIndex < TestThemeCreator.GetColorCount(); colorIndex++) {
            testCases.add(TestThemeCreator.GetTestThemeCase(colorIndex, -1,
                    permutation.IsDark, permutation.ContrastLevel, permutation.SpecVersion,
                    permutation.Platform, permutation.VariantName));

            if (permutation.VariantName.equals("CMF") || permutation.VariantName.equals("Content")) {
                for (int colorIndexTwo = 0; colorIndexTwo < TestThemeCreator.GetColorCount(); colorIndexTwo++) {
                    testCases.add(TestThemeCreator.GetTestThemeCase(colorIndex, colorIndexTwo,
                            permutation.IsDark, permutation.ContrastLevel, permutation.SpecVersion,
                            permutation.Platform, permutation.VariantName));
                }
            }
        }
        return testCases;
    }

    private static void SaveTestThemeCases(List<Map<String, Object>> testCases,
                                           TestThemePermutation permutation,
                                           Path targetDirectory,
                                           ObjectMapper objectMapper) throws IOException {
        var variantDirectory = permutation.VariantName;
        var specDirectory = "spec" + GetSpecVersionValue(permutation.SpecVersion);
        var platformDirectory = permutation.Platform.name().toLowerCase(Locale.ROOT);
        var modeDirectory = permutation.IsDark ? "dark" : "light";
        var contrastFileName = permutation.ContrastLevel.name().toLowerCase(Locale.ROOT) + ".json";
        var outputFile = targetDirectory.resolve(variantDirectory)
                .resolve(specDirectory)
                .resolve(platformDirectory)
                .resolve(modeDirectory)
                .resolve(contrastFileName);

        Files.createDirectories(outputFile.getParent());
        objectMapper.writeValue(outputFile.toFile(), testCases);
        System.out.println(outputFile);
    }

    private static int GetSpecVersionValue(ColorSpec.SpecVersion specVersion) {
        return switch (specVersion) {
            case SPEC_2021 -> 2021;
            case SPEC_2025 -> 2025;
            case SPEC_2026 -> 2026;
        };
    }
}
