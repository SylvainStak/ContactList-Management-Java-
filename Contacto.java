/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sylvain
 */
public class Contacto {
    private String Nombre;
    private String Telefono;
    
    public Contacto(String N, String T){
        this.Nombre = N;
        this.Telefono = T;
    }
    
    //Getters & Setters
    public String GetNombre(){
        return this.Nombre;
    }
    
    public String GetTelefono(){
        return this.Telefono;
    }
    
    public void SetNombre(String N){
        this.Nombre = N;
    }
    
    public void SetTelefono(String T){
        this.Telefono = T;
    }
}
