package org.jarego.nbplugin.jswingfixes;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import javax.swing.plaf.synth.Region;
import javax.swing.plaf.synth.SynthLookAndFeel;
import javax.swing.plaf.synth.SynthStyleFactory;

public class ExtendedLookAndFeel {
	public static void applyLookAndFeelExtended() {
		switch (UIManager.getLookAndFeel().getClass().getName()) {
			case "com.sun.java.swing.plaf.gtk.GTKLookAndFeel":
				installGTKMenuBorders();
				UIManager.getDefaults().put("MenuUI",
						ExtendedMenuUI.class.getName());
				UIManager.getDefaults().put("ColorChooserUI",
						"javax.swing.plaf.basic.BasicColorChooserUI");
				break;
		}
		UIManager.getDefaults().put("PopupMenuUI",
				ExtendedPopupMenuUI.class.getName());
	}
	
	public static void unapplyGTKLookAndFeelExtended() {
		try {
			LookAndFeel laf = UIManager.getLookAndFeel();
			Class<?> lafClass = laf.getClass();
			if (lafClass.getName().equals(ExtendedLookAndFeel.class.getName())) {
				Method m = lafClass.getSuperclass().getDeclaredMethod(
						"getGTKStyleFactory", new Class<?>[] {});
				m.setAccessible(true);
				SynthLookAndFeel.setStyleFactory((SynthStyleFactory)m.invoke(laf, new Object[] {}));
				m.setAccessible(false);
			}
		} catch (IllegalAccessException | IllegalArgumentException | NoSuchMethodException |
				SecurityException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}
	}
	
	private static void installGTKMenuBorders() {
		try {
			gtkChangeYThikcness(SynthLookAndFeel.getStyle(null, Region.POPUP_MENU), 1);
			gtkChangeXThikcness(SynthLookAndFeel.getStyle(null, Region.POPUP_MENU), 1);
			gtkChangeYThikcness(SynthLookAndFeel.getStyle(null, Region.POPUP_MENU_SEPARATOR), 1);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	private static void gtkChangeYThikcness(Object style, int border) throws Exception {
		Field field = style.getClass().getDeclaredField("yThickness");
		field.setAccessible(true);
		field.setInt(style, Math.max(border, field.getInt(style)));
		field.setAccessible(false);
	}
	private static void gtkChangeXThikcness(Object style, int border) throws Exception {
		Field field = style.getClass().getDeclaredField("xThickness");
		field.setAccessible(true);
		field.setInt(style, Math.max(border, field.getInt(style)));
		field.setAccessible(false);
	}
}
