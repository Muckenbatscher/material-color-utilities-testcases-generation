import dynamiccolor.ColorSpec;
import dynamiccolor.DynamicScheme;
import hct.Hct;
import scheme.*;
import java.util.ArrayList;
import java.util.List;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

public class TestThemeCreator {

    private static final List<SourceColor> SOURCE_COLORS = List.of(
            new SourceColor("Red", Hct.from(25, 70, 50)),
            new SourceColor("Orange", Hct.from(50, 50, 60)),
            new SourceColor("Yellow", Hct.from(110, 50, 70)),
            new SourceColor("Green", Hct.from(150, 50, 50)),
            new SourceColor("Cyan", Hct.from(210, 60, 70)),
            new SourceColor("Blue", Hct.from(260, 50, 40)),
            new SourceColor("Purple", Hct.from(310, 60, 50)),
            new SourceColor("Magenta", Hct.from(350, 70, 60)));

    public enum ContrastLevelType {
        Normal,
        Medium,
        High
    }

    public static int GetColorCount() {
        return SOURCE_COLORS.size();
    }

    public static Map<String, Object> GetTestThemeCase(int colorIndex,
                                                       int colorIndexTwo,
                                                       boolean isDark,
                                                       ContrastLevelType contrastLevelType,
                                                       ColorSpec.SpecVersion specVersion,
                                                       DynamicScheme.Platform platform,
                                                       String variantName) {
        var sourceColor = GetSourceColor(colorIndex);
        var hasSecondColor = colorIndexTwo >= 0;
        var secondSourceColor = hasSecondColor ? GetSourceColor(colorIndexTwo) : null;
        var contrastLevel = GetContrastLevelValue(contrastLevelType);
        var scheme = CreateScheme(sourceColor.color(),
                hasSecondColor ? secondSourceColor.color() : null,
                isDark, contrastLevel, specVersion, platform, variantName);

        var name = variantName + "_"
                + GetSpecName(specVersion) + "_"
                + GetPlatformName(platform) + "_"
                + (isDark ? "DarkMode" : "LightMode") + "_"
                + GetContrastLevelName(contrastLevelType) + "_"
                + sourceColor.name();
        if (hasSecondColor) {
            name += "_" + secondSourceColor.name();
        }

        var inputs = new LinkedHashMap<String, Object>();
        inputs.put("color", GetColorFromArgb(sourceColor.color().toInt()));
        if (hasSecondColor) {
            inputs.put("second_color", GetColorFromArgb(secondSourceColor.color().toInt()));
        }
        inputs.put("is_dark", isDark);
        inputs.put("variant", variantName);
        inputs.put("platform", platform.name().toLowerCase(Locale.ROOT));
        inputs.put("spec_version", GetSpecVersionValue(specVersion));
        inputs.put("contrast_level", contrastLevel);

        var outputs = new LinkedHashMap<String, Object>();
        AddColor(outputs, "primary", scheme.getPrimary());
        AddColor(outputs, "on_primary", scheme.getOnPrimary());
        AddColor(outputs, "primary_container", scheme.getPrimaryContainer());
        AddColor(outputs, "on_primary_container", scheme.getOnPrimaryContainer());
        AddColor(outputs, "secondary", scheme.getSecondary());
        AddColor(outputs, "on_secondary", scheme.getOnSecondary());
        AddColor(outputs, "secondary_container", scheme.getSecondaryContainer());
        AddColor(outputs, "on_secondary_container", scheme.getOnSecondaryContainer());
        AddColor(outputs, "tertiary", scheme.getTertiary());
        AddColor(outputs, "on_tertiary", scheme.getOnTertiary());
        AddColor(outputs, "tertiary_container", scheme.getTertiaryContainer());
        AddColor(outputs, "on_tertiary_container", scheme.getOnTertiaryContainer());
        AddColor(outputs, "error", scheme.getError());
        AddColor(outputs, "on_error", scheme.getOnError());
        AddColor(outputs, "error_container", scheme.getErrorContainer());
        AddColor(outputs, "on_error_container", scheme.getOnErrorContainer());
        AddColor(outputs, "surface", scheme.getSurface());
        AddColor(outputs, "surface_variant", scheme.getSurfaceVariant());
        AddColor(outputs, "on_surface", scheme.getOnSurface());
        AddColor(outputs, "on_surface_variant", scheme.getOnSurfaceVariant());
        AddColor(outputs, "surface_dim", scheme.getSurfaceDim());
        AddColor(outputs, "surface_bright", scheme.getSurfaceBright());
        AddColor(outputs, "surface_tint", scheme.getSurfaceTint());
        AddColor(outputs, "background", scheme.getBackground());
        AddColor(outputs, "on_background", scheme.getOnBackground());
        AddColor(outputs, "outline", scheme.getOutline());
        AddColor(outputs, "outline_variant", scheme.getOutlineVariant());
        AddColor(outputs, "shadow", scheme.getShadow());
        AddColor(outputs, "scrim", scheme.getScrim());
        AddColor(outputs, "inverse_surface", scheme.getInverseSurface());
        AddColor(outputs, "inverse_on_surface", scheme.getInverseOnSurface());
        AddColor(outputs, "inverse_primary", scheme.getInversePrimary());
        AddColor(outputs, "primary_fixed", scheme.getPrimaryFixed());
        AddColor(outputs, "on_primary_fixed", scheme.getOnPrimaryFixed());
        AddColor(outputs, "primary_fixed_dim", scheme.getPrimaryFixedDim());
        AddColor(outputs, "on_primary_fixed_variant", scheme.getOnPrimaryFixedVariant());
        AddColor(outputs, "secondary_fixed", scheme.getSecondaryFixed());
        AddColor(outputs, "on_secondary_fixed", scheme.getOnSecondaryFixed());
        AddColor(outputs, "secondary_fixed_dim", scheme.getSecondaryFixedDim());
        AddColor(outputs, "on_secondary_fixed_variant", scheme.getOnSecondaryFixedVariant());
        AddColor(outputs, "tertiary_fixed", scheme.getTertiaryFixed());
        AddColor(outputs, "on_tertiary_fixed", scheme.getOnTertiaryFixed());
        AddColor(outputs, "tertiary_fixed_dim", scheme.getTertiaryFixedDim());
        AddColor(outputs, "on_tertiary_fixed_variant", scheme.getOnTertiaryFixedVariant());
        AddColor(outputs, "surface_container_lowest", scheme.getSurfaceContainerLowest());
        AddColor(outputs, "surface_container_low", scheme.getSurfaceContainerLow());
        AddColor(outputs, "surface_container", scheme.getSurfaceContainer());
        AddColor(outputs, "surface_container_high", scheme.getSurfaceContainerHigh());
        AddColor(outputs, "surface_container_highest", scheme.getSurfaceContainerHighest());

        var testCase = new LinkedHashMap<String, Object>();
        testCase.put("name", name);
        testCase.put("inputs", inputs);
        testCase.put("outputs", outputs);
        return testCase;
    }

