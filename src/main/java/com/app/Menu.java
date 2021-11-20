package com.app;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.logging.Logger;

/**
 *
 * @author leonardo
 */
public class Menu {

    private final Scanner sc;
    private final Servicios servicios;
    private final Cliente cliente;
    private final DBClientes bClientes;
    
    private static Logger log;

    public Menu() {
        this.sc = new Scanner(System.in);
        servicios = Servicios.GetServicios();
        cliente = new Cliente();
        bClientes = DBClientes.getBClientes();
        
        log = Logger.getLogger(this.getClass().getName());
    }

    public void ShowMenu() {
        System.out.println("Bienvenido (a) acontinuación selecciona tipo de usuario!");
        System.out.println(
                "**********************\n"
                + "** 1) Cliente       **\n"
                + "** 2) Agente        **\n"
                + "**********************");
        int type_user = this.sc.nextInt();

        switch (type_user) {
            case 1:
                this.menuClientes();
                break;
            case 2:
                this.menuAgente();
                break;
            default:
                System.exit(0);
        }

    }

    /**
     * Manu que permite solicitar turno de atención
     */
    public void menuClientes() {

        int opcion = 0;
        String turno = "";
        int consecutivo = 0;

        while (opcion != 8) {
            System.out.println("Selecciona el tipo de servicio que requieres!");
            System.out.println(
                    "*************************\n"
                    + "** 1) Asesoria         **\n"
                    + "** 2) Planeación       **\n"
                    + "** 3) Compra           **\n"
                    + "** 4) Venta4           **\n"
                    + "** 5) PQR              **\n"
                    + "** 6) Hipotecas        **\n"
                    + "** 7) Recuperación     **\n"
                    + "** 8) Salir            **\n"
                    + "*************************");
            opcion = this.sc.nextInt();
            consecutivo++;

            switch (opcion) {
                case 1:
                    valida("Asesoria", consecutivo);
                    break;
                case 2:
                    valida("Planeación", consecutivo);
                    break;
                case 3:
                    valida("Compra", consecutivo);
                    break;
                case 4:
                    valida("Venta", consecutivo);
                    break;
                case 5:
                    valida("PQR", consecutivo);
                    break;
                case 6:
                    valida("Hipotecas", consecutivo);
                    break;
                case 7:
                    valida("Recuperación", consecutivo);
                    break;
                case 8:
                    this.ShowMenu();
                    break;
                default:
                    System.out.println("No hay Servicios!");
                    break;
            }

        }

    }

    /**
     * Menu que permite atender los turnos generados
     */
    public void menuAgente() {
        int opcion = 0;
        while (opcion != 6) {
            System.out.println("Selecciona una de las opciones a continuación:\n"
                    + "*************************************\n"
                    + "** 1) Ver turnos en espera         **\n"      
                    + "** 2) Llamar turno                 **\n"
                    + "** 3) Eliminar turnos              **\n"
                    + "** 4) Cantidad de turno generados  **\n"
                    + "** 5) Ver ultimo turno             **\n"
                    + "** 6) Salir                        **\n"
                    + "*************************************");
            opcion = this.sc.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println(bClientes.show());
                    break;
                case 2:
                    if(bClientes.cola() != 0){
                        System.out.println("Atendiendo: " + bClientes.fisrt());
                        System.out.println("Usuario atendido");
                        habilitar(bClientes.fisrt());
                        bClientes.pop();
                    }else{
                        System.out.println("Actualmente no hay turnos!");
                    }
                    
                    break;
                case 3:
                    bClientes.clean();
                    System.out.println("Turnos eliminados");
                    break;
                case 4:
                    System.out.println("Turnos generados: "+bClientes.cola());
                    break;
                case 5:
                    System.out.println("Ultimo cliente registrado: "+bClientes.last());
                    break;
                default:
                    System.out.println("Opción no valida!");
                    break;
            }

        }
        this.ShowMenu();
    }

    /**
     * Habilitar un servicio despues de haber sido utilizado
     * @param cadena String
     */
    public void habilitar(String cadena){
        servicios.habilitar(cadena);
    }
    
    
    /**
     * Validar disponibilidad del servcio
     * asigna si es valido
     * @param service
     * @param consecutivo
     */
    public void valida(String service, int consecutivo) {
        if (servicios.validar(service)) {
            System.out.println("Asignando cita!");
            
            String cli = this.crearCliente(consecutivo, service);
            bClientes.push(cli);
            
            String[] details = cli.split("-");
            mensaje(details[0], this.cliente.getNombre());
        } else {
            System.out.println("El servicio " + service + " no cuentas con mas citas disponibles!");
        }
    }

    /**
     * Crear la estructura del String del usuario
     * @param consecutivo
     * @param service
     * @return 
     */
    public String crearCliente(int consecutivo, String service) {
        cliente.setServicio(service);
        System.out.println("Ingrese su nombre");
        cliente.setNombre(this.sc.next());
        System.out.println("Ingrese su id");
        cliente.setId(this.sc.nextInt());
        cliente.setFecha(LocalDate.now());
        return "TUR" + consecutivo + "-"+cliente.getServicio()+" "+cliente.getNombre() + " " + cliente.getId() + " " + cliente.getFecha();
    }
    


    /**
     * Mensaje de aprobación y asignación de cita
     * @param turno
     * @param persona
     */
    public void mensaje(String turno, String persona) {
        System.out.println(" ");
        System.out.println(
                "*******************************************\n"
                + "** Cita asignada!                      **\n"
                + "** Turno: "+turno+"                    **\n"
                + "** Usuario: "+persona+"                **\n"
                + "** En un momento un agente lo llamara  **\n"
                + "***************************************");
        System.out.println(" ");
    }
}
