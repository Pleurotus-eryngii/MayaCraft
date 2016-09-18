package eryngii.mayacraft.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemCrocodile extends ItemFood{

	public ItemCrocodile(int par1, float par2, boolean par3) {
		super(par1 ,par2 ,par3);
		this.setAlwaysEdible();	//お腹すいてなくても食べれる。

	}
	

	
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
		int potionID6 = Potion.poison.id;
		//Potionの効果時間（【20tick ≒ 1秒】なので*20）
		int duration = 100 * 20;
		int duration2 = 100 * 20;
		int duration3 = 100 * 20;
		int duration4 = 100 * 20;
		int duration5 = 100 * 20;
		int duration6 = 120 * 20;
		//PotionのLv(実際はこのレベルに+1される）
		int amplifier = 3;
		int amplifier2 = 3;
		int amplifier3 = 2;
		int amplifier4 = 3;
		int amplifier5 = 3;
		int amplifier6 = 1;

		//PotionEffectの設定
		PotionEffect Effect = new PotionEffect(potionID, duration, amplifier);
		PotionEffect Effect2 = new PotionEffect(potionID2, duration2, amplifier2);
		PotionEffect Effect3 = new PotionEffect(potionID3, duration3, amplifier3);
		PotionEffect Effect4 = new PotionEffect(potionID4, duration4, amplifier4);
		PotionEffect Effect5 = new PotionEffect(potionID5, duration5, amplifier5);
		PotionEffect Effect6 = new PotionEffect(potionID6, duration6, amplifier6);

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
         
         par2World.playSoundAtEntity(par3EntityPlayer, "mayacraft:cannabisbgm", 1.0F, 2.0F);
         //お遊び。任意のセリフに変更してかまわない
         //このままだとクライアントとサーバが両方処理して2回表示されてしまうので、ifでクライアントのみに絞る
         //http://forum.minecraftuser.jp/viewtopic.php?f=39&t=8370&start=2200#p271893  による

         }

     
        return par1ItemStack;
}


}