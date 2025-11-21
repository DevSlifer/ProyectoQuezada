package Views.swing;

import Utils.UIConstants;
import Utils.ValidationUtils;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * Modern text field with validation feedback, floating labels, and error states.
 * Provides real-time validation and visual feedback to improve user experience.
 *
 * @author ProyectoQuezada Team
 */
public class ModernTextField extends JTextField {

    public enum ValidationState {
        NONE,
        VALID,
        ERROR,
        WARNING
    }

    public enum ValidationType {
        NONE,
        REQUIRED,
        EMAIL,
        CEDULA,
        PHONE,
        NUMBER,
        CUSTOM
    }

    private Icon prefixIcon;
    private Icon suffixIcon;
    private String placeholder = "";
    private String errorMessage = "";
    private String helperText = "";

    private ValidationState validationState = ValidationState.NONE;
    private ValidationType validationType = ValidationType.NONE;
    private ValidationCallback customValidator;

    private Color borderColor = UIConstants.Colors.BORDER;
    private Color focusColor = UIConstants.Colors.BORDER_FOCUS;
    private boolean isFocused = false;
    private int borderRadius = UIConstants.BorderRadius.MEDIUM;

    private JLabel errorLabel;
    private JLabel helperLabel;

    // Functional interface for custom validation
    @FunctionalInterface
    public interface ValidationCallback {
        boolean validate(String text);
    }

    public ModernTextField() {
        this("");
    }

    public ModernTextField(String placeholder) {
        this.placeholder = placeholder;
        initializeComponent();
    }

    private void initializeComponent() {
        setFont(UIConstants.Fonts.INPUT);
        setForeground(UIConstants.Colors.TEXT_PRIMARY);
        setBackground(UIConstants.Colors.INPUT_BACKGROUND);
        setCaretColor(UIConstants.Colors.PRIMARY);
        setSelectionColor(UIConstants.Colors.PRIMARY_LIGHT);
        setSelectedTextColor(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(
                UIConstants.Spacing.SM,
                UIConstants.Spacing.MD,
                UIConstants.Spacing.SM,
                UIConstants.Spacing.MD
        ));

        // Add focus listener for border highlighting
        addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                isFocused = true;
                borderColor = focusColor;
                repaint();
            }

