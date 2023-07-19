package fr.flowsqy.shopchestlanggenerator;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.File;
import java.util.Objects;

public class App {

    public static void main(String[] args) {
        final ArgumentsLoader argumentsLoader = new ArgumentsLoader(args);
        if (!argumentsLoader.check()) {
            return;
        }

        final File inputFile = new File(argumentsLoader.getIn());
        final File outputFile = new File(argumentsLoader.getOut());
        final IOChecker ioChecker = new IOChecker(inputFile, outputFile);
        if (!ioChecker.check()) {
            return;
        }

        final JsonExtractor extractor = new JsonExtractor(inputFile);
        final JsonElement jsonElement = extractor.extract();

        final JsonValidator jsonValidator = new JsonValidator();
        if (!jsonValidator.validate(jsonElement)) {
            return;
        }
        final JsonObject jsonObject = Objects.requireNonNull(jsonElement).getAsJsonObject();

        final LangWriter langWriter = new LangWriter(new KeyFilter());
        langWriter.write(jsonObject, outputFile);
    }

}
