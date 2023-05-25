/*import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Imagen {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Mostrar Imagen");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//cerrar ventana

        String rutaImagen = "C:\\Users\\hp\\IdeaProjects\\FormularioHilos\\Family.png";
        ImageIcon icono = new ImageIcon(rutaImagen);

        JLabel etiquetaImagen = new JLabel(icono);

        frame.getContentPane().add(etiquetaImagen);

        frame.pack();//ajuste del frm

        frame.setLocationRelativeTo(null);

        frame.setVisible(true);

        ExecutorService executorService = Executors.newFixedThreadPool(1);

        Questionx exerciseThread = new Questionx();
        exerciseThread.startExercise("-----------------------------------------\nRead the conversation and answer the questions\n");
        exerciseThread.waitForAnswer("1. Anne is Paul's ________? ", "wife");
        exerciseThread.evaluateAnswer("Anne's");

        exerciseThread.startExercise("2. Jason and Emily are ________? ");
        exerciseThread.waitForAnswer("2. Who are they? ", "their");
        exerciseThread.evaluateAnswer("their");

        exerciseThread.startExercise("3. Paul is ________? ");
        exerciseThread.waitForAnswer("3. Who is Paul's? ", "Anne");
        exerciseThread.evaluateAnswer("Anne");

        exerciseThread.startExercise("4. Jason is ________? ");
        exerciseThread.waitForAnswer("4. Who is Jason's? ", "Anne");
        exerciseThread.evaluateAnswer("Anne");

        exerciseThread.startExercise("5. Emily is ________? ");
        exerciseThread.waitForAnswer("5. Who is Emily's? ", "Paul");
        exerciseThread.evaluateAnswer("Paul");

        exerciseThread.startExercise("6. Jason is ________? ");
        exerciseThread.waitForAnswer("6. Who is Jason's? ", "Emily");
        exerciseThread.evaluateAnswer("Emily");

        exerciseThread.startExercise("7. Emily is ________? ");
        exerciseThread.waitForAnswer("7. Who is Emily's? ", "Jason");
        exerciseThread.evaluateAnswer("Jason");

        exerciseThread.startExercise("8. Paul and Anne are ________? ");
        exerciseThread.waitForAnswer("8. Who are Paul and Anne? ", "Jason's");
        exerciseThread.evaluateAnswer("Jason's");
    }

    private static void displayMessage(String message) {
        for (int i = 0; i < message.length(); i++) {
            System.out.print(message.charAt(i));
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println();
    }

    private static class Questionx {
        private void startExercise(String exercise) {
            System.out.println(exercise);
            sleep();
        }

        private void waitForAnswer(String question, String expectedAnswer) {
            Scanner scanner = new Scanner(System.in);
            System.out.print(question);

            String answer = scanner.nextLine();
            while (!answer.equalsIgnoreCase(expectedAnswer)) {
                System.out.println("Incorrect answer. Try again!");
                System.out.print(question);
                answer = scanner.nextLine();
            }
        }

        private void evaluateAnswer(String person) {
            System.out.println("Correct answer! " + person + " is correct.");
            System.out.println("Great job!");
            System.out.println();
            sleep();
        }

        private void sleep() {
            try {
                Thread.sleep(2000); // hilo con retardo de 2 segundos
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}*/