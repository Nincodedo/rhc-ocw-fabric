package dev.nincodedo.rhcfabric.event;

import com.mojang.logging.LogUtils;
import net.minecraft.util.ActionResult;
import org.slf4j.Logger;

public class EventRegister {

    private static final Logger LOGGER = LogUtils.getLogger();

    public void registerEvents() {
        WorldEndingCallback.EVENT.register(player -> {
            LOGGER.info("YOU DID THIS {}", player);
            return ActionResult.PASS;
        });
    }
}
