package Project2;

import javafx.application.Application;
import javafx.stage.Stage;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Task_6_15_29 extends Application{

        @Override
        public void start(Stage primaryStage) throws Exception {

            CarPane carPane = new CarPane(600, 200);

            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.millis(10), e-> carPane.moveCar(1)));
            timeline.setCycleCount(carPane.getOneCycleDuration());
            timeline.play();
            timeline.setOnFinished(e-> {
                carPane.reset();
                timeline.play();
            });


            Button pause = new Button("pause");
            pause.setOnAction(e-> timeline.pause());

            Button resume = new Button("resume");
            resume.setOnAction(e-> timeline.play());

            Button increase = new Button("increase");
            increase.setOnAction(e-> {
                timeline.setRate(timeline.getCurrentRate() + 1);
            });

            Button decrease = new Button("decrease");
            decrease.setOnAction(e-> {
                if (timeline.getCurrentRate() <= 0) return;

                timeline.setRate(timeline.getCurrentRate() - 1);
            });

            HBox hButtons = new HBox(pause,resume,increase,decrease);
            hButtons.setSpacing(10);
            hButtons.setAlignment(Pos.CENTER);
            hButtons.setPadding(new Insets(5, 5, 5, 5));


            BorderPane borderPane = new BorderPane(carPane, null, null, hButtons, null);
            borderPane.setOnKeyPressed(e-> {
                switch (e.getCode()) {
                    case UP: increase.fire(); break;
                    case DOWN: decrease.fire(); break;
                }
            });
            primaryStage.setScene(new Scene(borderPane));
            primaryStage.setTitle("Moving Car");
            primaryStage.show();

        }

        public static void main(String[] args) {
            Application.launch(args);

        }

        private class CarPane extends Pane {

            private double w;
            private double h;


            private double leftTireX;
            private double leftTireY;
            private double tireRadius;

            Circle[] tires = new Circle[2];
            Polyline cover = new Polyline();
            ObservableList<Double> points;
            Rectangle base;

            private CarPane(double width, double height){


                w = width;
                h = height;
                leftTireX = w * 0.2;
                leftTireY = h * 0.9;
                tireRadius = h * 0.1;


                setMinWidth(w);
                setMinHeight(h);

                setMaxWidth(w);
                setMaxHeight(h);

                reset();
            }

            public void reset() {

                if (points != null)
                    points.clear();
                getChildren().clear();
                drawCar();
                moveCar(tireRadius * 13 * -1);
            }

            public void drawCar() {
                for (int i = 0; i < tires.length; i++) {
                    tires[i] = new Circle(leftTireX + (i * 4 * tireRadius), leftTireY, tireRadius);
                    tires[i].setStroke(Color.BLACK);
                    tires[i].setFill(Color.BLACK);

                }

                double baseX = tires[0].getCenterX() - tires[0].getRadius() * 3;
                double baseY = tires[0].getCenterY() - tires[0].getRadius() * 3;
                base = new Rectangle(baseX, baseY, tireRadius * 10, tireRadius * 2);
                base.setFill(Color.LIGHTCORAL);
                base.setStroke(Color.BLACK);

                // draw the car's top cover
                double startX = base.getX() + tireRadius * 2;
                double startY = base.getY();
                double currentX = startX;
                double currentY = startY;

                points = cover.getPoints();

                double distance = tireRadius * 2;
                points.addAll(currentX, currentY); // start point

                currentX += distance;
                currentY -= distance;
                points.addAll(currentX, currentY); // up right

                currentX += distance;
                points.addAll(currentX,currentY); // right

                currentX += distance;
                currentY += distance;
                points.addAll(currentX,currentY); // down right

                points.addAll(startX, startY); // connect to starting point
                cover.setFill(Color.PURPLE);

                getChildren().addAll(tires);
                getChildren().add(base);
                getChildren().add(cover);
            }

            private void moveCar(double distance) {

                // tires
                for (Circle c : tires) {
                    c.setCenterX(c.getCenterX() + distance);
                }

                base.setX(base.getX() + distance);

                for (int i = 0; i < points.size(); i += 2) {
                    points.set(i, points.get(i) + distance);
                }

            }

            public int getOneCycleDuration(){
                return (int)w;
            }
        }
    }

