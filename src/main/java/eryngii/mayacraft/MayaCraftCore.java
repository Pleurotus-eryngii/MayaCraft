package eryngii.mayacraft;

import java.awt.Color;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.VillagerRegistry;
import cpw.mods.fml.relauncher.Side;
import eryngii.mayacraft.achievements.MayaAchievements;
import eryngii.mayacraft.blocks.CannabisBlock;
import eryngii.mayacraft.blocks.CannabisContainerBlock;
import eryngii.mayacraft.blocks.FreshCannabisBlock;
import eryngii.mayacraft.blocks.FreshOpiumPoppyBlock;
import eryngii.mayacraft.blocks.OPoppyBlock;
import eryngii.mayacraft.items.ItemAnimalSomething;
import eryngii.mayacraft.items.ItemBonsaiManEgg;
import eryngii.mayacraft.items.ItemCannabisContainerBlock;
import eryngii.mayacraft.items.ItemCannabisLeaf;
import eryngii.mayacraft.items.ItemCannabisPowder;
import eryngii.mayacraft.items.ItemCrocodile;
import eryngii.mayacraft.items.ItemDCS;
import eryngii.mayacraft.items.ItemDryCannabisLeaf;
import eryngii.mayacraft.items.ItemDryOPoppy;
import eryngii.mayacraft.items.ItemHakataSalt;
import eryngii.mayacraft.items.ItemOPoppy;
import eryngii.mayacraft.items.ItemOPoppyPowder;
import eryngii.mayacraft.items.ItemPoison;
import eryngii.mayacraft.items.ItemSuperBonsaiManEgg;
import eryngii.mayacraft.items.ItemVegetableMix;
import eryngii.mayacraft.mobs.EntityBonsaiMan;
import eryngii.mayacraft.mobs.EntitySuperBonsaiMan;
import eryngii.mayacraft.mobs.RenderBonsaiMan;
import eryngii.mayacraft.proxy.ClientProxy;
import eryngii.mayacraft.proxy.CommonProxy;
import eryngii.mayacraft.village.ComponentVillageMayaHouse;
import eryngii.mayacraft.village.VillageCreationHandleMayaHouse;
import eryngii.mayacraft.village.VillagerMaya;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.structure.MapGenStructureIO;

@Mod(modid="mayacraftmod", name="MayaCraft", version="1.0")
public class MayaCraftCore {
	 public static final String resourceDomain = "mayacraft:";
	 
	  public static Block blockCannabis;//苗
	  public static Item itemCannabisPlant;//全体
	  public static Item itemCannabisLeaf;//葉
	  public static Item itemDryCannabisLeaf;//乾燥物
	  public static Item itemCannabisPowder;//粉
	  public static Block blockOpiumPoppy;//苗
	  public static Item itemOpiumPoppyPlant;//全体
	  public static Item itemOpiumPoppy;//使用部分
	  public static Item itemDryOpiumPoppy;//乾燥物
	  public static Item itemOpiumPoppyPowder;//粉
	  public static Block blockFreshCannabis;//乾燥前
	  public static Block blockFreshOpiumPoppy;//乾燥前
	  public static Block blockCannabisContainer;//圧縮ブロック.2つをメタデータで処理
	  
	  
	  public static Item itemAnimalSomething;
	  public static Item itemVegetableMix;
	  public static Item itemCrocodile;
	  public static Item itemHakataSalt;
	  public static Item itemPoison;
	  public static Item itemEnderJewel;
	  public static Item itemDCS;
	  

	  public static Item bonsaiManEgg;//盆栽マンのスポーンエッグ。デバッグ用に近いが一応残す
	  public static Item superBonsaiManEgg;
	  
	  public boolean Flag=false;//1回だけ実行する判定用。複数薬物使用対策で一応個別に分ける
	  public boolean Flag2=false;//1回だけ実行する判定用
	  public boolean Flag3=false;//1回だけ実行する判定用
	  public boolean Flag4=false;//1回だけ実行する判定用
	  public boolean Flag5=false;//1回だけ実行する判定用
	  public boolean Flag6=false;//1回だけ実行する判定用
	  
	  //クリエイティブタブ
	  public static final CreativeTabs tabsMaya = new CreativeTabMaya("Maya");
	  //村人。
	  public static VillagerMaya villagerMaya;
	  public static int mayaVillagerProfession = 114514;
	  
