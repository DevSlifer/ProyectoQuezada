package Views;

import Views.Components.PanelCover;
import Views.Components.PanelLoginAndRegister;
import Views.swing.MyTextField;
import Views.swing.MyPasswordField;
import Views.swing.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.text.DecimalFormat;
import javax.swing.*;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

/**
 *
 * @author 1000g
 */
public class LoginView extends javax.swing.JFrame {

    private MigLayout layout;
    private PanelCover cover;
    private PanelLoginAndRegister loginAndRegister;
    private boolean isLogin;
    private final double addSize = 30;
    private final double coversize = 40;
    private final double loginSize = 60;
    private final DecimalFormat df = new DecimalFormat("##0.###");
    private MyTextField txtUsuario;
    private MyPasswordField txtContrasena;
    private Button btnIngresar;
    private Animator animator;
    private javax.swing.JLayeredPane bg;
    // Métodos públicos para el controlador

    public String getUsuario() {
        return txtUsuario.getText();
    }

    public void setUsuario(String usuario) {
        txtUsuario.setText(usuario);
    }

    public void addUsuarioKeyListener(KeyListener listener) {
        txtUsuario.addKeyListener(listener);
    }

    public String getContrasena() {
        return new String(txtContrasena.getPassword());
    }

    public void addContrasenaKeyListener(KeyListener listener) {
        txtContrasena.addKeyListener(listener);
    }

    public void addIngresarListener(ActionListener listener) {
        btnIngresar.addActionListener(listener);
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public void cerrar() {
        this.dispose();
    }

    public LoginView() {
        initComponents();
        init();
    }
    

    private void init() {
        layout = new MigLayout("fill, insets 0");
        cover = new PanelCover();
        loginAndRegister = new PanelLoginAndRegister();
        isLogin = false;

        txtUsuario = new MyTextField();
        txtUsuario.setHint("Ingrese su usuario");

        txtContrasena = new MyPasswordField();
        txtContrasena.setHint("Ingrese su contraseña");

        btnIngresar = new Button();
        btnIngresar.setText("Ingresar");

        // Configurar posiciones y tamaños de los componentes
        txtUsuario.setBounds(50, 50, 300, 40);
        txtContrasena.setBounds(50, 110, 300, 40);
        btnIngresar.setBounds(150, 180, 100, 40);

        // Establecer layout nulo y añadir componentes
        loginAndRegister.setLayout(null);
        loginAndRegister.add(txtUsuario);
        loginAndRegister.add(txtContrasena);
        loginAndRegister.add(btnIngresar);

        TimingTarget target;
        target = new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {

                double fractionCover;
                double fractionLogin;
                double size = coversize;
                if (fraction < 0.5f) {
                    size += fraction * addSize;

                } else {
                    size += addSize - fraction * addSize;

                }

                if (isLogin) {
                    fractionCover = 1f - fraction;
                    fractionLogin = fraction;
                    if (fraction >= 0.5f) {
                        cover.registerRight(fractionCover * 100);

                    } else {
                        cover.LoginRight(fractionLogin * 100);

                    }

                } else {
                    fractionCover = fraction;
                    fractionLogin = 1f - fraction;
                    if (fraction <= 0.5f) {
                        cover.registerLeft(fraction * 100);

                    } else {
                        cover.LoginLeft((1f - fraction) * 100);

                    }
                }

                if (fraction >= 0.5f) {
                    loginAndRegister.showRegister(isLogin);

                }

                fractionCover = Double.valueOf(df.format(fractionCover));
                fractionLogin = Double.valueOf(df.format(fractionLogin));

                layout.setComponentConstraints(cover, "width " + size + "%, pos " + fractionCover + "al 0 n 100%");
                layout.setComponentConstraints(loginAndRegister, "width " + loginSize + "%, pos " + fractionLogin + "al 0 n 100%");

                bg.revalidate();

            }

            @Override
            public void end() {
                isLogin = !isLogin;

            }

        };

        Animator animator = new Animator(800, target);
        animator.setAcceleration(0.5f);
        animator.setDeceleration(0.5f);
        animator.setResolution(0); // for smooth animation
        bg.setLayout(layout);
        bg.add(cover, "width " + coversize + "%, pos 0al 0 n 100%");
        bg.add(loginAndRegister, "width " + loginSize + "%, pos 1al 0 n 100%"); //1al as 100%
        cover.addEvent(new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent ae) {
                if (!animator.isRunning()) {
                    animator.start();

                }

            }
        });

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JLayeredPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        bg.setBackground(new java.awt.Color(255, 255, 255));
        bg.setOpaque(true);

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
                bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 785, Short.MAX_VALUE)
        );
        bgLayout.setVerticalGroup(
                bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 494, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(bg, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(bg, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LoginView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new LoginView().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
