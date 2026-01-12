import dynamiccolor.ColorSpec;
import scheme.*;

import java.util.ArrayList;
import java.util.List;

public class TestThemePermutationCreator {
    public static List<TestThemePermutation> CreatePermutations(){
        var permutations = new ArrayList<TestThemePermutation>();
        var variants = GetVariants();
        for (int i = 0; i < variants.length; i++) {
            var variant = variants[i];
            permutations.add(new TestThemePermutation(variant, false, CsharpTestThemeCreator.ContrastLevelType.Normal, ColorSpec.SpecVersion.SPEC_2021));
            permutations.add(new TestThemePermutation(variant, false, CsharpTestThemeCreator.ContrastLevelType.Medium, ColorSpec.SpecVersion.SPEC_2021));
            permutations.add(new TestThemePermutation(variant, false, CsharpTestThemeCreator.ContrastLevelType.High, ColorSpec.SpecVersion.SPEC_2021));
            permutations.add(new TestThemePermutation(variant, true, CsharpTestThemeCreator.ContrastLevelType.Normal, ColorSpec.SpecVersion.SPEC_2021));
            permutations.add(new TestThemePermutation(variant, true, CsharpTestThemeCreator.ContrastLevelType.Medium, ColorSpec.SpecVersion.SPEC_2021));
            permutations.add(new TestThemePermutation(variant, true, CsharpTestThemeCreator.ContrastLevelType.High, ColorSpec.SpecVersion.SPEC_2021));

            permutations.add(new TestThemePermutation(variant, false, CsharpTestThemeCreator.ContrastLevelType.Normal, ColorSpec.SpecVersion.SPEC_2025));
            permutations.add(new TestThemePermutation(variant, false, CsharpTestThemeCreator.ContrastLevelType.Medium, ColorSpec.SpecVersion.SPEC_2025));
            permutations.add(new TestThemePermutation(variant, false, CsharpTestThemeCreator.ContrastLevelType.High, ColorSpec.SpecVersion.SPEC_2025));
            permutations.add(new TestThemePermutation(variant, true, CsharpTestThemeCreator.ContrastLevelType.Normal, ColorSpec.SpecVersion.SPEC_2025));
            permutations.add(new TestThemePermutation(variant, true, CsharpTestThemeCreator.ContrastLevelType.Medium, ColorSpec.SpecVersion.SPEC_2025));
            permutations.add(new TestThemePermutation(variant, true, CsharpTestThemeCreator.ContrastLevelType.High, ColorSpec.SpecVersion.SPEC_2025));
        }
        return permutations;
    }

    private static String[] GetVariants(){
        return new String[] {
            "Monochrome",
            "Neutral",
            "TonalSpot",
            "Vibrant",
            "Expressive",
            "Fidelity",
            "Content",
            "Rainbow",
            "FruitSalad"
        };
    }
}
