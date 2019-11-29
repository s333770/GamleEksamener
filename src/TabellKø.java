////////////////// class TabellKø //////////////////////////////
import java.util.NoSuchElementException;
import java.util.StringJoiner;

public class TabellKø<T> implements Kø<T>
{
    private T[] a;      // en tabell
    private int fra;    // posisjonen til den første i køen
    private int til;    // posisjonen til første ledige plass

    // Køen består av det sirkelformede halvåpne intervallet a[fra:til>

    /**
     * Hjelpemetode som "utvider" tabellen
     * @param størrelse er størrelsen til ny tabell
     * @return referanse til den nye tabellen
     */
    private T[] utvidTabell(int størrelse)
    {
        @SuppressWarnings("unchecked")    // pga. konverteringen: Object[] -> T[]
                T[] b = (T[])new Object[størrelse];  // ny tabell

        // kopierer intervallet a[fra:a.length> over i b
        System.arraycopy(a,fra,b,0,a.length - fra);

        // kopierer intervallet a[0:fra> over i b
        System.arraycopy(a,0,b,a.length - fra, fra);

        fra = 0; til = a.length;
        return b;
    }

    /**
     * Konstruktør som oppretter den interne tabellen. Den må
     * ha en størrelse på minst 1
     * @param størrelse er ønsket størrelse på den interne tabellen
     */
    @SuppressWarnings("unchecked")   // pga. konverteringen: Object[] -> T[]
    public TabellKø(int størrelse)
    {
        if (størrelse < 1)
            throw new IllegalArgumentException("Må ha størrelse > 0!");

        a = (T[]) new Object[størrelse];  // oppretter tabellen
        fra = til = 0;                    // a[fra:til> er tom
    }

    /**
     * Standardkonstruktør som oppretter en intern tabell med lengde 8
     */
    public TabellKø()  // standardkonstruktør
    {
        this(8);         // 8 som startstørrelse
    }

    /**
     * Metode som legger inn en ny verdi bakerst i køen
     * @param verdi er den nye verdien
     * @return true
     */
    @Override
    public boolean leggInn(T verdi)
    {
        a[til++] = verdi;                             // ny verdi bakerst
        if (til == a.length) til = 0;                 // hopper til 0
        if (fra == til) a = utvidTabell(2*a.length);  // dobler tabellen
        return true;
    }

    /**
     * Metode som returnerer den første verdien uten å fjerne den.
     * Det kastes et unntak hvis køen er tom.
     * @return den første verdien
     */
    @Override
    public T kikk()
    {
        if (fra == til)  // sjekker om køen er tom
            throw new NoSuchElementException("Køen er tom!");

        return a[fra];
    }

    /**
     * Metode som returnerer (og fjerner) den første verdien.
     * Det kastes et unntak hvis køen er tom.
     * @return den første verdien
     */
    @Override
    public T taUt()
    {
        if (fra == til)  // sjekker om køen er tom
            throw new NoSuchElementException("Køen er tom!");

        T temp = a[fra];                  // tar vare på den første i køen
        a[fra++] = null;                  // nuller innholdet
        if (fra == a.length) fra = 0;     // hopper til 0
        return temp;                      // returnerer den første
    }

    /**
     * Metode som returnerer antall verdier i køen
     * @return antall verdier
     */
    @Override
    public int antall()
    {
        return fra <= til ? til - fra : a.length + til - fra;
    }

    /**
     * Metode som forteller om køen er tom eller ikke
     * @return true hvis køen er tom, false ellers.
     */
    @Override
    public boolean tom()
    {
        return til == fra;
    }

    /**
     * Metode som tømmer køen
     */
    @Override
    public void nullstill()
    {
        while (fra != til)
        {
            a[fra++] = null;
            if (fra == a.length) fra = 0;
        }
    }

    /**
     * Metode som returnerer er en tegnstreng med køens innhold
     * @return en tegnstreng
     */
    @Override
    public String toString()
    {
        StringJoiner s = new StringJoiner(", ", "[", "]");

        for (int i = fra, k = til; i != k; i = (i + 1) % a.length)
        {
            s.add(a[i] == null ? "null" : a[i].toString());
        }

        return s.toString();
    }



}  // TabellKø