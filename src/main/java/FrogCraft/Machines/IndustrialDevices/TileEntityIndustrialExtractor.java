package FrogCraft.Machines.IndustrialDevices;

import net.minecraft.item.ItemStack;
import ic2.api.recipe.Recipes;

import java.util.List;

public class TileEntityIndustrialExtractor extends TileEntityIndustrialDevice
{

	@Override
	public List<ItemStack> getResult(ItemStack[] inv, int i)
	{
		if (inv[i] == null)
			return null;
		return Recipes.extractor.getOutputFor(inv[i], true).items;
	}

	@Override
	public List<ItemStack> getResult(ItemStack i)
	{
		if (i == null)
			return null;
		return Recipes.extractor.getOutputFor(i.copy(), false).items;
	}
}
