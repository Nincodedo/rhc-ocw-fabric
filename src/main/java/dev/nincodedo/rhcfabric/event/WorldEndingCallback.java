package dev.nincodedo.rhcfabric.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;

public interface WorldEndingCallback {
    Event<WorldEndingCallback> EVENT = EventFactory.createArrayBacked(WorldEndingCallback.class,
            listeners -> player -> {
        for (WorldEndingCallback listener : listeners) {
            ActionResult result = listener.worldEndedBy(player);

            if (result != ActionResult.PASS) {
                return result;
            }
        }
        return ActionResult.PASS;
    });

    ActionResult worldEndedBy(PlayerEntity player);
}
