package FrogCraft.nei;

import java.awt.Rectangle;
import java.util.ArrayList;

import org.lwjgl.opengl.GL11;

import FrogCraft.Machines.*;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

import codechicken.nei.PositionedStack;
import codechicken.nei.forge.GuiContainerManager;
import codechicken.nei.recipe.TemplateRecipeHandler;
import static codechicken.core.gui.GuiDraw.*;

public class AdvancedChemicalReactorRecipeHandler extends TemplateRecipeHandler{


	public AdvancedChemicalReactorRecipeHandler(){
		codechicken.nei.api.API.registerRecipeHandler(this);
		codechicken.nei.api.API.registerUsageHandler(this);
	}

	@Override
	public void loadTransferRects(){
		this.transferRects.add(new TemplateRecipeHandler.RecipeTransferRect(new Rectangle(68, 29, 30, 10), getRecipeId(), new Object[0]));

		//Add Machine Gui support
		ArrayList guis = new ArrayList();
		ArrayList transferRects2 = new ArrayList();
		guis.add(GuiAdvanceChemicalReactor.class);
		transferRects2.add(new TemplateRecipeHandler.RecipeTransferRect(new Rectangle(68, 29, 30, 10), getRecipeId(), new Object[0]));
		TemplateRecipeHandler.RecipeTransferRectHandler.registerRectsToGuis(guis, transferRects2); 
	}

	//The string displayed on the top of the recipe window
	@Override
	public String getRecipeName() {
		return StatCollector.translateToLocal("tile.Machines."+ItemBlockMachines.subNames[12]+".name");
	}

	//Recipe window texture
	@Override
	public String getGuiTexture() {
		return "frogcraft:textures/gui/Gui_AdvanceChemicalReactor.png";
	}

	@Override
	public int recipiesPerPage(){
		return 1;
	}

	//Return a custom ID for the Recipe GUI
	public String getRecipeId(){
		return "FrogCraft.ACR";
	}

	@Override
	public void drawExtras(int recipe){
		Cached_Recipe rec=(Cached_Recipe)arecipes.get(recipe);

		drawString(StatCollector.translateToLocal("nei.euTick")+":"+String.valueOf(rec.eu),28,90, 4210752,false);

		if (rec.tick<0){
			drawString(StatCollector.translateToLocal("nei.euTotal")+":"+String.valueOf(rec.eu*rec.tickWithCatalyst),28,80, 4210752,false);	
			drawString(StatCollector.translateToLocal("nei.tick")+":"+String.valueOf(rec.tickWithCatalyst),28,100, 4210752,false);
			drawString(StatCollector.translateToLocal("nei.catalystRequird"),28,110, 4210752,false);
		}
		else{
			drawString(StatCollector.translateToLocal("nei.euTotal")+":"+String.valueOf(rec.eu*rec.tick),28,80, 4210752,false);	
			drawString(StatCollector.translateToLocal("nei.tick")+":"+String.valueOf(rec.tick),28,100, 4210752,false);	
			if (rec.tickWithCatalyst>1)
				drawString(StatCollector.translateToLocal("tickWithCatalyst")+":"+String.valueOf(rec.tickWithCatalyst),28,110, 4210752,false);
		}
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		changeTexture("frogcraft:textures/gui/Gui_AdvanceChemicalReactor.png");
		drawProgressBar(68,29, 176, 0, 30,10,20,1);

		drawTexturedModalRect(143, 12, 176, 17, 10, 14);		
	}

	@Override
	public Class<? extends GuiContainer> getGuiClass(){
		return GuiAdvanceChemicalReactor.class;
	}


	@Override
    public void loadCraftingRecipes(String outputId, Object... results)
    {
		if (outputId.equals(getRecipeId())) {
			for(int i=0;i<FrogCraft.Common.RecipeManager.advanceChemicalReactorRecipes.size();i++)
				arecipes.add(new Cached_Recipe(i));
		}
		else 
			super.loadCraftingRecipes(outputId, results);
    }

	@Override
	public void loadCraftingRecipes(ItemStack result){
		for(int i=0;i<FrogCraft.Common.RecipeManager.advanceChemicalReactorRecipes.size();i++){
			Cached_Recipe rec=new Cached_Recipe(i);
			if (rec.contains(rec.products, result))
				this.arecipes.add(rec);
		}
	}

	@Override
	public void loadUsageRecipes(ItemStack ingredient){
		for(int i=0;i<FrogCraft.Common.RecipeManager.advanceChemicalReactorRecipes.size();i++){
			Cached_Recipe rec=new Cached_Recipe(i);
			if (rec.contains(rec.resources, ingredient))
				this.arecipes.add(rec);
		}
	}


	//Custom class represents a cached recipe

	public class Cached_Recipe extends TemplateRecipeHandler.CachedRecipe{
		public ArrayList products= new ArrayList();
		public ArrayList resources= new ArrayList();

		public int eu,tick,tickWithCatalyst;

		public Cached_Recipe(int i){			
			ItemStack[] rec=FrogCraft.Common.RecipeManager.advanceChemicalReactorRecipes.get(i);
			if (rec[0]!=null)
				this.resources.add(new PositionedStack(rec[0], 35, 11));
			if (rec[1]!=null)
				this.resources.add(new PositionedStack(rec[1], 55, 11));	
			if (rec[2]!=null)
				this.resources.add(new PositionedStack(rec[2], 75, 11));
			if (rec[3]!=null)
				this.resources.add(new PositionedStack(rec[3], 95, 11));		
			if (rec[4]!=null)
				this.resources.add(new PositionedStack(rec[4], 115, 11));	

			if (rec[5]!=null)
				this.products.add(new PositionedStack(rec[5], 35, 41));
			if (rec[6]!=null)
				this.products.add(new PositionedStack(rec[6], 55, 41));	
			if (rec[7]!=null)
				this.products.add(new PositionedStack(rec[7], 75, 41));
			if (rec[8]!=null)
				this.products.add(new PositionedStack(rec[8], 95, 41));		
			if (rec[9]!=null)
				this.products.add(new PositionedStack(rec[9], 115, 41));

			if (rec[10]!=null) //Catalyst
				this.resources.add(new PositionedStack(rec[10], 142, 41));
			if (rec[11]!=null) //Cell need
				this.resources.add(new PositionedStack(rec[11], 7, 11));		
			if (rec[12]!=null) //Cell produced
				this.products.add(new PositionedStack(rec[12], 7, 41));		

			eu=FrogCraft.Common.RecipeManager.advanceChemicalReactorRecipeInfo.get(i)[0];
			tick=FrogCraft.Common.RecipeManager.advanceChemicalReactorRecipeInfo.get(i)[1];
			tickWithCatalyst=FrogCraft.Common.RecipeManager.advanceChemicalReactorRecipeInfo.get(i)[2];
		}

		public ArrayList getIngredients()
		{
			return resources;
		}

		public PositionedStack getResult()
		{
			return null;
		}

		public ArrayList getOtherStacks() {
			return this.products;
		}
	}
}
