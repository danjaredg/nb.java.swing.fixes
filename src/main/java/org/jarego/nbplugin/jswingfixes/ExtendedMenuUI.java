package org.jarego.nbplugin.jswingfixes;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import javax.accessibility.Accessible;
import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.MenuElement;
import javax.swing.UIManager;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicMenuUI;

public class ExtendedMenuUI extends BasicMenuUI {
	public static ComponentUI createUI(JComponent x) {
		ComponentUI cui = UIManager.getLookAndFeel().getDefaults().getUI(x);
		if (cui instanceof BasicMenuUI)
			return new ExtendedMenuUI((BasicMenuUI)cui);
		else
			return cui;
	}
	
	private final BasicMenuUI menuBarUI;
	
	public ExtendedMenuUI(BasicMenuUI menuUI) {
		this.menuBarUI = menuUI;
	}
	
	@Override
	public void installUI(JComponent c) {
		menuBarUI.installUI(c);
		if (c instanceof JMenu) {
			JMenu menu = (JMenu)c;
			if (menu.getParent() != null && menu.getParent() instanceof JMenuBar) {
				menu.setMargin(new Insets(0, 6, 0, 6));
			}
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
