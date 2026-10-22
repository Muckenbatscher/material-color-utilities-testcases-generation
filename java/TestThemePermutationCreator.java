import dynamiccolor.ColorSpec;
import dynamiccolor.DynamicScheme;
import java.util.ArrayList;
import java.util.List;

public class TestThemePermutationCreator {
    public static List<TestThemePermutation> CreatePermutations() {
        var permutations = new ArrayList<TestThemePermutation>();
        for (var variant : GetVariants()) {
            for (var specVersion : GetSpecVersions(variant)) {
                for (var platform : DynamicScheme.Platform.values()) {
                    for (var isDark : new boolean[] {false, true}) {
                        for (var contrastLevel : TestThemeCreator.ContrastLevelType.values()) {
                            permutations.add(new TestThemePermutation(
                                    variant, specVersion, platform, isDark, contrastLevel));
                        }
                    }
                }
            }
        }
        return permutations;
    }

    private static String[] GetVariants() {
        return new String[] {
                "Monochrome",
                "Neutral",
                "TonalSpot",
                "Vibrant",
                "Expressive",
                "Fidelity",
                "Content",
                "Rainbow",
                "FruitSalad",
                "CMF"
        };
    }

    private static ColorSpec.SpecVersion[] GetSpecVersions(String variant) {
        if (variant.equals("CMF")) {
            return new ColorSpec.SpecVersion[] {ColorSpec.SpecVersion.SPEC_2026};
        }
        return ColorSpec.SpecVersion.values();
    }
}
