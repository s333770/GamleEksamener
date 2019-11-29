import java.util.Arrays;

public class V2017 {

    public static void bytt(int[] a, int i, int j)
    {
        int temp = a[i]; a[i] = a[j]; a[j] = temp;
    }


    public static void snu(int[] a){
      for(int v=0, h=a.length-1; v<h;){
          bytt(a,v++,h--);
      }
    }
    public static int finn(int[] a, int verdi){
        int v=0, h=a.length-1;
        if(verdi==a[a.length-1]/2){
            v=(a.length/2)-1;
            System.out.println(v);
            return v;
        }

        while(v<=h){
            int m=(v+h)/2;//Finner midtverdien
            int midtverdi=a[m];
            if(verdi>midtverdi){
                v=m+1;
            }
            else if(verdi<midtverdi){
                h=m-1;
            }
            else break;
        }
        if(v>h){
            return -(v+1);
        }
        while (v>0 && a[v-1]==verdi){
            v--;
        }
        System.out.println(v);
        return v;

    }
    public static void main(String[] args) {
        Kø<Character> kø = new LenketKø<>();
        char[] c = "ABCDEFGHI".toCharArray();
        for (char d : c) kø.leggInn(d);
        System.out.println(kø);                  // [A, B, C, D, E, F, G, H, I]
        int antall = LenketKø.fjernBakerst(kø,5);
        System.out.println(antall + " " + kø);   // 5 [A, B, C, D]
        antall = LenketKø.fjernBakerst(kø,5);
        System.out.println(antall + " " + kø);   // 4 []


    }
}
