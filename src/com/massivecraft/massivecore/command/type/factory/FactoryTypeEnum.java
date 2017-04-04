package com.massivecraft.massivecore.command.type.factory;

import com.massivecraft.massivecore.command.type.Type;
import com.massivecraft.massivecore.command.type.enumeration.TypeEnum;

public class FactoryTypeEnum implements FactoryType
{
	// -------------------------------------------- //
	// INSTANCE
	// -------------------------------------------- //
	
	private static FactoryTypeEnum i = new FactoryTypeEnum();
	public static FactoryTypeEnum get() { return i; }
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public boolean isApplicable(Object element)
	{
		if (!(element instanceof Class)) return false;
		return ((Class<?>)element).isEnum();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <E> Type<E> createType(Object element)
	{
		return new TypeEnum((Class)element);
	}
	
}
