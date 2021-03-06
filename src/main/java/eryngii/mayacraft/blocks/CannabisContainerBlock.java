package eryngii.mayacraft.blocks;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import eryngii.mayacraft.MayaCraftCore;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class CannabisContainerBlock   extends Block
{
	//テクスチャや内部名称の際に使用する数値を設定
		private IIcon[] iicon = new IIcon[2];
	
    @SideOnly(Side.CLIENT)
    private IIcon TopIcon;
 
    @SideOnly(Side.CLIENT)
    private IIcon SideIcon;
 
    public CannabisContainerBlock() {
        super(Material.wood);
        setCreativeTab(MayaCraftCore.tabsMaya);/*クリエイティブタブの選択*/
        setBlockName("blockCannabisContainer");/*システム名の設定*/
        setBlockTextureName("mayacraft:cannabiscontainer");/*ブロックのテクスチャの指定(複数指定の場合は消してください)*/
   
    }
    //ブロックのテクスチャ指定。blockrock_(メタデータの値)
  	@Override
  	@SideOnly(Side.CLIENT)
  	public void registerBlockIcons(IIconRegister register) {
  		for (int i = 0; i < 2; i ++) {
  			this.iicon[i] = register.registerIcon(this.getTextureName() + "_" + i);
  		}
  	}
     //インベントリ内でのテクスチャの指定。metaでメタデータを取得している
  	@Override
  	@SideOnly(Side.CLIENT)
  	public IIcon getIcon(int side, int meta) {
  		return iicon[meta];
  	}
  //クリエイティブタブへの登録メソッド。第三引数のListにアイテムスタックを追加
  	@Override
  	@SideOnly(Side.CLIENT)
  	public void getSubBlocks(Item item, CreativeTabs creativeTab, List list) {
  		for (int i = 0; i < 2; i ++) {
  			list.add(new ItemStack(item, 1, i));
  		}
  	}
  //ドロップ時のテクスチャ指定。インベントリ内での処理とほぼ同じ
  	@Override
  	public int damageDropped(int meta) {
  		return meta;
}
}