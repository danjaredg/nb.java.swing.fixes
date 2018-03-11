package org.jarego.nbplugin.jswingfixes;

import javax.swing.SwingUtilities;
import org.openide.modules.ModuleInstall;

public class Installer extends ModuleInstall {
	@Override
	public void restored() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				ExtendedLookAndFeel.applyLookAndFeelExtended();
			}
		});
	}
}
