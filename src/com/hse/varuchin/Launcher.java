package com.hse.varuchin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Valery on 13/12/2016.
 */
public class Launcher {
    private static final String START_MESSAGE = "Hello. Please, enter method number you want: \n" +
            "1) Take money only from first envelope\n" +
            "2) Take money only from second envelope\n" +
            "3) Take money using the way to conversion envelopes\n" +
            "4) Perform all methods and show results ";
    private static final String AMOUNT_OF_ITERATIONS = "Enter amount of iterations: ";
    private static final String NUMBER_FORMAT_ERROR = "Invalid input. Make sure you enter correct number";
    private static final String METHOD_ID = "Choose method (enter number): ";

    public static void main(String[] args) throws IOException, InterruptedException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        getLogger().log(Level.INFO, START_MESSAGE);
        int methodId = getMethod(bufferedReader);
        int iterationsAmount = getIterationsAmount(bufferedReader);

        EnvelopeTask envelopeTask = new EnvelopeTask(iterationsAmount, methodId);

        envelopeTask.performTask();
    }

    private static int getMethod(BufferedReader bufferedReader) throws IOException {
        int methodId;
        getLogger().log(Level.INFO, METHOD_ID);
        String input = bufferedReader.readLine();
        try {
            methodId = Integer.valueOf(input);
            if (methodId == 0 || methodId > 4) {
                System.err.println("Use correct method number!");
                methodId = getMethod(bufferedReader);
            }
        } catch (NumberFormatException e) {
            getLogger().log(Level.WARNING, NUMBER_FORMAT_ERROR);
            methodId = getMethod(bufferedReader);
        }

        return methodId;
    }

    private static int getIterationsAmount(BufferedReader bufferedReader) throws IOException {
        int amountOfIterations = 0;
        getLogger().log(Level.INFO, AMOUNT_OF_ITERATIONS);
        String input = bufferedReader.readLine();
        try {
            amountOfIterations = Integer.valueOf(input);
        } catch (NumberFormatException e) {
            getLogger().log(Level.WARNING, NUMBER_FORMAT_ERROR);
            amountOfIterations = getIterationsAmount(bufferedReader);
        }

        return amountOfIterations;
    }

    private static Logger getLogger() {
        return Logger.getAnonymousLogger();
    }
}
