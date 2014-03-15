package FrogCraft.nei;

import codechicken.nei.api.IConfigureNEI;

public class NEI_Config implements IConfigureNEI {

	@Override
	public void loadConfig() {
		new AdvancedChemicalReactorRecipeHandler();
		new ThermalCrackerRecipeHandler();
		new CondenseTowerRecipeHandler();
		new LiquifierRecipeHandler();
		new CombustionFurnaceRecipeHandler();		
	}

	@Override
	public String getName() {
		return "FrogCraft NEI Plugin";
	}

	@Override
	public String getVersion() {
		return "1.0";
	}

}
