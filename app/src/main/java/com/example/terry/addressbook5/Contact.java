package com.example.terry.addressbook5;

/**
 * Created by Terry on 4/6/2017.
 */

class Contact implements java.io.Serializable {

    public String _name;
    public String _number;
    public String _email;
    public String _street;
    public String _cityStateZip;

    public Contact(String name, String number, String email, String street, String cityStateZip){

        _name = name;
        _number = number;
        _email = email;
        _street = street;
        _cityStateZip = cityStateZip;
    }

    public String getName(){
        return _name;
    }

    public String getEmail(){
        return _email;
    }

    public String getNumber(){
        return _number;
    }

    public String getStreet(){
        return _street;
    }

    public String getCityStateZip(){
        return _cityStateZip;
    }
}
