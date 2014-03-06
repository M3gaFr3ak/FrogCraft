package FrogCraft.Items.MobilePS;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

import java.util.List;

public class ItemBlock_MobilePS extends ItemBlock implements ic2.api.item.IElectricItem
{
	public static int maxCharge = 60000;

	public ItemBlock_MobilePS(int id)
	{
		super(id);
		setMaxDamage(256);
		setMaxStackSize(1);
	}

	public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean par4)
	{
		String info = StatCollector.translateToLocal(getStatName() + ".info");
		list.add(info);
	}

	@Override
	public int getMetadata(int damageValue)
	{
		return damageValue;
	}

	@Override
	public boolean canProvideEnergy(ItemStack itemStack)
	{
		return true;
	}

	@Override
	public int getChargedItemId(ItemStack itemStack)
	{
		return itemID;
	}

	@Override
	public int getEmptyItemId(ItemStack itemStack)
	{
		return itemID;
	}

	@Override
	public int getMaxCharge(ItemStack itemStack)
	{
		int maxE;
		if (itemStack.stackTagCompound == null)
		{
			maxE = maxCharge;
		} else
		{
			maxE = itemStack.stackTagCompound.getInteger("maxEnergy");
			if (maxE == 0)
				maxE = maxCharge;
		}

		return maxE;
	}

	@Override
	public int getTier(ItemStack itemStack)
	{
		return 1;
	}

	@Override
	public int getTransferLimit(ItemStack itemStack)
	{
		return 1000;
	}

}
