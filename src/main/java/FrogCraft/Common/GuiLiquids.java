package FrogCraft.Common;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.inventory.Container;
import net.minecraft.util.Icon;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public abstract class GuiLiquids extends GuiContainer{
	public GuiLiquids(Container par1Container) {super(par1Container);}
	
	public static void drawLiquidBar(int x,int y,int width,int height,int fluidID,int percentage){
		Fluid fluid=FluidRegistry.getFluid(fluidID);
		if (fluid==null)
			return;
		
		Icon icon=fluid.getIcon();
		
		if (icon==null)
			return;

		//Bind SpriteNumber=0,texture "/terrain.png"
		TextureManager texturemanager = Minecraft.getMinecraft().getTextureManager();
		texturemanager.bindTexture(texturemanager.getResourceLocation(0));
		
		
		double u = icon.getInterpolatedU(3.0D);
		double u2 = icon.getInterpolatedU(13.0D);
		double v = icon.getInterpolatedV(1.0D);
		double v2 = icon.getInterpolatedV(15.0D);

		int z=height*percentage/100;

		GL11.glEnable(3553);
		GL11.glColor4d(1.0D, 1.0D, 1.0D, 1.0D);

		GL11.glBegin(7);
		GL11.glTexCoord2d(u, v);
		GL11.glVertex2i(x, y+height-z);

		GL11.glTexCoord2d(u, v2);
		GL11.glVertex2i(x, y + height);

		GL11.glTexCoord2d(u2, v2);
		GL11.glVertex2i(x + width, y + height);

		GL11.glTexCoord2d(u2, v);
		GL11.glVertex2i(x + width, y+height-z);
		GL11.glEnd();
	}
}
