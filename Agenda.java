/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.util.*;

/**
 *
 * @author Sylvain
 */
public class Agenda {
    public ArrayList<Contacto> contactos;
    public String opciones[];
    Scanner sc;

    
    public Agenda(){
        contactos = new ArrayList<>();
        sc = new Scanner(System.in);
        
        opciones = new String[7];        
        opciones[0] = "1.- Mostrar Contactos";
        opciones[1] = "2.- Buscar Por Nombre";
        opciones[2] = "3.- Añadir Contacto";
        opciones[3] = "4.- Modificar Contacto";
        opciones[4] = "5.- Eliminar Contacto";
        opciones[5] = "6.- Vaciar Agenda";
        opciones[6] = "7.- Salir";      
    }
    
    public void MostrarContactos(){   
        System.out.println("*********************************");
        System.out.println("************CONTACTOS************");
        System.out.println("*********************************");   
        if(contactos.size() > 0){
            contactos.forEach((contacto) -> 
                System.out.printf("*%-20s *%-9s*\n", contacto.GetNombre(), contacto.GetTelefono())); 
        }else{
            System.out.println("* No hay contactos registrados  *");
        }               
        System.out.println("*********************************");
        PressEnter();
    }
    
    public void BuscarNombre(){
        boolean seguir = true;
        int count;
        
        do{
            count = 0;
            System.out.print("Introduzca un nombre para realizar una busqueda: ");
            String busqueda = sc.nextLine();
            
            System.out.println("\nContactos relacionados:");
            System.out.println("*********************************"); 
            for (int i = 0; i < contactos.size(); i++) {
                if(contactos.get(i).GetNombre().contains(busqueda)){
                    System.out.printf("*%-20s *%-9s*\n", contactos.get(i).GetNombre(), contactos.get(i).GetTelefono()); 
                    count++;
                }
            }
            
            if(count == 0){
                System.out.println("No se han encontrado resultados para la busqueda <" + busqueda +">");
            }
            System.out.println("*********************************"); 
            
            System.out.print("\nQuiere hacer otra busqueda? (y/n): ");
            String respuesta = sc.nextLine();
            
            seguir = !(respuesta.equals("n") || respuesta.equals("N"));
        }while(seguir);        
    }
    
    public void AddContacto(){        
        boolean validName = true;
        boolean validPhone = true;
        boolean uniqueName = true;
        String nombre = "";        
        String telefono = "";
        
        //Bucle para pedir el nombre
        do{
            if(!validName){
                System.out.println("Debe introducir un nombre valido.");
            }
            
            if(!uniqueName){
                System.out.println("El nombre introducido <" + nombre + "> ya se encuentra en la agenda");
            }
            System.out.print("Nombre (Max.20 caracteres): ");
            nombre = sc.nextLine();
            
            uniqueName = true;
            
            //Comprueba que el nombre introducido no esta vacio ni tiene mas de 20 caracteres
            if(nombre.isEmpty() || nombre.length() > 20){
                validName = false;
            }else{
                validName = true;
                
                //Comprueba que el nombre no se encuentre ya en la agenda
                for (int i = 0; i < contactos.size(); i++) {
                    if(contactos.get(i).GetNombre().equals(nombre)){
                        uniqueName = false;      
                    }
                }
            }
        }while(!validName || !uniqueName);
        
        
        //Bucle para pedir el telefono
        do{
            if(!validPhone){
                System.out.println("Debe introducir un numero de telefono valido");
            }
            System.out.print("Telefono (9 caracteres numericos): ");
            telefono = sc.nextLine();
            
            //comprueba que es un numero de telefono valido
            if(telefono.length() == 9){
                validPhone = true;
                for (int i = 0; i < telefono.length(); i++) {
                    char x = telefono.charAt(i);
                    if(x != '0' &&
                       x != '1' &&
                       x != '2' &&
                       x != '3' &&
                       x != '4' &&
                       x != '5' &&
                       x != '6' &&
                       x != '7' &&
                       x != '8' &&
                       x != '9'){
                        validPhone = false;
                    }
                }
            }else{
                validPhone = false;
            }            
        }while(!validPhone);
        
        //Se añade el contacto
        contactos.add(new Contacto(nombre, telefono));
        
        //Ordena la lista alfabeticamente por el nombre de cada contacto
        Collections.sort(contactos, (Contacto c1, Contacto c2) -> c1.GetNombre().compareTo(c2.GetNombre()));
        
        //Muestra datos del contacto añadido
        System.out.println("\nSe ha añadido el contacto con la siguiente informacion correctamente:");
        System.out.println("Nombre --> " + nombre);
        System.out.println("Telefono --> " + telefono);        
        PressEnter();                   
    }  
    
