package Views.swing;

import Utils.UIConstants;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * Modern table cell renderer with alternating row colors and better styling.
 * Improves readability and visual appeal of table data.
 *
 * @author ProyectoQuezada Team
 */
public class ModernTableCellRenderer extends DefaultTableCellRenderer {

    private static final Color ALTERNATE_ROW_COLOR = new Color(248, 248, 248);
    private static final Color SELECTED_ROW_COLOR = UIConstants.Colors.PRIMARY_LIGHT;
    private static final Color SELECTED_ROW_FOREGROUND = UIConstants.Colors.TEXT_PRIMARY;
    private static final Color HOVER_COLOR = new Color(240, 240, 240);

    private boolean useAlternateRowColors = true;
    private int horizontalPadding = UIConstants.Spacing.MD;
    private int verticalPadding = UIConstants.Spacing.SM;

    public ModernTableCellRenderer() {
        setOpaque(true);
        setFont(UIConstants.Fonts.BODY);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {

        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        // Set padding
        setBorder(BorderFactory.createEmptyBorder(
                verticalPadding,
                horizontalPadding,
                verticalPadding,
                horizontalPadding
        ));

        // Set colors
        if (isSelected) {
            setBackground(SELECTED_ROW_COLOR);
            setForeground(SELECTED_ROW_FOREGROUND);
        } else {
            if (useAlternateRowColors && row % 2 == 1) {
                setBackground(ALTERNATE_ROW_COLOR);
            } else {
                setBackground(Color.WHITE);
            }
            setForeground(UIConstants.Colors.TEXT_PRIMARY);
        }

        // Set font
        setFont(UIConstants.Fonts.BODY);

        // Set text alignment based on data type
        if (value instanceof Number) {
            setHorizontalAlignment(SwingConstants.RIGHT);
        } else if (value instanceof Boolean) {
            setHorizontalAlignment(SwingConstants.CENTER);
        } else {
            setHorizontalAlignment(SwingConstants.LEFT);
        }

        return this;
    }

    public boolean isUseAlternateRowColors() {
        return useAlternateRowColors;
    }

    public void setUseAlternateRowColors(boolean useAlternateRowColors) {
        this.useAlternateRowColors = useAlternateRowColors;
    }

    public int getHorizontalPadding() {
        return horizontalPadding;
    }

    public void setHorizontalPadding(int horizontalPadding) {
        this.horizontalPadding = horizontalPadding;
    }

    public int getVerticalPadding() {
        return verticalPadding;
    }

    public void setVerticalPadding(int verticalPadding) {
        this.verticalPadding = verticalPadding;
    }

    /**
     * Applies modern styling to a JTable.
     *
     * @param table The table to style
     */
    public static void applyModernStyle(JTable table) {
        // Set renderer for all columns
        ModernTableCellRenderer renderer = new ModernTableCellRenderer();
        table.setDefaultRenderer(Object.class, renderer);

        // Table appearance
        table.setFont(UIConstants.Fonts.BODY);
        table.setRowHeight(UIConstants.Dimensions.TABLE_ROW_HEIGHT);
        table.setShowVerticalLines(false);
        table.setShowHorizontalLines(true);
        table.setGridColor(UIConstants.Colors.BORDER);
        table.setSelectionBackground(SELECTED_ROW_COLOR);
        table.setSelectionForeground(SELECTED_ROW_FOREGROUND);
        table.setIntercellSpacing(new Dimension(0, 0));

        // Header styling
        if (table.getTableHeader() != null) {
            table.getTableHeader().setFont(UIConstants.Fonts.BODY_BOLD);
            table.getTableHeader().setBackground(UIConstants.Colors.PRIMARY);
            table.getTableHeader().setForeground(Color.WHITE);
            table.getTableHeader().setPreferredSize(new Dimension(
                    table.getTableHeader().getPreferredSize().width,
                    UIConstants.Dimensions.TABLE_HEADER_HEIGHT
            ));
            table.getTableHeader().setReorderingAllowed(false);

            // Custom header renderer
            table.getTableHeader().setDefaultRenderer(new ModernTableHeaderRenderer());
        }

        // Selection mode
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    /**
     * Modern table header renderer.
     */
    static class ModernTableHeaderRenderer extends DefaultTableCellRenderer {

        public ModernTableHeaderRenderer() {
            setHorizontalAlignment(SwingConstants.LEFT);
            setOpaque(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {

            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            setBackground(UIConstants.Colors.PRIMARY);
            setForeground(Color.WHITE);
            setFont(UIConstants.Fonts.BODY_BOLD);
            setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createMatteBorder(0, 0, 2, 0, UIConstants.Colors.PRIMARY_DARK),
                    BorderFactory.createEmptyBorder(
                            UIConstants.Spacing.SM,
                            UIConstants.Spacing.MD,
                            UIConstants.Spacing.SM,
                            UIConstants.Spacing.MD
                    )
            ));

            return this;
        }
    }
}
