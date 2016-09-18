package eryngii.mayacraft.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import eryngii.mayacraft.MayaCraftCore;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.IIcon;

public class OpiumPoppyContainerBlock  extends Block
{
    @SideOnly(Side.CLIENT)
    private IIcon TopIcon;
 
    @SideOnly(Side.CLIENT)
    private IIcon SideIcon;
 
    public OpiumPoppyContainerBlock() {
        super(Material.wood);
        setCreativeTab(MayaCraftCore.tabsMaya);/*クリエイティブタブの選択*/
        setBlockName("blockOpiumPoppyContainer");/*システム名の設定*/
        setBlockTextureName("mayacraft:poppycontainer");/*ブロックのテクスチャの指定(複数指定の場合は消してください)*/
   
    }

}
