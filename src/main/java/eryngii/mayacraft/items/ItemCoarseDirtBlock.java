package eryngii.mayacraft.items;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;

public class ItemCoarseDirtBlock extends ItemBlockWithMetadata {
	public ItemCoarseDirtBlock(Block block) {
		super(block, block);
	}

	@Override
	public String getUnlocalizedName(ItemStack itemStack) {
		return this.getUnlocalizedName() + "." + itemStack.getItemDamage();
	}
}

