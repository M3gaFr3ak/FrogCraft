package FrogCraft.Machines.IndustrialDevices;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import FrogCraft.Machines.ItemBlockMachines;

public class GuiIndustrialDevice extends GuiContainer {
		
		protected TileEntityIndustrialDevice tileentity;
	
        public GuiIndustrialDevice (InventoryPlayer inventoryPlayer,TileEntityIndustrialDevice tileEntity) {
                super(new ContainerIndustrialDevice(inventoryPlayer, tileEntity));
                tileentity=tileEntity;
        }

        @Override
        protected void drawGuiContainerForegroundLayer(int param1, int param2) {
                //draw text and stuff here
                //the parameters for drawString are: string, x, y, color
        		fontRenderer.drawString(String.valueOf(tileentity.heat)+"%", 140,  ySize - 96 - 4, 4210752);
                fontRenderer.drawString(StatCollector.translateToLocal("tile.Machines."+ItemBlockMachines.subNames[tileentity.getBlockMetadata()]+".name"), 8, 6, 4210752);
                //draws "Inventory" or your regional equivalent
                fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 96, 4210752);
        }

        @Override
        protected void drawGuiContainerBackgroundLayer(float par1, int par2,
                        int par3) {
                //draw your Gui here, only thing you need to change is the path
                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                mc.renderEngine.bindTexture(new ResourceLocation("frogcraft:textures/gui/Gui_IndustrialDevice.png"));
                int x = (width - xSize) / 2;
                int y = (height - ySize) / 2;
                this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
                
                
                if (tileentity.energy>0)
                	this.drawTexturedModalRect(x + 81, y + 52+14-gete(), 176, 31 - gete(), 14, gete());
                
                if (tileentity instanceof TileEntityIndustrialFurnance)
                	this.drawTexturedModalRect(x + 76, y + 25, 201, 0, 24, 16);
                if (tileentity instanceof TileEntityIndustrialCompressor)
                	this.drawTexturedModalRect(x + 76, y + 25, 201, 32, 24, 16);	
                if (tileentity instanceof TileEntityIndustrialMacerator)
                	this.drawTexturedModalRect(x + 76, y + 25, 201, 50, 24, 16);
                if (tileentity instanceof TileEntityIndustrialExtractor)      
                	this.drawTexturedModalRect(x + 76, y + 25, 201, 68, 24, 16);
                
                if (tileentity.progress>0){
                	if (tileentity instanceof TileEntityIndustrialFurnance)
                		this.drawTexturedModalRect(x + 76, y + 25, 176, 0, getp(), 16);
                	if (tileentity instanceof TileEntityIndustrialCompressor)
                		this.drawTexturedModalRect(x + 76, y + 25, 176, 32, getp(), 16);
                	if (tileentity instanceof TileEntityIndustrialMacerator)
                		this.drawTexturedModalRect(x + 76, y + 25, 176, 50, getp(), 16);
                	if (tileentity instanceof TileEntityIndustrialExtractor)
                		this.drawTexturedModalRect(x + 76, y + 25, 176, 68, getp(), 16);                	
                }
                
        }

        int gete(){
        	return 14*tileentity.energy/tileentity.maxEnergy;
        }
        
        int getp(){
        	return tileentity.progress*24/15;
        }
}