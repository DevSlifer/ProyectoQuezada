
package Views.Components;

import Views.swing.ButtonOutLine;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import javax.swing.Action;
import javax.swing.JLabel;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author 1000g
 */
public class PanelCover extends javax.swing.JPanel {
    
    private final DecimalFormat df = new DecimalFormat("##0.###");
    private ActionListener event;
    private MigLayout layout;
    private JLabel title;
    private JLabel description;
    private JLabel description1;
    private ButtonOutLine Button;
    private boolean isLogin;
     
    
    public PanelCover() {
        initComponents();
        setOpaque(false);
        layout = new MigLayout("wrap, fill", "[center]", "push[]25[]10[]25[]push");
        setLayout(layout);
        init();
        
    }
    
        private void init(){
        title = new JLabel("Welcome Back!");
        title.setFont(new Font("sansserif", 1, 30));
        title.setForeground(new Color(245, 245, 245));
        add(title);
        
        description = new JLabel("To keep connected with us please");
        description.setForeground(new Color(245, 245, 245));
        add(description);
        
        description1 = new JLabel("Login with your personal information");
        description1.setForeground(new Color(245, 245, 245));
        add(description1);
        
        Button = new ButtonOutLine();
        Button.setBackground(new Color(255, 255, 255));
        Button.setForeground(new Color(255, 255, 255));
        Button.setText("SIGN IN");
        Button.addActionListener(new ActionListener(){
            
            @Override
            public void actionPerformed(ActionEvent ae) {
            event.actionPerformed(ae);
            }
        
        });
            add(Button,"w 60%, h 40");
        
        
        }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                formAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_formAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_formAncestorAdded

    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        GradientPaint gra=new GradientPaint(0, 0, new Color(35, 166, 97), 0,getHeight(), new Color(22, 116, 66));
        g2.setPaint(gra);
        g2.fillRect(0, 0, getWidth(),getHeight());
        
        super.paintComponent(grphcs); 
    }

    public void addEvent(ActionListener actionListener){
        
    this.event = actionListener;
    
    }
    
    public void registerLeft(double v){
     v = Double.valueOf(df.format(v));
     login(false);
     layout.setComponentConstraints(title, "pad 0 -" + v + "% 0 0");
     layout.setComponentConstraints(description, "pad 0 -" + v + "% 0 0");
     layout.setComponentConstraints(description1, "pad 0 -" + v + "% 0 0");
     
    
    }
    
    public void registerRight(double v){
     v = Double.valueOf(df.format(v));
     login(false);
     layout.setComponentConstraints(title, "pad 0 -" + v + "% 0 0");
     layout.setComponentConstraints(description, "pad 0 -" + v + "% 0 0");
     layout.setComponentConstraints(description1, "pad 0 -" + v + "% 0 0");
    
    }
    
    public void LoginLeft(double v){
     v = Double.valueOf(df.format(v));
     login(true);
     layout.setComponentConstraints(title, "pad 0 " + v + "% 0 " + v + "%");
     layout.setComponentConstraints(description, "pad 0 " + v + "% 0 " + v + "%");
     layout.setComponentConstraints(description1, "pad 0 " + v + "% 0 " + v + "%");
    } 
    
    public void LoginRight(double v){
     v = Double.valueOf(df.format(v));
     login(true);
     layout.setComponentConstraints(title, "pad 0 " + v + "% 0 " + v + "%");
     layout.setComponentConstraints(description, "pad 0 " + v + "% 0 " + v + "%");
     layout.setComponentConstraints(description1, "pad 0 " + v + "% 0 " + v + "%");
    }
    
    
    private void login (boolean login){
        if (this.isLogin != login) {
            if (login) {
              title.setText("Hello, Friend!");
              description.setText("Enter your personal details");
              description1.setText("and start your journey with us");
              Button.setText("SIGN UP");
            } else {
              title.setText("Welcome Back!");
              description.setText("To keep connected with us please");
              description1.setText("Login with your personal information"); 
              Button.setText("SIGN IN");
            }
            
            
            this.isLogin = login;
  
            
        }
    
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    
}
