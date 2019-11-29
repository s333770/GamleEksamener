
import java.util.*;

public class LenketKø<T> implements Kø<T>
{
    private static final class Node<T>     // en indre nodeklasse
    {
        private T verdi;
        private Node<T> neste;

        private Node(Node<T> neste)    // nodekonstruktør
        {
            this.verdi = null;
            this.neste = neste;
        }

    } // class Node

    private Node<T> fra;     // den første i køen
    private Node<T> til;     // etter den siste i køen
    private int antall;      // antall i køen

    private static final int START_STØRRELSE = 8;

    public LenketKø(int størrelse)   // konstruktør
    {
        // Hvis størrelse <= 1, blir det en node.
        // Ellers blir antall noder lik størrelse.

        til = fra = new Node<>(null);  // lager den første noden

        Node<T> p = fra;               // en hjelpevariabel
        for (int i = 1; i < størrelse; i++)
        {
            p = new Node<>(p);           // lager resten av nodene
        }
        fra.neste = p;                 // for å få en sirkel

        antall = 0;                    // ingen verdier foreløpig
    }

    public LenketKø()  // standardkonstruktør
    {
        this(START_STØRRELSE);
    }

    @Override
    public boolean leggInn(T verdi)
    {
        til.verdi = verdi;              // legger inn bakerst
        if (til.neste == fra)           // køen vil bli full - må utvides
        {
            til.neste = new Node<>(fra);  // ny node mellom til og fra
        }
        til = til.neste;                // flytter til
        antall++;                       // en ny verdi i køen
        return true;
    }

    @Override
    public T kikk()
    {
        if (tom()) throw new NoSuchElementException("Køen er tom!");

        return fra.verdi;           // returnerer verdien
    }

    @Override
    public T taUt()
    {
        if (tom()) throw new NoSuchElementException("Køen er tom!");

        T tempverdi = fra.verdi;    // tar vare på verdien i fra
        fra.verdi = null;           // nuller innholdet i fra
        fra = fra.neste;            // flytter fra
        antall--;                   // reduserer antallet
        return tempverdi;           // returnerer verdien
    }

    @Override
    public int antall()
    {
        return antall;
    }

    @Override
    public boolean tom()
    {
        return fra == til;  // eller antall == 0
    }

    @Override
    public void nullstill()  // tar vare på en del av nodene
    {
        Node<T> p = fra;
        for (int i = 1; i < START_STØRRELSE; i++)
        {
            p.verdi = null;
            if (p.neste == fra) break;
            p = p.neste;
        }

        Node<T> q = p.neste;
        while (q != fra)
        {
            q.verdi = null;
            q = q.neste;
        }

        p.verdi = null;
        til = p.neste = fra;
        antall = 0;
    }

    @Override
    public String toString()
    {
        StringJoiner s = new StringJoiner(", ", "[", "]");

        Node<T> p = fra;

        for (int i = 0; i < antall; i++, p = p.neste)
        {
            s.add(p.verdi == null ? "null" : p.verdi.toString());
        }

        return s.toString();
    }
    public static <T> int fjernBakerst(Kø<T> kø, int antall){
        if(antall<0){
            throw new IllegalArgumentException("Antall må være et gyldig tall");
        }
        int n=kø.antall();
        if(antall>n){
            kø.nullstill();
        }
        for(int i=n-antall; i>0; i--){
            kø.leggInn(kø.taUt());
        }
        for(int i=0;i<antall;i++){
            kø.taUt();
        }
        return antall;


    }

} // class LenketKø