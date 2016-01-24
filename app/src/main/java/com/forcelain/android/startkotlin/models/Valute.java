package com.forcelain.android.startkotlin.models;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root
public class Valute {
    @Attribute(required=false, name = "ID")
    public String id;

    @Element(name="NumCode", required = false)
    public String numCode;

    @Element(name="CharCode", required = false)
    public String charCode;

    @Element(name="Nominal", required = false)
    public String nominal;

    @Element(name="Name", required = false)
    public String name;

    @Element(name="Value", required = false)
    public String value;
}
