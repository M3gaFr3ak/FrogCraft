package FrogCraft.Machines;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import FrogCraft.*;
import FrogCraft.Common.GuiLiquids;

public class GuiThermalCracker extends GuiLiquids {
		
		protected TileEntityThermalCracker tileentity;
	
        public GuiThermalCracker (InventoryPlayer inventoryPlayer,TileEntityThermalCracker tileEntity) {
                super(new ContainerThermalCracker(inventoryPlayer, tileEntity));
                tileentity=tileEntity;
        }

        @Override
        protected void drawGuiContainerForegroundLayer(int par1, int par2) {
                //draw text and stuff here
                //the parameters for drawString are: string, x, y, color
                fontRenderer.drawString(ItemBlockMachines.Machines_Names[tileentity.getBlockMetadata()], 8, 6, 4210752);
                //draws "Inventory" or your regional equivalent
                //fontRenderer.drawString(String.valueOf(tileentity.progress)+"%", 8, ySize - 96, 4210752);    
                fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 96, 4210752);    
              
                
            	this.drawLiquidBar(143, 23, 16, 47, tileentity.idOut, tileentity.damageOut, tileentity.amountOutP/10);

            	mc.renderEngine.bindTexture("/mods/FrogCraft/textures/gui/Gui_ThermalCracker.png");     
            	this.drawTexturedModalRect(143, 23, 176, 0, 16, 47);
              	

            	int x=par1-((width-xSize)/2),y=par2-((height-ySize)/2);
            	if (x>=142&y>=22&x<=160&y<=70){
            		List<String> l = new ArrayList<String>();
            		if (tileentity.idOut>0){
            			l.add(new ItemStack(tileentity.idOut,1,tileentity.damageOut).getDisplayName());
            			l.add(String.valueOf(tileentity.amountOutP/10)+"."+String.valueOf(tileentity.amountOutP-(tileentity.amountOutP/10)*10)+"%");
            			l.add("About "+String.valueOf(tileentity.amountOutP*tileentity.maxCapacity/1000)+"mB");
            			func_102021_a(l, x, y);   
                    }             
            	}    	
        }

        @Override
        protected void drawGuiContainerBackgroundLayer(float par1, int par2,
                        int par3) {
                //draw your Gui here, only thing you need to change is the path
                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                this.mc.renderEngine.bindTexture("/mods/FrogCraft/textures/gui/Gui_ThermalCracker.png");
                int x = (width - xSize) / 2;
                int y = (height - ySize) / 2;
                this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
                
                
                if (tileentity.energy>0)
                	this.drawTexturedModalRect(x + 81, y + 57+14-gete(), 176, 52+14 - gete(), 14, gete());
                
                if (tileentity.progress>0)
                	this.drawTexturedModalRect(x + 45, y + 28, 176, 80, 24*tileentity.progress/100, 17);         
                
                if (tileentity.progress>0)
                	this.drawTexturedModalRect(x + 25, y + 51+13-geth(), 176, 66+13-geth(), 14, geth());
        }

        int gete(){
        	return 14*tileentity.energy/tileentity.maxEnergy;
        }
        
        int geth(){
        	if(tileentity.progress<50)
        	
        	return 26*tileentity.progress/100;
        	else
        		return 26*(100-tileentity.progress)/100;
        }
        
}