	  //プロキシ。Wikiでの登録手法では上手くいかなかったためきっちり登録した
	  @SidedProxy(clientSide = "eryngii.mayacraft.proxy.ClientProxy", 
						  serverSide = "eryngii.mayacraft.proxy.CommonProxy")
	  public static CommonProxy proxy;
	  public static ClientProxy clientproxy;

	  @Mod.EventHandler
	  public void preInit(FMLPreInitializationEvent event)
	{
		blockCannabis = new CannabisBlock();
		GameRegistry.registerBlock(blockCannabis, "blockCannabis");
		
		itemCannabisPlant = new Item()
				.setCreativeTab(tabsMaya)/*クリエイティブのタブ*/
				.setUnlocalizedName("itemCannabisPlant")/*システム名の登録*/
				.setTextureName("mayacraft:cannabis_plant");/*テクスチャの指定*/
		GameRegistry.registerItem(itemCannabisPlant, "itemCannabisPlant");
		
		//引数は左から満腹度回復量、腹持ち、オオカミに食べさせられるかどうか
		itemCannabisLeaf = (new ItemCannabisLeaf(1, 1.0F, false))
				.setCreativeTab(tabsMaya)/*クリエイティブのタブ*/
				.setUnlocalizedName("itemCannabisLeaf")/*システム名の登録*/
				.setTextureName("mayacraft:cannabis_leaf");/*テクスチャの指定*/
		GameRegistry.registerItem(itemCannabisLeaf, "itemCannabisLeaf");
		
		itemDryCannabisLeaf = (new ItemDryCannabisLeaf(1, 1.0F, false))
				.setCreativeTab(tabsMaya)/*クリエイティブのタブ*/
				.setUnlocalizedName("itemDryCannabisLeaf")/*システム名の登録*/
				.setTextureName("mayacraft:dry_cannabis_leaf");/*テクスチャの指定*/
		GameRegistry.registerItem(itemDryCannabisLeaf, "itemDryCannabisLeaf");
		
		itemCannabisPowder = (new ItemCannabisPowder(1, 1.0F, false))
				.setCreativeTab(tabsMaya)/*クリエイティブのタブ*/
				.setUnlocalizedName("itemCannabisPowder")/*システム名の登録*/
				.setTextureName("mayacraft:cannabis_powder")/*テクスチャの指定*/;
		GameRegistry.registerItem(itemCannabisPowder, "itemCannabisPowder");
		
		
		blockOpiumPoppy = new OPoppyBlock();
		GameRegistry.registerBlock(blockOpiumPoppy, "blockOpiumPoppy");
		
		itemOpiumPoppyPlant = new Item()
				.setCreativeTab(tabsMaya)/*クリエイティブのタブ*/
				.setUnlocalizedName("itemOpiumPoppyPlant")/*システム名の登録*/
				.setTextureName("mayacraft:opoppy_plant");/*テクスチャの指定*/
		GameRegistry.registerItem(itemOpiumPoppyPlant, "itemOpiumPoppyPlant");
		
		itemOpiumPoppy = (new ItemOPoppy(1, 1.0F, false))
				.setCreativeTab(tabsMaya)/*クリエイティブのタブ*/
				.setUnlocalizedName("itemOpiumPoppy")/*システム名の登録*/
				.setTextureName("mayacraft:opoppy");/*テクスチャの指定*/
		GameRegistry.registerItem(itemOpiumPoppy, "itemOpiumPoppy");
		
		itemDryOpiumPoppy = (new ItemDryOPoppy(1, 1.0F, false))
				.setCreativeTab(tabsMaya)/*クリエイティブのタブ*/
				.setUnlocalizedName("itemDryOpiumPoppy")/*システム名の登録*/
				.setTextureName("mayacraft:dry_opoppy");/*テクスチャの指定*/
		GameRegistry.registerItem(itemDryOpiumPoppy, "itemDryOpiumPoppy");
		
		itemOpiumPoppyPowder = (new ItemOPoppyPowder(1, 1.0F, false))
				.setCreativeTab(tabsMaya)/*クリエイティブのタブ*/
				.setUnlocalizedName("itemOpiumPoppyPowder")/*システム名の登録*/
				.setTextureName("mayacraft:opoppy_powder");/*テクスチャの指定*/
		GameRegistry.registerItem(itemOpiumPoppyPowder, "itemOpiumPoppyPowder");
		
		itemAnimalSomething = (new ItemAnimalSomething(1, 1.0F, false))
				.setCreativeTab(tabsMaya)/*クリエイティブのタブ*/
				.setUnlocalizedName("itemAnimalSomething")/*システム名の登録*/
				.setTextureName("mayacraft:animal_something");/*テクスチャの指定*/
		GameRegistry.registerItem(itemAnimalSomething, "itemAnimalSomething");
		
		itemVegetableMix = (new ItemVegetableMix(1, 1.0F, false))
				.setCreativeTab(tabsMaya)/*クリエイティブのタブ*/
				.setUnlocalizedName("itemVegetableMix")/*システム名の登録*/
				.setTextureName("mayacraft:vegetable_mix");/*テクスチャの指定*/
		GameRegistry.registerItem(itemVegetableMix, "itemVegetableMix");
		
		itemCrocodile = (new ItemCrocodile(1, 1.0F, false))
				.setCreativeTab(tabsMaya)/*クリエイティブのタブ*/
				.setUnlocalizedName("itemCrocodile")/*システム名の登録*/
				.setTextureName("mayacraft:crocodile");/*テクスチャの指定*/
		GameRegistry.registerItem(itemCrocodile, "itemCrocodile");
		
		itemHakataSalt = (new ItemHakataSalt(1, 1.0F, false))
				.setCreativeTab(tabsMaya)/*クリエイティブのタブ*/
				.setUnlocalizedName("itemHakataSalt")/*システム名の登録*/
				.setTextureName("mayacraft:hakata_salt");/*テクスチャの指定*/
		GameRegistry.registerItem(itemHakataSalt, "itemHakataSalt");
		
		itemPoison = (new ItemPoison(1, 1.0F, false))
				.setCreativeTab(tabsMaya)/*クリエイティブのタブ*/
				.setUnlocalizedName("itemPoison")/*システム名の登録*/
				.setTextureName("mayacraft:poison");/*テクスチャの指定*/
		GameRegistry.registerItem(itemPoison, "itemPoison");
		
		itemEnderJewel = new Item()
				.setCreativeTab(tabsMaya)/*クリエイティブのタブ*/
				.setUnlocalizedName("itemEnderJewel")/*システム名の登録*/
				.setTextureName("mayacraft:ender_jewel");/*テクスチャの指定*/
		GameRegistry.registerItem(itemEnderJewel, "itemEnderJewel");
				
		itemDCS = (new ItemDCS(1, 1.0F, false))
				.setCreativeTab(tabsMaya)/*クリエイティブのタブ*/
				.setUnlocalizedName("itemDCS")/*システム名の登録*/
				.setTextureName("mayacraft:dcs");/*テクスチャの指定*/
		GameRegistry.registerItem(itemDCS, "itemDCS");
		
		blockCannabisContainer = new CannabisContainerBlock();
		GameRegistry.registerBlock(blockCannabisContainer, ItemCannabisContainerBlock.class, "blockCannabisContainer");
		
		
		blockFreshCannabis = new FreshCannabisBlock();
		GameRegistry.registerBlock(blockFreshCannabis, "blockFreshCannabis");
		
		blockFreshOpiumPoppy = new FreshOpiumPoppyBlock();
		GameRegistry.registerBlock(blockFreshOpiumPoppy, "blockFreshOpiumPoppy");
		
		
		bonsaiManEgg = new ItemBonsaiManEgg(Color.BLACK.getRGB(), Color.GREEN.getRGB())
				//クリエイティブタブの登録
				.setCreativeTab(MayaCraftCore.tabsMaya)
				//システム名の登録
				.setUnlocalizedName("BonsaimanEgg")
				//テクスチャ名の登録
				.setTextureName("spawn_egg");
				//GameRegistryへの登録
				GameRegistry.registerItem(bonsaiManEgg, "bonsaiManEgg");
				
		superBonsaiManEgg = new ItemSuperBonsaiManEgg(Color.BLACK.getRGB(), Color.RED.getRGB())
				//クリエイティブタブの登録
				.setCreativeTab(MayaCraftCore.tabsMaya)
				//システム名の登録
				.setUnlocalizedName("SuperBonsaimanEgg")
				//テクスチャ名の登録
				.setTextureName("spawn_egg");
				//GameRegistryへの登録
				GameRegistry.registerItem(superBonsaiManEgg, "SuperBonsaiManEgg");
				
		//実績の登録
		MayaAchievements.initAchievements();
	}
	  @Mod.EventHandler
	  public void init(FMLInitializationEvent event){
			/*
			 * Eventの登録
			 */
			FMLCommonHandler.instance().bus().register(this);
			
		  //盆栽マンの登録。スポーン地点を大量に登録しているが動くかどうかは不明
		    EntityRegistry.registerModEntity(EntityBonsaiMan.class, "BonsaimanEntity", 0, this, 250, 1, false);
		    EntityRegistry.registerModEntity(EntitySuperBonsaiMan.class, "SuperBonsaimanEntity", 1, this, 250, 1, false);

	        EntityRegistry.addSpawn(EntityBonsaiMan.class, 20, 1, 4, EnumCreatureType.creature, BiomeGenBase.plains);
	        EntityRegistry.addSpawn(EntityBonsaiMan.class, 20, 1, 4, EnumCreatureType.creature, BiomeGenBase.forest);
	        EntityRegistry.addSpawn(EntityBonsaiMan.class, 20, 1, 4, EnumCreatureType.creature, BiomeGenBase.beach);
	        EntityRegistry.addSpawn(EntityBonsaiMan.class, 20, 1, 4, EnumCreatureType.creature, BiomeGenBase.desert);
	        EntityRegistry.addSpawn(EntitySuperBonsaiMan.class, 20, 1, 4, EnumCreatureType.creature, BiomeGenBase.savanna);
	        //Render設定
	        if(FMLCommonHandler.instance().getSide() == Side.CLIENT) {
			RenderingRegistry.registerEntityRenderingHandler(EntityBonsaiMan.class, new RenderBonsaiMan());
			RenderingRegistry.registerEntityRenderingHandler(EntitySuperBonsaiMan.class, new RenderBonsaiMan());
	        }

		  villagerMaya = new VillagerMaya();
		  
			/*
			 * 村人の職業IDを登録しています
			 */
	                VillagerRegistry.instance().registerVillagerId(mayaVillagerProfession);
	 
			/*
			 * 村人を登録しています
			 */
			VillagerRegistry.instance().registerVillageTradeHandler(mayaVillagerProfession, villagerMaya);
	 
			/*
			 * 村人の家を登録しています
			 */
			VillagerRegistry.instance().registerVillageCreationHandler(new VillageCreationHandleMayaHouse());
	                /*
	                 * #Forge1.7.10-10.13.0.1230現在
	                 * 通常は、registerStructureで建物のStructureStartを継承したクラスを登録した後
	                 * func_143031_aで建物のStructureComponentを継承したクラスを登録する必要がありますが
	                 * 今回は、両方でComponentVillageSampleHouseを登録しています
	                 */
	                MapGenStructureIO.registerStructure(ComponentVillageMayaHouse.class, "VCHSH");
	                MapGenStructureIO.func_143031_a(ComponentVillageMayaHouse.class, "VCHSHP");
	 
	               proxy.init();
	                

	    		  	//レシピ登録
	    		  RecipeRegister.init();

	    	        }
		@SubscribeEvent
		public void onPlayerTick(TickEvent.PlayerTickEvent event)
		{
			/*
			 * ここにPlayerに関するtick処理を書く.
			 * event.playerでEntityPlayerのインスタンスを取得できる.
			 */
			//フラッシュバックの設定に用いる
			long nowtime = System.currentTimeMillis();//現在時間を取得
	    	long calculation = (nowtime - ItemCannabisLeaf.depstartedtime)/1000L; //現在時間 - 使用時にセットした時間を秒単位に修正
	    	
	    	if(!Flag){
	    	if(ItemCannabisLeaf.depStart=true){//保険。意味はないと思うが、誤作動の防止目的
	    	if(calculation == 900)//900だった場合(=15分)
  		{
	    		
	    		//Worldのインスタンス取得。
	    		World world = Minecraft.getMinecraft().theWorld;

	    		//以下、吐き気のポーション。設定の意味はItemCannabisLeaf他参照
	    		int potionID = Potion.confusion.id;
	    		int duration = 120 * 20;
	    		int amplifier = 2;

	    		PotionEffect Effect = new PotionEffect(potionID, duration, amplifier);
	    		
	    		//効果付与
	    		event.player.addPotionEffect(Effect);
	             
	    		world.playSoundAtEntity(event.player, "mayacraft:cannabisbgm", 1.0F, 1.0F);
	             
	    		
	    		//一度だけ実行させるためのフラグ。Falseのときのみ実行されるため、2度目以降は実行されない
	    		Flag=true;
	    			}
	    		}
	    	
	    	if(calculation == 903){
	    		Flag= false;
	    		long i = System.currentTimeMillis();
	       	    ItemCannabisLeaf.depstartedtime = i;//フラッシュバック用。使用時の時間を取得して代入
	    	}
	    	//3秒ほど後にまたFlagをfalseにして、フラッシュバックが何度でも起こるようにしている。
	    	//なお、ここで時間もリセットされるため使用しない限り何度でも起こる
	    	}
	    	
	    //-------------乾燥葉---------------
	    	long calculation2 = (nowtime - ItemDryCannabisLeaf.depstartedtime)/1000L; //現在時間 - 使用時にセットした時間を秒単位に修正
  	
	    	if(!Flag2){
	    	if(ItemDryCannabisLeaf.depStart=true){//保険。意味はないと思うが、誤作動の防止目的
	    	if(calculation2 == 900)//900だった場合(=15分)
	    	{
  		
	    		//Worldのインスタンス取得。
	    		World world = Minecraft.getMinecraft().theWorld;

	    		//以下、吐き気のポーション。設定の意味はItemCannabisLeaf他参照
	    		int potionID = Potion.confusion.id;
	    		int duration = 120 * 20;
	    		int amplifier = 2;

	    		PotionEffect Effect = new PotionEffect(potionID, duration, amplifier);
  		
	    		//効果付与
	    		event.player.addPotionEffect(Effect);
           
	    		world.playSoundAtEntity(event.player, "mayacraft:cannabisbgm", 1.0F, 1.0F);
           
  		
	    		//一度だけ実行させるためのフラグ。Falseのときのみ実行されるため、2度目以降は実行されない
	    		Flag2=true;
  			}
  			}
	    	}
	    	if(calculation2 == 903){
	    		Flag2= false;
	    		long i = System.currentTimeMillis();
	       	    ItemCannabisLeaf.depstartedtime = i;//フラッシュバック用。使用時の時間を取得して代入	
	    	}
	    	//3秒ほど後にまたFlagをfalseにして、フラッシュバックが何度でも起こるようにしている。
  		
		   //-------------粉---------------
  	long calculation3 = (nowtime - ItemCannabisPowder.depstartedtime)/1000L; //現在時間 - 使用時にセットした時間を秒単位に修正
	
  	if(!Flag3){
  	if(ItemCannabisPowder.depStart=true){//保険。意味はないと思うが、誤作動の防止目的
  	if(calculation3 == 900)//900だった場合(=15分)
  	{
		
  		//Worldのインスタンス取得。
  		World world = Minecraft.getMinecraft().theWorld;

  		//以下、吐き気のポーション。設定の意味はItemCannabisLeaf他参照
  		int potionID = Potion.confusion.id;
  		int duration = 120 * 20;
  		int amplifier = 2;

  		PotionEffect Effect = new PotionEffect(potionID, duration, amplifier);
		
  		//効果付与
  		event.player.addPotionEffect(Effect);
       
  		world.playSoundAtEntity(event.player, "mayacraft:cannabisbgm", 1.0F, 1.0F);
       
		
  		//一度だけ実行させるためのフラグ。Falseのときのみ実行されるため、2度目以降は実行されない
  		Flag3=true;
			}
			}
  	}
  	if(calculation3 == 903){
  		Flag3= false;
  		long i = System.currentTimeMillis();
     	    ItemCannabisLeaf.depstartedtime = i;//フラッシュバック用。使用時の時間を取得して代入
  	}
  	//3秒ほど後にまたFlagをfalseにして、フラッシュバックが何度でも起こるようにしている。
  	
		   //-------------異様な植物---------------
	long calculation4 = (nowtime - ItemDryCannabisLeaf.depstartedtime)/1000L; //現在時間 - 使用時にセットした時間を秒単位に修正
	
	if(!Flag4){
	if(ItemOPoppy.depStart=true){//保険。意味はないと思うが、誤作動の防止目的
	if(calculation4 == 900)//900だった場合(=15分)
	{
		
		//Worldのインスタンス取得。
		World world = Minecraft.getMinecraft().theWorld;

		//以下、吐き気のポーション。設定の意味はItemCannabisLeaf他参照
		int potionID = Potion.confusion.id;
		int duration = 120 * 20;
		int amplifier = 2;

		PotionEffect Effect = new PotionEffect(potionID, duration, amplifier);
		
		//効果付与
		event.player.addPotionEffect(Effect);
    
		world.playSoundAtEntity(event.player, "mayacraft:cannabisbgm", 1.0F, 1.0F);
    
		
		//一度だけ実行させるためのフラグ。Falseのときのみ実行されるため、2度目以降は実行されない
		Flag4=true;
			}
			}
	}
	if(calculation4 == 903){
		Flag4= false;
		long i = System.currentTimeMillis();
 	    ItemCannabisLeaf.depstartedtime = i;//フラッシュバック用。使用時の時間を取得して代入	
	}
	//3秒ほど後にまたFlagをfalseにして、フラッシュバックが何度でも起こるようにしている。
		
  	
			
		   //-------------異様な植物の乾燥物---------------
	long calculation5 = (nowtime - ItemDryOPoppy.depstartedtime)/1000L; //現在時間 - 使用時にセットした時間を秒単位に修正
	
	if(!Flag5){
	if(ItemDryOPoppy.depStart=true){//保険。意味はないと思うが、誤作動の防止目的
	if(calculation5 == 900)//900だった場合(=15分)
	{
		
		//Worldのインスタンス取得。
		World world = Minecraft.getMinecraft().theWorld;

		//以下、吐き気のポーション。設定の意味はItemCannabisLeaf他参照
		int potionID = Potion.confusion.id;
		int duration = 120 * 20;
		int amplifier = 2;

		PotionEffect Effect = new PotionEffect(potionID, duration, amplifier);
		
		//効果付与
		event.player.addPotionEffect(Effect);
 
		world.playSoundAtEntity(event.player, "mayacraft:cannabisbgm", 1.0F, 1.0F);
 
		
		//一度だけ実行させるためのフラグ。Falseのときのみ実行されるため、2度目以降は実行されない
		Flag5=true;
			}
			}
	}
	if(calculation5 == 903){
		Flag5= false;
		long i = System.currentTimeMillis();
 	    ItemCannabisLeaf.depstartedtime = i;//フラッシュバック用。使用時の時間を取得して代入
	}
	//3秒ほど後にまたFlagをfalseにして、フラッシュバックが何度でも起こるようにしている。
	
	   //-------------異様な植物の粉末---------------
long calculation6 = (nowtime - ItemOPoppyPowder.depstartedtime)/1000L; //現在時間 - 使用時にセットした時間を秒単位に修正

if(!Flag6){
if(ItemOPoppyPowder.depStart=true){//保険。意味はないと思うが、誤作動の防止目的
if(calculation6 == 900)//900だった場合(=15分)
{
	
	//Worldのインスタンス取得。
	World world = Minecraft.getMinecraft().theWorld;

	//以下、吐き気のポーション。設定の意味はItemCannabisLeaf他参照
	int potionID = Potion.confusion.id;
	int duration = 120 * 20;
	int amplifier = 2;

	PotionEffect Effect = new PotionEffect(potionID, duration, amplifier);
	
	//効果付与
	event.player.addPotionEffect(Effect);

	world.playSoundAtEntity(event.player, "mayacraft:cannabisbgm", 1.0F, 1.0F);

	
	//一度だけ実行させるためのフラグ。Falseのときのみ実行されるため、2度目以降は実行されない
	Flag6=true;
		}
		}
}
if(calculation6 == 903){
	Flag6= false;
	long i = System.currentTimeMillis();
	    ItemCannabisLeaf.depstartedtime = i;//フラッシュバック用。使用時の時間を取得して代入
}
//3秒ほど後にまたFlagをfalseにして、フラッシュバックが何度でも起こるようにしている。
}
}
