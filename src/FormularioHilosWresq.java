import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;

public class FormularioHilosWresq extends JFrame {

    private static final long serialVersionUID = 1L;
    private static final String FILE_PATH = "texto_guardado.txt";
    private static final String DICTIONARY_PATH =
            "C:\\Users\\hp\\Documents\\UAEM\\Paradigmas II\\dict.txt";

    private static final int AUTO_SAVE_INTERVAL = 5000; // 5 segundos
    private static final int SPELL_CHECK_INTERVAL = 60000; // 60 segundos

    private JTextArea textArea;
    private ArrayList<String> dictionary;
    private boolean spellingErrors;

    public FormularioHilosWresq() {
        setTitle("Formulario con hilos");
        setSize(new Dimension(600, 400));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Cargar el diccionario
        try {
            String content = new String(Files.readAllBytes(Paths.get(DICTIONARY_PATH)));
            dictionary = new ArrayList<>(Arrays.asList(content.split("\\s+")));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar el diccionario");
            dictionary = new ArrayList<>();
        }

        // Crear la caja de texto y el panel de desplazamiento
        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        getContentPane().add(scrollPane);

        // Programar el hilo para el auto-guardado
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(AUTO_SAVE_INTERVAL);
                    guardarTexto();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        // Programar el hilo para la revisión ortográfica
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(SPELL_CHECK_INTERVAL);
                    revisarOrtografia();
                } catch (InterruptedException | BadLocationException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        // Manejar el evento de cierre de la ventana
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                guardarTexto();
                dispose();
            }
        });
    }

    private void guardarTexto() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH));
            writer.write(textArea.getText());
            writer.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al guardar el archivo");
        }
    }

    private void revisarOrtografia() throws BadLocationException {
        String text = textArea.getText();
        int offset = 0;
        spellingErrors = false;
        DefaultHighlighter.DefaultHighlightPainter painter = new DefaultHighlighter.DefaultHighlightPainter(Color.RED);
        textArea.getHighlighter().removeAllHighlights(); // Eliminar resaltados anteriores
        // Comprobar cada palabra en el texto
        for (String word : text.split("\\s+")) {
            if (!dictionary.contains(word.toLowerCase())) { // Si no está en el diccionario
                int start = text.indexOf(word, offset);
                int end = start + word.length();
                textArea.getHighlighter().addHighlight(start, end, painter); // Resaltar la palabra
                offset = end; // Actualizar el offset para evitar resaltados duplicados
                spellingErrors = true;
            }
        }
    }

public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            FormularioHilosWresq form = new FormularioHilosWresq();
            form.setVisible(true);
        });
    }
}