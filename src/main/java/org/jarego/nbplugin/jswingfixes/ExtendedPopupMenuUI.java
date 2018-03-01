package org.jarego.nbplugin.jswingfixes;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import javax.accessibility.Accessible;
import javax.swing.JComponent;
import javax.swing.JPopupMenu;
import javax.swing.Popup;
import javax.swing.UIManager;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicPopupMenuUI;

public class ExtendedPopupMenuUI extends BasicPopupMenuUI {
	public static ComponentUI createUI(JComponent x) {
		ComponentUI bpmu = UIManager.getLookAndFeel().getDefaults().getUI(x);
		if (bpmu instanceof BasicPopupMenuUI)
			return new ExtendedPopupMenuUI((BasicPopupMenuUI)bpmu);
		else
			return bpmu;
	}
	
	private final BasicPopupMenuUI basicPopupMenuUI;

	public ExtendedPopupMenuUI(BasicPopupMenuUI basicPopupMenuUI) {
		this.basicPopupMenuUI = basicPopupMenuUI;
	}
	
	@Override
	public void installUI(JComponent c) {
		basicPopupMenuUI.installUI(c);
		if (c instanceof JPopupMenu) {
			JPopupMenu popup = (JPopupMenu)c;
			popup.setLayout(new ExtendedPopupMenuLayout());
		}
	}
	@Override
	public void installDefaults() {
		basicPopupMenuUI.installDefaults();
	}
	@Override
	public void uninstallUI(JComponent c) {
		basicPopupMenuUI.uninstallUI(c);
	}
	@Override
	public boolean isPopupTrigger(MouseEvent e) {
		return basicPopupMenuUI.isPopupTrigger(e);
	}
	@Override
	public Popup getPopup(JPopupMenu popup, int x, int y) {
		return basicPopupMenuUI.getPopup(popup, x, y);
	}
	@Override
	public void paint(Graphics g, JComponent c) {
		basicPopupMenuUI.paint(g, c);
	}
	@Override
	public void update(Graphics g, JComponent c) {
		basicPopupMenuUI.update(g, c);
	}
	@Override
	public Dimension getPreferredSize(JComponent c) {
		return basicPopupMenuUI.getPreferredSize(c);
	}
	@Override
	public Dimension getMinimumSize(JComponent c) {
		return basicPopupMenuUI.getMinimumSize(c);
	}
	@Override
	public Dimension getMaximumSize(JComponent c) {
		return basicPopupMenuUI.getMaximumSize(c);
	}
	@Override
	public boolean contains(JComponent c, int x, int y) {
		return basicPopupMenuUI.contains(c, x, y);
	}
	@Override
	public int getBaseline(JComponent c, int width, int height) {
		return basicPopupMenuUI.getBaseline(c, width, height);
	}
	@Override
	public Component.BaselineResizeBehavior getBaselineResizeBehavior(JComponent c) {
		return basicPopupMenuUI.getBaselineResizeBehavior(c);
	}
	@Override
	public int getAccessibleChildrenCount(JComponent c) {
		return basicPopupMenuUI.getAccessibleChildrenCount(c);
	}
	@Override
	public Accessible getAccessibleChild(JComponent c, int i) {
		return basicPopupMenuUI.getAccessibleChild(c, i);
	}
}
