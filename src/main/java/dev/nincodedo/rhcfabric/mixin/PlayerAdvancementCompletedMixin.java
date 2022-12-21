package dev.nincodedo.rhcfabric.mixin;

import com.mojang.logging.LogUtils;
import dev.nincodedo.rhcfabric.RhcFabric;
import dev.nincodedo.rhcfabric.event.WorldEndingCallback;
import net.minecraft.advancement.PlayerAdvancementTracker;
import net.minecraft.advancement.criterion.AbstractCriterion;
import net.minecraft.advancement.criterion.Criterion;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractCriterion.class)
public class PlayerAdvancementCompletedMixin {

    private static final Logger LOGGER = LogUtils.getLogger();

    @Inject(method = "endTrackingCondition", at = @At(value = "INVOKE",
            target = "Ljava/util/Set;remove(Ljava/lang/Object;)Z"))
    protected void injected(PlayerAdvancementTracker manager, Criterion.ConditionsContainer conditions, CallbackInfo ci) {
        var advancementOwner = ((PlayerAdvancementTrackerAccessor) manager).getOwner();
        var advancement = ((ConditionsContainerAccessor) conditions).getAdvancement();
        var progress = manager.getProgress(advancement);
        if ("nincodedo:you_did_this".equals(advancement.getId().toString()) && progress.isDone() && !RhcFabric.isHasThisBeenDone()) {
            RhcFabric.setHasThisBeenDone(true);
            LOGGER.trace("World is over thanks to {}", advancementOwner);
            WorldEndingCallback.EVENT.invoker().worldEndedBy(advancementOwner);
        }
    }
}
