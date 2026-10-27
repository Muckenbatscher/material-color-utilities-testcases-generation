import dynamiccolor.ColorSpec;
import dynamiccolor.DynamicScheme;

public class TestThemePermutation {
    public String VariantName;
    public ColorSpec.SpecVersion SpecVersion;
    public DynamicScheme.Platform Platform;
    public boolean IsDark;
    public TestThemeCreator.ContrastLevelType ContrastLevel;

    public TestThemePermutation(String variant,
                                 ColorSpec.SpecVersion specVersion,
                                 DynamicScheme.Platform platform,
                                 boolean isDark,
                                 TestThemeCreator.ContrastLevelType contrastLevel) {
        VariantName = variant;
        SpecVersion = specVersion;
        Platform = platform;
        IsDark = isDark;
        ContrastLevel = contrastLevel;
    }
}
