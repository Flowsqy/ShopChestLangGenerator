package fr.flowsqy.shopchestlanggenerator;

import org.jetbrains.annotations.NotNull;

public class ArgumentsLoader {

    private final String[] args;

    public ArgumentsLoader(@NotNull String[] args) {
        this.args = args;
    }

    public boolean check() {
        if (args.length == 0 || args.length > 2) {
            System.out.println("You need to specify at least an input file");
            return false;
        }
        return true;
    }

    @NotNull
    public String getIn() {
        return args[0];
    }

    @NotNull
    public String getOut() {
        return args.length == 2 ? args[1] : "output.lang";
    }

}
