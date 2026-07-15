package fr.flowsqy.shopchestlanggenerator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

import org.jetbrains.annotations.NotNull;

public class FileLangReader implements LangReader {

    private final File inputFile;
    private Reader reader;

    public FileLangReader(@NotNull File inputFile) {
        this.inputFile = inputFile;
    }

    @Override
    @NotNull
    public Reader getReader() {
        if (this.reader != null) {
            return reader;
        }
        try {
            return reader = new BufferedReader(new FileReader(inputFile, StandardCharsets.UTF_8));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() {
        if (reader == null) {
            return;
        }
        try {
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        reader = null;
    }

}
