import java.util.Scanner;

class ConsoleColors {
    // Reset
    public static final String RESET = "\033[0m";  // Text Reset

    // Regular Colors
    public static final String BLACK = "\033[0;30m";   // BLACK
    public static final String RED = "\033[0;31m";     // RED
    public static final String GREEN = "\033[0;32m";   // GREEN
    public static final String YELLOW = "\033[0;33m";  // YELLOW



}





class Lock {
    public static enum Signal
    {
        GREEN,YELLOW,RED;
    }


     static volatile Signal previous=Signal.YELLOW;
     Signal signal;
}


class Green extends Thread {

    Lock l ;
    public Green( Lock l ){
        this.l = l ;
    }

    @Override
    public void run() {
        try {
            synchronized (l){
                while (true){
                    if (l.previous != Lock.Signal.RED) {
                        l.wait();
                    }
                    System.out.println(ConsoleColors.GREEN + "● " + ConsoleColors.RESET + "● " + "●"  );
                    l.previous = Lock.Signal.GREEN ;
                    Thread.sleep(5000);
                    l.notify();
                }
            }
        }catch (Exception e){
            System.out.println(e);
        }

    }
}

class Yello extends Thread {

    Lock l ;
    public Yello( Lock l ){
        this.l = l ;
    }

    @Override
    public void run() {
        try {
            synchronized (l){
                while (true){
                    if (l.previous != Lock.Signal.GREEN) {
                        l.wait();
                    }
                    System.out.println("● " + ConsoleColors.YELLOW+  "● " +ConsoleColors.RESET + "●"  );
                    l.previous = Lock.Signal.YELLOW ;
                    Thread.sleep(2000);
                    l.notify();
                }
            }
        }catch (Exception e){
            System.out.println(e);
        }

    }
}
 class Red extends Thread {

    Lock l ;
    public Red( Lock l ){
        this.l = l ;
    }

    @Override
    public void run() {
        try {
            synchronized (l){
                while (true){
                    if (l.previous != Lock.Signal.YELLOW) {
                        l.wait();
                    }
                    System.out.println("● " +  "● " +ConsoleColors.RED + "●"+ConsoleColors.RESET  );
                    l.previous = Lock.Signal.RED ;
                    Thread.sleep(5000);
                    l.notify();
                }
            }
        }catch (Exception e){
            System.out.println(e);
        }

    }
}



public class main {




    public static void main(String[] args) {

        Lock l =  new Lock() ;
        Green g = new Green(l);
        Yello y =  new Yello(l) ;
        Red r = new Red(l) ;
        g.start();
        y.start();
        r.start();
        String s = "" ;
        Scanner scanner = new Scanner(System.in) ;

        while (true){

            s = scanner.nextLine();
        if (s.equals("stop")){
            System.exit(0);
        }
        }







    }
}