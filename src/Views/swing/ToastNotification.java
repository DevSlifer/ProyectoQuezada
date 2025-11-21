package Views.swing;

import Utils.UIConstants;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTargetAdapter;

/**
 * Toast notification system for non-intrusive user feedback.
 * Displays temporary messages that slide in and fade out automatically.
 *
 * @author ProyectoQuezada Team
 */
public class ToastNotification extends JWindow {

    public enum ToastType {
        SUCCESS,
        ERROR,
        WARNING,
        INFO
    }

    private static final int TOAST_WIDTH = 350;
    private static final int TOAST_HEIGHT = 60;
    private static final int DISPLAY_DURATION = 3000; // 3 seconds
    private static final int FADE_DURATION = 300;

    private JLabel messageLabel;
    private JLabel iconLabel;
    private ToastType type;
    private float opacity = 0f;

    private Animator fadeInAnimator;
    private Animator fadeOutAnimator;
    private Timer displayTimer;

    public ToastNotification(Frame owner, String message, ToastType type) {
        super(owner);
        this.type = type;
        initComponents(message);
        initAnimations();
    }

    private void initComponents(String message) {
        JPanel contentPanel = new JPanel(new BorderLayout(UIConstants.Spacing.MD, 0));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(
                UIConstants.Spacing.MD,
                UIConstants.Spacing.LG,
                UIConstants.Spacing.MD,
                UIConstants.Spacing.LG
        ));
        contentPanel.setOpaque(true);

        // Set background and icon based on type
        Color backgroundColor;
        Color textColor = Color.WHITE;
        String iconText;

        switch (type) {
            case SUCCESS:
                backgroundColor = UIConstants.Colors.SUCCESS;
                iconText = "✓";
                break;
            case ERROR:
                backgroundColor = UIConstants.Colors.ERROR;
                iconText = "✕";
                break;
            case WARNING:
                backgroundColor = UIConstants.Colors.WARNING;
                iconText = "⚠";
                break;
            case INFO:
            default:
                backgroundColor = UIConstants.Colors.INFO;
                iconText = "ℹ";
                break;
        }

        contentPanel.setBackground(backgroundColor);

        // Icon
        iconLabel = new JLabel(iconText);
        iconLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        iconLabel.setForeground(textColor);
        contentPanel.add(iconLabel, BorderLayout.WEST);

        // Message
        messageLabel = new JLabel("<html>" + message + "</html>");
        messageLabel.setFont(UIConstants.Fonts.BODY);
        messageLabel.setForeground(textColor);
        contentPanel.add(messageLabel, BorderLayout.CENTER);

        setContentPane(contentPanel);
        setSize(TOAST_WIDTH, TOAST_HEIGHT);
        setAlwaysOnTop(true);
    }

    private void initAnimations() {
        // Fade in animation
        fadeInAnimator = new Animator(FADE_DURATION, new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                opacity = fraction;
                setOpacity(opacity);
            }
        });

        // Fade out animation
        fadeOutAnimator = new Animator(FADE_DURATION, new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                opacity = 1 - fraction;
                setOpacity(opacity);
            }

            @Override
            public void end() {
                ToastNotification.this.dispose();
            }
        });

        // Display timer
        displayTimer = new Timer(DISPLAY_DURATION, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fadeOut();
            }
        });
        displayTimer.setRepeats(false);
    }

    /**
     * Shows the toast notification.
     */
    public void showToast() {
        // Position at bottom-right of screen
        GraphicsConfiguration gc = getGraphicsConfiguration();
        Rectangle bounds = gc.getBounds();
        Insets insets = Toolkit.getDefaultToolkit().getScreenInsets(gc);

        int x = bounds.x + bounds.width - insets.right - TOAST_WIDTH - UIConstants.Spacing.LG;
        int y = bounds.y + bounds.height - insets.bottom - TOAST_HEIGHT - UIConstants.Spacing.LG;

        setLocation(x, y);
        setVisible(true);

        fadeInAnimator.start();
        displayTimer.start();
    }

    private void fadeOut() {
        if (!fadeOutAnimator.isRunning()) {
            fadeOutAnimator.start();
        }
    }

    /**
     * Static helper methods for showing toasts.
     */
    public static void showSuccess(Frame owner, String message) {
        new ToastNotification(owner, message, ToastType.SUCCESS).showToast();
    }

    public static void showError(Frame owner, String message) {
        new ToastNotification(owner, message, ToastType.ERROR).showToast();
    }

    public static void showWarning(Frame owner, String message) {
        new ToastNotification(owner, message, ToastType.WARNING).showToast();
    }

    public static void showInfo(Frame owner, String message) {
        new ToastNotification(owner, message, ToastType.INFO).showToast();
    }

    /**
     * Shows a toast with automatic type detection based on constants.
     */
    public static void show(Frame owner, String message, String title) {
        ToastType type = ToastType.INFO;

        if (title != null) {
            String lowerTitle = title.toLowerCase();
            if (lowerTitle.contains("éxito") || lowerTitle.contains("success")) {
                type = ToastType.SUCCESS;
            } else if (lowerTitle.contains("error")) {
                type = ToastType.ERROR;
            } else if (lowerTitle.contains("advertencia") || lowerTitle.contains("warning")) {
                type = ToastType.WARNING;
            }
        }

        new ToastNotification(owner, message, type).showToast();
    }
}
