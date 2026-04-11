import dynamiccolor.ColorSpec;
import dynamiccolor.DynamicScheme;
import hct.Hct;
import scheme.*;
import java.util.ArrayList;

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
                                                               int colorIndexTwo,
                                                               boolean isDark,
                                                               ContrastLevelType contrastLevelType,
                                                               ColorSpec.SpecVersion specVersion,
                                                               String variantName){
        var specifiedSecondColor = colorIndexTwo >= 0;
        var hct = GetColor(colorIndex);
        var colorName = GetColorName(colorIndex);
        var contrastLevel = GetContrastLevelValue(contrastLevelType);
        var platform = DynamicScheme.Platform.PHONE;

        String secondColorName = "";
        Hct secondHct = null;
        if (specifiedSecondColor){
            secondColorName = GetColorName(colorIndexTwo);
            secondHct = GetColor(colorIndexTwo);
        }
        var scheme = CreateScheme(hct, secondHct, isDark, contrastLevel, specVersion, platform, variantName);

        var csharpClassName = GetModeName(isDark) + "_"
                + GetContrastLevelName(contrastLevelType) + "_"
                + GetSpecName(specVersion) + "_"
                + colorName;
        if (specifiedSecondColor){
            csharpClassName = csharpClassName + "_" + secondColorName;
        }

        var newLine = System.lineSeparator();
        var builder = new StringBuilder();
        builder.append("namespace MaterialTheming.Tests.KnownTestThemes." + variantName + ";").append(newLine);
        builder.append(newLine);
        var interfaceName = specifiedSecondColor
                ? "ITestThemeSecondSourceColor"
                : "ITestTheme";
        builder.append("internal class " + csharpClassName + " : " + interfaceName).append(newLine);
        builder.append("{").append(newLine);

        builder.append("    ").append(GetCsharpColorProperty("SourceColor", hct.toInt()) + " //" + colorName).append(newLine);
        if (specifiedSecondColor){
            builder.append("    ").append(GetCsharpColorProperty("SecondSourceColor", secondHct.toInt()) + " //" + secondColorName).append(newLine);
        }
        builder.append("    ").append(GetCsharpProperty("IsDark", "bool", isDark ? "true" : "false")).append(newLine);
        builder.append("    ").append(GetCsharpProperty("Variant", "Variant", "Variant." + variantName)).append(newLine);
        builder.append("    ").append(GetCsharpProperty("ContrastLevelValue", "double", Double.toString(contrastLevel))).append(newLine);
        var specVersionCsharp = GetSpecName(specVersion);
        builder.append("    ").append(GetCsharpProperty("SpecVersion", "SpecVersion", "SpecVersion." + specVersionCsharp)).append(newLine);
        builder.append(newLine);

        // Primary
        builder.append("    ").append("// Primary").append(newLine);
        builder.append("    ").append(GetCsharpColorProperty("Primary", scheme.getPrimary())).append(newLine);
        builder.append("    ").append(GetCsharpColorProperty("OnPrimary", scheme.getOnPrimary())).append(newLine);
        builder.append("    ").append(GetCsharpColorProperty("PrimaryContainer", scheme.getPrimaryContainer())).append(newLine);
        builder.append("    ").append(GetCsharpColorProperty("OnPrimaryContainer", scheme.getOnPrimaryContainer())).append(newLine);

        // Secondary
        builder.append("    ").append("// Secondary").append(newLine);
        builder.append("    ").append(GetCsharpColorProperty("Secondary", scheme.getSecondary())).append(newLine);
        builder.append("    ").append(GetCsharpColorProperty("OnSecondary", scheme.getOnSecondary())).append(newLine);
        builder.append("    ").append(GetCsharpColorProperty("SecondaryContainer", scheme.getSecondaryContainer())).append(newLine);
        builder.append("    ").append(GetCsharpColorProperty("OnSecondaryContainer", scheme.getOnSecondaryContainer())).append(newLine);

        // Tertiary
        builder.append("    ").append("// Tertiary").append(newLine);
        builder.append("    ").append(GetCsharpColorProperty("Tertiary", scheme.getTertiary())).append(newLine);
        builder.append("    ").append(GetCsharpColorProperty("OnTertiary", scheme.getOnTertiary())).append(newLine);
        builder.append("    ").append(GetCsharpColorProperty("TertiaryContainer", scheme.getTertiaryContainer())).append(newLine);
        builder.append("    ").append(GetCsharpColorProperty("OnTertiaryContainer", scheme.getOnTertiaryContainer())).append(newLine);

        // Error
        builder.append("    ").append("// Error").append(newLine);
        builder.append("    ").append(GetCsharpColorProperty("Error", scheme.getError())).append(newLine);
        builder.append("    ").append(GetCsharpColorProperty("OnError", scheme.getOnError())).append(newLine);
        builder.append("    ").append(GetCsharpColorProperty("ErrorContainer", scheme.getErrorContainer())).append(newLine);
        builder.append("    ").append(GetCsharpColorProperty("OnErrorContainer", scheme.getOnErrorContainer())).append(newLine);

        // Surface
        builder.append("    ").append("// Surface").append(newLine);
        builder.append("    ").append(GetCsharpColorProperty("Surface", scheme.getSurface())).append(newLine);
        builder.append("    ").append(GetCsharpColorProperty("SurfaceVariant", scheme.getSurfaceVariant())).append(newLine);
        builder.append("    ").append(GetCsharpColorProperty("OnSurface", scheme.getOnSurface())).append(newLine);
        builder.append("    ").append(GetCsharpColorProperty("OnSurfaceVariant", scheme.getOnSurfaceVariant())).append(newLine);
        builder.append("    ").append(GetCsharpColorProperty("SurfaceDim", scheme.getSurfaceDim())).append(newLine);
        builder.append("    ").append(GetCsharpColorProperty("SurfaceBright", scheme.getSurfaceBright())).append(newLine);
        builder.append("    ").append(GetCsharpColorProperty("SurfaceTint", scheme.getSurfaceTint())).append(newLine);

        // Background
        builder.append("    ").append("// Background").append(newLine);
        builder.append("    ").append(GetCsharpColorProperty("Background", scheme.getBackground())).append(newLine);
        builder.append("    ").append(GetCsharpColorProperty("OnBackground", scheme.getOnBackground())).append(newLine);

        // Outline
        builder.append("    ").append("// Outline").append(newLine);
        builder.append("    ").append(GetCsharpColorProperty("Outline", scheme.getOutline())).append(newLine);
        builder.append("    ").append(GetCsharpColorProperty("OutlineVariant", scheme.getOutlineVariant())).append(newLine);

        // Shadow
        builder.append("    ").append("// Shadow").append(newLine);
        builder.append("    ").append(GetCsharpColorProperty("Shadow", scheme.getShadow())).append(newLine);
        builder.append("    ").append(GetCsharpColorProperty("Scrim", scheme.getScrim())).append(newLine);

        // Inverse
        builder.append("    ").append("// Inverse").append(newLine);
        builder.append("    ").append(GetCsharpColorProperty("InverseSurface", scheme.getInverseSurface())).append(newLine);
        builder.append("    ").append(GetCsharpColorProperty("InverseOnSurface", scheme.getInverseOnSurface())).append(newLine);
        builder.append("    ").append(GetCsharpColorProperty("InversePrimary", scheme.getInversePrimary())).append(newLine);

        // Primary Fixed
        builder.append("    ").append("// Primary Fixed").append(newLine);
        builder.append("    ").append(GetCsharpColorProperty("PrimaryFixed", scheme.getPrimaryFixed())).append(newLine);
        builder.append("    ").append(GetCsharpColorProperty("OnPrimaryFixed", scheme.getOnPrimaryFixed())).append(newLine);
        builder.append("    ").append(GetCsharpColorProperty("PrimaryFixedDim", scheme.getPrimaryFixedDim())).append(newLine);
        builder.append("    ").append(GetCsharpColorProperty("OnPrimaryFixedVariant", scheme.getOnPrimaryFixedVariant())).append(newLine);

        // Secondary Fixed
        builder.append("    ").append("// Secondary Fixed").append(newLine);
        builder.append("    ").append(GetCsharpColorProperty("SecondaryFixed", scheme.getSecondaryFixed())).append(newLine);
        builder.append("    ").append(GetCsharpColorProperty("OnSecondaryFixed", scheme.getOnSecondaryFixed())).append(newLine);
        builder.append("    ").append(GetCsharpColorProperty("SecondaryFixedDim", scheme.getSecondaryFixedDim())).append(newLine);
        builder.append("    ").append(GetCsharpColorProperty("OnSecondaryFixedVariant", scheme.getOnSecondaryFixedVariant())).append(newLine);

        // Tertiary Fixed
        builder.append("    ").append("// Tertiary Fixed").append(newLine);
        builder.append("    ").append(GetCsharpColorProperty("TertiaryFixed", scheme.getTertiaryFixed())).append(newLine);
        builder.append("    ").append(GetCsharpColorProperty("OnTertiaryFixed", scheme.getOnTertiaryFixed())).append(newLine);
        builder.append("    ").append(GetCsharpColorProperty("TertiaryFixedDim", scheme.getTertiaryFixedDim())).append(newLine);
        builder.append("    ").append(GetCsharpColorProperty("OnTertiaryFixedVariant", scheme.getOnTertiaryFixedVariant())).append(newLine);

        // Surface Container
        builder.append("    ").append("// Surface Container").append(newLine);
        builder.append("    ").append(GetCsharpColorProperty("SurfaceContainerLowest", scheme.getSurfaceContainerLowest())).append(newLine);
        builder.append("    ").append(GetCsharpColorProperty("SurfaceContainerLow", scheme.getSurfaceContainerLow())).append(newLine);
        builder.append("    ").append(GetCsharpColorProperty("SurfaceContainer", scheme.getSurfaceContainer())).append(newLine);
        builder.append("    ").append(GetCsharpColorProperty("SurfaceContainerHigh", scheme.getSurfaceContainerHigh())).append(newLine);
        builder.append("    ").append(GetCsharpColorProperty("SurfaceContainerHighest", scheme.getSurfaceContainerHighest())).append(newLine);

        builder.append("}").append(newLine);

        var fileContent = builder.toString();
        return new CsharpTestThemeClass(csharpClassName, variantName, fileContent);
    }

    private static DynamicScheme CreateScheme(Hct hct,
                                              Hct secondHct,
                                              boolean isDark,
                                              double contrastLevel,
                                              ColorSpec.SpecVersion specVersion,
                                              DynamicScheme.Platform platform,
                                              String variantName){
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
        if (specVersion == ColorSpec.SpecVersion.SPEC_2026)
            return "Spec2026";
        else if (specVersion == ColorSpec.SpecVersion.SPEC_2025)
            return "Spec2025";
        else
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
