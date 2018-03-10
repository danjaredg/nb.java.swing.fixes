package org.jarego.nbplugin.jswingfixes;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Rectangle;
import javax.accessibility.Accessible;
import javax.swing.ButtonModel;
import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.MenuElement;
import javax.swing.UIManager;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.UIResource;
import javax.swing.plaf.basic.BasicMenuUI;
import sun.swing.MenuItemLayoutHelper;

public class ExtendedMenuUI extends BasicMenuUI {

	public static ComponentUI createUI(JComponent x) {
		ComponentUI cui = UIManager.getLookAndFeel().getDefaults().getUI(x);
		if (cui instanceof BasicMenuUI) {
			return new ExtendedMenuUI((BasicMenuUI) cui);
		} else {
			return cui;
		}
	}

	private final BasicMenuUI menuBarUI;

	public ExtendedMenuUI(BasicMenuUI menuUI) {
		this.menuBarUI = menuUI;
	}

	@Override
	public void installUI(JComponent c) {
		menuBarUI.installUI(c);
		if (c instanceof JMenu) {
			JMenu menu = (JMenu) c;
			if (menu.getParent() != null && menu.getParent() instanceof JMenuBar) {
				menu.setMargin(new Insets(0, 6, 0, 6));
				installOtherDefaults();
			}
		}
	}
	
	private void installOtherDefaults() {
		String prefix = "Menu";
		acceleratorFont = UIManager.getFont("MenuItem.acceleratorFont");
		if (acceleratorFont == null) {
			acceleratorFont = UIManager.getFont("MenuItem.font");
		}
		if (selectionBackground == null
				|| selectionBackground instanceof UIResource) {
			selectionBackground
					= UIManager.getColor(prefix + ".selectionBackground");
		}
		if (selectionForeground == null
				|| selectionForeground instanceof UIResource) {
			selectionForeground
					= UIManager.getColor(prefix + ".selectionForeground");
		}
		if (disabledForeground == null
				|| disabledForeground instanceof UIResource) {
			disabledForeground
					= UIManager.getColor(prefix + ".disabledForeground");
		}
		if (acceleratorForeground == null
				|| acceleratorForeground instanceof UIResource) {
			acceleratorForeground
					= UIManager.getColor(prefix + ".acceleratorForeground");
		}
		if (acceleratorSelectionForeground == null
				|| acceleratorSelectionForeground instanceof UIResource) {
			acceleratorSelectionForeground
					= UIManager.getColor(prefix + ".acceleratorSelectionForeground");
		}
		acceleratorDelimiter
				= UIManager.getString("MenuItem.acceleratorDelimiter");
		if (acceleratorDelimiter == null) {
			acceleratorDelimiter = "+";
		}
	}

	@Override
	public void uninstallUI(JComponent c) {
		menuBarUI.uninstallUI(c);
	}

	@Override
	public Dimension getMinimumSize(JComponent c) {
		return menuBarUI.getMinimumSize(c);
	}

	@Override
	public Dimension getMaximumSize(JComponent c) {
		return menuBarUI.getMaximumSize(c);
	}

	@Override
	public Dimension getPreferredSize(JComponent c) {
		return menuBarUI.getPreferredSize(c);
	}

	@Override
	public void update(Graphics g, JComponent c) {
		menuBarUI.update(g, c);
		if (c instanceof JMenu) {
			JMenu menu = (JMenu) c;
			if (menu.getParent() != null && menu.getParent() instanceof JMenuBar) {
				ButtonModel model = menu.getModel();
				if (model.isSelected()) {
					repaintSelectedMenu(g, menu);
				}
			}
		}
	}
	
	private void repaintSelectedMenu(Graphics g, JMenu menu) {
		Rectangle viewRect = new Rectangle(0, 0,
							menu.getWidth(), menu.getHeight());
		Insets insets = menu.getInsets();
		if (insets != null) {
			viewRect.x += insets.left;
			viewRect.y += insets.top;
			viewRect.width -= (insets.right + viewRect.x);
			viewRect.height -= (insets.bottom + viewRect.y);
		}
		paintBackground(g, menu, selectionBackground);
		MenuItemLayoutHelper lh = new MenuItemLayoutHelper(menu, checkIcon,
				arrowIcon, viewRect, defaultTextIconGap, acceleratorDelimiter,
				menu.getComponentOrientation().isLeftToRight(), menu.getFont(),
				acceleratorFont, MenuItemLayoutHelper.useCheckAndArrow(menu),
				getPropertyPrefix());
		MenuItemLayoutHelper.LayoutResult lr = lh.layoutMenuItem();
		paintText(g, menu, lr.getTextRect(), lh.getText());
	}

	@Override
	public void paint(Graphics g, JComponent c) {
		menuBarUI.paint(g, c);
	}

	@Override
	public MenuElement[] getPath() {
		return menuBarUI.getPath();
	}

	@Override
	public boolean contains(JComponent c, int x, int y) {
		return menuBarUI.contains(c, x, y);
	}

	@Override
	public int getBaseline(JComponent c, int width, int height) {
		return menuBarUI.getBaseline(c, width, height);
	}

	@Override
	public Component.BaselineResizeBehavior getBaselineResizeBehavior(JComponent c) {
		return menuBarUI.getBaselineResizeBehavior(c);
	}

	@Override
	public int getAccessibleChildrenCount(JComponent c) {
		return menuBarUI.getAccessibleChildrenCount(c);
	}

	@Override
	public Accessible getAccessibleChild(JComponent c, int i) {
		return menuBarUI.getAccessibleChild(c, i);
	}
}
