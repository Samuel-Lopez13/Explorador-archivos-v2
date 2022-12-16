/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista;

import java.awt.Color;
import static java.awt.Component.CENTER_ALIGNMENT;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Principal extends javax.swing.JFrame {

    private Color mClicked = new Color(239, 203, 103);
    private Color mEntered = new Color(248, 239, 183);
    private Color mExited = new Color(248, 239, 235);

    private File archivos;
    private File listaArchivos[];
    private String pathR;
    private String mover;
    private String nombreC;
    private String to;
    private String anterior;
    private String reference = "";
    
    private int tamano = 1;
    private int x = 13;
    private int y = 13;

    public Principal() {
        setUndecorated(true);
        initComponents();
//        setSize(1316, 639);
        setSize(1315, 600);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Explorador de Archivos");
        setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 40, 40));
        panelSuperior.setName("C:\\Users\\lopez\\OneDrive\\Documentos");
        inicioComponentes();
    }

    public void inicioComponentes() {
        hoverLateral();
        generarDirectorios();

        retroceder.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                btnRegresar();
            }
        });

        carpeta1.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                crearCarpeta();
            }
        });

        archivo.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                crearArchivos();
            }
        });

        renombrar.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                renombrar();
            }
        });
        
        copiar.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent e) {
                pegar();
            }
        });
    }

    public void hoverLateral() {
//------------------------------------------------------------------------------
        documentos.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
//                documentos.setBackground(mClicked);

                panelCentral.removeAll();
                panelSuperior.setName("C:\\Users\\lopez\\OneDrive\\Documentos");
                x = 13;
                y = 13;
                generarDirectorios();
                nuevo();
                repaint();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                documentos.setBackground(mEntered);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                documentos.setBackground(mExited);
            }
        });
//------------------------------------------------------------------------------        
        descargas.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
//                descargas.setBackground(mClicked);

                panelCentral.removeAll();
                panelSuperior.setName("C:\\Users\\lopez\\Downloads");
                x = 13;
                y = 13;
                generarDirectorios();
                nuevo();
                repaint();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                descargas.setBackground(mEntered);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                descargas.setBackground(mExited);
            }
        });
//------------------------------------------------------------------------------      
        musica.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
//                musica.setBackground(mClicked);

                panelCentral.removeAll();
                panelSuperior.setName("C:\\Users\\lopez\\Music");
                x = 13;
                y = 13;
                generarDirectorios();
                nuevo();
                repaint();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                musica.setBackground(mEntered);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                musica.setBackground(mExited);
            }
        });
//------------------------------------------------------------------------------  
        fotos.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
//                fotos.setBackground(mClicked);

                panelCentral.removeAll();
                panelSuperior.setName("C:\\Users\\lopez\\OneDrive\\Imágenes");
                x = 13;
                y = 13;
                generarDirectorios();
                nuevo();
                repaint();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                fotos.setBackground(mEntered);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                fotos.setBackground(mExited);
            }
        });
//------------------------------------------------------------------------------
        videos.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
//                videos.setBackground(mClicked);

                panelCentral.removeAll();
                panelSuperior.setName("C:\\Users\\lopez\\Videos");
                x = 13;
                y = 13;
                generarDirectorios();
                nuevo();
                repaint();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                videos.setBackground(mEntered);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                videos.setBackground(mExited);
            }
        });
