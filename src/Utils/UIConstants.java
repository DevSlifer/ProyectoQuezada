package Utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

/**
 * UI Constants for consistent styling across the application.
 * Implements modern Material Design-inspired color palette and typography.
 *
 * @author ProyectoQuezada Team
 */
public final class UIConstants {

    private UIConstants() {
        throw new UnsupportedOperationException("UIConstants is a utility class and cannot be instantiated");
    }

    // Modern Color Palette - Material Design Inspired
    public static final class Colors {
        // Primary colors
        public static final Color PRIMARY = new Color(33, 150, 243);        // Modern Blue
        public static final Color PRIMARY_DARK = new Color(25, 118, 210);   // Darker Blue
        public static final Color PRIMARY_LIGHT = new Color(100, 181, 246); // Lighter Blue

        // Secondary colors
        public static final Color SECONDARY = new Color(255, 152, 0);       // Orange
        public static final Color SECONDARY_DARK = new Color(245, 124, 0);  // Darker Orange
        public static final Color SECONDARY_LIGHT = new Color(255, 183, 77);// Lighter Orange

        // Accent colors
        public static final Color ACCENT = new Color(76, 175, 80);          // Green
        public static final Color ACCENT_DARK = new Color(56, 142, 60);     // Darker Green

        // Backgrounds
        public static final Color BACKGROUND = new Color(250, 250, 250);    // Light Gray
        public static final Color SURFACE = Color.WHITE;
        public static final Color CARD_BACKGROUND = Color.WHITE;
        public static final Color INPUT_BACKGROUND = new Color(245, 245, 245);

        // Text colors
        public static final Color TEXT_PRIMARY = new Color(33, 33, 33);     // Almost Black
        public static final Color TEXT_SECONDARY = new Color(117, 117, 117);// Medium Gray
        public static final Color TEXT_DISABLED = new Color(189, 189, 189); // Light Gray
        public static final Color TEXT_HINT = new Color(158, 158, 158);     // Hint Gray

        // State colors
        public static final Color SUCCESS = new Color(76, 175, 80);         // Green
        public static final Color ERROR = new Color(244, 67, 54);           // Red
        public static final Color WARNING = new Color(255, 152, 0);         // Orange
        public static final Color INFO = new Color(33, 150, 243);           // Blue

        // Borders
        public static final Color BORDER = new Color(224, 224, 224);        // Light Border
        public static final Color BORDER_FOCUS = PRIMARY;                   // Focused Border
        public static final Color BORDER_ERROR = ERROR;                     // Error Border

        // Hover states
        public static final Color HOVER_OVERLAY = new Color(0, 0, 0, 10);   // Slight dark overlay
        public static final Color RIPPLE_EFFECT = new Color(255, 255, 255, 100);

        // Shadows
        public static final Color SHADOW_LIGHT = new Color(0, 0, 0, 20);
        public static final Color SHADOW_MEDIUM = new Color(0, 0, 0, 40);
        public static final Color SHADOW_DARK = new Color(0, 0, 0, 60);

        private Colors() {}
    }

    // Typography
    public static final class Fonts {
        public static final Font TITLE = new Font("Segoe UI", Font.BOLD, 24);
        public static final Font SUBTITLE = new Font("Segoe UI", Font.BOLD, 18);
        public static final Font HEADING = new Font("Segoe UI", Font.BOLD, 16);
        public static final Font BODY = new Font("Segoe UI", Font.PLAIN, 14);
        public static final Font BODY_BOLD = new Font("Segoe UI", Font.BOLD, 14);
        public static final Font CAPTION = new Font("Segoe UI", Font.PLAIN, 12);
        public static final Font BUTTON = new Font("Segoe UI", Font.BOLD, 14);
        public static final Font INPUT = new Font("Segoe UI", Font.PLAIN, 14);

        private Fonts() {}
    }

    // Spacing
    public static final class Spacing {
        public static final int XS = 4;
        public static final int SM = 8;
        public static final int MD = 16;
        public static final int LG = 24;
        public static final int XL = 32;
        public static final int XXL = 48;

