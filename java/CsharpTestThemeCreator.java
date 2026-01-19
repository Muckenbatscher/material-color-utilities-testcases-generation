import dynamiccolor.ColorSpec;
import dynamiccolor.DynamicScheme;
import hct.Hct;
import scheme.*;

public class CsharpTestThemeCreator {

    public enum ContrastLevelType{
        Normal,
        Medium,
        High
    }

    public static int GetColorCount(){
        return 8;
    }

    public static CsharpTestThemeClass GetCsharpTestThemeClass(int colorIndex,
                                                               boolean isDark,
                                                               ContrastLevelType contrastLevelType,
                                                               ColorSpec.SpecVersion specVersion,
                                                               String variantName){
        var hct = GetColor(colorIndex);
        var colorName = GetColorName(colorIndex);
        var contrastLevel = GetContrastLevelValue(contrastLevelType);
        var platform = DynamicScheme.Platform.PHONE;

        var scheme = CreateScheme(hct, isDark, contrastLevel, specVersion, platform, variantName);

        var csharpClassName = GetModeName(isDark) + "_"
                + GetContrastLevelName(contrastLevelType) + "_"
                + GetSpecName(specVersion) + "_"
                + colorName;

        var builder = new StringBuilder();
        builder.append("namespace MaterialTheming.Tests.KnownTestThemes." + variantName + ";").append("\r\n");
        builder.append("\r\n");
        builder.append("internal class " + csharpClassName + " : ITestTheme").append("\r\n");
        builder.append("{").append("\r\n");

        builder.append("    ").append(GetCsharpColorProperty("SourceColor", hct.toInt()) + " //" + colorName).append("\r\n");
        builder.append("    ").append(GetCsharpProperty("IsDark", "bool", isDark ? "true" : "false")).append("\r\n");
        builder.append("    ").append(GetCsharpProperty("Variant", "Variant", "Variant." + variantName)).append("\r\n");
        builder.append("    ").append(GetCsharpProperty("ContrastLevelValue", "double", Double.toString(contrastLevel))).append("\r\n");
        var specVersionCsharp = specVersion == ColorSpec.SpecVersion.SPEC_2021
                ? "Spec2021" : "Spec2025";
        builder.append("    ").append(GetCsharpProperty("SpecVersion", "SpecVersion", "SpecVersion." + specVersionCsharp)).append("\r\n");
        builder.append("\r\n");

        // Primary
        builder.append("    ").append("// Primary").append("\r\n");
        builder.append("    ").append(GetCsharpColorProperty("Primary", scheme.getPrimary())).append("\r\n");
        builder.append("    ").append(GetCsharpColorProperty("OnPrimary", scheme.getOnPrimary())).append("\r\n");
        builder.append("    ").append(GetCsharpColorProperty("PrimaryContainer", scheme.getPrimaryContainer())).append("\r\n");
        builder.append("    ").append(GetCsharpColorProperty("OnPrimaryContainer", scheme.getOnPrimaryContainer())).append("\r\n");

        // Secondary
        builder.append("    ").append("// Secondary").append("\r\n");
        builder.append("    ").append(GetCsharpColorProperty("Secondary", scheme.getSecondary())).append("\r\n");
        builder.append("    ").append(GetCsharpColorProperty("OnSecondary", scheme.getOnSecondary())).append("\r\n");
        builder.append("    ").append(GetCsharpColorProperty("SecondaryContainer", scheme.getSecondaryContainer())).append("\r\n");
        builder.append("    ").append(GetCsharpColorProperty("OnSecondaryContainer", scheme.getOnSecondaryContainer())).append("\r\n");

        // Tertiary
        builder.append("    ").append("// Tertiary").append("\r\n");
        builder.append("    ").append(GetCsharpColorProperty("Tertiary", scheme.getTertiary())).append("\r\n");
        builder.append("    ").append(GetCsharpColorProperty("OnTertiary", scheme.getOnTertiary())).append("\r\n");
        builder.append("    ").append(GetCsharpColorProperty("TertiaryContainer", scheme.getTertiaryContainer())).append("\r\n");
        builder.append("    ").append(GetCsharpColorProperty("OnTertiaryContainer", scheme.getOnTertiaryContainer())).append("\r\n");

        // Error
        builder.append("    ").append("// Error").append("\r\n");
        builder.append("    ").append(GetCsharpColorProperty("Error", scheme.getError())).append("\r\n");
        builder.append("    ").append(GetCsharpColorProperty("OnError", scheme.getOnError())).append("\r\n");
        builder.append("    ").append(GetCsharpColorProperty("ErrorContainer", scheme.getErrorContainer())).append("\r\n");
        builder.append("    ").append(GetCsharpColorProperty("OnErrorContainer", scheme.getOnErrorContainer())).append("\r\n");

        // Surface
        builder.append("    ").append("// Surface").append("\r\n");
        builder.append("    ").append(GetCsharpColorProperty("Surface", scheme.getSurface())).append("\r\n");
        builder.append("    ").append(GetCsharpColorProperty("SurfaceVariant", scheme.getSurfaceVariant())).append("\r\n");
        builder.append("    ").append(GetCsharpColorProperty("OnSurface", scheme.getOnSurface())).append("\r\n");
        builder.append("    ").append(GetCsharpColorProperty("OnSurfaceVariant", scheme.getOnSurfaceVariant())).append("\r\n");
        builder.append("    ").append(GetCsharpColorProperty("SurfaceDim", scheme.getSurfaceDim())).append("\r\n");
        builder.append("    ").append(GetCsharpColorProperty("SurfaceBright", scheme.getSurfaceBright())).append("\r\n");
        builder.append("    ").append(GetCsharpColorProperty("SurfaceTint", scheme.getSurfaceTint())).append("\r\n");

        // Background
        builder.append("    ").append("// Background").append("\r\n");
        builder.append("    ").append(GetCsharpColorProperty("Background", scheme.getBackground())).append("\r\n");
        builder.append("    ").append(GetCsharpColorProperty("OnBackground", scheme.getOnBackground())).append("\r\n");

        // Outline
        builder.append("    ").append("// Outline").append("\r\n");
        builder.append("    ").append(GetCsharpColorProperty("Outline", scheme.getOutline())).append("\r\n");
        builder.append("    ").append(GetCsharpColorProperty("OutlineVariant", scheme.getOutlineVariant())).append("\r\n");

        // Shadow
        builder.append("    ").append("// Shadow").append("\r\n");
        builder.append("    ").append(GetCsharpColorProperty("Shadow", scheme.getShadow())).append("\r\n");
        builder.append("    ").append(GetCsharpColorProperty("Scrim", scheme.getScrim())).append("\r\n");

        // Inverse
        builder.append("    ").append("// Inverse").append("\r\n");
        builder.append("    ").append(GetCsharpColorProperty("InverseSurface", scheme.getInverseSurface())).append("\r\n");
        builder.append("    ").append(GetCsharpColorProperty("InverseOnSurface", scheme.getInverseOnSurface())).append("\r\n");
        builder.append("    ").append(GetCsharpColorProperty("InversePrimary", scheme.getInversePrimary())).append("\r\n");

        // Primary Fixed
        builder.append("    ").append("// Primary Fixed").append("\r\n");
        builder.append("    ").append(GetCsharpColorProperty("PrimaryFixed", scheme.getPrimaryFixed())).append("\r\n");
        builder.append("    ").append(GetCsharpColorProperty("OnPrimaryFixed", scheme.getOnPrimaryFixed())).append("\r\n");
        builder.append("    ").append(GetCsharpColorProperty("PrimaryFixedDim", scheme.getPrimaryFixedDim())).append("\r\n");
        builder.append("    ").append(GetCsharpColorProperty("OnPrimaryFixedVariant", scheme.getOnPrimaryFixedVariant())).append("\r\n");

        // Secondary Fixed
        builder.append("    ").append("// Secondary Fixed").append("\r\n");
        builder.append("    ").append(GetCsharpColorProperty("SecondaryFixed", scheme.getSecondaryFixed())).append("\r\n");
        builder.append("    ").append(GetCsharpColorProperty("OnSecondaryFixed", scheme.getOnSecondaryFixed())).append("\r\n");
        builder.append("    ").append(GetCsharpColorProperty("SecondaryFixedDim", scheme.getSecondaryFixedDim())).append("\r\n");
        builder.append("    ").append(GetCsharpColorProperty("OnSecondaryFixedVariant", scheme.getOnSecondaryFixedVariant())).append("\r\n");

        // Tertiary Fixed
        builder.append("    ").append("// Tertiary Fixed").append("\r\n");
        builder.append("    ").append(GetCsharpColorProperty("TertiaryFixed", scheme.getTertiaryFixed())).append("\r\n");
        builder.append("    ").append(GetCsharpColorProperty("OnTertiaryFixed", scheme.getOnTertiaryFixed())).append("\r\n");
        builder.append("    ").append(GetCsharpColorProperty("TertiaryFixedDim", scheme.getTertiaryFixedDim())).append("\r\n");
        builder.append("    ").append(GetCsharpColorProperty("OnTertiaryFixedVariant", scheme.getOnTertiaryFixedVariant())).append("\r\n");

        // Surface Container
        builder.append("    ").append("// Surface Container").append("\r\n");
        builder.append("    ").append(GetCsharpColorProperty("SurfaceContainerLowest", scheme.getSurfaceContainerLowest())).append("\r\n");
        builder.append("    ").append(GetCsharpColorProperty("SurfaceContainerLow", scheme.getSurfaceContainerLow())).append("\r\n");
        builder.append("    ").append(GetCsharpColorProperty("SurfaceContainer", scheme.getSurfaceContainer())).append("\r\n");
        builder.append("    ").append(GetCsharpColorProperty("SurfaceContainerHigh", scheme.getSurfaceContainerHigh())).append("\r\n");
        builder.append("    ").append(GetCsharpColorProperty("SurfaceContainerHighest", scheme.getSurfaceContainerHighest())).append("\r\n");

        builder.append("}").append("\r\n");

        var fileContent = builder.toString();
        return new CsharpTestThemeClass(csharpClassName, variantName, fileContent);
    }

