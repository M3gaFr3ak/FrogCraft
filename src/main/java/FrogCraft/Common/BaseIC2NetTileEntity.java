package FrogCraft.Common;

import net.minecraftforge.common.MinecraftForge;
import ic2.api.energy.event.EnergyTileLoadEvent;
import ic2.api.energy.event.EnergyTileUnloadEvent;
import ic2.api.energy.tile.IEnergyTile;

public abstract class BaseIC2NetTileEntity extends SidedIC2Machine implements IEnergyTile
{
	public boolean addedToEnergyNet;

	@Override
	public void updateEntity()
	{
		super.updateEntity();
		if (!worldObj.isRemote & !this.addedToEnergyNet)
		{
			// EnergyNet.getForWorld(this.worldObj).addTileEntity(this);
			MinecraftForge.EVENT_BUS.post(new EnergyTileLoadEvent(this));
			this.addedToEnergyNet = true;
		}
	}

	@Override
	public void invalidate()
	{
		if (!worldObj.isRemote & this.addedToEnergyNet)
		{
			// EnergyNet.getForWorld(this.worldObj).removeTileEntity(this);
			MinecraftForge.EVENT_BUS.post(new EnergyTileUnloadEvent(this));
			this.addedToEnergyNet = false;
		}

		super.invalidate();
	}
}
