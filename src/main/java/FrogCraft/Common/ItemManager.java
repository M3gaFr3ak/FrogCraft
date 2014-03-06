package FrogCraft.Common;

import net.minecraft.item.ItemStack;

import FrogCraft.Items.*;
import FrogCraft.api.fcItems.cls;
import FrogCraft.api.fcItems;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

public class ItemManager {
	public static ItemStack getItem(int type,String name){return getItem(type,name,1);}
	
	public static ItemStack getItem(cls type,String name){return getItem(type,name,1);}
	
	public static ItemStack getItem(cls type, String name, int amount) {return getItem(type.t,name,amount);}
	
	public static ItemStack getItem(int type,String name,int amount){
		int id=-1;
		BiMap<Integer,String> nameMap= HashBiMap.create();
		

		if (type==0){
			id=fcItems.Ingots.itemID;
			nameMap=Item_Ingots.subNames;
		}
		if (type==1){
			id=fcItems.Cells.itemID;
			nameMap=Item_Cells.subNames;
		}
		if (type==2){
			id=fcItems.Miscs.itemID;
			nameMap=Item_Miscs.subNames;
		}
		if (type==3){
			id=fcItems.Dusts.itemID;
			nameMap=Item_Dusts.subNames;
		}
		
		
		if(id==-1|nameMap==null)
			return null;
		
		
		int dmg=nameMap.inverse().get(name);
		
		if (dmg>=nameMap.size())
			return null;
		
		return new ItemStack(id,amount,dmg);
	}
}
