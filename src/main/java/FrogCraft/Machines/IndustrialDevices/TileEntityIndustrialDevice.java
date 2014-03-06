package FrogCraft.Machines.IndustrialDevices;

import FrogCraft.Common.BaseIC2Machine;
import ic2.api.network.NetworkHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

import java.util.ArrayList;
import java.util.List;

public abstract class TileEntityIndustrialDevice extends BaseIC2Machine implements ISidedInventory
{
	// Variables
	private ItemStack[] inv;

	public int progress;
	public int tick;
	public int heat;

	// public static String[] SoundSource = {"Machines/CompressorOp.ogg","Machines/MaceratorOp.ogg","Machines/ExtractorOp.ogg","Machines/Electro Furnace/ElectroFurnaceStart.ogg"};

	// Class Declaration
	public TileEntityIndustrialDevice()
	{
		super(128, 10000);
		inv = new ItemStack[12];
		progress = 0;
		tick = 0;
		heat = 0;

		if (networkedFields == null)
		{
			networkedFields = new ArrayList();
			networkedFields.add("facing");
		}

	}

	@Override
	public void invalidate()
	{
		super.invalidate();
	}

	@Override
	public void updateEntity()
	{

		if (!this.created)
		{
			this.created = true;
			NetworkHelper.requestInitialData(this);
			NetworkHelper.announceBlockUpdate(worldObj, xCoord, yCoord, zCoord);
		}

		super.updateEntity();

		if (worldObj.isRemote)
			return;

		tick += 1;

		boolean canwork = canWork();

		setWorking(canwork);

		if (canwork & heat < 100 & energy - 40 >= 0)
		{
			if (heat >= 98)
				energy -= 5;
			else
				energy -= 40;

			if (tick % 50 == 0)
				heat = heat + 1;
		}

		if (tick == 100)
		{
			tick = 0;

			if (heat > 0)
				heat = heat - 1;

		}

		if (heat < 30 | !canwork)
		{
			progress = 0;
			return;
		}

		if (cansmeltg() & energy >= 300)
		{
			progress += 1;
			if (progress == 15)
			{
				progress = 0;
				doWork();

			}
		} else
			progress = 0;
	}

	void doWork()
	{
		for (int i = 0; i < 6; i++)
		{
			List<ItemStack> result = getResult(inv, i);
			if (result != null && !result.isEmpty())
			{ // Can smelt
				if (inv[i + 6] == null)
				{
					inv[i + 6] = result.get(0).copy();

					if (inv[i].stackSize == 0)
						inv[i] = null;

					energy -= 50;
				}

				else if (inv[i + 6].isItemEqual(result.get(0)))
				{
					if (inv[i + 6].stackSize <= inv[i + 6].getMaxStackSize() - result.get(0).stackSize)
					{

						inv[i + 6].stackSize += result.get(0).stackSize;

						if (inv[i].stackSize == 0)
							inv[i] = null;

						energy -= 50;
					}
				}
			}
		}
	}

	public boolean canWork()
	{
		if (energy < 300)
			return false;

		if (redPowerd())
			return true;
		else if (cansmeltg())
			return true;

		return false;
	}

	boolean redPowerd()
	{
		return (worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord));
	}

	boolean cansmeltg()
	{
		for (int i = 0; i < 6; i++)
		{
			List<ItemStack> result = getResult(inv[i]);
			if (result != null && !result.isEmpty())
			{
				if (inv[i + 6] == null)
				{
					return true;
				} else
				{
					for (ItemStack stack : result)
						if (stack.isItemEqual(inv[i + 6]) && inv[i + 6].stackSize <= stack.getMaxStackSize() - stack.stackSize)
							return true;
				}
			}
		}

		return false;
	}

	public abstract List<ItemStack> getResult(ItemStack[] inv, int id);

	public abstract List<ItemStack> getResult(ItemStack i);

	@Override
	public int getSizeInventory()
	{
		return inv.length;
	}

	@Override
	public ItemStack getStackInSlot(int slot)
	{
		return inv[slot];
	}

	@Override
	public ItemStack decrStackSize(int slot, int amt)
	{
		ItemStack stack = getStackInSlot(slot);
		if (stack != null)
		{
			if (stack.stackSize <= amt)
			{
				setInventorySlotContents(slot, null);
			} else
			{
				stack = stack.splitStack(amt);
				if (stack.stackSize == 0)
				{
					setInventorySlotContents(slot, null);
				}
			}
		}
		return stack;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot)
	{
		ItemStack stack = getStackInSlot(slot);
		if (stack != null)
			setInventorySlotContents(slot, null);
		return stack;
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack)
	{
		inv[slot] = stack;
		if (stack != null && stack.stackSize > getInventoryStackLimit())
			stack.stackSize = getInventoryStackLimit();
	}

	@Override
	public String getInvName()
	{
		return "tileentityIndustrialDevice";
	}

	@Override
	public boolean isInvNameLocalized()
	{
		return false;
	}

	@Override
	public int getInventoryStackLimit()
	{
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player)
	{
		return worldObj.getBlockTileEntity(xCoord, yCoord, zCoord) == this && player.getDistanceSq(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5) < 64;
	}

	@Override
	public void readFromNBT(NBTTagCompound tagCompound)
	{
		super.readFromNBT(tagCompound);

		NBTTagList tagList = tagCompound.getTagList("Inventory");
		for (int i = 0; i < tagList.tagCount(); i++)
		{
			NBTTagCompound tag = (NBTTagCompound) tagList.tagAt(i);
			byte slot = tag.getByte("Slot");
			if (slot >= 0 && slot < inv.length)
			{
				inv[slot] = ItemStack.loadItemStackFromNBT(tag);
			}
		}

		heat = tagCompound.getInteger("heat");
	}

	@Override
	public void writeToNBT(NBTTagCompound tagCompound)
	{
		super.writeToNBT(tagCompound);

		NBTTagList itemList = new NBTTagList();
		for (int i = 0; i < inv.length; i++)
		{
			ItemStack stack = inv[i];
			if (stack != null)
			{
				NBTTagCompound tag = new NBTTagCompound();
				tag.setByte("Slot", (byte) i);
				stack.writeToNBT(tag);
				itemList.appendTag(tag);
			}
		}
		tagCompound.setTag("Inventory", itemList);
		tagCompound.setInteger("heat", heat);
	}

	// @Override
	// public void onNetworkEvent(int event){
	// NetworkHelper.announceBlockUpdate(worldObj, xCoord, yCoord, zCoord);
	// }

	@Override
	public void openChest()
	{
	}

	@Override
	public void closeChest()
	{
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack)
	{
		return true;
	}

	public int[] getAccessibleSlotsFromSide(int var1)
	{
		return new int[]
		{ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 };
	}

	public boolean canInsertItem(int i, ItemStack itemstack, int j)
	{
		if (i < 6)
			return true;
		return false;
	}

	public boolean canExtractItem(int i, ItemStack itemstack, int j)
	{
		if (i > 5)
			return true;
		return false;
	}

}
