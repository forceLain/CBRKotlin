package com.forcelain.android.startkotlin.models;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root
public class ValCurs {

    @Attribute(required=false, name = "Date")
    private String date;

    @Attribute(required=false, name = "name")
    private String name;

    @ElementList(inline = true, entry = "Valute")
    public List<Valute> valute;
}
