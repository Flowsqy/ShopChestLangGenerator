package fr.flowsqy.shopchestlanggenerator;

import org.jetbrains.annotations.NotNull;

import java.io.File;

public class IOChecker {

    private final File inputFile;
    private final File outputFile;

    public IOChecker(@NotNull File inputFile, @NotNull File outputFile) {
        this.inputFile = inputFile;
        this.outputFile = outputFile;
    }

    public boolean check() {
        if (!inputFile.exists()) {
            System.err.println(inputFile.getAbsolutePath() + " does not exist");
            return false;
        }

        if (!inputFile.isFile()) {
            System.err.println(inputFile.getAbsolutePath() + " is not a file");
            return false;
        }

        if (outputFile.exists()) {
            System.err.println(outputFile.getAbsolutePath() + " already exist. Please backup the file or specify another output file");
            return false;
        }

        return true;
    }

}
