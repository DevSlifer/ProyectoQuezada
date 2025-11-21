package Views.swing;

import Utils.Constants;
import Utils.UIConstants;
import java.awt.*;
import javax.swing.*;

/**
 * Modern dialog utilities for better-styled message dialogs.
 * Provides consistent, Material Design-inspired dialogs across the application.
 *
 * @author ProyectoQuezada Team
 */
public final class ModernDialog {

    private ModernDialog() {
        throw new UnsupportedOperationException("ModernDialog is a utility class");
    }

    /**
     * Shows a modern success message.
     */
    public static void showSuccess(Component parent, String message) {
        showSuccess(parent, message, Constants.DialogTitles.SUCCESS);
    }

    public static void showSuccess(Component parent, String message, String title) {
        configureUIDefaults();
        JOptionPane.showMessageDialog(
                parent,
                wrapMessage(message),
                title,
                JOptionPane.INFORMATION_MESSAGE,
                createIcon("✓", UIConstants.Colors.SUCCESS)
        );
    }

    /**
     * Shows a modern error message.
     */
    public static void showError(Component parent, String message) {
        showError(parent, message, Constants.DialogTitles.ERROR);
    }

    public static void showError(Component parent, String message, String title) {
        configureUIDefaults();
        JOptionPane.showMessageDialog(
                parent,
                wrapMessage(message),
                title,
                JOptionPane.ERROR_MESSAGE,
                createIcon("✕", UIConstants.Colors.ERROR)
        );
    }

    /**
     * Shows a modern warning message.
     */
    public static void showWarning(Component parent, String message) {
        showWarning(parent, message, Constants.DialogTitles.WARNING);
    }

    public static void showWarning(Component parent, String message, String title) {
        configureUIDefaults();
        JOptionPane.showMessageDialog(
                parent,
                wrapMessage(message),
                title,
                JOptionPane.WARNING_MESSAGE,
                createIcon("⚠", UIConstants.Colors.WARNING)
        );
    }

    /**
     * Shows a modern info message.
     */
    public static void showInfo(Component parent, String message) {
        showInfo(parent, message, Constants.DialogTitles.INFO);
    }

    public static void showInfo(Component parent, String message, String title) {
        configureUIDefaults();
        JOptionPane.showMessageDialog(
                parent,
                wrapMessage(message),
                title,
                JOptionPane.INFORMATION_MESSAGE,
                createIcon("ℹ", UIConstants.Colors.INFO)
        );
    }

    /**
     * Shows a modern confirmation dialog.
     */
    public static boolean showConfirmation(Component parent, String message) {
        return showConfirmation(parent, message, Constants.DialogTitles.CONFIRMATION);
    }

    public static boolean showConfirmation(Component parent, String message, String title) {
        configureUIDefaults();

        String[] options = {"Sí", "No"};
        int result = JOptionPane.showOptionDialog(
                parent,
                wrapMessage(message),
                title,
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                createIcon("?", UIConstants.Colors.INFO),
                options,
                options[0]
        );

        return result == JOptionPane.YES_OPTION;
    }

    /**
     * Shows a modern input dialog.
     */
    public static String showInput(Component parent, String message, String initialValue) {
        return showInput(parent, message, "Entrada", initialValue);
    }

    public static String showInput(Component parent, String message, String title, String initialValue) {
        configureUIDefaults();

        return (String) JOptionPane.showInputDialog(
                parent,
                wrapMessage(message),
                title,
                JOptionPane.QUESTION_MESSAGE,
                null,
                null,
                initialValue
        );
    }

