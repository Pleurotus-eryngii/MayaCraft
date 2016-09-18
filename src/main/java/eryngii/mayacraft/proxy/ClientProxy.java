package eryngii.mayacraft.proxy;

import cpw.mods.fml.common.registry.VillagerRegistry;
import eryngii.mayacraft.MayaCraftCore;
import net.minecraft.util.ResourceLocation;

public class ClientProxy  extends CommonProxy {
    public void init() {
	/*
	 * 村人のテクスチャを指定しています
	 */
        VillagerRegistry.instance().registerVillagerSkin(MayaCraftCore.mayaVillagerProfession, new ResourceLocation("mayacraft", "textures/mobs/vill4.png"));
    }
}
