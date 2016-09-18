package eryngii.mayacraft.items;

import net.minecraft.item.ItemFood;
import net.minecraft.potion.Potion;

public class ItemPoison extends ItemFood{

	public ItemPoison(int par1, float par2, boolean par3) {
		super(par1 ,par2 ,par3);
		this.setAlwaysEdible();	//お腹すいてなくても食べれる。
		this.setPotionEffect(Potion.poison.id, 2000, 3,1.0F );

	}
	
}
