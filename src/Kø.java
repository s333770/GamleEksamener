////////////////// interface Kø  //////////////////////////////
public interface Kø<T>           // eng: Queue
{
    public boolean leggInn(T t);   // eng: offer/push  legger inn bakerst
    public T kikk();               // eng: peek        ser på det som er først
    public T taUt();               // eng: poll/pop    tar ut det som er først
    public int antall();           // eng: size        antall i køen
    public boolean tom();          // eng: isEmpty     er køen tom?
    public void nullstill();       // eng: clear       tømmer køen

} // interface Kø