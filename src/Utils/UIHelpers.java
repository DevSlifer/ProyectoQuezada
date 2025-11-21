package Utils;

import Views.swing.ModernTableCellRenderer;
import java.awt.*;
import java.awt.event.KeyEvent;
import javax.swing.*;

/**
 * UI Helper utilities for common UI operations.
 * Provides convenient methods for styling and configuring UI components.
 *
 * @author ProyectoQuezada Team
 */
public final class UIHelpers {

    private UIHelpers() {
        throw new UnsupportedOperationException("UIHelpers is a utility class");
    }

    /**
     * Centers a window on the screen.
     */
    public static void centerWindow(Window window) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension windowSize = window.getSize();
        int x = (screenSize.width - windowSize.width) / 2;
        int y = (screenSize.height - windowSize.height) / 2;
        window.setLocation(x, y);
    }

    /**
     * Applies modern styling to a JTable.
     */
    public static void styleTable(JTable table) {
        ModernTableCellRenderer.applyModernStyle(table);
    }

    /**
     * Creates a titled panel with modern styling.
     */
    public static JPanel createTitledPanel(String title, JComponent content) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(UIConstants.Colors.BORDER, 1),
                UIConstants.Borders.PADDING_MD
        ));

        // Title label
        if (title != null && !title.isEmpty()) {
            JLabel titleLabel = new JLabel(title);
            titleLabel.setFont(UIConstants.Fonts.HEADING);
            titleLabel.setForeground(UIConstants.Colors.TEXT_PRIMARY);
            titleLabel.setBorder(BorderFactory.createEmptyBorder(
                    0, 0, UIConstants.Spacing.MD, 0
            ));
            panel.add(titleLabel, BorderLayout.NORTH);
        }

        panel.add(content, BorderLayout.CENTER);
        return panel;
    }

    /**
     * Creates a card-style panel.
     */
    public static JPanel createCard() {
        JPanel card = new JPanel();
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(UIConstants.Colors.BORDER, 1),
                UIConstants.Borders.PADDING_LG
        ));
        return card;
    }

    /**
     * Creates a form field with label.
     */
    public static JPanel createFormField(String labelText, JComponent field) {
        JPanel panel = new JPanel(new BorderLayout(0, UIConstants.Spacing.XS));
        panel.setOpaque(false);

        JLabel label = new JLabel(labelText);
        label.setFont(UIConstants.Fonts.BODY_BOLD);
        label.setForeground(UIConstants.Colors.TEXT_PRIMARY);
        panel.add(label, BorderLayout.NORTH);

        panel.add(field, BorderLayout.CENTER);

        return panel;
    }

    /**
     * Creates a horizontal button panel with proper spacing.
     */
    public static JPanel createButtonPanel(JButton... buttons) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT, UIConstants.Spacing.SM, 0));
        panel.setOpaque(false);

        for (JButton button : buttons) {
            panel.add(button);
        }

        return panel;
    }

    /**
     * Creates a vertical form panel with proper spacing.
     */
    public static JPanel createFormPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false);
        panel.setBorder(UIConstants.Borders.PADDING_LG);
        return panel;
    }

    /**
     * Adds vertical spacing between components.
     */
    public static Component createVerticalSpace(int height) {
        return Box.createRigidArea(new Dimension(0, height));
    }

    /**
     * Adds horizontal spacing between components.
     */
    public static Component createHorizontalSpace(int width) {
        return Box.createRigidArea(new Dimension(width, 0));
    }

    /**
     * Sets up keyboard shortcut for a component.
     */
    public static void setKeyboardShortcut(JComponent component, int keyCode, int modifiers, Action action) {
        KeyStroke keyStroke = KeyStroke.getKeyStroke(keyCode, modifiers);
        String actionKey = "shortcut_" + keyCode + "_" + modifiers;
        component.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStroke, actionKey);
        component.getActionMap().put(actionKey, action);
    }

    /**
     * Applies consistent padding to a component.
     */
    public static void applyPadding(JComponent component, int padding) {
        component.setBorder(BorderFactory.createEmptyBorder(padding, padding, padding, padding));
    }

    /**
     * Makes a JScrollPane modern-looking.
     */
    public static void styleScrollPane(JScrollPane scrollPane) {
        scrollPane.setBorder(BorderFactory.createLineBorder(UIConstants.Colors.BORDER, 1));
        scrollPane.getViewport().setBackground(Color.WHITE);
        scrollPane.setBackground(Color.WHITE);

        // Style scrollbars
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.getHorizontalScrollBar().setUnitIncrement(16);
    }

    /**
     * Disables a component and changes its appearance.
     */
    public static void setComponentEnabled(JComponent component, boolean enabled) {
        component.setEnabled(enabled);
        if (enabled) {
            component.setForeground(UIConstants.Colors.TEXT_PRIMARY);
        } else {
            component.setForeground(UIConstants.Colors.TEXT_DISABLED);
        }
    }

    /**
     * Creates a separator line.
     */
    public static JSeparator createSeparator() {
        JSeparator separator = new JSeparator();
        separator.setForeground(UIConstants.Colors.BORDER);
        separator.setBackground(UIConstants.Colors.BORDER);
        return separator;
    }

    /**
     * Adds a subtle shadow effect to a component.
     */
    public static void addShadow(JComponent component) {
        component.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(2, 2, 4, 4),
                component.getBorder()
        ));
    }

    /**
     * Shows/hides a component with fade effect.
     */
    public static void setVisible(JComponent component, boolean visible) {
        component.setVisible(visible);
    }

    /**
     * Creates a modern-looking menu bar.
     */
    public static void styleMenuBar(JMenuBar menuBar) {
        menuBar.setBackground(UIConstants.Colors.PRIMARY);
        menuBar.setBorder(BorderFactory.createEmptyBorder());
        menuBar.setFont(UIConstants.Fonts.BODY);

        // Style all menus
        for (int i = 0; i < menuBar.getMenuCount(); i++) {
            JMenu menu = menuBar.getMenu(i);
            if (menu != null) {
                styleMenu(menu);
            }
        }
    }

    /**
     * Styles a JMenu.
     */
    public static void styleMenu(JMenu menu) {
        menu.setFont(UIConstants.Fonts.BODY);
        menu.setForeground(Color.WHITE);
        menu.setOpaque(false);
    }

    /**
     * Validates all text fields in a container.
     */
    public static boolean validateAllFields(Container container) {
        boolean allValid = true;

        for (Component comp : container.getComponents()) {
            if (comp instanceof Views.swing.ModernTextField) {
                Views.swing.ModernTextField field = (Views.swing.ModernTextField) comp;
                if (!field.isValid()) {
                    allValid = false;
                }
            } else if (comp instanceof Container) {
                if (!validateAllFields((Container) comp)) {
                    allValid = false;
                }
            }
        }

        return allValid;
    }

    /**
     * Clears all text fields in a container.
     */
    public static void clearAllFields(Container container) {
        for (Component comp : container.getComponents()) {
            if (comp instanceof JTextField) {
                ((JTextField) comp).setText("");
            } else if (comp instanceof JTextArea) {
                ((JTextArea) comp).setText("");
            } else if (comp instanceof Container) {
                clearAllFields((Container) comp);
            }
        }
    }

    /**
     * Sets the Look and Feel to system default.
     */
    public static void setSystemLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            // Fallback to default
        }
    }

    /**
     * Common keyboard shortcuts constants.
     */
    public static final class Shortcuts {
        public static final int SAVE = KeyEvent.VK_S;
        public static final int DELETE = KeyEvent.VK_DELETE;
        public static final int REFRESH = KeyEvent.VK_F5;
        public static final int SEARCH = KeyEvent.VK_F;
        public static final int NEW = KeyEvent.VK_N;
        public static final int CTRL = Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx();

        private Shortcuts() {}
    }
}
