import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.File;

public class Editor extends JFrame {
    //Constructor
    Editor() {
        //Frame set-up
        super("Arena Game Map Editor");
        //Make fullscreen
        this.setSize(1008, 700);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);

        //Setting up editing panel
        JPanel editPanel = new editorPanel();
        editPanel.setLayout(null);

        //Adding main panel to frame
        this.add(editPanel);

        //Starting editor
        this.setVisible(true);
    }

    //Inner class for the actual map where the user will edit
    static class editorPanel extends JPanel{
        //Declaring variables
        Map map;
        int currentX;
        int currentY;
        int currentImage = 14;


        //Constructor
        public editorPanel(){
            setPreferredSize(new Dimension(1008, 700));
            //Adding mouselistener
            EditorMapListener listener = new EditorMapListener();
            this.addMouseListener(listener);
            map = new Map();
            //Creating save, load and exit
            JButton saveButton = new JButton("Save");
            JButton loadButton = new JButton("Load");
            JButton exitButton = new JButton("Exit");

            //Adding actionlisteners to save, load and exit buttons
            saveButton.addActionListener(new SaveButtonListener());
            loadButton.addActionListener(new LoadButtonListener());
            exitButton.addActionListener(new ExitButtonListener());

            saveButton.setBounds(350, 600, 100, 50);
            loadButton.setBounds(470, 600, 100, 50);
            exitButton.setBounds(590, 600, 100, 50);
            
            //Adding save and load buttons to south panel
            this.add(saveButton);
            this.add(loadButton);
            this.add(exitButton);
        }

        public void paintComponent(Graphics g){
            super.paintComponent(g);
            //Draw map
            map.drawMap(g);
            map.drawTiles(g);
            //Request Repaint
            repaint();
        }

        //Method to set the current image
        public void setCurrentImage(int currentImage){
            this.currentImage = currentImage;
        }

        //Inner class for mouse listener
        private class EditorMapListener implements MouseListener{

            public void mouseClicked(MouseEvent e) {
                System.out.println(e.getY());
                if(e.getY() < 512) {
                    currentX = e.getX() / 32;
                    currentY = e.getY() / 32;
                    map.editGrid(currentX, currentY, currentImage);
                }
                else{
                    if(e.getX() >= 0 && e.getX() <= 32){
                        setCurrentImage(0);
                    }
                    else if(e.getX() >= 36 && e.getX() <= 68){
                        setCurrentImage(1);
                    }
                    else if (e.getX() >= 72 && e.getX() <= 104){
                        setCurrentImage(2);
                    }
                    else if (e.getX() >= 108 && e.getX() <= 140){
                        setCurrentImage(3);
                    }
                    else if (e.getX() >= 144 && e.getX() <= 176){
                        setCurrentImage(4);
                    }
                    else if (e.getX() >= 180 && e.getX() <= 212){
                        setCurrentImage(5);
                    }
                    else if (e.getX() >= 216 && e.getX() <= 248){
                        setCurrentImage(6);
                    }
                    else if (e.getX() >= 252 && e.getX() <=284){
                        setCurrentImage(7);
                    }
                    else if (e.getX() >= 288 && e.getX() <= 320){
                        setCurrentImage(8);
                    }
                    else if (e.getX() >= 324 && e.getX() <= 356){
                        setCurrentImage(9);
                    }
                    else if (e.getX() >= 360 && e.getX() <= 392){
                        setCurrentImage(10);
                    }
                    else if (e.getX() >= 396 && e.getX() <= 428){
                        setCurrentImage(11);
                    }
                    else if (e.getX() >= 432 && e.getX() <= 464){
                        setCurrentImage(12);
                    }
                    else if (e.getX() >= 468 && e.getX() <= 500){
                        setCurrentImage(13);
                    }
                    else if (e.getX() >= 504 && e.getX() <= 536){
                        setCurrentImage(14);
                    }

                }
            }

            public void mousePressed(MouseEvent e) {
            }

            public void mouseReleased(MouseEvent e) {
            }

            public void mouseEntered(MouseEvent e) {
            }

            public void mouseExited(MouseEvent e) {
            }
        }

        //Inner class for save button action listener
        class SaveButtonListener implements ActionListener {
            public void actionPerformed(ActionEvent event){
                try {
                    map.saveMap(map.getGrid());
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }

        //Inner class for load button action listener
        class LoadButtonListener implements ActionListener{
            public void actionPerformed(ActionEvent event){
                map.setGrid(map.loadMap());
            }
        }

        //Inner class for exit button action listener
        class ExitButtonListener implements ActionListener{
            public void actionPerformed(ActionEvent e){
                System.exit(0);
            }
        }
    }
}

//Class representing the map to be edited
class Map {
    final int width = 31;
    final int height = 16;
    final int numImages = 15;
    BufferedImage[] images = new BufferedImage[numImages];
    int[][] grid = new int[width][height];

    //Constructor
    public Map() {
        loadImages();
        fillGrid();
    }

    //Method to load in images
    public void loadImages() {
        for (int i = 0; i < numImages; i++) {
            try {
                images[i] = (ImageIO.read(new File("mainHub/" + Integer.toString(i) + ".png")));
            } catch (Exception e) {
                System.out.println("error");
            }
        }
    }

    //Method to fill initial grid
    public void fillGrid() {
        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                grid[i][j] = 14;
            }
        }
    }

    //Method to draw map
    public void drawMap(Graphics g){
        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                g.drawImage(images[grid[i][j]], i * 32, j * 32, null);
            }
        }
    }

    //Method to draw tiles at bottom
    public void drawTiles(Graphics g){
        for(int i = 0; i < 15; i++){
            g.drawImage(images[i], i * 36, 520, null);
        }
    }

    //Method to edit grid
    public void editGrid(int x, int y, int image){
        grid[x][y] = image;
    }

    //Method to save map to a text file
    public void saveMap(int[][] savedGrid){
        try {
            File file = new File("map.txt");
            PrintWriter pw = new PrintWriter(file);
            for(int i = 0; i < width; i++){
                for(int j = 0; j < height; j++){
                    pw.println(savedGrid[i][j]);
                }
            }
            pw.close();
        }catch(IOException e){
            System.out.println("Error");
        }
    }

    //method to load map from a text file
    public int[][] loadMap(){
        File file = new File("map.txt");
        int[][] newGrid = new int[width][height];
        try {
            Scanner scanner = new Scanner(file);
            String num;
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    num = scanner.nextLine();
                    newGrid[i][j] = Integer.parseInt(num);
                }
            }
            scanner.close();
        }catch(IOException e){
            e.printStackTrace();
        }
        return newGrid;
    }

    //Getters and setters
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getNumImages() {
        return numImages;
    }

    public BufferedImage[] getImages() {
        return images;
    }

    public void setImages(BufferedImage[] images) {
        this.images = images;
    }

    public int[][] getGrid() {
        return grid;
    }

    public void setGrid(int[][] grid) {
        this.grid = grid;
    }
}