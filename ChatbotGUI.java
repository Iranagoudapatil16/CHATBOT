import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ChatbotGUI {
    private static Map<String, String> responses = new HashMap<>();

    public static void main(String[] args) {
        // Predefined responses
        responses.put("hi", "Hello! How can I help you today?");
        responses.put("hello", "Hi there! How's your day going?");
        responses.put("how are you", "I'm just a bot, but I'm doing fine! Thanks for asking.");
        responses.put("bye", "Goodbye! Have a great day!");
        responses.put("what is your name", "I am ChatBot, your friendly assistant!");
        responses.put("what can you do", "I can chat with you! Ask me anything.");
        responses.put("tell me a joke", "Why don’t skeletons fight each other? Because they don’t have the guts!");
        responses.put("who created you", "I was created by a developer who loves coding!");
        responses.put("how's the weather", "I can't check the weather yet, but I hope it's nice where you are!");
        responses.put("thank you", "You're welcome! Happy to help.");
        responses.put("how old are you", "I am timeless, just like the internet!");

        // Create main frame
        JFrame frame = new JFrame("Chatbot");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 700);
        frame.setLayout(new BorderLayout());

        // Top panel for date and time
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());

        JLabel dateTimeLabel = new JLabel(getCurrentDateTime(), JLabel.CENTER);
        dateTimeLabel.setFont(new Font("Arial", Font.BOLD, 14));
        topPanel.add(dateTimeLabel, BorderLayout.NORTH);

        // Chatbot image
        ImageIcon botIcon = new ImageIcon("chatbot.png"); // Ensure chatbot.png is in your project folder
        JLabel botLabel = new JLabel(botIcon, JLabel.CENTER);
        topPanel.add(botLabel, BorderLayout.CENTER);

        frame.add(topPanel, BorderLayout.NORTH);

        // Chat display area
        JTextArea chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setFont(new Font("Arial", Font.PLAIN, 14));
        chatArea.setLineWrap(true);
        chatArea.setWrapStyleWord(true);
        chatArea.setBackground(new Color(240, 248, 255));
        JScrollPane scrollPane = new JScrollPane(chatArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Display available questions at the start
        chatArea.append("Welcome! You can ask me the following:\n");
        for (String question : responses.keySet()) {
            chatArea.append(" - " + question + "\n");
        }
        chatArea.append("\n");

        // Input area
        JTextField userInput = new JTextField();
        JButton sendButton = new JButton("Send");
        sendButton.setFont(new Font("Arial", Font.BOLD, 14));
        sendButton.setBackground(new Color(100, 149, 237));
        sendButton.setForeground(Color.WHITE);

        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.add(userInput, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);
        frame.add(inputPanel, BorderLayout.SOUTH);

        // Action listeners for sending messages
        ActionListener sendAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputText = userInput.getText().toLowerCase().trim();
                if (!inputText.isEmpty()) {
                    String timestamp = new SimpleDateFormat("HH:mm").format(new Date());
                    chatArea.append("You [" + timestamp + "]: " + inputText + "\n");
                    userInput.setText("");

                    String response = responses.getOrDefault(inputText, "I'm not sure how to respond to that. Try asking something else!");
                    chatArea.append("Bot [" + timestamp + "]: " + response + "\n");
                }
            }
        };

        sendButton.addActionListener(sendAction);
        userInput.addActionListener(sendAction);

        // Update date & time every second
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dateTimeLabel.setText(getCurrentDateTime());
            }
        });
        timer.start();

        frame.setVisible(true);
    }

    // Get current date & time as a formatted string
    private static String getCurrentDateTime() {
        return new SimpleDateFormat("EEEE, MMMM dd, yyyy - HH:mm:ss").format(new Date());
    }
}
