package FrogCraft.Machines;

import FrogCraft.Common.BaseIC2Machine;
import ic2.api.energy.event.EnergyTileEvent;
import ic2.api.energy.event.EnergyTileLoadEvent;
import ic2.api.energy.event.EnergyTileUnloadEvent;
import ic2.api.energy.tile.IEnergySource;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.MinecraftForge;

public class TileEntityEVTransformer extends BaseIC2Machine implements IEnergySource
{

	private EnergyTileEvent sourceEvent;
	private boolean red = false;

	public TileEntityEVTransformer()
	{
		super(8192, 8192 * 2);
	}

	@Override
	public void updateEntity()
	{
		super.updateEntity();

		if (red != (worldObj.getBlockPowerInput(xCoord, yCoord, zCoord) > 0))
		{
			MinecraftForge.EVENT_BUS.post(new EnergyTileUnloadEvent(this));
			this.addedToEnergyNet = false;
			MinecraftForge.EVENT_BUS.post(new EnergyTileLoadEvent(this));
			this.addedToEnergyNet = true;
		}

		red = worldObj.getBlockPowerInput(xCoord, yCoord, zCoord) > 0;

		if (!red)
		{
			for (int i = 0; i < (maxEnergy / 2048); i++)
				out(2048);
		} else
			out(8192);
	}

	void out(int amount)
	{
		if (amount > energy)
			return;

		if (amount < 0)
			return;

		sourceEvent = new EnergyTileEvent(this);
		MinecraftForge.EVENT_BUS.post(sourceEvent);
	}

	@Override
	public boolean emitsEnergyTo(TileEntity receiver, ForgeDirection direction)
	{
		if (worldObj.getBlockPowerInput(xCoord, yCoord, zCoord) == 0)
		{
			if (direction.ordinal() == facing)
				return false;
			return true;
		} else
		{
			if (direction.ordinal() == facing)
				return true;
			return false;
		}
	}

	@Override
	public void beforeSetFacing(short newFacing, short oldFacing)
	{
		if (this.addedToEnergyNet)
		{
			MinecraftForge.EVENT_BUS.post(new EnergyTileUnloadEvent(this));
			this.addedToEnergyNet = false;
		}
		return;
	}

	@Override
	public void afterSetFacing(short facing)
	{
		if (!this.addedToEnergyNet)
		{
			MinecraftForge.EVENT_BUS.post(new EnergyTileLoadEvent(this));
			this.addedToEnergyNet = true;
		}
		return;
	}

	@Override
	public boolean acceptsEnergyFrom(TileEntity tileEntity, ForgeDirection side)
	{
		if (worldObj.getBlockPowerInput(xCoord, yCoord, zCoord) == 0)
		{
			if (side.ordinal() == facing)
				return true;
			return false;
		} else
		{
			if (side.ordinal() == facing)
				return false;
			return true;
		}
	}

	@Override
	public boolean wrenchCanSetFacing(EntityPlayer var1, int facingToSet)
	{
		if (facingToSet == facing)
			return false;
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
