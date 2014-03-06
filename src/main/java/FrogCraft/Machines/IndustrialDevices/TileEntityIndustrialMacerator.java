package FrogCraft.Machines.IndustrialDevices;

import net.minecraft.item.ItemStack;
import ic2.api.recipe.Recipes;

import java.util.List;

public class TileEntityIndustrialMacerator extends TileEntityIndustrialDevice
{
	@Override
	public List<ItemStack> getResult(ItemStack[] inv, int i)
	{
		if (inv[i] == null)
			return null;
		return Recipes.macerator.getOutputFor(inv[i], true).items;
	}

	@Override
	public List<ItemStack> getResult(ItemStack i)
	{
		if (i == null)
			return null;
		return Recipes.macerator.getOutputFor(i.copy(), false).items;
	}
}
