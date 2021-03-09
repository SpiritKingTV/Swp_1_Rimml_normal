import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.Scanner;




public class readCompanies {

    Scanner companiesRead;
    Scanner Length;
    public int length = 10;
    public int sLength;
    public String[] dataCompanies = new String[4];

    readCompanies(File companies) throws FileNotFoundException {
        companiesRead = new Scanner(companies);
        Length = new Scanner(companies);



    }
    public void rLenght(){
        if(Length.hasNext())
            sLength = Length.nextInt();
        Length.close();
    }

    public String[] read() {
        if (companiesRead.hasNext()) {
             length = companiesRead.nextInt();

            System.out.println(length);
            for (int i = 0; i < 4; i++) {
                dataCompanies[i] = companiesRead.next();





            }
            return dataCompanies;
        }
        return null;
    }
    public void close(){
        companiesRead.close();
    }

}
