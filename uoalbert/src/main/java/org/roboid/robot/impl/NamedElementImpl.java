package org.roboid.robot.impl;

import org.roboid.robot.NamedElement;

/**
 * @author akaii@kw.ac.kr (Kwang-Hyun Park)
 */
abstract class NamedElementImpl implements NamedElement
{
	private String mName = "";
	
	NamedElementImpl()
	{
	}
	
	NamedElementImpl(String name)
	{
		setName(name);
	}
	
	@Override
	public String getName()
	{
		return mName;
	}
	
	@Override
	public void setName(String name)
	{
		mName = (name == null) ? "" : name;
	}
}
