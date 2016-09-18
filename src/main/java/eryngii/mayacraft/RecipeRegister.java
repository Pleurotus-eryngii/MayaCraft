package eryngii.mayacraft;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class RecipeRegister {
	public static void init(){
		GameRegistry.addShapelessRecipe(new ItemStack(MayaCraftCore.itemCannabisLeaf,4),
				new ItemStack(MayaCraftCore.itemCannabisPlant,1)
				);
		     
		
		GameRegistry.addShapelessRecipe(new ItemStack(MayaCraftCore.itemCannabisPlant,9),
				new ItemStack(MayaCraftCore.blockCannabisContainer,1,0)
				);
		
		GameRegistry.addRecipe(new ItemStack(MayaCraftCore.blockCannabis),
		    	"##",
		    	"##",
	             '#',new ItemStack(Items.wheat_seeds)
				);
		     
		GameRegistry.addRecipe(new ItemStack(MayaCraftCore.blockCannabis,3),
				"@@",
				"@@",
				'@', new ItemStack(MayaCraftCore.itemCannabisPlant,1)
				);
		
		GameRegistry.addRecipe(new ItemStack(MayaCraftCore.blockFreshCannabis,1),
				"@@",
				"@@",
				'@', new ItemStack(MayaCraftCore.itemCannabisLeaf,1)
				);
		
		
		GameRegistry.addRecipe(new ItemStack(MayaCraftCore.blockCannabisContainer,3,0),
				"@@@",
				"@@@",
				"@@@",
				'@', new ItemStack(MayaCraftCore.itemCannabisPlant,1)
				);
		     
		
		 GameRegistry.addSmelting(MayaCraftCore.itemDryCannabisLeaf,new ItemStack(MayaCraftCore.itemCannabisPowder),0.1f);
			

		GameRegistry.addShapelessRecipe(new ItemStack(MayaCraftCore.itemOpiumPoppy,4),
				new ItemStack(MayaCraftCore.itemOpiumPoppyPlant,1)
				);
		     
		
		GameRegistry.addShapelessRecipe(new ItemStack(MayaCraftCore.itemOpiumPoppyPlant,9),
				new ItemStack(MayaCraftCore.blockCannabisContainer,1,1)
				);
		
		
		GameRegistry.addRecipe(new ItemStack(MayaCraftCore.blockOpiumPoppy),
				"#",
				"#",
				'#',new ItemStack(Items.wheat_seeds)
				);
		    	
		GameRegistry.addRecipe(new ItemStack(MayaCraftCore.blockOpiumPoppy,3),
				"@@",
				"@@",
				'@', new ItemStack(MayaCraftCore.itemOpiumPoppyPlant,1)
				);
		
		GameRegistry.addRecipe(new ItemStack(MayaCraftCore.blockFreshOpiumPoppy,1),
				"@@",
				"@@",
				'@', new ItemStack(MayaCraftCore.itemOpiumPoppy,1)
				);
		
		GameRegistry.addRecipe(new ItemStack(MayaCraftCore.blockCannabisContainer,3,1),
				"@@@",
				"@@@",
				"@@@",
				'@', new ItemStack(MayaCraftCore.itemOpiumPoppyPlant,1)
				);
		
		GameRegistry.addShapelessRecipe(new ItemStack(MayaCraftCore.itemAnimalSomething,1),
				Items.beef,
				Items.porkchop,
				Items.chicken,
				Items.rotten_flesh,
				Items.fish,
				Items.cooked_fished,
				Items.cooked_beef,
				Items.cooked_porkchop,
				Items.cooked_chicken
				);
		
		GameRegistry.addShapelessRecipe(new ItemStack(MayaCraftCore.itemVegetableMix,1),
				Items.carrot,
				Items.wheat,
				Items.potato,
				Items.melon,
				Items.poisonous_potato,
				Blocks.pumpkin,
				(new ItemStack(Items.dye,1,3)),
				Blocks.red_mushroom,
				Blocks.brown_mushroom
				);
		
		GameRegistry.addShapelessRecipe(new ItemStack(MayaCraftCore.itemVegetableMix,1),
				Items.spider_eye,
				Items.poisonous_potato,
				Items.rotten_flesh,
				(new ItemStack(Items.potionitem,1,8228)),
				(new ItemStack(Items.potionitem,2,16420)),
				(new ItemStack(Items.potionitem,3,8196))
				);
		
		GameRegistry.addShapelessRecipe(new ItemStack(MayaCraftCore.itemOpiumPoppyPlant,9),
				new ItemStack(MayaCraftCore.blockCannabisContainer,1,1)
				);
		
		GameRegistry.addRecipe(new ItemStack(MayaCraftCore.itemCrocodile,1),
				"@@@",
				"#$@",
				"###",
				'@', new ItemStack(MayaCraftCore.itemCannabisPowder,1),
				'#', new ItemStack(MayaCraftCore.itemOpiumPoppyPowder,1),
				'$', new ItemStack(MayaCraftCore.itemCannabisLeaf,1)
				
				);

		GameRegistry.addShapelessRecipe(new ItemStack(MayaCraftCore.itemHakataSalt,1),
				(new ItemStack(Items.sugar,9))
				);
		
		GameRegistry.addRecipe(new ItemStack(MayaCraftCore.itemEnderJewel,1),
				"#@#",
				"@$@",
				"#@#",
				'@', new ItemStack(Blocks.end_stone,1),
				'#', new ItemStack(Items.ender_pearl,1),
				'$', new ItemStack(Items.ender_eye,1)
				
				);
		
		GameRegistry.addRecipe(new ItemStack(MayaCraftCore.itemDCS,1),
				"#@$",
				"*&%",
				"?+?",
				'#', new ItemStack(MayaCraftCore.itemAnimalSomething,1),
				'@', new ItemStack(MayaCraftCore.itemVegetableMix,1),
				'$', new ItemStack(MayaCraftCore.itemPoison,1),
				'*', new ItemStack(MayaCraftCore.itemCrocodile,1),
				'&', new ItemStack(MayaCraftCore.itemHakataSalt,1),
				'%', new ItemStack(MayaCraftCore.itemEnderJewel,1),
				'?', new ItemStack(Items.water_bucket,1),
				'+', new ItemStack(Items.bowl,1)
				
				);
		 GameRegistry.addSmelting(MayaCraftCore.itemDryOpiumPoppy,new ItemStack(MayaCraftCore.itemOpiumPoppyPowder),0.1f);
	}
}
