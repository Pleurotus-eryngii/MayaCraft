package eryngii.mayacraft.items;

import net.minecraft.item.ItemFood;

public class ItemHakataSalt extends ItemFood{

	public ItemHakataSalt(int par1, float par2, boolean par3) {
		super(par1 ,par2 ,par3);
		this.setAlwaysEdible();	//お腹すいてなくても食べれる。

	}

}