    public void EditContacto(){
        boolean found = false;
        System.out.print("Introduzca el nombre del contacto que desea modificar: ");
        String contacto = sc.nextLine();
        String nombre = "";
        String telefono = "";
        
        for (int i = 0; i < contactos.size(); i++) {
            if(contactos.get(i).GetNombre().equals(contacto)){
                found = true;
                
                System.out.println("Nombre --> " + contactos.get(i).GetNombre());
                System.out.println("Telefono --> " + contactos.get(i).GetTelefono());
                System.out.println("Desea modificar los datos de este contacto? (y/n): ");
                
                String respuesta = sc.nextLine();
                
                if(respuesta.equals("y") || respuesta.equals("Y")){
                    boolean validName = true;
                    boolean validPhone = true;
                    boolean uniqueName = true;                    

                    //Bucle para pedir el nombre
                    do {
                        if (!validName) {
                            System.out.println("Debe introducir un nombre valido.");
                        }

                        if (!uniqueName) {
                            System.out.println("El nombre introducido <" + nombre + "> ya se encuentra en la agenda");
                        }
                        System.out.print("Nombre (Max.20 caracteres): ");
                        nombre = sc.nextLine();

                        uniqueName = true;

                        //Comprueba que el nombre introducido no esta vacio ni tiene mas de 20 caracteres
                        if (nombre.isEmpty() || nombre.length() > 20) {
                            validName = false;
                        } else {
                            validName = true;

                            //Comprueba que el nombre no se encuentre ya en la agenda
                            for (int j = 0; j < contactos.size(); j++) {
                                if (contactos.get(j).GetNombre().equals(nombre)) {
                                    uniqueName = false;
                                }
                            }
                        }
                    } while (!validName || !uniqueName);

                    //Bucle para pedir el telefono
                    do {
                        if (!validPhone) {
                            System.out.println("Debe introducir un numero de telefono valido");
                        }
                        System.out.print("Telefono (9 caracteres numericos): ");
                        telefono = sc.nextLine();

                        //comprueba que es un numero de telefono valido
                        if (telefono.length() == 9) {
                            validPhone = true;
                            for (int j = 0; j < telefono.length(); j++) {
                                char x = telefono.charAt(j);
                                if (x != '0'
                                        && x != '1'
                                        && x != '2'
                                        && x != '3'
                                        && x != '4'
                                        && x != '5'
                                        && x != '6'
                                        && x != '7'
                                        && x != '8'
                                        && x != '9') {
                                    validPhone = false;
                                }
                            }
                        } else {
                            validPhone = false;
                        }
                    } while (!validPhone);
                    
                    contactos.get(i).SetNombre(nombre);
                    contactos.get(i).SetTelefono(telefono);

                    System.out.println("Se ha modificado el contacto satisfactoriamente");
                    System.out.println("Datos Nuevos:");
                    System.out.println("Nombre --> " + nombre);
                    System.out.println("Telefono --> " + telefono);
                }                                   
            }
        }     
        
        if(!found){
            System.out.println("No se ha encontrado a ningun contacto con ese nombre");
        }      
        
        PressEnter();
    }
    
    public void EliminarContacto(){
        boolean found = false;
        System.out.print("Introduzca el nombre del contacto que desea eliminar: ");
        String nombre = sc.nextLine();
        
        for (int i = 0; i < contactos.size(); i++) {
            if(contactos.get(i).GetNombre().equals(nombre)){
                found = true;
                System.out.println("Nombre --> " + contactos.get(i).GetNombre());
                System.out.println("Telefono --> " + contactos.get(i).GetTelefono());
                System.out.print("Desea eliminar este contacto? (y/n): ");
                String respuesta = sc.nextLine();
                
                if(respuesta.equals("Y") || respuesta.equals("y")){
                    contactos.remove(i);
                    System.out.println("Se ha eliminado el contacto satisfactoriamente");
                }
            }
        }
        
        if(!found){
            System.out.println("No se ha encontrado a ningún contacto con el nombre <" + nombre + ">");
        }
        
        PressEnter();
    }
    
    public void VaciarAgenda(){
        System.out.print("Actualmente hay " + contactos.size() + " contacto/s registrados en la agenda,\n"
                + "Esta de acuerdo con vaciarla entera? (y/n): ");
        
        String respuesta = sc.nextLine();
        
        if(respuesta.equals("Y") || respuesta.equals("y")){
            contactos.clear();
            System.out.println("La agenda ha quedado vacia, contactos actuales: <" + contactos.size() + ">");
        }else{
            System.out.println("Finalmente la agenda no se ha vaciado.");
        }
        
        PressEnter();      
    }
    
    //Menu Methods 
    
    public void MostrarMenu(){
        for (String opcion : opciones) {
            System.out.println(opcion);
        }
    } 
    
    public boolean LeeryEjecutar(){
        
        boolean exit = false;
        int opt;
        
        do{       
            opt = -1;
            MostrarMenu();
            try{
                System.out.print("\nSeleccione una opcion: "); 
                opt = Integer.parseInt(sc.nextLine());
            }
            catch(NumberFormatException e){
                System.out.println("\nDebe introducir el numero de la opcion que desee realizar");
            }           
        }while(opt < 0 || opt > 7);
        
        switch(opt){
            case 1:
                MostrarContactos();
                break;
            case 2:
                BuscarNombre();
                break;
            case 3:
                AddContacto();
                break;
            case 4:
                EditContacto();
                break;
            case 5:
                EliminarContacto();
                break;
            case 6:
                VaciarAgenda();
                break;
            case 7:
                exit = true;
                break;                
        }        
        
        return exit;       
    }
    
    public void PressEnter() {
        System.out.println("\nPulse ENTER para continuar ...");
        try {
            System.in.read();
        } catch (IOException e) {
        }
    }
    
    /**
     * 
     * Main Function
     * @param args 
     */
    public static void main(String[] args) {
        Agenda agenda = new Agenda();
        boolean exit;
        
        do{
            exit = agenda.LeeryEjecutar();
        }while(!exit);       
    }   
}
