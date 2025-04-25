package trabajo;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Juego extends Application {
    private final Image imagen = new Image("Imagenes\\fondo_pelea.gif");
    private final ImageView reemplazoVidas= new ImageView("Imagenes\\hadouken_negro.png");
    private final StackPane contenedor = new StackPane();
    private final Image gif1 = new Image("Imagenes\\animacion-general-unscreen.gif");
    private final ImageView piedra = new ImageView("Imagenes\\piedra.png");
    private final ImageView papel = new ImageView("Imagenes\\papel.png");
    private final ImageView tijeras = new ImageView("Imagenes\\tijeras.png");
    private final ImageView piedra2 = new ImageView("Imagenes\\piedra.png");
    private final ImageView papel2 = new ImageView("Imagenes\\papel.png");
    private final ImageView tijeras2 = new ImageView("Imagenes\\tijeras.png");
    private final ImageView vida1 = new ImageView("Imagenes\\hadouken_vida.png");
    private final ImageView vida2 = new ImageView("Imagenes\\hadouken_vida.png");
    private final ImageView vida3 = new ImageView("Imagenes\\hadouken_vida.png");
    private final ImageView vida1Jugador2 = new ImageView("Imagenes\\hadouken_vida.png");
    private final ImageView vida2Jugador2 = new ImageView("Imagenes\\hadouken_vida.png");
    private final ImageView vida3Jugador2 = new ImageView("Imagenes\\hadouken_vida.png");
    private final ImageView versus = new ImageView("Imagenes\\vs.png");
    private int valor_usuario, valorAleatorio =0;
    private final Random random = new Random();
    private int contador1 =0, contador2 =0;

    public void start(Stage ventana) {
        Scene escena = new Scene(this.contenedor,1920, 1080);
        ventana.setTitle("Street Fighter");

        //Fondo del Juego
        ImageView fondoView = new ImageView();
        fondoView.setImage(this.imagen);
        fondoView.setPreserveRatio(false);
        fondoView.fitWidthProperty().bind(ventana.widthProperty());
        fondoView.fitHeightProperty().bind(ventana.heightProperty());

        //Gifs de los puños y imagen del centro
        ImageView gifView1 = new ImageView(gif1);
        gifView1.setScaleX(-1);
        ImageView gifView2 = new ImageView(gif1);



        //Ajuste del tamaño de los gifs
        gifView1.fitWidthProperty().bind(ventana.widthProperty().multiply(0.6));
        gifView1.fitHeightProperty().bind(ventana.heightProperty().multiply(0.7));
        gifView2.fitWidthProperty().bind(ventana.widthProperty().multiply(0.6));
        gifView2.fitHeightProperty().bind(ventana.heightProperty().multiply(0.7));
        versus.fitWidthProperty().bind(ventana.widthProperty().multiply(0.3));
        versus.fitHeightProperty().bind(ventana.heightProperty().multiply(0.5));

        //Hbox para las vidas de la izquierda
        HBox hBoxVida1 = new HBox(10,vida1,vida2,vida3);
        hBoxVida1.setAlignment(Pos.CENTER_LEFT);

        //Ajuste del tamaño de las vidas de la izquierda
        vida1.fitWidthProperty().bind(ventana.widthProperty().multiply(0.1));
        vida1.fitHeightProperty().bind(ventana.heightProperty().multiply(0.180));
        vida2.fitWidthProperty().bind(ventana.widthProperty().multiply(0.1));
        vida2.fitHeightProperty().bind(ventana.heightProperty().multiply(0.180));
        vida3.fitWidthProperty().bind(ventana.widthProperty().multiply(0.1));
        vida3.fitHeightProperty().bind(ventana.heightProperty().multiply(0.180));

        //Hbox para las vidas de la derecha
        HBox hBoxVida2 = new HBox(10,vida1Jugador2, vida2Jugador2, vida3Jugador2);
        hBoxVida2.setAlignment(Pos.CENTER_RIGHT);
        hBoxVida2.setRotate(180);

        //Ajuste del tamaño de las vidas de la derecha
        vida1Jugador2.fitWidthProperty().bind(ventana.widthProperty().multiply(0.1));
        vida1Jugador2.fitHeightProperty().bind(ventana.heightProperty().multiply(0.180));
        vida2Jugador2.fitWidthProperty().bind(ventana.widthProperty().multiply(0.1));
        vida2Jugador2.fitHeightProperty().bind(ventana.heightProperty().multiply(0.180));
        vida3Jugador2.fitWidthProperty().bind(ventana.widthProperty().multiply(0.1));
        vida3Jugador2.fitHeightProperty().bind(ventana.heightProperty().multiply(0.180));


        //Hbox para toda la barra de arriba
        HBox hBoxVida = new HBox();
        hBoxVida.getChildren().addAll(hBoxVida1, hBoxVida2);
        hBoxVida.setAlignment(Pos.CENTER);
        hBoxVida.setSpacing(600);

        //Hbox para los gifts y el versus
        HBox hboxGif = new HBox();
        hboxGif.setAlignment(Pos.CENTER);
        hboxGif.getChildren().addAll(gifView1,gifView2);

        //Hbox versus
        HBox hboxVersus = new HBox(versus);
        hboxVersus.setAlignment(Pos.CENTER);

        //Vbox para todas las imagenes del juego
        VBox vBox1 = new VBox();
        vBox1.getChildren().addAll(hBoxVida,hboxGif);



        //Ajuste del tamaño del fondo
        fondoView.fitWidthProperty().bind(ventana.widthProperty());
        fondoView.fitHeightProperty().bind(ventana.heightProperty());

        // --- Botones con temática de Street Fighter ---
        Button btnpiedra = new Button("Piedra");
        Button btntijeras = new Button("Tijeras");
        Button btnpapel = new Button("Papel");

        // HBox para los botones
        HBox hBoxBotones = new HBox();
        hBoxBotones.getChildren().addAll(btnpiedra, btntijeras, btnpapel);
        hBoxBotones.setAlignment(Pos.CENTER);
        hBoxBotones.setSpacing(20);

        //Pausa para la transicion
        PauseTransition pausa = new PauseTransition(Duration.seconds(0.5));

        //Restaurar la pantalla inicial
        Runnable restaurarEstadoInicial = () -> {
            gifView1.setImage(gif1);
            gifView2.setImage(gif1);
            gifView1.fitWidthProperty().bind(ventana.widthProperty().multiply(0.6));
            gifView1.fitHeightProperty().bind(ventana.heightProperty().multiply(0.7));
            gifView2.fitWidthProperty().bind(ventana.widthProperty().multiply(0.6));
            gifView2.fitHeightProperty().bind(ventana.heightProperty().multiply(0.7));
            versus.setImage(new Image("Imagenes\\vs.png"));
            hBoxBotones.setVisible(true);
        };

        //Cuando presionas un boton cambia la imagen
        btnpiedra.setOnAction((e) -> {
            hBoxBotones.setVisible(false);
            System.out.println(valor_usuario);
            pausa.setOnFinished(event -> {
                gifView1.setImage(piedra.getImage());
                gifView1.fitWidthProperty().bind(ventana.widthProperty().multiply(0.5));
                gifView2.fitWidthProperty().bind(ventana.widthProperty().multiply(0.5));
                //Jugador 2
                 valorAleatorio = random.nextInt(3) + 1;
                    if (valorAleatorio == 1) {
                        gifView2.setImage(piedra2.getImage());
                    }
                    else if (valorAleatorio == 2) {
                        gifView2.setImage(tijeras2.getImage());
                    }
                    else {
                        gifView2.setImage(papel2.getImage());
                    }
                valor_usuario=1;
                System.out.println(resultado());

                versus.setImage(null);
                estadisticasJuego(resultado());

                //Perdida de vidas piedra
                if(resultado().equals("Win")){
                    if(contador1 ==0) {
                        vida3Jugador2.setImage(reemplazoVidas.getImage());
                        contador1++;
                    } else if (contador1 == 1) {
                        vida2Jugador2.setImage(reemplazoVidas.getImage());
                        contador1++;
                    }else{
                        vida1Jugador2.setImage(reemplazoVidas.getImage());
                        contador1++;
                        mostrarVictoria(contenedor);
                        estadisticasJuego(resultado());
                    }
                }
                if(resultado().equals("Lose")){
                    if(contador2 ==0) {
                        vida3.setImage(reemplazoVidas.getImage());
                        contador2++;
                    } else if (contador2 == 1) {
                        vida2.setImage(reemplazoVidas.getImage());
                        contador2++;
                    }else{
                        vida1.setImage(reemplazoVidas.getImage());
                        contador2++;
                        mostrarDerrota(contenedor);
                        estadisticasJuego(resultado());
                    }
                }
            });
            PauseTransition restaurar = new PauseTransition(Duration.seconds(2));
            restaurar.setOnFinished(ev -> restaurarEstadoInicial.run());
            restaurar.play();
            pausa.play();
        });

        btntijeras.setOnAction((e) -> {
            hBoxBotones.setVisible(false);
            System.out.println(valor_usuario);
            pausa.setOnFinished(event -> {
            gifView1.setImage(tijeras.getImage());
            gifView1.fitWidthProperty().bind(ventana.widthProperty().multiply(0.5));
            gifView2.fitWidthProperty().bind(ventana.widthProperty().multiply(0.5));

            //Jugador 2
             valorAleatorio = random.nextInt(3)+1;
                if (valorAleatorio == 1) {
                    gifView2.setImage(piedra2.getImage());
                }
                else if (valorAleatorio == 2) {
                    gifView2.setImage(tijeras2.getImage());
                }
                else {
                    gifView2.setImage(papel2.getImage());
                }
                valor_usuario=2;
                System.out.println(resultado());

                versus.setImage(null);

                estadisticasJuego(resultado());

            //Perdida de vida tijeras
            if(resultado().equals("Win")){
                if(contador1 ==0) {
                    vida3Jugador2.setImage(reemplazoVidas.getImage());
                    contador1++;
                } else if (contador1 == 1) {
                    vida2Jugador2.setImage(reemplazoVidas.getImage());
                    contador1++;
                }else{
                    vida1Jugador2.setImage(reemplazoVidas.getImage());
                    contador1++;
                    mostrarVictoria(contenedor);
                    estadisticasJuego(resultado());
                }
            }
                if(resultado().equals("Lose")){
                    if(contador2 ==0) {
                        vida3.setImage(reemplazoVidas.getImage());
                        contador2++;
                    } else if (contador2 == 1) {
                        vida2.setImage(reemplazoVidas.getImage());
                        contador2++;
                    }else{
                        vida1.setImage(reemplazoVidas.getImage());
                        contador2++;
                        mostrarDerrota(contenedor);
                        estadisticasJuego(resultado());
                    }
                }
            });

            PauseTransition restaurar = new PauseTransition(Duration.seconds(2));
            restaurar.setOnFinished(ev -> restaurarEstadoInicial.run());
            restaurar.play();
            pausa.play();
        });

        //Boton papel
        btnpapel.setOnAction((e) -> {
            hBoxBotones.setVisible(false);
            System.out.println(valor_usuario);
            pausa.setOnFinished(event -> {
                gifView1.setImage(papel.getImage());
                gifView1.fitWidthProperty().bind(ventana.widthProperty().multiply(0.5));
                gifView2.fitWidthProperty().bind(ventana.widthProperty().multiply(0.5));

                //Jugador 2
                valorAleatorio = random.nextInt(3)+1;
                if (valorAleatorio == 1) {
                    gifView2.setImage(piedra2.getImage());
                }
                else if (valorAleatorio == 2) {
                    gifView2.setImage(tijeras2.getImage());
                }
                else {
                    gifView2.setImage(papel2.getImage());
                }
                valor_usuario=3;
                System.out.println(resultado());

                versus.setImage(null);
                estadisticasJuego(resultado());

                //Perdida de vida papel
                if(resultado().equals("Win")){
                    if(contador1 ==0) {
                        vida3Jugador2.setImage(reemplazoVidas.getImage());
                        contador1++;
                    } else if (contador1 == 1) {
                        vida2Jugador2.setImage(reemplazoVidas.getImage());
                        contador1++;
                    }else{
                        vida1Jugador2.setImage(reemplazoVidas.getImage());
                        contador1++;
                        mostrarVictoria(contenedor);
                        estadisticasJuego(resultado());

                    }
                }
                if(resultado().equals("Lose")){
                    if(contador2 ==0) {
                        vida3.setImage(reemplazoVidas.getImage());
                        contador2++;
                    } else if (contador2 == 1) {
                        vida2.setImage(reemplazoVidas.getImage());
                        contador2++;
                    }else{
                        vida1.setImage(reemplazoVidas.getImage());
                        contador2++;
                        mostrarDerrota(contenedor);
                        estadisticasJuego(resultado());

                    }
                }
            });
            PauseTransition restaurar = new PauseTransition(Duration.seconds(2));
            restaurar.setOnFinished(ev -> restaurarEstadoInicial.run());
            restaurar.play();
            pausa.play();
        });


        //Dar clases a los botones para los estilos
        btnpiedra.getStyleClass().add("Piedra");
        btntijeras.getStyleClass().add("Tijeras");
        btnpapel.getStyleClass().add("Papel");

        // Estilos para los botones
        escena.getStylesheets().add("Estilo.css");



        // Añadir los botones debajo de los gifs
        VBox vBox2 = new VBox();
        vBox2.getChildren().addAll(vBox1, hBoxBotones);
        vBox2.setSpacing(30);
        vBox2.setAlignment(Pos.CENTER);


        this.contenedor.getChildren().addAll(new Node[]{fondoView,hboxVersus,vBox2});
        ventana.setScene(escena);
        ventana.setFullScreen(true);
        ventana.show();
    }
    public String resultado(){
        if (valorAleatorio==valor_usuario){
            return "Empate";
        } else if ((valor_usuario == 1 && valorAleatorio == 2) || (valor_usuario ==2 && valorAleatorio ==3) ||
                (valor_usuario == 3 && valorAleatorio==1)) {
            return "Win";
        }else {
            return "Lose";
        }

    }
    public static void mostrarDerrota(StackPane root) {
        ImageView fondoNegro = new ImageView("Imagenes\\fondo_negro.png");
        Image imagenDerrota= new Image("Imagenes\\derrota.png");
        ImageView imagenSuperpuesta= new ImageView(imagenDerrota);
        imagenSuperpuesta.setFitWidth(600);
        imagenSuperpuesta.setFitHeight(500);
        fondoNegro.setFitHeight(1080);
        fondoNegro.setFitWidth(1920);
        root.getChildren().addAll(fondoNegro,imagenSuperpuesta);
    }
    public static void mostrarVictoria(StackPane root) {
        ImageView fondoNegro = new ImageView("Imagenes\\fondo_negro.png");
        Image imagenVictoria= new Image("Imagenes\\victoria.png");
        ImageView imagenSuperpuesta= new ImageView(imagenVictoria);
        imagenSuperpuesta.setFitWidth(600);
        imagenSuperpuesta.setFitHeight(500);
        fondoNegro.setFitHeight(1080);
        fondoNegro.setFitWidth(1920);
        root.getChildren().addAll(fondoNegro,imagenSuperpuesta);
    }
    public void estadisticasJuego(String resultado){
        try (FileWriter writer = new FileWriter("C:\\Users\\diazp\\IdeaProjects\\Street_Fighter\\src\\estadisticas.txt", true)) {
            writer.write("Resultado del turno: " + resultado + "\n");
            writer.write("Jugador seleccionó: " + valorUsuarioAString(valor_usuario) + "\n");
            writer.write("Jugador 2 seleccionó: " + valorUsuarioAString(valorAleatorio) + "\n");
            writer.write("Vidas restantes Jugador 1: " + (3 - contador1) + "\n");
            writer.write("Vidas restantes Jugador 2: " + (3 - contador2) + "\n");
            writer.write("--------------------------\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private String valorUsuarioAString(int valor) {
        return switch (valor) {
            case 1 -> "Piedra";
            case 2 -> "Tijeras";
            case 3 -> "Papel";
            default -> "Desconocido";
        };
    }
}

