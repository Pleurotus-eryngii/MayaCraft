package eryngii.mayacraft.village;

import java.util.Random;

import cpw.mods.fml.common.registry.VillagerRegistry.IVillageTradeHandler;
import eryngii.mayacraft.MayaCraftCore;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;

public class VillagerMaya  implements IVillageTradeHandler {
	 
		@Override
		public void manipulateTradesForVillager(EntityVillager villager,
				MerchantRecipeList recipeList, Random random) {
			/*
			 * 今回新しく作成した職業用の取引内容を登録します
			 * 既存の職業に取引を追加したりもできます
			 */
			//if(villager.getProfession() == AddTrade.sampleVillagerProfession) {
				/*
				 * エメラルド１個で葉っぱ或いは実x10または粉x5
				 */
				recipeList.add(new MerchantRecipe( new ItemStack(Items.emerald, 1, 0), (new ItemStack(MayaCraftCore.itemCannabisLeaf, 10, 0))));
				recipeList.add(new MerchantRecipe( new ItemStack(Items.emerald, 1, 0), (new ItemStack(MayaCraftCore.itemOpiumPoppy, 10, 0))));
				recipeList.add(new MerchantRecipe( new ItemStack(Items.emerald, 1, 0), (new ItemStack(MayaCraftCore.itemCannabisPowder, 5, 0))));
				recipeList.add(new MerchantRecipe( new ItemStack(Items.emerald, 1, 0), (new ItemStack(MayaCraftCore.itemOpiumPoppyPowder, 5, 0))));
				recipeList.add(new MerchantRecipe( new ItemStack(Items.emerald, 10, 0), (new ItemStack(MayaCraftCore.itemDCS, 1, 0))));
				/*
				 * 1枚で2個
				 */
				recipeList.add(new MerchantRecipe( new ItemStack(MayaCraftCore.itemCannabisLeaf), new ItemStack(Items.emerald, 2, 0)));
				recipeList.add(new MerchantRecipe( new ItemStack(MayaCraftCore.itemOpiumPoppy), new ItemStack(Items.emerald, 2, 0)));
				recipeList.add(new MerchantRecipe( new ItemStack(MayaCraftCore.itemCannabisPowder), new ItemStack(Items.emerald, 2, 0)));
				recipeList.add(new MerchantRecipe( new ItemStack(MayaCraftCore.itemOpiumPoppyPowder), new ItemStack(Items.emerald, 2, 0)));
			//}
		}
}
