package widecat.meteorcrashaddon.mixins;

/*
Ported from Cornos to Crash Addon by Wide_Cat
https://github.com/0x151/Cornos/blob/master/src/main/java/me/zeroX150/cornos/mixin/packet/LoginHelloC2SPacketMixin.java
 */

import meteordevelopment.meteorclient.systems.modules.Modules;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.packet.c2s.login.LoginHelloC2SPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import widecat.meteorcrashaddon.modules.LoginCrash;

@Mixin(LoginHelloC2SPacket.class)
public abstract class LoginHelloC2SPacketMixin {
    @Inject(method = "write", cancellable = true, at = @At("HEAD"))
    public void gid(PacketByteBuf buf, CallbackInfo ci) {
        if (Modules.get().isActive(LoginCrash.class)) {
            Modules.get().get(LoginCrash.class).toggle();
            buf.writeString(null);
            ci.cancel();
        }
    }
}
