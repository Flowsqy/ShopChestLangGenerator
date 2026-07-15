package fr.flowsqy.shopchestlanggenerator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class DownloadLangReader implements LangReader {

    private final boolean downloadLatest;
    private final String version;
    private final String language;
    private Reader reader;

    public DownloadLangReader(boolean downloadLatest, @Nullable String version, @NotNull String language) {
        this.downloadLatest = downloadLatest;
        this.version = version;
        this.language = language;
    }

    @Override
    @NotNull
    public Reader getReader() {
        try {
            final URL manifestUrl = new URL("https://piston-meta.mojang.com/mc/game/version_manifest.json");
            final InputStream manifestStream = manifestUrl.openStream();
            final JsonElement manifestElement = JsonParser
                    .parseReader(new BufferedReader(new InputStreamReader(manifestStream)));
            manifestStream.close();
            final String version;
            if (downloadLatest) {
                version = manifestElement.getAsJsonObject().getAsJsonObject("latest").get("release").getAsString();
            } else {
                version = this.version;
            }
            JsonElement latestVersionElement = null;
            for (final JsonElement versionElement : manifestElement.getAsJsonObject().get("versions")
                    .getAsJsonArray()) {
                if (versionElement.getAsJsonObject().get("id").getAsString().equals(version)) {
                    latestVersionElement = versionElement;
                    break;
                }
            }
            if (latestVersionElement == null) {
                System.err.println("Can't find the json manifest for version: " + version);
                System.exit(1);
            }
            final URL versionUrl = new URL(latestVersionElement.getAsJsonObject().get("url").getAsString());
            final InputStream versionStream = versionUrl.openStream();
            final JsonElement versionElement = JsonParser
                    .parseReader(new BufferedReader(new InputStreamReader(versionStream)));
            versionStream.close();
            final URL assetsURL = new URL(versionElement.getAsJsonObject().getAsJsonObject("assetIndex").get("url").getAsString());
            final InputStream assetsStream = assetsURL.openStream();
            final JsonElement assetsElement = JsonParser.parseReader(new BufferedReader(new InputStreamReader(assetsStream)));
            assetsStream.close();
            JsonObject languageElement = assetsElement.getAsJsonObject().getAsJsonObject("objects").getAsJsonObject("minecraft/lang/" + language + ".json");
            if (languageElement == null) {
                System.err.println("Can't find the selected language: " + language);
                System.exit(1);
            }
            final String languageFileHash = languageElement.get("hash").getAsString();
            final URL languageUrl = new URL("https://resources.download.minecraft.net/"
                    + languageFileHash.substring(0, 2) + "/" + languageFileHash);
            final InputStream languageStream = languageUrl.openStream();
            return reader = new BufferedReader(new InputStreamReader(languageStream));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() {
        try {
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        reader = null;
    }

}
