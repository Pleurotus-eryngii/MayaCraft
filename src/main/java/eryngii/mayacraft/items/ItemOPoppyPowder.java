package eryngii.mayacraft.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemOPoppyPowder  extends ItemFood{

	public ItemOPoppyPowder(int par1, float par2, boolean par3) {
		super(par1 ,par2 ,par3);
		this.setAlwaysEdible();	//お腹すいてなくても食べれる。

	}
	
	public static boolean depStart=false;
	public static long depstartedtime;
	
	@Override
	public ItemStack onEaten(ItemStack par1ItemStack, World par2World,
			EntityPlayer par3EntityPlayer) {
		
		//クリエイティブモード以外ならスタックサイズを減らす
		if (!par3EntityPlayer.capabilities.isCreativeMode) {
			--par1ItemStack.stackSize;
		}
		
		/*この場合個別に登録しているが、PotionEffectの処理の際には、
		*同じdurationやamplifierを流用できるはず。
		*今回は調整しやすくするために分けてみた
		*/
		int potionID = Potion.jump.id;
		int potionID2 = Potion.damageBoost.id;
		int potionID3 = Potion.confusion.id;
		int potionID4 = Potion.digSpeed.id;
		int potionID5 = Potion.moveSpeed.id;
		//Potionの効果時間（【20tick ≒ 1秒】なので*20）
		int duration = 70 * 20;
		int duration2 = 70 * 20;
		int duration3 = 70 * 20;
		int duration4 = 70 * 20;
		int duration5 = 70 * 20;
		//PotionのLv(実際はこのレベルに+1される）
		int amplifier = 3;
		int amplifier2 = 3;
		int amplifier3 = 2;
		int amplifier4 = 3;
		int amplifier5 = 3;

		//PotionEffectの設定
		PotionEffect Effect = new PotionEffect(potionID, duration, amplifier);
		PotionEffect Effect2 = new PotionEffect(potionID2, duration2, amplifier2);
		PotionEffect Effect3 = new PotionEffect(potionID3, duration3, amplifier3);
		PotionEffect Effect4 = new PotionEffect(potionID4, duration4, amplifier4);
		PotionEffect Effect5 = new PotionEffect(potionID5, duration5, amplifier5);

     //PotionEffect(Effect)がEntityPlayerに付与されているかの判定
     boolean isMoveSpeed = par3EntityPlayer.isPotionActive(Effect.getPotionID());

     //PotionEffect(Effect)がEntityPlayerに付与されていない場合
     if( !isMoveSpeed )
     {
         //効果付与
         par3EntityPlayer.addPotionEffect(Effect);
         par3EntityPlayer.addPotionEffect(Effect2);
         par3EntityPlayer.addPotionEffect(Effect3);
         par3EntityPlayer.addPotionEffect(Effect4);
         par3EntityPlayer.addPotionEffect(Effect5);
         
         par2World.playSoundAtEntity(par3EntityPlayer, "mayacraft:cannabisbgm", 1.0F, 1.0F);
         
         long i = System.currentTimeMillis();
    	 depstartedtime = i;//フラッシュバック用。使用時の時間を取得して代入
    	 depStart=true;//保険。これがTrueのときのみ発動する。
     }

     
        return par1ItemStack;
}


}