package fr.flowsqy.shopchestlanggenerator;

import org.jetbrains.annotations.NotNull;

public class ArgumentsLoader {

    private final static String OUTPUT_FILE_NAME = "output.lang";

    private final String[] args;
    private String in;
    private String out;
    private boolean download;
    private boolean latest;
    private String version;
    private String language;

    public ArgumentsLoader(@NotNull String[] args) {
        this.args = args;
    }

    public boolean check() {
        if (args.length == 0) {
            System.out.println("You need to specify download properties or at least an input file");
            System.out.println("download <latest|<version>> <language> [output]");
            System.out.println("Or");
            System.out.println("<input> [output]");
            System.out.println("Examples:");
            System.out.println("download latest fr_fr");
            System.out.println("download 26.2 en_gb items_en_gb.lang");
            System.out.println("input.json");
            System.out.println("fr_fr.json items_fr_fr.lang");
            return false;
        }
        if (!"download".equals(args[0])) {
            in = args[0];
            out = args.length > 1 ? args[1] : OUTPUT_FILE_NAME;
            return true;
        }
        download = true;
        if (args.length < 3) {
            System.out.println("For download specify a version and a language");
            return false;
        }
        if ("latest".equals(args[1])) {
            latest = true;
        } else {
            version = args[1];
        }
        language = args[2];
        out = args.length > 3 ? args[3] : OUTPUT_FILE_NAME;
        return true;
    }

    public boolean shouldDownload() {
        return download;
    }

    public String[] getArgs() {
        return args;
    }

    public String getIn() {
        return in;
    }

    public String getOut() {
        return out;
    }

    public boolean downloadLatest() {
        return latest;
    }

    public String getVersion() {
        return version;
    }

    public String getLanguage() {
        return language;
    }

}
