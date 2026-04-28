package com.mythicmetals.component;

import com.mojang.serialization.Codec;
import com.mythicmetals.misc.RegistryHelper;
import io.wispforest.owo.serialization.CodecUtils;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.network.codec.ByteBufCodecs;

public class MythicDataComponents {
    public static final DataComponentType<GoldFoldedComponent> GOLD_FOLDED = RegistryHelper.dataComponentType(
        "gold_folded", builder -> builder
            .codec(CodecUtils.toCodec(GoldFoldedComponent.ENDEC))
            .packetCodec(CodecUtils.toPacketCodec(GoldFoldedComponent.ENDEC))
    );
    @Deprecated(forRemoval = true, since = "0.23.0")
    public static final DataComponentType<Boolean> LOCKED = RegistryHelper.dataComponentType(
        "locked", builder -> builder
            .codec(Codec.BOOL)
            .packetCodec(ByteBufCodecs.BOOL)
    );
    public static final DataComponentType<Boolean> WAS_USED = RegistryHelper.dataComponentType(
        "was_used", builder ->
            builder.codec(Codec.BOOL)
                .packetCodec(ByteBufCodecs.BOOL)
    );
    public static final DataComponentType<CarmotStaffComponent> CARMOT_STAFF_BLOCK = RegistryHelper.dataComponentType(
        "carmot_staff_block", builder -> builder
            .codec(CodecUtils.toCodec(CarmotStaffComponent.ENDEC))
            .packetCodec(CodecUtils.toPacketCodec(CarmotStaffComponent.ENDEC))
    );
    public static final DataComponentType<TidesingerPatternComponent> TIDESINGER = RegistryHelper.dataComponentType(
        "tidesinger", builder -> builder
            .codec(CodecUtils.toCodec(TidesingerPatternComponent.ENDEC))
            .packetCodec(CodecUtils.toPacketCodec(TidesingerPatternComponent.ENDEC))
    );
    public static final DataComponentType<DrillComponent> DRILL = RegistryHelper.dataComponentType(
        "drill", builder -> builder
            .codec(CodecUtils.toCodec(DrillComponent.ENDEC))
            .packetCodec(CodecUtils.toPacketCodec(DrillComponent.ENDEC))
    );
    public static final DataComponentType<UpgradeComponent> UPGRADES = RegistryHelper.dataComponentType(
        "upgrades", builder -> builder
            .codec(CodecUtils.toCodec(UpgradeComponent.ENDEC))
            .packetCodec(CodecUtils.toPacketCodec(UpgradeComponent.ENDEC))
    );
    public static final DataComponentType<PrometheumComponent> PROMETHEUM = RegistryHelper.dataComponentType(
        "prometheum", builder -> builder
            .codec(CodecUtils.toCodec(PrometheumComponent.ENDEC))
            .packetCodec(CodecUtils.toPacketCodec(PrometheumComponent.ENDEC))
    );

    public static void init() {
    }
}
