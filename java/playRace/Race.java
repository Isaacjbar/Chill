import java.util.ArrayList;

public class Race {
	public static void main(String[] args) throws InterruptedException {
        ArrayList<String> bus2 = new ArrayList<>();
        
        String[][] bus = {
            {" ", " ", " ", " ", " ", " ", " ", " ", "_", "_", "_", "_", "_", "_", "_", " ", " ", " ", " ", " ", " "},
            {" ", " ", " ", " ", " ", " ", " ", "/", " ", "/","||", " ", " ","\\","\\", " ", " ", " ", " ", " ", " "},
            {" ", " ", "_", "_", "_", "_", "/", " ", "/", "_","||", "_", "_", "_","\\","\\", "_", "_", "_", "_", " "},
            {" ", "/", " ", " ", "_", " ", " ", " ", " ", " ", " ", " ", " ", "_", " ", " ", " ", " ", " ", " ", "("},
            {"|", "_", "_", "/", " ","\\", "_", "_", "_", "_", "_", "_", "/", " ","\\", "_", "_", "_", "_", "_", "|"},
            {" ", " ", " ","\\", "_", "/", " ", " ", " ", " ", " ", " ","\\", "_", "/", " ", " ", " ", " ", " ", " "}
        };
        System.out.println(bus.length);
        
        printBus(bus,20);
        /*Animación del autobús
        for (int i = 0; i < 20; i++) { // El número de iteraciones controla cuánto se mueve
            imprimirAutobus(autobus);
            moverAutobus(autobus); // Añade espacio al inicio para mover
            Thread.sleep(300); // Pausa de 300 ms entre cada movimiento
            System.out.println("\n\n\n\n\n\n"); // Limpia la pantalla entre movimientos
        } */
    }

    public static void printBus(String[][] bus, int meta) throws InterruptedException {
        String distance = " ";
        if(meta==0){
            return;
        }
        for (int i = 0; i < bus.length; i++) {
            //System.out.print(distance);
            for (int j = 0; j < 21; j++) {
                System.out.print(bus[i][j]);
            }
            System.out.println("");
            distance+=" ";
        }

        Thread.sleep(300); // Pausa de 300 ms
        System.out.println("\n\n\n\n\n\n");
        printBus(bus, meta-1);
    }


}
