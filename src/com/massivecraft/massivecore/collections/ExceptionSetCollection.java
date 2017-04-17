package com.massivecraft.massivecore.collections;

import com.massivecraft.massivecore.util.MUtil;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

public class ExceptionSetCollection<E extends Collection>
{
	// -------------------------------------------- //
	// FIELDS
	// -------------------------------------------- //
	
	private boolean standard = true;
	public boolean isStandard() { return this.standard; }
	public void setStandard(boolean standard) { this.standard = standard; }
	
	private E exceptions;
	public E getExceptions() { return this.exceptions; }
	public void setExceptions(E exceptions) { this.exceptions = exceptions; }
	
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public ExceptionSetCollection(E collection)
	{
		// Explicitly specify last argument as null to avoid overloading conflict
		this(collection, true, (E)null);
	}

	public ExceptionSetCollection(E collection, boolean standard)
	{
		// Explicitly specify last argument as null to avoid overloading conflict
		this(collection, standard, (E)null);
	}
	
	@SuppressWarnings("unchecked")
	public ExceptionSetCollection(E collection, boolean standard, Object... exceptions)
	{
		this.exceptions = collection;
		this.standard = standard;
		if (exceptions == null || exceptions.length == 0) return;
		this.exceptions.addAll(Arrays.asList(exceptions));
	}
	
	// -------------------------------------------- //
	// CONTAINS
	// -------------------------------------------- //
	
	public boolean contains(Object object)
	{
		if (object == null) return !this.isStandard();
		if (this.getExceptions().contains(object)) return !this.isStandard();
		return this.isStandard();
	}
	
	// -------------------------------------------- //
	// IS EMPTY
	// -------------------------------------------- //
	
	public boolean isEmpty()
	{
		return !this.isStandard() && this.getExceptions().isEmpty();
	}
	
	// -------------------------------------------- //
	// EQUALS & HASH CODE
	// -------------------------------------------- //
	
	@Override
	public boolean equals(Object object)
	{
		if (!(object instanceof ExceptionSetCollection)) return false;
		ExceptionSetCollection that = (ExceptionSetCollection)object;
		
		return MUtil.equals(
			this.isStandard(), that.isStandard(),
			this.getExceptions(), that.getExceptions()
		);
	}
	
	@Override
	public int hashCode()
	{
		return Objects.hash(
			this.isStandard(),
			this.getExceptions()
		);
	}
	
}
