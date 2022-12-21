package dev.nincodedo.rhcfabric;

import dev.nincodedo.rhcfabric.event.EventRegister;
import net.fabricmc.api.ModInitializer;

public class RhcFabric implements ModInitializer {

    private static boolean hasThisBeenDone = false;

    public static boolean isHasThisBeenDone() {
        return hasThisBeenDone;
    }

    public static void setHasThisBeenDone(boolean isThisBeenDone) {
        hasThisBeenDone = isThisBeenDone;
    }

    @Override
    public void onInitialize() {
        new EventRegister().registerEvents();
    }
}
