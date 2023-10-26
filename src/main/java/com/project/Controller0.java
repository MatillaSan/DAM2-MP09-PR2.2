package com.project;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javafx.application.Platform;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

public class Controller0 {

    @FXML
    private Button buttonTask1, buttonTask2, buttonTask3, buttonChangeView;

    @FXML
    private ProgressBar taskBar1, taskBar2, taskBar3;

    @FXML
    private Label labelTask1, labelTask2, labelTask3;

    private boolean running1 = false;
    private boolean paused1 = false;
    private boolean running2 = false;
    private boolean paused2 = false;
    private boolean running3 = false;
    private boolean paused3 = false;

    private ExecutorService executor = Executors.newFixedThreadPool(3); // Creem una pool de tres fils

    @FXML
    private void animateToView1(ActionEvent event) {
        UtilsViews.setViewAnimating("View1");
    }

    @FXML
    private void runTask1() throws InterruptedException {

        if (!running1 & !paused1) {
            backgroundTask(0);
            running1 = true;
            buttonTask1.setText("Aturar");
        } else if (paused1) {
            running1 = true;
            paused1 = false;
            buttonTask1.setText("Aturar");
        } else {
            running1 = false;
            paused1 = true;
            buttonTask1.setText("Iniciar");
        }

    }

    @FXML
    private void runTask2() {

        if (!running2 & !paused2) {
            backgroundTask(1);
            running2 = true;
            buttonTask2.setText("Aturar");
        } else if (paused2) {
            running2 = true;
            paused2 = false;
            buttonTask2.setText("Aturar");
        } else {
            running2 = false;
            paused2 = true;
            buttonTask2.setText("Iniciar");
        }

    }

    @FXML
    private void runTask3() {

        if (!running3 & !paused3) {
            backgroundTask(2);
            running3 = true;
            buttonTask3.setText("Aturar");
        } else if (paused3) {
            running3 = true;
            paused3 = false;
            buttonTask3.setText("Aturar");
        } else {
            running3 = false;
            paused3 = true;
            buttonTask3.setText("Iniciar");
        }
    }

    private void backgroundTask(int index) {
        // Executar la tasca
        executor.submit(() -> {
            try {

                for (int i = 0; i <= 100; i++) {
                    final int currentValue = i;
                    if (index == 0) {
                        while (!running1) {
                            Thread.sleep(1000);
                        }
                        Platform.runLater(() -> {
                            double pgr = (double) currentValue / 100;
                            labelTask1.setText("Tasca 1, " + String.valueOf(currentValue) + "%");
                            taskBar1.setProgress(pgr);
                            System.out.println("Updating label: " + index + ", Value: " + currentValue);
                            if (pgr >= 1) {
                                running1 = false;
                                paused1 = false;
                                buttonTask3.setText("Iniciar");
                            }
                        });
                        Thread.sleep(1000);
                    }
                    if (index == 1) {
                        while (!running2) {
                            Thread.sleep(1000);
                        }
                        int random1 = (int) (Math.random() * 2001) + 3000;
                        int random2 = (int) (Math.random() * 2) + 2;
                        double pgr = ((double) currentValue + (double) random2) / 100;
                        i = currentValue + random2;
                        Platform.runLater(() -> {
                            labelTask2.setText("Tasca 2, " + String.valueOf(currentValue + random2) + "%");
                            taskBar2.setProgress(pgr);
                            System.out.println("Updating label: " + index + ", Value: " + (currentValue + random2));
                            if (pgr >= 1) {
                                running2 = false;
                                paused2 = false;
                                buttonTask3.setText("Iniciar");
                            }
                        });
                        Thread.sleep(random1);
                    }
                    if (index == 2) {
                        while (!running3) {
                            Thread.sleep(1000);
                        }
                        int random1 = (int) (Math.random() * 5001) + 3000;
                        int random2 = (int) (Math.random() * 4) + 2;
                        double pgr = ((double) currentValue + (double) random2) / 100;
                        i = currentValue + random2;
                        Platform.runLater(() -> {
                            labelTask3.setText("Tasca 3, " + String.valueOf(currentValue + random2) + "%");
                            taskBar3.setProgress(pgr);
                            System.out.println("Updating label: " + index + ", Value: " + (currentValue + random2));
                            if (pgr >= 1) {
                                running3 = false;
                                paused3 = false;
                                buttonTask3.setText("Iniciar");
                            }
                        });
                        Thread.sleep(random1);
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    // Aquesta funció la cridaries quan vulguis tancar l'executor (per exemple, quan
    // tanquis la teva aplicació)
    public void stopExecutor() {
        executor.shutdownNow();
    }
}