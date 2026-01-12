import dynamiccolor.ColorSpec;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class MyTest {
    public static void main(String[] args) {
        String targetDirectoryPath;
        System.out.print("Enter target directory path: ");
        try {
            BufferedReader inReader = new BufferedReader(new InputStreamReader(System.in));
            targetDirectoryPath = inReader.readLine();
        } catch (IOException ioe) {
            ioe.printStackTrace();
            return;
        }

        var testThemeList = TestThemePermutationCreator.CreatePermutations();
        var testThemePermutations = testThemeList.toArray(new TestThemePermutation[0]);
        for (int i = 0; i < testThemePermutations.length; i++) {
            var permutation = testThemePermutations[i];
            var csharpClasses = CreateFromPermutation(permutation);
            for (var csharpClass : csharpClasses) {
                System.out.println(csharpClass.GetClassName());
                SaveTestThemeCsharpClass(csharpClass, targetDirectoryPath);
            }
        }
    }

    private static CsharpTestThemeClass[] CreateFromPermutation(TestThemePermutation permutation){
        var classes = new ArrayList<CsharpTestThemeClass>();
        for (int colorIndex = 0; colorIndex < CsharpTestThemeCreator.GetColorCount(); colorIndex++) {
            var testClass = CsharpTestThemeCreator.GetCsharpTestThemeClass(colorIndex,
                    permutation.IsDark, permutation.ContrastLevel, permutation.SpecVersion, permutation.VariantName);
            classes.add(testClass);
        }
        return classes.toArray(new CsharpTestThemeClass[0]);
    }

    private static void SaveTestThemeCsharpClass(CsharpTestThemeClass csharpClass, String targetDirectoryPath){
        String sanitizedTargetDirectoryPath = targetDirectoryPath.endsWith("\\")
                ? targetDirectoryPath
                : targetDirectoryPath + "\\";
        String directoryName = sanitizedTargetDirectoryPath + csharpClass.GetVariantName() + "\\";
        String fileName = directoryName + csharpClass.GetClassName() + ".cs";

        try {
            Files.createDirectories(Paths.get(directoryName));
            var fileWriter = new FileWriter(fileName);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            writer.write(csharpClass.GetFileContent());
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
