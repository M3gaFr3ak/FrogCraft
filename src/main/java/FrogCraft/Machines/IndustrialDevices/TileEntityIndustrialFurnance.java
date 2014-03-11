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
		if (inv == null)
			return Lists.newArrayList();
		if (inv[i] == null)
			return Lists.newArrayList();
		ItemStack outputFor = FurnaceRecipes.smelting().getSmeltingResult(inv[i].copy());
		if (outputFor == null)
			return Lists.newArrayList();
		return Lists.newArrayList(outputFor);
	}

	@Override
	public List<ItemStack> getResult(ItemStack i)
	{
		if (i == null)
			return Lists.newArrayList();
		ItemStack outputFor = FurnaceRecipes.smelting().getSmeltingResult(i.copy());
		if (outputFor == null)
			return Lists.newArrayList();
		return Lists.newArrayList(outputFor);
	}
}
