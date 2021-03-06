package eryngii.mayacraft.achievements;

import java.util.ArrayList;

import eryngii.mayacraft.MayaCraftCore;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;

public class MayaAchievements {

    private static ArrayList<Achievement> core = new ArrayList<Achievement>();
    
    //実績の登録
    public static Achievement CallingFrom;
    public static Achievement DangerousChallenge;
    public static Achievement MoreEffect;
    public static Achievement Poppy;
    public static Achievement NotFlower;
    public static Achievement Deeply;
    public static Achievement BoundUntil;

    public static void initAchievements() {

    	//第3引数のItemStackに登録したアイテムやブロックがクラフトされた際に実績が取得されるようになっている
        CallingFrom = new MayaAchievementCrafting("CallingFrom", -3, 0, new ItemStack(MayaCraftCore.blockCannabis), (Achievement) null, core).registerStat();
        DangerousChallenge = new MayaAchievementCrafting("DangerousChallenge", 0, 0, new ItemStack(MayaCraftCore.itemCannabisLeaf), CallingFrom,core).registerStat();
        MoreEffect = new  MayaAchievementCrafting("MoreEffect", 3, 0, new ItemStack(MayaCraftCore.itemCannabisPowder), DangerousChallenge,core).registerStat();
        Poppy = new  MayaAchievementCrafting("Poppy", -3, 3, new ItemStack(MayaCraftCore.blockOpiumPoppy), null,core).registerStat();
        NotFlower = new  MayaAchievementCrafting("NotFlower", 0, 3, new ItemStack(MayaCraftCore.itemOpiumPoppy), Poppy,core).registerStat();
        Deeply = new MayaAchievementCrafting("Deeply", 3, 3, new ItemStack(MayaCraftCore.itemOpiumPoppyPowder), NotFlower,core).registerStat();
        AchievementPage.registerAchievementPage(new AchievementPageBase("achievement.bountifulmod", core));

    }
    
    public static void loadAchievements(){
    	BoundUntil = new Achievement("achievement.cfm_install", "cfm_install", 2, 2, new ItemStack(MayaCraftCore.blockCannabisContainer), null).setSpecial();
    }

}