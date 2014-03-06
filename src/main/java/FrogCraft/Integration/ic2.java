package FrogCraft.Integration;

import ic2.api.item.Items;
import ic2.api.recipe.RecipeInputItemStack;
import ic2.api.recipe.Recipes;
import net.minecraft.item.ItemStack;

public class ic2
{
	public static void init()
	{/*/
		// IC2
		OreDictionary.registerOre("calclavia:WIRE", getItem("insulatedCopperCableItem"));
		OreDictionary.registerOre("craftingWireGold", getItem("doubleInsulatedGoldCableItem"));
		OreDictionary.registerOre("craftingWireIron", getItem("trippleInsulatedIronCableItem"));
		OreDictionary.registerOre("craftingRawMachineTier01", getItem("machine"));
		OreDictionary.registerOre("craftingRawMachineTier02", getItem("advancedMachine"));
		OreDictionary.registerOre("craftingCompressor", getItem("compressor"));
		OreDictionary.registerOre("craftingMacerator", getItem("macerator"));
		OreDictionary.registerOre("craftingExtractor", getItem("extractor"));
		OreDictionary.registerOre("craftingElectricFurnace", getItem("electroFurnace"));
		OreDictionary.registerOre("craftingInductionFurnace", getItem("inductionFurnace"));
		OreDictionary.registerOre("craftingPump", getItem("pump"));
		OreDictionary.registerOre("craftingCircuitTier02", getItem("electronicCircuit"));
		OreDictionary.registerOre("craftingCircuitTier04", getItem("advancedCircuit"));
		OreDictionary.registerOre("crafting100kEUStore", getItem("energyCrystal"));
		OreDictionary.registerOre("crafting1kkEUStore", getItem("lapotronCrystal"));

		OreDictionary.registerOre("ingotRefinedIron", getItem("refinedIronIngot"));
		OreDictionary.registerOre("plateAlloyIridium", getItem("iridiumPlate"));
		OreDictionary.registerOre("plateAlloyAdvanced", getItem("advancedAlloy"));
		OreDictionary.registerOre("plateAlloyCarbon", getItem("carbonPlate"));
		OreDictionary.registerOre("plateDenseCopper", getItem("denseCopperPlate"));
		OreDictionary.registerOre("craftingFertilizer", getItem("fertilizer"));

		OreDictionary.registerOre("chunkLazurite", new ItemStack(Block.blockLapis));//*/
	}

	// Common methods
	public static void addMacerator(ItemStack in, ItemStack out)
	{
		Recipes.macerator.addRecipe(new RecipeInputItemStack(in), null, out);
	}

	public static void addExtractor(ItemStack in, ItemStack out)
	{
		Recipes.extractor.addRecipe(new RecipeInputItemStack(in), null, out);
	}

	public static void addCompressor(ItemStack in, ItemStack out)
	{
		Recipes.compressor.addRecipe(new RecipeInputItemStack(in), null, out);
	}

	public static void addAmplifier(ItemStack item, int value)
	{
		// TODO doesnt work. Recipes.matterAmplifier.addRecipe(new RecipeInputItemStack(item), value);
	}

	public static ItemStack getItem(String name)
	{
		return getItem(name, 1);
	}

	public static ItemStack getItem(String name, int amount)
	{
		try
		{
			return Items.getItem(name).copy().splitStack(amount);
		} catch (Throwable e)
		{
			return null;
		}
	}

	public static void regUnshaped(ItemStack result, Object[] par)
	{
		Recipes.advRecipes.addShapelessRecipe(result, par);
	}

	public static void regShaped(ItemStack result, Object[] par)
	{
		Recipes.advRecipes.addRecipe(result, par);
	}
}