    private static DynamicScheme CreateScheme(Hct hct,
                                              boolean isDark,
                                              double contrastLevel,
                                              ColorSpec.SpecVersion specVersion,
                                              DynamicScheme.Platform platform,
                                              String variantName){
        return switch (variantName) {
            case "Monochrome" -> new SchemeMonochrome(hct, isDark, contrastLevel, specVersion, platform);
            case "Neutral" -> new SchemeNeutral(hct, isDark, contrastLevel, specVersion, platform);
            case "TonalSpot" -> new SchemeTonalSpot(hct, isDark, contrastLevel, specVersion, platform);
            case "Vibrant" -> new SchemeVibrant(hct, isDark, contrastLevel, specVersion, platform);
            case "Expressive" -> new SchemeExpressive(hct, isDark, contrastLevel, specVersion, platform);
            case "Fidelity" -> new SchemeFidelity(hct, isDark, contrastLevel, specVersion, platform);
            case "Content" -> new SchemeContent(hct, isDark, contrastLevel, specVersion, platform);
            case "Rainbow" -> new SchemeRainbow(hct, isDark, contrastLevel, specVersion, platform);
            case "FruitSalad" -> new SchemeFruitSalad(hct, isDark, contrastLevel, specVersion, platform);
            default -> null;
        };
    }

