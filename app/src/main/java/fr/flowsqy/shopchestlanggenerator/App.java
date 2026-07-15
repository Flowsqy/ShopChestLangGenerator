package fr.flowsqy.shopchestlanggenerator;

import java.io.File;
import java.util.Objects;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class App {

    public static void main(String[] args) {
        final ArgumentsLoader argumentsLoader = new ArgumentsLoader(args);
        if (!argumentsLoader.check()) {
            return;
        }

        final File inputFile = argumentsLoader.shouldDownload() ? null : new File(argumentsLoader.getIn());
        final File outputFile = new File(argumentsLoader.getOut());
        final IOChecker ioChecker = new IOChecker(inputFile, outputFile);
        if (!ioChecker.check()) {
            return;
        }

        final LangReader langReader = argumentsLoader.shouldDownload()
                ? new DownloadLangReader(argumentsLoader.downloadLatest(), argumentsLoader.getVersion(),
                        argumentsLoader.getLanguage())
                : new FileLangReader(inputFile);

        final JsonExtractor extractor = new JsonExtractor(langReader);
        final JsonElement jsonElement = extractor.extract();
        langReader.close();

        final JsonValidator jsonValidator = new JsonValidator();
        if (!jsonValidator.validate(jsonElement)) {
            return;
        }
        final JsonObject jsonObject = Objects.requireNonNull(jsonElement).getAsJsonObject();

        final LangWriter langWriter = new LangWriter(new KeyFilter());
        langWriter.write(jsonObject, outputFile);
    }

}
