public class CsharpTestThemeClass {
    public CsharpTestThemeClass(String className, String variantName, String fileContent){
        _className = className;
        _variantName = variantName;
        _fileContent = fileContent;
    }

    private final String _className;
    public String GetClassName(){
        return _className;
    }

    private final String _fileContent;
    public String GetFileContent(){
        return  _fileContent;
    }

    private final String _variantName;
    public String GetVariantName(){
        return _variantName;
    }
}
