package Views.swing;

import Utils.UIConstants;
import java.awt.*;
import java.awt.geom.Arc2D;
import javax.swing.*;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTargetAdapter;

/**
 * Modern loading dialog with animated spinner.
 * Provides visual feedback during long-running operations.
 *
 * @author ProyectoQuezada Team
 */
public class LoadingDialog extends JDialog {

    private JLabel messageLabel;
    private SpinnerPanel spinnerPanel;
    private Animator spinnerAnimator;

    public LoadingDialog(Frame owner) {
        this(owner, "Cargando...");
    }

    public LoadingDialog(Frame owner, String message) {
        super(owner, "", true);
        initComponents(message);
        setLocationRelativeTo(owner);
    }

    private void initComponents(String message) {
        setUndecorated(true);
        setBackground(new Color(0, 0, 0, 0));

        JPanel contentPanel = new JPanel(new BorderLayout(0, UIConstants.Spacing.LG));
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(UIConstants.Colors.BORDER, 1),
                BorderFactory.createEmptyBorder(
                        UIConstants.Spacing.XL,
                        UIConstants.Spacing.XL,
                        UIConstants.Spacing.XL,
                        UIConstants.Spacing.XL
                )
        ));

        // Spinner
        spinnerPanel = new SpinnerPanel();
        spinnerPanel.setPreferredSize(new Dimension(60, 60));
        contentPanel.add(spinnerPanel, BorderLayout.CENTER);

        // Message
        messageLabel = new JLabel(message, SwingConstants.CENTER);
        messageLabel.setFont(UIConstants.Fonts.BODY);
        messageLabel.setForeground(UIConstants.Colors.TEXT_PRIMARY);
        contentPanel.add(messageLabel, BorderLayout.SOUTH);

        setContentPane(contentPanel);
        pack();
    }

    /**
     * Shows the loading dialog and starts the animation.
     */
    public void showLoading() {
        spinnerPanel.startAnimation();
        setVisible(true);
    }

    /**
     * Hides the loading dialog and stops the animation.
     */
    public void hideLoading() {
        spinnerPanel.stopAnimation();
        setVisible(false);
        dispose();
    }

    /**
     * Updates the loading message.
     */
    public void setMessage(String message) {
        messageLabel.setText(message);
    }

    /**
     * Custom panel for rendering the animated spinner.
     */
    private static class SpinnerPanel extends JPanel {

        private float angle = 0f;
        private Animator animator;
        private static final int ARC_COUNT = 8;
        private static final float ARC_ANGLE = 30f;

        public SpinnerPanel() {
            setOpaque(false);
            initAnimator();
        }

        private void initAnimator() {
            animator = new Animator(1000, new TimingTargetAdapter() {
                @Override
                public void timingEvent(float fraction) {
                    angle = fraction * 360;
                    repaint();
                }
            });
            animator.setRepeatCount(Animator.INFINITE);
        }

        public void startAnimation() {
            if (!animator.isRunning()) {
                animator.start();
            }
        }

        public void stopAnimation() {
            if (animator.isRunning()) {
                animator.stop();
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int centerX = getWidth() / 2;
            int centerY = getHeight() / 2;
            int radius = Math.min(getWidth(), getHeight()) / 3;

            for (int i = 0; i < ARC_COUNT; i++) {
                float currentAngle = angle + (i * 360f / ARC_COUNT);
                float alpha = 1f - (i / (float) ARC_COUNT);

                g2.setColor(new Color(
                        UIConstants.Colors.PRIMARY.getRed(),
                        UIConstants.Colors.PRIMARY.getGreen(),
                        UIConstants.Colors.PRIMARY.getBlue(),
                        (int) (alpha * 255)
                ));

                int arcRadius = radius + 5;
                Arc2D.Float arc = new Arc2D.Float(
                        centerX - arcRadius,
                        centerY - arcRadius,
                        arcRadius * 2,
                        arcRadius * 2,
                        currentAngle,
                        ARC_ANGLE,
                        Arc2D.PIE
                );

                g2.fill(arc);
            }
        }
    }

    /**
     * Static helper method for executing tasks with loading dialog.
     */
    public static void executeWithLoading(Frame owner, String message, Runnable task) {
        LoadingDialog dialog = new LoadingDialog(owner, message);

        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                task.run();
                return null;
            }

            @Override
            protected void done() {
                dialog.hideLoading();
            }
        };

        worker.execute();
        dialog.showLoading();
    }
}
