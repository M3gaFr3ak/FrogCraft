package FrogCraft.Machines.IndustrialDevices;

import ic2.api.recipe.Recipes;
import net.minecraft.item.ItemStack;

import java.util.List;

public class TileEntityIndustrialCompressor extends TileEntityIndustrialDevice
{

	@Override
	public List<ItemStack> getResult(ItemStack[] inv, int i)
	{
		if (inv[i] == null)
			return null;
		return Recipes.compressor.getOutputFor(inv[i], true).items;
	}

	@Override
	public List<ItemStack> getResult(ItemStack i)
	{
		if (i == null)
			return null;
		return Recipes.compressor.getOutputFor(i.copy(), false).items;
	}

}
