package FrogCraft.Common;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.MinecraftForge;
import ic2.api.energy.event.EnergyTileEvent;
import ic2.api.energy.tile.IEnergySource;

public class BaseIC2Generator extends BaseIC2NetTileEntity implements IEnergySource
{
	private EnergyTileEvent sourceEvent;
	public int maxEnergy, energy = 0;

	public BaseIC2Generator()
	{
		this.maxEnergy = Integer.MAX_VALUE;
	}

	public BaseIC2Generator(int maxEnergy)
	{
		this.maxEnergy = maxEnergy;
	}

	@Override
	public void writeToNBT(NBTTagCompound tagCompound)
	{
		super.writeToNBT(tagCompound);
		tagCompound.setInteger("energy", energy);
	}

	@Override
	public void readFromNBT(NBTTagCompound tagCompound)
	{
		super.readFromNBT(tagCompound);
		energy = tagCompound.getInteger("energy");
	}

	/** Output amounts of energy, return energy not being emitted */
	protected int out(int voltage, int power)
	{
		if (voltage > power)
			return voltage - power;

		int num = power / voltage;
		for (int i = 0; i < num; i++)
		{
			out(voltage);
		}

		return num * voltage - power;
	}

	/** Output a packet of energy */
	protected void out(int amount)
	{
		if (energy < amount)
			return;

		sourceEvent = new EnergyTileEvent(this);
		MinecraftForge.EVENT_BUS.post(sourceEvent);
	}

	@Override
	public boolean emitsEnergyTo(TileEntity receiver, ForgeDirection direction)
	{
		return true;
	}

	@Override
	public double getOfferedEnergy()
	{
		return Math.min(energy, maxEnergy);
	}

	@Override
	public void drawEnergy(double amount)
	{
		energy -= amount;
	}
}
