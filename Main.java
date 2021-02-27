package com.company;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.*;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Main extends JPanel {
    private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private static final int dimension_X = (int) screenSize.getWidth();
    private static final int dimension_Y = (int) screenSize.getHeight() * 11/12;
    private static final JFrame frame = new JFrame();
    private static JPanel mainPanel = new JPanel();
    private static ArrayList<atomMotion> atomList = new ArrayList<>();
    private static atomMotion atom;

    private static void createFrame()
    {
        frame.setTitle("Ideal Gas Laws");
        frame.setSize(dimension_X, dimension_Y);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        //frame.setIconImage(LogoImage.getImage());
        frame.setResizable(false);

        createMainPanel();

        frame.add(mainPanel);

        frame.setVisible(true);
    }

    private static JPanel displayPanel = new Main();
    private static JPanel constantsPanel = new JPanel();
    private static JPanel constantsOptPanel = new JPanel();
    private static int Panel_X_Dimension = (int) dimension_X * 3 / 4;
    private static int Panel_Y_Dimension = (int) dimension_Y;
    private static int pistonLocation = Panel_X_Dimension * 9 / 20;
    private static JPanel tempGradPanel =  new createTempGrad();

    private static JButton pistonStemMove = new JButton();
    private static JButton pistonCylinderMove = new JButton();
    private static int width;
    private static double[] preVel = new double[2];
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //System.out.println("enter");
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.LIGHT_GRAY);
        int val = Panel_X_Dimension * 9/10;

        g2.fillRect(0, Panel_Y_Dimension / 20, val + 20, Panel_Y_Dimension * 17 / 20);
        //g2.setColor(Color.GREEN);
        //g2.fillRect(0,0,Panel_X_Dimension, dimension_Y);
        //g2.setColor(Color.LIGHT_GRAY);
        int width = val + 10;
        int height = Panel_Y_Dimension * 17 / 20;
        //System.out.println("Width: " + width + " Height: " + height);



        //System.out.println("Position: " + pistonLocation);
        int pistonStemWidthH = Panel_Y_Dimension / 40;
        int pistonStemMidLoc = Panel_Y_Dimension / 2 - pistonStemWidthH;
        g2.setColor(new Color(255, 133, 254));
        g2.fillRect(0, pistonStemMidLoc - pistonStemWidthH / 2, pistonLocation - 40, pistonStemWidthH * 2);
        g2.setPaint(Color.MAGENTA);
        g2.fillRect(0, pistonStemMidLoc, pistonLocation - 40, pistonStemWidthH * 2);
        g2.setPaint(Color.MAGENTA);
        g2.fillOval(pistonLocation - 60, Panel_Y_Dimension / 20, 40, Panel_Y_Dimension*17/20+1);
        g2.fillRect(pistonLocation - 40, Panel_Y_Dimension / 20, 60, Panel_Y_Dimension*17/20);
        g2.setPaint(new Color(255, 133, 254));
        g2.fillOval(pistonLocation, Panel_Y_Dimension / 20 - 1, 40, Panel_Y_Dimension*17/20+1);

        int buttonWidth1S = pistonStemMidLoc - pistonStemWidthH / 2;
        int buttonWidth2S = pistonStemMidLoc + pistonStemWidthH * 2;
        int buttonWidthS = buttonWidth2S - buttonWidth1S;
        pistonStemMove.setBounds(0, pistonStemMidLoc - pistonStemWidthH / 2, pistonLocation - 40, buttonWidthS);
        pistonStemMove.setOpaque(false);
        pistonStemMove.setContentAreaFilled(false);
        pistonStemMove.setBorderPainted(false);

        int buttonWidthC = pistonLocation + 40 - pistonLocation + 60;
        pistonCylinderMove.setBounds(pistonLocation - 60, Panel_Y_Dimension / 20, buttonWidthC, Panel_Y_Dimension*17/20);
        pistonCylinderMove.setOpaque(false);
        pistonCylinderMove.setContentAreaFilled(false);
        pistonCylinderMove.setBorderPainted(false);

        g2.setPaint(Color.DARK_GRAY);
        //g2.fillOval(0, 0, 100, Panel_Y_Dimension * 18 / 20);

        int r = Panel_X_Dimension * 9 / 10 + 20;
        //System.out.println(pistonLocation+20);
        atomMotionPanel.setBounds(pistonLocation+20, Panel_Y_Dimension / 20, r - pistonLocation - 20, Panel_Y_Dimension * 17 /20);
        //atomMotionPanel.setBounds(0, Panel_Y_Dimension / 20, 100, 100);
        //displayPanel.add(atomMotionPanel);
        g2.fillOval(Panel_X_Dimension * 9 / 10, Panel_Y_Dimension / 20 - 1, 40, Panel_Y_Dimension * 17 / 20 + 1);
        Width = Panel_X_Dimension * 9 / 10 + 20 - pistonLocation - 20;
        displayPanel.add(pistonCylinderMove);
        displayPanel.add(pistonStemMove);

        if(pistonLocation == Panel_X_Dimension * 9 / 10 && s == 0) {
            //System.out.println("hi");
            System.out.println("hhhh");
            //
            for(int i = 0; i < noOfAtoms; i++) {
                preVels.add(new double[] {atomList.get(i).getVelX(), atomList.get(i).getVelY()});
                atomList.get(i).setVelX(0);
                atomList.get(i).setVelY(0);
            }
            s = 1;
        }
        else if(255 - tempVal == 0 && s == 0) {
            System.out.println("absolute zero");
            for(int i = 0; i < noOfAtoms; i++) {
                preVels.add(new double[] {atomList.get(i).getVelX(), atomList.get(i).getVelY()});
                atomList.get(i).setVelX(0);
                atomList.get(i).setVelY(0);
            }
            s = 1;
        }
        else if(s == 1 && pistonLocation < Panel_X_Dimension * 9 / 10 && 255 - tempVal!= 0) {

            for(int i = 0; i < noOfAtoms; i++) {
                atomList.get(i).setVelX(preVels.get(i)[0]);
                atomList.get(i).setVelY(preVels.get(i)[1]);
            }
            s = 0;
        }
        System.out.println("frozen1"+atomList.get(0).getVelX());
        //System.out.println("Volume = " +  0.108577633008 * (921 - pistonLocation));
        int tikLocation = val + 20;
        this.width = val + 20;
        int div = tikLocation / 20;
        //g2.setColor(Color.BLACK);

        for(int i = 1; i < 20; i++) {
            tikLocation -= div;
            int volVal = i * 5;

            //g2.fillRect(400,400,400,400);
            g2.drawString("" + volVal, tikLocation, Panel_Y_Dimension * 18 / 20 - 50);
            if(pistonLocation + 20 != tikLocation && i * 5 == 50) {
                if(z == 0) {
                    pistonLocation = tikLocation - 20;
                    z++;
                    displayPanel.repaint();
                }
            }

            g2.drawLine(tikLocation, Panel_Y_Dimension * 18 / 20, tikLocation, Panel_Y_Dimension * 18 / 20 - 40);

        }
        //g2.rotate(Math.toRadians(-90));
        g2.setColor(new Color(0, 122, 109));
        for(int i = 0; i < noOfAtoms; i++) {

            if (r == 0) {
                g2.fillOval(x, y, 10, 10);
                r++;
            } else {
                g2.fillOval(atomList.get(i).getX(), atomList.get(i).getY(), 10, 10);
            }
        }
        //int p = Panel_Y_Dimension / 20;
        //int h = p + Height;
        //System.out.println( Height +","+y);


        repaint();
    }
    private static int z = 0;
    private static ButtonGroup buttonList = new ButtonGroup();
    private static JRadioButton Pressure = new JRadioButton("Pressure");
    private static JRadioButton Volume = new JRadioButton("Volume");
    private static JRadioButton Temperature = new JRadioButton("Temperature");
    private static JLabel Title = new JLabel("Keep Constant");
    private static void createRadioButtonGroup() {
        Title.setPreferredSize(new Dimension(dimension_X / 4, dimension_Y / 15));
        //constantsOptPanel.setPreferredSize(new Dimension(dimension_X / 4, dimension_Y / 3));
        //constantsOptPanel.setBackground(Color.GREEN);
        constantsOptPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        //constantsOptPanel.setAlignmentX(Component.LEFT_ALIGNMENT);;
        Title.setForeground(new Color(178, 206, 228));
        Title.setFont(new Font(Font.SERIF, Font.BOLD, 25));
        Pressure.setForeground(new Color(154, 179, 193));
        constantsOptPanel.add(Title);
        Pressure.setActionCommand("Pressure");
        Pressure.setOpaque(false);
        Pressure.addActionListener(new radioButtonAction());
        Volume.setActionCommand("Volume");
        Volume.setOpaque(false);
        Volume.addActionListener(new radioButtonAction());
        Volume.setForeground(new Color(154, 179, 193));
        Temperature.setActionCommand("Temperature");
        Temperature.setOpaque(false);
        Temperature.addActionListener(new radioButtonAction());
        Temperature.setForeground(new Color(154, 179, 193));
        Pressure.setSelected(true);
        pressure = true;

        tempSlider.setEnabled(true);
        pistonDisabled = false;

        calcPressure();

        System.out.println("Pressure: " + pressureConstantVal);
        //constantsOptPanel.add(Box.createRigidArea(new Dimension(5, 10)));
        constantsOptPanel.add(Pressure);
        constantsOptPanel.add(Box.createRigidArea(new Dimension(100, 1)));
        constantsOptPanel.add(Volume);
        constantsOptPanel.add(Box.createRigidArea(new Dimension(100, 1)));
        constantsOptPanel.add(Temperature);
        //constantsOptPanel.add(Box.createRigidArea(new Dimension(5, 20)));
        buttonList.add(Pressure);
        buttonList.add(Volume);
        buttonList.add(Temperature);
    }

    private static void calcPressure() {
        double temp = 255 - tempSlider.getValue();
        double volum = 100 - (double) 100 * (pistonLocation + 20) / (Panel_X_Dimension * 9 / 10 + 10);
        System.out.println("t: " + temp + " v:" + volum);
        pressureConstantVal = Double.parseDouble(molesInput.getText()) * R * temp / volum;
        pressureVal.setText(String.format("%.2f", pressureConstantVal));
    }

    private static ArrayList<double[]> preVels = new ArrayList<>();
    private static int listSize = 10;
    private static int s = 0;
    private static int f = 0;
    //private static Timer t;
    private static int x;
    private static int y = Panel_Y_Dimension / 20 + 100;
    private static int velX = 10;
    private static int velY = 10;
    private static int Width;// = Panel_X_Dimension * 9 / 10 + 20 - pistonLocation - 20;
    private static int Height = Panel_Y_Dimension * 17 / 20;

    //private static JLabel imageContainer = new JLabel();
    private static JButton molesSetButton = new JButton("Set");
    private static int r = 0;
    private static int p = 0;
    private static int l = 0;

    private static JPanel totalDisplayPanel = new JPanel();
    private static JPanel displayOverLayerPanel = new overLayPanel();
    private static JPanel atomMotionPanel = new JPanel();//atomMotion(Panel_X_Dimension * 9 / 10 + 20 - pistonLocation - 20, Panel_Y_Dimension * 17 / 20);
    private static JPanel tempPanel = new JPanel();
    private static JPanel pressurePanel = new JPanel();
    private static int v = 0;
    private static int start;
    private static int MouseLocationX;
    private static JPanel bottomSidePanel = new paintSidePanel();
    private static class moveListener implements MouseListener, MouseMotionListener
    {
        int moved;
        @Override
        public void mouseDragged(MouseEvent e) {

            //System.out.println("start: "+start);
            //System.out.println("startMouse: "+MouseLocationX);
            //System.out.println("MouseLoc: " + e.getX());
            //System.out.println("Piston2: " + pistonLocation);
            //displayPanel.paintImmediately(0,0,Panel_X_Dimension,Panel_Y_Dimension);
            if(!pistonDisabled) {
                temperatureChange = false;
                //pressureChange = false;
                volumeChange = true;
                moved = e.getXOnScreen() - MouseLocationX;
                if (start + moved <= Panel_X_Dimension * 9 / 10 && start + moved >= 5) {
                    pistonLocation = start + moved;
                    effect();
                }
            }
            e.consume();
        }

        @Override
        public void mousePressed(MouseEvent e) {
            if(!pistonDisabled) {
                if (v == 0) {
                    //System.out.println("Clicked");

                    start = pistonLocation;

                    MouseLocationX = e.getXOnScreen();
                    //System.out.println("start1: "+MouseLocationX);
                    v++;
                }
            }
        }

        @Override
        public void mouseMoved(MouseEvent e) { e.consume(); }

        @Override
        public void mouseReleased(MouseEvent e) {
            v = 0;
        }

        @Override
        public void mouseEntered(MouseEvent e) { e.consume(); }

        @Override
        public void mouseExited(MouseEvent e) { e.consume(); }

        @Override
        public void mouseClicked(MouseEvent e) { e.consume(); }
    }

    private static TextField molesInput = new TextField("5.0");
    private static JPanel molesPanel = new JPanel();
    private static ArrayList<Thread> threadList = new ArrayList<>();
    private static ArrayList<JLabel> atomImageList = new ArrayList<>();
    private static ArrayList<Timer> timerList = new ArrayList<>();
    private static int noOfAtoms = 0;
    private static int click = 0;
    private static JSlider tempSlider = new JSlider(JSlider.HORIZONTAL, -255, 255, 0);
    private static boolean pistonDisabled = false;
    private static void createMainPanel() {
        displayPanel.setBackground(Color.BLUE);
        if (f == 0) {
            x = pistonLocation + 80;
            f++;
        }
        //atom = new atomMotion(x,y);
        //t = new Timer(10, new AnimationListener(i));
        bottomSidePanel.setLayout(new OverlayLayout(bottomSidePanel));
        bottomSidePanel.add(constantsPanel);

        displayPanel.setLayout(new OverlayLayout(displayPanel));
        //imageContainer.setIcon(atomImage);
        //imageContainer.setOpaque(false);

        //imageContainer.setVisible(false);

        displayOverLayerPanel.setPreferredSize(new Dimension(Panel_X_Dimension, Panel_Y_Dimension));
        //displayPanel.setLayout(null);
        //displayOverLayerPanel.setBounds(0,0,600,Panel_Y_Dimension);
        displayOverLayerPanel.setOpaque(false);
        //displayOverLayerPanel.setBackground(new Color(0,0,0,0));


        displayPanel.add(displayOverLayerPanel);


        //displayPanel.add(imageContainer);

        mainPanel.setBackground(Color.BLACK);
        mainPanel.setPreferredSize(new Dimension(dimension_X, dimension_Y));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));

        displayPanel.setPreferredSize(new Dimension(dimension_X * 3 / 4, dimension_Y));
        displayPanel.setOpaque(false);

        tempTitle.setForeground(new Color(154, 179, 193));
        tempValue.setForeground(new Color(154, 179, 193));
        temUnit.setForeground(new Color(154, 179, 193));
        tempPanel.setLayout(new FlowLayout());
        tempPanel.setOpaque(false);
        tempPanel.add(tempTitle);
        double h = 255 - tempSlider.getValue();
        tempValue.setText("" + h);
        tempPanel.add(tempValue);
        tempPanel.add(temUnit);

        pressureTitle.setForeground(new Color(154, 179, 193));
        pressureVal.setForeground(new Color(154, 179, 193));
        pressureUnit.setForeground(new Color(154, 179, 193));
        pressurePanel.setLayout(new FlowLayout());
        pressurePanel.setOpaque(false);
        pressurePanel.add(pressureTitle);
        double temp = 255 - tempVal;
        double H;
        double volum = 100 - (double) 100 * (pistonLocation + 20) / width;

        if(temp == 0 || volum == 0) {
            H = 0;
        }
        else {
            H = Double.parseDouble(molesInput.getText()) * R * temp / volum;
        }

        pressureVal.setText(String.format("%.2f", H));
        System.out.println("----------" + String.format("%.2f", H));
        pressurePanel.add(pressureVal);
        pressurePanel.add(pressureUnit);

        molesPanel.setLayout(new FlowLayout());
        JLabel molesText = new JLabel("Moles: ");
        molesPanel.setOpaque(false);
        molesPanel.add(molesText);
        molesInput.setBounds(dimension_X - 50, 100, 20, 30);
        molesInput.setPreferredSize(new Dimension(10, 30));
        molesPanel.add(molesInput);
        molesSetButton.setPreferredSize(new Dimension(60, 24));
        molesPanel.add(molesSetButton);
        molesText.setForeground(new Color(154, 179, 193));

        tempSlider.setPaintTicks(false);
        tempSlider.setOpaque(false);
        tempGradPanel.setOpaque(false);
        constantsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        constantsPanel.setPreferredSize(new Dimension(0, dimension_Y));
        //bottomSidePanel.setBounds(//dimension_X / 4);
        displayPanel.setPreferredSize(new Dimension(Panel_X_Dimension, Panel_Y_Dimension));
        bottomSidePanel.setPreferredSize(new Dimension(dimension_X / 4 - 15, dimension_Y));
        bottomSidePanel.setBackground(Color.RED);
        constantsPanel.setOpaque(false);
        //JButton cancelButton = new JButton();
        //cancelButton.addActionListener(new cancelListener());
        molesSetButton.addActionListener(new cancelListener());
        //constantsPanel.setLayout(new BoxLayout(constantsPanel, BoxLayout.Y_AXIS));

        resetButton.addActionListener(new resetListener());


        constantsOptPanel.setPreferredSize(new Dimension(dimension_X / 4 - 100, dimension_Y / 3 + 75));
        constantsOptPanel.setOpaque(false);
        //constantsOptPanel.setBackground(Color.BLACK);
        //constantsOptPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        tempGradPanel.setPreferredSize(new Dimension(dimension_X / 4  - 110, 24));



        //constantsOptPanel.setLayout(new BoxLayout(constantsOptPanel, BoxLayout.Y_AXIS));
        createRadioButtonGroup();
        constantsOptPanel.add(molesPanel);
        constantsOptPanel.add(Box.createRigidArea(new Dimension(100, 10)));
        tempSlider.setPreferredSize(new Dimension(dimension_X / 4  - 110, 24));

        tempSlider.addChangeListener(new tempChangeListener());
        JPanel together = new JPanel();
        together.setLayout(new BoxLayout(together, BoxLayout.Y_AXIS));
        together.setOpaque(false);

        together.add(tempPanel);
        together.add(Box.createRigidArea(new Dimension(10,10)));
        together.add(pressurePanel);

        constantsOptPanel.add(tempSlider);
        constantsOptPanel.add(tempGradPanel);
        constantsOptPanel.add(together);
        constantsOptPanel.add(resetButton);
        //constantsOptPanel.add(Box.createRigidArea(new Dimension(100, 10)));
        //  constantsOptPanel.add(pressurePanel);
        //constantsOptPanel.add(buttonList);
        //constantsPanel.add(Box.createRigidArea(new Dimension(20, 10)));
        constantsPanel.add(constantsOptPanel);
        //constantsPanel.add(cancelButton);

        pistonStemMove.addMouseMotionListener(new moveListener());
        pistonStemMove.addMouseListener(new moveListener());

        pistonCylinderMove.addMouseListener(new moveListener());

        pistonCylinderMove.addMouseMotionListener(new moveListener());

        //totalDisplayPanel.setLayout(new OverlayLayout(totalDisplayPanel));
        totalDisplayPanel.setBackground(Color.BLACK);
        totalDisplayPanel.add(displayPanel);
        //totalDisplayPanel.add(displayOverLayerPanel);
        mainPanel.add(totalDisplayPanel);
        mainPanel.add(bottomSidePanel);

        createAtoms();

        //frame.add();
        //diagram.drawing();

    }
    private static int tempColorVal = 0;
    private static char tempState = 'n';
    private static double tempVal = 0;
    private static boolean volumeChange;
    private static boolean temperatureChange;
    private static JLabel pressureTitle = new JLabel("Pressure: ");
    private static JLabel pressureVal = new JLabel();
    private static JLabel pressureUnit = new JLabel("atm");
    private static JLabel tempTitle = new JLabel("Temperature: ");
    private static JLabel tempValue = new JLabel();
    private static JLabel temUnit = new JLabel("K");
    private static void effect() {
        if(pressure) {
            if(volumeChange) {
                double volum = 100 - (double) 100 * (pistonLocation + 20) / width;

                double temp = pressureConstantVal * volum / (R * Double.parseDouble(molesInput.getText()));
                System.out.println(volum + "," + temp);
                double j = 255 - temp;
                //tempValue.setText("" + j);
                tempSlider.setValue(255 - (int) temp);
            }
            else if(temperatureChange) {
                double temp = 255 - tempVal;
                double volum = R * Double.parseDouble(molesInput.getText()) * temp / pressureConstantVal;

                pistonLocation = (int) ((volum - 100) * -width / 100) - 20;
                displayPanel.paintImmediately(0,0,Panel_X_Dimension,Panel_X_Dimension);
            }
        }
        else if(volume) {
            if(temperatureChange) {
                double temp = 255 - tempVal;
                double P = R * Double.parseDouble(molesInput.getText()) * temp / volumeConstantVal;
                pressureVal.setText(String.format("%.2f", P));
            }
        }
        else if(temperature) {
            if(volumeChange) {
                double volum = 100 - (double) 100 * (pistonLocation + 20) / width;
                double P = R * Double.parseDouble(molesInput.getText()) * temperatureConstantVal / volum;
                pressureVal.setText(String.format("%.2f", P));
            }
        }
    }

    private static class tempChangeListener implements ChangeListener {
        @Override
        public void stateChanged(ChangeEvent e) {
            temperatureChange = true;
            volumeChange = false;
            //pressureChange = false;
            tempColorVal = tempSlider.getValue();
            tempVal = tempSlider.getValue();
            double j = 255 - tempVal;
            tempValue.setText("" + j);
            if(tempColorVal > 0) {
                tempState = 'c';
            }
            else if(tempColorVal < 0) {
                tempState = 'h';
                tempColorVal*=-1;
            }
            else
                tempState = 'n';
            //System.out.println(tempColorVal);
            //System.out.println("changing " + tempVal);
            double tVal = (255 - tempVal) * 7 / 255;
            for(int i = 0; i < noOfAtoms; i++) {
                double X;
                double Y;
                double resultTheta = 0.0;
                if(atomList.get(i).getVelX() == 0 && atomList.get(i).getVelY() == 0) {
                    //System.out.println("hi");
                    resultTheta = rand.nextDouble() * 359 + 1;
                }
                else {
                    X = atomList.get(i).getVelX();
                    Y = -1 * atomList.get(i).getVelY();
                    if(X == 0 && Y > 0) {
                        resultTheta = 90;
                    }
                    else if(X == 0 && Y < 0) {
                        resultTheta = 270;
                    }
                    else if(Y == 0 && X > 0) {
                        resultTheta = 0;
                    }
                    else if(Y == 0 && X < 0) {
                        resultTheta = 180;
                    }
                    else {
                        double resultThetaPre = Math.toDegrees(Math.atan((double) Y / X));
                        if(X > 0 && Y > 0) {
                            resultTheta = resultThetaPre;
                        }
                        else if(X < 0 && Y > 0) {
                            resultTheta = 180 + resultThetaPre;
                        }
                        else if(X < 0 && Y < 0) {
                            resultTheta = 180 + resultThetaPre;
                        }
                        else if(X > 0 && Y < 0) {
                            resultTheta = 360 + resultThetaPre;
                        }
                    }
                }



                if(tVal < 1 && pistonLocation + 20 < width && 255 - tempVal != 0)
                    tVal = 2;


                atomList.get(i).setVelX(tVal * Math.cos(Math.toRadians(resultTheta)));
                atomList.get(i).setVelY(-1 * tVal * Math.sin(Math.toRadians(resultTheta)));
                System.out.println("frozen2"+atomList.get(0).getVelX());
            }

            bottomSidePanel.paintImmediately(0,0,Panel_X_Dimension,Panel_Y_Dimension);
            displayPanel.paintImmediately(0,0,Panel_X_Dimension, Panel_Y_Dimension);
            effect();
        }
    }
    private static double pressureConstantVal;
    private static double volumeConstantVal;
    private static double temperatureConstantVal;
    private static boolean pressure;
    private static boolean volume;
    private static boolean temperature;
    private static double R =  0.082057;
    private static JButton resetButton = new JButton("Reset");
    private static class radioButtonAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getActionCommand().equals("Pressure")) {
                double temp = 255 - tempVal;
                double volum = 100 - (double) 100 * (pistonLocation + 20) / width;
                tempSlider.setEnabled(true);
                pistonDisabled = false;

                if(temp == 0 || volum == 0) {
                    pressureConstantVal = 0;
                    tempSlider.setEnabled(false);
                }
                else {
                    pressureConstantVal = Double.parseDouble(molesInput.getText()) * R * temp / volum;
                }
                System.out.println("Pressure: " + pressureConstantVal);
                pressure = true;
                volume = false;
                temperature = false;
            }
            else if(e.getActionCommand().equals("Volume")) {
                tempSlider.setEnabled(true);
                pistonDisabled = false;
                double temp = 255 - tempVal;
                double volum = 100 - (double) 100 * (pistonLocation + 20) / width;
                pistonDisabled = true;
                if(temp == 0 && volum == 0) {
                    pressureConstantVal = 0;
                    tempSlider.setEnabled(false);
                }
                // System.out.println(MylinderMove.isEnabled());
                volumeConstantVal = 100 - (double) 100 * (pistonLocation + 20) / width;

                System.out.println("Volume: " + volumeConstantVal);

                pressure = false;
                volume = true;
                temperature = false;
            }
            else if(e.getActionCommand().equals("Temperature")) {
                tempSlider.setEnabled(true);
                pistonDisabled = false;

                tempSlider.setEnabled(false);

                temperatureConstantVal = 255 - tempVal;

                System.out.println("Temperature: " + temperatureConstantVal);

                pressure = false;
                volume = false;
                temperature = true;
            }
        }
    }

    private static void createAtoms() {
        for (int i = 0; i < listSize; i++) {
            //System.out.println("="+i);

            //atomImageList.add(new JLabel(images.getAtomImage()));
            //atomImageList.get(i).setBounds(x,y,10,10);

            atomList.add(new atomMotion(x, y));
            //threadList.add(new Thread(new threadFunc(i)));
            //System.out.println(threadList.size());
            timerList.add(new Timer());
            int finalI = i;
            noOfAtoms = finalI + 1;
            timerList.get(i).scheduleAtFixedRate(new TimerTask() {
                public void run() {
                    //System.out.println(finalI + " - " + atomList.get(finalI).getVelX() + "," + atomList.get(finalI).getVelY());
                    //System.out.println(finalI + " speed:" + calcMag(atomList.get(finalI).getVelX(), atomList.get(finalI).getVelY()));
                    atomList.get(finalI).setCollisionStageOneCheck(false);

                    if (click >= 10) {
                        //System.out.println(finalI + " location: " + atomList.get(finalI).getX() + ", " + atomList.get(finalI).getY());
                        //System.out.println(finalI + " -- " + calcMag(atomList.get(finalI).getVelX(), atomList.get(finalI).getVelY()));
                        int[] result = detectCollision(finalI, noOfAtoms);

                        if (result[0] == 1) {
                            //System.out.println(finalI + " collision detected");

                            atomList.get(finalI).setCollisionStageOneCheck(true);
                            //System.out.println(finalI + " collision " + atomList.get(finalI).getCollisionStageOneCheck());
                            try {
                                while (!atomList.get(result[1]).getCollisionStageOneCheck()) {
                                    // System.out.println(finalI + " in loop");
                                    try {
                                        Thread.sleep(5);
                                    } catch (InterruptedException e) {
                                        System.out.println("error caught");
                                        e.printStackTrace();
                                    }
                                }
                            }
                            catch(IndexOutOfBoundsException e) {
                                System.out.println("error caught");
                                e.printStackTrace();
                            }

                            try {
                                //System.out.println(finalI + " --- " + calcMag(atomList.get(finalI).getVelX(), atomList.get(finalI).getVelY()));
                                //System.out.println(finalI + " --- " + atomList.get(finalI).getVelX() + "," + atomList.get(finalI).getVelY());
                                double[] finalVelocity = calcFinalVelocity(finalI, result[1]);
                                atomList.get(finalI).setVelX((finalVelocity[0] * Math.cos(Math.toRadians(finalVelocity[1]))));
                                atomList.get(finalI).setVelY((-1 * finalVelocity[0] * Math.sin(Math.toRadians(finalVelocity[1]))));
                                //if(atomList.get(finalI).isCalculating()) {
                                //  atomList.get(finalI).setCalculating(false);
                                // System.out.println(finalI + " ----- " + calcMag(atomList.get(finalI).getVelX(), atomList.get(finalI).getVelY()));
                                // System.out.println(finalI + " ------ " + calcMag(finalVelocity[0] * Math.cos(Math.toRadians(finalVelocity[1])), -1 * finalVelocity[0] * Math.sin(Math.toRadians(finalVelocity[1]))));
                                //}
                            } catch (InterruptedException e) {
                                System.out.println("error caught");
                                e.printStackTrace();
                            }
                        }
                    }
                    action(finalI);
                    if (click < 10)
                        click++;
                    //System.out.println(finalI);


                }
            }, 0, 10);
            //displayPanel.add(atomImageList.get(i));
            //threadList.get(i).start();
        }
    }
    private static int m = 0;
    private static int[] detectCollision(int indexNo, int sampleSize) {
        int[] result = new int[2];
        for(int i = 0; i < sampleSize; i++) {
            if(i != indexNo) {
                if (atomList.get(indexNo).getX() <= atomList.get(i).getX() + 10 && atomList.get(indexNo).getX() + 10 >= atomList.get(i).getX() && atomList.get(indexNo).getY() <= atomList.get(i).getY() + 10 && atomList.get(indexNo).getY() + 10 >= atomList.get(i).getY()) {
                    result[0] = 1;
                    result[1] = i;
                }
            }
        }
        return result;
    }

    private static class paintSidePanel extends JPanel {

        @Override

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            //System.out.println("painting " + tempState + " " + tempColorVal);
            Graphics2D g2D = (Graphics2D) g;
            if(tempState == 'c') {
                GradientPaint grad = new GradientPaint(dimension_X / 8,0,Color.BLACK,dimension_X / 4, 0, new Color(0,0,tempColorVal));
                g2D.setPaint(grad);
                g2D.fillRect(0,0,dimension_X / 4 + 30, dimension_Y);
            }
            else if(tempState == 'h') {
                GradientPaint grad = new GradientPaint(dimension_X / 8,0,Color.BLACK,dimension_X / 4,0, new Color(tempColorVal,0,0));
                g2D.setPaint(grad);
                g2D.fillRect(0,0,dimension_X / 4 + 30, dimension_Y);
            }
            else {
                g2D.setPaint(Color.BLACK);
                g2D.fillRect(0,0,dimension_X / 4 + 30, dimension_Y);
            }
            //Graphics2D g2D = (Graphics2D) g;


        }


    }

    private static class overLayPanel extends JPanel {
        @Override

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2D = (Graphics2D) g;
            g2D.setColor(Color.BLACK);
            g2D.fillRect(Panel_X_Dimension * 9 / 10 + 20, 0, 80, Panel_Y_Dimension);
            g2D.fillRect(0,0, Panel_X_Dimension, Panel_Y_Dimension / 20);
            g2D.fillRect(0,Panel_Y_Dimension * 18 / 20, Panel_X_Dimension, Panel_Y_Dimension / 20);
            g2D.setColor(Color.DARK_GRAY);
            g2D.fillOval(Panel_X_Dimension * 9 / 10, Panel_Y_Dimension / 20 - 1, 40, Panel_Y_Dimension * 17 / 20 + 1);

            //System.out.println("painting " + tempState + " " + tempColorVal);
            Graphics2D g2 = (Graphics2D) g;
            if (tempState == 'c') {
                GradientPaint grad = new GradientPaint(0, 0, new Color(0, 0, tempColorVal), (float) dimension_X / 4, 0, Color.BLACK);
                g2D.setPaint(grad);
                g2D.fillRect(0,0,dimension_X / 4 + 30, Panel_Y_Dimension / 20);
                g2D.fillRect(0,Panel_Y_Dimension * 18 / 20, Panel_X_Dimension, Panel_Y_Dimension / 20);
            } else if (tempState == 'h') {
                GradientPaint grad = new GradientPaint(0, 0, new Color(tempColorVal, 0, 0), (float) dimension_X / 4, 0, Color.BLACK);
                g2D.setPaint(grad);
                g2D.fillRect(0,0, Panel_X_Dimension, Panel_Y_Dimension / 20);
                g2D.fillRect(0,Panel_Y_Dimension * 18 / 20, Panel_X_Dimension, Panel_Y_Dimension / 20);
            } else {
                g2D.setPaint(Color.BLACK);
                g2D.fillRect(0,0, Panel_X_Dimension, Panel_Y_Dimension / 20);
                g2D.fillRect(0,Panel_Y_Dimension * 18 / 20, Panel_X_Dimension, Panel_Y_Dimension / 20);
            }

        }


    }

    private static class createTempGrad extends JPanel {
        @Override

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            //Graphics2D g2D = (Graphics2D) g;
            Graphics2D g2D = (Graphics2D) g;
            GradientPaint grad = new GradientPaint(0,0,Color.RED,dimension_X / 4  - 10, 24, Color.BLUE);
            g2D.setPaint(grad);
            g2D.fillRect(0,0,dimension_X / 4  - 10, 24);
        }


    }

    private static Random rand = new Random();
    public static void main(String[] args) {
        createFrame();
    }

    private static class resetListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            for(int i = 0; i < noOfAtoms; i++) {
                timerList.get(i).cancel();
            }
            if(2 * Double.parseDouble(molesInput.getText()) > 250) {
                molesInput.setText("" + 250);
            }

            noOfAtoms = 0;
            listSize = 0;
            click = 0;
            atomList.clear();
            timerList.clear();
            listSize = (int)(2 * Double.parseDouble(molesInput.getText()));

            createAtoms();
            tempSlider.setValue(0);
            tempColorVal = 0;
            tempState = 'n';
            z = 0;
            tempVal = 0;
            calcPressure();
            constantsPanel.paintImmediately(0,0,Panel_X_Dimension,Panel_Y_Dimension);
            displayPanel.paintImmediately(0,0,Panel_X_Dimension, Panel_Y_Dimension);
            displayPanel.repaint();
        }
    }

    private static class cancelListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            for(int i = 0; i < noOfAtoms; i++) {
                timerList.get(i).cancel();
            }
            if(2 * Double.parseDouble(molesInput.getText()) > 250) {
                molesInput.setText("" + 250);
            }

            noOfAtoms = 0;
            listSize = 0;
            click = 0;
            atomList.clear();
            timerList.clear();
            listSize = (int)(2 * Double.parseDouble(molesInput.getText()));
            System.out.println();
            createAtoms();
            tempSlider.setValue(0);
            tempColorVal = 0;
            tempState = 'n';
            calcPressure();
            constantsPanel.paintImmediately(0,0,Panel_X_Dimension,Panel_Y_Dimension);
            displayPanel.paintImmediately(0,0,Panel_X_Dimension, Panel_Y_Dimension);
            displayPanel.repaint();
        }
    }

    private static void action(int indexNo) {
        //System.out.println(indexNo);

        //System.out.println("-"+indexNo);
        if(atomList.get(indexNo).getX() < pistonLocation + 20 || atomList.get(indexNo).getX() + 10 > pistonLocation + 20 + Width) {

            //System.out.println("first" + c + "," + d);

            atomList.get(indexNo).setVelX(atomList.get(indexNo).getVelX() * -1);
            if(atomList.get(indexNo).getX()<pistonLocation + 20) {
                atomList.get(indexNo).setX(pistonLocation + 20);
            }
            else if(atomList.get(indexNo).getX() + 10>pistonLocation + 20+Width) {
                atomList.get(indexNo).setX(pistonLocation + 20 + Width - 10);

                //System.out.println("then" + c + "," + d);
            }
        }
        //System.out.println(indexNo);
        if(atomList.get(indexNo).getY()<Panel_Y_Dimension / 20||atomList.get(indexNo).getY()+10>Panel_Y_Dimension / 20+Height) {
            atomList.get(indexNo).setVelY(atomList.get(indexNo).getVelY() * -1);
        }


        atomList.get(indexNo).setX((int) atomList.get(indexNo).getX()+ (int) atomList.get(indexNo).getVelX());
        atomList.get(indexNo).setY((int) atomList.get(indexNo).getY()+ (int) atomList.get(indexNo).getVelY());

        displayPanel.repaint();
    }
    //private static final double accelerationMagnitude = 50;
    private static double[] calcFinalVelocity(int indexNo, int collIndexNo) throws InterruptedException {
        // System.out.println(indexNo + " enterred");
        // System.out.println(indexNo + " ---- " + calcMag(atomList.get(indexNo).getVelX(), atomList.get(indexNo).getVelY()));
        try {
            atomList.get(indexNo).setCollisionStageTwoCheck(false);
        }
        catch(IndexOutOfBoundsException e) {
            System.out.println("error caught");
            e.printStackTrace();
        }
        atomMotion atom = atomList.get(indexNo).copy();
        atomMotion collAtom = atomList.get(collIndexNo).copy();

        atomList.get(indexNo).setCollisionStageTwoCheck(true);
        while(!atomList.get(collIndexNo).getCollisionStageTwoCheck()) {
            //System.out.println(indexNo + " in loop 2");
            Thread.sleep(5);
        }
        //Thread.sleep(7);

        double atomOneMag = calcMag(atom.getVelX(), atom.getVelY());
        double atomTwoMag = calcMag(collAtom.getVelX(), collAtom.getVelY());
        double min = 0.0;
        double max = 0.0;
        //System.out.println(indexNo + " " + atom.getVelX() + "," + atom.getVelY());
        //System.out.println(indexNo + " ---- " + atomList.get(indexNo).getVelX() + "," + atomList.get(indexNo).getVelY());
        if(atomOneMag >= atomTwoMag) {
            min = atomTwoMag;
            max = atomOneMag;
            //System.out.println(indexNo + " is max");
        }
        else {
            max = atomTwoMag;
            min = atomOneMag;
            //System.out.println(collIndexNo + " is max");
        }
        double accMag = (max + min) / 2;

        double[] finalVelocity;



        //atomList.get(indexNo).setCalculating(true);
        //System.out.println(indexNo + " is calculating");
        double accTheta = calcForceDirection(atom, collAtom);
        double velTheta = 0.0;

        double forceThetaPre = Math.toDegrees(Math.atan((double) -1 * atom.getVelY() / atom.getVelX()));


        if (atom.getVelX() > 0 && atom.getVelY() > 0) {
            velTheta = forceThetaPre;
        } else if (atom.getVelX() < 0 && atom.getVelY() > 0) {
            velTheta = 180 + forceThetaPre;
        } else if (atom.getVelX() < 0 && atom.getVelY() < 0) {
            velTheta = 180 + forceThetaPre;
        } else if (atom.getVelX() > 0 && atom.getVelY() < 0) {
            velTheta = 360 + forceThetaPre;
        }
        //System.out.println(indexNo + "- X: " + atom.getVelX() + " Y: " + atom.getVelY());
        //System.out.println(indexNo + " acceleration: " + accMag + " min: " + min + " max: " + max + " thetav: " + velTheta + " thetaa: " + accTheta);

        finalVelocity = addVectors(atom, accTheta, accMag);


        //System.out.println(indexNo + ": " + calcMag(atom.getVelX(), atom.getVelY()) + " " + collIndexNo + ": " + calcMag(collAtom.getVelX(), collAtom.getVelY()));
        //Thread.sleep(10);

        finalVelocity[0] = calcMag(collAtom.getVelX(), collAtom.getVelY());
        //System.out.println(indexNo + " returned: " + finalVelocity[0]);
        return finalVelocity;
    }
    private static Double calcMag(double X, double Y) {
        return Math.sqrt(X*X + Y*Y);
    }
    private static double[] addVectors(atomMotion atom, double accTheta, double accelerationMagnitude) {
        double X = atom.getVelX() + accelerationMagnitude * Math.cos(Math.toRadians(accTheta));
        double Y = -1 * atom.getVelY() + accelerationMagnitude * Math.sin(Math.toRadians(accTheta));
        double result = Math.sqrt(X * X + Y * Y);

        double resultTheta = 0.0;
        if(X == 0 && Y > 0) {
            resultTheta = 90;
        }
        else if(X == 0 && Y < 0) {
            resultTheta = 270;
        }
        else if(Y == 0 && X > 0) {
            resultTheta = 0;
        }
        else if(Y == 0 && X < 0) {
            resultTheta = 180;
        }
        else {
            double resultThetaPre = Math.toDegrees(Math.atan((double) Y / X));
            if(X > 0 && Y > 0) {
                resultTheta = resultThetaPre;
            }
            else if(X < 0 && Y > 0) {
                resultTheta = 180 + resultThetaPre;
            }
            else if(X < 0 && Y < 0) {
                resultTheta = 180 + resultThetaPre;
            }
            else if(X > 0 && Y < 0) {
                resultTheta = 360 + resultThetaPre;
            }
        }
        return new double[] {result, resultTheta};
    }
    private static double calcForceDirection(atomMotion atom, atomMotion collideAtom) throws InterruptedException {
        int X = atom.getX() - collideAtom.getX();
        int Y = -1 * (atom.getY() - collideAtom.getY());

        double forceTheta = 0.0;
        if(X == 0 && Y > 0) {
            forceTheta = 90;
        }
        else if(X == 0 && Y < 0) {
            forceTheta = 270;
        }
        else if(Y == 0 && X > 0) {
            forceTheta = 0;
        }
        else if(Y == 0 && X < 0) {
            forceTheta = 180;
        }
        else {
            double forceThetaPre = 0.0;
            try {
                forceThetaPre = Math.toDegrees(Math.atan((double) Y / X));
            }
            catch(ArithmeticException e) {
                // System.out.println("X:" + X + " Y:" + Y);
                System.out.println("error caught");
                e.printStackTrace();
            }
            if(X > 0 && Y > 0) {
                forceTheta = forceThetaPre;
            }
            else if(X < 0 && Y > 0) {
                forceTheta = 180 + forceThetaPre;
            }
            else if(X < 0 && Y < 0) {
                forceTheta = 180 + forceThetaPre;
            }
            else if(X > 0 && Y < 0) {
                forceTheta = 360 + forceThetaPre;
            }
        }
        return forceTheta;
    }

