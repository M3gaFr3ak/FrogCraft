package FrogCraft.Integration;

import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.Loader;
import gregtechmod.api.util.GT_Recipe;

public class GregTech
{
	public static boolean isGTLoaded = false;
	public static ItemStack sampleITNT = null;

	public static int GTComponent = -1;

	public static void init()
	{
		sampleITNT = new ItemStack(ic2.getItem("industrialTnt").itemID, 64, 0);
		isGTLoaded = Loader.isModLoaded("gregtech_addon");

		if (getGregTechItem(3, 1, 27) != null)
			GTComponent = getGregTechItem(3, 1, 27).itemID;
	}

	public static ItemStack getGTItem(int damage, int amount)
	{
		return getGregTechItem(0, amount, damage);
	}

	public static ItemStack getGTDust(int damage, int amount)
	{
		return getGregTechItem(1, amount, damage);
	}

	public static ItemStack getGTCell(int damage, int amount)
	{
		return getGregTechItem(2, amount, damage);
	}

	public static ItemStack getGTComponent(int damage, int amount)
	{
		return getGregTechItem(3, amount, damage);
	}

	public static ItemStack getGTSmallDust(int damage, int amount)
	{
		return getGregTechItem(4, amount, damage);
	}

	/** Return:{output,itntStackSize,cosumedInput} */
	public static Object[] findImplosionRecipe(ItemStack in)
	{
		if (!isGTLoaded)
			return null;

		GT_Recipe g = null;
		for (GT_Recipe recipe : GT_Recipe.sImplosionRecipes)
		{
			if (recipe.getRepresentativeInput1().isItemEqual(in) && recipe.getRepresentativeInput2().isItemEqual(sampleITNT))
				g = recipe;
			break;
		}
		if (g == null)
			return null;

		return new Object[]
		{ g.getOutput(1), g.getOutput(2).stackSize, g.getRepresentativeInput1().stackSize };
	}

	private static ItemStack getGregTechItem(int type, int amount, int damage)
	{
		try
		{
			return (ItemStack) Class.forName("gregtechmod.api.GregTech_API").getMethod("getGregTechItem", int.class, int.class, int.class).invoke(null, type, amount, damage);
		} catch (Exception e)
		{
		}
		return null;
	}
}
