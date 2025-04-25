package trabajo;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;



public class Intro extends Application {
    private Image imagen = new Image("Imagenes\\fondo_inicio.gif");
    private ImageView barraCarga = new ImageView("Imagenes\\barra_carga2.gif");
    private Image imagenTransicion= new Image("Imagenes\\fondo_negro.png");
    private ImageView titulo = new ImageView("Imagenes\\Titulo.png");
    Juego juego= new Juego();

    public void start(Stage primaryStage) {
        ImageView imageView = new ImageView();
        imageView.setImage(this.imagen);
        imageView.setPreserveRatio(false);
        imageView.fitWidthProperty().bind(primaryStage.widthProperty());
        imageView.fitHeightProperty().bind(primaryStage.heightProperty());

        //Ajustes del titulo
        titulo.setPreserveRatio(true);
        titulo.setFitWidth(800);

        //Boton para juagar
        Button boton = new Button("JUGAR");
        boton.setPrefWidth(250);
        boton.setPrefHeight(30);


        //Transicion para que el boton tenga movimiento
        ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(1), boton);
        scaleTransition.setFromX(1.0);
        scaleTransition.setFromY(1.0);
        scaleTransition.setToX(1.3);
        scaleTransition.setToY(1.3);
        scaleTransition.setAutoReverse(true);
        scaleTransition.setCycleCount(ScaleTransition.INDEFINITE);
        scaleTransition.play();

        //Hbox para el boton
        HBox hbox = new HBox(boton);
        hbox.setAlignment(Pos.BOTTOM_CENTER);
        hbox.setTranslateY(-20);

        //Vbox para el hbox y el titulo
        VBox vbox = new VBox();
        vbox.setAlignment(Pos.TOP_CENTER);
        vbox.getChildren().addAll(titulo, hbox);
        VBox.setMargin(titulo, new javafx.geometry.Insets(50, 0, 200, 0));


        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(imageView, vbox);


        Scene scene = new Scene(stackPane, 1920, 1080);

        // Estilos para el boton
        boton.getStyleClass().add("Piedra");
        scene.getStylesheets().add("Estilo.css");

        //Accion al boton cuando se acciona
        boton.setOnAction((e)->{
            fadeOutAndStartGame(primaryStage, stackPane);
            boton.setVisible(false);
        });

        scene.widthProperty().addListener((obs, oldVal, newVal) -> {
            imageView.setFitWidth(newVal.doubleValue());
        });

        scene.heightProperty().addListener((obs, oldVal, newVal) -> {
            imageView.setFitHeight(newVal.doubleValue());
        });

        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);
        primaryStage.show();
    }
    // Transicion de cambio de escena al juego
    private void fadeOutAndStartGame(Stage primaryStage, StackPane stackPane) {
        FadeTransition fadeOut = new FadeTransition(Duration.seconds(2), stackPane);
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);

        fadeOut.setOnFinished(event -> {
            StackPane transitionPane = new StackPane();
            ImageView transicion = new ImageView(imagenTransicion);
            transicion.setFitWidth(1920);
            transicion.setFitHeight(1080);
            transicion.setPreserveRatio(false);

            barraCarga.setFitWidth(350);
            barraCarga.setFitHeight(300);
            HBox hboxTransicion = new HBox();
            hboxTransicion.getChildren().add(barraCarga);
            hboxTransicion.setAlignment(Pos.CENTER);

            transitionPane.getChildren().addAll(transicion, hboxTransicion);

            Scene transitionScene = new Scene(transitionPane, 1920, 1080);

            // Agregar transición de desvanecimiento para la escena intermedia
            primaryStage.setScene(transitionScene);
            primaryStage.setFullScreen(true);

            // Hacer desvanecimiento inverso
            FadeTransition fadeIn = new FadeTransition(Duration.seconds(2), transitionPane);
            fadeIn.setFromValue(0.0);
            fadeIn.setToValue(1.0);
            fadeIn.setOnFinished(event1 -> {
                // Esperar 2 segundos y luego iniciar la escena del juego
                FadeTransition waitAndChangeScene = new FadeTransition(Duration.seconds(1.4), transitionPane);
                waitAndChangeScene.setOnFinished(event2 -> juego.start(primaryStage));
                waitAndChangeScene.play();
            });

            fadeIn.play();
        });

        fadeOut.play();
    }
}

