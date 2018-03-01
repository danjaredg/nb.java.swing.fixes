package org.jarego.nbplugin.jswingfixes;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

public class ExtendedPopupMenuLayout implements LayoutManager {	
	private final List<Integer> menuColumnWidth = new ArrayList<>();
	
	@Override
	public void addLayoutComponent(String name, Component comp) {
		
	}
	@Override
	public void removeLayoutComponent(Component comp) {
		
	}
	@Override
	public Dimension preferredLayoutSize(Container parent) {
		return getSize(parent);
	}
	@Override
	public Dimension minimumLayoutSize(Container parent) {
		return getSize(parent);
	}
	
	protected Dimension getSize(Container parent) {
		menuColumnWidth.clear();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Insets insets = parent.getInsets();
		int w = 0;
		int h = 0;
		int wtmp = 0;
		int htmp = insets.top;
		for (int i=0;i<parent.getComponentCount();i++) {
			Component c = parent.getComponent(i);
			Dimension cs = c.getPreferredSize();
			wtmp = Math.max(wtmp, cs.width);
			if (htmp+insets.bottom+cs.height > screenSize.height) {
				h = Math.max(h, htmp);
				menuColumnWidth.add(wtmp);
				w += wtmp;
				htmp = insets.top;
				wtmp = 0;
			}
			htmp += cs.height;
		}
		h = Math.max(h, htmp);
		menuColumnWidth.add(wtmp);
		w += wtmp;
		return new Dimension(w+insets.left+insets.right, h+insets.bottom);
	}
	
	@Override
	public void layoutContainer(Container parent) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Insets insets = parent.getInsets();
		int woffset = insets.left;
		int hoffset = insets.top;
		int wtmp = 0;
		int column = 0;
		for (int i=0;i<parent.getComponentCount();i++) {
			Component c = parent.getComponent(i);
			Dimension cs = c.getPreferredSize();
			wtmp = Math.max(wtmp, cs.width);
			if (hoffset+insets.bottom+cs.height > screenSize.height) {
				hoffset = insets.top;
				woffset = wtmp;
				column++;
			}
			c.setBounds(woffset, hoffset, menuColumnWidth.get(column), cs.height);
			hoffset += cs.height;
		}
	}
}
