package PilatesPulse.Test;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {try {
        // Define the command to run the Python script
        String pythonScriptPath = "C:\\java\\GestionPhyAct\\Library\\RecomIA.py";
        String new_sexe = "Femme";
        int new_age = 30;
        int new_height = 170;
        int new_weight = 65;
        String[] cmd = {
                "C:\\java\\GestionPhyAct\\Library\\dist\\RecomIA.exe",
                pythonScriptPath,
                String.valueOf(new_sexe),
                String.valueOf(new_age),
                String.valueOf(new_height),
                String.valueOf(new_weight)
        };
        ProcessBuilder processBuilder = new ProcessBuilder(cmd);

        Process process = processBuilder.start();

        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println("Python Output: " + line);
        }

        int exitCode = process.waitFor();
        System.out.println("Python script exited with code " + exitCode);

    } catch (IOException | InterruptedException e) {
        e.printStackTrace();
    }}

}
