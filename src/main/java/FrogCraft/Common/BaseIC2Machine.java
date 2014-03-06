package FrogCraft.Common;

import ic2.api.energy.tile.IEnergySink;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;

public abstract class BaseIC2Machine extends BaseIC2NetTileEntity implements IEnergySink
{

	public int maxEnergy = 0;
	public int maxInput;
	public int energy = 0;

	public BaseIC2Machine(int saftVoltage, int maxStoredEnergy)
	{
		maxEnergy = maxStoredEnergy;
		maxInput = saftVoltage;
	}

	@Override
	public void readFromNBT(NBTTagCompound var1)
	{
		super.readFromNBT(var1);
		this.energy = var1.getInteger("energy");
	}

	@Override
	public void writeToNBT(NBTTagCompound var1)
	{
		super.writeToNBT(var1);
		var1.setInteger("energy", this.energy);
	}

	@Override
	public double injectEnergyUnits(ForgeDirection directionFrom, double amount)
	{
		if (amount > this.maxInput)
		{
			this.worldObj.createExplosion(null, this.xCoord, this.yCoord, this.zCoord, 2F, true);
			invalidate();
			return 0;
		} else
		{
			this.energy += amount;
			int var3 = 0;
			if (this.energy > this.maxEnergy)
			{
				var3 = this.energy - this.maxEnergy;
				this.energy = this.maxEnergy;
			}
			return var3;
		}
	}

	@Override
	public double demandedEnergyUnits()
	{
		return maxEnergy - energy;
	}

	@Override
	public int getMaxSafeInput()
	{
		return maxInput;
	}

	@Override
	public boolean acceptsEnergyFrom(TileEntity emitter, ForgeDirection direction)
	{
		return true;
	}
}
