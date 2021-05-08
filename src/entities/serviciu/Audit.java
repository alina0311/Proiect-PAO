package entities.serviciu;

import entities.CabinetMedical;
import entities.Programare;
import entities.persoana.Pacient;
import entities.persoana.angajat.Angajat;
import entities.persoana.angajat.Asistent;
import entities.persoana.angajat.Medic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.*;

public class Audit {

    public static Audit audit;
    static CabinetMedical c = CabinetMedical.getCabinet();

    private Audit(){}

    public static Audit getCP(){
        if (audit == null)
            audit = new Audit();
        return audit;
    }

    public void actiune(String action){
        System.out.println("in act");
        Path calea = Paths.get("src/csv_files/Audit.csv");
        File file = new File("src/csv_files/Audit.csv");
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter csvWriter = new FileWriter("src/csv_files/Audit.csv", true);

            if (file.length() == 0) {
                csvWriter.append("NumeActiune");
                csvWriter.append(",");
                csvWriter.append("Data&Ora");
                csvWriter.append("\n");
            }
            Date date = Calendar.getInstance().getTime();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String timestamp = dateFormat.format(date);

            List<String> a = Arrays.asList(action, timestamp);
            csvWriter.append(String.join(",", a));
            csvWriter.append("\n");


            csvWriter.flush();
            csvWriter.close();
        }
        catch(IOException e) {
            e.printStackTrace();
        }


    }

}
