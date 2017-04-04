package com.massivecraft.massivecore.command.type.factory;

import com.massivecraft.massivecore.command.type.Type;

public interface FactoryType
{
	// Is it safe for element to be put through createType?
	boolean isApplicable(Object element);
	
	// This will never return null.
	// NOTE: element is assummed to have passed isApplicable.
	<E> Type<E> createType(Object element);
	
}
