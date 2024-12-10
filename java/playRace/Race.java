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
        printBus(bus, car,"","");
    }

    public static void printBus(String[][] bus, String[][] car, String distanceO, String distanceS) throws InterruptedException {
        if(distanceO.length()>=102 && distanceS.length()>=102){
            System.out.println("Empate");
            return;
        }
        if(distanceS.length() >= 102){
            System.out.println(distanceS.length()>=102?"Gano Monster :), "+distanceS.length():"");
            return;
        }
        if(distanceO.length() >= 102){
            System.out.println(distanceO.length()>=102?"Gano Redbull :v, "+distanceO.length():"");
            return;
        }

        Thread.sleep(70);
        System.out.print("\033[H\033[2J");

        int rmO = (int)(Math. random()*8+1);
        for (int i = 0; i < rmO; i++) {
            distanceO +=" ";
        }
        int rmS = (int)(Math. random()*8+1);
        for (int i = 0; i < rmS; i++) {
            distanceS +=" ";
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
    
        printBus(bus, car, distanceO+=" ", distanceS+=" ");
    }

}
