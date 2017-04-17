package com.massivecraft.massivecore.command.type.container;

import com.massivecraft.massivecore.collections.ExceptionSetCollection;
import com.massivecraft.massivecore.command.editor.Property;
import com.massivecraft.massivecore.command.editor.PropertyReflection;
import com.massivecraft.massivecore.command.type.Type;
import com.massivecraft.massivecore.command.type.TypeReflection;
import com.massivecraft.massivecore.util.ReflectionUtil;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;

public class TypeExceptionSetCollection<E extends Collection> extends TypeReflection<ExceptionSetCollection>
{
	// -------------------------------------------- //
	// INSTANCE & CONSTRUCT
	// -------------------------------------------- //
	
	public static <E extends Collection> TypeExceptionSetCollection get(Type<E> innerType) { return new TypeExceptionSetCollection(innerType); }
	
	public <E extends Collection> TypeExceptionSetCollection(Type<E> innerType)
	{
		// Exclude "exceptions" from automatic handling
		// because it is a raw type when TypeReflection looks at it
		// and thus, the inner type cannot be automatically determined.
		super(ExceptionSetCollection.class, "exceptions");
		this.setInnerType(innerType);
		
		// We'll handle the exceptions field here with the correct inner type.
		List<Property<ExceptionSetCollection, ?>> properties = this.getInnerProperties();
		Field exceptionsField = ReflectionUtil.getField(ExceptionSetCollection.class, "exceptions");
		properties.add(new PropertyReflection<>(this, innerType, exceptionsField));
	}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	@SuppressWarnings("unchecked")
	public ExceptionSetCollection<E> createNewInstance()
	{
		return new ExceptionSetCollection((Collection) this.getInnerType().createNewInstance());
	}
	
}
