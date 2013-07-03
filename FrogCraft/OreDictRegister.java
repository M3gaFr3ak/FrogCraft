package FrogCraft;

import FrogCraft.api.fcItems;
import net.minecraft.item.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraftforge.oredict.OreDictionary;
import gregtechmod.api.GregTech_API;
import ic2.api.item.Items;

public class OreDictRegister {
	public static void registerOreDict(){
		//IC2
		OreDictionary.registerOre("crafting1kkEUStore",Items.getItem("lapotronCrystal"));		
		OreDictionary.registerOre("craftingRawMachineTier01",Items.getItem("machine"));		
		OreDictionary.registerOre("craftingRawMachineTier02",Items.getItem("advancedMachine"));
		
		OreDictionary.registerOre("ingotPotassium",new ItemStack(fcItems.ingotsID,1,0));
		OreDictionary.registerOre("gemPhosphor",new ItemStack(fcItems.ingotsID,1,1));	
		OreDictionary.registerOre("molecule_1n_4h_1n_3o",new ItemStack(fcItems.cellsID,1,7));
		OreDictionary.registerOre("molecule_1h_1n_3o",new ItemStack(fcItems.cellsID,1,8));
		OreDictionary.registerOre("molecule_1n_1o",new ItemStack(fcItems.cellsID,1,9));		
		OreDictionary.registerOre("dustAmmoniumNitrate",new ItemStack(fcItems.dustsID,1,2));			
		OreDictionary.registerOre("crafting60kCoolantStore",fcItems.IC2Coolant_NH3_60K);
		OreDictionary.registerOre("crafting180kCoolantStore",fcItems.IC2Coolant_NH3_180K);	
		OreDictionary.registerOre("crafting360kCoolantStore",fcItems.IC2Coolant_NH3_360K);
	}
}
