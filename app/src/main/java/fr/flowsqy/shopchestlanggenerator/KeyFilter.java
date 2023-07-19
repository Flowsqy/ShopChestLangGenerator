package fr.flowsqy.shopchestlanggenerator;

import org.jetbrains.annotations.NotNull;

public class KeyFilter {

    public boolean isAllowed(@NotNull String key) {
        if (key.startsWith("block.minecraft.")) {
            return true;
        }
        return key.startsWith("item.minecraft.");
    }

}