        private Spacing() {}
    }

    // Border Radius
    public static final class BorderRadius {
        public static final int SMALL = 4;
        public static final int MEDIUM = 8;
        public static final int LARGE = 12;
        public static final int XLARGE = 16;
        public static final int ROUND = 999; // For pill-shaped buttons

        private BorderRadius() {}
    }

    // Component Dimensions
    public static final class Dimensions {
        // Buttons
        public static final int BUTTON_HEIGHT = 40;
        public static final int BUTTON_HEIGHT_SMALL = 32;
        public static final int BUTTON_HEIGHT_LARGE = 48;
        public static final Dimension BUTTON_SIZE = new Dimension(120, BUTTON_HEIGHT);
        public static final Dimension BUTTON_SIZE_WIDE = new Dimension(200, BUTTON_HEIGHT);

        // Input fields
        public static final int INPUT_HEIGHT = 40;
        public static final int INPUT_HEIGHT_SMALL = 32;
        public static final Dimension INPUT_SIZE = new Dimension(250, INPUT_HEIGHT);
        public static final Dimension INPUT_SIZE_WIDE = new Dimension(350, INPUT_HEIGHT);

        // Tables
        public static final int TABLE_ROW_HEIGHT = 40;
        public static final int TABLE_HEADER_HEIGHT = 45;

        private Dimensions() {}
    }

    // Borders
    public static final class Borders {
        public static final Border EMPTY = BorderFactory.createEmptyBorder();
        public static final Border PADDING_SM = BorderFactory.createEmptyBorder(
                Spacing.SM, Spacing.SM, Spacing.SM, Spacing.SM);
        public static final Border PADDING_MD = BorderFactory.createEmptyBorder(
                Spacing.MD, Spacing.MD, Spacing.MD, Spacing.MD);
        public static final Border PADDING_LG = BorderFactory.createEmptyBorder(
                Spacing.LG, Spacing.LG, Spacing.LG, Spacing.LG);

        public static final Border LINE = BorderFactory.createLineBorder(Colors.BORDER, 1);
        public static final Border LINE_FOCUS = BorderFactory.createLineBorder(Colors.BORDER_FOCUS, 2);
        public static final Border LINE_ERROR = BorderFactory.createLineBorder(Colors.BORDER_ERROR, 2);

        public static final Border ROUNDED = BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Colors.BORDER, 1, true),
                BorderFactory.createEmptyBorder(Spacing.SM, Spacing.MD, Spacing.SM, Spacing.MD)
        );

        private Borders() {}
    }

    // Animation Durations (milliseconds)
    public static final class Animation {
        public static final int FAST = 150;
        public static final int NORMAL = 300;
        public static final int SLOW = 500;
        public static final int RIPPLE = 700;

        private Animation() {}
    }

    // Shadow Elevations
    public static final class Elevation {
        public static final int FLAT = 0;
        public static final int LOW = 2;
        public static final int MEDIUM = 4;
        public static final int HIGH = 8;
        public static final int HIGHEST = 16;

        private Elevation() {}
    }

    // Icon Sizes
    public static final class IconSize {
        public static final int SMALL = 16;
        public static final int MEDIUM = 24;
        public static final int LARGE = 32;
        public static final int XLARGE = 48;

        private IconSize() {}
    }

    // Z-Index (for layering)
    public static final class ZIndex {
        public static final int BACKGROUND = 0;
        public static final int CONTENT = 1;
        public static final int DROPDOWN = 100;
        public static final int MODAL = 200;
        public static final int TOAST = 300;
        public static final int TOOLTIP = 400;

        private ZIndex() {}
    }

    // Transition Effects
    public static final class Transitions {
        public static final String EASE = "ease";
        public static final String EASE_IN = "ease-in";
        public static final String EASE_OUT = "ease-out";
        public static final String EASE_IN_OUT = "ease-in-out";

        private Transitions() {}
    }
}