            @Override
            public void focusLost(FocusEvent e) {
                isFocused = false;
                borderColor = getStateColor();
                if (validationType != ValidationType.NONE) {
                    validateInput();
                }
                repaint();
            }
        });

        // Add document listener for real-time validation
        getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (validationType != ValidationType.NONE && !getText().isEmpty()) {
                    SwingUtilities.invokeLater(() -> validateInput());
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if (validationType != ValidationType.NONE) {
                    SwingUtilities.invokeLater(() -> validateInput());
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                if (validationType != ValidationType.NONE) {
                    SwingUtilities.invokeLater(() -> validateInput());
                }
            }
        });

        // Create helper and error labels
        errorLabel = new JLabel();
        errorLabel.setFont(UIConstants.Fonts.CAPTION);
        errorLabel.setForeground(UIConstants.Colors.ERROR);
        errorLabel.setVisible(false);

        helperLabel = new JLabel();
        helperLabel.setFont(UIConstants.Fonts.CAPTION);
        helperLabel.setForeground(UIConstants.Colors.TEXT_SECONDARY);
    }

    private void validateInput() {
        String text = getText().trim();
        boolean isValid = true;
        String error = "";

        switch (validationType) {
            case REQUIRED:
                isValid = ValidationUtils.isNotEmpty(text);
                error = isValid ? "" : "Este campo es requerido";
                break;
            case EMAIL:
                isValid = ValidationUtils.isValidEmail(text);
                error = isValid ? "" : "Formato de email inválido";
                break;
            case CEDULA:
                isValid = ValidationUtils.isValidCedula(text);
                error = isValid ? "" : "Cédula debe tener 11 dígitos";
                break;
            case PHONE:
                isValid = ValidationUtils.isValidPhone(text);
                error = isValid ? "" : "Teléfono debe tener 10 dígitos";
                break;
            case NUMBER:
                isValid = ValidationUtils.isValidInteger(text) || ValidationUtils.isValidDouble(text);
                error = isValid ? "" : "Debe ser un número válido";
                break;
            case CUSTOM:
                if (customValidator != null) {
                    isValid = customValidator.validate(text);
                    error = isValid ? "" : errorMessage;
                }
                break;
        }

        setValidationState(isValid ? ValidationState.VALID : ValidationState.ERROR, error);
    }

    public void setValidationState(ValidationState state, String message) {
        this.validationState = state;
        this.errorMessage = message;

        switch (state) {
            case VALID:
                borderColor = UIConstants.Colors.SUCCESS;
                if (errorLabel != null) {
                    errorLabel.setVisible(false);
                }
                break;
            case ERROR:
                borderColor = UIConstants.Colors.ERROR;
                if (errorLabel != null) {
                    errorLabel.setText(message);
                    errorLabel.setVisible(true);
                }
                break;
            case WARNING:
                borderColor = UIConstants.Colors.WARNING;
                break;
            case NONE:
            default:
                borderColor = isFocused ? focusColor : UIConstants.Colors.BORDER;
                if (errorLabel != null) {
                    errorLabel.setVisible(false);
                }
                break;
        }

        repaint();
    }

    private Color getStateColor() {
        switch (validationState) {
            case VALID:
                return UIConstants.Colors.SUCCESS;
            case ERROR:
                return UIConstants.Colors.ERROR;
            case WARNING:
                return UIConstants.Colors.WARNING;
            default:
                return UIConstants.Colors.BORDER;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        // Draw background
        g2.setColor(getBackground());
        g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), borderRadius, borderRadius));

        // Draw border
        g2.setColor(borderColor);
        g2.setStroke(new BasicStroke(isFocused ? 2 : 1));
        g2.draw(new RoundRectangle2D.Float(
                isFocused ? 1 : 0.5f,
                isFocused ? 1 : 0.5f,
                getWidth() - (isFocused ? 2 : 1),
                getHeight() - (isFocused ? 2 : 1),
                borderRadius,
                borderRadius
        ));

        // Draw icons
        paintIcons(g2);

        super.paintComponent(g);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // Draw placeholder
        if (getText().isEmpty() && !placeholder.isEmpty()) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

            Insets insets = getInsets();
            FontMetrics fm = g.getFontMetrics();
            int x = insets.left + (prefixIcon != null ? UIConstants.IconSize.MEDIUM + UIConstants.Spacing.SM : 0);
            int y = (getHeight() + fm.getAscent() - fm.getDescent()) / 2;

            g2.setColor(UIConstants.Colors.TEXT_HINT);
            g2.drawString(placeholder, x, y);
        }
    }

    private void paintIcons(Graphics2D g2) {
        if (prefixIcon != null) {
            Image prefix = ((ImageIcon) prefixIcon).getImage();
            int y = (getHeight() - prefixIcon.getIconHeight()) / 2;
            g2.drawImage(prefix, UIConstants.Spacing.MD, y, this);
        }

        if (suffixIcon != null) {
            Image suffix = ((ImageIcon) suffixIcon).getImage();
            int y = (getHeight() - suffixIcon.getIconHeight()) / 2;
            int x = getWidth() - suffixIcon.getIconWidth() - UIConstants.Spacing.MD;
            g2.drawImage(suffix, x, y, this);
        }
    }

    private void updateBorder() {
        int left = UIConstants.Spacing.MD;
        int right = UIConstants.Spacing.MD;

        if (prefixIcon != null) {
            left = prefixIcon.getIconWidth() + UIConstants.Spacing.LG;
        }
        if (suffixIcon != null) {
            right = suffixIcon.getIconWidth() + UIConstants.Spacing.LG;
        }

        setBorder(BorderFactory.createEmptyBorder(
                UIConstants.Spacing.SM, left, UIConstants.Spacing.SM, right
        ));
    }

    // Public API
    public boolean isValid() {
        validateInput();
        return validationState == ValidationState.VALID || validationState == ValidationState.NONE;
    }

    public JPanel getFieldPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(false);
        panel.add(this, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 2));
        bottomPanel.setOpaque(false);

        if (helperText != null && !helperText.isEmpty()) {
            helperLabel.setText(helperText);
            bottomPanel.add(helperLabel);
        }

        bottomPanel.add(errorLabel);
        panel.add(bottomPanel, BorderLayout.SOUTH);

        return panel;
    }

    // Getters and Setters
    public String getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
        repaint();
    }

    public Icon getPrefixIcon() {
        return prefixIcon;
    }

    public void setPrefixIcon(Icon prefixIcon) {
        this.prefixIcon = prefixIcon;
        updateBorder();
        repaint();
    }

    public Icon getSuffixIcon() {
        return suffixIcon;
    }

    public void setSuffixIcon(Icon suffixIcon) {
        this.suffixIcon = suffixIcon;
        updateBorder();
        repaint();
    }

    public ValidationType getValidationType() {
        return validationType;
    }

    public void setValidationType(ValidationType validationType) {
        this.validationType = validationType;
    }

    public void setCustomValidator(ValidationCallback validator) {
        this.validationType = ValidationType.CUSTOM;
        this.customValidator = validator;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getHelperText() {
        return helperText;
    }

    public void setHelperText(String helperText) {
        this.helperText = helperText;
        if (helperLabel != null) {
            helperLabel.setText(helperText);
        }
    }

    public int getBorderRadius() {
        return borderRadius;
    }

    public void setBorderRadius(int borderRadius) {
        this.borderRadius = borderRadius;
        repaint();
    }
}
