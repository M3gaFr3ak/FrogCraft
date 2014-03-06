package FrogCraft.Machines.IndustrialDevices;

import com.google.common.collect.Lists;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;

import java.util.List;

public class TileEntityIndustrialFurnance extends TileEntityIndustrialDevice
{

	@Override
	public List<ItemStack> getResult(ItemStack inv[], int i)
	{
		if (inv[i] == null)
			return null;
		ItemStack r = FurnaceRecipes.smelting().getSmeltingResult(inv[i]);
		if (r != null)
			inv[i].stackSize -= 1;
		return Lists.newArrayList(r);
	}

	@Override
	public List<ItemStack> getResult(ItemStack i)
	{
		if (i == null)
			return null;
		return Lists.newArrayList(FurnaceRecipes.smelting().getSmeltingResult(i.copy()));
	}
}
