package com.example.appinventario.model;


import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

import java.util.Hashtable;

public class Empleado implements KvmSerializable {

    public int IdEmpleado;
    public String Emp_Nombre;
    public String Emp_Login;
    public String Emp_Password;


    public Empleado (int idEmpleado, String emp_Nombre, String emp_Login, String emp_Password) {

        this.IdEmpleado = idEmpleado;
        this.Emp_Nombre = emp_Nombre;
        this.Emp_Login = emp_Login;
        this.Emp_Password = emp_Password;
    }

    public Empleado() {

        IdEmpleado = 0;
        Emp_Nombre = "";
        Emp_Login = "";
        Emp_Password = "";
    }


    @Override
    public Object getProperty(int index) {

        switch(index)
        {
            case 0:
                return IdEmpleado;
            case 1:
                return Emp_Nombre;
            case 2:
                return Emp_Login;
            case 3:
                return Emp_Password;
        }
        return null;
    }

    @Override
    public int getPropertyCount() {
        return 4;
    }

    @Override
    public void setProperty(int index, Object value) {

        switch(index)
        {
            case 0:
                IdEmpleado = Integer.parseInt(value.toString());
                break;
            case 1:
                Emp_Nombre = value.toString();
                break;
            case 2:
                Emp_Login = value.toString();
                break;
            case 3:
                Emp_Password = value.toString();
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
                info.name = "IdEmpleado";
                break;
            case 1:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "Emp_Nombre";
                break;
            case 2:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "Emp_Login";
                break;
            case 3:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "Emp_Password";
                break;
            default:break;
        }

    }
}
