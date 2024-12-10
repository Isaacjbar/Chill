import java.util.ArrayList;

public class Race {
	public static void main(String[] args) throws InterruptedException {
        ArrayList<String> bus2 = new ArrayList<>();
        
        String[][] bus = {
            {" ", " ", " ", " ", " ", " ", " ", " ", "_", "_", "_", "_", "_", "_", "_", " ", " ", " ", " ", " ", " "},
            {" ", " ", " ", " ", " ", " ", " ", "/", " ", "/","||", " ", " ","\\","\\", " ", " ", " ", " ", " ", " "},
            {" ", " ", "_", "_", "_", "_", "/", " ", "/", "_","||", "_", "_", "_","\\","\\", "_", "_", "_", "_", " "},
            {" ", "/", " ", " ", "_", " ", "REDBULL", "_", " ", " ", " ", " ", " ", " ", "("," ", " "," ", " "," "," ",},
            {"|", "_", "_", "/", " ","\\", "_", "_", "_", "_", "_", "_", "/", " ","\\", "_", "_", "_", "_", "_", "|"},
            {" ", " ", " ","\\", "_", "/", " ", " ", " ", " ", " ", " ","\\", "_", "/", " ", " ", " ", " ", " ", " "}
        };
        String[][] car = {
            {" ", " ", " ", " ", " ", " ", " ", " ", "_", "_", "_", "_", "_", "_", "_", " ", " ", " ", " ", " ", " "},
            {" ", " ", " ", " ", " ", " ", " ", "/", " ", "/","||", " ", " ","\\","\\", " ", " ", " ", " ", " ", " "},
            {" ", " ", "_", "_", "_", "_", "/", " ", "/", "_","||", "_", "_", "_","\\","\\", "_", "_", "_", "_", " "},
            {" ", "/", " ", " ", "_", "MONSTER", " ","_", " ", " ", " ", " ", " ", " ", "("," ", " ", " ", " "," "," ",},
            {"|", "_", "_", "/", " ","\\", "_", "_", "_", "_", "_", "_", "/", " ","\\", "_", "_", "_", "_", "_", "|"},
            {" ", " ", " ","\\", "_", "/", " ", " ", " ", " ", " ", " ","\\", "_", "/", " ", " ", " ", " ", " ", " "}
        };
        
        printBus(bus, car, 120,"","");
    }

    public static void printBus(String[][] bus, String[][] car, int meta, String distanceO, String distanceS) throws InterruptedException {
        if(meta==0){
            return;
        }
        if(distanceO.length() == 40 || distanceS.length() == 40){
            return;
        }
        for (int i = 0; i < bus.length; i++) {
            System.out.print(distanceO);
            for (int j = 0; j < 21; j++) {
                System.out.print(bus[i][j]);
            }
            System.out.println("");
        }

        System.out.println("");
        
        for (int i = 0; i < car.length; i++) {
            System.out.print(distanceS);
            for (int j = 0; j < 21; j++) {
                System.out.print(car[i][j]);
            }
            System.out.println("");
        }
        
        Thread.sleep(100);
        System.out.print("\033[H\033[2J");
        printBus(bus, car, meta-1, distanceO+=" ", distanceS+="   ");
    }

}
