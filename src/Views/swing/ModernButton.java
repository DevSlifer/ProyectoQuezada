package Views.swing;

import Utils.UIConstants;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

/**
 * Modern Material Design-inspired button with ripple effect and hover states.
 * Enhanced with better visual feedback and accessibility.
 *
 * @author ProyectoQuezada Team
 */
public class ModernButton extends JButton {

    public enum ButtonStyle {
        PRIMARY,
        SECONDARY,
        SUCCESS,
        WARNING,
        ERROR,
        OUTLINED,
        TEXT
    }

    private Animator rippleAnimator;
    private int targetSize;
    private float animatSize;
    private Point pressedPoint;
    private float rippleAlpha;
    private Color rippleColor = UIConstants.Colors.RIPPLE_EFFECT;

    private boolean isHovered = false;
    private float hoverAlpha = 0f;
    private Animator hoverAnimator;

    private ButtonStyle buttonStyle = ButtonStyle.PRIMARY;
    private int borderRadius = UIConstants.BorderRadius.MEDIUM;
    private boolean isEnabled = true;

    public ModernButton() {
        this("Button", ButtonStyle.PRIMARY);
    }

    public ModernButton(String text) {
        this(text, ButtonStyle.PRIMARY);
    }

    public ModernButton(String text, ButtonStyle style) {
        super(text);
        this.buttonStyle = style;
        initializeComponent();
    }

    private void initializeComponent() {
        setContentAreaFilled(false);
        setBorder(new EmptyBorder(
                UIConstants.Spacing.SM,
                UIConstants.Spacing.LG,
                UIConstants.Spacing.SM,
                UIConstants.Spacing.LG
        ));
        setFont(UIConstants.Fonts.BUTTON);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setFocusPainted(false);

        applyStyle();
        initializeAnimations();
        addMouseListeners();
    }

    private void applyStyle() {
        switch (buttonStyle) {
            case PRIMARY:
                setBackground(UIConstants.Colors.PRIMARY);
                setForeground(Color.WHITE);
                rippleColor = new Color(255, 255, 255, 100);
                break;
            case SECONDARY:
                setBackground(UIConstants.Colors.SECONDARY);
                setForeground(Color.WHITE);
                rippleColor = new Color(255, 255, 255, 100);
                break;
            case SUCCESS:
                setBackground(UIConstants.Colors.SUCCESS);
                setForeground(Color.WHITE);
                rippleColor = new Color(255, 255, 255, 100);
                break;
            case WARNING:
                setBackground(UIConstants.Colors.WARNING);
                setForeground(Color.WHITE);
                rippleColor = new Color(255, 255, 255, 100);
                break;
            case ERROR:
                setBackground(UIConstants.Colors.ERROR);
                setForeground(Color.WHITE);
                rippleColor = new Color(255, 255, 255, 100);
                break;
            case OUTLINED:
                setBackground(Color.WHITE);
                setForeground(UIConstants.Colors.PRIMARY);
                rippleColor = new Color(33, 150, 243, 50);
                break;
            case TEXT:
                setBackground(new Color(0, 0, 0, 0));
                setForeground(UIConstants.Colors.PRIMARY);
                rippleColor = new Color(33, 150, 243, 50);
                break;
        }
    }

    private void initializeAnimations() {
        // Ripple animation
        TimingTarget rippleTarget = new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                if (fraction > 0.5f) {
                    rippleAlpha = 1 - fraction;
                }
                animatSize = fraction * targetSize;
                repaint();
            }
        };
        rippleAnimator = new Animator(UIConstants.Animation.RIPPLE, rippleTarget);
        rippleAnimator.setAcceleration(0.5f);
        rippleAnimator.setDeceleration(0.5f);
        rippleAnimator.setResolution(0);

        // Hover animation
        TimingTarget hoverTarget = new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                hoverAlpha = isHovered ? fraction * 0.1f : (1 - fraction) * 0.1f;
                repaint();
            }
        };
        hoverAnimator = new Animator(UIConstants.Animation.FAST, hoverTarget);
        hoverAnimator.setResolution(0);
    }

    private void addMouseListeners() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                if (isEnabled()) {
                    targetSize = Math.max(getWidth(), getHeight()) * 2;
                    animatSize = 0;
                    pressedPoint = me.getPoint();
                    rippleAlpha = 0.5f;
                    if (rippleAnimator.isRunning()) {
                        rippleAnimator.stop();
                    }
                    rippleAnimator.start();
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (isEnabled()) {
                    isHovered = true;
                    if (!hoverAnimator.isRunning()) {
                        hoverAnimator.start();
                    }
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                isHovered = false;
                if (!hoverAnimator.isRunning()) {
                    hoverAnimator.start();
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        int width = getWidth();
        int height = getHeight();
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = img.createGraphics();

        // Enable anti-aliasing for smooth rendering
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        // Draw button background
        if (buttonStyle != ButtonStyle.TEXT) {
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, width, height, borderRadius, borderRadius);
        }

        // Draw border for outlined style
        if (buttonStyle == ButtonStyle.OUTLINED) {
            g2.setColor(UIConstants.Colors.PRIMARY);
            g2.setStroke(new BasicStroke(2));
            g2.drawRoundRect(1, 1, width - 2, height - 2, borderRadius, borderRadius);
        }

        // Draw hover overlay
        if (hoverAlpha > 0) {
            g2.setColor(new Color(0, 0, 0, (int)(hoverAlpha * 255)));
            g2.fillRoundRect(0, 0, width, height, borderRadius, borderRadius);
        }

        // Draw ripple effect
        if (pressedPoint != null && rippleAlpha > 0) {
            g2.setColor(rippleColor);
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, rippleAlpha));
            Shape rippleClip = new RoundRectangle2D.Float(0, 0, width, height, borderRadius, borderRadius);
            g2.setClip(rippleClip);
            g2.fillOval(
                (int) (pressedPoint.x - animatSize / 2),
                (int) (pressedPoint.y - animatSize / 2),
                (int) animatSize,
                (int) animatSize
            );
        }

        // Draw disabled overlay
        if (!isEnabled()) {
            g2.setColor(new Color(255, 255, 255, 180));
            g2.fillRoundRect(0, 0, width, height, borderRadius, borderRadius);
        }

        g2.dispose();
        grphcs.drawImage(img, 0, 0, null);
        super.paintComponent(grphcs);
    }

    // Getters and Setters
    public ButtonStyle getButtonStyle() {
        return buttonStyle;
    }

    public void setButtonStyle(ButtonStyle buttonStyle) {
        this.buttonStyle = buttonStyle;
        applyStyle();
        repaint();
    }

    public int getBorderRadius() {
        return borderRadius;
    }

    public void setBorderRadius(int borderRadius) {
        this.borderRadius = borderRadius;
        repaint();
    }

    public Color getRippleColor() {
        return rippleColor;
    }

    public void setRippleColor(Color rippleColor) {
        this.rippleColor = rippleColor;
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        this.isEnabled = enabled;
        setCursor(enabled ? new Cursor(Cursor.HAND_CURSOR) : new Cursor(Cursor.DEFAULT_CURSOR));
        repaint();
    }
}
