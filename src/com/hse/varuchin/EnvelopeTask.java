package com.hse.varuchin;

import java.io.IOException;
import java.util.Random;

/**
 * Created by Valery on 13/12/2016.
 */
public class EnvelopeTask {
    private static final String AMOUNT_OF_MONEY_FIRST_ENVELOPE = "Amount of money in the first envelope: ";
    private static final String AMOUNT_OF_MONET_SECOND_ENVELOPE = "Amount of money in the second envelope: ";
    private static final String NUMBER_OF_SUCCESS_CASES_FIRST_ENVELOPE = "Number of success cases for the first envelope: ";
    private static final String NUMBER_OF_SUCCESS_CASES_SECOND_ENVELOPE = "Number of success cases for the second envelope: ";
    private static final String NUMBER_OF_SUCCESS_CASES_OF_CHANGED_ENVELOPES = "Number of success cases after conversion of envelopes: ";

    private int iterationsAmount;
    private int methodId;

    private static final int MONEY_AMOUNT = 1000;

    private int firstEnvelope;
    private int secondEnvelope;

    public EnvelopeTask(int iterationsAmount, int methodId) throws IOException {
        setMethodId(methodId);
        setIterationsAmount(iterationsAmount);
    }

    public void performTask() throws IOException, InterruptedException {
        showResults(iterationsAmount, methodId);
    }

    private void showResults(int amountOfIterations, int methodId) throws InterruptedException {
        if (methodId == 1) {
            int successAmount = getMoneyAmountFromFirstEnvelope(amountOfIterations);
            System.err.println(NUMBER_OF_SUCCESS_CASES_FIRST_ENVELOPE + successAmount);
        } else if (methodId == 2) {
            int successAmount = getMoneyFromSecondEnvelope(amountOfIterations);
            System.err.println(NUMBER_OF_SUCCESS_CASES_SECOND_ENVELOPE + successAmount);
        } else if (methodId == 3) {
            int successAmount = changeEnvelopes(amountOfIterations);
            System.out.println(NUMBER_OF_SUCCESS_CASES_OF_CHANGED_ENVELOPES + successAmount);
        } else {
            int firstMethodSuccessAmount = getMoneyAmountFromFirstEnvelope(amountOfIterations);
            int secondMethodSuccessAmount = getMoneyFromSecondEnvelope(amountOfIterations);
            int thirdMethodSuccessAmount = changeEnvelopes(amountOfIterations);

            Thread.sleep(1000);
            System.err.println(NUMBER_OF_SUCCESS_CASES_FIRST_ENVELOPE + firstMethodSuccessAmount);
            Thread.sleep(1000);
            System.err.println(NUMBER_OF_SUCCESS_CASES_SECOND_ENVELOPE + secondMethodSuccessAmount);
            Thread.sleep(1000);
            System.err.println(NUMBER_OF_SUCCESS_CASES_OF_CHANGED_ENVELOPES + thirdMethodSuccessAmount);
        }
    }


    private int getMoneyAmountFromFirstEnvelope(int amountOfIterations) {
        int successCounter = 0;
        while (amountOfIterations != 0) {
            putMoneyToEnvelopes();
            if (firstEnvelope == MONEY_AMOUNT * 2) {
                successCounter++;
            }
            System.out.println(AMOUNT_OF_MONEY_FIRST_ENVELOPE + firstEnvelope);
            System.out.println();
            amountOfIterations--;
        }
        return successCounter;
    }

    private int getMoneyFromSecondEnvelope(int amountOfIterations) {
        int successCounter = 0;
        while (amountOfIterations != 0) {
            putMoneyToEnvelopes();
            if (secondEnvelope == MONEY_AMOUNT * 2) {
                successCounter++;
            }
            System.out.print(AMOUNT_OF_MONET_SECOND_ENVELOPE + secondEnvelope);
            System.out.println();
            amountOfIterations--;
        }

        return successCounter;
    }

    private int changeEnvelopes(int amountOfIterations) {
        int successCounter = 0;
        while (amountOfIterations != 0) {
            putMoneyToEnvelopes();
            int temp = firstEnvelope;
            firstEnvelope = secondEnvelope;
            secondEnvelope = temp;
            if (firstEnvelope == MONEY_AMOUNT * 2) {
                successCounter++;
            }
            System.out.println(AMOUNT_OF_MONEY_FIRST_ENVELOPE + firstEnvelope);
            System.out.println(AMOUNT_OF_MONET_SECOND_ENVELOPE + secondEnvelope);
            amountOfIterations--;
        }

        return successCounter;
    }

    private void putMoneyToEnvelopes() {
        int[] envelopes = {0, 0};
        int randomEnvelope = new Random().nextInt(2);
        envelopes[randomEnvelope] = MONEY_AMOUNT;
        if (envelopes[0] == 0) {
            envelopes[0] = MONEY_AMOUNT * 2;
        } else {
            envelopes[1] = MONEY_AMOUNT * 2;
        }

        firstEnvelope = envelopes[0];
        secondEnvelope = envelopes[1];
    }

    private void setMethodId(int methodId) {
        this.methodId = methodId;
    }

    private void setIterationsAmount(int iterationsAmount) {
        this.iterationsAmount = iterationsAmount;
    }
}