/*
    private static double[] calcFinalVelocity(int indexNo, int collIndexNo) throws InterruptedException {
        atomMotion atom = atomList.get(indexNo).copy();
        atomMotion collideAtom = atomList.get(indexNo).copy();

        atomList.get(indexNo).setCollisionStageOneCheck(true);
        while(!atomList.get(collIndexNo).getCollisionStageOneCheck()) {
            Thread.sleep(10);
        }

        double theta = 0.0;
        //make a thread communicator here somehow
        double accelerationTheta = calcForceDirection(atom, collideAtom);
        double initialVelocityTheta = calcInitVelocityDirection(atom);
        if(accelerationTheta == initialVelocityTheta || accelerationTheta == initialVelocityTheta + 180 || initialVelocityTheta == accelerationTheta + 180) {

        }
        else {
            double ax = 1000000000 * Math.cos(Math.toRadians(accelerationTheta));
            double ay = 1000000000 * Math.sin(Math.toRadians(accelerationTheta));
            double bottom = atom.getVelX() + ax;
            double top = -1 * atom.getVelY() + ay;
            double fraction = top / bottom;
            double thetaPre = Math.toDegrees(Math.atan(fraction));
            theta = calcFinTheta(initialVelocityTheta, thetaPre);
        }

        atomList.get(indexNo).setFinTheta(theta);
        atomList.get(indexNo).setCollisionStageTwoCheck(true);
        while(!atomList.get(collIndexNo).getCollisionStageTwoCheck()) {
            Thread.sleep(10);
        }
        double finalVelocityMagnitude = calcFinalVelocityMagnitude(atom.getVelX(), collideAtom.getVelX(), atom.getVelY(), collideAtom.getVelY(), atomList.get(indexNo).getFinTheta(), atomList.get(collIndexNo).getFinTheta());
        return new double[] {finalVelocityMagnitude, theta};
    }
    private static double calcInitVelocityDirection(atomMotion atom) {
        int X = atom.getVelX();
        int Y = -1 * atom.getVelY();
        double velocityTheta = 0.0;
        if(X == 0 && Y > 0) {
            velocityTheta = 90;
        }
        else if(X == 0 && Y < 0) {
            velocityTheta = 270;
        }
        else if(Y == 0 && X > 0) {
            velocityTheta = 0;
        }
        else if(Y == 0 && X < 0) {
            velocityTheta = 180;
        }
        else {
            double forceThetaPre = Math.toDegrees(Math.atan(Y / X));
            if(X > 0 && Y > 0) {
                velocityTheta = forceThetaPre;
            }
            else if(X < 0 && Y > 0) {
                velocityTheta = 180 + forceThetaPre;
            }
            else if(X < 0 && Y < 0) {
                velocityTheta = 180 + forceThetaPre;
            }
            else if(X > 0 && Y < 0) {
                velocityTheta = 360 + forceThetaPre;
            }
        }
        return velocityTheta;
    }
    private static double calcForceDirection(atomMotion atom, atomMotion collideAtom) {
        int X = atom.getX() - collideAtom.getX();
        int Y = -1 * (atom.getY() - collideAtom.getY());
        double forceTheta = 0.0;
        if(X == 0 && Y > 0) {
            forceTheta = 90;
        }
        else if(X == 0 && Y < 0) {
            forceTheta = 270;
        }
        else if(Y == 0 && X > 0) {
            forceTheta = 0;
        }
        else if(Y == 0 && X < 0) {
            forceTheta = 180;
        }
        else {
            double forceThetaPre = Math.toDegrees(Math.atan(Y / X));
            if(X > 0 && Y > 0) {
                forceTheta = forceThetaPre;
            }
            else if(X < 0 && Y > 0) {
                forceTheta = 180 + forceThetaPre;
            }
            else if(X < 0 && Y < 0) {
                forceTheta = 180 + forceThetaPre;
            }
            else if(X > 0 && Y < 0) {
                forceTheta = 360 + forceThetaPre;
            }
        }
        return forceTheta;
    }
    private static double calcFinTheta(double thetaOne, double thetaTwo) {
        double maxTheta = 0.0;
        double minTheta = 0.0;

        if(thetaOne > thetaTwo) {
            maxTheta = thetaOne;
            minTheta = thetaTwo;
        }
        else if(thetaTwo > thetaOne) {
            maxTheta = thetaTwo;
            minTheta = thetaOne;
        }
        double theta = minTheta + (maxTheta - minTheta) * rand.nextDouble();
        return theta;
    }
    private static double calcFinalVelocityMagnitude(double initialVelocityOneIX, double initialVelocityTwoIX, double initialVelocityOneIY, double initialVelocityTwoIY, double finalThetaOneF, double finalThetaTwoF) {
        double s = initialVelocityOneIX + initialVelocityTwoIX;
        double c = -1 * initialVelocityOneIY + -1 * initialVelocityTwoIY;
        double top = c - s * Math.tan(Math.toRadians(finalThetaTwoF));
        double bottom = Math.sin(Math.toRadians(finalThetaOneF)) - Math.cos(Math.toRadians(finalThetaOneF)) * Math.tan(Math.toRadians(finalThetaOneF));
        double finalVelocity = top / bottom;

        return finalVelocity;
    }
/*
    private static class threadFunc implements Runnable {
        private static int indexNo;
        private static int count = 0;
        private static Timer t = new Timer(10, new AnimationListener(indexNo));

        public threadFunc(int index) {
            indexNo = index;
        }

        @Override
        public void run() {
            //System.out.println("-"+indexNo);
            try {
                setAtomLocation(indexNo);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("end");
        }
        private static void setAtomLocation(int indexNo) throws InterruptedException {
            //Thread.sleep(100);
            count++;
            System.out.println(count);
            if(count  == 1000)
                t.stop();

               // System.out.println("+"+indexNo);
            //System.out.println(c + "," + d);

            //System.out.println("+"+indexNo);
            atomImageList.get(indexNo).setBounds(atomList.get(indexNo).getX(), atomList.get(indexNo).getY(), 10, 10);

            //displayOverLayerPanel.setBounds(atom.getX(),0,600,Panel_Y_Dimension);
            //System.out.println(atomList.get(1).getX());

            //timerList.get(indexNo).start();
            if(count<1000)
            t.start();
        }

        private static class AnimationListener implements ActionListener {
            private static int indexNo;

            public AnimationListener(int index) {
                indexNo = index;
            }
            public void actionPerformed(ActionEvent e) {
                //System.out.println(indexNo);

                //System.out.println("-"+indexNo);
                if(atomList.get(indexNo).getX() < pistonLocation + 20 || atomList.get(indexNo).getX() + 10 > pistonLocation + 20 + Width) {

                    //System.out.println("first" + c + "," + d);

                    atomList.get(indexNo).setVelX(atomList.get(indexNo).getVelX() * -1);
                    if(atomList.get(indexNo).getX()<pistonLocation + 20) {
                        atomList.get(indexNo).setX(pistonLocation + 20);
                    }
                    else if(atomList.get(indexNo).getX() + 10>pistonLocation + 20+Width) {
                        atomList.get(indexNo).setX(pistonLocation + 20 + Width - 10);

                        //System.out.println("then" + c + "," + d);
                    }
                }
                //System.out.println(indexNo);
                if(atomList.get(indexNo).getY()<Panel_Y_Dimension / 20||atomList.get(indexNo).getY()+10>Panel_Y_Dimension / 20+Height) {
                    atomList.get(indexNo).setVelY(atomList.get(indexNo).getVelY() * -1);
                }


                atomList.get(indexNo).setX(atomList.get(indexNo).getX()+atomList.get(indexNo).getVelX());
                atomList.get(indexNo).setY(atomList.get(indexNo).getY()+atomList.get(indexNo).getVelY());
                try {
                    setAtomLocation(indexNo);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }

 */

}