//------------------------------------------------------------------------------
        retroceder.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                retroceder.setIcon(new ImageIcon("src/img/flecha2.png"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                retroceder.setIcon(new ImageIcon("src/img/flecha1.png"));
            }
        });

        eliminar.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                eliminarArchivos();
            }
        });
    }

    public void directorios() {
        archivos = new File(panelSuperior.getName());
        if (archivos.exists()) {
            if (archivos.isDirectory()) {
                listaArchivos = archivos.listFiles();
            }
        }
    }

    public void nuevo() {
        if (tamano == 1) {
            setSize(1315, 600);
            tamano = 0;
        } else if (tamano == 0) {
            setSize(1316, 600);
            tamano = 1;
        }
    }

    public void generarDirectorios() {
        directorios();

        int i = 0;
        for (i = 0; i < listaArchivos.length; i++) {

            JButton btn;
            btn = new JButton(listaArchivos[i].getName());
            btn.setHorizontalAlignment((int) CENTER_ALIGNMENT);
//            btn.setBackground(new Color(248, 239, 235));
            btn.setBackground(mEntered);
            btn.setFocusable(false);
            btn.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
            btn.setForeground(Color.BLACK);
            btn.setBorderPainted(false);

            String path = listaArchivos[i].getPath();
            cortarUltimo(path);
            pathR = path;

            btn.setBounds(x, y, 120, 170);
            x += 133;
            if (x == 1077) {
                x = 13;
                y += 183;
            }

            int modulos = 0;

            if (listaArchivos.length % 8 == 0) {
                modulos = 0;
            } else {
                modulos += 183;
            }

            //dependiendo cambiara el tamaño del panel para poder mostrar el scroll
            panelCentral.setPreferredSize(new Dimension(1060, y + modulos));

            btn.setName(path);
            if(btn.getName().equals(reference)){
                btn.setBackground(mClicked);
            }
            
            panelCentral.add(btn);

            boolean directorio = listaArchivos[i].isDirectory();
            String nombre = listaArchivos[i].getName();
            
            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (directorio) {
                        panelCentral.removeAll();
                        panelSuperior.setName(path);
                        x = 13;
                        y = 13;
                        generarDirectorios();
                        nuevo();
                        repaint();
                    } else {
                        Desktop dt = Desktop.getDesktop();
                        try {
                            dt.open(new File(path));
                        } catch (IOException ex) {
                        }
                    }
                }
            });

            btn.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    if ((e.getModifiers() & 8) != 0) {
                        reference = btn.getName();
                        mover = path;
                        nombreC = "\\" + nombre;
                        
                        panelCentral.removeAll();
                        x = 13;
                        y = 13;
                        generarDirectorios();
                        nuevo();
                        repaint();
                    }
                }
            });
        }

        if (listaArchivos.length == 0) {
            String valor = panelSuperior.getName();
            url.setText(valor);
        }
    }

    public void cortarUltimo(String pathR) {
        char caracterInvertido[] = new char[pathR.length()];
        String nuevoValor = "";
        String pathFinal = "";
        int contador = 0;
        for (int i = pathR.length(); i > 0; i--) {
            caracterInvertido[contador] = pathR.charAt(i - 1);
            contador++;
        }
        boolean bandera = true;
        int c = 0;
        for (int i = 0; i < pathR.length(); i++) {
            if (bandera) {
                if (String.valueOf(caracterInvertido[i]).equals("\\")) {
                    bandera = false;
                    bandera = false;
                }
            } else {
                nuevoValor += String.valueOf(caracterInvertido[i]);
            }
        }
        for (int i = nuevoValor.length(); i > 0; i--) {
            pathFinal += nuevoValor.charAt(i - 1);
        }

        url.setText(pathFinal);
    }

    public void btnRegresar() {
        reference = "";
        char caracterInvertido[];
        String pathFinal = "";
        if (listaArchivos.length == 0) {
            caracterInvertido = new char[panelSuperior.getName().length()];
            String nuevoValor = "";
            pathFinal = "";
            int contador = 0;
            for (int i = panelSuperior.getName().length(); i > 0; i--) {
                caracterInvertido[contador] = panelSuperior.getName().charAt(i - 1);
                contador++;
            }
            boolean bandera = true;
            int c = 0;
            for (int i = 0; i < panelSuperior.getName().length(); i++) {
                if (bandera) {
                    if (String.valueOf(caracterInvertido[i]).equals("\\")) {
                        c++;
                        if (listaArchivos.length == 0) {
                            bandera = false;
                        }
                        if (c == 2) {
                            bandera = false;
                        }
                    }
                } else {
                    nuevoValor += String.valueOf(caracterInvertido[i]);
                }
            }
            for (int i = nuevoValor.length(); i > 0; i--) {
                pathFinal += nuevoValor.charAt(i - 1);
            }
        } else {
            caracterInvertido = new char[pathR.length()];
            String nuevoValor = "";
            pathFinal = "";
            int contador = 0;
            for (int i = pathR.length(); i > 0; i--) {
                caracterInvertido[contador] = pathR.charAt(i - 1);
                contador++;
            }
            boolean bandera = true;
            int c = 0;
            for (int i = 0; i < pathR.length(); i++) {
                if (bandera) {
                    if (String.valueOf(caracterInvertido[i]).equals("\\")) {
                        c++;
                        if (listaArchivos.length == 0) {
                            bandera = false;
                        }
                        if (c == 2) {
                            bandera = false;
                        }
                    }
                } else {
                    nuevoValor += String.valueOf(caracterInvertido[i]);
                }
            }
            for (int i = nuevoValor.length(); i > 0; i--) {
                pathFinal += nuevoValor.charAt(i - 1);
            }
        }
        panelCentral.removeAll();
        panelSuperior.setName(pathFinal);
        x = 13;
        y = 13;
        generarDirectorios();
        nuevo();
        repaint();
    }

    public void crearCarpeta() {
        reference = "";
        String nombre = JOptionPane.showInputDialog("Nombre de la Carpeta");

        char caracterInvertido[];
        String pathFinal = "";

        if (listaArchivos.length == 0) {
            caracterInvertido = new char[panelSuperior.getName().length()];
            String nuevoValor = "";
            pathFinal = "";
            int contador = 0;
            for (int i = panelSuperior.getName().length(); i > 0; i--) {
                caracterInvertido[contador] = panelSuperior.getName().charAt(i - 1);
                contador++;
            }
            boolean bandera = true;
            int c = 0;

            for (int i = 0; i < panelSuperior.getName().length(); i++) {
                if (bandera && listaArchivos.length != 0) {
                    if (String.valueOf(caracterInvertido[i]).equals("\\")) {
                        bandera = false;
                    }
                } else {
                    nuevoValor += String.valueOf(caracterInvertido[i]);
                }
            }
            for (int i = nuevoValor.length(); i > 0; i--) {
                pathFinal += nuevoValor.charAt(i - 1);
            }
            String nuevaCarpeta = pathFinal + "\\" + nombre;
            File archivo = new File(nuevaCarpeta);
            if (archivo.mkdir()) {
                System.out.println("creado");
            } else {
                System.out.println("no creado");
            }
        } else {
            caracterInvertido = new char[pathR.length()];
            String nuevoValor = "";
            pathFinal = "";
            int contador = 0;
            for (int i = pathR.length(); i > 0; i--) {
                caracterInvertido[contador] = pathR.charAt(i - 1);
                contador++;
            }
            boolean bandera = true;
            int c = 0;
            for (int i = 0; i < pathR.length(); i++) {
                if (bandera && listaArchivos.length != 0) {
                    if (String.valueOf(caracterInvertido[i]).equals("\\")) {
                        bandera = false;
                    }
                } else {
                    nuevoValor += String.valueOf(caracterInvertido[i]);
                }
            }
            for (int i = nuevoValor.length(); i > 0; i--) {
                pathFinal += nuevoValor.charAt(i - 1);
            }
            String nuevaCarpeta = pathFinal + "\\" + nombre;
            File archivo = new File(nuevaCarpeta);
            if (archivo.mkdir()) {
                System.out.println("creado");
            } else {
                System.out.println("no creado");
            }
        }
        panelCentral.removeAll();
        panelSuperior.setName(pathFinal);
        x = 13;
        y = 13;
        generarDirectorios();
        nuevo();
        repaint();
    }

    public void crearArchivos() {
        reference = "";
        String nombre = JOptionPane.showInputDialog("Introduce el nombre de la carpeta");
        char caracterInvertido[];
        String pathFinal = "";

        if (listaArchivos.length == 0) {
            //tomo el path ultimo path Guardado y lo pongo en un arreglo
            caracterInvertido = new char[panelSuperior.getName().length()];
            String nuevoValor = "";
            pathFinal = "";
            int contador = 0;

            //toma los caracteres y lo invierte
            for (int i = panelSuperior.getName().length(); i > 0; i--) {
                caracterInvertido[contador] = panelSuperior.getName().charAt(i - 1);
                contador++;
            }

            boolean bandera = true;
            int c = 0;

            for (int i = 0; i < panelSuperior.getName().length(); i++) {
                //la bandera es para que ya no vuelva a entrar estando en negativo
                if (bandera && listaArchivos.length != 0) {
                    //como ya esta invertido va a buscar una coincidencia de \
                    //que indica que alli acaba una carpeta
                    if (String.valueOf(caracterInvertido[i]).equals("\\")) {
                        //bandera solo recorrera una vez
                        bandera = false;
                    }
                } else {
                    //una vez encuentra eso pues nuevoValor = a los caracteres despues
                    //De los que no tomo
                    nuevoValor += String.valueOf(caracterInvertido[i]);
                }
            }

            //vuelve a invertir la cadena y la deja como deberia estar
            for (int i = nuevoValor.length(); i > 0; i--) {
                pathFinal += nuevoValor.charAt(i - 1);
            }

            String nuevaCarpeta = pathFinal + "\\" + nombre;

            File archivo = new File(nuevaCarpeta);
            try {
                PrintWriter salida = new PrintWriter(new FileWriter(archivo));
                salida.close();
                System.out.println("Se ha creado el archivo");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else {
            //tomo el path ultimo path Guardado y lo pongo en un arreglo
            caracterInvertido = new char[pathR.length()];
            String nuevoValor = "";
            pathFinal = "";
            int contador = 0;

            //toma los caracteres y lo invierte
            for (int i = pathR.length(); i > 0; i--) {
                caracterInvertido[contador] = pathR.charAt(i - 1);
                contador++;
            }

            boolean bandera = true;
            int c = 0;

            for (int i = 0; i < pathR.length(); i++) {
                //la bandera es para que ya no vuelva a entrar estando en negativo
                if (bandera && listaArchivos.length != 0) {
                    //como ya esta invertido va a buscar una coincidencia de \
                    //que indica que alli acaba una carpeta
                    if (String.valueOf(caracterInvertido[i]).equals("\\")) {
                        //bandera solo recorrera una vez
                        bandera = false;
                    }
                } else {
                    //una vez encuentra eso pues nuevoValor = a los caracteres despues
                    //De los que no tomo
                    nuevoValor += String.valueOf(caracterInvertido[i]);
                }
            }

            //vuelve a invertir la cadena y la deja como deberia estar
            for (int i = nuevoValor.length(); i > 0; i--) {
                pathFinal += nuevoValor.charAt(i - 1);
            }

            String nuevaCarpeta = pathFinal + "\\" + nombre;

            File archivo = new File(nuevaCarpeta);
            try {
                PrintWriter salida = new PrintWriter(new FileWriter(archivo));
                salida.close();
                System.out.println("Se ha creado el archivo");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        //se remueve todo
        panelCentral.removeAll();
        //le paso el path al panel donde guardo
        //este solo es una ayuda pudo ser a cualquier componente estaico
        panelSuperior.setName(pathFinal);
        //se resetea las posiciones de las carpetas
        x = 13;
        y = 13;
        //y ejecuto los siguientes metodos
        generarDirectorios();
        nuevo();
        repaint();
    }

    public void eliminarArchivos() {
        reference = "";
        File f = new File(mover);
        if (f.delete()) {
            x = 13;
            y = 13;
            panelCentral.removeAll();
            generarDirectorios();
            nuevo();
            repaint();
        } else {
            System.out.println("no se elimino");
        }
    }

    public void renombrar() {
        reference = "";
        char caracterInvertido[] = new char[pathR.length()];
        String nuevoValor = "";
        String pathFinal = "";
        int contador = 0;

        for (int i = pathR.length(); i > 0; i--) {
            caracterInvertido[contador] = pathR.charAt(i - 1);
            contador++;
        }

        boolean bandera = true;
        int c = 0;

        for (int i = 0; i < pathR.length(); i++) {
            if (bandera) {
                if (String.valueOf(caracterInvertido[i]).equals("\\")) {
                    bandera = false;
                }
            } else {
                nuevoValor += String.valueOf(caracterInvertido[i]);
            }
        }

        for (int i = nuevoValor.length(); i > 0; i--) {
            pathFinal += nuevoValor.charAt(i - 1);
        }

        anterior = JOptionPane.showInputDialog("Renombrar");

        String archivo1 = mover;
        String archivo2 = pathFinal + "\\" + anterior;

        try {
            Path temp = Files.move(Paths.get(archivo1), Paths.get(archivo2));
            if (temp != null) {
                panelCentral.removeAll();
                x = 13;
                y = 13;
                generarDirectorios();
                nuevo();
                repaint();
            } else {
                System.out.println("Failed to move the file");
            }
        } catch (IOException ex) {
        }
    }

    public void pegar() {
        reference = "";
        to = panelSuperior.getName();

        String archivo1 = mover;
        String archivo2 = to + nombreC;

        try {
            Path temp = Files.move(Paths.get(archivo1), Paths.get(archivo2));
            if (temp != null) {
                panelCentral.removeAll();
                //ejecuto los siguientes metodos
                //se resetea las posiciones de las carpetas
                x = 13;
                y = 13;
                generarDirectorios();
                nuevo();
                repaint();
            } else {
                System.out.println("Failed to move the file");
            }
        } catch (IOException ex) {
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelSuperior = new javax.swing.JPanel();
        retroceder = new javax.swing.JLabel();
        url = new javax.swing.JLabel();
        archivo = new javax.swing.JButton();
        copiar = new javax.swing.JButton();
        carpeta1 = new javax.swing.JButton();
        eliminar = new javax.swing.JButton();
        renombrar = new javax.swing.JButton();
        panelLateral = new javax.swing.JPanel();
        documentos = new javax.swing.JLabel();
        descargas = new javax.swing.JLabel();
        musica = new javax.swing.JLabel();
        fotos = new javax.swing.JLabel();
        videos = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        panelCentral = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelSuperior.setBackground(new java.awt.Color(248, 239, 235));
        panelSuperior.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelSuperior.setLayout(null);

        retroceder.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/flecha1.png"))); // NOI18N
        panelSuperior.add(retroceder);
        retroceder.setBounds(30, 15, 40, 40);

        url.setBackground(new java.awt.Color(248, 239, 183));
        url.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        url.setText("Hola");
        url.setOpaque(true);
        panelSuperior.add(url);
        url.setBounds(250, 40, 1030, 20);

        archivo.setBackground(new java.awt.Color(239, 203, 103));
        archivo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        archivo.setText("Crear Archivo");
        archivo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        archivo.setFocusPainted(false);
        panelSuperior.add(archivo);
        archivo.setBounds(90, 40, 130, 20);

        copiar.setBackground(new java.awt.Color(239, 203, 103));
        copiar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        copiar.setText("Copiar/Pegar");
        copiar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        copiar.setFocusPainted(false);
        panelSuperior.add(copiar);
        copiar.setBounds(550, 10, 130, 20);

        carpeta1.setBackground(new java.awt.Color(239, 203, 103));
        carpeta1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        carpeta1.setText("Crear Carpeta");
        carpeta1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        carpeta1.setFocusPainted(false);
        panelSuperior.add(carpeta1);
        carpeta1.setBounds(90, 10, 130, 20);

        eliminar.setBackground(new java.awt.Color(239, 203, 103));
        eliminar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        eliminar.setText("Eliminar");
        eliminar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        eliminar.setFocusPainted(false);
        panelSuperior.add(eliminar);
        eliminar.setBounds(250, 10, 130, 20);

        renombrar.setBackground(new java.awt.Color(239, 203, 103));
        renombrar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        renombrar.setText("Renombrar");
        renombrar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        renombrar.setFocusPainted(false);
        panelSuperior.add(renombrar);
        renombrar.setBounds(400, 10, 130, 20);

        getContentPane().add(panelSuperior, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1320, 70));

        panelLateral.setBackground(new java.awt.Color(248, 239, 235));
        panelLateral.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelLateral.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        documentos.setBackground(new java.awt.Color(248, 239, 235));
        documentos.setFont(new java.awt.Font("Sylfaen", 1, 18)); // NOI18N
        documentos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        documentos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/documentos1.png"))); // NOI18N
        documentos.setText("Documentos");
        documentos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        documentos.setOpaque(true);
        panelLateral.add(documentos, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 239, 80));

        descargas.setBackground(new java.awt.Color(248, 239, 235));
        descargas.setFont(new java.awt.Font("Sylfaen", 1, 18)); // NOI18N
        descargas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        descargas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/descargar1.png"))); // NOI18N
        descargas.setText("Descargas");
        descargas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        descargas.setOpaque(true);
        panelLateral.add(descargas, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 239, 80));

        musica.setBackground(new java.awt.Color(248, 239, 235));
        musica.setFont(new java.awt.Font("Sylfaen", 1, 18)); // NOI18N
        musica.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        musica.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/musica1.png"))); // NOI18N
        musica.setText("Musica");
        musica.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        musica.setOpaque(true);
        panelLateral.add(musica, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, 239, 80));

        fotos.setBackground(new java.awt.Color(248, 239, 235));
        fotos.setFont(new java.awt.Font("Sylfaen", 1, 18)); // NOI18N
        fotos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fotos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/imagen1.png"))); // NOI18N
        fotos.setText("Fotos");
        fotos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        fotos.setOpaque(true);
        panelLateral.add(fotos, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, 239, 80));

        videos.setBackground(new java.awt.Color(248, 239, 235));
        videos.setFont(new java.awt.Font("Sylfaen", 1, 18)); // NOI18N
        videos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        videos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/video1.png"))); // NOI18N
        videos.setText("Videos");
        videos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        videos.setOpaque(true);
        panelLateral.add(videos, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 320, 239, 80));

        getContentPane().add(panelLateral, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 240, 530));

        panelCentral.setBackground(new java.awt.Color(248, 239, 235));

        javax.swing.GroupLayout panelCentralLayout = new javax.swing.GroupLayout(panelCentral);
        panelCentral.setLayout(panelCentralLayout);
        panelCentralLayout.setHorizontalGroup(
            panelCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1078, Short.MAX_VALUE)
        );
        panelCentralLayout.setVerticalGroup(
            panelCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 528, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(panelCentral);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 70, 1080, 530));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton archivo;
    private javax.swing.JButton carpeta1;
    private javax.swing.JButton copiar;
    private javax.swing.JLabel descargas;
    private javax.swing.JLabel documentos;
    private javax.swing.JButton eliminar;
    private javax.swing.JLabel fotos;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel musica;
    private javax.swing.JPanel panelCentral;
    private javax.swing.JPanel panelLateral;
    private javax.swing.JPanel panelSuperior;
    private javax.swing.JButton renombrar;
    private javax.swing.JLabel retroceder;
    private javax.swing.JLabel url;
    private javax.swing.JLabel videos;
    // End of variables declaration//GEN-END:variables
}
