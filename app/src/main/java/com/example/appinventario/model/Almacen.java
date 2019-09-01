package com.example.appinventario.model;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

import java.util.Hashtable;

public class Almacen implements KvmSerializable {

    private int IdAlmacen;
    private String Alm_Descrip;

    public Almacen (int idAlmacen, String alm_Descrip) {

        this.setIdAlmacen(idAlmacen);
        this.setAlm_Descrip(alm_Descrip);

    }

    public Almacen () {

        this.setIdAlmacen(0);
        this.setAlm_Descrip("");

    }


    public int getIdAlmacen() {
        return IdAlmacen;
    }

    public void setIdAlmacen(int idAlmacen) {
        IdAlmacen = idAlmacen;
    }

    public String getAlm_Descrip() {
        return Alm_Descrip;
    }

    public void setAlm_Descrip(String alm_Descrip) {
        Alm_Descrip = alm_Descrip;
    }

    @Override
    public Object getProperty(int index) {

        switch(index)
        {
            case 0:
                return IdAlmacen;
            case 1:
                return Alm_Descrip;

        }
        return null;
    }

    @Override
    public int getPropertyCount() {
        return 1;
    }

    @Override
    public void setProperty(int index, Object value) {

        switch(index)
        {
            case 0:
                IdAlmacen = Integer.parseInt(value.toString());
                break;
            case 1:
                Alm_Descrip = value.toString();
                break;
            default:
                break;
        }

    }

    @Override
    public void getPropertyInfo(int index, Hashtable properties, PropertyInfo info) {

        switch(index)
        {
            case 0:
                info.type = PropertyInfo.INTEGER_CLASS;
                info.name = "IdAlmacen";
                break;
            case 1:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "Alm_Descrip";
                break;

            default:break;
        }

    }
}