    /**
     * Configures UIManager defaults for modern look.
     */
    private static void configureUIDefaults() {
        // Dialog background
        UIManager.put("OptionPane.background", Color.WHITE);
        UIManager.put("Panel.background", Color.WHITE);

        // Button styling
        UIManager.put("Button.background", UIConstants.Colors.PRIMARY);
        UIManager.put("Button.foreground", Color.WHITE);
        UIManager.put("Button.font", UIConstants.Fonts.BUTTON);
        UIManager.put("Button.border", BorderFactory.createEmptyBorder(
                UIConstants.Spacing.SM,
                UIConstants.Spacing.LG,
                UIConstants.Spacing.SM,
                UIConstants.Spacing.LG
        ));

        // Text styling
        UIManager.put("OptionPane.messageFont", UIConstants.Fonts.BODY);
        UIManager.put("OptionPane.messageForeground", UIConstants.Colors.TEXT_PRIMARY);
        UIManager.put("OptionPane.buttonFont", UIConstants.Fonts.BUTTON);

        // Border
        UIManager.put("OptionPane.border", BorderFactory.createEmptyBorder(
                UIConstants.Spacing.LG,
                UIConstants.Spacing.LG,
                UIConstants.Spacing.LG,
                UIConstants.Spacing.LG
        ));
    }

    /**
     * Wraps message in HTML for better formatting.
     */
    private static String wrapMessage(String message) {
        if (message == null || message.isEmpty()) {
            return message;
        }

        // Don't wrap if already HTML
        if (message.trim().toLowerCase().startsWith("<html>")) {
            return message;
        }

        // Wrap in HTML with proper styling
        return "<html><div style='width: 300px; font-family: Segoe UI; font-size: 14px;'>"
                + message.replace("\n", "<br>")
                + "</div></html>";
    }

    /**
     * Creates a colored icon for dialogs.
     */
    private static Icon createIcon(String text, Color color) {
        return new Icon() {
            @Override
            public void paintIcon(Component c, Graphics g, int x, int y) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Draw circle background
                g2.setColor(color);
                g2.fillOval(x, y, getIconWidth(), getIconHeight());

                // Draw text
                g2.setColor(Color.WHITE);
                g2.setFont(new Font("Segoe UI", Font.BOLD, 24));
                FontMetrics fm = g2.getFontMetrics();
                int textX = x + (getIconWidth() - fm.stringWidth(text)) / 2;
                int textY = y + ((getIconHeight() - fm.getHeight()) / 2) + fm.getAscent();
                g2.drawString(text, textX, textY);
            }

            @Override
            public int getIconWidth() {
                return 48;
            }

            @Override
            public int getIconHeight() {
                return 48;
            }
        };
    }

    /**
     * Shows a modern progress dialog for long operations.
     */
    public static class ProgressDialog extends JDialog {
        private JProgressBar progressBar;
        private JLabel statusLabel;

        public ProgressDialog(Frame owner, String title) {
            super(owner, title, true);
            initComponents();
            setLocationRelativeTo(owner);
        }

        private void initComponents() {
            setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
            setResizable(false);

            JPanel contentPanel = new JPanel(new BorderLayout(0, UIConstants.Spacing.MD));
            contentPanel.setBorder(UIConstants.Borders.PADDING_LG);
            contentPanel.setBackground(Color.WHITE);

            statusLabel = new JLabel("Procesando...");
            statusLabel.setFont(UIConstants.Fonts.BODY);
            statusLabel.setForeground(UIConstants.Colors.TEXT_PRIMARY);
            contentPanel.add(statusLabel, BorderLayout.NORTH);

            progressBar = new JProgressBar();
            progressBar.setIndeterminate(true);
            progressBar.setPreferredSize(new Dimension(300, 8));
            progressBar.setForeground(UIConstants.Colors.PRIMARY);
            contentPanel.add(progressBar, BorderLayout.CENTER);

            setContentPane(contentPanel);
            pack();
        }

        public void setStatus(String status) {
            statusLabel.setText(status);
        }

        public void setProgress(int value) {
            progressBar.setIndeterminate(false);
            progressBar.setValue(value);
        }

        public void setIndeterminate(boolean indeterminate) {
            progressBar.setIndeterminate(indeterminate);
        }
    }
}
