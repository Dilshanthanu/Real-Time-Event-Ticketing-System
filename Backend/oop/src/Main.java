//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
            String x ="hello world";

            int count = 0;
            for (int i = 0; i < x.length(); i++) {
                count++;
            }
            System.out.println(count);

        System.out.println(x.charAt(2));

        String y = x.substring(1,6);
        System.out.println(y);

        System.out.println(x.toUpperCase());

        for (int i = 0; i < x.length(); i++) {
            if (x.charAt(i) != ' ') {
                System.out.print(x.charAt(i));
            }else {
                continue;
            }
        }
        String [] words = x.split(" ");
        for (int i = 0; i < words.length; i++) {
            System.out.println(words[i]);
        }
        String New= "Dilshan Thanushka";
        String modify;
        for (int i = 0; i < words.length; i++) {
         modify =  x.replace(x.charAt(i) , New.charAt(i));
        }
        System.out.println(modify);
        }

    }
