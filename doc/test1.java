public class test1 {
    public static void main(String[] args) {
        int a=0;
        int b=a;
        a=2;
        System.out.println(a);
        System.out.println(b);

        String c="c";
        String d=c;
        c="c1";
        System.out.println(c);
        System.out.println(d);

        String e="e";
        String f="e";
        System.out.println(e==f);

        String g=new String("e");
        System.out.println(g==e);

        String h="h";
                int i=1;
        changeState(h,i);
        System.out.println(h);
        System.out.println(i);

        short s1=1;
        float f2=s1;
        float f1=1f;
        System.out.println(s1);
        System.out.println(f2);
        System.out.println("result is"+s1+f1);
        System.out.println(f2);
    }

    private static void changeState(String h,int i) {
        h="g";
        i=2;
    }

}

//