    private static Hct GetColor(int index){
        Hct[] colors = new Hct[] {
                Hct.from(25, 70, 50),
                Hct.from(50, 50, 60),
                Hct.from(110, 50, 70),
                Hct.from(150, 50, 50),
                Hct.from(210, 60, 70),
                Hct.from(260, 50, 40),
                Hct.from(310, 60, 50),
                Hct.from(350, 70, 60)
        };
        var sanitizedIndex = ((index % colors.length) + colors.length) % colors.length;
        return colors[sanitizedIndex];
    }
    private static String GetColorName(int index){
        String[] colors = new String[] {
                "Red",
                "Orange",
                "Yellow",
                "Green",
                "Cyan",
                "Blue",
                "Purple",
                "Magenta"
        };
        var sanitizedIndex = ((index % colors.length) + colors.length) % colors.length;
        return colors[sanitizedIndex];
    }
    private static double GetContrastLevelValue(ContrastLevelType contrastLevel){
        if (contrastLevel == ContrastLevelType.High)
            return 1.0;
        if (contrastLevel == ContrastLevelType.Medium)
            return 0.5;
        else
            return 0;
    }

    private static String GetModeName(boolean isDark){
        return isDark ? "DarkMode" : "LightMode";
    }
    private static String GetContrastLevelName(ContrastLevelType contrastLevel){
        if (contrastLevel == ContrastLevelType.High)
            return "HighContrast";
        if (contrastLevel == ContrastLevelType.Medium)
            return "MediumContrast";
        else
            return "NormalContrast";
    }
    private static String GetSpecName(ColorSpec.SpecVersion specVersion){
        if (specVersion == ColorSpec.SpecVersion.SPEC_2025)
            return "Spec2025";
        return "Spec2021";
    }

    private static String GetColorFromArgb(int argb){
        var hexString = Integer.toHexString(argb);
        // Ensure hex string has leading zeros if necessary
        while(hexString.length() < 8) {
            hexString = "0" + hexString;
        }
        var withoutAlpha = hexString.substring(2);
        return "#" + withoutAlpha;
    }

    private static String GetCsharpColorProperty(String propertyName, int argbColor){
        return GetCsharpProperty(propertyName, "string", "\"" + GetColorFromArgb(argbColor).toUpperCase() +"\"");
    }
    private static String GetCsharpProperty(String propertyName, String typeName, String valueLiteral){
        return "public " + typeName + " " + propertyName + " => " + valueLiteral + ";";
    }

}
