import dynamiccolor.ColorSpec;

public class TestThemePermutation {
    public String VariantName;
    public boolean IsDark;
    public CsharpTestThemeCreator.ContrastLevelType ContrastLevel;
    public ColorSpec.SpecVersion SpecVersion;

    public  TestThemePermutation(String variant, boolean isDark,
                                 CsharpTestThemeCreator.ContrastLevelType contrastLevel,
                                 ColorSpec.SpecVersion specVersion){
        VariantName = variant;
        IsDark = isDark;
        ContrastLevel = contrastLevel;
        SpecVersion = specVersion;
    }
}