    private static DynamicScheme CreateScheme(Hct hct,
                                              Hct secondHct,
                                              boolean isDark,
                                              double contrastLevel,
                                              ColorSpec.SpecVersion specVersion,
                                              DynamicScheme.Platform platform,
                                              String variantName) {
        var sourceColors = new ArrayList<Hct>();
        sourceColors.add(hct);
        if (secondHct != null) {
            sourceColors.add(secondHct);
        }
        return switch (variantName) {
            case "Monochrome" -> new SchemeMonochrome(sourceColors, isDark, contrastLevel, specVersion, platform);
            case "Neutral" -> new SchemeNeutral(sourceColors, isDark, contrastLevel, specVersion, platform);
            case "TonalSpot" -> new SchemeTonalSpot(sourceColors, isDark, contrastLevel, specVersion, platform);
            case "Vibrant" -> new SchemeVibrant(sourceColors, isDark, contrastLevel, specVersion, platform);
            case "Expressive" -> new SchemeExpressive(sourceColors, isDark, contrastLevel, specVersion, platform);
            case "Fidelity" -> new SchemeFidelity(sourceColors, isDark, contrastLevel, specVersion, platform);
            case "Content" -> new SchemeContent(sourceColors, isDark, contrastLevel, specVersion, platform);
            case "Rainbow" -> new SchemeRainbow(sourceColors, isDark, contrastLevel, specVersion, platform);
            case "FruitSalad" -> new SchemeFruitSalad(sourceColors, isDark, contrastLevel, specVersion, platform);
            case "CMF" -> new SchemeCmf(sourceColors, isDark, contrastLevel, specVersion, platform);
            default -> throw new IllegalArgumentException("Unknown variant: " + variantName);
        };
    }

    private static SourceColor GetSourceColor(int index) {
        var sourceColor = SOURCE_COLORS.get(Math.floorMod(index, SOURCE_COLORS.size()));
        return new SourceColor(sourceColor.name(), Hct.fromInt(sourceColor.color().toInt()));
    }

    private static double GetContrastLevelValue(ContrastLevelType contrastLevel) {
        return switch (contrastLevel) {
            case Normal -> 0;
            case Medium -> 0.5;
            case High -> 1.0;
        };
    }

    private static String GetContrastLevelName(ContrastLevelType contrastLevel) {
        return switch (contrastLevel) {
            case Normal -> "NormalContrast";
            case Medium -> "MediumContrast";
            case High -> "HighContrast";
        };
    }

    private static String GetSpecName(ColorSpec.SpecVersion specVersion) {
        return "Spec" + GetSpecVersionValue(specVersion);
    }

    private static int GetSpecVersionValue(ColorSpec.SpecVersion specVersion) {
        return switch (specVersion) {
            case SPEC_2021 -> 2021;
            case SPEC_2025 -> 2025;
            case SPEC_2026 -> 2026;
        };
    }

    private static String GetPlatformName(DynamicScheme.Platform platform) {
        return switch (platform) {
            case PHONE -> "Phone";
            case WATCH -> "Watch";
        };
    }

    private static void AddColor(Map<String, Object> colors, String name, int argb) {
        colors.put(name, GetColorFromArgb(argb));
    }

    private static String GetColorFromArgb(int argb) {
        return String.format("#%06X", argb & 0x00FFFFFF);
    }
}
