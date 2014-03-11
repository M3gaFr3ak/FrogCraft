package FrogCraft.Machines.IndustrialDevices;

import com.google.common.collect.Lists;
import ic2.api.recipe.RecipeOutput;
import net.minecraft.item.ItemStack;
import ic2.api.recipe.Recipes;

import java.util.List;

public class TileEntityIndustrialExtractor extends TileEntityIndustrialDevice
{

	@Override
	public List<ItemStack> getResult(ItemStack[] inv, int i)
	{
		if (inv == null)
			return Lists.newArrayList();
		if (inv[i] == null)
			return Lists.newArrayList();
		RecipeOutput outputFor = Recipes.extractor.getOutputFor(inv[i].copy(), false);
		if (outputFor == null)
			return Lists.newArrayList();
		return outputFor.items;
	}

	@Override
	public List<ItemStack> getResult(ItemStack i)
	{
		if (i == null)
			return Lists.newArrayList();
		RecipeOutput outputFor = Recipes.extractor.getOutputFor(i.copy(), false);
		if (outputFor == null)
			return Lists.newArrayList();
		return outputFor.items;
	}
}
