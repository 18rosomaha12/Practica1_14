
public class Practica1_1 {
    public static void main(String[] args) {
        System.out.println("Практическое задание № 1.14, РИБО-03-22, Тувыкин Михаил Денисович, Вариант №4");
        Glav glav=new Glav();
        Pot1 pot1 = new Pot1(glav);
        Pot2 pot2 = new Pot2(glav);
        new Thread(pot1).start();
        new Thread(pot2).start();
    }
}
class Glav{
    private int ch=0;
    public synchronized void get() {
        while (ch<1) {
            try {
                wait();
            }
            catch (InterruptedException e) {
            }
        }
        ch--;
        System.out.println("Thread-0");
        notify();
    }
    public synchronized void put() {
        while (ch>=1) {
            try {
                wait();
            }
            catch (InterruptedException e) {
            }
        }
        ch++;
        System.out.println("Thread-1");
        notify();
    }
}
class Pot1 implements Runnable{

    Glav glav;
    Pot1(Glav glav){
        this.glav=glav;
    }
    public void run(){
        for (int i = 1; i < 10000; i++) {
            glav.put();
        }
    }
}
class Pot2 implements Runnable{

    Glav glav;
    Pot2(Glav glav){
        this.glav=glav;
    }
    public void run(){
        for (int i = 1; i < 10000; i++) {
            glav.get();
        }
    }
}