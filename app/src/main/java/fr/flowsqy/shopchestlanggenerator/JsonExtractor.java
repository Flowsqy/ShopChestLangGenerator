package fr.flowsqy.shopchestlanggenerator;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class JsonExtractor {

    private final File inputFile;

    public JsonExtractor(@NotNull File inputFile) {
        this.inputFile = inputFile;
    }

    @Nullable
    public JsonElement extract() {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            return JsonParser.parseReader(reader);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
