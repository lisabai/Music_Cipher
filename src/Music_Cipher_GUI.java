/**
 *Author: Lisa Bai on 11/7/14.
 * Title: Music Cipher GUI
 *
 * GUI Component to Music Cipher program.
 * Type a message into the text field, and then press "Enter" or the "Transform to Music!" button to enjoy the created music.
 *
 *
 */

import javax.swing.UIManager;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Music_Cipher_GUI extends JFrame {

    private static final int buttonWidth=120, buttonHeight=40;
    private static final Font buttonFont = new java.awt.Font("SansSerif", 1, 22);
    private static final Font font = new java.awt.Font("SansSerif", Font.BOLD, 42);
    private static final Dimension buttonDimension = new Dimension(buttonWidth,buttonHeight);

    // all of the GUI components

    private JPanel contentPane;
    //2 buttons
    private JPanel jPanel1 = new ImagePanel("music notes.png");

    //divides text field and JLabel ("Enter a message")
    private JPanel jPanel2 =  new ImagePanel("textToMusic text img.png");

    //Label "Enter your message"
    private JPanel jPanel3 =  new ImagePanel("textToMusic text img.png");

    //Text field
    private JPanel jPanel4 =  new ImagePanel("music notes.png");


    private musicCipher musicCipher = new musicCipher();

    private JTextField messageField = new JTextField();
    private JButton clearButton = new Key("Clear", buttonDimension);
    private JButton transformToMusicButton = new JButton("Transform to Music!");
    private JLabel resultLabel = new JLabel("Enter your message:");

    boolean packFrame = false;

    //Construct the application
    public Music_Cipher_GUI() {

        enableEvents(AWTEvent.WINDOW_EVENT_MASK);

        // add the components
        contentPane = (JPanel) this.getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(jPanel1, BorderLayout.CENTER);
        contentPane.add(jPanel2,  BorderLayout.NORTH);
        this.setSize(new Dimension(4*(buttonWidth+10)-10, 400));
        this.setTitle("Music Cipher");
        messageField.setFont(buttonFont);
        messageField.setHorizontalAlignment(SwingConstants.LEFT);

        transformToMusicButton.setPreferredSize(new Dimension(2*buttonWidth+10, buttonHeight));
        transformToMusicButton.setFont(buttonFont);
        clearButton.setFont(buttonFont);


        resultLabel.setHorizontalAlignment(SwingConstants.LEFT);
        resultLabel.setForeground(Color.BLACK);


        jPanel1.add(transformToMusicButton, null);
        jPanel1.add(clearButton, null);

        jPanel2.setLayout(new BorderLayout());
        jPanel2.add(jPanel3, BorderLayout.CENTER);
        jPanel2.add(jPanel4, BorderLayout.SOUTH);
        jPanel3.setLayout(new GridLayout(3,1));
        jPanel4.setLayout(new GridLayout(3,1));

        resultLabel.setFont(font);

        jPanel3.add(resultLabel, null);

        jPanel4.add(messageField, null);

        this.getRootPane().setDefaultButton(transformToMusicButton);

        if (packFrame) {
            this.pack();
        }
        else {
            this.validate();
        }
        //Center the window
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = this.getSize();
        if (frameSize.height > screenSize.height) {
            frameSize.height = screenSize.height;
        }
        if (frameSize.width > screenSize.width) {
            frameSize.width = screenSize.width;
        }
        this.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 4);
        this.setVisible(true);


        clearButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                messageField.setText("");
            }
        });

        transformToMusicButton.addActionListener(new ActionListener() {
                                                     public void actionPerformed(ActionEvent evt) {
                                                         buttonActionPerformed(evt);
                                                     }
                                                 }
        );



    }

    private void buttonActionPerformed(ActionEvent evt) {
        String text = messageField.getText();
        musicCipher.runApp(text);
    }

    //Overridden so we can exit when window is closed
    protected void processWindowEvent(WindowEvent e) {
        super.processWindowEvent(e);
        if (e.getID() == WindowEvent.WINDOW_CLOSING) {
            System.exit(0);
        }
    }

    //Main method
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        }
        catch(Exception e) {
            e.printStackTrace();
        }
        new Music_Cipher_GUI();
    }

    class Key extends JButton {
        String token;

        public Key(String token, Dimension dimension){
            this.token = token;
            setText(token);
            setFont(font);
            setPreferredSize(dimension);
        }
    }

    public class ImagePanel extends JPanel
    {
        /*the default image to use*/
        //String imageFile = "textToMusic text img.png";
        String imageFile;

        public ImagePanel(String imageFile)
        {
            super();
            this.imageFile = imageFile;
        }

//        public ImagePanel(String imageFile)
//        {
//            super();
//
//        }

        public ImagePanel(LayoutManager layout)
        {
            super(layout);
        }

        public void paintComponent(Graphics g)
        {
/*create image icon to get image*/
            ImageIcon imageicon = new ImageIcon(getClass().getResource(imageFile));
            Image image = imageicon.getImage();

/*Draw image on the panel*/
            super.paintComponent(g);

            if (image != null)
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        }
    }



}
